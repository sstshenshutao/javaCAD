package view;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import data.ListItem;
import util.Constants;

/**
 * Representing and managing the canvas of the user interface
 * 
 * @author Nora Wester
 */
public class MainCanvas extends JPanel {

	/**
	 * 
	 */
	private static final long					serialVersionUID	= 1L;

	private ListItem<GeometricGraphicElement>	list;
	// transformation to flip the coordinates
	private AffineTransform						flipCoordinate;

	/**
	 * Constructor of the drawing area
	 * 
	 * @param frameWidth
	 *            width of the drawing area
	 * @param frameHeight
	 *            height of the drawing area
	 */
	public MainCanvas(int frameWidth, int frameHeight) {

		this.list = new ListItem<GeometricGraphicElement>(null);

		// the canvas must be smaller, because of the ScrollPane
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		this.setMinimumSize(new Dimension(frameWidth, frameHeight));

		// to transform the normal coordinate system of Graphics to the Cartesian coordinate system
		// there should be a little space between the drawing and the the panel border
		flipCoordinate = new AffineTransform(1, 0, 0, -1, Constants.paddingWidth,
				frameHeight - Constants.paddingHeight);
	}

	/**
	 * Constructor of the drawing area
	 * 
	 * @param list
	 *            with the graphic elements
	 * @param frameWidth
	 *            width of the drawing area
	 * @param frameHeight
	 *            height of the drawing area
	 */
	public MainCanvas(ListItem<GeometricGraphicElement> list, int frameWidth, int frameHeight) {
		this.list = list;

		// the canvas must be smaller, because of the ScrollPane
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		this.setMinimumSize(new Dimension(frameWidth, frameHeight));

		// to transform the normal coordinate system of Graphics to the Cartesian coordinate system
		// there should be a little space between the drawing and the the panel border
		flipCoordinate = new AffineTransform(1, 0, 0, -1, Constants.paddingWidth,
				frameHeight - Constants.paddingHeight);
	}

	/**
	 * draw the picture
	 * 
	 * @param graphics
	 *            which have to be paint at the component
	 */
	@Override
	protected void paintChildren(Graphics graphics) {
		super.paintChildren(graphics);
		Graphics2D g = (Graphics2D) graphics;
		// spin the coordinate system
		g.setTransform(flipCoordinate);
		// antialiasing
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// draw all elements of the list
		for (int i = 1; i <= list.getSize(); i++)
			list.get(i).print(g);

		revalidate();
	}

	/**
	 * set the picture, which should be drawn
	 * 
	 * @param list
	 *            of graphic elements
	 */
	public void setPicture(ListItem<GeometricGraphicElement> list) {
		this.list = list;
	}
}