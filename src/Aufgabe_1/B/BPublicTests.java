package Aufgabe_1.B;

import data.ListItem;
import model.GeometricModelElement;
import model.round.CircleElement;
import model.round.EllipseElement;
import org.junit.*;

/**
 * Public test for method "selectType" in 1.B.
 * 
 * @author Lukas Roehr
 */
public class BPublicTests {
	private B<GeometricModelElement> B = new B<>();

	@Test(expected = IllegalArgumentException.class)
	public void selectType_Test_0() {
		B.selectType(null, null);
	}

	@Test
	public void selectType_Test_1() {
		EllipseElement one = new EllipseElement(), three = new EllipseElement();
		CircleElement two = new CircleElement();
		ListItem<GeometricModelElement> _one = new ListItem<>(one), _two = new ListItem<>(two),
				_three = new ListItem<>(three);
		_one.next = _two;
		_two.next = _three;
		Object[] res = B.selectType(_one, CircleElement.class);
		Assert.assertEquals(two, res[0]);

		res = B.selectType(_one, EllipseElement.class);
		Assert.assertEquals(one, res[0]);
		Assert.assertEquals(two, res[1]);
		Assert.assertEquals(three, res[2]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void selectType_Test_2() {
		B.selectType(null, CircleElement.class);
	}
}