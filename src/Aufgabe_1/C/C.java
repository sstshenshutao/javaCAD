package Aufgabe_1.C;

import java.lang.reflect.Array;
import java.util.Comparator;

import data.ListItem;

/**
 * Tasks of subproject 1.C
 *
 * @author Lukas Roehr
 * @author David Koehler
 * @param <T>
 *            Generic Type
 */
public class C<T> {
	
	public void hilfecombine(T[] arr1, T[] arr2, T[] arr3, Comparator<T> cmp, int i, int j)
	 {
	  if (arr1 == null)
	  {
	   if (arr2 == null)
	    arr3 = null;
	   else
	    arr3 = arr2;
	  }
	  else if (arr2 == null)
	   arr3 = arr1;
	  else if (i+j >= arr3.length)
	   return;
	  else if (i >= arr1.length)
	  { 
	   arr3[i+j] = arr2[j];
	   hilfecombine(arr1, arr2, arr3, cmp, i, j + 1);
	  }
	  else if (j >= arr2.length)
	  {
	   arr3[i + j] = arr1[i];
	  hilfecombine(arr1, arr2, arr3, cmp, i + 1, j);
		}
	   else if (cmp.compare(arr1[i], arr2[j]) <= 0)
		{
		 arr3[i + j] = arr1[i];
		 hilfecombine(arr1, arr2, arr3, cmp, i + 1, j);
		}
		 else
		 {
		 arr3[i + j] = arr2[j];
		 hilfecombine(arr1, arr2, arr3, cmp, i, j + 1);
		}
	 }
	/**
	 * Die Methode kombiniert zwei nach dem übergebenem Comparator aufsteigend sortierte Arrays zu einem Array, das
	 * wiederum gemäß des Comparators aufsteigend sortiert ist, und gibt dieses zurück. Wird für cmp eine null-Referenz
	 * übergeben, so ist eine IllegalArgumentException zu werfen. Ist eines der beiden oder gar beide Arrays nicht
	 * aufsteigend gemäß cmp sortiert, so wird ebenfalls eine IllegalArgumentException geworfen. Wird für beide Arrays
	 * eine null-Referenz übergeben, so soll null zurückgegeben werden. Implementieren Sie diese Methode rekursiv.
	 * 
	 * @param arr1
	 *            the first array
	 * @param arr2
	 *            the second array to combine
	 * @param cmp
	 *            the comparator
	 * @return a combined Array with all elements of the two given arrays.
	 */
	  public T[] combine(T[] arr1, T[] arr2, Comparator<T> cmp, Class<?> type) throws IllegalArgumentException {
		  if (cmp == null || type == null)
		   throw new IllegalArgumentException();
		  
		  if (arr1== null) return arr2;
		  if (arr2== null) return arr1;
		   for (int i = 0; i < arr1.length - 1; i++)
		   {
		    if (cmp.compare(arr1[i], arr1[i + 1]) > 0)
		     throw new IllegalArgumentException();
		   }
		  
		  
		
		 
		   for (int i = 0; i < arr2.length - 1; i++)
		   
		   {
		    if (cmp.compare(arr2[i], arr2[i + 1]) > 0)
		     throw new IllegalArgumentException();
		   }
		  
		  int i1 = arr1.length;
		  int j1 = arr2.length;
		  T[] arr3 = (T[]) Array.newInstance(type, i1 + j1);
		  int i = 0;
		  int j = 0;
		  hilfecombine(arr1, arr2 ,arr3, cmp, i, j);
		  return arr3;
	  }

	/**
	 * Die Methode fügt die komplette Liste head an den Anfang der Liste lst an und gibt den neuen Listenkopf zurück.
	 * Falls für head eine null-Referenz übergeben wird, soll eine IllegalArgumentException geworfen werden, es sei
	 * denn, lst ist ebenfalls null. Hierfür darf ein neues Listenelement erstellt werden. Implementieren Sie diese
	 * Methode rekursiv.
	 * 
	 * @param lst
	 *            the list where to add the new head onto
	 * @param head
	 *            the new head (may be longer than one element)
	 * @return the list with the new head in front
	 */
	public ListItem<T> insertHead(ListItem<T> lst, ListItem<T> head) {
		if (lst == null && head == null) {
			throw new IllegalArgumentException();
		}
		if (lst == null) {
			return head;
		}
		head.insert(lst.key);
		return insertHead(lst.next, head);
	}


	/**
	 * Die Methode entfernt das letzte Listenelement aus der Liste lst und gibt die resultierende Liste zurück. Wenn für
	 * lst eine null-Referenz übergeben wird, ist das letzte Element als bereits gelöscht anzusehen. Implementieren Sie
	 * diese Methode iterativ.
	 * 
	 * @param lst
	 *            the list to work on
	 * @return the list without the last element
	 */
	public ListItem<T> removeLast(ListItem<T> lst) {
		if (lst == null) {
			return lst;
		}else if (lst.next== null) {
			return null;
		}else {
			ListItem<T> p = lst;
			for (; p.next.next!= null; p=p.next) ;
				p.next=null;
		}
			return lst;
	}

	/**
	 * Die Methode invertiert die komplette Reihenfolge der Liste lst, d.h. das erste Element wird zum neuen letzten
	 * Element, das zweite zum neuen zweitletzten, usw. Implementieren Sie diese Methode iterativ.
	 * 
	 * @param lst
	 *            the list to work on
	 * @return the inverted list
	 */
	public ListItem<T> invert(ListItem<T> lst) {
	    if(lst == null && lst.next == null){
	     return lst;
	    }    
	     ListItem<T> newlst = null;
	     while (lst != null) {
	     ListItem<T> next = lst.next;
	     lst.next = newlst;
	     newlst = lst;
	     lst = next;
	     } 
	       return newlst;
	     }

	/**
	 * Die Methode gibt eine Liste zurück, die an gerader Position alle Elemente aus lst1 und an ungerader Position alle
	 * Elemente aus lst2 enthält. Sollte die Länge der beiden Listen unterschiedlich sein, so ist der Rest der längeren
	 * Liste anzuhängen. Implementieren Sie diese Methode rekursiv.
	 * 
	 * @param lst1
	 *            the first list
	 * @param lst2
	 *            the second list to work with
	 * @return the combined list
	 */
	public ListItem<T> combineLists(ListItem<T> lst1, ListItem<T> lst2) {
		if (lst1 == null)
		   return lst2;
		  else if (lst2 == null)
		   return lst1;
		  else
		  {
			  if (lst1.getSize() < 2)
			  {
				  ListItem<T> lst = lst2;
				  return lst;
			  }
			  
			  if (lst2.getSize() < 2)
			  {
				  
				  ListItem<T> lst = new ListItem<>(lst1.next.key);
				  lst.next = new ListItem<>(lst2.key);
				  lst.next.next = combineLists(lst1.next.next, null);
				  return lst;
			  }
			  
			  
			  ListItem<T> lst = new ListItem<>(lst1.next.key);
			  lst.next = new ListItem<>(lst2.key);
			  lst.next.next = combineLists(lst1.next.next, lst2.next.next);
			  return lst;
		  }
	}

	/**
	 * Die Methode gibt eine Liste zurück, in welcher alle Elemente des Eingabearrays in der ursprünglichen Reihenfolge
	 * als Schlüsselwerte der Liste zu finden sind. Falls für arr eine null-Referenz übergeben wird, soll eine
	 * IllegalArgumentException geworfen werden. Falls das Array die Länge null hat, soll null zurückgegeben werden.
	 * Hierfür dürfen neue Listenelemente erstellt werden. Implementieren Sie diese Methode iterativ.
	 * 
	 * @param arr
	 *            the array to convert
	 * @return a list where the keys are the elements of the given array
	 * @throws IllegalArgumentException
	 */
	public ListItem<T> arrayIntoList(T[] arr) throws IllegalArgumentException {
		if(arr == null) {
			throw new IllegalArgumentException();
		}	
		if(arr.length == 0) {
			return null;
		}
		ListItem<T> newlst = new ListItem<T>(arr[0]);
		ListItem<T> p = newlst;
		for (int i = 1; i < arr.length; i++) {
			ListItem<T> item = new ListItem<T>(arr[i]);
			p.next = item;
			p = p.next;
		}
		return newlst;
	}
}