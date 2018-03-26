package model.angled;

import model.GeometricModelElement;
import model.Point;
import util.Constants;

public class HexagonElement extends AngledGeometricElement {

	
	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ANGLED_HEXAGON_ELEMENT;
	}

	
	@Override
	public HexagonElement cloneElement() {
		HexagonElement angleEle = new HexagonElement();

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
