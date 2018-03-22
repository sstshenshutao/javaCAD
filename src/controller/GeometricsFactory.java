package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import data.ListItem;
import model.GeometricModelElement;
import model.Point;
import model.round.RoundGeometricElement;
import pictures.ExamplePicture;
import pictures.PictureCalculator;
import util.Constants;
import util.Util;
import view.GeometricGraphicElement;

/**
 * Methods for creating geometric elements
 * 
 * @author Nora Wester, David Koehler
 */
public class GeometricsFactory {

	/**
	 * creates from a graphic of the model side a corresponding graphic for the view side
	 * 
	 * @param element
	 *            graphic of the model side
	 * @return corresponding graphic for the view side
	 */
	public static GeometricGraphicElement makeGeometricGraphicElement(GeometricModelElement element) {
		//gModel转gGraphic，如果是圆类型的g设置了metadata
		
		// the points have to be split into x and y positions
		Point[] points = element.getPoints();
		double[] xs = new double[points.length];
		double[] ys = new double[points.length];

		int counter = 0;
		// transfer from raster scale to pixel scale
		for (Point point : points) {
			xs[counter] = Util.getPixelPosition(point.getX()); //乘了个系数30而已
			ys[counter] = Util.getPixelPosition(point.getY());
			counter++;
		}

		int type = Constants.GRAPHIC_TYPE_ANGULAR;
		HashMap<String, Object> metaView = new HashMap<String, Object>();

		// get the type out of the class
		if (element instanceof RoundGeometricElement) {
			type = Constants.GRAPHIC_TYPE_ROUND;
			HashMap<String, Object> metaModel = ((RoundGeometricElement) element).getMetaData();//第一次读入metadata为空
			// parse meta data
			if (metaModel.containsKey(Constants.ANGLE))
				metaView.put(Constants.ANGLE, metaModel.get(Constants.ANGLE));

			if (metaModel.containsKey(Constants.SCALE))
				metaView.put(Constants.SCALE, metaModel.get(Constants.SCALE));

			if (metaModel.containsKey(Constants.TRANSLATE)) {
				Point tran = (Point) metaModel.get(Constants.TRANSLATE);
				metaView.put(Constants.TRANSLATE,
						new Point(Util.getPixelPosition(tran.getX()), Util.getPixelPosition(tran.getY())));
			}
		}
		GeometricGraphicElement g = new GeometricGraphicElement(xs, ys, element.getColorCode(), type);

		if (type == Constants.GRAPHIC_TYPE_ROUND)
			// set meta data
			g.setMetaData(metaView);

		return g;
	}

	/**
	 * Transform a list of pictures of the model side to a list of graphics of the view side
	 * 
	 * @param list
	 *            list of pictures of the model side
	 * @return corresponding list of pictures for the view side
	 */
	public static PictureGraphicList makePictureGraphicList(PictureModelList list) {
		List<ListItem<GeometricGraphicElement>> viewList = new ArrayList<ListItem<GeometricGraphicElement>>();

		for (int i = 0; i < list.getLength(); i++) {
			ListItem<GeometricModelElement> model = list.getPicture(i);
			ListItem<GeometricGraphicElement> view = new ListItem<GeometricGraphicElement>(null);

			for (int j = 1; j <= model.getSize(); j++) {
				GeometricGraphicElement g = GeometricsFactory.makeGeometricGraphicElement(model.get(j));

				view.insert(g);
			}
			viewList.add(view);
		}
		return new PictureGraphicList(viewList, list.getNames());
	}

	/**
	 * parse a picture of the model side to a picture of the view side
	 * 
	 * @param model
	 *            picture of the model side
	 * @return corresponding picture of the view side
	 */
	public static ListItem<GeometricGraphicElement> makePicture(ListItem<GeometricModelElement> model) {
		ListItem<GeometricGraphicElement> view = new ListItem<GeometricGraphicElement>(null);
		//Gmodel转Ggraphic 返回ListItem《Ggraphic》，并且如果是圆类型设置了metadata
		for (int j = 1; j <= model.getSize(); j++) {
			GeometricGraphicElement g = GeometricsFactory.makeGeometricGraphicElement(model.get(j));
			view.insert(g);
		}
		return view;
	}

	/**
	 * create a list of pictures (model side)
	 * 
	 * @return the list of pictures
	 */
	public static PictureModelList makePictureModelList() {

		// for every graphic, add a new GraphicsCalculator, which calculates the graphic
		List<PictureCalculator> factory = new ArrayList<PictureCalculator>();
		factory.add(new ExamplePicture());

		// list of the graphics
		List<ListItem<GeometricModelElement>> elements = new ArrayList<ListItem<GeometricModelElement>>();
		String[] names = new String[factory.size()];
		int counter = 0;
		// go through the list of graphicsCalclators
		// for every calculator
		for (PictureCalculator f : factory) {
			// calculate a graphic
			f.calculate();
			// add this graphic to the graphic list
			elements.add(f.getPictureModel());
			// get the name of the graphic
			String name = f.getName();
			// if the graphic has no name, calculate a random name out of numbers
			if (name.compareTo("") == 0)
				name = Double.toString(Math.random() * 100);
			names[counter] = name;
			counter++;
		}
		return new PictureModelList(elements, names);
	}

	/**
	 * Adds a Picture as a JSON Object into the Picture Model List.
	 * 
	 * @param jObject
	 *            the picture to add into the list
	 */
	public static void addJSONObjectIntoPictureModelList(JsonObject jObject)
			throws InstantiationException, IllegalAccessException {
		ListItem<GeometricModelElement> dummy = new ListItem<GeometricModelElement>(null);
		JsonArray data = jObject.getJsonArray("data");//read "data"
		JsonObject jGeometricElement;
		ListItem<GeometricModelElement> p = dummy;
		for (int i = 0; i < data.size(); i++) {
			jGeometricElement = data.getJsonObject(i);//read all jGeoEle
			Class<? extends GeometricModelElement> geometricClass = Util//我们自定义的图形，只要继承自主类都可以
					.getGeometricElementsClass(jGeometricElement.getString("classname"));//返回类名
			if (GeometricModelElement.constructFromJSONObject(jGeometricElement, geometricClass) == null)
				System.out.println(
						"Some geometric Elements could not be read correctly. Please check the spelling of the classnames:\n"
								+ jGeometricElement.getString("classname"));
			else {
				p.next = new ListItem<GeometricModelElement>(
						GeometricModelElement.constructFromJSONObject(jGeometricElement, geometricClass));
				p = p.next;
			}
		}//把读入的jObj根据读入的类名，造型成相应的类对象，然后加入ListItem容器P中
		MainController controller = MainController.getInstance();
		controller.setLoadedElement(dummy.next, jObject.getString("name"));
		//把读出的listItem返回给controller, 同时传回参数文件名name
	}
}