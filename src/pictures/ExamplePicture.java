package pictures;

import data.ListItem;
import model.GeometricModelElement;
import model.Point;
import model.round.CircleElement;
import model.round.EllipseElement;
import util.Constants;

/**
 * An example for creating/calculating a picture
 * 
 * @author Nora Wester
 */
public class ExamplePicture extends PictureCalculator {

	// calculate a test picture

	private ListItem<GeometricModelElement> g = new ListItem<GeometricModelElement>(null);

	@Override
	public ListItem<GeometricModelElement> getPictureModel() {

		return g;
	}

	@Override
	public void calculate() {

		super.setName("Example_2");

		CircleElement circle = new CircleElement();
		circle.setStartPoint(new Point(0, 0));
		circle.setHeight(1);

		g.insert(circle);

		EllipseElement ellipse = new EllipseElement();
		ellipse.setStartPoint(new Point(3, 4));
		ellipse.changeColorCode(Constants.COLOR_CODE_BLUE);
		ellipse.setHeight(2);
		ellipse.setWidth(1);

		g.insert(ellipse);

		EllipseElement ellipse2 = (EllipseElement) ellipse.cloneElement();
		ellipse2.rotate(45);
		ellipse2.changeColorCode(Constants.COLOR_CODE_MAGENTA);

		g.insert(ellipse2);

		EllipseElement ellipse3 = (EllipseElement) ellipse.cloneElement();
		ellipse3.scale(2);
		ellipse3.changeColorCode(Constants.COLOR_CODE_GREEN);

		g.insert(ellipse3);
	}
}