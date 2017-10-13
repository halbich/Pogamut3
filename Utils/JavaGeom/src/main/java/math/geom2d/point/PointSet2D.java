/**

 * File: 	PointSet2D.java
 * Project: javaGeom
 * 
 * Distributed under the LGPL License.
 *
 * Created: 4 feb. 09
 */
package math.geom2d.point;

import java.util.Collection;

import math.geom2d.AffineTransform2D;
import math.geom2d.Box2D;
import math.geom2d.Point2D;


/**
 * A set of points. All points within the set are instances of Point2D.
 * The most direct implementation of PointSet2D is PointArray2D.
 * @author dlegland
 *
 */
public interface PointSet2D extends PointShape2D, Iterable<Point2D> {

    /**
     * Adds a new point to the point set. If point is not an instance of
     * Point2D, a Point2D with same location is added instead of point.
     * 
     * @param point the initial point in the set
     */
    public void addPoint(java.awt.geom.Point2D point);

    /**
     * Add a series of points
     * 
     * @param points an array of points
     */
    public void addPoints(Collection<? extends Point2D> points);

    /**
     * Returns an iterator on the internal point collection.
     * 
     * @return the collection of points
     */
    public Collection<Point2D> getPoints();

    /**
     * Returns the number of points in the set.
     * 
     * @return the number of points
     */
    public int getPointNumber();
    
    /**
     * Transforms the point set by returning a new point set containing each 
     * transformed point.
     */
    public abstract PointSet2D transform(AffineTransform2D trans);
    
    /**
     * Returns a new point set containing only points located within the box.
     */
    public abstract PointSet2D clip(Box2D box);
}
