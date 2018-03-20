package model.round;

import java.util.HashMap;

import javax.json.JsonObject;

import model.GeometricModelElement;
import model.Point;
import util.Constants;

/**
 * basic class of the round picture elements
 * 
 * @author Nora Wester
 */
public abstract class RoundGeometricElement extends GeometricModelElement {

	// meta data of the rounded Element
	private HashMap<String, Object> metaData;

	/**
	 * Constructor
	 * 
	 * @param points
	 *            points of the element
	 */
	public RoundGeometricElement(Point[] points) {
		super(points);
		this.metaData = new HashMap<String, Object>();
	}

	/**
	 * gets the meta data of the rounded element
	 * 
	 * @return the meta data
	 */
	public HashMap<String, Object> getMetaData() {
		return metaData;
	}

	/**
	 * Adds an integer as value into the MetaData HashMap
	 * 
	 * @param key
	 *            the key to add
	 * @param value
	 *            the value to add
	 */
	private void addMetaDataDouble(String key, double value) {
		this.metaData.put(key, value);
	}

	/**
	 * Adds a Point as value into the MetaData HashMap
	 * 
	 * @param key
	 *            the key to add
	 * @param p
	 *            the point to add
	 */
	private void addMetaDataPoint(String key, Point p) {
		this.metaData.put(key, p);
	}

	@Override
	protected String getJSONMetaData() {
		String metaString = "";
		for (HashMap.Entry<String, Object> entry : this.metaData.entrySet())
			metaString += this.getJSONMetaValue(entry.getKey(), entry.getValue());
		if (metaString.length() > 0)
			return metaString.substring(0, metaString.length() - 1); // Delete the last comma
		return "";
	}

	/**
	 * Returns the JSON representation for the given key and value, if the key is part of the defined key in
	 * Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES..
	 * 
	 * @param key
	 *            the key of the tupel
	 * @param value
	 *            the value of the tupel
	 * @return the JSON representation of the tupel
	 */
	private String getJSONMetaValue(String key, Object value) {
		for (int i = 0; i < Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_DOUBLE.length; i++)
			if (key.equals(Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_DOUBLE[i]))
				return ("\"" + key + "\":" + ((double) value) + ",");

		for (int i = 0; i < Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_POINTS.length; i++)
			if (key.equals(Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_POINTS[i]))
				return ("\"" + key + "\":" + ((Point) value).toJSON() + ",");

		return "";
	}

	@Override
	public void setJSONdata(JsonObject elementData) {
		super.setJSONdata(elementData);

		JsonObject metaData = elementData.getJsonObject("metaData");
		for (int i = 0; i < Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_DOUBLE.length; i++)
			try {
				this.addMetaDataDouble(Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_DOUBLE[i], metaData
						.getJsonNumber(Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_DOUBLE[i]).doubleValue());
			} catch (NullPointerException e) { // If the GeometricElement does not have all MetaData values
			}

		JsonObject metaElem;
		for (int i = 0; i < Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_POINTS.length; i++) {
			try {
				metaElem = metaData.getJsonObject(Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_POINTS[i]);
				this.addMetaDataPoint(Constants.ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_POINTS[i],
						GeometricModelElement.readPointFromJSON(metaElem));
			} catch (NullPointerException e) { // If the GeometricElement does not have all MetaData values
			}
		}
	}

	@Override
	protected void calculateMove(double xDirection, double yDirection) {
		if (metaData.containsKey(Constants.TRANSLATE)) {
			Point p = (Point) metaData.get(Constants.TRANSLATE);
			p.setX(p.getX() + xDirection);
			p.setY(p.getY() + yDirection);
			metaData.put(Constants.TRANSLATE, p);
		} else
			metaData.put(Constants.TRANSLATE, new Point(xDirection, yDirection));
	}

	@Override
	protected void calculateRotation(double angle) {
		if (metaData.containsKey(Constants.ANGLE))
			angle += (double) metaData.get(Constants.ANGLE);
		metaData.put(Constants.ANGLE, (angle) % 360);
	}

	@Override
	protected void calculateScale(double factor) {
		if (metaData.containsKey(Constants.SCALE)) {
			double value = ((double) metaData.get(Constants.SCALE)) * factor;
			metaData.put(Constants.SCALE, value);
		} else
			metaData.put(Constants.SCALE, factor);
	}

	@Override
	protected void calculateMirroring(String axis) {
		double rotate = 0;
		if (metaData.containsKey(Constants.ANGLE))
			rotate = (double) metaData.get(Constants.ANGLE);

		if (axis.compareTo(Constants.X_AXIS) == 0)
			if (rotate > 90)
				rotate = 2 * (270 - rotate);
			else
				rotate = 2 * (90 - rotate);
		else {
			if (rotate > 180)
				rotate = 2 * (360 - rotate);
			else
				rotate = 2 * (180 - rotate);
		}
		calculateRotation(rotate % 360);
	}
}