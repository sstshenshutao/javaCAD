package Aufgabe_2;

import org.junit.Test;

import Aufgabe_1.B.*;
import Aufgabe_1.V.ComparatorInteger;
import data.ListItem;

public class BTest {

	/*****************************************************************
	 * rotateQuadrupleLeft
	 *****************************************************************/

	@Test
	public void rotateQuadrupleLeft_Test_1() {

	}

	@Test
	public void rotateQuadrupleLeft_Test_2() {

	}

	@Test
	public void rotateQuadrupleLeft_Test_3() {

	}

	@Test(expected = IllegalArgumentException.class)
	public void rotateQuadrupleLeft_Test_4() {

	}

	/*****************************************************************
	 * insertSingle
	 *****************************************************************/

	@Test
	public void insertSingle_Test_1() {
		ListItem<Integer> newlist= new ListItem<Integer>(4);
		B<Integer> b = new B<>();
		System.out.println("t"+newlist);
		newlist.insert(3);
		System.out.println("t"+newlist);
		ListItem<Integer> newlist2= b.insertSingle(newlist, new Integer(2), new ComparatorInteger());
		System.out.println("t"+newlist);
		System.out.println("t"+newlist2);

	}

	@Test
	public void insertSingle_Test_2() {

	}

	@Test
	public void insertSingle_Test_3() {

	}

	@Test(expected = IllegalArgumentException.class)
	public void insertSingle_Test_4() {

	}

	/*****************************************************************
	 * removeHead
	 *****************************************************************/

	@Test
	public void removeHead_Test_1() {

	}

	@Test
	public void removeHead_Test_2() {

	}

	@Test
	public void removeHead_Test_3() {

	}

	/*****************************************************************
	 * ringShiftRight
	 *****************************************************************/

	@Test
	public void ringShiftRight_Test_1() {

	}

	@Test
	public void ringShiftRight_Test_2() {

	}

	@Test
	public void ringShiftRight_Test_3() {

	}

	/*****************************************************************
	 * listInLists
	 *****************************************************************/

	@Test
	public void listInLists_Test_1() {

	}

	@Test
	public void listInLists_Test_2() {

	}

	@Test
	public void listInLists_Test_3() {

	}

	/*****************************************************************
	 * selectType
	 *****************************************************************/

	@Test
	public void selectType_Test_1() {

	}

	@Test
	public void selectType_Test_2() {

	}

	@Test
	public void selectType_Test_3() {

	}

	@Test(expected = IllegalArgumentException.class)
	public void selectType_Test_4() {

	}

	/*****************************************************************
	 * ComparatorSurface.compare
	 *****************************************************************/

	@Test
	public void compare_Test_1() {

	}

	@Test
	public void compare_Test_2() {

	}

	@Test
	public void compare_Test_3() {

	}

	/*****************************************************************
	 * scale
	 *****************************************************************/

	@Test
	public void scale_Test_1() {

	}

	@Test
	public void scale_Test_2() {

	}

	@Test
	public void scale_Test_3() {

	}
}