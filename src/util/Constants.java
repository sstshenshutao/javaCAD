package util;

import model.GeometricModelElement;
import model.angled.EquilateralTriangleElement;
import model.angled.HexagonElement;
import model.angled.IsoscelesTrapezoidElement;
import model.angled.RectangleElement;
import model.angled.RegularHexagonElement;
import model.angled.SquareElement;
import model.angled.TrapezoidElement;
import model.angled.TriangleElement;
import model.round.CircleElement;
import model.round.EllipseElement;

/**
 * storing the constant variables which are used in the whole system
 * 
 * @author Nora Wester
 */
public class Constants {

	// available intern color codes
	public static final int									COLOR_CODE_BLACK								= 1;
	public static final int									COLOR_CODE_RED									= 2;
	public static final int									COLOR_CODE_BLUE									= 3;
	public static final int									COLOR_CODE_GREEN								= 4;
	public static final int									COLOR_CODE_YELLOW								= 5;
	public static final int									COLOR_CODE_MAGENTA								= 6;
	public static final int									COLOR_CODE_GREY									= 7;
	public static final int									COLOR_CODE_ORANGE								= 8;

	// file path of the data json file
	public static final String								PATH_GRAPHIC_FILES								= "graphics/";

	// array of all color codes
	public static final int[]								COLOR_CODES										= new int[] {
			COLOR_CODE_BLACK, COLOR_CODE_RED, COLOR_CODE_BLUE, COLOR_CODE_GREEN, COLOR_CODE_YELLOW, COLOR_CODE_MAGENTA,
			COLOR_CODE_GREY, COLOR_CODE_ORANGE };

	// type of graphic element
	public static final int									GRAPHIC_TYPE_ROUND								= 0;
	public static final int									GRAPHIC_TYPE_ANGULAR							= 1;

	// name of the model classes
	public static final String								GEOMETRIC_ROUND_ELLIPSE_ELEMENT					= "geometric.round.EllipseElement";
	public static final String								GEOMETRIC_ROUND_ELLIPSE_CIRCLE_ELEMENT			= "geometric.round.ellipse.CircleElement";
	public static final String								GEOMETRIC_ANGLED_TRIANGLE_ELEMENT									= "geometric.angled.TriangleElement";
	public static final String								GEOMETRIC_ANGLED_TRIANGLE_EQUILATERALTRIANGLE_ELEMENT				= "geometric.angled.triangle.EquilateralTriangleElement";
	public static final String								GEOMETRIC_ANGLED_HEXAGON_ELEMENT										= "geometric.angled.HexagonElement";
	public static final String								GEOMETRIC_ANGLED_HEXAGON_REGULARHEXAGON_ELEMENT						= "geometric.angled.hexagon.RegularHexagonElement";
	public static final String								GEOMETRIC_ANGLED_TRAPEZOID_ELEMENT									= "geometric.angled.TrapezoidElement";
	public static final String								GEOMETRIC_ANGLED_TRAPEZOID_ISOSCELESTRAPEZOID_ELEMENT				= "geometric.angled.trapezoid.IsoscelesTrapezoidElement";
	public static final String								GEOMETRIC_ANGLED_RECTANGLE_ELEMENT									= "geometric.angled.RectangleElement";
	public static final String								GEOMETRIC_ANGLED_RECTANGLE_SQUARE_ELEMENT							= "geometric.angled.rectangle.SquareElement";
	
	// All names of the model classes. The order must be equal to the order of GEOMETRIC_MODEL_CLASSES
	public static final String[]							GEOMETRIC_MODEL_CLASS_NAMES						= new String[] {
			GEOMETRIC_ROUND_ELLIPSE_ELEMENT, GEOMETRIC_ROUND_ELLIPSE_CIRCLE_ELEMENT, 
			GEOMETRIC_ANGLED_TRIANGLE_ELEMENT, GEOMETRIC_ANGLED_TRIANGLE_EQUILATERALTRIANGLE_ELEMENT	,
			GEOMETRIC_ANGLED_HEXAGON_ELEMENT	, GEOMETRIC_ANGLED_HEXAGON_REGULARHEXAGON_ELEMENT,
			GEOMETRIC_ANGLED_TRAPEZOID_ELEMENT, GEOMETRIC_ANGLED_TRAPEZOID_ISOSCELESTRAPEZOID_ELEMENT,
			GEOMETRIC_ANGLED_RECTANGLE_ELEMENT,GEOMETRIC_ANGLED_RECTANGLE_SQUARE_ELEMENT};

	// All classes of the models. The order must be equal to the order of GEOMETRIC_MODEL_CLASS_NAMES
	@SuppressWarnings("unchecked")
	public static Class<? extends GeometricModelElement>[]	GEOMETRIC_MODEL_CLASSES							= (Class<? extends GeometricModelElement>[]) new Class<?>[] {
			EllipseElement.class, CircleElement.class, 
			TriangleElement.class, EquilateralTriangleElement.class, 
			HexagonElement.class, RegularHexagonElement.class,
			TrapezoidElement.class, IsoscelesTrapezoidElement.class, 
			RectangleElement.class, SquareElement.class};

	// type of user interaction
	public static final int									ACTION_EVENT_DRAW								= 0;
	public static final int									ACTION_CHOOSE_GRAPHIC							= 1;
	public static final int									ACTION_EVENT_SAVE								= 2;
	public static final int									ACTION_EVENT_READ_JSON							= 3;
	public static final int									ACTION_EVENT_DO_SOMETHING						= 4;
	public static final int									ACTION_EVENT_NEW									= 5;
	
	// type of user interaction: GeometricBotton
	public static final int		  							ACTION_EVENT_GEOMETRIC							= 10;
	
	// type of user interaction: ChangeBotton
	public static final int		  							ACTION_EVENT_CHANGE								= 20;

	
	
	// padding to get a little space between the drawing and the the panel border
	public static final int									paddingWidth									= 30;
	public static final int									paddingHeight									= 30;

	// factor between raster scale and pixel scale
	public static final double								factor											= 30;

	// delay between every element by drawing
	public static final int									INTERVAL_TIME									= 1000;

	// axis for mirroring
	public static final String								X_AXIS											= "x";
	public static final String								Y_AXIS											= "y";

	// types of meta data for rounded elements
	public static final String								SCALE											= "scale";
	public static final String								TRANSLATE										= "translate";
	public static final String								ANGLE											= "angle";
	public static final String[]							ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_DOUBLE	= new String[] {
			Constants.SCALE, Constants.ANGLE };
	public static final String[]							ROUNDED_GEOMETRIC_ELEMENT_METADATA_NAMES_POINTS	= new String[] {
			Constants.TRANSLATE };
}