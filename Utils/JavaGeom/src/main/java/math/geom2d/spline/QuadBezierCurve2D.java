/* File QuadBezierCurve2D.java 
 *
 * Project : geometry
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

package math.geom2d.spline;

import java.awt.geom.QuadCurve2D;
import java.util.Collection;

import math.JavaGeomMath;
import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.Shape2D;
import math.geom2d.Vector2D;
import math.geom2d.curve.AbstractSmoothCurve2D;
import math.geom2d.curve.Curve2D;
import math.geom2d.curve.Curve2DUtils;
import math.geom2d.curve.CurveArray2D;
import math.geom2d.curve.CurveSet2D;
import math.geom2d.curve.SmoothCurve2D;
import math.geom2d.domain.ContinuousOrientedCurve2D;
import math.geom2d.line.LinearShape2D;
import math.geom2d.line.StraightLine2D;

/**
 * A quadratic bezier curve, defined by 3 points.
 * 
 * From javaGeom 0.8.0, this shape does not extends.
 * java.awt.geom.QuadCurve2D.Double anymore
 * 
 * @author Legland
 */
public class QuadBezierCurve2D extends AbstractSmoothCurve2D
implements SmoothCurve2D, ContinuousOrientedCurve2D, Cloneable {

	protected double x1, y1;
	protected double ctrlx, ctrly;
	protected double x2, y2;

    // ===================================================================
    // constructors

    public QuadBezierCurve2D() {
        this(0, 0, 0, 0, 0, 0);
    }

    /**
     * Build a new Bezier curve from its array of coefficients. The array must
     * have size 2*3.
     * 
     * @param coefs the coefficients of the QuadBezierCurve2D.
     */
    public QuadBezierCurve2D(double[][] coefs) {
        this(coefs[0][0], coefs[1][0], coefs[0][0]+coefs[0][1]/2.0, coefs[1][0]
                +coefs[1][1]/2.0, coefs[0][0]+coefs[0][1]+coefs[0][2],
                coefs[1][0]+coefs[1][1]+coefs[1][2]);
    }

    /**
     * Build a new quadratic Bezier curve by specifying position of extreme
     * points and position of control point. The resulting curve is totally
     * contained in the convex polygon formed by the 3 control points.
     * 
     * @param p1 first point
     * @param ctrl control point
     * @param p2 last point
     */
    public QuadBezierCurve2D(java.awt.geom.Point2D p1, java.awt.geom.Point2D ctrl,
            java.awt.geom.Point2D p2) {
        this(p1.getX(), p1.getY(), ctrl.getX(), ctrl.getY(), p2.getX(), p2
                .getY());
    }

    public QuadBezierCurve2D(java.awt.geom.Point2D[] pts) {
        this(pts[0].getX(), pts[0].getY(), pts[1].getX(), pts[1].getY(), pts[2]
                .getX(), pts[2].getY());
    }

    /**
     * Build a new quadratic Bezier curve by specifying position of extreme
     * points and position of control point. The resulting curve is totally
     * contained in the convex polygon formed by the 3 control points.
     */
    public QuadBezierCurve2D(double x1, double y1, double xctrl, double yctrl,
            double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.ctrlx = xctrl;
        this.ctrly = yctrl;
        this.x2 = x2;
        this.y2 = y2;
    }

    // ===================================================================
    // static methods
   
    /**
     * Static factory for creating a new Quadratic Bezier curve from 3 points.
     * @since 0.8.1
     */
    public static QuadBezierCurve2D create(Point2D p1, Point2D p2, Point2D p3) {
    	return new QuadBezierCurve2D(p1, p2, p3);
    }
    

    // ===================================================================
    // methods specific to QuadBezierCurve2D

    public Point2D getControl() {
        return new Point2D(ctrlx, ctrly);
    }

    public Point2D getP1() {
    	return this.getFirstPoint();
    }
    
    public Point2D getP2() {
    	return this.getLastPoint();
    }
    
    public Point2D getCtrl() {
    	return this.getControl();
    }
    
    /**
     * Returns the matrix of parametric representation of the line. Result is a
     * 2x3 array with coefficients:
     * <p>
     * <code>[ cx0  cx1 cx2] </code>
     * <p>
     * <code>[ cy0  cy1 cy2] </code>
     * <p>
     * Coefficients are from the parametric equation : <code>
     * x(t) = cx0 + cx1*t + cx2*t^2 
     * y(t) = cy0 + cy1*t + cy2*t^2
     * </code>
     */
    public double[][] getParametric() {
        double[][] tab = new double[2][3];
        tab[0][0] = x1;
        tab[0][1] = 2*ctrlx-2*x1;
        tab[0][2] = x2-2*ctrlx+x1;

        tab[1][0] = y1;
        tab[1][1] = 2*ctrly-2*y1;
        tab[1][2] = y2-2*ctrly+y1;
        return tab;
    }

    // ===================================================================
    // methods from OrientedCurve2D interface

    /**
     * Use winding angle of approximated polyline
     * 
     * @see math.geom2d.domain.OrientedCurve2D#getWindingAngle(java.awt.geom.Point2D)
     */
    public double getWindingAngle(java.awt.geom.Point2D point) {
        return this.getAsPolyline(100).getWindingAngle(point);
    }

    /**
     * return true if the point is 'inside' the domain bounded by the curve.
     * Uses a polyline approximation.
     * 
     * @param pt a point in the plane
     * @return true if the point is on the left side of the curve.
     */
    public boolean isInside(java.awt.geom.Point2D pt) {
        return this.getAsPolyline(100).isInside(pt);
    }

    public double getSignedDistance(java.awt.geom.Point2D point) {
        if (isInside(point))
            return -getDistance(point.getX(), point.getY());
        else
            return getDistance(point.getX(), point.getY());
    }

    /**
     * @see math.geom2d.domain.OrientedCurve2D#getSignedDistance(java.awt.geom.Point2D)
     */
    public double getSignedDistance(double x, double y) {
        if (isInside(new Point2D(x, y)))
            return -getDistance(x, y);
        else
            return getDistance(x, y);
    }

    // ===================================================================
    // methods from SmoothCurve2D interface

    public Vector2D getTangent(double t) {
        double[][] c = getParametric();
        double dx = c[0][1]+2*c[0][2]*t;
        double dy = c[1][1]+2*c[1][2]*t;
        return new Vector2D(dx, dy);
    }

    /**
     * returns the curvature of the Curve.
     */
    public double getCurvature(double t) {
        double[][] c = getParametric();
        double xp = c[0][1]+2*c[0][2]*t;
        double yp = c[1][1]+2*c[1][2]*t;
        double xs = 2*c[0][2];
        double ys = 2*c[1][2];

        return (xp*ys-yp*xs)/Math.pow(JavaGeomMath.hypot(xp, yp), 3);
    }

    // ===================================================================
    // methods from ContinousCurve2D interface

    /**
     * The cubic curve is never closed.
     */
    public boolean isClosed() {
        return false;
    }

    // ===================================================================
    // methods from Curve2D interface

    /**
     * Returns 0, as Bezier curve is parametrized between 0 and 1.
     */
    public double getT0() {
        return 0;
    }

    /**
     * Returns 1, as Bezier curve is parametrized between 0 and 1.
     */
    public double getT1() {
        return 1;
    }

    /**
     * Use approximation, by replacing Bezier curve with a polyline.
     * 
     * @see math.geom2d.curve.Curve2D#getIntersections(math.geom2d.line.LinearShape2D)
     */
    public Collection<Point2D> getIntersections(LinearShape2D line) {
        return this.getAsPolyline(100).getIntersections(line);
    }

    /**
     * @see math.geom2d.curve.Curve2D#getPoint(double)
     */
    public Point2D getPoint(double t) {
        t = Math.min(Math.max(t, 0), 1);
        double[][] c = getParametric();
        double x = c[0][0]+(c[0][1]+c[0][2]*t)*t;
        double y = c[1][0]+(c[1][1]+c[1][2]*t)*t;
        return new Point2D(x, y);
    }

    /**
     * Get the first point of the curve.
     * 
     * @return the first point of the curve
     */
	@Override
    public Point2D getFirstPoint() {
        return new Point2D(this.x1, this.y1);
    }

    /**
     * Get the last point of the curve.
     * 
     * @return the last point of the curve.
     */
	@Override
    public Point2D getLastPoint() {
        return new Point2D(this.x2, this.y2);
    }

    /**
     * Compute position by approximating cubic spline with a polyline.
     */
    public double getPosition(java.awt.geom.Point2D point) {
        int N = 100;
        return this.getAsPolyline(N).getPosition(point)/(N);
    }

    /**
     * Compute position by approximating cubic spline with a polyline.
     */
    public double project(java.awt.geom.Point2D point) {
        int N = 100;
        return this.getAsPolyline(N).project(point)/(N);
    }

    /**
     * Returns the bezier curve given by control points taken in reverse order.
     */
    public QuadBezierCurve2D getReverseCurve() {
        return new QuadBezierCurve2D(
        		this.getLastPoint(), this.getControl(), this.getFirstPoint());
    }

    /**
     * Computes portion of BezierCurve. If t1<t0, returns null.
     */
    public QuadBezierCurve2D getSubCurve(double t0, double t1) {
        t0 = Math.max(t0, 0);
        t1 = Math.min(t1, 1);
        if (t0>t1)
            return null;

        // Extreme points
        Point2D p0 = getPoint(t0);
        Point2D p1 = getPoint(t1);

        // tangent vectors at extreme points
        Vector2D v0 = getTangent(t0);
        Vector2D v1 = getTangent(t1);

        // compute position of control point as intersection of tangent lines
        StraightLine2D tan0 = new StraightLine2D(p0, v0);
        StraightLine2D tan1 = new StraightLine2D(p1, v1);
        Point2D control = tan0.getIntersection(tan1);

        // build the new quad curve
        return new QuadBezierCurve2D(p0, control, p1);
    }

    // ===================================================================
    // methods from Shape2D interface

	/* (non-Javadoc)
	 * @see math.geom2d.Shape2D#contains(double, double)
	 */
	public boolean contains(double x, double y) {
		return new QuadCurve2D.Double(
				x1, y1, ctrlx, ctrly, x2, y2).contains(x, y);
	}

	/* (non-Javadoc)
	 * @see math.geom2d.Shape2D#contains(java.awt.geom.Point2D)
	 */
	public boolean contains(java.awt.geom.Point2D p) {
		return this.contains(p.getX(), p.getY());
	}

	/**
     * @see math.geom2d.Shape2D#getDistance(java.awt.geom.Point2D)
     */
    public double getDistance(java.awt.geom.Point2D p) {
        return this.getDistance(p.getX(), p.getY());
    }

    /**
     * Compute approximated distance, computed on a polyline.
     * 
     * @see math.geom2d.Shape2D#getDistance(double, double)
     */
    public double getDistance(double x, double y) {
        return this.getAsPolyline(100).getDistance(x, y);
    }

    /**
     * return true, a cubic Bezier Curve is always bounded.
     */
    public boolean isBounded() {
        return true;
    }

    public boolean isEmpty() {
        return false;
    }

    /**
     * Clip the circle arc by a box. The result is an instance of
     * ContinuousOrientedCurveSet2D<QuadBezierCurve2D>, which contains only
     * instances of EllipseArc2D. If the ellipse arc is not clipped, the result
     * is an instance of ContinuousOrientedCurveSet2D<QuadBezierCurve2D>
     * which contains 0 curves.
     */
    public CurveSet2D<? extends QuadBezierCurve2D> clip(Box2D box) {
        // Clip the curve
        CurveSet2D<SmoothCurve2D> set = Curve2DUtils.clipSmoothCurve(this, box);

        // Stores the result in appropriate structure
        CurveArray2D<QuadBezierCurve2D> result = 
        	new CurveArray2D<QuadBezierCurve2D>(set.getCurveNumber());

        // convert the result
        for (Curve2D curve : set.getCurves()) {
            if (curve instanceof QuadBezierCurve2D)
                result.addCurve((QuadBezierCurve2D) curve);
        }
        return result;
    }

    public Box2D getBoundingBox() {
    	Point2D p1 = this.getFirstPoint();
        Point2D p2 = this.getControl();
        Point2D p3 = this.getLastPoint();
        double xmin = Math.min(Math.min(p1.getX(), p2.getX()), p3.getX());
        double xmax = Math.max(Math.max(p1.getX(), p2.getX()), p3.getX());
        double ymin = Math.min(Math.min(p1.getY(), p2.getY()), p3.getY());
        double ymax = Math.max(Math.max(p1.getY(), p2.getY()), p3.getY());
        return new Box2D(xmin, xmax, ymin, ymax);
    }

    /**
     * Returns the Bezier Curve transformed by the given AffineTransform2D. This
     * is simply done by transforming control points of the curve.
     */
    public QuadBezierCurve2D transform(AffineTransform2D trans) {
        return new QuadBezierCurve2D(
                trans.transform(this.getFirstPoint()), 
                trans.transform(this.getControl()),
                trans.transform(this.getLastPoint()));
    }

    public java.awt.geom.GeneralPath appendPath(java.awt.geom.GeneralPath path) {
        Point2D p2 = this.getControl();
        Point2D p3 = this.getLastPoint();
        path.quadTo(p2.getX(), p2.getY(), p3.getX(), p3.getY());
        return path;
    }

    public java.awt.geom.GeneralPath getGeneralPath() {
        java.awt.geom.GeneralPath path = new java.awt.geom.GeneralPath();
        Point2D p1 = this.getFirstPoint();
        Point2D p2 = this.getControl();
        Point2D p3 = this.getLastPoint();
        path.moveTo(p1.getX(), p1.getY());
        path.quadTo(p2.getX(), p2.getY(), p3.getX(), p3.getY());
        return path;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof QuadBezierCurve2D))
            return false;
        
        // Class cast
        QuadBezierCurve2D bezier = (QuadBezierCurve2D) obj;
        
        // Compare each field
        if(Math.abs(this.x1-bezier.x1)>Shape2D.ACCURACY) return false;
        if(Math.abs(this.y1-bezier.y1)>Shape2D.ACCURACY) return false;
        if(Math.abs(this.ctrlx-bezier.ctrlx)>Shape2D.ACCURACY) return false;
        if(Math.abs(this.ctrly-bezier.ctrly)>Shape2D.ACCURACY) return false;
        if(Math.abs(this.x2-bezier.x2)>Shape2D.ACCURACY) return false;
        if(Math.abs(this.y2-bezier.y2)>Shape2D.ACCURACY) return false;
        
        return true;
    }
    
	@Override
    public QuadBezierCurve2D clone() {
        return new QuadBezierCurve2D(x1, y1, ctrlx, ctrly, x2, y2);
    }
}
