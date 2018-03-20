package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JFileChooser;

import data.ListItem;
import model.GeometricModelElement;
import pictures.PictureManipulator;
import util.Constants;
import util.Util;
import view.GeometricGraphicElement;
import view.MainView;

/**
 * main controller of the application handle and delegate the work flow
 * 
 * @author Nora Wester, David Koehler
 */
public class MainController {
	// model list
	private PictureModelList		model;
	// view list
	private PictureGraphicList		view;
	// frame
	private MainView				mainView;
	// position of selected picture
	private int						positionOfSelectedPicture;

	private static MainController	instance;

	/**
	 * singleton-pattern there should be only one instance of the MainController
	 * 
	 * @return the single MainController instance
	 */
	public static MainController getInstance() {
		if (MainController.instance == null) {
			MainController.instance = new MainController();
		}
		return MainController.instance;
	}

	/**
	 * Initiate the needed objects
	 */
	public void init() {
		// init the picture list of the model
		model = GeometricsFactory.makePictureModelList();

		// makes a list representing the model list
		view = GeometricsFactory.makePictureGraphicList(model);

		// select a picture randomly by its position in the list
		positionOfSelectedPicture = (int) (Math.random() * (view.getLength() - 1));

		// init the frame
		mainView = new MainView(view.getNames(), positionOfSelectedPicture);
	}

	/**
	 * Handles the user input
	 * 
	 * @param code
	 *            indicates which user input
	 * @param information
	 *            extra information of the user input e.g. the position of the selected value
	 */
	public void setUserInput(int code, Object information) {
		if (code == Constants.ACTION_CHOOSE_GRAPHIC)
			positionOfSelectedPicture = (int) information;

		if (code == Constants.ACTION_EVENT_DRAW) {
			ListItem<GeometricGraphicElement> element = view.getPicture(positionOfSelectedPicture);
			mainView.showPicture(element);
		}
		if (code == Constants.ACTION_EVENT_SAVE) {
			// Dies nicht ganz sauber, da selectedGraphic nicht auf Modell bezogen ist. Die Reihenfolge muss aber gleich
			// sein, daher ist dies zul�ssig.
			ListItem<GeometricModelElement> selecetedModell = this.model.getPicture(positionOfSelectedPicture);
			String name = this.model.getNames()[positionOfSelectedPicture];
			String jsonString = "{\"name\": \"" + name + "\", ";
			jsonString += "\"data\": [";
			ListItem<GeometricModelElement> p = selecetedModell;
			if (p != null && p.next == null)
				jsonString += p.next.key.toJSON() + "]}";
			else {
				for (; p != null && p.key != null && p.next != null && p.next.key != null; p = p.next)
					jsonString += p.key.toJSON() + ", ";
				jsonString += p.key.toJSON() + "]}";
			}
			try {
				Util.jsonStringToJSONFile(jsonString, Constants.PATH_GRAPHIC_FILES, name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (code == Constants.ACTION_EVENT_READ_JSON) {
			final JFileChooser chooser = new JFileChooser("Verzeichnis w�hlen");
			File file = new File(Constants.PATH_GRAPHIC_FILES);
			chooser.setCurrentDirectory(file);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				if (file.exists()) {
					try {
						JsonReader reader = Json.createReader(new FileReader(file));
						JsonObject jObject = reader.readObject();
						GeometricsFactory.addJSONObjectIntoPictureModelList(jObject);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (code == Constants.ACTION_EVENT_DO_SOMETHING) {
			ListItem<GeometricModelElement> newG = PictureManipulator
					.doSomething(model.getPicture(positionOfSelectedPicture));
			model.changePicture(newG, positionOfSelectedPicture);
			ListItem<GeometricGraphicElement> gView = GeometricsFactory.makePicture(newG);
			view.changePicture(gView, positionOfSelectedPicture);
			mainView.showPicture(gView);
		}
	}

	/**
	 * main method as starting point
	 * 
	 * @param args
	 *            standard variable
	 * 
	 */
	static public void main(String[] args) {
		MainController controller = MainController.getInstance();
		controller.init();
	}

	/**
	 * @return the model
	 */
	public PictureModelList getModel() {
		return model;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(PictureGraphicList view) {
		this.view = view;
	}

	/**
	 * adds the loaded picture into the model and the view
	 * 
	 * @param g
	 *            new picture (model side)
	 * @param n
	 *            name of the new picture
	 */
	public void setLoadedElement(ListItem<GeometricModelElement> g, String n) {
		model.addPicture(g, n);
		ListItem<GeometricGraphicElement> gView = GeometricsFactory.makePicture(g);
		view.addPicture(gView, n);
		this.positionOfSelectedPicture = model.getLength() - 1;
		mainView.addPictureName(n);
		mainView.showPicture(gView);
	}
}