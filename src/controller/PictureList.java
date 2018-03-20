package controller;

import java.util.List;

/**
 * Represents a list of pictures
 * 
 * @author Nora Wester
 *
 * @param <T>
 *            generic type of the pictures
 */
public abstract class PictureList<T> {

	// list of pictures
	protected List<T>	list;
	// array of names according to the pictures in the list
	protected String[]	names;

	/**
	 * Constructor
	 * 
	 * @param list
	 *            list of graphics
	 * @param names
	 *            array of names according to the graphics in the list
	 */
	public PictureList(List<T> list, String[] names) {
		// control, if there are as many pictures in the list as names in the array
		if (list.size() == names.length) {
			this.list = list;
			this.names = names;
		}
	}

	/**
	 * Adds the picture to the end of the list
	 * 
	 * @param lst
	 *            the picture to add
	 */
	public void addPicture(T lst, String name) {
		if (lst != null) {
			list.add(lst);
			String[] names = new String[this.names.length + 1];
			for (int i = 0; i + 1 < names.length; i++)
				names[i] = this.names[i];
			names[names.length - 1] = name;
			this.names = names;
		}
	}

	/**
	 * get the picture at a specific position
	 * 
	 * @param position
	 *            position of the picture
	 * @return wanted picture
	 */
	public T getPicture(int position) {
		return list.get(position);
	}

	/**
	 * set the given picture at the given position
	 * 
	 * @param element
	 *            the new picture
	 * @param position
	 *            position of the new picture
	 */
	public void changePicture(T element, int position) {
		list.set(position, element);
	}

	/**
	 * return the name array
	 * 
	 * @return array containing the names of the pictures
	 */
	public String[] getNames() {
		return names;
	}

	/**
	 * get the size of the list
	 * 
	 * @return number of available pictures
	 */
	public int getLength() {
		return list.size();
	}
}