package model.angled;

import model.Point;
import util.Constants;

public class IsoscelesTrapezoidElement extends TrapezoidElement {

	public IsoscelesTrapezoidElement() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ANGLED_TRAPEZOID_ISOSCELESTRAPEZOID_ELEMENT;
	}

	@Override
	public IsoscelesTrapezoidElement cloneElement() {
		IsoscelesTrapezoidElement angleEle = new IsoscelesTrapezoidElement();

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
