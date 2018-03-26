package model.angled;

import model.GeometricModelElement;
import model.Point;
import util.Constants;

public class TrapezoidElement extends AngledGeometricElement {

	public TrapezoidElement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ANGLED_TRAPEZOID_ELEMENT;
	}

	@Override
	public TrapezoidElement cloneElement() {
		TrapezoidElement angleEle = new TrapezoidElement();

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
