package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Aufgabe_1.A.A;
import Aufgabe_1.B.B;
import Aufgabe_1.C.C;
import data.ListItem;
import model.GeometricModelElement;
import model.Point;
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
			for (; p != null && p.key != null && p.next != null && p.next.key != null; p = p.next)
	                jsonString += p.key.toJSON() + ", ";
	        if (p != null && p.key != null) {
				jsonString += p.key.toJSON() + "]}";
			}
			try {
				Util.jsonStringToJSONFile(jsonString, Constants.PATH_GRAPHIC_FILES, name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (code == Constants.ACTION_EVENT_READ_JSON) {
			final JFileChooser chooser = new JFileChooser("Verzeichnis wählen");
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
		
		if (code == Constants.ACTION_EVENT_NEW) {
			//对话框读入用户输入的参数
			String name = JOptionPane.showInputDialog(mainView, new String("you want to create a picture? please give the name of the picture"), "new File", JOptionPane.QUESTION_MESSAGE);
//			System.out.println(input);
			if (name.trim().length() != 0)
				{ListItem<GeometricModelElement> g= new ListItem<GeometricModelElement>(null);
				model.addPicture(g, name);
				ListItem<GeometricGraphicElement> gView = GeometricsFactory.makePicture(g);
				view.addPicture(gView, name);
				this.positionOfSelectedPicture = model.getLength() - 1;
				mainView.addPictureName(name);
				mainView.showPicture(gView);
				}
		}
		//GeoButton:
		if (code == Constants.ACTION_EVENT_GEOMETRIC) {
			// if the picture new picture?
			if (model.getLength()==1) {
					JOptionPane.showMessageDialog(mainView, "this is the example picture, create a new picture for you");
					setUserInput(Constants.ACTION_EVENT_NEW, null);}
		
			Class<? extends GeometricModelElement> geometricClass = null;
			if (information instanceof String) {
				geometricClass = Util.getGeometricElementsClass((String)information);
			}else return;
			try {
				GeometricModelElement geometricElem = geometricClass.newInstance();
				String vollClassname = geometricElem.getClass().toString();
				String shortClassname = vollClassname.substring(vollClassname.lastIndexOf(".")+1,vollClassname.lastIndexOf("Element"));
				//new Dialog to get the Information for geometricElem: colorCode and Points
				boolean signal = false;
				while (!signal)
				try {
					//对话框读入用户输入的参数
					String pointMessage="";
					for(int i=1;i<=geometricElem.getNumberOfPoints();i++) {
						pointMessage+="x"+i+",y"+i+",";
					}
					String input = JOptionPane.showInputDialog(mainView, new String("please give the parameter to draw the "+ shortClassname+
							" ,need "+geometricElem.getNumberOfPoints()+" points, input it in Form: "
									+ pointMessage+"colorcode(1-9)"), shortClassname, JOptionPane.QUESTION_MESSAGE);
//					System.out.println(input);
					String [] inputList = input.split(",");
					System.out.println(inputList.length);
					if ((inputList.length) != geometricElem.getNumberOfPoints()*2 + 1) {throw new Exception();}
					Point[] points = new Point[inputList.length/2];
					for (int i=0; i<inputList.length -1 ; i+=2) {
						points[i/2]=new Point(Double.parseDouble(inputList[i]), Double.parseDouble(inputList[i+1]));
					}
					//设置点的信息
					geometricElem.changeColorCode(Integer.parseInt(inputList[inputList.length-1]));
					geometricElem.setPoints(points);
					//存到model里
					ListItem<GeometricModelElement> modelElements = model.getPicture(positionOfSelectedPicture);
					modelElements.insert(geometricElem);
					model.changePicture(modelElements, positionOfSelectedPicture);
					//更新view
					ListItem<GeometricGraphicElement> graphicElements = GeometricsFactory.makePicture(modelElements);
					view.changePicture(graphicElements, positionOfSelectedPicture);
					//重画
					mainView.showPicture(graphicElements);
					signal=true;
				}catch(Exception e) {
//					e.printStackTrace();
					if (JOptionPane.showConfirmDialog(mainView, "wrong input, please try again.")==0) {
						signal=false;
					}else{
						signal=true;
					}
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//ChangeButton:
		
		if (code == Constants.ACTION_EVENT_CHANGE) {
			String buttonName = null;
			if (information instanceof String) {
				buttonName = (String)information;
			}else return;
			ListItem<GeometricModelElement> newG =null;
			try {
			switch (buttonName) {
				case "Move": System.out.println("move");
						String movePara = JOptionPane.showInputDialog(mainView, new String("please input a Direction Vector, like \"x,y\""), "move", JOptionPane.QUESTION_MESSAGE);
						if (movePara==null) return;
						String [] mp = movePara.split(","); 
						if (mp.length!=2) {throw new Exception();}
						newG = PictureManipulator
						.movePic(model.getPicture(positionOfSelectedPicture), Double.parseDouble(mp[0]), Double.parseDouble(mp[1]));
            					break;
				case "Scale":System.out.println("scale"); 
						String scalepara = JOptionPane.showInputDialog(mainView, new String("please input the scale factor"), "scale", JOptionPane.QUESTION_MESSAGE);
						if (scalepara==null) return;
						newG = PictureManipulator
						.scalePic(model.getPicture(positionOfSelectedPicture), Double.parseDouble(scalepara));// need change parameter
							break;
				case "Rotation": System.out.println("rotation");
						String roPara = JOptionPane.showInputDialog(mainView, new String("please input the angle"), "rotate", JOptionPane.QUESTION_MESSAGE);
						if (roPara==null) return;
						newG = PictureManipulator
						.rotatePic(model.getPicture(positionOfSelectedPicture), Double.parseDouble(roPara));// need change parameter
							break;
				case "Mirroring": System.out.println("mirroring");
						String miPara = JOptionPane.showInputDialog(mainView, new String("please input axis, x or y"), "mirror", JOptionPane.QUESTION_MESSAGE);
//						if (miPara.compareToIgnoreCase("x")!=0 || miPara.compareToIgnoreCase("y")!=0) {throw new Exception();}
						if (miPara==null) return;
						newG = PictureManipulator
						.mirrorPic(model.getPicture(positionOfSelectedPicture), miPara.trim());// need change parameter				
							break;
				case "SelMove": 
					//have already used b.selecttype
						GeometricModelElement[] changeList = getSelect();
						String selmovePara = JOptionPane.showInputDialog(mainView, new String("please input a Direction Vector, like \"x,y\""), "move", JOptionPane.QUESTION_MESSAGE);
						if (selmovePara==null) return;
						String [] smp = selmovePara.split(","); 
						if (smp.length!=2) {throw new Exception();}
						for(GeometricModelElement m: changeList) {
							m.move(Double.parseDouble(smp[0]), Double.parseDouble(smp[1]));
						}
						newG = model.getPicture(positionOfSelectedPicture);
						break;
				case "SelScale": 
					GeometricModelElement[] changeLists = getSelect();
					String selScalePara = JOptionPane.showInputDialog(mainView, new String("please input the scale factor"), "scale", JOptionPane.QUESTION_MESSAGE);
					if (selScalePara==null) return;
					for(GeometricModelElement m: changeLists) {
						m.scale(Double.parseDouble(selScalePara));
					}
					newG = model.getPicture(positionOfSelectedPicture);
					break;
				case "SelRotation": 
					GeometricModelElement[] changeListr = getSelect();
					String selRotationPara = JOptionPane.showInputDialog(mainView, new String("please input the angle"), "rotate", JOptionPane.QUESTION_MESSAGE);
					if (selRotationPara==null) return;
					for(GeometricModelElement m: changeListr) {
						m.rotate(Double.parseDouble(selRotationPara));
					}
					newG = model.getPicture(positionOfSelectedPicture);
					break;
				case "SelMirroring": 
					GeometricModelElement[] changeListm = getSelect();
					String selMirroringPara = JOptionPane.showInputDialog(mainView, new String("please input axis, x or y"), "mirror", JOptionPane.QUESTION_MESSAGE);
					if (selMirroringPara==null) return;
					for(GeometricModelElement m: changeListm) {
						m.mirror(selMirroringPara);
					}
					newG = model.getPicture(positionOfSelectedPicture);
					break;
				case "RemoveLast" :
					//have already used c.removelast
					C<GeometricModelElement> C = new C<>();
					if (model.getPicture(positionOfSelectedPicture).getSize()!=1)
					newG = C.removeLast(model.getPicture(positionOfSelectedPicture));
					break;
				case "RemoveFirst" :
					//have already used b.removehead
					B<GeometricModelElement> B = new B<>();
					if (model.getPicture(positionOfSelectedPicture).getSize()!=1)
					newG = B.removeHead(model.getPicture(positionOfSelectedPicture));
					break;
				case "Shiftleft"	:
					//have already used a.ringShiftLeft
					A<GeometricModelElement> A = new A<>();
					newG = A.ringShiftLeft(model.getPicture(positionOfSelectedPicture));
//					break;
				case "ShiftRight":
					//have already used b.ringShiftRight
					B<GeometricModelElement> Bs = new B<>();
					newG = Bs.ringShiftRight(model.getPicture(positionOfSelectedPicture));
					break;
				case "Parabola":
					//
					newG = PictureManipulator.parabola(model.getPicture(positionOfSelectedPicture),80);
					break;
				case "Attraction" :
					newG = PictureManipulator.attraction(model.getPicture(positionOfSelectedPicture),5);
					break;
				case "Sinxpic" :
					newG = PictureManipulator.sinxpic(model.getPicture(positionOfSelectedPicture),5);
					break;
				case "Cosxpic" :
					newG = PictureManipulator.cosxpic(model.getPicture(positionOfSelectedPicture),5);
					break;
				case "Sunrise":
					newG = PictureManipulator.sunrise(model.getPicture(positionOfSelectedPicture),5,5);
					break;
					
			}}catch(Exception e) {
				e.getStackTrace();
//				JOptionPane.showMessageDialog(mainView,"opration failed, please do it again");
//				return;
			}
			if(newG==null) return;
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
	
	private GeometricModelElement[] getSelect() {
		B<GeometricModelElement> B = new B<>();
		String selCode = JOptionPane.showInputDialog(mainView, new String("please input the select class, "
				+ "for example: circle will change all the circles."), "mirror", JOptionPane.QUESTION_MESSAGE);
		String clazz = SelectAndChange.parsePradicate(selCode);
		Class<? extends GeometricModelElement> geometricClass = null;
		if (clazz!=null) {
			geometricClass = Util.getGeometricElementsClass(clazz);
		}else return null;
		ListItem<GeometricModelElement> glist = model.getPicture(positionOfSelectedPicture);
		return B.selectType(glist, geometricClass);
	}
}