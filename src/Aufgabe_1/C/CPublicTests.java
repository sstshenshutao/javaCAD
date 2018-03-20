package Aufgabe_1.C;

import data.ListItem;
import model.GeometricModelElement;
import model.round.CircleElement;
import model.round.EllipseElement;
import org.junit.*;

/**
 * Public tests for method "combineLists" in 1.C
 * 
 * @author Lukas Roehr
 */
public class CPublicTests {
	private C<GeometricModelElement> C	= new C<>();
	private ListItem<GeometricModelElement>
			one	= new ListItem<>(new CircleElement()),
			two = new ListItem<>(new EllipseElement()),
			three = new ListItem<>(new CircleElement()),
			four = new ListItem<>(new EllipseElement()),
			five = new ListItem<>(new CircleElement()),
			six = new ListItem<>(new CircleElement());

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

	/*
		Im folgenden gibt es zwei Versionen der Tests:
			- Version 1, stellt Tests für die *aktuelle Aufgabenstellung* bereit.
			- Version 2, stellt Tests für eine ältere Version der Aufgabenstellung bereit.

		Falls Studierende bereits die Aufgabenstellung der älteren Version implementiert haben,
		akzeptieren wir diese natürlich auch!
	 */
	@Test
	public void VERSION_1_combineLists_Test_1() {
		ListItem<GeometricModelElement> res = C.combineLists(one, four);
		Assert.assertEquals(two, res);
		Assert.assertEquals(four, res.next);
		Assert.assertEquals(six, res.next.next);
		Assert.assertEquals(null, res.next.next.next);
	}

	@Test
	public void VERSION_1_combineLists_Test_2() {
		ListItem<GeometricModelElement> seven = new ListItem<>(null);
		six.next = seven;
		ListItem<GeometricModelElement> res = C.combineLists(one, four);
		Assert.assertEquals(two, res);
		Assert.assertEquals(four, res.next);
		Assert.assertEquals(six, res.next.next);
		Assert.assertEquals(seven, res.next.next.next);
		Assert.assertEquals(null, res.next.next.next.next);
	}

	@Test
	public void VERSION_2_combineLists_Test_1() {
		ListItem<GeometricModelElement> res = C.combineLists(one, four);
		Assert.assertEquals(one, res);
		Assert.assertEquals(five, res.next);
		Assert.assertEquals(three, res.next.next);
		Assert.assertEquals(null, res.next.next.next);
	}

	@Test
	public void VERSION_2_combineLists_Test_2() {
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
