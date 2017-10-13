/*
 * Copyright (C) 2014 AMIS research group, Faculty of Mathematics and Physics, Charles University in Prague, Czech Republic
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.cuni.amis.pogamut.ut2004.agent.navigation.navmesh.pathfollowing;

import cz.cuni.amis.pogamut.base3d.worldview.object.Location;
import cz.cuni.amis.pogamut.ut2004.agent.navigation.navmesh.NavMeshConstants;
import cz.cuni.amis.pogamut.ut2004.agent.navigation.navmesh.old.OldNavMesh;
import cz.cuni.amis.pogamut.ut2004.agent.navigation.navmesh.old.OldNavMeshPolygon;
import cz.cuni.amis.pogamut.ut2004.communication.messages.gbinfomessages.NavPoint;
import cz.cuni.amis.pogamut.ut2004.communication.messages.gbinfomessages.NavPointNeighbourLink;
import cz.cuni.amis.pogamut.ut2004.utils.LinkFlag;
import cz.cuni.amis.pogamut.ut2004.utils.UnrealUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.vecmath.Vector2d;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.line.Line2D;

/**
 * Module for computation of jumps. Decides if the jump is doable, determines
 * correct type and power of the jump.
 *
 * @author Bogo
 */
public class JumpModule {

    //
    // JUMP EQUATION - SINGLE JUMP
    // z = -475 * t^2 + power * t
    //
    // t = Unreal time
    //
    //
    // JUMP EQUATION - DOUBLE JUMP
    // z = -475 * t^2 + power * t + koef * (t - delay)
    //
    // koef = (power - 340) * 1.066 + 2 
    //
    // t = Unreal time
    //
    public static final double MAX_DOUBLE_JUMP_POWER = 755;
    public static final double MAX_SINGLE_JUMP_POWER = 340;

    private OldNavMesh navMesh;

    private Logger log;

    public static final double MAX_JUMP_HEIGHT = 130;
    private static final double MAX_SINGLE_JUMP_HEIGHT = 60;
    private static final double NAVMESH_Z_COORD_CORRECTION = 20;
    private static final double SPEED_BOOST_DELAY = 0.100;
    private static final double BOT_RADIUS = 70;
    private static final double JUMP_PEEK_TIME = 0.39;

    public JumpModule(OldNavMesh mesh, Logger log) {
        this.navMesh = mesh;
        this.log = log;
    }

    /**
     * Computes jump boundaries for given link, with using maximal available
     * information about environment. Stores the boundaries in the "Jump" object
     * for later use.
     *
     * @param jumpLink Link on which the jump should occur.
     * @return True if jump boundaries were successfully computed, false
     * otherwise.
     */
    public JumpBoundaries computeJumpBoundaries(NavPointNeighbourLink jumpLink) {
        if (jumpLink == null) {
            return new JumpBoundaries(null);
        }

        NavPoint startNavPoint = jumpLink.getFromNavPoint();
        NavPoint endNavPoint = jumpLink.getToNavPoint();
        Location startLocation = startNavPoint.getLocation();
        Location endLocation = endNavPoint.getLocation();

        //Get edge of the mesh + offset
        Location linkDirection3d = endLocation.sub(startLocation);
        Vector2d linkDirection = new Vector2d(linkDirection3d.x, linkDirection3d.y);

        //double startDistanceFromEdge = navMesh.getDistanceFromEdge(startLocation, linkDirection);
        BorderPoint startBorder = getBorderPoint(startLocation, endLocation);
        Location startBorderPoint = startBorder.getPoint();

        //Get landing edge of the mesh + offset
        Vector2d negatedLinkDirection = new Vector2d(linkDirection);
        negatedLinkDirection.negate();

        //double endDistanceFromEdge = navMesh.getDistanceFromEdge(endLocation, negatedLinkDirection);
        BorderPoint endBorderPoint = getBorderPoint(endLocation, startLocation);
        if (!endBorderPoint.getPoint().equals(endNavPoint.getLocation(), 1.0)) {
            //NavMesh Z coordinate correction
            endBorderPoint.setPoint(endBorderPoint.getPoint().addZ(NAVMESH_Z_COORD_CORRECTION));
        }

        //Check capability to jump between borders
        boolean borderToBorder = isJumpable(startBorderPoint, endBorderPoint.getPoint(), UnrealUtils.MAX_VELOCITY);
        if (!borderToBorder) {
            //We can't jump between the nearest possible points. Uh-oh.
            return new JumpBoundaries(jumpLink);
        }

        //Find take-off booundary
        Location testBoundary = startLocation;
        Location currentBoundary = startBorderPoint;
        boolean boundaryFound = false;
        double distanceToSearch = 0;
        do {
            boolean isJumpable = isJumpable(testBoundary, endBorderPoint.getPoint(), UnrealUtils.MAX_VELOCITY);
            if (isJumpable && distanceToSearch < BOUNDARY_THRESHOLD) {
                currentBoundary = testBoundary;
                boundaryFound = true;
            } else if (isJumpable) {
                //Move the test location far from the border
                currentBoundary = testBoundary;
                distanceToSearch /= 2;
                testBoundary = getNavMeshPoint(testBoundary, startLocation, distanceToSearch);

            } else if (distanceToSearch < BOUNDARY_THRESHOLD && distanceToSearch > 0) {
                //Take the last successfull point
                boundaryFound = true;
            } else {
                //Move the test location nearer to the border
                if (distanceToSearch == 0) {
                    distanceToSearch = testBoundary.getDistance2D(startBorderPoint);
                }
                distanceToSearch /= 2;
                testBoundary = getNavMeshPoint(testBoundary, startBorderPoint, distanceToSearch);
            }

        } while (!boundaryFound);

        return new JumpBoundaries(jumpLink, currentBoundary, startBorderPoint, startBorder.getDirection(), endBorderPoint.getPoint(), endBorderPoint.getDirection(), endLocation);
    }
    private static final int BOUNDARY_THRESHOLD = 50;

    /**
     * Gets border point of the mesh for the path between specified points.
     *
     * @param start Start of the path.
     * @param end End of the path.
     * @return Border point in the direction of the path.
     */
    public BorderPoint getBorderPoint(Location start, Location end) {
        int endPolygonId = navMesh.getPolygonId(end);
        return getBorderPoint(start, end, -1, endPolygonId, start, 0);
    }

    /**
     * Border point lays on the navigation mesh edge.
     *
     * @param start
     * @param end
     * @param pId Neighboring polygon id.
     * @return
     */
    private BorderPoint getBorderPoint(Location start, Location end, int pId, int endPolygonId, Location lastCross, int depth) {

        // get a 2D projection of ray
        Line2D ray = new Line2D(start.x, start.y, end.x, end.y);
        // get the current polygon
        if (pId < 0) {
            pId = navMesh.getPolygonId(start);
        }
        if (pId < 0 || depth > 250) {
            return new BorderPoint(start, null);
        }
        if (pId == endPolygonId) {
            return new BorderPoint(end, null);
        }

        // how to find end of navmesh?
        // 1. start on the polygon of starting location
        // 2. find where the line crosses its border
        // 3. while there is another polygon behind, repeat
        // 4. return the last cross
        int currentPolygonId = pId;
        int nextPolygonId = -1;

        // find the first cross
        Point2D cross = null, cross2 = null;
        int v1 = -1, v2 = -1;
        int c2v1 = -1, c2v2 = -1;
        double[] vertex1 = null;
        double[] vertex2 = null;
        int[] polygon = navMesh.getPolygon(currentPolygonId);
        boolean foundFirstCross = false;
        for (int i = 0; i < polygon.length; i++) {
            v1 = polygon[i];
            v2 = polygon[((i == polygon.length - 1) ? 0 : i + 1)];
            vertex1 = navMesh.getVertex(v1);
            vertex2 = navMesh.getVertex(v2);
            Line2D edge = new Line2D(vertex1[0], vertex1[1], vertex2[0], vertex2[1]);
            cross = ray.getIntersection(edge);

            if (cross != null) {
                if (((cross.x <= Math.max(edge.p1.x, edge.p2.x) && cross.x >= Math.min(edge.p1.x, edge.p2.x)) || Math.abs(cross.x - edge.p1.x) < 0.0001)
                        && ((cross.x <= Math.max(ray.p1.x, ray.p2.x) && cross.x >= Math.min(ray.p1.x, ray.p2.x)) || Math.abs(cross.x - ray.p1.x) < 0.0001)) {
                    // is a cross!
                    if (foundFirstCross) {
                        break;
                    } else {
                        cross2 = cross;
                        c2v1 = v1;
                        c2v2 = v2;
                        foundFirstCross = true;
                    }
                } else {
                    // it's not a cross
                    cross = null;
                }
            }
        }
        // now we have the cross.

        if (cross2 == null) {
            return new BorderPoint(start, null);
        } else if (cross == null) {
            cross = cross2;
            v1 = c2v1;
            v2 = c2v2;
            vertex1 = navMesh.getVertex(v1);
            vertex2 = navMesh.getVertex(v2);
        } else {
            double distToCross = end.getDistance2D(new Location(cross.x, cross.y));
            double distToCross2 = end.getDistance2D(new Location(cross2.x, cross2.y));
            if (distToCross > distToCross2) {
                cross = cross2;
                v1 = c2v1;
                v2 = c2v2;
                vertex1 = navMesh.getVertex(v1);
                vertex2 = navMesh.getVertex(v2);
            }
        }

        // is there another polygon behind?
        nextPolygonId = navMesh.getNeighbourPolygon(currentPolygonId, v1, v2);

        Location vertex1Loc = new Location(vertex1);
        Location vertex2Loc = new Location(vertex2);
        Location crossLocation = new Location(cross.x, cross.y);

        double koef = vertex1Loc.getDistance2D(crossLocation) / vertex1Loc.getDistance2D(vertex2Loc);

        Location border = vertex1Loc.interpolate(vertex2Loc, koef);

        if (nextPolygonId == -1) {
            // there is no polygon. we return the cross

            return new BorderPoint(border.addZ(NavMeshConstants.liftPolygonLocation), vertex2Loc.sub(vertex1Loc));

        } else {
            // if there is another polygon, we return recursively border in that direction

            if (border.getDistance2D(lastCross) < 2.0) {
                // move a little so it is in the neighbour polygon
                Vector2D directionVector = new Vector2D(end.x - start.x, end.y - start.y);
                directionVector.normalize();
                border = border.addX(directionVector.getX() * 3);
                border = border.addY(directionVector.getY() * 3);
            }

            log.log(Level.INFO, "getBorderPoint(): Border location: {0} Next polygon: {1}", new Object[]{border, nextPolygonId});
            return getBorderPoint(border.addZ(NavMeshConstants.liftPolygonLocation), end, nextPolygonId, endPolygonId, border, ++depth);
        }
    }

    /**
     * Decides whether jump is needed for following given link.
     *
     * @param link Link to analyze.
     * @return True if jump is needed for successful following of given link.
     */
    public boolean needsJump(NavPointNeighbourLink link) {
        if (link == null) {
            //It is NavMesh link, no jump is needed
            return false;
        } else {
            //Off mesh link
            if ((link.getFlags() & LinkFlag.JUMP.get()) != 0) {
                //Jump flag present
                return true;
            }
            if (link.isForceDoubleJump()) {
                //Flag for double jump is present
                return true;
            }
            if (link.getNeededJump() != null) {
                //Jump information is present
                return true;
            }
        }

        return false;
    }
    
    // =======
    // JUMPING
    // =======

    /**
     * Compute power for given jump.
     *
     * @param start Start location of the jump.
     * @param boundaries Jump boundaries for given link.
     * @param velocity Current velocity of the bot.
     * @param jumpAngleCos Cos of angle of direction of the movement to the
     * direction of the link.
     * @return Power of the jump.
     */
    public Double computeJump(Location start, JumpBoundaries boundaries, double velocity, double jumpAngleCos) {
    	Location end = boundaries.getLandingTarget();
    	double distance2d = getDistance2D(start, boundaries.getLandingTarget(), jumpAngleCos);
    	double targetZ = end.z - start.z;
        debug("Jump {0} --(D3D:{1}|D2D:{2}|D2DC:{3}|DZ:{4})--> {5} [Velocity {6}]", new Object[]{start, end.getDistance(start), end.getDistance2D(start), distance2d, targetZ, end, velocity});
        
        //Try to jump to landing target
        double timeToPassDistance = getTimeToPassDistance(distance2d, velocity) - 0.055;
        Double force = computeJump(targetZ, timeToPassDistance, jumpAngleCos);

        double originalTime = timeToPassDistance;
        int jumpKoef = originalTime > JUMP_PEEK_TIME ? 2 : 1;

        if (force.equals(Double.NaN) && timeToPassDistance < jumpKoef * JUMP_PEEK_TIME && targetZ > 0) {
            force = getPowerForJumpByZDiff(targetZ);
        }

        if (force.equals(Double.NaN) || force < 0) {
            return force;
        }

        if (log.isLoggable(Level.FINER)) {
            debug("Computed force before collision adjustment: {0}", force);
        }

        Location collisionLocation = getCollisionLocation(boundaries);
        if (collisionLocation == null) {
            return force;
        } else {
            double collisionDistance = getDistance2D(start, collisionLocation, jumpAngleCos);
            double timeToCollision = getTimeToPassDistance(collisionDistance, velocity);

            double collidingTime = JUMP_PEEK_TIME * (force <= MAX_SINGLE_JUMP_POWER ? 1 : 2) - timeToCollision;
            if (collidingTime > 0) {

                Location edgeDirection = boundaries.getTargetEdgeDirection().setZ(0).getNormalized();
                Location computedDirection = boundaries.getLandingTarget().sub(boundaries.getTakeOffMax()).setZ(0).getNormalized();
                Location verticalDirection = new Location(edgeDirection.y, -edgeDirection.x);

                double angleCos = verticalDirection.dot(computedDirection);

                //Collision will occur...
                double collisionCoef = 1 + (collidingTime / JUMP_PEEK_TIME) * (1 - angleCos);
                force = Math.min(MAX_DOUBLE_JUMP_POWER, force * collisionCoef);
                debug("Possible jump collision detected, adjusting power. NEW POWER: {0}, Angle cos: {1}, Colliding time: {2}", new Object[]{force, angleCos, collidingTime});
            }

            return force;
        }

    }

    /**
     * Compute jump power.
     *
     * @param targetZ Difference between start and end Z coordinate.
     * @param timeToPassDistance Time it will take to pass the distance from
     * start to end.
     * @param jumpAngleCos Cos of angle of direction of the movement to the
     * direction of the link.
     * @return
     */
    public Double computeJump(double targetZ, double timeToPassDistance, double jumpAngleCos) {

        if (!isJumpable(timeToPassDistance, targetZ)) {
            //We are not able to jump there
            debug("We are not able to jump there! Time: {0} Z: {1}", new Object[]{timeToPassDistance, targetZ});
            return Double.NaN;
        }

        //Jump delay time
        if (log.isLoggable(Level.FINER)) {
            debug("Computing jump. Time to pass the distance: {0}", timeToPassDistance);
        }

        Double power = Double.NaN;

        if (isSingleJumpable(timeToPassDistance, targetZ)) {
            debug("Computing jump. Single jump should suffice.");
            //TODO: Check - COrrection -> Jump Delay
            power = getSingleJumpPower(targetZ, timeToPassDistance);
        }
        //Not single jumpable, or single jump power computation failed.
        if (power.equals(Double.NaN)) {
            debug("Computing jump. Double jump will be needed.");
            power = getDoubleJumpPower(targetZ, timeToPassDistance, UnrealUtils.FULL_DOUBLEJUMP_DELAY);
        }

        return power;
    }
    
    // ========
    // FALLIING
    // ========
    
    /**
     * Compute power for given fall.
     *
     * @param start Start location of the jump.
     * @param boundaries Jump boundaries for given link.
     * @param velocity Current velocity of the bot.
     * @param jumpAngleCos Cos of angle of direction of the movement to the
     * direction of the link.
     * @return Power of the jump.
     */
    public Double computeFall(Location start, JumpBoundaries boundaries, double velocity, double jumpAngleCos) {
    	Location end = boundaries.getLandingTarget();
    	
    	double distance2D = getDistance2D(start, boundaries.getLandingTarget(), jumpAngleCos);
    	
    	double targetZ = end.z - start.z;
        
        debug("Fall {0} --(D3D:{1}|D2D:{2}|D2DC:{3}|DZ:{4})--> {5} [Velocity {6}]", new Object[]{start, end.getDistance(start), end.getDistance2D(start), distance2D, targetZ, end, velocity});
        
        double fallSpeed = 430;        
        double fallTime = Math.abs(targetZ) / fallSpeed;
        double fallDistance2D = velocity * fallTime;
        
        double remainingDistance2D = distance2D - fallDistance2D;
        
        if (remainingDistance2D < 0) {
        	debug("Remaining distance after fall " + ((int)remainingDistance2D) + " < 0 => perform only small jump");
        	return 110.0d;
        }
        
        //Try to jump to landing target
        double timeToPassDistance = getTimeToPassDistance(distance2D, velocity) - 0.055;
        Double force = computeFall(targetZ, timeToPassDistance, jumpAngleCos);

        double originalTime = timeToPassDistance;
        int jumpKoef = originalTime > JUMP_PEEK_TIME ? 2 : 1;

        if (force.equals(Double.NaN) && timeToPassDistance < jumpKoef * JUMP_PEEK_TIME && targetZ > 0) {
            force = getPowerForJumpByZDiff(targetZ);
        }

        if (force.equals(Double.NaN) || force < 0) {
            return force;
        }

        if (log.isLoggable(Level.FINER)) {
            debug("Computed force before collision adjustment: {0}", force);
        }

        Location collisionLocation = getCollisionLocation(boundaries);
        if (collisionLocation == null) {
            return force;
        } else {
            double collisionDistance = getDistance2D(start, collisionLocation, jumpAngleCos);
            double timeToCollision = getTimeToPassDistance(collisionDistance, velocity);

            double collidingTime = JUMP_PEEK_TIME * (force <= MAX_SINGLE_JUMP_POWER ? 1 : 2) - timeToCollision;
            if (collidingTime > 0) {

                Location edgeDirection = boundaries.getTargetEdgeDirection().setZ(0).getNormalized();
                Location computedDirection = boundaries.getLandingTarget().sub(boundaries.getTakeOffMax()).setZ(0).getNormalized();
                Location verticalDirection = new Location(edgeDirection.y, -edgeDirection.x);

                double angleCos = verticalDirection.dot(computedDirection);

                //Collision will occur...
                double collisionCoef = 1 + (collidingTime / JUMP_PEEK_TIME) * (1 - angleCos);
                force = Math.min(MAX_DOUBLE_JUMP_POWER, force * collisionCoef);
                debug("Possible jump collision detected, adjusting power. NEW POWER: {0}, Angle cos: {1}, Colliding time: {2}", new Object[]{force, angleCos, collidingTime});
            }

            return force;
        }

    }

    /**
     * Compute jump power.
     *
     * @param targetZ Difference between start and end Z coordinate.
     * @param timeToPassDistance Time it will take to pass the distance from
     * start to end.
     * @param jumpAngleCos Cos of angle of direction of the movement to the
     * direction of the link.
     * @return
     */
    public Double computeFall(double targetZ, double timeToPassDistance, double jumpAngleCos) {

        if (!isJumpable(timeToPassDistance, targetZ)) {
            //We are not able to jump there
            debug("We are not able to jump there! Time: {0} Z: {1}", new Object[]{timeToPassDistance, targetZ});
            return Double.NaN;
        }

        //Jump delay time
        if (log.isLoggable(Level.FINER)) {
            debug("Computing jump. Time to pass the distance: {0}", timeToPassDistance);
        }

        Double power = Double.NaN;

        if (isSingleJumpable(timeToPassDistance, targetZ)) {
            debug("Computing jump. Single jump should suffice.");
            //TODO: Check - COrrection -> Jump Delay
            power = getSingleJumpPower(targetZ, timeToPassDistance);
        }
        //Not single jumpable, or single jump power computation failed.
        if (power.equals(Double.NaN)) {
            debug("Computing jump. Double jump will be needed.");
            power = getDoubleJumpPower(targetZ, timeToPassDistance, UnrealUtils.FULL_DOUBLEJUMP_DELAY);
        }

        return power;
    }
    
    // =====
    // UTILS
    // =====

    private double getDistance2D(Location start, Location end, double jumpAngleCos) {

        double distance2d = start.getDistance2D(end);
       // debug("Computing jump. Distance2D: {0} AngleCos: {1}", new Object[]{distance2d, jumpAngleCos});
        if (jumpAngleCos > 0) {
            distance2d = start.getDistance2D(end) / jumpAngleCos;
            //debug("Computing jump. Distance2D after angle correction: {0}", distance2d);
        } else {
            //debug("Computing jump. Jump angle is > 90, no angle correction: {0}", distance2d);
        }
        return distance2d;
    }

    /**
     * Whether the given jump is doable.
     *
     * @param start Start of the jump.
     * @param end End of the jump.
     * @param velocity Current velocity.
     * @return Whether the given jump is doable.
     */
    public boolean isJumpable(Location start, Location end, double velocity) {
        if (start == null || end == null) {
            return false;
        }

        if (end.z - start.z > MAX_JUMP_HEIGHT) {
            //We cannot jump higher than MAX_JUMP_HEIGHT.
            return false;
        }

        double distance2d = start.getDistance2D(end);

        return isJumpable(distance2d, velocity, end.z - start.z);
    }

    private boolean isJumpable(double distance2d, double velocity, double zDiff) {
        double timeToPassDistance = getTimeToPassDistance(distance2d, velocity);
        return isJumpable(timeToPassDistance, zDiff);
    }

    private boolean isJumpable(double timeToPassDistance, double zDiff) {
        double computedZDiff;
        double maxTime = 2 * JUMP_PEEK_TIME;
        if (timeToPassDistance < maxTime) {
            computedZDiff = MAX_JUMP_HEIGHT;
        } else {
            computedZDiff = getZDiffForJump(MAX_DOUBLE_JUMP_POWER, timeToPassDistance, true, 0.39);
        }

        return computedZDiff >= zDiff;
    }

    private double getTimeToPassDistance(double distance2d, double velocity) {
        //return distance2d / velocity;
        //TODO: Inspect - strange behaviour
        if (distance2d < velocity * SPEED_BOOST_DELAY) {
            return distance2d / velocity;
        } else {
            return SPEED_BOOST_DELAY + (distance2d - velocity * SPEED_BOOST_DELAY) / (velocity * JUMP_SPEED_BOOST);
        }
    }

    private static final double JUMP_SPEED_BOOST = 1.08959;

    //
    // JUMP EQUATION - SINGLE JUMP
    // z = -475 * t^2 + power * t
    //
    // t = Unreal time
    //
    //
    // JUMP EQUATION - DOUBLE JUMP
    // z = -475 * t^2 + power * t + koef * (t - delay)
    //
    // koef = (power - 340) * 1.066 + 2 
    //
    // t = Unreal time
    //
    private double getZDiffForJump(double power, double deltaTime, boolean isDoubleJump, double delay) {
        double z = 0;

        //Equation for single jump
        z += -475 * (deltaTime * deltaTime) + Math.min(power, MAX_SINGLE_JUMP_POWER) * deltaTime;
        if (isDoubleJump) {
            //Double jump specific
            z += ((power - MAX_SINGLE_JUMP_POWER) * 1.066 + 2) * (deltaTime - delay);
        }
        return z;
    }

    private double getSingleJumpPower(double targetZ, double time) {

        double power = (targetZ + 475 * time * time) / time;

        if (power > MAX_SINGLE_JUMP_POWER) {
            return Double.NaN;
        }

        return power;
    }

    private double getDoubleJumpPower(double targetZ, double time, double delay) {
        double power = 0;

        if (time < delay) {
            debug("Computing double jump power. Time is lower than delay, postponing result.");
            power = getPowerForJumpByZDiff(targetZ);
            if (power < 0) {
                return power;
            }
            return Double.NaN;
        } else {

            //power = (targetZ + 475 * time * time + 360.5 * (time - delay)) / (2.066 * time - 1.066 * delay);
            power = (targetZ + 475 * time * time + 20 * time - 140) / (1.066 * (time - delay));
            debug("Computing double jump power. targetZ: {0}, Time: {1}, Delay: {2}, POWER: {3}", new Object[]{targetZ, time, delay, power});

        }

        if (power > MAX_DOUBLE_JUMP_POWER) {
            return Double.NaN;
        }

        return power;
    }

    private Location getNavMeshPoint(Location from, Location direction, double distance) {

        return from.interpolate(direction, distance / from.getDistance2D(direction));
    }

    private boolean isSingleJumpable(double timeToPassDistance, double zDiff) {
        if (zDiff > MAX_SINGLE_JUMP_HEIGHT) {
            //We cannot jump higher than MAX_JUMP_HEIGHT.
            return false;
        }

        if (timeToPassDistance < JUMP_PEEK_TIME) {
            return true;
        }

        double computedZDiff = getZDiffForJump(MAX_SINGLE_JUMP_POWER, timeToPassDistance, false, 0);

        return computedZDiff >= zDiff;
    }

    public Location getCollisionLocation(JumpBoundaries boundaries) {

        if (!boundaries.isJumpUp()) {
            return null;
        }
        if (boundaries.getTargetEdgeDirection() == null) {
            return null;
        }

        Location edgeDirection = boundaries.getTargetEdgeDirection().setZ(0).getNormalized();
        Location computedDirection = boundaries.getLandingTarget().sub(boundaries.getTakeOffMax()).setZ(0).getNormalized();
        Location verticalDirection = new Location(edgeDirection.y, -edgeDirection.x);

        double angleCos = verticalDirection.dot(computedDirection);

        double xAngle = edgeDirection.dot(computedDirection);
        if (Math.abs(xAngle) < Math.cos(Math.PI / 3)) {
            debug("Computing collision. Ignoring collision. Edge to mesh angle cos: {0}", xAngle);
            return null;
        }
        debug("Computing collision. Angle cos: {0}", angleCos);

        double correctionDistance = (2 * BOT_RADIUS) / angleCos;

        double koef = correctionDistance / boundaries.getLandingTarget().getDistance2D(boundaries.getTakeOffMax());

        Location collisionLocation;
        if (koef > 1.0) {
            collisionLocation = boundaries.getTakeOffMax();
        } else {
            collisionLocation = boundaries.getLandingTarget().interpolate(boundaries.getTakeOffMax(), koef);
        }

        return collisionLocation;
    }

    public Location getNearestMeshDirection(Location location, Location direction) {

        //TODO: Not good enough - centre of polygon not suitable for big polygons
        OldNavMeshPolygon poly = navMesh.getNearestPolygon(location);

        Line2D ray = new Line2D(location.x, location.y, location.x + direction.x * 10000, location.y + direction.y * 10000);

        int[] polygon = navMesh.getPolygon(poly.getPolygonId());
        for (int i = 0; i < polygon.length; i++) {
            int v1 = polygon[i];
            int v2 = polygon[((i == polygon.length - 1) ? 0 : i + 1)];
            double[] vertex1 = navMesh.getVertex(v1);
            double[] vertex2 = navMesh.getVertex(v2);
            Line2D edge = new Line2D(vertex1[0], vertex1[1], vertex2[0], vertex2[1]);
            Point2D cross = ray.getIntersection(edge);
            if (cross != null) {
                if (((cross.x <= Math.max(edge.p1.x, edge.p2.x) && cross.x >= Math.min(edge.p1.x, edge.p2.x)) || Math.abs(cross.x - edge.p1.x) < 0.0001)
                        && ((cross.x <= Math.max(ray.p1.x, ray.p2.x) && cross.x >= Math.min(ray.p1.x, ray.p2.x)) || Math.abs(cross.x - ray.p1.x) < 0.0001)) {
                    // is a cross!
                    Location vertex1Loc = new Location(vertex1);
                    Location vertex2Loc = new Location(vertex2);

                    return vertex2Loc.sub(vertex1Loc);
                }
            }
        }
        return null;
    }

    public double getCorrectedVelocity(double velocity, boolean isAccelerating) {
        if (!isAccelerating) {

            return velocity;
        } else {
            return Math.min(UnrealUtils.MAX_VELOCITY, 0.9788 * velocity + 111);
        }
    }

    public double getCorrectedAngle(double angleCos, boolean isFirstStep) {
        if (isFirstStep) {
            return angleCos;
        }
        return Math.cos(Math.acos(angleCos) / 2);
    }

    private double getPowerForJumpByZDiff(double zDiff) {
        //Reserve
        zDiff += 5;

        if (zDiff < MAX_SINGLE_JUMP_HEIGHT) {
            return 3.87 * zDiff + 111;
        } else {
            return 3.136 * zDiff + 287;
        }
    }
    
    private void debug(String msg) {
    	log.finer("      +-- " + msg);
    }
    
    private void debug(String msg, Object... objects) {
    	log.log(Level.FINER, "      +-- " + msg, objects);
    }

}
