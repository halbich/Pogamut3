/**
 * File: 	ContinuousCirculinearBoundary2D.java
 * Project: javaGeom-circulinear
 * 
 * Distributed under the LGPL License.
 *
 * Created: 5 juil. 09
 */
package math.geom2d.circulinear;

import math.geom2d.curve.Curve2D;
import math.geom2d.domain.Boundary2D;
import math.geom2d.domain.ContinuousBoundary2D;
import math.geom2d.transform.CircleInversion2D;


/**
 * Tagging interface to gather Continuous and boundary circulinear curves.
 * @author dlegland
 *
 */
public interface CirculinearContour2D extends ContinuousBoundary2D,
CirculinearContinuousCurve2D, CirculinearBoundary2D {

	public CirculinearContour2D getParallel(double d);
	public CirculinearContour2D transform(CircleInversion2D inv);
	public Boundary2D getReverseCurve();
}
