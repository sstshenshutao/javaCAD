package model.angled;

import model.Point;
import util.Constants;

public class SquareElement extends RectangleElement {

	public SquareElement() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected String getJSONClassName() {
		// TODO Auto-generated method stub
		return Constants.GEOMETRIC_ANGLED_RECTANGLE_SQUARE_ELEMENT;
	}

	@Override
	public SquareElement cloneElement() {
		SquareElement angleEle = new SquareElement();

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
