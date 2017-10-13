/* File Ray2D.java 
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

import java.awt.geom.GeneralPath;

import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.Shape2D;
import math.geom2d.UnboundedShapeException;
import math.geom2d.Vector2D;

// Imports

/**
 * Ray, or half-line, defined from an origin and a direction vector. It is
 * composed of all points satisfying the parametric equation:
 * <p>
 * <code>x(t) = x0+t*dx<code><br>
 * <code>y(t) = y0+t*dy<code></p> 
 * With <code>t<code> comprised between 0 and +INFINITY.
 */
public class Ray2D extends AbstractLine2D implements Cloneable {

	private static final long serialVersionUID = 1L;
	
	/**
     * Empty constructor for Ray2D. Default is ray starting at origin, and
     * having a slope of 1*dx and 0*dy.
     */
    public Ray2D() {
        this(0, 0, 1, 0);
    }

    /**
     * Creates a new Ray2D, originating from
     * <code>point1<\code>, and going in the
     * direction of <code>point2<\code>.
     */
    public Ray2D(Point2D point1, Point2D point2) {
        this(point1.getX(), point1.getY(), 
        		point2.getX()-point1.getX(),
        		point2.getY()-point1.getY());
    }

    /**
     * Creates a new Ray2D, originating from point
     * <code>(x1,y1)<\code>, and going 
     * in the direction defined by vector <code>(dx, dy)<\code>.
     */
    public Ray2D(double x1, double y1, double dx, double dy) {
        super(x1, y1, dx, dy);
    }

    /**
     * Creates a new Ray2D, originating from point <code>point<\code>, and going 
     * in the direction defined by vector <code>(dx,dy)<\code>.
     */
    public Ray2D(Point2D point, double dx, double dy) {
        this(point.getX(), point.getY(), dx, dy);
    }

    /**
     * Creates a new Ray2D, originating from point <code>point<\code>, and going 
     * in the direction specified by <code>vector<\code>.
     */
    public Ray2D(Point2D point, Vector2D vector) {
        this(point.getX(), point.getY(), vector.getX(), vector.getY());
    }

    /**
     * Creates a new Ray2D, originating from point <code>point<\code>, and going 
     * in the direction specified by <code>angle<\code> (in radians).
     */
    public Ray2D(Point2D point, double angle) {
        this(point.getX(), point.getY(), Math.cos(angle), Math.sin(angle));
    }

    /**
     * Creates a new Ray2D, originating from point
     * <code>(x, y)<\code>, and going 
     * in the direction specified by <code>angle<\code> (in radians).
     */
    public Ray2D(double x, double y, double angle) {
        this(x, y, Math.cos(angle), Math.sin(angle));
    }

    /**
     * Define a new Ray, with same characteristics as given object.
     */
    public Ray2D(LinearShape2D line) {
        super(line);
    }
    
    @Override
    public boolean containsParametric(double t) {
    	return t >= 0;
    }

    // ===================================================================
    // methods specific to Ray2D

    /**
     * Static factory for creating a new ray.
     * @since 0.8.1
     */
    public final static Ray2D create(Point2D origin, Vector2D direction) {
    	return new Ray2D(origin, direction);
    }
    
    /**
     * Static factory for creating a new ray, originating from
     * <code>origin<\code>, and going in the
     * direction of <code>target<\code>.
     * @since 0.8.1
     */
    public final static Ray2D create(Point2D origin, Point2D target) {
    	return new Ray2D(origin, target);
    }
    
    /**
     * @deprecated lines will become immutable in a future release
     */
    @Deprecated
    public void setRay(double x0, double y0, double dx, double dy) {
        this.x0 = x0;
        this.y0 = y0;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @deprecated lines will become immutable in a future release
     */
    @Deprecated
    public void setRay(Point2D p1, Point2D p2) {
        this.x0 = p1.getX();
        this.y0 = p1.getY();
        this.dx = p2.getX()-this.x0;
        this.dy = p2.getY()-this.y0;
    }

    /**
     * @deprecated lines will become immutable in a future release
     */
    @Deprecated
    public void setRay(Point2D point, Vector2D vect) {
        this.x0 = point.getX();
        this.y0 = point.getY();
        this.dx = vect.getX();
        this.dy = vect.getY();
    }


    // ===================================================================
    // methods implementing the CirculinearCurve2D interface

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#getParallel(double)
	 */
	public Ray2D getParallel(double d) {
        double dd = Math.sqrt(dx*dx+dy*dy);
        return new Ray2D(x0+dy*d/dd, y0-dx*d/dd, dx, dy);
	}

    // ===================================================================
    // methods implementing the ContinuousCurve2D interface

    /** Throws an infiniteShapeException */
    public GeneralPath appendPath(GeneralPath path) {
        throw new UnboundedShapeException(this);
    }

    /** Throws an infiniteShapeException */
    public java.awt.geom.GeneralPath getGeneralPath() {
        throw new UnboundedShapeException(this);
    }

    // ===================================================================
    // methods implementing the Curve2D interface

	@Override
    public Point2D getFirstPoint() {
        return new Point2D(x0, y0);
    }

    public Point2D getPoint(double t) {
        t = Math.max(t, 0);
        return new Point2D(x0+t*dx, y0+t*dy);
    }

    public double getT0() {
        return 0;
    }

    public double getT1() {
        return Double.POSITIVE_INFINITY;
    }

    public InvertedRay2D getReverseCurve() {
        return new InvertedRay2D(x0, y0, -dx, -dy);
    }

    // ===================================================================
    // methods implementing the Shape2D interface

    /** Always returns false, because a ray is not bounded. */
    public boolean isBounded() {
        return false;
    }

    public boolean contains(double x, double y) {
        if (!this.supportContains(x, y))
            return false;
        double t = this.getPositionOnLine(x, y);
        return t>-Shape2D.ACCURACY;
    }

    public Box2D getBoundingBox() {
        double t = Double.POSITIVE_INFINITY;
        return new Box2D(x0, x0+t*dx, y0, y0+t*dy);
    }

    @Override
    public Ray2D transform(AffineTransform2D trans) {
        double[] tab = trans.getCoefficients();
        double x1 = x0*tab[0]+y0*tab[1]+tab[2];
        double y1 = x0*tab[3]+y0*tab[4]+tab[5];
        return new Ray2D(x1, y1, dx*tab[0]+dy*tab[1], dx*tab[3]+dy*tab[4]);
    }


    // ===================================================================
    // methods implementing the Object interface

    @Override
    public String toString() {
        return new String("Ray2D(" + x0 + "," + y0 + "," + 
        		dx + "," + dy + ")");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ray2D))
            return false;
        Ray2D ray = (Ray2D) obj;
        if (Math.abs(x0-ray.x0)>Shape2D.ACCURACY)
            return false;
        if (Math.abs(y0-ray.y0)>Shape2D.ACCURACY)
            return false;
        if (Math.abs(dx-ray.dx)>Shape2D.ACCURACY)
            return false;
        if (Math.abs(dy-ray.dy)>Shape2D.ACCURACY)
            return false;
        return true;
    }

    @Override
    public Ray2D clone() {
        return new Ray2D(x0, y0, dx, dy);
        
    }
}