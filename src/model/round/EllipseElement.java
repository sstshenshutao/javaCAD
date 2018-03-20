package model.round;

import java.util.HashMap;

import model.GeometricModelElement;
import model.Point;
import util.Constants;

/**
 * Representing an ellipse
 * @author Nora Wester
 */
public class EllipseElement extends RoundGeometricElement {

	/**
	 * Constructor first point => lower left corner second point => upper right corner
	 */
	public EllipseElement() {
		super(new Point[] { new Point(0, 0), new Point(0, 0) });
	}

	/**
	 * set the the upper left edge of the surrounding rectangle
	 * 
	 * @param start
	 *            point of the lower left edge of the surrounding rectangle
	 */
	public void setStartPoint(Point start) {
		super.changePoint(0, start);
	}

	/**
	 * set the width of the ellipse
	 * 
	 * @param width
	 *            new width of the ellipse
	 */
	public void setWidth(double width) {
		Point p0 = super.getPoint(0);
		Point p1 = super.getPoint(1);
		p1.setX(p0.getX() + width);
	}

	/**
	 * set the height of the ellipse
	 * 
	 * @param height
	 *            new height of the ellipse
	 */
	public void setHeight(double height) {
		Point p0 = super.getPoint(0);
		Point p1 = super.getPoint(1);
		p1.setY(p0.getY() + height);
	}

	@Override
	public GeometricModelElement cloneElement() {

		EllipseElement ellipse = new EllipseElement();

		Point[] p = new Point[super.getPoints().length];
		for (int i = 0; i < p.length; i++) {
			Point old = super.getPoint(i);
			Point n = new Point(old.getX(), old.getY());
			p[i] = n;
		}
		ellipse.setPoints(p);
		ellipse.changeColorCode(super.getColorCode());

		// copy the meta data
		HashMap<String, Object> meta = getMetaData();
		if (meta.containsKey(Constants.ANGLE))
			ellipse.rotate((double) meta.get(Constants.ANGLE));

		if (meta.containsKey(Constants.TRANSLATE)) {
			Point trans = (Point) meta.get(Constants.TRANSLATE);
			double x = trans.getX();
			double y = trans.getY();
			ellipse.move(x, y);
		}
		if (meta.containsKey(Constants.SCALE))
			ellipse.scale((double) meta.get(Constants.SCALE));

		return ellipse;
	}

	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ROUND_ELLIPSE_ELEMENT;
	}
}