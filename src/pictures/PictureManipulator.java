package pictures;

import Aufgabe_1.V.V;
import data.ListItem;
import model.GeometricModelElement;

/**
 * Methods for manipulating an existing picture by creating a new one
 * 
 * @author Nora Wester
 */
public class PictureManipulator {

	/**
	 * manipulates the given picture in the manner, that every element is duplicated and moved
	 * 
	 * @param g
	 *            original picture
	 * @return the new changed picture
	 */
	public static ListItem<GeometricModelElement> doSomething(ListItem<GeometricModelElement> g) {
		ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);

		for (int j = 1; j <= g.getSize(); j++) {
			GeometricModelElement e = g.get(j);
			// draw the original element
			newGraphic.insert(e);
			// clone it
			GeometricModelElement cloneE = e.cloneElement();
			// and move it
			cloneE.move(1, 1);
			newGraphic.insert(cloneE);
		}
		return newGraphic;
	}
	
	/**
	 * rotate the whole picture
	 * @param g
	 * 			original picture
	 * @param angle
	 * 			rotate angle
	 * @return
	 */
	public static ListItem<GeometricModelElement> rotatePic(ListItem<GeometricModelElement> g, double angle) {
		ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);

		for (int j = 1; j <= g.getSize(); j++) {
			GeometricModelElement e = g.get(j);
			// rotate
			e.rotate(angle);
			newGraphic.insert(e);
		}
		return newGraphic;
	}
	
	/**
	 * move the whole picture
	 * @param g
	 * 			original picture
	 * @param xDirection
	 * 			move xDirection distance
	 * @param yDirection
	 * 			move yDirection distance
	 * @return
	 */
	public static ListItem<GeometricModelElement> movePic(ListItem<GeometricModelElement> g, double xDirection, double yDirection) {
		ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);

		for (int j = 1; j <= g.getSize(); j++) {
			GeometricModelElement e = g.get(j);
			// move
			e.move(xDirection, yDirection);
			newGraphic.insert(e);
		}
		return newGraphic;
	}
	
	/**
	 * mirror the whole picture
	 * @param g
	 * 			original picture
	 * @param axis
	 * 			mirror axis
	 * @return
	 */
	public static ListItem<GeometricModelElement> mirrorPic(ListItem<GeometricModelElement> g, String axis) {
		ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);

		for (int j = 1; j <= g.getSize(); j++) {
			GeometricModelElement e = g.get(j);
			// mirror
			e.mirror(axis);
			newGraphic.insert(e);
		}
		return newGraphic;
	}
	
	/**
	 * scale the whole picture
	 * @param g
	 * 			original picture
	 * @param factor
	 * 			scaled factor
	 * @return
	 */
	public static ListItem<GeometricModelElement> scalePic(ListItem<GeometricModelElement> g, double factor) {
		ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);

		for (int j = 1; j <= g.getSize(); j++) {
			GeometricModelElement e = g.get(j);
			// scale
			e.scale(factor);
			newGraphic.insert(e);
		}
		return newGraphic;
	}
	
	public static ListItem<GeometricModelElement> parabola (ListItem<GeometricModelElement> g1, double speed) {
		double g = -9.8/40;
		double h =0;
		double v= speed/40;
		V<GeometricModelElement> V= new V<>();
		ListItem<GeometricModelElement> ghead= g1;
		ListItem<GeometricModelElement> finalList= new ListItem<GeometricModelElement>(null);
		GeometricModelElement accc= ghead.key;
		finalList.insert(accc);
		int c=1;
		for(int i=0;i<16;i++) {
			h+=g;
			GeometricModelElement cloneE =accc.cloneElement();
			cloneE.move(v, h);
			cloneE.rotate(-10);
			if(cloneE.getColorCode()<=1) c=1;else if (cloneE.getColorCode()>=9) c=-1;
			cloneE.changeColorCode(cloneE.getColorCode()+c);
			finalList.insert(cloneE);
			accc=cloneE;
		}
		
		return finalList;
	}
	
	
}