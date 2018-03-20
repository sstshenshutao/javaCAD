package view;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import controller.MainController;
import data.ListItem;
import util.Constants;

/**
 * Representing the user interface of the application
 * 
 * @author Nora Wester
 */
public class MainView extends JFrame {

	private static final long	serialVersionUID	= 1L;
	// height of the frame
	private int					frameHeight			= 0;
	// width of the frame
	private int					frameWidth			= 0;
	// drawing area
	MainCanvas					canvas;
	// names of the available pictures
	JComboBox<String>			pictureNames;
	// timer, for drawing a picture in an interval
	Timer						timer;

	/**
	 * Constructor of the frame
	 * 
	 * @param pictureNames
	 *            array containing the names of the available pictures
	 * @param selectedPicturePosition
	 *            position of the selected picture in the array
	 */
	public MainView(String[] pictureNames, int selectedPicturePosition) {
		initPanel(pictureNames);

		// the canvas must be smaller, because of the ScrollPane and the area above and
		// below
		canvas = new MainCanvas(frameWidth - 80, frameHeight - 120);

		// create ScrollPane and add it to the frame panel
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.add(canvas);

		scrollPane.setPreferredSize(new Dimension(frameWidth - 50, frameHeight - 90));
		scrollPane.setMinimumSize(new Dimension(frameWidth - 50, frameHeight - 90));

		add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * init the panel of the frame
	 * 
	 * @param pictureNames
	 *            array containing the names of the available pictures
	 */
	private void initPanel(String[] pictureNames) {
		// make it possible to close the window and at once to exit the process
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// get the dimension of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth() / 2;
		int height = (int) screenSize.getHeight() / 2;

		// set LayoutManager to the panel
		setLayout(new BorderLayout());

		// set the size of the frame
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));

		// save the size
		frameWidth = width;
		frameHeight = height;

		// create a new panel and set the LayoutManager
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		// ----------------------------------- DROP-DOWN ----------------------------------- \\
		// create a drop down box for choosing the graphic
		this.pictureNames = new JComboBox<String>(pictureNames);
		// add an ActionListener to be informed of an user interaction
		this.pictureNames.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// get the user interaction and give it to the controller
				@SuppressWarnings("rawtypes")
				JComboBox cb = (JComboBox) arg0.getSource();
				MainController.getInstance().setUserInput(Constants.ACTION_CHOOSE_GRAPHIC, cb.getSelectedIndex());
			}
		});

		// add the drop down box to the center of the new panel
		top.add(this.pictureNames, BorderLayout.CENTER);
		JPanel spaceR = new JPanel();
		spaceR.setMinimumSize(new Dimension(width / 2 - 50, 10));
		JPanel spaceL = new JPanel();
		spaceL.setMinimumSize(new Dimension(width / 2 - 50, 10));
		top.add(spaceR, BorderLayout.EAST);
		top.add(spaceL, BorderLayout.WEST);

		// add the new panel to the top of the frame panel
		add(top, BorderLayout.NORTH);

		// ----------------------------------- Button: ZEICHNEN ----------------------------------- \\
		// create a second panel
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BorderLayout());
		// crate a button with the label "Zeichnen"
		JButton print = new JButton("Zeichnen");
		// add an ActionListener to be informed of an user interaction
		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// inform the controller of the user interaction
				MainController.getInstance().setUserInput(Constants.ACTION_EVENT_DRAW, null);
			}
		});
		// add the button to the left side of the second panel
		buttonPane.add(print, BorderLayout.EAST);

		// ----------------------------------- Button: SPEICHERN ----------------------------------- \\
		// crate a button with the label "Speichern"
		JButton save = new JButton("Speichern");
		// add an ActionListener to be informed of an user interaction
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// inform the controller of the user interaction
				MainController.getInstance().setUserInput(Constants.ACTION_EVENT_SAVE, null);
			}
		});
		// add the button to the left side of the second panel
		buttonPane.add(save, BorderLayout.WEST);

		// ----------------------------------- Button: ÖFFNEN ----------------------------------- \\
		// crate a button with the label "�ffnen"
		JButton open = new JButton("Öffnen");
		// add an ActionListener to be informed of an user interaction
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// inform the controller of the user interaction
				MainController.getInstance().setUserInput(Constants.ACTION_EVENT_READ_JSON, null);
			}
		});
		// add the button to the left side of the second panel
		buttonPane.add(open, BorderLayout.CENTER);

		// add the second panel to the bottom of the frame panel
		add(buttonPane, BorderLayout.SOUTH);

		// ----------------------------------- Button: DO ----------------------------------- \\
		// create a new third panel and set the LayoutManager
		JPanel controllPanel = new JPanel();
		controllPanel.setLayout(new GridLayout(1, 0));

		JButton doSomething = new JButton("DO");
		doSomething.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// inform the controller of the user interaction
				MainController.getInstance().setUserInput(Constants.ACTION_EVENT_DO_SOMETHING, null);
			}
		});
		// add the button to the third panel
		controllPanel.add(doSomething);

		// add the third panel to the left of the frame panel
		add(controllPanel, BorderLayout.EAST);

		pack();
		setVisible(true);
	}

	/**
	 * Constructor of the frame
	 * 
	 * @param pictureNames
	 *            array containing the names of the available pictures
	 * @param list
	 *            picture which should be drawn
	 */
	public MainView(ListItem<GeometricGraphicElement> list, String[] pictureNames) {

		initPanel(pictureNames);

		// the canvas must be smaller, because of the ScrollPane and the area above and
		// below
		canvas = new MainCanvas(list, frameWidth - 80, frameHeight - 120);

		// create ScrollPane and add it to the frame panel
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.add(canvas);

		scrollPane.setPreferredSize(new Dimension(frameWidth - 50, frameHeight - 90));
		scrollPane.setMinimumSize(new Dimension(frameWidth - 50, frameHeight - 90));

		add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * update the elements of the frame panel (as far as needed)
	 */
	public void updateView() {
		// repaint the canvas panel
		canvas.repaint();
	}

	/**
	 * adds a new picture name to the drop down menu
	 * 
	 * @param name
	 *            picture name to be added
	 */
	public void addPictureName(String name) {
		pictureNames.addItem(name);
		pictureNames.setSelectedItem(name);
	}

	/**
	 * set a picture which should be shown and update the panels
	 * 
	 * @param element
	 *            picture which should be shown
	 */
	public void showPicture(ListItem<GeometricGraphicElement> element) {
		// every element of the picture should be drawn with a little delay to the next
		// element

		// init the timer with delay and an ActionListener implementing the behavior
		timer = new Timer(Constants.INTERVAL_TIME, new ActionListener() {

			// indicates which element should be shown next (the first time)
			int									counter	= 1;
			// list of elements which had been shown
			ListItem<GeometricGraphicElement>	list	= new ListItem<GeometricGraphicElement>(null);

			@Override
			// what should be done in every step
			public void actionPerformed(ActionEvent arg0) {
				// as long as there are elements not shown, pick the next element of the list
				// and put it to the list of elements, which had already been shown
				if (counter <= element.getSize()) {

					list.insert(element.get(counter));

					// let the canvas repaint
					canvas.setPicture(list);
					updateView();
					counter++;
				} else
					// stop the timer, if every element is visible
					timer.stop();
			}
		});
		timer.setInitialDelay(0);
		// start the timer
		timer.start();
	}
}