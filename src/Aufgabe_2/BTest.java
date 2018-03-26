package Aufgabe_2;

import org.junit.Assert;
import org.junit.Test;

import Aufgabe_1.B.B;
import Aufgabe_1.B.ComparatorSurface;
import Aufgabe_1.V.ComparatorInteger;
import data.ListItem;
import model.GeometricModelElement;
import model.Point;
import model.angled.AngledGeometricElement;
import model.angled.HexagonElement;
import model.angled.SquareElement;
import model.round.CircleElement;
import model.round.EllipseElement;

public class BTest {
	private B<GeometricModelElement> B = new B<>();
	private B<Integer> A = new B<>();
	/*****************************************************************
	 * rotateQuadrupleLeft
	 *****************************************************************/

	@Test
	public void rotateQuadrupleLeft_Test_1() {
		EllipseElement a1 = new EllipseElement();
		EllipseElement a2 = new EllipseElement();
		EllipseElement a3 = new EllipseElement();
		EllipseElement a4 = new EllipseElement();
		GeometricModelElement[] arr = new GeometricModelElement[]{a1, a2, a3, a4};
		GeometricModelElement[] arr1 = new GeometricModelElement[]{a2, a3, a4, a1};
		B.rotateQuadrupleLeft(arr);
		Assert.assertEquals(arr1[0], arr[0]);
		Assert.assertEquals(arr1[1], arr[1]);
		Assert.assertEquals(arr1[2], arr[2]);
		Assert.assertEquals(arr1[3], arr[3]);
	}

	@Test
	public void rotateQuadrupleLeft_Test_2() {
		EllipseElement a1 = new EllipseElement();
		EllipseElement a2 = new EllipseElement();
		GeometricModelElement[] arr = new GeometricModelElement[]{a1, a2};
		B.rotateQuadrupleLeft(arr);
		Assert.assertEquals(a1, arr[0]);
		Assert.assertEquals(a2, arr[1]);
	}

	@Test
	public void rotateQuadrupleLeft_Test_3() {
		EllipseElement a1 = new EllipseElement();
		EllipseElement a2 = new EllipseElement();
		EllipseElement a3 = new EllipseElement();
		EllipseElement a4 = new EllipseElement();
		CircleElement c1 = new CircleElement();
		CircleElement c2 = new CircleElement();
		CircleElement c3 = new CircleElement();
		GeometricModelElement[] arr = new GeometricModelElement[]{a1, a2, a3, a4, c1, c2, c3};
		B.rotateQuadrupleLeft(arr);
		Assert.assertEquals(a2, arr[0]);
		Assert.assertEquals(a3, arr[1]);
		Assert.assertEquals(a4, arr[2]);
		Assert.assertEquals(a1, arr[3]);
		Assert.assertEquals(c1, arr[4]);
		Assert.assertEquals(c2, arr[5]);
		Assert.assertEquals(c3, arr[6]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void rotateQuadrupleLeft_Test_4() {
		B.rotateQuadrupleLeft(null);
	}

	/*****************************************************************
	 * insertSingle
	 *****************************************************************/

	@Test
	public void insertSingle_Test_1() {
		int a1 = 1;
		int a2 = 2;
		int a3 = 3;
		int a4 = 5;
		int b1 = 4;
		ListItem<Integer> lst = new ListItem<>(a1);
		lst.insert(a2);
		lst.insert(a3);
		lst.insert(a4);
		ListItem<Integer> nlst = A.insertSingle(lst, b1, new ComparatorInteger());
		Assert.assertEquals((Integer) 1, nlst.key);
		Assert.assertEquals((Integer) 4, nlst.get(4));
	}

	@Test
	public void insertSingle_Test_2() {
		int a1 = 1;
		ListItem<Integer> lst = new ListItem<>(null);
		ListItem<Integer> nlst = A.insertSingle(lst, a1, new ComparatorInteger());
	}

	@Test
	public void insertSingle_Test_3() {

	}

	@Test(expected = IllegalArgumentException.class)
	public void insertSingle_Test_4() {
		EllipseElement a1 = new EllipseElement();
		ListItem<GeometricModelElement> lst = new ListItem<>(a1);
		ListItem<GeometricModelElement> nlst = B.insertSingle(lst, a1, null);
	}

	/*****************************************************************
	 * removeHead
	 *****************************************************************/

	@Test
	public void removeHead_Test_1() {
		EllipseElement a1 = new EllipseElement();
		ListItem<GeometricModelElement> lst = new ListItem<>(a1);
		Assert.assertEquals(null, B.removeHead(lst));
	}

	@Test
	public void removeHead_Test_2() {
		ListItem<GeometricModelElement> lst = null;
		Assert.assertEquals(null, B.removeHead(lst));
	}

	@Test
	public void removeHead_Test_3() {
		EllipseElement a1 = new EllipseElement();
		EllipseElement a2 = new EllipseElement();
		ListItem<GeometricModelElement> lst = new ListItem<>(a1);
		lst.insert(a2);
		Assert.assertEquals(a2, B.removeHead(lst).key);
	}

	/*****************************************************************
	 * ringShiftRight
	 *****************************************************************/

	@Test
	public void ringShiftRight_Test_1() {
		EllipseElement a1 = new EllipseElement();
		EllipseElement a2 = new EllipseElement();
		ListItem<GeometricModelElement> lst = new ListItem<>(a1);
		lst.insert(a2);
		lst.insert(a1);
		ListItem<GeometricModelElement> nlst = B.ringShiftRight(lst);
		Assert.assertEquals(a1, nlst.key);
		Assert.assertEquals(a1, nlst.next.key);
		Assert.assertEquals(a2, nlst.next.next.key);
	}

	@Test
	public void ringShiftRight_Test_2() {
		EllipseElement a1 = new EllipseElement();
		ListItem<GeometricModelElement> lst = new ListItem<>(a1);
		ListItem<GeometricModelElement> nlst = B.ringShiftRight(lst);
		Assert.assertEquals(a1, nlst.key);
		Assert.assertEquals(null, nlst.next);
	}

	@Test
	public void ringShiftRight_Test_3() {
		ListItem<GeometricModelElement> lst = null;
		ListItem<GeometricModelElement> nlst = B.ringShiftRight(lst);
		Assert.assertEquals(null, nlst);
	}

	/*****************************************************************
	 * listInLists
	 *****************************************************************/

	@Test
	public void listInLists_Test_1() {
		EllipseElement a1 = new EllipseElement();
		EllipseElement a2 = new EllipseElement();
		ListItem<GeometricModelElement> lst = new ListItem<>(a1);
		lst.insert(a2);
		lst.insert(a1);
		Assert.assertEquals(a1, B.listInLists(lst).key.key);
		Assert.assertEquals(a2, B.listInLists(lst).next.key.key);
		Assert.assertEquals(a1, B.listInLists(lst).next.next.key.key);
	}

	@Test
	public void listInLists_Test_2() {
		EllipseElement a1 = new EllipseElement();
		ListItem<GeometricModelElement> lst = new ListItem<>(a1);
		Assert.assertEquals(a1, B.listInLists(lst).key.key);
		Assert.assertEquals(null, B.listInLists(lst).key.next);
		Assert.assertEquals(null, B.listInLists(lst).next);
	}

	@Test
	public void listInLists_Test_3() {
		ListItem<GeometricModelElement> lst = null;
		Assert.assertEquals(null, B.listInLists(lst));
	}

	/*****************************************************************
	 * selectType
	 *****************************************************************/

	@Test
	public void selectType_Test_1() {
		int a1 = 1;
		int a2 = 2;
		int a3 = 3;
		int a4 = 4;
		ListItem<Integer> lst = new ListItem<>(a1);
		lst.insert(a2);
		lst.insert(a3);
		lst.insert(a4);
		Integer[] arr = A.selectType(lst, Integer.class);
		Assert.assertEquals((Integer) a1, arr[0]);
		Assert.assertEquals((Integer) a2, arr[1]);
		Assert.assertEquals((Integer) a3, arr[2]);
		Assert.assertEquals((Integer) a4, arr[3]);
	}

	@Test
	public void selectType_Test_2() {
		EllipseElement b1 = new EllipseElement();
		EllipseElement b2 = new EllipseElement();
		EllipseElement b3 = new EllipseElement();
		CircleElement c1 = new CircleElement();
		CircleElement c2 = new CircleElement();
		ListItem<GeometricModelElement> lst1 = new ListItem<>(b1);
		lst1.insert(c1);
		lst1.insert(b2);
		lst1.insert(c2);
		lst1.insert(b3);
		GeometricModelElement[] arr = B.selectType(lst1, EllipseElement.class).clone();
		Assert.assertEquals(b1, arr[0]);
		Assert.assertEquals(b2, arr[1]);
		Assert.assertEquals(b3, arr[2]);
	}

	@Test
	public void selectType_Test_3() {
		CircleElement c1 = new CircleElement();
		CircleElement c2 = new CircleElement();
		ListItem<GeometricModelElement> lst1 = new ListItem<>(c1);
		lst1.insert(c2);
		GeometricModelElement[] arr = B.selectType(lst1, EllipseElement.class).clone();
		Assert.assertNull(arr);
	}

	@Test(expected = IllegalArgumentException.class)
	public void selectType_Test_4() {
		Integer[] t = A.selectType(null, Integer.class);
	}

	/*****************************************************************
	 * ComparatorSurface.compare
	 *****************************************************************/

	@Test
	public void compare_Test_1() {
		CircleElement c1 = new CircleElement();
		c1.setWidth(3.0);
		CircleElement c2 = new CircleElement();
		c2.setHeight(2.0);
		ComparatorSurface cmp = new ComparatorSurface();
		Assert.assertEquals(1, cmp.compare(c1, c2));
	}

	@Test
	public void compare_Test_2() {
		CircleElement c1 = new CircleElement();
		c1.setWidth(1.0);
		CircleElement c2 = new CircleElement();
		c2.setHeight(2.0);
		ComparatorSurface cmp = new ComparatorSurface();
		Assert.assertEquals(-1, cmp.compare(c1, c2));
	}

	@Test
	public void compare_Test_3() {
		CircleElement c1 = new CircleElement();
		c1.setWidth(2.0);
		CircleElement c2 = new CircleElement();
		ComparatorSurface cmp = new ComparatorSurface();
		Assert.assertEquals(1, cmp.compare(c1, c2));
	}

	/*****************************************************************
	 * scale
	 *****************************************************************/

	@Test
	public void scale_Test_1() {
		Point[] points = new Point[]{new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 0)};
		SquareElement s = new SquareElement();
		s.setPoints(points);
		s.scale(1);
		Point[] test = s.getPoints();
		Assert.assertTrue(test[0].getX() == 0 && test[0].getY() == 0);
		Assert.assertTrue(test[1].getX() == 0 && test[1].getY() == 1);
		Assert.assertTrue(test[2].getX() == 1 && test[2].getY() == 1);
		Assert.assertTrue(test[3].getX() == 1 && test[3].getY() == 0);
	}

	@Test
	public void scale_Test_2() {
		Point[] points = new Point[]{new Point(0, 0), new Point(0, 2), new Point(2, 2)};
		AngledGeometricElement s = new TriangleElement();
		s.setPoints(points);
		s.scale(2);
		Point[] test = s.getPoints();
		Assert.assertTrue(test[0].getX() == -1 && test[0].getY() == -1);
		Assert.assertTrue(test[1].getX() == -1 && test[1].getY() == 3);
		Assert.assertTrue(test[2].getX() == 3 && test[2].getY() == 3);
	}

	@Test
	public void scale_Test_3() {
		Point[] points = new Point[]{new Point(0, 0), new Point(0, 4), new Point(2, 4), new Point(2, 0)};
		AngledGeometricElement s = new HexagonElement();
		s.setPoints(points);
		s.scale(0);
		Point[] test = s.getPoints();
		Assert.assertTrue(test[0].getX() == 1 && test[0].getY() == 2);
		Assert.assertTrue(test[1].getX() == 1 && test[1].getY() == 2);
		Assert.assertTrue(test[2].getX() == 1 && test[2].getY() == 2);
		Assert.assertTrue(test[3].getX() == 1 && test[3].getY() == 2);
	}
}