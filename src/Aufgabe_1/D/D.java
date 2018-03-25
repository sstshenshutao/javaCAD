package Aufgabe_1.D;

import data.ListItem;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * Tasks of subproject 1.D
 * 
 * @author Lukas Roehr
 * @author David Koehler
 * @param <T>
 *            Generic Type
 */
public class D<T> {

	private int index = 1;
	/**
	 * Die Methode vertauscht die Elemente an den zwei angegebenen Indizes. Falls f�r arr eine null-Referenz übergeben
	 * wird oder einer der Indizes nicht in dem Array liegt, soll eine IllegalArgumentException geworfen werden.
	 * Implementieren Sie diese Methode in konstanter Laufzeit.
	 * 
	 * @param arr
	 *            The array to work on
	 * @param ind1
	 *            Index of the first element
	 * @param ind2
	 *            Index of the second element
	 * @throws IllegalArgumentException
	 *             if arr is null or Indices is invalid.
	 */
	public void switchElements(T[] arr, int ind1, int ind2) throws IllegalArgumentException {
		if (arr == null)
			throw new IllegalArgumentException();
		
		if (ind1 < 0 || ind1 > arr.length || ind2 < 0 || ind2 > arr.length)
			throw new IllegalArgumentException();
		
		T temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}

	/**
	 * Die Methode fügt die Liste elem an das Ende von lst an und gibt das Ergebnis zurück. Implementieren Sie diese
	 * Methode iterativ.
	 * 
	 * @param lst
	 *            the list to work on
	 * @param elem
	 *            the list element(s) to add to the end
	 * @return the list lst with elem added to the end
	 */
	public ListItem<T> insertLast(ListItem<T> lst, ListItem<T> elem) {
		for (int i = 0; i < elem.getSize(); i++)
		{
			lst.insert(elem.key);
			elem = elem.next;
		}
		return lst;
	}

	/**
	 * Die Methode löscht das zweitgrößte Element aus der Eingabeliste. Ist dieses Element nicht enthalten, so ist es
	 * bereits als gelöscht anzusehen. Falls für cmp eine null-Referenz übergeben wird, soll eine
	 * IllegalArgumentException geworfen werden. Implementieren Sie diese Methode iterativ.
	 *
	 * @param lst
	 *            the list to work on
	 * @param cmp
	 *            the comparator to detect the second max element
	 * @return the list lst without the second biggest element
	 * @throws IllegalArgumentException
	 */
	public ListItem<T> removeSecMaxElement(ListItem<T> lst, Comparator<T> cmp) throws IllegalArgumentException {
		if (cmp == null)
			throw new IllegalArgumentException();
		
		int n =lst.getSize();
		T max = null;
		T semax = null;
		ListItem<T> nlst = lst;
		for (int i = 0; i < n; i++)
		{
			if (max == null)
			{
				max = nlst.key;
				nlst = nlst.next;
			}
			else if (cmp.compare(max, nlst.key) < 0)
			{
				semax = max;
				max = nlst.key;
				nlst = nlst.next;
			}
			else if (cmp.compare(max, nlst.key) == 0)
			{
				nlst = nlst.next;
			}
			else if (semax == null)
			{
				semax = nlst.key;
				nlst = nlst.next;
			}
			else if (cmp.compare(semax, nlst.key) < 0)
			{
				semax = nlst.key;
				nlst = nlst.next;
			}
			else
			{
				nlst = nlst.next;
			}
		}
		if (semax != null)
		{
			nlst = lst;
			lst = new ListItem<T>(null);
			for (int j = 0; j < n; j++)
			{
				if (cmp.compare(semax, nlst.key) == 0)
				{
					nlst = nlst.next;
				}
				else
				{
					lst.insert(nlst.key);
					nlst = nlst.next;
				}
			}
		}
		return lst;
	}

	/**
	 * Für i = 1,2,3,... vertauscht die Methode die Schl�sselwerte an den Positionen 3i - 2 und 3i, d.h.: Das Element,
	 * das vorher an Position 3i - 2 war, ist hinterher an Position 3i und umgekehrt. Ist kein komplettes Triple mehr
	 * übrig, auf dem die Operation ausgeführt werden kann, so bricht die Methode ab und ist fertig. Falls eine
	 * null-Referenz übergeben wird, soll eine IllegalArgumentException geworfen werden. Implementieren Sie diese
	 * Methode rekursiv.
	 * 
	 * @param lst
	 *            the list to work on
	 * @return the list where all complete triples are inverted
	 */
	public ListItem<T> invertTriples(ListItem<T> lst) {
		if (lst == null)
			throw new IllegalArgumentException();
		
		
		int n = lst.getSize();
		if (n < index * 3)
		{
			index = 1;
			return lst;
		}
		else
		{
			ListItem<T> nlst = new ListItem<T>(null);
			T temp1 = lst.key;
			lst = lst.next;
			T temp2 = lst.key;
			lst = lst.next;
			T temp3 = lst.key;
			lst = lst.next;
			nlst.insert(temp3);
			nlst.insert(temp2);
			nlst.insert(temp1);
			nlst.next.next.next = invertTriples(lst);
			index++;
			return nlst;
		}
	}

	public ListItem<T> hilfeIntoList(ListItem<T> lst)
	{
		if (lst.getSize() <= 2)
		{
			return new ListItem<T>(lst.key);
		}
		else
		{
			ListItem<T> lst1 = new ListItem<T>(lst.key);
			lst1.next = hilfeIntoList(lst.next.next);
			return lst1;
		}
	}
	
	/**
	 * Die Methode erhält eine Liste und gibt eine Liste von genau zwei Listen zurück, wobei alle Elemente an einer
	 * ungeraden Position der Eingabeliste in der ersten Liste zu finden sind, alle Elemente der Eingabeliste mit einer
	 * geraden Position in der zweiten Liste. Hierfür dürfen neue Listenelemente der Klasse ListItem<ListItem<T>>
	 * erstellt werden. Implementieren Sie diese Methode rekursiv.
	 *
	 * @param lst
	 *            the list to split
	 * @return a list of two lists where all list elements of the input list are located
	 */
	public ListItem<ListItem<T>> divideAlternatinglyIntoLists(ListItem<T> lst) {
		if (lst.getSize() == 0)
		{
			ListItem<T> lst1 = new ListItem<T>(null);
			ListItem<T> lst2 = new ListItem<T>(null);
			ListItem<ListItem<T>> nlst = new ListItem<ListItem<T>>(lst1);
			nlst.next.key = lst2;
			return nlst;
		}
		else if (lst.getSize() == 1)
		{
			ListItem<T> lst1 = new ListItem<T>(lst.key);
			ListItem<T> lst2 = new ListItem<T>(null);
			ListItem<ListItem<T>> nlst = new ListItem<ListItem<T>>(lst1);
			nlst.next.key = lst2;
			return nlst;
		}
		else
		{
			ListItem<T> nst = lst.next;
			ListItem<T> lst1 = hilfeIntoList(lst);
			ListItem<T> lst2 =  hilfeIntoList(nst);
			ListItem<ListItem<T>> nlst = new ListItem<ListItem<T>>(lst1);
			nlst.next.key = lst2;
			return nlst;
		}
	}

	/**
	 * Die Methode erhält eine Liste und wandelt diese in ein Array um, d.h. genau alle Schlüsselwerte der Liste sind in
	 * dem Array, welches zurückgegeben wird, in ursprünglicher Reihenfolge enthalten. Sind keine Schlüsselwerte in der
	 * Liste enthalten, so soll ein Array der Länge null zurückgegeben werden. Implementieren Sie diese Methode
	 * iterativ.
	 *
	 * 
	 * @param lst
	 *            the list to convert
	 * @return a array with all key values of the given list
	 */
	public T[] listIntoArray(ListItem<T> lst, Class<?> type) {
		if (type == null || lst == null)
			throw new IllegalArgumentException();
		ListItem<T> nlst = lst;
		T[] arr = (T[]) Array.newInstance(type, lst.getSize());
		for (int i = 0; i < lst.getSize(); i++)
		{
			arr[i] = nlst.key;
			nlst = nlst.next;
		}
		return arr;
	}
}
