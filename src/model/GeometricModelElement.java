package model;

import java.util.Arrays;

import javax.json.JsonArray;
import javax.json.JsonObject;

import util.Constants;

/**
 * Basic class of the model side picture elements
 * 
 * @author Nora Wester
 */
public abstract class GeometricModelElement {

	// points of the element
	private Point[]	points;
	// color of the element representing by intern color code
	private int		colorCode;

	/**
	 * Constructor
	 * 
	 * @param points
	 *            points of the element
	 * @param colorCode
	 *            color of the element representing by intern color code
	 */
	public GeometricModelElement(Point[] points, int colorCode) {
		this.points = points;
		this.colorCode = colorCode;
	}

	/**
	 * Constructor
	 * 
	 * @param points
	 *            points of the element
	 */
	public GeometricModelElement(Point[] points) {
		this.points = points;
		// set default color black
		this.colorCode = Constants.COLOR_CODE_BLACK;
	}

	/**
	 * Constructor
	 */
	public GeometricModelElement() {
		this.points = new Point[] {};
		// set default color black
		this.colorCode = Constants.COLOR_CODE_BLACK;
	}

	protected abstract void calculateMove(double xDirection, double yDirection);

	protected abstract void calculateRotation(double angle);

	protected abstract void calculateScale(double factor);

	protected abstract void calculateMirroring(String axis);

	/**
	 * translate this element to a JSON representation
	 * 
	 * @return JSON representation of this element
	 */
	public String toJSON() {
		Point[] ps = this.getPoints();
		String points = "[";
		for (int i = 0; i + 1 < ps.length; i++)
			points += ps[i].toJSON() + ", ";
		points += ps[ps.length - 1].toJSON() + "]";
		return ("{\"classname\": \"" + this.getJSONClassName() + "\", \"elementData\": " + "{\"colorcode\": "
				+ this.getColorCode() + ", \"points\": " + points + ",\"metaData\":{" + this.getJSONMetaData() + "}}}");
	}

	/**
	 * Retuns the MetaData of the Instance. If there are no, it return an empty String (""). The children can overwrite
	 * this class if they have metaData.
	 * 
	 * @return the MetaData of this instance
	 */
	protected String getJSONMetaData() {
		return "";
	}

	/**
	 * Retuns a Class-Identifier for building the JSON-String. The children of this class can and should overwrite this
	 * method. The Class-Identifier is a string from the constants class. If the name for a new class does not yet
	 * exist, define a new one and orientate youself on the existing Class-Identifierts.
	 * 
	 * @return a Class-Identifier
	 */
	protected abstract String getJSONClassName();

	/**
	 * get a similar Element with the same values
	 * 
	 * @return a clone of this Element
	 */
	public abstract GeometricModelElement cloneElement();

	/**
	 * translate the element in the given direction
	 * 
	 * @param xDirection
	 *            x component of the direction
	 * @param yDirection
	 *            y component of the direction
	 */
	public void move(double xDirection, double yDirection) {
		calculateMove(xDirection, yDirection);
	}

	/**
	 * rotate the element to the given angle
	 * 
	 * @param angle
	 *            angle to be rotated in degree
	 */
	public void rotate(double angle) {
		calculateRotation(angle);
	}

	/**
	 * scale the element about the given factor
	 * 
	 * @param factor
	 *            factor to be scaled
	 */
	public void scale(double factor) {
		calculateScale(factor);
	}

	/**
	 * mirror the element according to the given axis depending on the centroid
	 * 
	 * @param axis
	 *            given axis (possible values are x und y)
	 */
	public void mirror(String axis) {
		calculateMirroring(axis);
	}

	/**
	 * change the color code of the element to the given color code
	 * 
	 * @param colorCode
	 *            new color code
	 */
	public void changeColorCode(int colorCode) {
		if (Arrays.binarySearch(Constants.COLOR_CODES, colorCode) != -1) {
			this.colorCode = colorCode;
		}
	}

	/**
	 * get the points of the element
	 * 
	 * @return points of the element
	 */
	public Point[] getPoints() {
		return points;
	}

	/**
	 * set the points of the element
	 * 
	 * @param points
	 *            new element points
	 */
	public void setPoints(Point[] points) {
		this.points = points;
	}

	/**
	 * get the color code of the element
	 * 
	 * @return color code of the element
	 */
	public int getColorCode() {
		return this.colorCode;
	}

	/**
	 * change the point at the given position
	 * 
	 * @param position
	 *            position, where to add the new point
	 * @param p
	 *            new Point
	 */
	public void changePoint(int position, Point p) {
		points[position] = p;
	}

	public int getNumberOfPoints() {
		return points.length;
	}

	/**
	 * get Point from the given position
	 * 
	 * @param position
	 *            position of the wanted point
	 * @return wanted point
	 */
	public Point getPoint(int position) {
		return points[position];
	}

	/**
	 * Sets the values of the given Json Object as parameters of this Instance.
	 * 
	 * @param elementData
	 *            the JSON Object to use the data from
	 */
	public void setJSONdata(JsonObject elementData) {
		this.changeColorCode(elementData.getInt("colorcode"));
		this.setPoints(GeometricModelElement.readPointsFromJSON(elementData));
	}

	/**
	 * Constructs and returns a new Instance of the given geometricClass with the data of the Json Object added to the
	 * instance
	 * 
	 * @param jObject
	 *            the data to add to the new Instance
	 * @param geometricClass
	 *            the Class to construct a new instance of
	 * @return the new Instance of the given class
	 * @throws InstantiationException
	 *             If there is an Exception by Constructing the new Instance
	 * @throws IllegalAccessException
	 *             If there is an Exception by Constructing the new Instance
	 */
	public static GeometricModelElement constructFromJSONObject(JsonObject jObject,
			Class<? extends GeometricModelElement> geometricClass)
			throws InstantiationException, IllegalAccessException {

		if (geometricClass == null)
			return null;
		@SuppressWarnings("deprecation")
		GeometricModelElement geometricElem = geometricClass.newInstance();
		geometricElem.setJSONdata(jObject.getJsonObject("elementData"));
		return geometricElem;
	}

	/**
	 * Returns an Array of Points from a JsonObject which must have a parameter "points" of the type JsonArray where the
	 * points are represented in.
	 * 
	 * @param jObject
	 *            the JsonObject in wich the "points"-JsonArray is a parameter
	 * @return the represented Array of Points
	 */
	public static Point[] readPointsFromJSON(JsonObject jObject) {
		JsonArray jPoints = jObject.getJsonArray("points");
		Point[] points = new Point[jPoints.size()];
		for (int i = 0; i < jPoints.size(); i++)
			points[i] = GeometricModelElement.readPointFromJSON(jPoints.getJsonObject(i));
		return points;
	}

	/**
	 * Returns a Point from the given JsonObject. Only works for JsonObjects that actualy represent a Point.
	 * 
	 * @param jPoint
	 *            the JsonObject that represents a Point (with x and y value)
	 * @return the Point that was represented by the JsonObject
	 */
	protected static Point readPointFromJSON(JsonObject jPoint) {
		return new Point(jPoint.getJsonNumber("x").doubleValue(), jPoint.getJsonNumber("y").doubleValue());
	}
}