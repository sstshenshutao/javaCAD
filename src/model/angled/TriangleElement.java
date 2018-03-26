/**
 * 
 */
package model.angled;

import model.GeometricModelElement;
import model.Point;
import util.Constants;

/**
 * @author Shutao Shen
 *
 */
public class TriangleElement extends AngledGeometricElement {

	
	public TriangleElement() {
		super(new Point[] { new Point(0, 0), new Point(0, 0) , new Point(0, 0)});
	}

	/* (non-Javadoc)
	 * @see model.GeometricModelElement#getJSONClassName()
	 */
	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ANGLED_TRIANGLE_ELEMENT;
	}

	@Override
	public TriangleElement cloneElement() {
		TriangleElement angleEle = new TriangleElement();

		// copy the points
		Point[] p = new Point[super.getPoints().length];
		for (int i = 0; i < p.length; i++) {
			Point old = super.getPoint(i);
			Point n = new Point(old.getX(), old.getY());
			p[i] = n;
		}
		angleEle.setPoints(p);
		angleEle.changeColorCode(super.getColorCode());
		return angleEle;
	}

}
