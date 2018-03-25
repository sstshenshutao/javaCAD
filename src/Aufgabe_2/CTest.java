package Aufgabe_2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Assert;
import org.junit.Test;

import Aufgabe_1.C.C;
import Aufgabe_1.C.ComparatorRange;
import Aufgabe_1.V.ComparatorInteger;
import data.ListItem;
import model.GeometricModelElement;
import model.Point;
import model.angled.AngledGeometricElement;
import model.angled.SquareElement;
import model.angled.TrapezoidElement;
import model.angled.TriangleElement;
import model.round.CircleElement;
import model.round.EllipseElement;

public class CTest {

	/*****************************************************************
	 * combine
	 *****************************************************************/
	private C<Integer> c = new C<>();
	private C<GeometricModelElement> cGeo = new C<>();
	private Integer[] arr1= {1,2,3,4,5};
	private Integer[] arr2= {4,5,6,7,8};
	private Integer[] arr11= {1,7,13,24,35};
	private Integer[] arr12= {4,15,16,27,38};
	@Test
	public void combine_Test_1() {
		//test with 2 same number
		Integer[] arr3= c.combine(arr1, arr2, new ComparatorInteger(), Integer.class);
		Integer[] arrCompare= {1,2,3,4,4,5,5,6,7,8};
		for(int i=0;i<arr3.length;i++) {
			Assert.assertEquals(arr3[i].intValue(),arrCompare[i].intValue());
		}
	}

	@Test
	public void combine_Test_2() {
		Assert.assertNull(c.combine(null, arr2, new ComparatorInteger(), Integer.class));
	}

	@Test
	public void combine_Test_3() {
		//test with 2 interactive list
		Integer[] arr3= c.combine(arr11, arr12, new ComparatorInteger(), Integer.class);
		Integer[] arrCompare= {1,4,7,13,15,16,24,27,35,38};
		for(int i=0;i<arr3.length;i++) {
			Assert.assertEquals(arr3[i].intValue(),arrCompare[i].intValue());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void combine_Test_4() {
		Integer[] arr3= {1,7,4,9,5};
		c.combine(arr1, arr3, new ComparatorInteger(), Integer.class);
	}

	/*****************************************************************
	 * insertHead
	 *****************************************************************/

	@Test
	public void insertHead_Test_1() {
		//insert a single node
		//tested
		ListItem<Integer> threeInt= new ListItem<>(3);
		ListItem<Integer> twoInt= new ListItem<>(2);
		ListItem<Integer> oneInt= new ListItem<>(1);
		threeInt.next= twoInt;
		twoInt.next= oneInt;
		ListItem<Integer> fourInt= new ListItem<>(4);
		ListItem<Integer> newlst = c.insertHead(threeInt, fourInt);
		//expect
		ListItem<Integer> expectLst= new ListItem<>(4);
		expectLst.insert(3);
		expectLst.insert(2);
		expectLst.insert(1);

		Assert.assertTrue(expectLst.equals(newlst));
	}

	@Test
	public void insertHead_Test_2() {
		//insert a single node to a null-list(create a  new list)
		ListItem<Integer> head= new ListItem<>(3);
		ListItem<Integer> newlst =c.insertHead(null, head);
		Assert.assertTrue(newlst.equals(new ListItem<>(3)));
	}

	@Test
	public void insertHead_Test_3() {
		//insert a whole List
		//tested:
		ListItem<Integer> threeInt= new ListItem<>(3);
		ListItem<Integer> twoInt= new ListItem<>(2);
		ListItem<Integer> oneInt= new ListItem<>(1);
		threeInt.next= twoInt;
		twoInt.next= oneInt;
		//threeInt=3 2 1
		
		ListItem<Integer> fourInt= new ListItem<>(4);
		ListItem<Integer> fiveInt= new ListItem<>(5);
		fourInt.next=fiveInt;
		//fourInt=4 5
		
		ListItem<Integer> newlst = c.insertHead(threeInt, fourInt);//4 5 3 2 1
		//expect:
		ListItem<Integer> expectLst= new ListItem<>(4);
		expectLst.insert(5);
		expectLst.insert(3);
		expectLst.insert(2);
		expectLst.insert(1);

		Assert.assertTrue(expectLst.equals(newlst));
	}

	/*****************************************************************
	 * removeLast
	 *****************************************************************/

	@Test
	public void removeLast_Test_1() {
		//null-list
		Assert.assertNull(c.removeLast(null));
	}

	@Test
	public void removeLast_Test_2() {
		//normal list
//		test:
		ListItem<Integer> oriLst= new ListItem<>(4);
		oriLst.insert(5);
		oriLst.insert(3);
		oriLst.insert(2);
		oriLst.insert(1);
		ListItem<Integer> newLst=c.removeLast(oriLst);//4 5 3 2
		//expect
		ListItem<Integer> expectLst= new ListItem<>(4);
		expectLst.insert(5);
		expectLst.insert(3);
		expectLst.insert(2);
		
		Assert.assertTrue(expectLst.equals(newLst));

	}

	@Test
	public void removeLast_Test_3() {
		//just one node:
		ListItem<Integer> oriLst= new ListItem<>(4);
		Assert.assertNull(c.removeLast(oriLst));//null
	}

	/*****************************************************************
	 * invert
	 *****************************************************************/

	@Test
	public void invert_Test_1() {
		//just one node:
		ListItem<Integer> oriLst= new ListItem<>(4);
		ListItem<Integer> newLst= c.invert(oriLst);
		assertTrue(newLst.equals(new ListItem<Integer>(4)));
	}

	@Test
	public void invert_Test_2() {
		//normal list
//		test:
		ListItem<Integer> oriLst= new ListItem<>(4);
		oriLst.insert(5);
		oriLst.insert(3);
		oriLst.insert(2);
		oriLst.insert(1);
		ListItem<Integer> newLst=c.invert(oriLst);//12354
		//expect
		ListItem<Integer> expectLst= new ListItem<>(1);
		expectLst.insert(2);
		expectLst.insert(3);
		expectLst.insert(5);
		expectLst.insert(4);
		assertTrue(newLst.equals(expectLst));
		
	}

	@Test
	public void invert_Test_3() {
		//test:
		EllipseElement one = new EllipseElement(), three = new EllipseElement();
		CircleElement two = new CircleElement();
		ListItem<GeometricModelElement> _one = new ListItem<>(one), _two = new ListItem<>(two),
				_three = new ListItem<>(three);
		_one.next = _two;
		_two.next = _three;
		ListItem<GeometricModelElement> newLst= cGeo.invert(_one);
		//expect:
		ListItem<GeometricModelElement> e_one = new ListItem<>(one), e_two = new ListItem<>(two),
				e_three = new ListItem<>(three);
		e_three.next=e_two;
		e_two.next=e_one;
		assertTrue(e_three.equals(newLst));
	}

	/*****************************************************************
	 * combineLists
	 *****************************************************************/

	@Test
	public void combineLists_Test_1() {
		//|lst1| == |lst2|
		ListItem<Integer> oriLst1= new ListItem<>(5);
		oriLst1.insert(4);
		oriLst1.insert(3);
		oriLst1.insert(2);
		oriLst1.insert(1);
		
		ListItem<Integer> oriLst2= new ListItem<>(15);
		oriLst2.insert(14);
		oriLst2.insert(13);
		oriLst2.insert(12);
		oriLst2.insert(11);
		
		ListItem<Integer> newLst = c.combineLists(oriLst1, oriLst2);
		
		//expect
		ListItem<Integer> expectLst= new ListItem<>(15);
		expectLst.insert(5);
		expectLst.insert(14);
		expectLst.insert(4);
		expectLst.insert(13);
		expectLst.insert(3);
		expectLst.insert(12);
		expectLst.insert(2);
		expectLst.insert(11);
		expectLst.insert(1);
		
		assertTrue(expectLst.equals(newLst));
	}

	@Test
	public void combineLists_Test_2() {
		//|lst1| < |lst2|
		ListItem<Integer> oriLst1= new ListItem<>(5);
		oriLst1.insert(4);
		oriLst1.insert(3);
		
		ListItem<Integer> oriLst2= new ListItem<>(15);
		oriLst2.insert(14);
		oriLst2.insert(13);
		oriLst2.insert(12);
		oriLst2.insert(11);
		oriLst2.insert(10);
		
		ListItem<Integer> newLst = c.combineLists(oriLst1, oriLst2);
		
		//expect
		ListItem<Integer> expectLst= new ListItem<>(15);
		expectLst.insert(5);
		expectLst.insert(14);
		expectLst.insert(4);
		expectLst.insert(13);
		expectLst.insert(3);
		expectLst.insert(12);
		expectLst.insert(11);
		expectLst.insert(10);
		
		assertTrue(expectLst.equals(newLst));
	}

	@Test
	public void combineLists_Test_3() {
		//|lst1| > |lst2|
		ListItem<Integer> oriLst1= new ListItem<>(5);
		oriLst1.insert(4);
		oriLst1.insert(3);
		oriLst1.insert(2);
		oriLst1.insert(1);
		oriLst1.insert(0);

		ListItem<Integer> oriLst2= new ListItem<>(15);
		oriLst2.insert(14);
		oriLst2.insert(13);
		
		ListItem<Integer> newLst = c.combineLists(oriLst1, oriLst2);
		
		//expect
		ListItem<Integer> expectLst= new ListItem<>(15);
		expectLst.insert(5);
		expectLst.insert(14);
		expectLst.insert(4);
		expectLst.insert(13);
		expectLst.insert(3);
		expectLst.insert(2);
		expectLst.insert(1);
		expectLst.insert(0);
		
		assertTrue(expectLst.equals(newLst));
	}

	/*****************************************************************
	 * arrayIntoLists
	 *****************************************************************/

	@Test
	public void arrayIntoLists_Test_1() {
		//array.length==1
		Integer[] arr= {5};
		ListItem<Integer> newLst = c.arrayIntoList(arr);
		
		ListItem<Integer> expectLst= new ListItem<>(5);
		assertTrue(expectLst.equals(newLst));
	}

	@Test
	public void arrayIntoLists_Test_2() {
		//array.length=n; normal test
		Integer[] arr= {5,4,3,2,1};
		ListItem<Integer> newLst = c.arrayIntoList(arr);
		
		ListItem<Integer> expectLst= new ListItem<>(5);
		expectLst.insert(4);
		expectLst.insert(3);
		expectLst.insert(2);
		expectLst.insert(1);
		
		assertTrue(expectLst.equals(newLst));
		
	}

	@Test
	public void arrayIntoLists_Test_3() {
		//test:
		EllipseElement one = new EllipseElement(), three = new EllipseElement();
		CircleElement two = new CircleElement();
		ListItem<GeometricModelElement> _one = new ListItem<>(one), _two = new ListItem<>(two),
				_three = new ListItem<>(three);
		_one.next = _two;
		_two.next = _three;
		GeometricModelElement[] arr= {one,two,three};
		ListItem<GeometricModelElement> newLst= cGeo.arrayIntoList(arr);
		assertTrue(newLst.equals(_one));
	}
	
	@Test
	public void arrayIntoLists_Test_5() {
		//null length array
		Integer[] arr= new Integer[0];
		ListItem<Integer> newLst = c.arrayIntoList(arr);
		assertNull(newLst);
	}

	@Test(expected = IllegalArgumentException.class)
	public void arrayIntoLists_Test_4() {
		Integer[] arr= null;
		c.arrayIntoList(arr);
	}

	/*****************************************************************
	 * ComparatorRange.compare
	 *****************************************************************/
	ComparatorRange cr=new ComparatorRange();
	@Test
	public void compare_Test_1() {
		//two triangle
		AngledGeometricElement elem1 = new TriangleElement();
		elem1.setPoints(new Point[] {new Point(0, 0),new Point(0, 1),new Point(1, 0)}); //range=1+1+sqrt2
		AngledGeometricElement elem2 = new TriangleElement();
		elem2.setPoints(new Point[] {new Point(0, 0),new Point(0, 2),new Point(1, 0)}); //range=1+2+sqrt5
		assertTrue(cr.compare(elem1, elem2) < 0);
	}

	@Test
	public void compare_Test_2() {
		//triangle and trapezoid
		AngledGeometricElement elem1 = new TrapezoidElement();
		elem1.setPoints(new Point[] {new Point(0, 0),new Point(1, 1),new Point(2, 1),new Point(3, 0)}); //range=1+3+2sqrt2
		AngledGeometricElement elem2 = new TriangleElement();
		elem2.setPoints(new Point[] {new Point(0, 0),new Point(0, 2),new Point(1, 0)}); //range=1+2+sqrt5
		assertTrue(cr.compare(elem1, elem2) > 0);
	}

	@Test
	public void compare_Test_3() {
		//Square and triangle
		AngledGeometricElement elem1 = new SquareElement();
		elem1.setPoints(new Point[] {new Point(0, 0),new Point(0, 1),new Point(1, 1),new Point(1, 0)}); //range=4
		AngledGeometricElement elem2 = new TriangleElement();
		elem2.setPoints(new Point[] {new Point(0, 0),new Point(0, 2),new Point(1, 0)}); //range=1+2+sqrt5
		assertTrue(cr.compare(elem1, elem2) < 0);
	}

	/*****************************************************************
	 * mirror
	 *****************************************************************/

	@Test
	public void mirror_Test_1() {
		AngledGeometricElement elem1 = new TrapezoidElement();
		elem1.setPoints(new Point[] {new Point(0, 0),new Point(1, 1),new Point(2, 1),new Point(3, 0)});
		elem1.mirror("x");
		Point[] points = elem1.getPoints();
		assertTrue(points[0].getX()==0 && points[0].getY()==1);
		assertTrue(points[1].getX()==1 && points[1].getY()==0);
		assertTrue(points[2].getX()==2 && points[2].getY()==0);
		assertTrue(points[3].getX()==3 && points[3].getY()==1);
	}

	@Test
	public void mirror_Test_2() {
		AngledGeometricElement elem1 = new SquareElement();
		elem1.setPoints(new Point[] {new Point(0, 0),new Point(0, 1),new Point(1, 1),new Point(1, 0)});
		elem1.mirror("x");
		Point[] points = elem1.getPoints();
		assertTrue(points[0].getX()==0 && points[0].getY()==1);
		assertTrue(points[1].getX()==0 && points[1].getY()==0);
		assertTrue(points[2].getX()==1 && points[2].getY()==0);
		assertTrue(points[3].getX()==1 && points[3].getY()==1);
	}

	@Test
	public void mirror_Test_3() {
		AngledGeometricElement elem1 = new TriangleElement();
		elem1.setPoints(new Point[] {new Point(0, 0),new Point(0, 1),new Point(1, 0)});
		elem1.mirror("y");
		Point[] points = elem1.getPoints();
		assertTrue(points[0].getX()==1 && points[0].getY()==0);
		assertTrue(points[1].getX()==1 && points[1].getY()==1);
		assertTrue(points[2].getX()==0 && points[2].getY()==0);
	}
}