/* File AbstractLine2D.java 
 *
 * Project : Java Geometry Library
 *
 * ===========================================
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or (at
 * your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY, without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. if not, write to :
 * The Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 */

// package

package math.geom2d.line;

//Imports
import java.util.ArrayList;
import java.util.Collection;

import math.JavaGeomMath;
import math.geom2d.AffineTransform2D;
import math.geom2d.Angle2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.Shape2D;
import math.geom2d.Vector2D;
import math.geom2d.circulinear.CirculinearCurve2DUtils;
import math.geom2d.circulinear.CirculinearDomain2D;
import math.geom2d.circulinear.CirculinearElement2D;
import math.geom2d.conic.Circle2D;
import math.geom2d.conic.CircleArc2D;
import math.geom2d.curve.AbstractSmoothCurve2D;

import math.geom2d.curve.ContinuousCurve2D;
import math.geom2d.curve.Curve2D;
import math.geom2d.curve.Curve2DUtils;
import math.geom2d.curve.CurveArray2D;
import math.geom2d.curve.CurveSet2D;
import math.geom2d.domain.SmoothOrientedCurve2D;
import math.geom2d.transform.CircleInversion2D;

/**
 * <p>
 * Base class for straight curves, such as straight lines, rays, or edges.
 * </p>
 * <p>
 * Internal representation of straight objects is parametric: (x0, y0) is a
 * point in the object, and (dx, dy) is a direction vector of the line.
 * </p>
 * <p>
 * If the line is defined by two point, we can set (x0,y0) to the first point,
 * and (dx,dy) to the vector (p1, p2).
 * </p>
 * <p>
 * Then, coordinates for a point (x,y) such as x=x0+t*dx and y=y0+t=dy, t
 * between 0 and 1 give a point inside p1 and p2, t<0 give a point 'before' p1,
 * and t>1 give a point 'after' p2, so it is convenient to easily manage edges,
 * rays and straight lines.
 * <p>
 */
public abstract class AbstractLine2D extends AbstractSmoothCurve2D
implements SmoothOrientedCurve2D, LinearShape2D, CirculinearElement2D {

    // ===================================================================
    // constants

    // ===================================================================
    // class variables

    /**
     * Coordinates of starting point of the line
     */
    protected double x0, y0;

    /**
     * Direction vector of the line. dx and dy should not be both zero.
     */
    protected double dx, dy;

    // ===================================================================
    // static methods

    /**
     * Returns the unique intersection of two straight objects. If intersection
     * doesn't exist (parallel lines), return null.
     * 
     * @deprecated This method act as if the line was {@link StraightLine2D} and may return invalid intersection.
     */
    @Deprecated
    public final static Point2D getIntersection(AbstractLine2D l1,
            AbstractLine2D l2) {
        double t = ((l1.y0-l2.y0)*l2.dx-(l1.x0-l2.x0)*l2.dy)
                /(l1.dx*l2.dy-l1.dy*l2.dx);
        return new Point2D(l1.x0+t*l1.dx, l1.y0+t*l1.dy);
    }

    /**
     * Test if the two linear objects are located on the same straight line.
     */
    public final static boolean isColinear(AbstractLine2D line1,
            AbstractLine2D line2) {
        // test if the two lines are parallel
        if (Math.abs(line1.dx*line2.dy-line1.dy*line2.dx)>ACCURACY)
            return false;

        // test if the two lines share at least one point (see the contains()
        // method for details on tests)
        return (Math.abs((line2.y0-line1.y0)*line2.dx-(line2.x0-line1.x0)
                *line2.dy)
                /JavaGeomMath.hypot(line2.dx, line2.dy)<Shape2D.ACCURACY);
    }

    /**
     * Test if the two linear objects are parallel.
     */
    public final static boolean isParallel(AbstractLine2D line1,
            AbstractLine2D line2) {
        return (Math.abs(line1.dx*line2.dy-line1.dy*line2.dx)<ACCURACY);
    }

    // ===================================================================
    // Protected constructors

    protected AbstractLine2D(double x0, double y0, double dx, double dy) {
        this.x0 = x0;
        this.y0 = y0;
        this.dx = dx;
        this.dy = dy;
    }

    protected AbstractLine2D(Point2D point, Vector2D vector) {
        this.x0 = point.getX();
        this.y0 = point.getY();
        this.dx = vector.getX();
        this.dy = vector.getY();
    }

    protected AbstractLine2D(LinearShape2D line) {
        this(line.getOrigin(), line.getVector());
    }

    // ===================================================================
    // Methods specific to Line shapes

    public boolean isColinear(LinearShape2D linear) {
        // test if the two lines are parallel
        if (!isParallel(linear))
            return false;

        // test if the two lines share at least one point (see the contains()
        // method for details on tests)
        StraightLine2D line = linear.getSupportingLine();
        if (Math.abs(dx)>Math.abs(dy)) {
            if (Math.abs((line.x0-x0)*dy/dx+y0-line.y0)>Shape2D.ACCURACY)
                return false;
            else
                return true;
        } else {
            if (Math.abs((line.y0-y0)*dx/dy+x0-line.x0)>Shape2D.ACCURACY)
                return false;
            else
                return true;
        }
    }

    /**
     * Test if the this object is parallel to the given one.
     */
    public boolean isParallel(LinearShape2D line) {
        return Vector2D.isColinear(this.getVector(), line.getVector());
    }

    /**
     * Returns true if the point (x, y) lies on the line covering the object,
     * with precision given by Shape2D.ACCURACY.
     */
    protected boolean supportContains(double x, double y) {
        return (Math.abs((x-x0)*dy-(y-y0)*dx)/JavaGeomMath.hypot(dx, dy)<Shape2D.ACCURACY);
    }

    /**
     * Returns the matrix of parametric representation of the line. Result has
     * the form:
     * <p> [ x0 dx ]
     * <p> [ y0 dy ]
     * <p>
     * It can be easily extended to higher dimensions and/or higher polynomial
     * forms.
     */
    public double[][] getParametric() {
        double tab[][] = new double[2][2];
        tab[0][0] = x0;
        tab[0][1] = dx;
        tab[1][0] = y0;
        tab[1][1] = dy;
        return tab;
    }

    /**
     * Returns the coefficient of the Cartesian representation of the line.
     * Cartesian equation has the form: <code>ax+by+c=0</code>
     * 
     * @return the array {a, b, c}.
     */
    public double[] getCartesianEquation() {
        double tab[] = new double[3];
        tab[0] = dy;
        tab[1] = -dx;
        tab[2] = dx*y0-dy*x0;
        return tab;
    }

    /**
     * Returns polar coefficients.
     * 
     * @return an array of 2 elements, the first one is the distance to the
     *         origin, the second one is the angle with horizontal, between 0
     *         and 2*PI.
     */
    public double[] getPolarCoefficients() {
        double tab[] = new double[2];
        double d = getSignedDistance(0, 0);
        tab[0] = Math.abs(d);
        if (d>0)
            tab[1] = (getHorizontalAngle()+Math.PI)%(2*Math.PI);
        else
            tab[1] = getHorizontalAngle();
        return tab;
    }

    /**
     * Returns the signed polar coefficients. Distance to origin can be
     * negative: this allows representation of directed lines.
     * 
     * @return an array of 2 elements, the first one is the signed distance to
     *         the origin, the second one is the angle with horizontal, between
     *         0 and 2*PI.
     */
    public double[] getSignedPolarCoefficients() {
        double tab[] = new double[2];
        tab[0] = getSignedDistance(0, 0);
        tab[1] = getHorizontalAngle();
        return tab;
    }

    public double getPositionOnLine(java.awt.geom.Point2D point) {
        return getPositionOnLine(point.getX(), point.getY());
    }

    /**
     * Compute position on the line, that is the number t such that if the point
     * belong to the line, it location is given by x=x0+t*dx and y=y0+t*dy.
     * <p>
     * If the point does not belong to the line, the method returns the position
     * of its projection on the line.
     */
    public double getPositionOnLine(double x, double y) {
        return ((y-y0)*dy+(x-x0)*dx)/(dx*dx+dy*dy);
    }

    /**
     * Return the projection of point p on the line. The returned point can be
     * used to compute distance from point to line.
     * 
     * @param p a point outside the line (if point p lies on the line, it is
     *            returned)
     * @return the projection of the point p on the line
     */
    public Point2D getProjectedPoint(Point2D p) {
        return getProjectedPoint(p.getX(), p.getY());
    }

    /**
     * Return the projection of point p on the line. The returned point can be
     * used to compute distance from point to line.
     * 
     * @param x : coordinate x of point to be projected
     * @param y : coordinate y of point to be projected
     * @return the projection of the point p on the line
     */
    public Point2D getProjectedPoint(double x, double y) {
        if (contains(x, y))
            return new Point2D(x, y);

        // compute position on the line
        double t = getPositionOnLine(x, y);

        // compute position of intersection point
        return new Point2D(x0+t*dx, y0+t*dy);
    }

    /**
     * Return the symmetric of point p relative to this straight line.
     * 
     * @param p a point outside the line (if point p lies on the line, it is
     *            returned)
     * @return the projection of the point p on the line
     */
    public Point2D getSymmetric(Point2D p) {
        return getSymmetric(p.getX(), p.getY());
    }

    /**
     * Return the symmetric of point with coordinate (x, y) relative to this
     * straight line.
     * 
     * @param x : coordinate x of point to be projected
     * @param y : coordinate y of point to be projected
     * @return the projection of the point (x,y) on the line
     */
    public Point2D getSymmetric(double x, double y) {
        // compute position on the line
        double t = 2*getPositionOnLine(x, y);

        // compute position of intersection point
        return new Point2D(2*x0+t*dx-x, 2*y0+t*dy-y);
    }

    /**
     * Create a straight line parallel to this object, and going through the
     * given point.
     * 
     * @param point the point to go through
     * @return the parallel through the point
     */
    public StraightLine2D getParallel(Point2D point) {
        return new StraightLine2D(point, this.dx, this.dy);
    }

    /**
     * Create a straight line perpendicular to this object, and going through
     * the given point.
     * 
     * @param point the point to go through
     * @return the perpendicular through the point
     */
    public StraightLine2D getPerpendicular(Point2D point) {
        return new StraightLine2D(point, -this.dy, this.dx);
    }

   // ===================================================================

    // Methods implementing the LinearShape2D interface

    public Point2D getOrigin() {
        return new Point2D(x0, y0);
    }

    public Vector2D getVector() {
        return new Vector2D(dx, dy);
    }

    /**
     * Gets Angle with axis (O,i), counted counter-clockwise. Result is given
     * between 0 and 2*pi.
     */
    public double getHorizontalAngle() {
        return (Math.atan2(dy, dx)+2*Math.PI)%(2*Math.PI);
    }

    /** Compute intersection with another linear shape
     * 
     * @param line linear shape
     * @return the intersection or null it doesn't exist (parallel lines, etc).
     */
    public Point2D getIntersection(LinearShape2D line) {
    	double t = getIntersectionParametric(line);
    	
    	if ( Double.isInfinite(t) || Double.isNaN(t) ) {
    		return null;
    	}
    	
    	return getPoint(t);
    }
    
    /** Get parametric representation of intersection with another linear shape
     * 
     * @param other the other linear shape
     * @return parametric representation of the intersection or NaN if doesn't exist (parallel lines, etc)
     */
    public double getIntersectionParametric(LinearShape2D line) {
        Vector2D vect = line.getVector();
        double dx2 = vect.getX();
        double dy2 = vect.getY();

        // test if two lines are parallel
        if (Math.abs(dx*dy2-dy*dx2)<Shape2D.ACCURACY)
            return Double.NaN;

        // compute position on the line
        Point2D origin = line.getOrigin();
        double x2 = origin.getX();
        double y2 = origin.getY();
        double t = ((y0-y2)*dx2-(x0-x2)*dy2)/(dx*dy2-dy*dx2);
        
    	if ( !containsParametric( t ) ) {
    		return Double.NaN;
    	}
    	
    	if ( !line.contains( getPoint(t) ) ) {
    		return Double.NaN;
    	}
    	
    	return t;
    }

    public StraightLine2D getSupportingLine() {
        return new StraightLine2D(this);
    }


    // ===================================================================
    // methods implementing the CirculinearCurve2D interface
  
	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearShape2D#getBuffer(double)
	 */
	public CirculinearDomain2D getBuffer(double dist) {
		return CirculinearCurve2DUtils.computeBuffer(this, dist);
	}

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#getLength()
	 */
	public double getLength() {
		if(!this.isBounded()) return Double.POSITIVE_INFINITY;
		return (getT1()-getT0())*JavaGeomMath.hypot(dx, dy);
	}

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#getLength(double)
	 */
	public double getLength(double pos) {
		return pos*JavaGeomMath.hypot(dx, dy);
	}

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#getPosition(double)
	 */
	public double getPosition(double length) {
		return length/JavaGeomMath.hypot(dx, dy);
	}

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#transform(math.geom2d.transform.CircleInversion2D)
	 */
	public CirculinearElement2D transform(CircleInversion2D inv) {
		// Extract inversion parameters
        Point2D center 	= inv.getCenter();
        double r 		= inv.getRadius();
        
        // compute distance of line to inversion center
        Point2D po 	= this.getProjectedPoint(center);
        double d 	= this.getDistance(po);

        // flag for indicating if line extremities are finite
        boolean inf0 = Double.isInfinite(this.getT0());
        boolean inf1 = Double.isInfinite(this.getT1());

        // Degenerate case of a line passing through the center.
        // returns the line itself.
        if (Math.abs(d)<Shape2D.ACCURACY){
        	if (inf0){
        		if (inf1){
        			// case of a straight line, which transform into itself
        			return new StraightLine2D(this);
        		} else {
        			// case of an inverted ray, which transform into another
        			// inverted ray
        			Point2D p2 = this.getLastPoint().transform(inv);
        			return new InvertedRay2D(p2, this.getVector());
        		}
        	} else {
        		Point2D p1 = this.getFirstPoint().transform(inv);
                if (inf1){
        			// case of a ray, which transform into another ray
        			return new Ray2D(p1, this.getVector());
        		} else {
        			// case of an line segment
        			Point2D p2 = this.getLastPoint().transform(inv);
        			return new LineSegment2D(p1, p2);
        		}
        	}
        }
        
        // angle from center to line
        double angle = Angle2D.getHorizontalAngle(center, po);

        // center of transformed circle
        double r2 	= r*r/d/2;
        Point2D c2 	= Point2D.createPolar(center, r2, angle);

        // choose direction of circle arc
        boolean direct = !this.isInside(center);
        
        // case of a straight line
        if (inf0 && inf1) {
        	return new Circle2D(c2, r2, direct);
        }
        
        // Compute the transform of the end points, which can be the center of
        // the inversion circle in the case of an infinite line.
        Point2D p1 = inf0 ? center : this.getFirstPoint();
        Point2D p2 = inf1 ? center : this.getLastPoint();
        
        // compute angle between center of transformed circle and end points
        double theta1 = Angle2D.getHorizontalAngle(c2, p1);
        double theta2 = Angle2D.getHorizontalAngle(c2, p2);
        
        // create the new circle arc
        return new CircleArc2D(c2, r2, theta1, theta2, direct);
	}


    // ===================================================================
    // methods of OrientedCurve2D interface

    public double getWindingAngle(java.awt.geom.Point2D point) {

        double t0 = this.getT0();
        double t1 = this.getT1();

        double angle1, angle2;
        if (t0==Double.NEGATIVE_INFINITY)
            angle1 = Angle2D.getHorizontalAngle(-dx, -dy);
        else
            angle1 = Angle2D.getHorizontalAngle(point.getX(), point.getY(), x0
                    +t0*dx, y0+t0*dy);

        if (t1==Double.POSITIVE_INFINITY)
            angle2 = Angle2D.getHorizontalAngle(dx, dy);
        else
            angle2 = Angle2D.getHorizontalAngle(point.getX(), point.getY(), x0
                    +t1*dx, y0+t1*dy);

        if (this.isInside(point)) {
            if (angle2>angle1)
                return angle2-angle1;
            else
                return 2*Math.PI-angle1+angle2;
        } else {
            if (angle2>angle1)
                return angle2-angle1-2*Math.PI;
            else
                return angle2-angle1;
        }
    }

    /**
     * Get the signed distance of the StraightObject2d to the given point. The
     * signed distance is positive if point lies 'to the right' of the line,
     * when moving in the direction given by direction vector. This method is
     * not designed to be used directly, because AbstractLine2D is an abstract
     * class, but it can be used by subclasses to help computations.
     */
    public double getSignedDistance(java.awt.geom.Point2D p) {
        return getSignedDistance(p.getX(), p.getY());
    }

    /**
     * Get the signed distance of the StraightObject2d to the given point. The
     * signed distance is positive if point lies 'to the right' of the line,
     * when moving in the direction given by direction vector. This method is
     * not designed to be used directly, because AbstractLine2D is an abstract
     * class, but it can be used by subclasses to help computations.
     */
    public double getSignedDistance(double x, double y) {
        return ((x-x0)*dy-(y-y0)*dx)/JavaGeomMath.hypot(dx, dy);
    }

    /**
     * Returns true if the given point lies to the left of the line when
     * traveling along the line in the direction given by its direction vector.
     * 
     * @param p the point to test
     * @return true if point p lies on the 'left' of the line.
     */
    public boolean isInside(java.awt.geom.Point2D p) {
        return ((p.getX()-x0)*dy-(p.getY()-y0)*dx<0);
    }

    // ===================================================================
    // methods of SmoothCurve2D interface

    public Vector2D getTangent(double t) {
        return new Vector2D(dx, dy);
    }

    /**
     * returns 0 as every straight object.
     */
    public double getCurvature(double t) {
        return 0.0;
    }

    // ===================================================================
    // methods implementing the ContinuousCurve2D interface

    /**
     * Always returns false, because we can not come back to starting point if
     * we always go straight...
     */
    public boolean isClosed() {
        return false;
    }

    // ===================================================================
    // methods implementing the Curve2D interface

    /**
     * Return the intersection points of the curve with the specified line. The
     * length of the result array is the number of intersection points.
     */
	@Override
    public Collection<? extends AbstractLine2D> getSmoothPieces() {
        return wrapCurve(this);
    }


    public Collection<Point2D> getIntersections(LinearShape2D line) {
        ArrayList<Point2D> points = new ArrayList<Point2D>();

        Point2D point = getIntersection(line);
        if (point==null)
            return points;

        // return array with the intersection point.
        points.add(point);
        return points;
    }

    /**
     * Gets the position of the point on the line arc. If point belongs to the
     * line, this position is defined by the ratio:
     * <p>
     * <code> t = (xp - x0)/dx <\code>, or equivalently:<p>
     * <code> t = (yp - y0)/dy <\code>.<p>
     * If point does not belong to edge, returns Double.NaN.
     */
    public double getPosition(java.awt.geom.Point2D point) {
        double pos = this.getPositionOnLine(point);

        // return either pos or NaN
        if (pos<this.getT0())
            return Double.NaN;
        if (pos>this.getT1())
            return Double.NaN;
        return pos;
    }

    /**
     * Gets the position of the closest point on the line arc. If point belongs
     * to the line, this position is defined by the ratio:
     * <p>
     * <code> t = (xp - x0)/dx <\code>, or equivalently:<p>
     * <code> t = (yp - y0)/dy <\code>.<p>
     * If point does not belong to edge, returns t0, or t1, depending on which
     * one is the closest.
     */
    public double project(java.awt.geom.Point2D point) {
        double pos = this.getPositionOnLine(point);

        // Bounds between t0 and t1
        return Math.min(Math.max(pos, this.getT0()), this.getT1());
    }

    /**
     * Returns a new AbstractLine2D, which is the portion of this AbstractLine2D
     * delimited by parameters t0 and t1. Casts the result to StraightLine2D,
     * Ray2D or LineSegment2D when appropriate.
     */
    public AbstractLine2D getSubCurve(double t0, double t1) {
        t0 = Math.max(t0, this.getT0());
        t1 = Math.min(t1, this.getT1());
        if (Double.isInfinite(t1)) {
            if (Double.isInfinite(t0))
                return new StraightLine2D(this);
            else
                return new Ray2D(this.getPoint(t0), this.getVector());
        }

        if (Double.isInfinite(t0))
            return new InvertedRay2D(this.getPoint(t1), this.getVector());
        else
            return new LineSegment2D(this.getPoint(t0), this.getPoint(t1));

    }

	@Override
	public Collection<? extends AbstractLine2D> getContinuousCurves() {
    	return wrapCurve(this);
    }

    // ===================================================================
    // methods implementing the Shape2D

    /**
     * Gets the distance of the StraightObject2d to the given point. This method
     * is not designed to be used directly, because AbstractLine2D is an
     * abstract class, but it can be called by subclasses to help computations.
     */
    public double getDistance(java.awt.geom.Point2D p) {
        return getDistance(p.getX(), p.getY());
    }

    /**
     * Gets the distance of the StraightObject2d to the given point. This method
     * is not designed to be used directly, because AbstractLine2D is an
     * abstract class, but it can be used by subclasses to help computations.
     * 
     * @param x x-coordinate of the point
     * @param y y-coordinate of the point
     * @return distance between this object and the point (x,y)
     */
    public double getDistance(double x, double y) {
    	// first project on the line
        Point2D proj = getProjectedPoint(x, y);
        
        // if the line contains the projection, returns the distance
        if (contains(proj))
            return proj.distance(x, y);
        
        // otherwise, returns the distance to the closest singular point
        double dist = Double.POSITIVE_INFINITY;
        if(!Double.isInfinite(getT0()))
        	dist = getFirstPoint().getDistance(x, y);
        if(!Double.isInfinite(getT1()))
        	dist = Math.min(dist, getLastPoint().getDistance(x, y));
       	return dist;
    }

    public boolean contains(java.awt.geom.Point2D p) {
        return this.contains(p.getX(), p.getY());
    }
    
    /** Tell whether the line contains parametric representation of a point
     */
    public abstract boolean containsParametric( double t );

    /**
     * Returns false, unless both dx and dy equal 0.
     */
    public boolean isEmpty() {
        return JavaGeomMath.hypot(dx, dy)<Shape2D.ACCURACY;
    }

    public abstract AbstractLine2D transform(AffineTransform2D transform);

    public CurveSet2D<? extends AbstractLine2D> clip(Box2D box) {
        // Clip the curve
        CurveSet2D<ContinuousCurve2D> set = Curve2DUtils.clipContinuousCurve(
                this, box);

        // Stores the result in appropriate structure
        CurveArray2D<AbstractLine2D> result = 
        	new CurveArray2D<AbstractLine2D>(set.getCurveNumber());

        // convert the result
        for (Curve2D curve : set.getCurves()) {
            if (curve instanceof AbstractLine2D)
                result.addCurve((AbstractLine2D) curve);
        }
        return result;
    }
    
    /**
     * Ensures public declaration of clone(), and ensures valid return type.
     */
	@Override
    public abstract AbstractLine2D clone();
}
