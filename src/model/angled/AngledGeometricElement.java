package model.angled;

import model.GeometricModelElement;
import model.Point;
import util.Util;

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
		//scale sst
		Point[] points = this.getPoints();
		for (Point point : points) {
			point.setX(point.getX() * factor);
			point.setY(point.getY() * factor);
		}
	}

	@Override
	protected void calculateMirroring(String axis) {

	}
}