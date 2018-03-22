package Aufgabe_1.B;

import java.awt.geom.Ellipse2D;
import java.util.Comparator;

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
		/*
		 * double width = xPoints[1] - xPoints[0];
			double height = yPoints[1] - yPoints[0];
			Ellipse2D ellipse = new Ellipse2D.Double(xPoints[0], yPoints[0], width, height);
		 */
		
		elem1.getNumberOfPoints()
		return 0;
	}
}