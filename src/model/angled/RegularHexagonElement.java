package model.angled;

import model.Point;
import util.Constants;

public class RegularHexagonElement extends HexagonElement {

	public RegularHexagonElement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ANGLED_HEXAGON_REGULARHEXAGON_ELEMENT;
	}
	
	@Override
	public RegularHexagonElement cloneElement() {
		RegularHexagonElement angleEle = new RegularHexagonElement();

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
