package model;

/**
 * Representing a point with x and y dimension
 * @author Nora Wester
 */
public class Point {

	// x position of the point
	private double	xPosition;
	// y position of the point
	private double	yPosition;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x position of the point
	 * @param y
	 *            y position of the point
	 */
	public Point(double x, double y) {
		xPosition = x;
		yPosition = y;
	}

	/**
	 * get the x position of the point
	 * 
	 * @return x position of the point
	 */
	public double getX() {
		return xPosition;
	}

	/**
	 * get the y position of the point
	 * 
	 * @return y position of the point
	 */
	public double getY() {
		return yPosition;
	}

	/**
	 * set a new x position for the point
	 * 
	 * @param x
	 *            new x position
	 */
	public void setX(double x) {
		xPosition = x;
	}

	/**
	 * set a new y position for the point
	 * 
	 * @param y
	 *            new y position
	 */
	public void setY(double y) {
		yPosition = y;
	}

	/**
	 * Return the JSON representation of this point
	 * 
	 * @return the JSON representation of this point
	 */
	public String toJSON() {
		return "{\"x\": " + this.getX() + ", \"y\" :" + this.getY() + "}";
	}
}