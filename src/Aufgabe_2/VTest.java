package Aufgabe_2;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Aufgabe_1.V.ComparatorInteger;
import Aufgabe_1.V.V;
import data.ListItem;

/**
 * Tests for subproject 1.C
 *
 * @author Lukas Roehr
 * @author David Koehler
 *
 */
public class VTest {

	/**
	 * Listelements
	 */
	private ListItem<Integer>	lst1, lst2, lst3;
	private ListItem<Integer>	listAscInt;

	protected V<Integer>		vInt	= new V<>();
	protected Integer[]			arrayAscInt;
	private Comparator<Integer>	cmpInt	= new ComparatorInteger();

	// ********************************************** TESTS ********************************************** \\

	@Test(expected = IllegalArgumentException.class)
	public void bubbleSortArrNull() {
		vInt.bubbleSort(null, this.cmpInt);
	}

	@Test(expected = IllegalArgumentException.class)
	public void bubbleSortCmpNull() {
		vInt.bubbleSort(this.arrayAscInt, null);
	}

	@Test
	public void bubbleSort() {
		Integer[] originalArr = this.arrayAscInt.clone();
		for (int i = 0; i < this.arrayAscInt.length; i++)
			this.arrayAscInt[i] = originalArr[9 - i];

		vInt.bubbleSort(this.arrayAscInt, this.cmpInt);

		Assert.assertArrayEquals(originalArr, this.arrayAscInt);
	}

	@Test
	public void removeEverySecElement() {
		vInt.removeEverySecElement(this.listAscInt);
		Assert.assertEquals(0, this.listAscInt.key, 0);
		Assert.assertEquals(2, this.listAscInt.next.key, 0);
		Assert.assertEquals(4, this.listAscInt.next.next.key, 0);
		Assert.assertEquals(6, this.listAscInt.next.next.next.key, 0);
		Assert.assertEquals(8, this.listAscInt.next.next.next.next.key, 0);
		Assert.assertEquals(null, this.listAscInt.next.next.next.next.next);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cloneObjectsBiggerThanXKeyNull() {
		vInt.cloneObjectsBiggerThanX(this.listAscInt, null, this.cmpInt);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cloneObjectsBiggerThanXCmpNull() {
		vInt.cloneObjectsBiggerThanX(this.listAscInt, 5, null);
	}

	@Test
	public void cloneObjectsBiggerThanX() {
		ListItem<Integer> res = vInt.cloneObjectsBiggerThanX(this.listAscInt, 6, cmpInt);

		Assert.assertEquals(res.key, 7, 0);
		Assert.assertEquals(res.next.key, 8, 0);
		Assert.assertEquals(res.next.next.key, 9,0);
	}

	@Test
	public void addRunToList() {
		ListItem<Integer> dummy1 = new ListItem<Integer>(101);
		ListItem<Integer> dummy2 = new ListItem<Integer>(102);

		lst1 = vInt.addRunToList(lst1, dummy1, this.cmpInt);
		lst1 = vInt.addRunToList(lst1, dummy2, this.cmpInt);
		lst1 = vInt.addRunToList(lst1, dummy1, this.cmpInt);
		lst1 = vInt.addRunToList(lst1, dummy2, this.cmpInt);
		lst1 = vInt.addRunToList(lst1, dummy1, this.cmpInt);
		lst1 = vInt.addRunToList(lst1, dummy2, this.cmpInt);
		lst1 = vInt.addRunToList(lst1, dummy1, this.cmpInt);

		Assert.assertTrue(this.lst2.equals(dummy1));
		Assert.assertTrue(this.lst3.equals(dummy2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addRunToListCmpNull() {
		vInt.addRunToList(this.listAscInt, lst1, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addRunToListDummyNull() {
		vInt.addRunToList(this.listAscInt, null, this.cmpInt);
	}

	@Test
	public void cloneLst() {
		ListItem<Integer> clonedLst = vInt.clone(this.listAscInt);
		Assert.assertEquals(0, clonedLst.key, 0);
		Assert.assertEquals(1, clonedLst.next.key, 0);
		Assert.assertEquals(2, clonedLst.next.next.key, 0);
		Assert.assertEquals(3, clonedLst.next.next.next.key, 0);
		Assert.assertEquals(4, clonedLst.next.next.next.next.key, 0);
		Assert.assertEquals(5, clonedLst.next.next.next.next.next.key, 0);
		Assert.assertEquals(6, clonedLst.next.next.next.next.next.next.key, 0);
		Assert.assertEquals(7, clonedLst.next.next.next.next.next.next.next.key, 0);
		Assert.assertEquals(8, clonedLst.next.next.next.next.next.next.next.next.key, 0);
		Assert.assertEquals(9, clonedLst.next.next.next.next.next.next.next.next.next.key, 0);
		Assert.assertEquals(null, clonedLst.next.next.next.next.next.next.next.next.next.next);
	}

	@Test(expected = IllegalArgumentException.class)
	public void divideByRunsIntoArrayAndListCmpNull() {
		vInt.divideByRunsIntoArrayAndList(lst1, null, Integer.class);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void divideByRunsIntoArrayAndList() {
		ListItem<Object> result = vInt.divideByRunsIntoArrayAndList(lst1, this.cmpInt, Integer.class);

		Assert.assertTrue("Fehler123456", this.lst2.next.equals(((ListItem<Integer>) result.key).next));
		Assert.assertArrayEquals("Fehler0000", new Integer[] { 1, 20, 0, 4 }, (Object[]) result.next.key);
	}

	@Test
	public void divideByRunsIntoListAndList() {
		ListItem<Integer> d1 = new ListItem<Integer>(101), d2 = new ListItem<Integer>(102);
		ListItem<ListItem<Integer>> result = vInt.divideByRunsIntoListAndList(lst1, d1, d2, this.cmpInt);

		Assert.assertTrue(this.lst2.equals(result.key));
		Assert.assertTrue(this.lst3.equals(result.next.key));
	}

	// ********************************************** HELPER ********************************************** \\

	@Before
	public void buildList() {
		// lst1 : 1, 2, 1, 20, 10, 0, 4
		this.lst1 = new ListItem<Integer>(1);
		lst1.next = new ListItem<Integer>(2);
		lst1.next.next = new ListItem<Integer>(1);
		lst1.next.next.next = new ListItem<Integer>(20);
		lst1.next.next.next.next = new ListItem<Integer>(10);
		lst1.next.next.next.next.next = new ListItem<Integer>(0);
		lst1.next.next.next.next.next.next = new ListItem<Integer>(4);

		// lst2 : 101, 1, 2, 10
		this.lst2 = new ListItem<Integer>(101);
		lst2.next = new ListItem<Integer>(1);
		lst2.next.next = new ListItem<Integer>(2);
		lst2.next.next.next = new ListItem<Integer>(10);

		// lst3 : 102, 1, 20, 0, 4
		this.lst3 = new ListItem<Integer>(102);
		lst3.next = new ListItem<Integer>(1);
		lst3.next.next = new ListItem<Integer>(20);
		lst3.next.next.next = new ListItem<Integer>(0);
		lst3.next.next.next.next = new ListItem<Integer>(4);
	}

	/**
	 * Creates a testList with Integers from 0 to 9
	 */
	@Before
	public void buildTestListAscInt() {
		listAscInt = new ListItem<Integer>(0);
		ListItem<Integer> p = listAscInt;
		for (int i = 1; i < 10; i++) {
			p.next = new ListItem<Integer>(i);
			p = p.next;
		}
	}

	/**
	 * Creates a testArray with Integers from 0 to 9
	 */
	@Before
	public void buildTestArrayAscInt() {
		arrayAscInt = new Integer[10];
		for (int i = 0; i < 10; i++)
			arrayAscInt[i] = i;
	}
}
