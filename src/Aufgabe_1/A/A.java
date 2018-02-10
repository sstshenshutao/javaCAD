package Aufgabe_1.A;

import data.ListItem;

import java.util.Comparator;

/**
 * Tasks of subproject 1.A
 * 
 * @author Lukas Roehr
 * @author David Koehler
 * @param <T>
 *            Generic Type
 */
public class A<T> {

	/**
	 * Für i = 0, 1, 2, ... vertauscht die Methode die Elemente an den Indizes 3i und 3i + 2, d.h.: Das Element, das
	 * vorher an Index 3i war, ist hinterher an Index 3i + 2 und umgekehrt. Ist kein komplettes Triple mehr übrig, so
	 * bricht die Methode ab und ist fertig. Falls eine null-Referenz übergeben wird, soll eine IllegalArgumentException
	 * geworfen werden. Implementieren Sie diese Methode rekursiv.
	 * 
	 * @param arr
	 *            the array to work on
	 * 
	 * @throws IllegalArgumentException
	 *             if arr is null
	 */
	public void invertTriples(T[] arr) throws IllegalArgumentException {
		// TODO Your task
		return;
	}

	/**
	 * Fügt ein Listenelement mit dem Schlüsselwert key am Anfang der Liste ein und gibt diesen neuen Listenkopf zurück.
	 * Hierfür darf ein neues Listenelement erstellt werden. Implementieren Sie diese Methode in konstanter Laufzeit.
	 * 
	 * @param lst
	 *            the list to add the element to the head
	 * @param key
	 *            the key to set for the new list head
	 * @return the new List
	 */
	public ListItem<T> insertSingleHead(ListItem<T> lst, T key) {
		// TODO Your task
		return null;
	}

	/**
	 * Die Methode entfernt alle Listenelemente, deren Schlüsselwert gleich dem Parameter key ist, aus dem Parameter lst
	 * und gibt die resultierende Liste zurück. Nutzen Sie zum Vergleich den Parameter cmp vom Typ java.util.Comparator.
	 * Falls für cmp oder key eine null-Referenz übergeben wird, soll eine IllegalArgumentException geworfen werden.
	 * Implementieren Sie diese Methode rekursiv.
	 * 
	 * @param lst
	 *            the list to work on
	 * @param key
	 *            comparison element
	 * @param cmp
	 *            comparator
	 * @return the new list
	 * @throws IllegalArgumentException
	 *             if key or cmp is null
	 */
	public ListItem<T> removeElementsEqualX(ListItem<T> lst, T key, Comparator<T> cmp) throws IllegalArgumentException {
		// TODO Your task
		return null;
	}

	/**
	 * Die Methode verschiebt alle Listenelemente des Parameters lst um eine Stelle nach links. Das erste Element wird
	 * das neue letzte Element der Liste. Die Methode gibt den neuen Kopf der korrekt im Ring nach links geshifteten
	 * Liste zurück. Implementieren Sie diese Methode iterativ.
	 * 
	 * @param lst
	 *            the list to work on
	 * @return the new list
	 */
	public ListItem<T> ringShiftLeft(ListItem<T> lst) {
		// TODO Your task
		return null;
	}

	/**
	 * Die Methode erstellt eine zusammenhänge Liste aus dem Parameter lsts. Dazu sollen alle Listen des Parameters lsts
	 * in der ursprünglichen Reihenfolge hintereinander angefägt werden und der Kopf der resultierenden Liste
	 * zurückgegeben werden. Falls für lsts eine null-Referenz übergeben wird, soll eine IllegalArgumentException
	 * geworfen werden; eine Liste von leeren Listen (also null als Schlüsselwert von lsts) ist hingegen zulässig.
	 * Hierfür dürfen neue Listenelemente der Klasse ListItem<ListItem<T>> erstellt werden. Implementieren Sie diese
	 * Methode rekursiv.
	 * 
	 * @param lsts
	 *            the lists to work on
	 * @return linked list
	 * @throws IllegalArgumentException
	 *             if lsts is null.
	 */
	public ListItem<T> listsInList(ListItem<ListItem<T>> lsts) throws IllegalArgumentException {
		// TODO Your task
		return null;
	}

	/**
	 * Die Methode überträgt alle Runs des Parameters arr als Liste in eine Liste und gibt den Kopf der erstellten
	 * Liste von Listen zurück. Nutzen Sie zum Vergleich den Parameter cmp. Falls für arr oder cmp eine null-Referenz
	 * übergeben wird soll eine IllegalArgumentException geworfen werden. Wird ein Array der Länge null übergeben, so
	 * ist null zurückzugeben. Hierfür dürfen neue Listenelemente erstellt werden. Implementieren Sie diese Methode
	 * iterativ.
	 * 
	 * @param arr
	 *            the array to work on
	 * @param cmp
	 *            the Comparator to use for finding runs
	 * @return a list of lists with all ascend sorted runs of the array
	 * @throws IllegalArgumentException
	 *             if arr or cmp is null
	 */
	public ListItem<ListItem<T>> arrayRunsToListOfLists(T[] arr, Comparator<T> cmp) throws IllegalArgumentException {
		// TODO Your task
		return null;
	}
}