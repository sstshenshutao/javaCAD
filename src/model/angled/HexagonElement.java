package model.angled;

import model.GeometricModelElement;
import util.Constants;

public class HexagonElement extends AngledGeometricElement {

	public HexagonElement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ANGLED_HEXAGON_ELEMENT;
	}

	@Override
	public GeometricModelElement cloneElement() {
		// TODO Auto-generated method stub
		return null;
	}

}
