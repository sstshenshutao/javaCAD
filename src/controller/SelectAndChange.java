package controller;

import javax.swing.JOptionPane;

import data.ListItem;
import model.GeometricModelElement;
import util.Constants;
import util.Util;

public class SelectAndChange {

	/**
	 * helpper Method for pradicate
	 * @param clazz
	 * @return
	 */
	public static String parsePradicate(String clazz) {
		switch(clazz) {
		case "circle": return Constants.GEOMETRIC_ROUND_ELLIPSE_CIRCLE_ELEMENT;
		case "ellipse": return Constants.GEOMETRIC_ROUND_ELLIPSE_ELEMENT;
		case "triangle": return Constants.GEOMETRIC_ANGLED_TRIANGLE_ELEMENT;
		case "equilateralTriangle": return Constants.GEOMETRIC_ANGLED_TRIANGLE_EQUILATERALTRIANGLE_ELEMENT;
		case "hexagon": return Constants.GEOMETRIC_ANGLED_HEXAGON_ELEMENT;
		case "regularHexagon": return Constants.GEOMETRIC_ANGLED_HEXAGON_REGULARHEXAGON_ELEMENT;
		case "trapezoid": return Constants.GEOMETRIC_ANGLED_TRAPEZOID_ELEMENT;
		case "isoscelesTrapezoid": return Constants.GEOMETRIC_ANGLED_TRAPEZOID_ISOSCELESTRAPEZOID_ELEMENT;
		case "rectangle": return Constants.GEOMETRIC_ANGLED_RECTANGLE_ELEMENT;
		case "square": return Constants.GEOMETRIC_ANGLED_RECTANGLE_SQUARE_ELEMENT;		
		}
		return null;
	}

}
