package model.angled;

import model.GeometricModelElement;

/**
 * Basic class of the angled picture elements
 * 
 * @author Nora Wester
 */
public abstract class AngledGeometricElement extends GeometricModelElement {

	@Override
	protected void calculateMove(double xDirection, double yDirection) {

	}

	@Override
	protected void calculateRotation(double angle) {

	}

	@Override
	protected void calculateScale(double factor) {

	}

	@Override
	protected void calculateMirroring(String axis) {

	}
}