package Aufgabe_2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

import data.ListItem;
import Aufgabe_1.A.A;
import Aufgabe_1.V.ComparatorInteger;
import model.Point;
import model.GeometricModelElement;
import model.angled.AngledGeometricElement;
import model.angled.SquareElement;
import model.angled.RectangleElement;
import model.round.CircleElement;
import model.round.RoundGeometricElement;

public class ATest {

	/*****************************************************************
	 * invertTriples
	 *****************************************************************/
	private A<Integer> a = new A<>();
	private Integer[] arr1 = {1,2,3,4,5,6,7};
	private Integer[] arr2 = {1,2,3};
	private Integer[] arr3 = {1,2};
	private Integer[] arr4 = null;
	
	@Test
	public void invertTriples_Test_1() {
		Integer[] iArr = a.invertTriples(arr1);
        Integer[] eArr = {3,2,1,6,5,4,7};
		
        Assert.assertTrue(eArr.equals(iArr));
	}

	@Test
	public void invertTriples_Test_2() {
		Integer[] iArr = a.invertTriples(arr2);
        Integer[] eArr = {3,2,1};
		
        Assert.assertTrue(eArr.equals(iArr));    
	}

	@Test
	public void invertTriples_Test_3() {
		Integer[] iArr = a.invertTriples(arr3);
        Integer[] eArr = {1,2};
		
        Assert.assertTrue(eArr.equals(iArr));        
	}

	@Test(expected = IllegalArgumentException.class)
	public void invertTriples_Test_4() {
		
		a.invertTriples(arr4);
	}

	/*****************************************************************
	 * insertSingleHead
	 *****************************************************************/

	@Test
	public void insertSingleHead_Test_1() {
		ListItem<Integer> lst= new ListItem<>(4);
		lst.insert(5);
		lst.insert(3);
		
		ListItem<Integer> newLst = a.insertSingleHead(lst,9);
		
		ListItem<Integer> eLst = new ListItem<>(9);
		eLst.insert(4);
		eLst.insert(5);
		eLst.insert(3);
		
		Assert.assertTrue(eLst.equals(newLst));

	}

	@Test
	public void insertSingleHead_Test_2() {
		ListItem<Integer> lst= null;
		
		ListItem<Integer> newLst = a.insertSingleHead(lst,9);
		
		ListItem<Integer> eLst = new ListItem<>(9);
		
		Assert.assertTrue(eLst.equals(newLst));
	}

	@Test
	public void insertSingleHead_Test_3() {
		ListItem<Integer> lst= new ListItem<>(8);
		
		ListItem<Integer> newLst = a.insertSingleHead(lst,9);
		
		ListItem<Integer> eLst = new ListItem<>(8);
		eLst.insert(9);
		
		Assert.assertTrue(eLst.equals(newLst));
	}

	/*****************************************************************
	 * removeElementsEqualX
	 *****************************************************************/

	@Test
	public void removeElementsEqualX_Test_1() {
		ListItem<Integer> lst= new ListItem<>(4);
		lst.insert(5);
		lst.insert(3);
		lst.insert(2);
		
		ListItem<Integer> newLst = a.removeElementsEqualX(lst, 2, intValue());
		
		ListItem<Integer> eLst = new ListItem<>(4);
		eLst.insert(5);
		eLst.insert(3);
		
		Assert.assertTrue(eLst.equals(newLst));
	}
	
	@Test
	public void removeElementsEqualX_Test_2() {
		ListItem<Integer> lst= new ListItem<>(4);
		lst.insert(5);
		lst.insert(3);
		lst.insert(3);
		lst.insert(2);
		
		ListItem<Integer> newLst = a.removeElementsEqualX(lst, 3, intValue());
		
		ListItem<Integer> eLst = new ListItem<>(4);
		eLst.insert(5);
		eLst.insert(2);
		
		Assert.assertTrue(eLst.equals(newLst));
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeElementsEqualX_Test_3() {
		ListItem<Integer> lst= new ListItem<>(8);
		a.removeElementsEqualX(lst, null, intValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeElementsEqualX_Test_4() {
		ListItem<Integer> lst= new ListItem<>(8);
		a.removeElementsEqualX(lst, 5, null);
	}

	/*****************************************************************
	 * ringShiftLeft
	 *****************************************************************/

	@Test
	public void ringShiftLeft_Test_1() {
		ListItem<Integer> lst= new ListItem<>(5);
		lst.insert(4);
		lst.insert(3);
		lst.insert(2);
		
		ListItem<Integer> newLst = a.ringShiftLeft(lst);
		ListItem<Integer> eLst = new ListItem<>(4);
		eLst.insert(3);
		eLst.insert(2);
		eLst.insert(5);
		
		Assert.assertTrue(eLst.equals(newLst));
	}
	
	@Test
	public void ringShiftLeft_Test_2() {
		ListItem<Integer> lst= null;
		
		Assert.assertNull(a.ringShiftLeft(lst));	
	}

	@Test
	public void ringShiftLeft_Test_3() {
		ListItem<Integer> lst= new ListItem<>(5);
		lst.insert(4);
		
		ListItem<Integer> newLst = a.ringShiftLeft(lst);
		ListItem<Integer> eLst = new ListItem<>(4);
		eLst.insert(5);
		
		Assert.assertTrue(eLst.equals(newLst));
	}

	/*****************************************************************
	 * listsInList
	 *****************************************************************/

	@Test
	public void listsInList_Test_1() {
		ListItem<ListItem<Integer>> lsts = new ListItem<ListItem<>>(95);
		lst.insert(35);
		lst.insert(11);
		lst.insert(63);
		
		ListItem<Integer> newLst = a.listsInList(lsts);
		
		ListItem<Integer> eLst = new ListItem<>(95);
		eLst.insert(35);
		eLst.insert(11);
		eLst.insert(63);
		
		assertTrue(eLst.equals(newLst));
		
	}

	@Test
	public void listsInList_Test_2() {
		ListItem<ListItem<Integer>> lsts = new ListItem<ListItem<>>(9);
		lst.insert(8);
		lst.insert(7);
		lst.insert(6);
		lst.insert(5);
		
		ListItem<Integer> newLst = a.listsInList(lsts);
		
		ListItem<Integer> eLst = new ListItem<>(9);
		eLst.insert(8);
		eLst.insert(7);
		eLst.insert(6);
		eLst.insert(5);
		
		assertTrue(eLst.equals(newLst));
		
	}

	@Test
	public void listsInList_Test_3() {
		ListItem<ListItem<Integer>> lsts = new ListItem<ListItem<>>(3);
		lst.insert(2);
		lst.insert(1);
		
		ListItem<Integer> newLst = a.listsInList(lsts);
		
		ListItem<Integer> eLst = new ListItem<>(3);
		eLst.insert(2);
		eLst.insert(1);
		
		assertTrue(eLst.equals(newLst));
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void listsInList_Test_4() {
		ListItem<ListItem<Integer>> lsts = null;
		
		a.listsInList(lsts);
	}

	/*****************************************************************
	 * arrayRunsToListOfLists
	 *****************************************************************/

	@Test
	public void arrayRunsToListOfLists_Test_1() {
		Integer[] arr= {1,2,3};
		
		ListItem<Integer> newLst = a.arrayRunsToListOfLists(arr, intValue());
		ListItem<ListItem<Integer>> elsts = new ListItem<ListItem<>>(1);
		elsts.insert(2);
		elsts.insert(3);
		
		assertTrue(eLsts.equals(newLst));
	}

	@Test
	public void arrayRunsToListOfLists_Test_2() {
		Integer[] arr= {};
		
		Assert.assertNull(a.arrayRunsToListOfLists(arr, intValue()));	
	}

	@Test(expected = IllegalArgumentException.class)
	public void arrayRunsToListOfLists_Test_3() {

		a.arrayRunsToListOfLists(null, intValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void arrayRunsToListOfLists_Test_4() {
		Integer[] arr= {1,2,3};
		
		a.arrayRunsToListOfLists(arr, null);
	}

	/*****************************************************************
	 * ComparatorColor.compare
	 *****************************************************************/

	@Test
	public void compare_Test_1() {
		AngledGeometricElement s1 = new RectangleElement();
		s1.changeColorCode(3);
		AngledGeometricElement s2 = new RectangleElement();
		Comparator s = new ComparatorColor();
		 
		Assert.assertTrue(s.compare(s1, s2) > 0);

	}

	@Test
	public void compare_Test_2() {
		AngledGeometricElement s1 = new RectangleElement();
		AngledGeometricElement s2 = new RectangleElement();
		Comparator s = new ComparatorColor();
		
		Assert.assertTrue(s.compare(s1, s2) == 0);

	}

	@Test
	public void compare_Test_3() {
		AngledGeometricElement s1 = new RectangleElement();
		RoundGeometricElement c1 = new CircleElement();
		c1.changeColorCode(6);
		s1.changeColorCode(3);
		Comparator s = new ComparatorColor();
		  
		Assert.assertTrue(s.compare(s1, c1) < 0);

	}

	/*****************************************************************
	 * move
	 *****************************************************************/

	@Test
	public void move_Test_1() {
		Point[] points = new Point[]{new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 0)};
		AngledGeometricElement s1 = new SquareElement();
		s1.setPoints(points);
		s1.move(1, 1);
		Point[] np = s1.getPoints();
		  
		Assert.assertTrue(np[0].getX() == 1 && np[0].getY() == 1);
		Assert.assertTrue(np[1].getX() == 1 && np[1].getY() == 2);
		Assert.assertTrue(np[2].getX() == 2 && np[2].getY() == 2);
		Assert.assertTrue(np[3].getX() == 2 && np[3].getY() == 1);

	}

	@Test
	public void move_Test_2() {
		Point[] points = new Point[]{new Point(0, 0), new Point(0, 1), new Point(2, 1), new Point(2, 0)};
		AngledGeometricElement s1 = new RectangleElement();
		s1.setPoints(points);
		s1.move(-1, 0);
		Point[] np = s1.getPoints();
		
		Assert.assertTrue(np[0].getX() == -1 && np[0].getY() == 0);
		Assert.assertTrue(np[1].getX() == -1 && np[1].getY() == 1);
		Assert.assertTrue(np[2].getX() == 1 && np[2].getY() == 1);
		Assert.assertTrue(np[3].getX() == 1 && np[3].getY() == 0);

	}

	@Test
	public void move_Test_3() {
		Point[] points = new Point[]{new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 0)};
		AngledGeometricElement s1 = new SquareElement();
		s1.setPoints(points);
		s1.move(0, 0);
		Point[] np = s1.getPoints();
		 
		Assert.assertTrue(np[0].getX() == 0 && np[0].getY() == 0);
		Assert.assertTrue(np[1].getX() == 0 && np[1].getY() == 1);
		Assert.assertTrue(np[2].getX() == 1 && np[2].getY() == 1);
		Assert.assertTrue(np[3].getX() == 1 && np[3].getY() == 0);
		
	}
}