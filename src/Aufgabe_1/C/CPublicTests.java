package Aufgabe_1.C;

import data.ListItem;
import model.GeometricModelElement;
import org.junit.*;

/**
 * Public tests for method "combineLists" in 1.C
 * 
 * @author Lukas Roehr
 */
public class CPublicTests {
	private C<GeometricModelElement>		C	= new C<>();
	private ListItem<GeometricModelElement>	one	= new ListItem<>(null), two = new ListItem<>(null),
			three = new ListItem<>(null), four = new ListItem<>(null), five = new ListItem<>(null),
			six = new ListItem<>(null);

	@Before
	public void buildList() {
		one.next = two;
		two.next = three;
		four.next = five;
		five.next = six;
	}

	@Test
	public void combineLists_Test_0() {
		Assert.assertEquals(null, C.combineLists(null, null));
		ListItem<GeometricModelElement> elem = new ListItem<GeometricModelElement>(null);
		Assert.assertEquals(elem, C.combineLists(null, elem));
		Assert.assertEquals(elem, C.combineLists(elem, null));
	}

	@Test
	public void combineLists_Test_1() {
		ListItem<GeometricModelElement> res = C.combineLists(one, four);
		Assert.assertEquals(one, res);
		Assert.assertEquals(five, res.next);
		Assert.assertEquals(three, res.next.next);
		Assert.assertEquals(null, res.next.next.next);
	}

	@Test
	public void combineLists_Test_2() {
		ListItem<GeometricModelElement> seven = new ListItem<>(null);
		six.next = seven;
		ListItem<GeometricModelElement> res = C.combineLists(one, four);
		Assert.assertEquals(one, res);
		Assert.assertEquals(five, res.next);
		Assert.assertEquals(three, res.next.next);
		Assert.assertEquals(seven, res.next.next.next);
		Assert.assertEquals(null, res.next.next.next.next);
	}
}