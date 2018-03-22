package Aufgabe_1.B;

import java.util.Comparator;

import model.Point;
import model.round.RoundGeometricElement;

/**
 * Die Klasse implementiert einen java.util.Comparator, welcher den Vergleich anhang der Fläche durchführt. Dabei ist
 * eine kleinere Fläche als kleiner zu bewerten.
 */
public class ComparatorSurface implements Comparator<RoundGeometricElement> {
	/**
	 * Implementieren Sie die Metode compare der Klasse ComparatorSurface, welche einen java.util.Comparator
	 * implementiert. Die Methode führt den Vergleich anhand der Fläche durch. Dabei ist eine kleinere Fläche als
	 * kleiner anzusehen.
	 */
	@Override
	public int compare(RoundGeometricElement elem1, RoundGeometricElement elem2) {
		// TODO Your task
		/* the Method to draw is:
		 * 	double width = xPoints[1] - xPoints[0];
		 * 	double height = yPoints[1] - yPoints[0];
		 * 	Ellipse2D ellipse = new Ellipse2D.Double(xPoints[0], yPoints[0], width, height);
		 * the surface of a Ellipse is pi*a*b/4 , circle is also a Ellipse
		 * so it just needs to compare the width and the height of two RoundGeometricElement
		 */
		double pi=3.14;
		
		Point[] points1 = elem1.getPoints();
		double width1 = points1[1].getX() - points1[0].getX();//for circle points1[0]= 0;
		double height1 = points1[1].getY() - points1[0].getY();
		double surface1 = pi * width1 * height1 /4;
		
		Point[] points2 = elem2.getPoints();
		double width2 = points2[1].getX() - points2[0].getX();
		double height2 = points2[1].getY() - points2[0].getY();
		double surface2 = pi * width2 * height2 /4;

		double diff = surface1 - surface2;
		
		return (diff < 0 ? -1 : // smaller return -1
			(diff == 0 ? 0 : // equal return 0
				1)); // bigger return 1;
	}
}