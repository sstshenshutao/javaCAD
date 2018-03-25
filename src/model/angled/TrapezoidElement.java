package model.angled;

import model.GeometricModelElement;
import util.Constants;

public class TrapezoidElement extends AngledGeometricElement {

	public TrapezoidElement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ANGLED_TRAPEZOID_ELEMENT;
	}

	@Override
	public GeometricModelElement cloneElement() {
		// TODO Auto-generated method stub
		return null;
	}

}
