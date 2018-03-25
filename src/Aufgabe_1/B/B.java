package Aufgabe_1.B;

import data.ListItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

import com.oracle.tools.packager.RelativeFileSet.Type;

import Aufgabe_1.V.ComparatorInteger;
import Aufgabe_1.V.V;

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
		T fst = null;
		if (arr.length<4) return;
		try {
			//record the first Element of array, if arr eine null-Referenz goto catch
			fst = arr[0];
			//recursive start
			rotateQuadrupleLeftRec(arr, 0);
		}catch(IndexOutOfBoundsException e) {
			//recursive end
			arr[arr.length-1] = fst;
		}catch(NullPointerException e) {
			throw new IllegalArgumentException();
		}
		return;
	}
	/**
	 * Recursive helper method for rotateQuadrupleLeft.
	 *
	 * @param arr
	 *            the array to work on
	 * @param index
	 */
	private void rotateQuadrupleLeftRec(T[] arr, int index) {
		// If the array is null, throw the IndexOutOfBoundsException, let the callfunction handle it.
		arr[index]=arr[index+1];
		//else recursive shift
		rotateQuadrupleLeftRec(arr, index+1);
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
		//add the ListItem 
		if (key == null || cmp == null) throw new IllegalArgumentException();
		ListItem<T> p = new ListItem<T>(key);
		if (lst == null) return p;
		//sort the list
		ListItem<T> sList = sortList(lst, cmp);
		//is (the new key < the head Item(the least) of sortedList "lst") 
		if (cmp.compare(p.key, sList.key) <= 0){
			//p is the least one in the whole list
			p.next = sList;
			return p;
		}else {
			//p is not the least one in the whole list, then insertSingle it to the rest list.
			lst.next = insertSingle(lst.next, p.key, cmp);
			 return lst;
		}
	}
	
	/** 
	 * sort a list, als a helper method for insertSingle
	 * @param lst
	 * @param cmp
	 * @return the sortedList
	 */
	private ListItem<T> sortList(ListItem<T> lst, Comparator<T> cmp){
		//if the whole list just have one Item, then it is the greatest one.
		//if the whole list has other Items, call insertSingle, to insert the first key of the list into the left list
		// and insertSingle ensure that the returned list is in the increasing Order.
		System.out.println("sortList"+lst);
		if (lst.next != null) lst=insertSingle(lst.next, lst.key, cmp); 
		return lst;
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
		// return next
		return (lst == null)?lst:(lst=lst.next);
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
		if (lst== null || lst.next==null) {return lst;}
		ListItem<T> p = lst;
		//get the last one and then pop it
		ListItem<T> newHeader = popLastRec(p);
		//make the rest list behind the header
		newHeader.next = lst;
		return newHeader;
	}
	
	/** recursive pop the last Item of List
	 * @param lst
	 * @return the poped Item (tail)
	 */
	private ListItem<T> popLastRec(ListItem<T> lst){
		System.out.println("popLastRec"+lst);
		if (lst.next.next == null) {
			ListItem<T> tmp = lst.next;
			lst.next = null;
			return tmp;
		}else {
			return popLastRec(lst.next);
		}
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
		ListItem<ListItem<T>> llst= new ListItem<ListItem<T>>(null);
		for(ListItem<T> p = lst; p != null; p= p.next ) {
			ListItem<T> tmp = new ListItem<T>(p.key);
			tmp.next = null;
			ListItem<ListItem<T>> tmpl = new ListItem<ListItem<T>>(tmp);
			llst.next = tmpl;
			llst = llst.next;
		}
		return llst.next;
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

		if (lst==null || type == null) throw new IllegalArgumentException();
		int arrSize = 0;
		T[] retT = null;
		
		for(ListItem<T> p = lst; p != null; p= p.next) {
			if (type.isInstance(p.key)) {
				arrSize++;
				//dont need check cast, because has already checked the class
				T[] newT = (T[]) Array.newInstance(type, arrSize);
				for(int i=0;i<arrSize-1;i++) {
					newT[i] = retT[i];
				}
				newT[arrSize-1]= p.key;
				retT = newT;
			}
		}
		return retT;
	}
	
	
	

}