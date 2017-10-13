/**
 * File: 	CirculinearBoundarySet2D.java
 * Project: javaGeom
 * 
 * Distributed under the LGPL License.
 *
 * Created: 11 mai 09
 */
package math.geom2d.circulinear;

import java.util.ArrayList;
import java.util.Collection;

import math.geom2d.Box2D;
import math.geom2d.curve.*;
import math.geom2d.domain.BoundarySet2D;
import math.geom2d.domain.ContinuousOrientedCurve2D;
import math.geom2d.transform.CircleInversion2D;


/**
 * A circulinear boundary which is composed of several CirculinearRing2D.
 * @author dlegland
 *
 */
public class CirculinearBoundarySet2D<T extends CirculinearContour2D> 
extends BoundarySet2D<T> implements	CirculinearBoundary2D {

    // ===================================================================
    // static constructors

	

    // ===================================================================
    // constructors

	/**
     * Empty constructor. Initializes an empty array of curves.
     */
    public CirculinearBoundarySet2D() {
    	this.curves = new ArrayList<T>();
    }

    /**
     * Empty constructor. Initializes an empty array of curves, 
     * with a given size for allocating memory.
     */
    public CirculinearBoundarySet2D(int n) {
    	this.curves = new ArrayList<T>(n);
    }

    /**
     * Constructor from an array of curves.
     * 
     * @param curves the array of curves in the set
     */
    public CirculinearBoundarySet2D(T[] curves) {
    	this.curves = new ArrayList<T>(curves.length);
        for (T element : curves)
            this.addCurve(element);
    }

    /**
     * Constructor from a collection of curves. The curves are added to the
     * inner collection of curves.
     * 
     * @param curves the collection of curves to add to the set
     */
    public CirculinearBoundarySet2D(T curve) {
    	this.curves = new ArrayList<T>();
        this.curves.add(curve);
    }

    /**
     * Constructor from a collection of curves. The curves are added to the
     * inner collection of curves.
     * 
     * @param curves the collection of curves to add to the set
     */
    public CirculinearBoundarySet2D(Collection<? extends T> curves) {
    	this.curves = new ArrayList<T>(curves.size());
        this.curves.addAll(curves);
    }

    
    // ===================================================================
    // static methods

//    /**
//     * Static factory for creating a new CirculinearBoundarySet2D from a
//     * collection of curves.
//     * @since 0.8.1
//     */
//	public static <T extends CirculinearContour2D> 
//	CirculinearBoundarySet2D<CirculinearContour2D>
//	create(Collection<T> curves) {
//		return new CirculinearBoundarySet2D<CirculinearContour2D>(curves);
//	}
//
//    /**
//     * Static factory for creating a new CirculinearBoundarySet2D from an 
//     * array of curves.
//     * @since 0.8.1
//     */
//    public static <T extends CirculinearContour2D> 
//    CirculinearBoundarySet2D<T> create(T[] curves) {
//    	return new CirculinearBoundarySet2D<T>(curves);
//    }

    
    // ===================================================================
    // methods implementing the CirculinearCurve2D interface

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#getLength()
	 */
	public double getLength() {
		double sum = 0;
		for(CirculinearCurve2D curve : this.getCurves())
			sum += curve.getLength();
		return sum;
	}

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#getLength(double)
	 */
	public double getLength(double pos) {
		return CirculinearCurve2DUtils.getLength(this, pos);
	}

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#getPosition(double)
	 */
	public double getPosition(double length) {
		return CirculinearCurve2DUtils.getPosition(this, length);
	}

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearShape2D#getBuffer(double)
	 */
	public CirculinearDomain2D getBuffer(double dist) {
		return CirculinearCurve2DUtils.computeBuffer(this, dist);
	}

	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearContinuousCurve2D#getParallel(double)
	 */
	public CirculinearCurve2D getParallel(double d) {
		return CirculinearCurve2DUtils.createParallel(this, d);
	}
	
	/* (non-Javadoc)
	 * @see math.geom2d.circulinear.CirculinearCurve2D#transform(math.geom2d.transform.CircleInversion2D)
	 */
	public CirculinearBoundarySet2D<? extends CirculinearContour2D> 
	transform(CircleInversion2D inv) {
    	// Allocate array for result
		CirculinearBoundarySet2D<CirculinearContour2D> result = 
			new CirculinearBoundarySet2D<CirculinearContour2D>(
					curves.size());
        
        // add each transformed curve
        for (CirculinearContour2D curve : curves)
            result.addCurve(curve.transform(inv));
        return result;
	}
	
    // ===================================================================
    // methods implementing the Curve2D interface

    @Override
    public Collection<? extends CirculinearContour2D> getContinuousCurves() {
    	// create array for storing result
    	ArrayList<T> result = new ArrayList<T>();

    	// return the set of curves
    	result.addAll(curves);
    	return result;
    }

	@Override
	public CirculinearCurveSet2D<? extends CirculinearContinuousCurve2D> clip(Box2D box) {
        // Clip the curve
        CurveSet2D<? extends Curve2D> set = Curve2DUtils.clipCurve(this, box);

        // Stores the result in appropriate structure
        int n = set.getCurveNumber();
        CirculinearCurveSet2D<CirculinearContinuousCurve2D> result = 
        	new CirculinearCurveSet2D<CirculinearContinuousCurve2D>(n);

        // convert the result, class cast each curve
        for (Curve2D curve : set.getCurves()) {
            if (curve instanceof CirculinearContinuousCurve2D)
                result.addCurve((CirculinearContinuousCurve2D) curve);
        }
        
        // return the new set of curves
        return result;
	}
    
	@Override
	public CirculinearBoundarySet2D<? extends CirculinearContour2D>
	getReverseCurve(){
    	int n = curves.size();
        // create array of reversed curves
    	CirculinearContour2D[] curves2 = new CirculinearContour2D[n];
        
        // reverse each curve
        for (int i = 0; i<n; i++)
            curves2[i] = (CirculinearContour2D)curves.get(n-1-i).getReverseCurve();
        
        // create the reversed final curve
        return new CirculinearBoundarySet2D<CirculinearContour2D>(curves2);
	}
	
    @Override
    public CirculinearCurveSet2D<? extends CirculinearContinuousCurve2D> getSubCurve(
            double t0, double t1) {
        // get the subcurve
    	CurveSet2D<? extends ContinuousOrientedCurve2D> curveSet =
    		super.getSubCurve(t0, t1);

        // create subcurve array
        ArrayList<CirculinearContinuousCurve2D> curves = 
        	new ArrayList<CirculinearContinuousCurve2D>(
        			curveSet.getCurveNumber());
        
        // class cast each curve
        for (Curve2D curve : curveSet.getCurves())
            curves.add((CirculinearContinuousCurve2D) curve);

        // Create CurveSet for the result
        return new CirculinearCurveSet2D<CirculinearContinuousCurve2D>(curves);
    }
}
