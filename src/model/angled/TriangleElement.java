/**
 * 
 */
package model.angled;

import model.GeometricModelElement;
import model.Point;
import util.Constants;

/**
 * @author Shutao Shen
 *
 */
public class TriangleElement extends AngledGeometricElement {

	
	/* (non-Javadoc)
	 * @see model.GeometricModelElement#getJSONClassName()
	 */
	@Override
	protected String getJSONClassName() {
		return Constants.GEOMETRIC_ANGLED_TRIANGLE_ELEMENT;
	}

	/* (non-Javadoc)
	 * @see model.GeometricModelElement#cloneElement()
	 */
	@Override
	public GeometricModelElement cloneElement() {
		// TODO Auto-generated method stub
		return null;
	}

}
