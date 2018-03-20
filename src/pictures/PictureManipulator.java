package pictures;

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
}