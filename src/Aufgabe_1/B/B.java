package Aufgabe_1.B;

import data.ListItem;

import java.util.Comparator;

/**
 * Tasks of subproject 1.B
 *
 * @author Lukas Roehr
 * @author David Koehler
 * @param <T>
 *            Generic Type
 */
public class B<T> {

	/**
	 * Die Methode rotiert alle vollständigen Quadruple5 im Ring nach links, d.h. solange noch vier Elemente übrig sind,
	 * werden alle Elemente dieses Quadruples um eins nach links geschoben, das erste Element wird zum letzten. Falls
	 * für arr eine null-Referenz übergeben wird, soll eine IllegalArgumentException geworfen werden. Implementieren Sie
	 * diese Methode rekursiv.
	 * 
	 * @param arr
	 *            the array to work on
	 * @throws IllegalArgumentException
	 *             if arr is null
	 */
	public void rotateQuadrupleLeft(T[] arr) throws IllegalArgumentException {
		// TODO Your task
		return;
	}

	/**
	 * Fügt den Parameter key in den gemäß cmp aufsteigend sortierten Parameter lst ein. Nach dem Einfügen muss der
	 * Parameter lst aufsteigend nach cmp vom Typ java.util.Comparator sortiert sein. Die Methode gibt den Kopf der
	 * Liste zurück. Hierfür darf ein neues Listenelement erstellt werden. Implementieren Sie diese Methode rekursiv.
	 * 
	 * @param lst
	 *            list in which the element is to be inserted
	 * @param key
	 *            key to insert
	 * @param cmp
	 *            comparator
	 * @return the new list
	 * @throws IllegalArgumentException
	 *             if key or cmp is null
	 */
	public ListItem<T> insertSingle(ListItem<T> lst, T key, Comparator<T> cmp) throws IllegalArgumentException {
		// TODO Your task
		return null;
	}

	/**
	 * Löscht das Kopf-Element des Parameters lst. Die Methode gibt den neuen Listenkopf zurück. Wenn für lst eine
	 * null-Referenz übergeben wird, ist der Kopf als bereits gelöscht anzusehen. Implementieren Sie diese Methode in
	 * konstanter Laufzeit.
	 * 
	 * @param lst
	 *            the list to work on
	 * @return list with a new head
	 */
	public ListItem<T> removeHead(ListItem<T> lst) {
		// TODO Your task
		return null;
	}

	/**
	 * Die Methode verschiebt alle Listenelemente des Parameters lst um eine Stelle nach rechts. Das letzte Element wird
	 * der neue Kopf der Liste. Die Methode gibt den neuen Kopf der korrekt im Ring nach rechts geshifteten Liste
	 * zurück. Implementieren Sie diese Methode rekursiv.
	 * 
	 * @param lst
	 *            the list to work on
	 * @return the new list
	 */
	public ListItem<T> ringShiftRight(ListItem<T> lst) {
		// TODO Your task
		return null;
	}

	/**
	 * Die Methode teilt die übergebene Liste in eine Liste von mehreren einelementrigen Listen auf, wobei jeder
	 * Schlüsselwert der Eingabeliste zu genau einem Schlüsselwert einer einelementrigen Liste wird. Hierfür dürfen neue
	 * Listenelemente der Klasse ListItem<ListItem<T>> erstellt werden. Implementieren Sie diese Aufgabe iterativ.
	 * 
	 * @param lst
	 *            the list to split
	 * @return List of (List of T)
	 */
	public ListItem<ListItem<T>> listInLists(ListItem<T> lst) {
		// TODO Your task
		return null;
	}

	/**
	 * Die Methode selektiert alle Schlüsselwerte der Liste lst, die vom übergebenen Typ type sind. Diese Elemente
	 * werden in einem Array zurückgegeben. Falls für type oder für lst eine null-Referent übergeben wird, soll eine
	 * IllegalArgumentException geworfen werden. Implementieren Sie diese Aufgabe iterativ.
	 * 
	 * @param lst
	 *            the list to select of
	 * @param type
	 *            the class to select, must be a subclass of the generic type T
	 * @return a array with all keys of the List that are the given class type
	 * @throws IllegalArgumentException
	 *             if type is null
	 */
	public T[] selectType(ListItem<T> lst, Class<? extends T> type) throws IllegalArgumentException {
		// TODO Your task
		return null;
	}
}