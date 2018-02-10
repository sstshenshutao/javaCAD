package Aufgabe_1.A;

import data.ListItem;
import model.GeometricModelElement;
import model.round.CircleElement;
import org.junit.*;

import java.util.Comparator;

/**
 * Public tests for Method "arrayRunsToListOfLists" in 1.A.
 * 
 * @author Lukas Roehr
 */
public class APublicTests {

	private A<GeometricModelElement>			A	= new A<>();

	/**
	 * The comparator must be implemented by you. See task.
	 */
	private Comparator<GeometricModelElement>	cmp	= new ComparatorColor();

	@Test(expected = IllegalArgumentException.class)
	public void arrayRunsToListOfLists_Test_0() {
		Assert.assertEquals(null, A.arrayRunsToListOfLists(null, null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void arrayRunsToListOfLists_Test_1() {
		Assert.assertEquals(null, A.arrayRunsToListOfLists(null, cmp));
	}

	@Test
	public void arrayRunsToListOfLists_Test_2() {
		ListItem<ListItem<GeometricModelElement>> res = A.arrayRunsToListOfLists(new GeometricModelElement[0], cmp);
		Assert.assertEquals(null, res);
	}

	@Test
	public void arrayRunsToListOfLists_Test_3() {
		GeometricModelElement[] arr = new GeometricModelElement[5];
		CircleElement one = new CircleElement(), two = new CircleElement(), three = new CircleElement(),
				four = new CircleElement(), five = new CircleElement();

		one.changeColorCode(1);
		two.changeColorCode(1);
		three.changeColorCode(6);
		four.changeColorCode(2);
		five.changeColorCode(1);
		arr[0] = one;
		arr[1] = two;
		arr[2] = three;
		arr[3] = four;
		arr[4] = five;
		ListItem<ListItem<GeometricModelElement>> res = A.arrayRunsToListOfLists(arr, cmp);
		Assert.assertEquals(one, res.key.key);
		Assert.assertEquals(two, res.key.next.key);
		Assert.assertEquals(three, res.key.next.next.key);
		Assert.assertEquals(four, res.next.key.key);
		Assert.assertEquals(five, res.next.next.key.key);
		Assert.assertEquals(null, res.next.next.next);
	}
}