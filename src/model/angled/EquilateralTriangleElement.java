/**
 * 
 */
package model.angled;

import model.Point;
import util.Constants;

/**
 * @author Shutao Shen
 *
 */
public class EquilateralTriangleElement extends TriangleElement {


	
	

	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ANGLED_TRIANGLE_EQUILATERALTRIANGLE_ELEMENT;
	}
	
	@Override
	public EquilateralTriangleElement cloneElement() {
		EquilateralTriangleElement angleEle = new EquilateralTriangleElement();

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
