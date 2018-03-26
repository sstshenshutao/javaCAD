package model.angled;

import model.GeometricModelElement;
import model.Point;
import util.Constants;

public class RectangleElement extends AngledGeometricElement {

	public RectangleElement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getJSONClassName() {
		// TODO Auto-generated method stub
		return Constants.GEOMETRIC_ANGLED_RECTANGLE_ELEMENT;
	}

	
	@Override
	public RectangleElement cloneElement() {
		RectangleElement angleEle = new RectangleElement();

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
