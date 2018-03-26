package Aufgabe_1.D;

import java.util.Comparator;

import model.round.CircleElement;

/**
 * Die Klasse implementiert einen java.util.Comparator, welcher den Vergleich anhang des Radius durchführt. Dabei ist
 * ein kleinerer Radius als kleiner zu bewerten.
 */
public class ComparatorRadius implements Comparator<CircleElement> {
	/**
	 * Implementieren Sie die Metode compare der Klasse ComparatorRadius, welche einen java.util.Comparator
	 * implementiert. Die Methode führt den Vergleich anhand des Radius durch. Dabei ist ein kleinerer Radius als
	 * kleiner zu bewerten.
	 */
	@Override
	public int compare(CircleElement elem1, CircleElement elem2) {
		double r1, r2;
		r1 = (elem1.getPoint(1).getX() - elem1.getPoint(0).getX())/2;
		r2 = (elem2.getPoint(1).getX() - elem2.getPoint(0).getX())/2;
		
		if (r1 < r2)
			return -1;
		else if (r1 > r2)
			return 1;
		else
			return 0;
	}
}