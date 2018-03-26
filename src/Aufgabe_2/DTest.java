package Aufgabe_2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

import data.ListItem;
import Aufgabe_1.D.D;
import Aufgabe_1.D.ComparatorRadius;
import Aufgabe_1.V.ComparatorInteger;
import model.GeometricModelElement;
import model.Point;
import model.angled.AngledGeometricElement;
import model.angled.SquareElement;
import model.angled.RectangleElement;
import model.round.CircleElement;

public class DTest {
 
	/*****************************************************************
	 * switchElements
	 *****************************************************************/
	private D<Integer> d = new D<>();
	private Integer[] arr1 = {54, 86, 234, 155};
	private Integer[] arr2 = null;
	
	@Test
	public void switchElements_Test_1() {
		Integer[] sArr = d.switchElements(arr1, 0, 1);
		Integer[] eArr = {86, 54, 234, 155};
		
		Assert.assertTrue(eArr.equals(sArr));
	}

	@Test
	public void switchElements_Test_2() {
		Integer[] sArr = d.switchElements(arr1, 2, 3);
		Integer[] eArr = {54, 86, 155, 234};
		
		Assert.assertTrue(eArr.equals(sArr));
	}

	@Test(expected = IllegalArgumentException.class)
	public void switchElements_Test_3() {
		
		d.switchElements(arr2, 0, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void switchElements_Test_4() {
		
		d.switchElements(arr1, 7, 1);
	}

	/*****************************************************************
	 * insertLast
	 *****************************************************************/

	@Test
	public void insertLast_Test_1() {
		ListItem<Integer> oneInt = new ListItem<>(3);
		ListItem<Integer> twoInt = new ListItem<>(7);
		ListItem<Integer> threeInt = new ListItem<>(7);
		oneInt.next= twoInt;
		twoInt.next= threeInt;
		ListItem<Integer> fourInt = new ListItem<>(4);
		ListItem<Integer> newlst = d.insertLast(oneInt, fourInt);
		
		ListItem<Integer> eLst = new ListItem<>(3);
		eLst.insert(7);
		eLst.insert(7);
		eLst.insert(4);

		Assert.assertTrue(eLst.equals(newlst));
}

	@Test
	public void insertLast_Test_2() {
		ListItem<Integer> oneInt = new ListItem<>(3);
		ListItem<Integer> twoInt = new ListItem<>(7);
		ListItem<Integer> threeInt = new ListItem<>(7);
		oneInt.next= twoInt;
		twoInt.next= threeInt;
		
		ListItem<Integer> fourInt = new ListItem<>(4);
		ListItem<Integer> fiveInt = new ListItem<>(5);
		fourInt.next=fiveInt;
		ListItem<Integer> newlst = d.insertLast(oneInt, fourInt);
		
		ListItem<Integer> eLst = new ListItem<>(3);
		eLst.insert(7);
		eLst.insert(7);
		eLst.insert(4);
		eLst.insert(5);

		Assert.assertTrue(eLst.equals(newlst));
	}

	@Test
	public void insertLast_Test_3() {
		ListItem<Integer> last = new ListItem<>(1);
		ListItem<Integer> newlst = d.insertLast(null, last);
		Assert.assertTrue(newlst.equals(new ListItem<>(1)));
	}

	/*****************************************************************
	 * removeSecMaxElement
	 *****************************************************************/

	@Test
	public void removeSecMaxElement_Test_1() {
		ListItem<Integer> lst = new ListItem<>(11);
		lst.insert(22);
		lst.insert(33);
		lst.insert(44);

		ListItem<Integer> newLst = d.removeSecMaxElement(lst,new ComparatorInteger());
		
		ListItem<Integer> eLst = new ListItem<>(11);
		eLst.insert(22);
		eLst.insert(44);
		
		Assert.assertTrue(eLst.equals(newLst));

}

	@Test
	public void removeSecMaxElement_Test_2() {
		ListItem<Integer> lst = new ListItem<>(11);
		lst.insert(22);
		lst.insert(22);
		lst.insert(44);

		ListItem<Integer> newLst = d.removeSecMaxElement(lst,new ComparatorInteger());
		
		ListItem<Integer> eLst = new ListItem<>(11);
		eLst.insert(44);
		
		Assert.assertTrue(eLst.equals(newLst));
	}

	@Test
	public void removeSecMaxElement_Test_3() {
	
	Assert.assertNull(d.removeSecMaxElement(null,new ComparatorInteger()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeSecMaxElement_Test_4() {
		ListItem<Integer> lst = new ListItem<>(11);
		lst.insert(22);
		lst.insert(33);
		lst.insert(44);
		
		d.removeSecMaxElement(lst,null);
	}

	/*****************************************************************
	 * invertTriples
	 *****************************************************************/

	@Test
	public void invertTriples_Test_1() {
		ListItem<Integer> lst = new ListItem<>(55);
		oriLst.insert(44);
		oriLst.insert(33);
		oriLst.insert(22);
		ListItem<Integer> newLst = d.invertTriples(lst);
		
		ListItem<Integer> eLst = new ListItem<>(33);
		eLst.insert(44);
		eLst.insert(55);
		eLst.insert(22);
		assertTrue(newLst.equals(eLst));
	
	}

	@Test
	public void invertTriples_Test_2() {
		ListItem<Integer> lst = new ListItem<>(55);
		oriLst.insert(44);
		oriLst.insert(33);
		oriLst.insert(22);
		oriLst.insert(11);
		oriLst.insert(66);
		ListItem<Integer> newLst = d.invertTriples(lst);
		
		ListItem<Integer> eLst = new ListItem<>(33);
		eLst.insert(44);
		eLst.insert(55);
		eLst.insert(66);
		eLst.insert(11);
		eLst.insert(22);
		assertTrue(newLst.equals(eLst));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invertTriples_Test_3() {
		
		d.invertTriples(null);
    }

	/*****************************************************************
	 * divideAlternatinglyIntoLists
	 *****************************************************************/

	@Test
	public void divideAlternatinglyIntoLists_Test_1() {
		ListItem<Integer> lst = new ListItem<>(95);
		lst.insert(5);
		lst.insert(35);
		lst.insert(2);
		lst.insert(11);
		lst.insert(24);
		lst.insert(63);
		lst.insert(77);
		lst.insert(66);
		
		ListItem<ListItem<Integer>> newLst = d.divideAlternatinglyIntoLists(lst);
		
		ListItem<ListItem<Integer>> eLst = new ListItem<ListItem<>>(95);
		eLst.insert(35);
		eLst.insert(11);
		eLst.insert(63);
		eLst.insert(66);
		eLst.insert(5);
		eLst.insert(2);
		eLst.insert(24);
		eLst.insert(77);
		
		assertTrue(eLst.equals(newLst));
		
	}

	@Test
	public void divideAlternatinglyIntoLists_Test_2() {
		ListItem<Integer> lst= new ListItem<>(1);
		lst.insert(2);
		lst.insert(3);
		lst.insert(4);
		
		ListItem<ListItem<Integer>> newLst = d.divideAlternatinglyIntoLists(lst);
		
		ListItem<ListItem<Integer>> eLst = new ListItem<ListItem<>>(1);
		eLst.insert(3);
		eLst.insert(2);
		eLst.insert(4);
		
		assertTrue(eLst.equals(newLst));	
	}

	@Test
	public void divideAlternatinglyIntoLists_Test_3() {
		ListItem<Integer> lst= new ListItem<>(5);
	    ListItem<ListItem<Integer>> newLst = d.divideAlternatinglyIntoLists(lst);
		ListItem<ListItem<Integer>> eLst = new ListItem<ListItem<>>(5);
		
		assertTrue(eLst.equals(newLst));	

	}

	/*****************************************************************
	 * listIntoArray
	 *****************************************************************/

	@Test
	public void listIntoArray_Test_1() {
		ListItem<Integer> lst= new ListItem<>(67);
        Integer[] arr= d.listIntoArray(lst, intValue());
		
		Integer[] eArr= {67};
		
		for(int i=0;i<arr.length;i++) {
			Assert.assertEquals(arr[i].intValue(),eArr[i].intValue());
		}
	}

	@Test
	public void listIntoArray_Test_2() {
		ListItem<Integer> lst= new ListItem<>(5);
		expectLst.insert(4);
		expectLst.insert(3);
		expectLst.insert(2);
		expectLst.insert(1);
		
		Integer[] arr= d.listIntoArray(lst, intValue());
		Integer[] eArr= {5,4,3,2,1};
		
		
		for(int i=0;i<arr.length;i++) {
			Assert.assertEquals(arr[i].intValue(),eArr[i].intValue());
		}	
	}

	@Test(expected = IllegalArgumentException.class)
	public void listIntoArray_Test_3() {
		ListItem<Integer> lst = null;
		
		d.listIntoArray(lst, intValue());
	}


	@Test(expected = IllegalArgumentException.class)
	public void listIntoArray_Test_4() {
		ListItem<Integer> lst= new ListItem<>(5);
		expectLst.insert(4);
		expectLst.insert(3);
		expectLst.insert(2);
		expectLst.insert(1);
		
		d.listIntoArray(lst, null);
	}

	/*****************************************************************
	 * ComparatorRadius.compare
	 *****************************************************************/

	@Test
	public void compare_Test_1() {
		CircleElement c1 = new CircleElement();
		c1.setHeight(2);
		CircleElement c2 = new CircleElement();
		c2.setHeight(2);
		ComparatorRadius cmp = new ComparatorRadius();
	
		Assert.assertEquals(0, cmp.compare(c1, c2));
	}

	@Test
	public void compare_Test_2() {
		CircleElement c1 = new CircleElement();
		c1.setStartPoint(new Point(1, 1));
		c1.setHeight(2);
		CircleElement c2 = new CircleElement();
		c2.setHeight(12);
		ComparatorRadius cmp = new ComparatorRadius();
		
		Assert.assertEquals(-1, cmp.compare(c1, c2));

	}

	@Test
	public void compare_Test_3() {
		CircleElement c1 = new CircleElement();
		c1.setStartPoint(new Point(1, 1));
		c1.setHeight(2);
		CircleElement c2 = new CircleElement();
		c2.setWidth(1);
		ComparatorRadius cmp = new ComparatorRadius();
		 
		Assert.assertEquals(1, cmp.compare(c1, c2));

	}

	/*****************************************************************
	 * rotate
	 *****************************************************************/

	@Test
	public void rotate_Test_1() {
	    AngledGeometricElement s1 = new SquareElement();
		Point[] points = new Point[]{new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 0)};
		s1.setPoints(points);
		s1.rotate(0);
		Point[] newp = s1.getPoints();
		  
		Assert.assertTrue(newp[0].getX() == 0);
		Assert.assertTrue(newp[2].getY() == 1);

	}

	@Test
	public void rotate_Test_2() {
		AngledGeometricElement s1 = new RectangleElement();
		Point[] points = new Point[]{new Point(0, 0), new Point(0, 4), new Point(2, 4), new Point(2, 0)};
		s1.setPoints(points);
		s1.rotate(90);
		Point[] newp = s1.getPoints();
		  
		Assert.assertTrue(newp[0].getX() == -1);
		Assert.assertTrue(newp[2].getY() == 1);

	}

	@Test
	public void rotate_Test_3() {
		 AngledGeometricElement s1 = new SquareElement();
		 Point[] points = new Point[]{new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 0)};
		 s1.setPoints(points);
		 s1.rotate(3600);
		 Point[] newp = s1.getPoints();
		 
		 Assert.assertTrue(newp[0].getX() == 0);
		 Assert.assertTrue(newp[2].getY() == 1);

	}
}