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
	private int index = 0;

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
		if (arr == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if (arr.length < 3 + 3 * index)
			{
				index = 0;
				return;
			}
			else if (arr.length == 3 + 3 * index)
			{
				T a = arr[index * 3];
				arr[index * 3] = arr[index * 3 + 2];
				arr[index * 3 + 2] = a;
				index = 0;
				return;
			}
			else if (arr.length > 3 + 3 * index)
			{
				T a = arr[index * 3];
				arr[index * 3] = arr[index * 3 + 2];
				arr[index * 3 + 2] = a;
				index++;
				invertTriples(arr);
			}
		}
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
		ListItem<T> l = new ListItem<T>(key);
		l.next = lst;
		return l;
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
		if (key == null || cmp == null)
			throw new IllegalArgumentException();
		else if (lst == null)
		{
			return lst;
		}
		else
		{
			if (cmp.compare(lst.key, key) == 0)
			{
				return removeElementsEqualX(lst.next, key, cmp);
			}
			else
			{
				lst.next = removeElementsEqualX(lst.next, key, cmp);
				return lst;
			}
		}
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
		ListItem<T> nlst = new ListItem<T>(null);
		if (lst == null)
			return lst;
		else
		{	
			T temp = lst.key;
			int n = lst.getSize();
			for (int i = 1; i < n; i++)
			{
				T t = lst.next.key;
				nlst.insert(t);
				lst = lst.next;
			}
			nlst.insert(temp);
			return nlst;
		}
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
		ListItem<ListItem<T>> nlsts;
		if (lsts == null)
			throw new IllegalArgumentException();
		
		if (lsts.next == null && lsts.key.next == null)
			return lsts.key;
		else
		{
			if (lsts.key.next == null)
			{
				lsts.key.next = listsInList(lsts.next);
				return lsts.key; 
			}
			else
			{
				nlsts = lsts;
				ListItem<ListItem<T>> nnlsts = new ListItem<ListItem<T>>(lsts.key.next);
				nnlsts.key = lsts.key.next;
				nlsts.key.next = listsInList(nnlsts);
				return nlsts.key;
			}
		}
		
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
		ListItem<ListItem<T>> nlsts = new ListItem<ListItem<T>>(null);
		ListItem<T> klst = new ListItem<T>(null);
		if (arr == null || cmp == null)
			throw new IllegalArgumentException();
		
		if (arr.length == 0)
			return null;
		
		
		for (int i = 0; i < arr.length; i++)
		{
			if (klst.key == null || cmp.compare(arr[i - 1], arr[i]) < 0)
			{
				klst.insert(arr[i]);
			}
			else
			{
				nlsts.insert(klst);
				klst = new ListItem<T>(null);
			}
		}
		nlsts.insert(klst);
		return nlsts;
	}
}