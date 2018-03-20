package Aufgabe_1.V;

import data.ListItem;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * Example subproject 1.V
 * 
 * @author Lukas Roehr
 * @author David Koehler
 * @param <T>
 *            Generic Type
 */
public class V<T> {

	/**
	 * Sorts the given array in ascending order by the comparator by using the bubblesort algorithm
	 * 
	 * @param arr
	 *            the array to work on
	 * @param cmp
	 *            a Comparator to sort the array
	 * @throws IllegalArgumentException
	 */
	public void bubbleSort(T[] arr, Comparator<T> cmp) throws IllegalArgumentException {
		if (arr == null || cmp == null)
			throw new IllegalArgumentException("The array and the comparator must be unequal null.");
		int n = arr.length;
		do {
			// If we don't swap any elements from position x to n, they are in corrent order
			// and we can set x as our new n, since the rest is in correct order
			int newN = 1;
			for (int i = 0; i < n - 1; ++i)
				// Compare the current element with the next and swap if necessary
				if (cmp.compare(arr[i], arr[i + 1]) > 0) {
					// Swap i and i + 1
					T tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
					newN = i + 1;
				} // ende if
			// Set the last position of swaping as our new n, since the rest is in correct
			// oder
			n = newN;
		} while (n > 1); // If n <= 1 we are done, since one element is automatically in correct order
	}

	/**
	 * Removes every second item from the list
	 * 
	 * @param lst
	 *            the list to work on
	 * @return head of list
	 */
	public ListItem<T> removeEverySecElement(ListItem<T> lst) {
		// Call the recursive method
		removeEverySecElementRec(lst);
		return lst;
	}

	/**
	 * Recursive helper method for removeEverySecElement.
	 *
	 * @param lst
	 *            the list to work on
	 * @return head of list
	 */
	private void removeEverySecElementRec(ListItem<T> lst) {
		// If the list has no more than one element, we are done
		if (lst == null || lst.next == null)
			return;
		// Skip the next element
		lst.next = lst.next.next;
		// Call the recursive method again
		removeEverySecElementRec(lst.next);
	}

	/**
	 * Clone all elements of a list that are larger than key.
	 * 
	 * @param lst
	 *            the list to clone
	 * @param key
	 *            Comparison element
	 * @param cmp
	 *            compartor
	 * @return head of new list
	 * @throws IllegalArgumentException
	 *             if Comparison element or comparator is null
	 */
	public ListItem<T> cloneObjectsBiggerThanX(ListItem<T> lst, T key, Comparator<T> cmp)
			throws IllegalArgumentException {
		// The key and the comparator must be unequal null
		if (key == null || cmp == null)
			throw new IllegalArgumentException("The key and the comparator must be unequal null.");
		// A dummy lsit
		ListItem<T> dummy = new ListItem<>(null);
		ListItem<T> current = dummy;
		for (ListItem<T> p = lst; p != null; p = p.next) {
			if (cmp.compare(p.key, key) > 0) {
				current.next = new ListItem<>(p.key);
				current = current.next;
			}
		}
		return dummy.next;
	}

	/**
	 * Adds the first Run of the given lst to the end of the dummyLst and returns the rest of lst
	 * 
	 * @param lst
	 *            the list to select the first run of
	 * @param dummyLst
	 *            the list to add the run to
	 * @param cmp
	 *            the Comparator to detect the run
	 * @return
	 * @throws IllegalArgumentException
	 *             if cmp is null
	 */
	public ListItem<T> addRunToList(ListItem<T> lst, ListItem<T> dummyLst, Comparator<T> cmp)
			throws IllegalArgumentException {
		// The comparator must be unequal null
		if (cmp == null | dummyLst == null)
			throw new IllegalArgumentException("The comparator and the dummy list must be unequal null.");
		// If the list is empty, we are done
		if (lst == null)
			return lst;

		// Go to the end of dummyLst
		while (dummyLst.next != null)
			dummyLst = dummyLst.next;
		// Add the first element of the run to the dummylst
		dummyLst.next = new ListItem<T>(lst.key);
		dummyLst = dummyLst.next;
		dummyLst.next = null;

		lst = lst.next;
		// Add elements of lst to the dummylst as long as it is sorted correctly
		while (lst != null && lst.key != null && dummyLst != null && dummyLst.key != null
				&& cmp.compare(dummyLst.key, lst.key) < 0) {
			dummyLst.next = new ListItem<T>(lst.key);
			dummyLst = dummyLst.next;
			lst = lst.next;
		}
		return lst;
	}

	/**
	 * Copies the given list.
	 * 
	 * @param lst
	 *            the list to clone
	 * @return a new list with the same keys as the original list
	 */
	public ListItem<T> clone(ListItem<T> lst) {
		// A dummy element to add all new elements onto
		ListItem<T> dummy = new ListItem<>(null);
		ListItem<T> last = dummy;
		// Add all copied elements to the dummy list
		for (ListItem<T> p = lst; p != null; p = p.next) {
			last.next = new ListItem<>(p.key);
			last = last.next;
		}
		return dummy.next;
	}

	/**
	 * This method returns a List with two Listelements. The key of the first Listelement is a List with every second
	 * run of the given list, the key of the second Listelement is an array with the other runs of the given list.
	 * 
	 * @param lst
	 *            the list to split alternangly into a list and an array by Runs
	 * @param cmp
	 *            the comparator to detect Runs
	 * @return a List with two Listelements. The key of the first Listelement is a List with every second run, the key
	 *         of the second Listelement is an array with the other runs of the given list.
	 * @throws IllegalArgumentException
	 */
	public ListItem<Object> divideByRunsIntoArrayAndList(ListItem<T> lst, Comparator<T> cmp, Class<?> type)
			throws IllegalArgumentException {
		if (cmp == null)
			throw new IllegalArgumentException("The comparator must be unequal null.");
		ListItem<T> dummy1 = new ListItem<T>(null), dummy2 = new ListItem<T>(null);
		// Call the helper method
		ListItem<ListItem<T>> listOfLists = divideByRunsIntoListAndList(lst, dummy1, dummy2, cmp);
		@SuppressWarnings("unchecked")
		ListItem<Object> result = new ListItem<Object>((ListItem<Object>) listOfLists.key);

		ListItem<T> lst2 = listOfLists.next.key.next;
		int size = 0;
		ListItem<T> p = lst2;
		// Count the number of elements
		for (; p != null && p.key != null; p = p.next)
			size++;

		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(type, size);
		int i = 0;
		// Put the keys into the array
		for (p = lst2; p != null && p.key != null; p = p.next) {
			array[i] = p.key;
			i++;
		}

		result.next = new ListItem<Object>(array);
		return result;
	}

	/**
	 * Divides one list into a list of two lists by runs and adds these runs alternagly to dummy1 and dummy2.
	 * 
	 * @param lst
	 *            the list to divide
	 * @param dummy1
	 *            the first list to add every second run into (the first, thrid, ... run)
	 * @param dummy2
	 *            the second list to add every second run into (the second, fourth, ... run)
	 * @param cmp
	 *            the comparator to detect runs
	 * @return a list of two lists with dummy1 and dummy2, each one with the added runs to the end
	 */
	public ListItem<ListItem<T>> divideByRunsIntoListAndList(ListItem<T> lst, ListItem<T> dummy1, ListItem<T> dummy2,
			Comparator<T> cmp) {
		if (lst == null || dummy1 == null || dummy2 == null || cmp == null)
			return null;
		boolean addTo1 = true;
		while (lst != null) {
			if (addTo1)
				lst = this.addRunToList(lst, dummy1, cmp);
			else
				lst = this.addRunToList(lst, dummy2, cmp);
			addTo1 = !addTo1;
		}
		ListItem<ListItem<T>> result = new ListItem<ListItem<T>>(dummy1);
		result.next = new ListItem<ListItem<T>>(dummy2);
		return result;
	}
}
