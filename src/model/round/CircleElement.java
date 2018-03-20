package model.round;

import java.util.HashMap;

import model.Point;
import util.Constants;

/**
 * Representing a circle
 * @author Nora Wester
 */
public class CircleElement extends EllipseElement {

	/**
	 * Sets the width and because this is a circle it also sets the height of this circle.
	 * 
	 * @param width
	 */
	public void setWidth(double width) {
		// if you have a circle, the width is equal to the height
		super.setHeight(width);
		super.setWidth(width);
	}

	/**
	 * Sets the height and because this is a circle it also sets the width of this circle.
	 * 
	 * @param height
	 */
	public void setHeight(double height) {
		// if you have a circle, the width is equal to the height
		super.setHeight(height);
		super.setWidth(height);
	}

	@Override
	public CircleElement cloneElement() {
		CircleElement circle = new CircleElement();

		// copy the points
		Point[] p = new Point[super.getPoints().length];
		for (int i = 0; i < p.length; i++) {
			Point old = super.getPoint(i);
			Point n = new Point(old.getX(), old.getY());
			p[i] = n;
		}
		circle.setPoints(p);
		circle.changeColorCode(super.getColorCode());

		// copy the meta data
		HashMap<String, Object> meta = getMetaData();
		if (meta.containsKey(Constants.ANGLE))
			circle.rotate((double) meta.get(Constants.ANGLE));

		if (meta.containsKey(Constants.TRANSLATE)) {
			Point trans = (Point) meta.get(Constants.TRANSLATE);
			double x = trans.getX();
			double y = trans.getY();
			circle.move(x, y);
		}
		if (meta.containsKey(Constants.SCALE))
			circle.scale((double) meta.get(Constants.SCALE));

		return circle;
	}

	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ROUND_ELLIPSE_CIRCLE_ELEMENT;
	}
}