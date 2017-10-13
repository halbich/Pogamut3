/* File LineSegment2D.java 
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

import math.JavaGeomMath;
import math.geom2d.AffineTransform2D;
import math.geom2d.Angle2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.Shape2D;
import math.geom2d.Vector2D;
import math.geom2d.circulinear.CirculinearElement2D;


/**
 * Line segment, defined as the set of points located between the two end
 * points.
 * {@See Line2D}
 */
public class LineSegment2D extends AbstractLine2D
implements Cloneable, CirculinearElement2D {

	private static final long serialVersionUID = 1L;
	
	/** Define a new Edge with two extremities. */
    public LineSegment2D(java.awt.geom.Point2D point1,
            java.awt.geom.Point2D point2) {
        this(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }

    /** Define a new Edge with two extremities. */
    public LineSegment2D(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2-x1, y2-y1);
    }

    // ===================================================================
    // static methods

    /**
     * Static factory for creating a new line segment between two points.
     * @since 0.8.1
     */
    public final static LineSegment2D create(Point2D p1, Point2D p2) {
    	return new LineSegment2D(p1, p2);
    }

    public final static StraightLine2D getMedian(LineSegment2D edge) {
        return new StraightLine2D(
        		edge.x0+edge.dx*.5, edge.y0+edge.dy*.5,
                -edge.dy, edge.dx);
    }

    /**
     * Returns angle between two edges sharing one vertex.
     */
    public final static double getEdgeAngle(LineSegment2D edge1,
            LineSegment2D edge2) {
        double x0, y0, x1, y1, x2, y2;

        if (Math.abs(edge1.x0-edge2.x0)<Shape2D.ACCURACY
                &&Math.abs(edge1.y0-edge2.y0)<Shape2D.ACCURACY) {
            x0 = edge1.x0;
            y0 = edge1.y0;
            x1 = edge1.x0+edge1.dx;
            y1 = edge1.y0+edge1.dy;
            x2 = edge2.x0+edge2.dx;
            y2 = edge2.y0+edge2.dy;
        } else if (Math.abs(edge1.x0+edge1.dx-edge2.x0)<Shape2D.ACCURACY
                &&Math.abs(edge1.y0+edge1.dy-edge2.y0)<Shape2D.ACCURACY) {
            x0 = edge1.x0+edge1.dx;
            y0 = edge1.y0+edge1.dy;
            x1 = edge1.x0;
            y1 = edge1.y0;
            x2 = edge2.x0+edge2.dx;
            y2 = edge2.y0+edge2.dy;
        } else if (Math.abs(edge1.x0+edge1.dx-edge2.x0-edge2.dx)<Shape2D.ACCURACY
                &&Math.abs(edge1.y0+edge1.dy-edge2.y0-edge2.dy)<Shape2D.ACCURACY) {
            x0 = edge1.x0+edge1.dx;
            y0 = edge1.y0+edge1.dy;
            x1 = edge1.x0;
            y1 = edge1.y0;
            x2 = edge2.x0;
            y2 = edge2.y0;
        } else if (Math.abs(edge1.x0-edge2.x0-edge2.dx)<Shape2D.ACCURACY
                &&Math.abs(edge1.y0-edge2.y0-edge2.dy)<Shape2D.ACCURACY) {
            x0 = edge1.x0;
            y0 = edge1.y0;
            x1 = edge1.x0+edge1.dx;
            y1 = edge1.y0+edge1.dy;
            x2 = edge2.x0;
            y2 = edge2.y0;
        } else {// no common vertex -> return NaN
            return Double.NaN;
        }

        return Angle2D.getAngle(new Vector2D(x1-x0, y1-y0), new Vector2D(x2-x0,
                y2-y0));
    }

    /**
     * Checks if two line segment intersect. Uses the Point2D.ccw() method,
     * which is based on Sedgewick algorithm.
     * 
     * @param edge1 a line segment
     * @param edge2 a line segment
     * @return true if the 2 line segments intersect
     */
    public final static boolean intersects(LineSegment2D edge1,
            LineSegment2D edge2) {
        Point2D e1p1 = edge1.getFirstPoint();
        Point2D e1p2 = edge1.getLastPoint();
        Point2D e2p1 = edge2.getFirstPoint();
        Point2D e2p2 = edge2.getLastPoint();

        boolean b1 = Point2D.ccw(e1p1, e1p2, e2p1)
                *Point2D.ccw(e1p1, e1p2, e2p2)<=0;
        boolean b2 = Point2D.ccw(e2p1, e2p2, e1p1)
                *Point2D.ccw(e2p1, e2p2, e1p2)<=0;
        return b1&&b2;
    }

    
    // ===================================================================
    // Methods specific to LineSegment2D

    /**
     * Return the opposite vertex of the edge.
     * 
     * @param point one of the vertices of the edge
     * @return the other vertex, or null if point is nor a vertex of the edge
     */
    public Point2D getOtherPoint(Point2D point) {
        if (point.equals(new Point2D(x0, y0)))
            return new Point2D(x0+dx, y0+dy);
        if (point.equals(new Point2D(x0+dx, y0+dy)))
            return new Point2D(x0, y0);
        return null;
    }

    /**
     * Return the median of the edge, that is the locus of points located at
     * equal distance of each vertex.
     */
    public StraightLine2D getMedian() {
        // initial point is the middle of the edge -> x = x0+.5*dx
        // direction vector is the initial direction vector rotated by pi/2.
        return new StraightLine2D(x0+dx*.5, y0+dy*.5, -dy, dx);
    }

    /**
     * @deprecated lines will become immutable in a future release
     */
    @Deprecated
    public void setLineSegment(Point2D p1, Point2D p2) {
        this.x0 = p1.getX();
        this.y0 = p1.getY();
        this.dx = p2.getX()-this.x0;
        this.dy = p2.getY()-this.y0;
    }

    /**
     * @deprecated lines will become immutable in a future release
     */
    @Deprecated
    public void setLineSegment(double x1, double y1, double x2, double y2) {
        this.x0 = x1;
        this.y0 = y1;
        this.dx = x2-x1;
        this.dy = y2-y1;
    }
    
    @Override
    public boolean containsParametric(double t) {
    	return 0 <= t && t <= 1.0;
    }
    
    // ===================================================================
    // methods implementing the CirculinearCurve2D interface

    /**
     * Returns the length of the line segment.
     */
	@Override
    public double getLength() {
        return JavaGeomMath.hypot(dx, dy);
    }

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#getParallel(double)
	 */
	public LineSegment2D getParallel(double d) {
        double dd = Math.sqrt(dx*dx+dy*dy);
        return new LineSegment2D(
        		x0+dy*d/dd, y0-dx*d/dd, 
        		x0+dx+dy*d/dd, y0+dy-dx*d/dd);
	}

    // ===================================================================
    // Methods implementing the OrientedCurve2D interface

    @Override
    public double getSignedDistance(double x, double y) {
        Point2D proj = super.getProjectedPoint(x, y);
        if (contains(proj))
            return super.getSignedDistance(x, y);

        double d = this.getDistance(x, y);
        return super.getSignedDistance(x, y)>0 ? d : -d;
    }
    
//    // ===================================================================
//    // Methods implementing the CirculinearCurve2D interface
//
//	/* (non-Javadoc)
//	 * @see math.geom2d.circulinear.CirculinearCurve2D#transform(math.geom2d.transform.CircleInversion2D)
//	 */
//	public CirculinearCurve2D transform(CircleInversion2D inv) {
//		// Extract inversion parameters
//        Point2D center = inv.getCenter();
//        double r = inv.getRadius();
//        
//        StraightLine2D line = this.getSupportingLine();
//        
//        Point2D po = line.getProjectedPoint(center);
//        double d = line.getDistance(po);
//
//        // transform limits of edge, to obtain limits of arc
//        Point2D p1 = this.getFirstPoint().transform(inv);
//        Point2D p2 = this.getLastPoint().transform(inv);
//
//        // Degenerate case of a point belonging to the line:
//        // the transform is the line itself.
//        if (Math.abs(d)<Shape2D.ACCURACY){
//        	return new LineSegment2D(p1, p2);
//        }
//        
//        // angle from center to line
//        double angle = Angle2D.getHorizontalAngle(center, po);
//
//        // center of transformed circle
//        double r2 = r*r/d/2;
//        Point2D c2 = Point2D.createPolar(center, r2, angle);
//
//        // compute start and end angles of arc
//        double theta1 = Angle2D.getHorizontalAngle(c2, p1);
//        double theta2 = Angle2D.getHorizontalAngle(c2, p2);
//
//        boolean direct = line.isInside(center);
//
//        return new CircleArc2D(c2, r2, theta1, theta2, direct);
//	}


    // ===================================================================
    // Methods implementing the Curve2D interface

    /**
     * Return the first point of the edge.
     * 
     * @return the first point of the edge
     */
	@Override
    public Point2D getFirstPoint() {
        return new Point2D(x0, y0);
    }

    /**
     * Return the last point of the edge.
     * 
     * @return the last point of the edge
     */
	@Override
    public Point2D getLastPoint() {
        return new Point2D(x0+dx, y0+dy);
    }

    /**
     * Returns the parameter of the first point of the edge, equals to 0.
     */
    public double getT0() {
        return 0.0;
    }

    /**
     * Returns the parameter of the last point of the edge, equals to 1.
     */
    public double getT1() {
        return 1.0;
    }

    public Point2D getPoint(double t) {
        t = Math.min(Math.max(t, 0), 1);
        return new Point2D(x0+dx*t, y0+dy*t);
    }

    /**
     * Returns the LineSegment which start from last point of this line segment,
     * and which ends at the fist point of this last segment.
     */
    public LineSegment2D getReverseCurve() {
        return new LineSegment2D(x0+dx, y0+dy, x0, y0);
    }

    // ===================================================================
    // Methods implementing the Shape2D interface

    /**
     * Returns true
     */
    public boolean isBounded() {
        return true;
    }

    public boolean contains(double xp, double yp) {
        if (!super.supportContains(xp, yp))
            return false;

        // compute position on the line
        double t = getPositionOnLine(xp, yp);

        if (t<-ACCURACY)
            return false;
        if (t-1>ACCURACY)
            return false;

        return true;
    }

    /**
     * Get the distance of the point (x, y) to this edge.
     */
    @Override
    public double getDistance(double x, double y) {
        Point2D proj = super.getProjectedPoint(x, y);
        if (contains(proj))
            return proj.distance(x, y);
        double d1 = JavaGeomMath.hypot(x0-x, y0-y);
        double d2 = JavaGeomMath.hypot(x0+dx-x, y0+dy-y);
        return Math.min(d1, d2);
    }

    @Override
    public LineSegment2D transform(AffineTransform2D trans) {
        double[] tab = trans.getCoefficients();
        double x1 = x0*tab[0]+y0*tab[1]+tab[2];
        double y1 = x0*tab[3]+y0*tab[4]+tab[5];
        double x2 = (x0+dx)*tab[0]+(y0+dy)*tab[1]+tab[2];
        double y2 = (x0+dx)*tab[3]+(y0+dy)*tab[4]+tab[5];
        return new LineSegment2D(x1, y1, x2, y2);
    }

    public Box2D getBoundingBox() {
        return new Box2D(x0, x0+dx, y0, y0+dy);
    }

    // =================================
    // Methods implementing the Shape interface

    /**
     * Appends a line to the current path.
     * 
     * @param path the path to modify
     * @return the modified path
     */
    public java.awt.geom.GeneralPath appendPath(java.awt.geom.GeneralPath path) {
        path.lineTo((float) x0+dx, (float) y0+dy);
        return path;
    }

    /**
     * deprecated
     */
    public java.awt.geom.GeneralPath getGeneralPath() {
        java.awt.geom.GeneralPath path = new java.awt.geom.GeneralPath();
        path.moveTo((float) x0, (float) y0);
        path.lineTo((float) (x0+dx), (float) (y0+dy));
        return path;
    }

    // ===================================================================
    // Methods implementing the Object interface

    @Override
    public String toString() {
        return new String("LineSegment2D[(" + x0 + "," + y0 + ")-(" 
                + (x0+dx) + "," + (y0+dy) + ")]");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LineSegment2D))
            return false;
        LineSegment2D edge = (LineSegment2D) obj;

        if (Math.abs(x0-edge.x0)>Shape2D.ACCURACY)
            return false;
        if (Math.abs(y0-edge.y0)>Shape2D.ACCURACY)
            return false;
        if (Math.abs(dx-edge.dx)>Shape2D.ACCURACY)
            return false;
        if (Math.abs(dy-edge.dy)>Shape2D.ACCURACY)
            return false;
        return true;
    }
    
    @Override
    public LineSegment2D clone() {
        return new LineSegment2D(x0, y0, x0+dx, y0+dy);
    }
}
