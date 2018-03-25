package model.angled;

import model.GeometricModelElement;
import util.Constants;

public class RectangleElement extends AngledGeometricElement {

	public RectangleElement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getJSONClassName() {
		// TODO Auto-generated method stub
		return Constants.GEOMETRIC_ANGLED_RECTANGLE_ELEMENT;
	}

	@Override
	public GeometricModelElement cloneElement() {
		// TODO Auto-generated method stub
		return null;
	}

}
