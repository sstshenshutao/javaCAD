package pictures;

import data.ListItem;
import model.GeometricModelElement;

/**
 * basic class for creating/calculating a picture
 * 
 * @author Nora Wester
 */
public abstract class PictureCalculator {

	// name of the picture
	private String name = "";

	/**
	 * get the calculated picture (model side)
	 * 
	 * @return the calculated picture
	 */
	public abstract ListItem<GeometricModelElement> getPictureModel();

	/**
	 * calculate the picture
	 */
	public abstract void calculate();

	/**
	 * change the name of the picture
	 * 
	 * @param name
	 *            new name of the picture
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get the name of the picture
	 * 
	 * @return the pictures's name
	 */
	public String getName() {
		return name;
	}
}