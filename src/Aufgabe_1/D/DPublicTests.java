package Aufgabe_1.D;

import data.ListItem;
import model.GeometricModelElement;
import model.round.CircleElement;
import org.junit.*;

import java.util.Comparator;

/**
 * Public tests for method "removeSecMaxElement" in 1.D
 * 
 * @author Lukas Roehr
 */
public class DPublicTests {
	private D<CircleElement>			D	= new D<>();

	/**
	 * The comparator must be implemented by you. See task.
	 */
	private Comparator<CircleElement>	cmp	= new ComparatorRadius();

	@Test
	public void removeSecMaxElement_Test_0() {
		CircleElement _one = new CircleElement(), _two = new CircleElement(), _three = new CircleElement();
		ListItem<CircleElement> one = new ListItem<>(_one), two = new ListItem<>(_two), three = new ListItem<>(_three);
		_one.setHeight(1);
		_two.setHeight(2);
		_three.setHeight(3);
		one.next = two;
		two.next = three;
		ListItem<CircleElement> res = D.removeSecMaxElement(one, cmp);
		Assert.assertEquals(_one, res.key);
		Assert.assertEquals(_three, res.next.key);
		Assert.assertEquals(null, res.next.next);
		Assert.assertNotEquals(null, new ListItem<GeometricModelElement>(_one));
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeSecMaxElement_Test_1() {
		Assert.assertEquals(null, D.removeSecMaxElement(null, cmp));
		D.removeSecMaxElement(null, null);
	}
}