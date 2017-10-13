/**
 * File: 	GenericCirculinearRing2D.java
 * Project: javaGeom
 * 
 * Distributed under the LGPL License.
 *
 * Created: 11 mai 09
 */
package math.geom2d.circulinear;

import java.awt.Graphics2D;
import java.util.Collection;

import math.geom2d.AffineTransform2D;
import math.geom2d.domain.BoundaryPolyCurve2D;
import math.geom2d.domain.ContinuousOrientedCurve2D;
import math.geom2d.transform.CircleInversion2D;


/**
 * A basic implementation of a CirculinearRing2D, which is supposed to be
 * always bounded and closed.
 * @author dlegland
 *
 */
public class GenericCirculinearRing2D 
extends PolyCirculinearCurve2D<CirculinearElement2D>
implements CirculinearRing2D {
//TODO: parametrize with curve type ?
	
    // ===================================================================
    // constructors

    public GenericCirculinearRing2D() {
        super();
        this.closed = true;
    }

    public GenericCirculinearRing2D(int size) {
        super(size);
        this.closed = true;
    }

    public GenericCirculinearRing2D(CirculinearElement2D[] curves) {
        super(curves);
        this.closed = true;
    }

    /**
     * @deprecated, as a ring is closed by definition (0.8.1)
     */
    @Deprecated
    public GenericCirculinearRing2D(CirculinearElement2D[] curves, 
    		boolean closed) {
        super(curves, closed);
    }

    public GenericCirculinearRing2D(
    		Collection<? extends CirculinearElement2D> curves) {
        super(curves);
        this.closed = true;
    }

    /**
     * @deprecated, as a ring is closed by definition (0.8.1)
     */
    @Deprecated
    public GenericCirculinearRing2D(
    		Collection<? extends CirculinearElement2D> curves, 
    		boolean closed) {
        super(curves, closed);
    }

    // ===================================================================
    // static methods

    /**
     * Static factory for creating a new GenericCirculinearRing2D from a collection of
     * curves.
     * @since 0.8.1
     */
    public static <T extends CirculinearElement2D> GenericCirculinearRing2D
    create(Collection<T> curves) {
    	return new GenericCirculinearRing2D(curves);
    }
    
    /**
     * Static factory for creating a new GenericCirculinearRing2D from an array of
     * curves.
     * @since 0.8.1
     */
    public static GenericCirculinearRing2D create(
    		CirculinearElement2D[] curves) {
    	return new GenericCirculinearRing2D(curves);
    }

    
    // ===================================================================
    // methods specific to GenericCirculinearRing2D

    // ===================================================================
    // static methods

	@Override
    public CirculinearRing2D getParallel(double dist) {
    	return new GenericCirculinearRing2D(
    			CirculinearCurve2DUtils.createContinuousParallel(this, dist)
    			.getSmoothPieces());
    }
    
	public Collection<? extends GenericCirculinearRing2D> getContinuousCurves() {
    	return wrapCurve(this);
    }
	
	@Override
	public GenericCirculinearRing2D transform(CircleInversion2D inv) {
    	// Allocate array for result
		GenericCirculinearRing2D result =
			new GenericCirculinearRing2D(curves.size());
        
        // add each transformed element
        for (CirculinearElement2D element : curves)
            result.addCurve((CirculinearElement2D)element.transform(inv));
        return result;
	}
	
	/* (non-Javadoc)
	 * @see math.geom2d.domain.Boundary2D#fill(java.awt.Graphics2D)
	 */
	public void fill(Graphics2D g2) {
		g2.fill(this.getGeneralPath());
	}

	/* (non-Javadoc)
	 * @see math.geom2d.domain.Boundary2D#getBoundaryCurves()
	 */
	public Collection<? extends CirculinearContour2D> getBoundaryCurves() {
        return wrapCurve(this);
	}

	/* (non-Javadoc)
	 * @see math.geom2d.domain.Boundary2D#getDomain()
	 */
	public CirculinearDomain2D getDomain() {
		return new GenericCirculinearDomain2D(this);
	}

	@Override
	public GenericCirculinearRing2D getReverseCurve(){
    	int n = curves.size();
        // create array of reversed curves
    	CirculinearElement2D[] curves2 = new CirculinearElement2D[n];
        
        // reverse each curve
        for (int i = 0; i<n; i++)
            curves2[i] = (CirculinearElement2D)curves.get(n-1-i).getReverseCurve();
        
        // create the reversed final curve
        return new GenericCirculinearRing2D(curves2);
	}
	
	@Override
	public BoundaryPolyCurve2D<ContinuousOrientedCurve2D> 
	transform(AffineTransform2D trans) {
		// number of curves
		int n = this.getCurveNumber();
		
		// create result curve
        BoundaryPolyCurve2D<ContinuousOrientedCurve2D> result =
        	new BoundaryPolyCurve2D<ContinuousOrientedCurve2D>(n);
        
        // add each curve after class cast
        for (ContinuousOrientedCurve2D curve : curves)
            result.addCurve((ContinuousOrientedCurve2D)curve.transform(trans));
        return result;
	}

}
