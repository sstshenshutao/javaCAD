package util;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import model.GeometricModelElement;
import model.Point;

/**
 * some methods which are used several times in the system
 * 
 * @author David Koehler
 * @author Nora Wester
 */
public class Util {

	/**
	 * convert the intern color Code into a Color-Object
	 * 
	 * @param colorCode
	 *            intern code for the wanted color
	 * @return according Color-Object
	 */
	public static Color codeToColor(int colorCode) {
		Color color = Color.BLACK;
		if (colorCode != -1) {
			switch (colorCode) {
			case (Constants.COLOR_CODE_BLUE):
				color = Color.BLUE;
				break;
			case (Constants.COLOR_CODE_GREEN):
				color = Color.GREEN;
				break;
			case (Constants.COLOR_CODE_RED):
				color = Color.RED;
				break;
			case (Constants.COLOR_CODE_GREY):
				color = Color.GRAY;
				break;
			case (Constants.COLOR_CODE_ORANGE):
				color = Color.ORANGE;
				break;
			case (Constants.COLOR_CODE_MAGENTA):
				color = Color.MAGENTA;
				break;
			case (Constants.COLOR_CODE_YELLOW):
				color = Color.YELLOW;
				break;
			}
		}
		return color;
	}

	/**
	 * Returns the Class from the given classname
	 * 
	 * @param classname
	 *            the name of the searched class.
	 * @return
	 */
	public static Class<? extends GeometricModelElement> getGeometricElementsClass(String classname) {
		for (int i = 0; i < Constants.GEOMETRIC_MODEL_CLASS_NAMES.length; i++)
			if (classname.equals(Constants.GEOMETRIC_MODEL_CLASS_NAMES[i]))
				return Constants.GEOMETRIC_MODEL_CLASSES[i];
		return null;
	}

	/**
	 * calculates the raster scale position into the pixel scale position
	 * 
	 * @param rasterPosition
	 *            position in raster scale
	 * @return according position in pixel scale
	 */
	public static double getPixelPosition(double rasterPosition) {
		return (rasterPosition * Constants.factor);
	}

	/**
	 * calculate the centroid of the given points
	 * 
	 * @param points
	 *            given points
	 * @return centroid of the points
	 */
	public static Point calculateCentroid(Point[] points) {
		double x = 0;
		double y = 0;

		for (int i = 0; i < points.length; i++) {
			x = x + points[i].getX();
			y = y + points[i].getY();
		}

		x = x / points.length;
		y = y / points.length;

		return new Point(x, y);
	}

	/**
	 * Saves the JSON String into an .json file at the given filepath with the given name.json
	 * 
	 * @param jsonString
	 *            the string to save, must be in json format
	 * @param filepath
	 *            the path to save the file at
	 * @param fileName
	 *            the name of the new json file
	 * @throws IOException
	 */
	public static void jsonStringToJSONFile(String jsonString, String filepath, String fileName) throws IOException {
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject jobj = jsonReader.readObject();

		File file = new File(filepath + fileName + ".json");
		file.getParentFile().mkdirs();
		if (!file.exists())
			file.createNewFile();
		FileWriter writer = new FileWriter(file);
		writer.write(Util.formatJson(jobj.toString()));
		writer.close();
	}

	/**
	 * Formats a json file, so you can read it more easily
	 * 
	 * @param json
	 *            The json string
	 * @return The formatted json string.
	 */
	public static String formatJson(String json) {
		int depth = 0;
		String buffer = "";
		for (int input : json.chars().toArray()) {
			if (input == '}' || input == ']') {
				depth--;
				buffer += "\n";
				for (int i = 0; i < depth; i++)
					buffer += "  ";
				buffer += (char) input;
				continue;
			}
			if (input == '{' || input == '[') {
				buffer += (char) input;
				depth++;
				buffer += "\n";
				for (int i = 0; i < depth; i++)
					buffer += "  ";
				continue;
			}
			if (input == ',') {
				buffer += (char) input;
				buffer += "\n";
				for (int i = 0; i < depth; i++)
					buffer += "  ";
				continue;
			}
			buffer += (char) input;
		}
		return buffer;
	}

	/**
	 * Converts a double array into an integer array
	 * 
	 * @param arrD
	 *            the double array to convert
	 * @return an integer array with the rounded double values of the double array
	 */
	public static int[] toIntArray(double[] arrD) {
		int[] arrI = new int[arrD.length];
		for (int i = 0; i < arrD.length; i++)
			arrI[i] = (int) Math.round(arrD[i]);
		return arrI;
	}
}