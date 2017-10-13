/* file : LinearRing2D.java
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
 * 
 * Created on 16 avr. 2007
 *
 */

package math.geom2d.polygon;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;

import math.geom2d.AffineTransform2D;
import math.geom2d.Point2D;
import math.geom2d.Shape2D;
import math.geom2d.circulinear.*;
import math.geom2d.domain.ContinuousBoundary2D;
import math.geom2d.domain.Domain2D;
import math.geom2d.domain.GenericDomain2D;
import math.geom2d.line.LineSegment2D;
import math.geom2d.transform.CircleInversion2D;

/**
 * <p>
 * A LinearRing2D is a Polyline2D whose last point is connected to the first one.
 * This is typically the boundary of a SimplePolygon2D.
 * </p>
 * <p>
 * The name 'LinearRing2D' was used for 2 reasons:
 * <ul><li>it is short</li> <li>it is consistent with the JTS name</li></ul>
 * </p>
 * @author dlegland
 */
public class LinearRing2D extends Polyline2D implements CirculinearContour2D {

	public LinearRing2D() {
        super();
    }

    public LinearRing2D(Point2D initialPoint) {
        super(initialPoint);
    }

    public LinearRing2D(Point2D[] points) {
        super(points);
    }

    public LinearRing2D(double[] xcoords, double[] ycoords) {
        super(xcoords, ycoords);
    }

    public LinearRing2D(Collection<? extends Point2D> points) {
        super(points);
    }

    
    // ===================================================================
    // Static methods
    
    /**
     * Static factory for creating a new LinearRing2D from a collection of
     * points.
     * @since 0.8.1
     */
    public static LinearRing2D create(Collection<? extends Point2D> points) {
    	return new LinearRing2D(points);
    }
    
    /**
     * Static factory for creating a new LinearRing2D from an array of
     * points.
     * @since 0.8.1
     */
    public static LinearRing2D create(Point2D[] points) {
    	return new LinearRing2D(points);
    }
    

    // ===================================================================
    // Methods specific to ClosedPolyline2D

    /**
     * Computes area of the polyline, by returning the absolute value of the
     * signed area.
     */
    public double getArea() {
        return Math.abs(this.getSignedArea());
    }

    /**
     * Computes the signed area of the polyline. Algorithm is taken from page:
     * <a href="http://local.wasp.uwa.edu.au/~pbourke/geometry/polyarea/">
     * http://local.wasp.uwa.edu.au/~pbourke/geometry/polyarea/</a>. Signed are
     * is positive if polyline is oriented counter-clockwise, and negative
     * otherwise. Result is wrong if polyline is self-intersecting.
     * 
     * @return the signed area of the polyline.
     */
    public double getSignedArea() {
        double area = 0;
        Point2D prev = this.points.get(this.points.size()-1);
        Point2D point;
        for (int i = 0; i<points.size(); i++) {
            point = this.points.get(i);
            area += prev.getX()*point.getY()-prev.getY()*point.getX();
            prev = point;
        }
        return area /= 2;
    }

    // ===================================================================
    // Methods specific to Polyline2D

    /**
     * Returns an array of LineSegment2D. The number of edges is the same as
     * the number of vertices.
     * 
     * @return the edges of the polyline
     */
    @Override
    public Collection<LineSegment2D> getEdges() {
        int n = points.size();
        ArrayList<LineSegment2D> edges = new ArrayList<LineSegment2D>();

        if (n<2)
            return edges;

        for (int i = 0; i<n-1; i++)
            edges.add(new LineSegment2D(points.get(i), points.get(i+1)));
        edges.add(new LineSegment2D(points.get(n-1), points.get(0)));
        return edges;
    }

    @Override
    public LineSegment2D getLastEdge() {
        int n = points.size();
        if (n<2)
            return null;
        return new LineSegment2D(points.get(n-1), points.get(0));
    }

	// ===================================================================
    // Methods inherited from CirculinearCurve2D

	@Override
    public CirculinearRing2D getParallel(double dist) {
    	return GenericCirculinearRing2D.create(
    			CirculinearCurve2DUtils.createContinuousParallel(this, dist)
    			.getSmoothPieces());
    }
    
	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#transform(math.geom2d.transform.CircleInversion2D)
	 */
	@Override
	public CirculinearContour2D transform(CircleInversion2D inv) {
		
		// Create array for storing transformed arcs
		Collection<LineSegment2D> edges = this.getEdges();
		ArrayList<CirculinearElement2D> arcs = 
			new ArrayList<CirculinearElement2D>(edges.size());
		
		// Transform each arc
		for(LineSegment2D edge : edges) {
			arcs.add(edge.transform(inv));
		}
		
		// create the transformed shape
		return new BoundaryPolyCirculinearCurve2D<CirculinearElement2D>(arcs);
	}

	// ===================================================================
    // Methods inherited from Boundary2D

    public Collection<ContinuousBoundary2D> getBoundaryCurves() {
        ArrayList<ContinuousBoundary2D> list = 
            new ArrayList<ContinuousBoundary2D>(1);
        list.add(this);
        return list;
    }

    public Domain2D getDomain() {
        return new GenericDomain2D(this);
    }

    public void fill(Graphics2D g2) {
        g2.fill(this.getGeneralPath());
    }

    // ===================================================================
    // Methods inherited from interface OrientedCurve2D

    /*
     * (non-Javadoc)
     * 
     * @see math.geom2d.OrientedCurve2D#getSignedDistance(double, double)
     */
    @Override
    public double getSignedDistance(double x, double y) {
        return (this.isInside(x, y) ? -1 : 1)*this.getDistance(x, y);
    }

    /*
     * (non-Javadoc)
     * 
     * @see math.geom2d.OrientedCurve2D#getSignedDistance(java.awt.geom.Point2D)
     */
    @Override
    public double getSignedDistance(java.awt.geom.Point2D point) {
        return getSignedDistance(point.getX(), point.getY());
    }

    /*
     * (non-Javadoc)
     * 
     * @see math.geom2d.OrientedCurve2D#getWindingAngle(java.awt.geom.Point2D)
     */
    @Override
    public double getWindingAngle(java.awt.geom.Point2D point) {
        int wn = Polygon2DUtils.windingNumber(this.points, point);
        return wn*2*Math.PI;
    }

    public boolean isInside(double x, double y) {
        return this.isInside(new Point2D(x, y));
    }

    /*
     * (non-Javadoc)
     * 
     * @see math.geom2d.OrientedCurve2D#isInside(java.awt.geom.Point2D)
     */
    @Override
    public boolean isInside(java.awt.geom.Point2D point) {
        // TODO: choose convention for points on the boundary
        int wn = Polygon2DUtils.windingNumber(this.points, point);
        if (wn==1)
            return true;
        if (this.contains(point))
            return true;
        return false;
    }

    // ===================================================================
    // Methods inherited from interface ContinuousCurve2D

    /**
     * return true, by definition.
     */
    @Override
    public boolean isClosed() {
        return true;
    }

    // ===================================================================
    // Methods inherited from interface Curve2D

    /**
     * Returns point from position as double. Position t can be from 0 to n,
     * with n equal to the number of vertices of the polyline.
     */
    @Override
    public math.geom2d.Point2D getPoint(double t) {
        // format position to stay between limits
        double t0 = this.getT0();
        double t1 = this.getT1();
        t = Math.max(Math.min(t, t1), t0);

        int n = points.size();

        // index of vertex before point
        int ind0 = (int) Math.floor(t+Shape2D.ACCURACY);
        double tl = t-ind0;

        if (ind0==n)
            ind0 = 0;
        Point2D p0 = points.get(ind0);

        // check if equal to a vertex
        if (Math.abs(t-ind0)<Shape2D.ACCURACY)
            return new Point2D(p0);

        // index of vertex after point
        int ind1 = ind0+1;
        if (ind1==n)
            ind1 = 0;
        Point2D p1 = points.get(ind1);

        // position on line;
        double x0 = p0.getX();
        double y0 = p0.getY();
        double dx = p1.getX()-x0;
        double dy = p1.getY()-y0;

        return new Point2D(x0+tl*dx, y0+tl*dy);
    }

    /**
     * returns 0.
     */
    @Override
    public double getT0() {
        return 0;
    }

    /**
     * return the number of points in the polyline.
     */
    @Override
    public double getT1() {
        return points.size();
    }

    /**
     * return the first point of the polyline.
     */
    @Override
    public Point2D getFirstPoint() {
        if (points.size()==0)
            return null;
        return points.get(0);
    }

    /**
     * return the first point, as this is the same as the last point.
     */
    @Override
    public Point2D getLastPoint() {
        if (points.size()==0)
            return null;
        return points.get(0);
    }

	@Override
    public Collection<? extends LinearRing2D> getContinuousCurves() {
    	return wrapCurve(this);
    }

    /**
     * Returns the closed polyline with same points taken in reverse order. The
     * first points is still the same. Points of reverse curve are the same as
     * the original curve (same pointers).
     */
    @Override
    public LinearRing2D getReverseCurve() {
        Point2D[] points2 = new Point2D[points.size()];
        int n = points.size();
        if (n>0) {
            points2[0] = points.get(0);
            for (int i = 1; i<n; i++)
                points2[i] = points.get(n-i);
        }
        return new LinearRing2D(points2);
    }

    /**
     * Return an instance of Polyline2D. If t1 is lower than t0, the returned
     * Polyline contains the origin of the curve.
     */
    @Override
    public Polyline2D getSubCurve(double t0, double t1) {
        // code adapted from CurveSet2D

        Polyline2D res = new Polyline2D();

        // number of points in the polyline
        int indMax = this.getVertexNumber();

        // format to ensure t is between T0 and T1
        t0 = Math.min(Math.max(t0, 0), indMax);
        t1 = Math.min(Math.max(t1, 0), indMax);

        // find curves index
        int ind0 = (int) Math.floor(t0+Shape2D.ACCURACY);
        int ind1 = (int) Math.floor(t1+Shape2D.ACCURACY);

        // need to subdivide only one line segment
        if (ind0==ind1&&t0<t1) {
            // extract limit points
            res.addPoint(this.getPoint(t0));
            res.addPoint(this.getPoint(t1));
            // return result
            return res;
        }

        // add the point corresponding to t0
        res.addPoint(this.getPoint(t0));

        if (ind1>ind0) {
            // add all the whole points between the 2 cuts
            for (int n = ind0+1; n<=ind1; n++)
                res.addPoint(points.get(n));
        } else {
            // add all points until the end of the set
            for (int n = ind0+1; n<indMax; n++)
                res.addPoint(points.get(n));

            // add all points from the beginning of the set
            for (int n = 0; n<=ind1; n++)
                res.addPoint(points.get(n));
        }

        // add the last point
        res.addPoint(this.getPoint(t1));

        // return the curve set
        return res;
    }

    // ===================================================================
    // Methods inherited from interface Shape2D

    /**
     * Return the transformed shape, as a ClosePolyline2D.
     */
    @Override
    public LinearRing2D transform(AffineTransform2D trans) {
        Point2D[] pts = new Point2D[points.size()];
        for (int i = 0; i<points.size(); i++)
            pts[i] = trans.transform(points.get(i));
        return new LinearRing2D(pts);
    }

    /*
     * (non-Javadoc)
     * 
     * @see math.geom2d.ContinuousCurve2D#appendPath(java.awt.geom.GeneralPath)
     */
    @Override
    public java.awt.geom.GeneralPath appendPath(java.awt.geom.GeneralPath path) {

        if (points.size()<2)
            return path;

        // move to last first point of the curve (then a line will be drawn to
        // the first point)
        Point2D p0 = this.getLastPoint();
        path.moveTo((float) p0.getX(), (float) p0.getY());
        
        // process each point
        for(Point2D point : points)
            path.lineTo((float) point.getX(), (float) point.getY());
        
        // close the path, even if the path is already at the right position
        path.closePath();
        
        return path;
    }

    /**
     * Return a general path iterator.
     */
    @Override
    public java.awt.geom.GeneralPath getGeneralPath() {
        java.awt.geom.GeneralPath path = new java.awt.geom.GeneralPath();
        if (points.size()<2)
            return path;
        return this.appendPath(path);
//        // get point iterator
//        Iterator<Point2D> iter = points.iterator();
//
//        // move to first point
//        Point2D point = iter.next();
//        path.moveTo((float) (point.getX()), (float) (point.getY()));
//
//        // line to each other point
//        while (iter.hasNext()) {
//            point = iter.next();
//            path.lineTo((float) (point.getX()), (float) (point.getY()));
//        }
//
//        // closes the path
//        path.closePath();
//
//        return path;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LinearRing2D))
            return false;
        LinearRing2D ring = (LinearRing2D) object;

        if (points.size()!=ring.points.size())
            return false;
        for (int i = 0; i<points.size(); i++)
            if (!(points.get(i)).equals(ring.points.get(i)))
                return false;
        return true;
    }
    
    @Override
    public LinearRing2D clone() {
        ArrayList<Point2D> array = new ArrayList<Point2D>(points.size());
        for(Point2D point : points)
            array.add(point.clone());
        return new LinearRing2D(array);
    }
}
