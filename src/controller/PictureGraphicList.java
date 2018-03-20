package controller;

import java.util.List;

import data.ListItem;
import view.GeometricGraphicElement;

/**
 * Represents a list of pictures (at the view side)
 * 
 * @author Nora Wester
 */
public class PictureGraphicList extends PictureList<ListItem<GeometricGraphicElement>> {

	public PictureGraphicList(List<ListItem<GeometricGraphicElement>> list, String[] names) {
		super(list, names);
	}
}