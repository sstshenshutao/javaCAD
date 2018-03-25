package Aufgabe_1.A;

import java.util.Comparator;

import model.GeometricModelElement;

/**
 * Die Klasse implementiert einen java.util.Comparator, welcher den Vergleich anhang der Farbe durchführt. Die
 * Farbreihenfolge ist in util.Constants festgelegt.
 */
public class ComparatorColor implements Comparator<GeometricModelElement> {

	/**
	 * Implementieren Sie die Metode compare der Klasse ComparatorColor, welche einen java.util.Comparator
	 * implementiert. Die Methode führt den Vergleich anhand der Farbecodes durch. Die Farbreihenfolge ist in
	 * util.Constants festgelegt und ein kleinerer Code ist als kleiner anzusehen.
	 */
	@Override
	public int compare(GeometricModelElement elem1, GeometricModelElement elem2) throws IllegalArgumentException {
		if (elem1.getColorCode() < 1 || elem1.getColorCode() > 8 || 
				elem2.getColorCode() < 1 || elem2.getColorCode() > 8)
			throw new IllegalArgumentException();
		if (elem1.getColorCode() < elem2.getColorCode())
			return -1;
		else if (elem1.getColorCode() == elem2.getColorCode())
			return 0;
		else
			return 1;
	}
}