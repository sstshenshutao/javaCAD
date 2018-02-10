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
		// TODO Your task
		return 0;
	}
}