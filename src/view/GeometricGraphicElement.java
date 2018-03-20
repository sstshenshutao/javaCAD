package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;

import model.Point;
import util.Constants;
import util.Util;

/**
 * Representing a picture element (at the view side)
 * 
 * @author Nora Wester
 * @author David Koehler
 */
public class GeometricGraphicElement {
	// x positions of the points of the graphic element
	private double[]				xPoints;
	// y positions of the points of the graphic element
	private double[]				yPoints;

	// color of the graphic element
	private Color					color;
	// type of the graphic element (if it is a rounded or angled)
	private int						type;

	// rounded elements have some meta data
	private HashMap<String, Object>	meta;

	/**
	 * Constructor for a graphic element
	 * 
	 * @param xs
	 *            x positions of the points of the graphic element
	 * @param ys
	 *            y positions of the points of the graphic element
	 * @param colorCode
	 *            intern color code
	 * @param type
	 *            type of the graphic element
	 */
	public GeometricGraphicElement(double[] xs, double[] ys, int colorCode, int type) {
		// control, that there are the x and y positions of all points
		// instead there are no points given
		if (xs.length == ys.length) {
			xPoints = xs;
			yPoints = ys;
		} else {
			xPoints = new double[] {};
			yPoints = new double[] {};
		}
		// get the corresponding color
		color = Util.codeToColor(colorCode);
		this.type = type;
		this.meta = new HashMap<String, Object>();
	}

	/**
	 * Constructor for a graphic element
	 * 
	 * @param xs
	 *            x positions of the points of the graphic element
	 * @param ys
	 *            y positions of the points of the graphic element
	 * @param type
	 *            type of the graphic element
	 */
	public GeometricGraphicElement(double[] xs, double[] ys, int type) {
		// set the color to black
		new GeometricGraphicElement(xs, ys, -1, type);
	}

	/**
	 * Constructor for a graphic element
	 * 
	 * @param type
	 *            type of the graphic element
	 */
	public GeometricGraphicElement(int type) {
		// set the color to black, set no points and set no metadata
		new GeometricGraphicElement(new double[] {}, new double[] {}, type);
	}

	/**
	 * set the meta data for a rounded graphic element
	 * 
	 * @param meta
	 */
	public void setMetaData(HashMap<String, Object> meta) {
		this.meta = meta;
	}

	/**
	 * set the points
	 * 
	 * @param xs
	 *            x positions of the points of the graphic element
	 * @param ys
	 *            y positions of the points of the graphic element
	 */
	public void setPoints(double[] xs, double[] ys) {
		// control, that there are the x and y positions of all points
		if (xs.length == ys.length) {
			xPoints = xs;
			yPoints = ys;
		}
	}

	/**
	 * draw the graphic element
	 * 
	 * @param g
	 *            Graphic-Object on which the element should be drawn
	 */
	public void print(Graphics2D g) {

		// according to the type of the element, the points have other "functions"
		if (type == Constants.GRAPHIC_TYPE_ANGULAR) {
			g.setColor(color);
			g.drawPolygon(Util.toIntArray(xPoints), Util.toIntArray(yPoints), xPoints.length);
		} else {

			AffineTransform old = g.getTransform();

			double width = xPoints[1] - xPoints[0];
			double height = yPoints[1] - yPoints[0];
			Ellipse2D ellipse = new Ellipse2D.Double(xPoints[0], yPoints[0], width, height);
			Shape shape = null;

			// is there a translation?
			if (meta.containsKey(Constants.TRANSLATE))
				if (meta.get(Constants.TRANSLATE) instanceof Point) {
					Point p = (Point) meta.get(Constants.TRANSLATE);
					g.translate(p.getX(), p.getY());
				}

			// is there a scale?
			if (meta.containsKey(Constants.SCALE))
				if (meta.get(Constants.SCALE) instanceof Double) {
					double factor = (double) meta.get(Constants.SCALE);
					double x = xPoints[0] + (1 - factor) * (width / 2);
					double y = yPoints[0] + (1 - factor) * (height / 2);
					width = (width * factor);
					height = (height * factor);
					ellipse = new Ellipse2D.Double(x, y, width, height);
				}

			// is there a rotation?
			if (meta.containsKey(Constants.ANGLE))
				if (meta.get(Constants.ANGLE) instanceof Double) {
					AffineTransform transform = new AffineTransform();
					transform.rotate(Math.toRadians((double) meta.get(Constants.ANGLE)), ellipse.getCenterX(),
							ellipse.getCenterY());
					shape = transform.createTransformedShape(ellipse);
				}

			g.setColor(color);
			// draw it
			if (shape != null) {
				g.draw(shape);
			} else {
				g.draw(ellipse);
			}
			// set old transformation for next element
			g.setTransform(old);
		}
	}
}
