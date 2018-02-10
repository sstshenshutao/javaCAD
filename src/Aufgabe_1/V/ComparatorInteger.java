package Aufgabe_1.V;

import java.util.Comparator;

/**
 * Die Klasse implementiert einen java.util.Comparator, welcher den Vergleich von Integer durchführt.
 * 
 * @author David Koehler
 */
public class ComparatorInteger implements Comparator<Integer> {

	@Override
	public int compare(Integer elem1, Integer elem2) {
		return (elem1 < elem2 ? -1 : // smaller return -1
				(elem1 == elem2 ? 0 : // equal return 0
						1)); // bigger return 1
	}
}