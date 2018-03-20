package controller;

import java.util.List;

import data.ListItem;
import model.GeometricModelElement;

/**
 * Represents a list of pictures (at the view side)
 * 
 * @author Nora Wester
 */
public class PictureModelList extends PictureList<ListItem<GeometricModelElement>> {

	public PictureModelList(List<ListItem<GeometricModelElement>> list, String[] names) {
		super(list, names);
	}
}