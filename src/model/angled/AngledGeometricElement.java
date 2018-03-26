package model.angled;

import java.util.HashMap;

import model.GeometricModelElement;
import model.Point;
import model.round.CircleElement;
import util.Constants;
import util.Util;

/**
 * Basic class of the angled picture elements
 * 
 * @author Nora Wester
 */
public abstract class AngledGeometricElement extends GeometricModelElement {

	@Override
	protected void calculateMove(double xDirection, double yDirection) {
		Point[] points = this.getPoints();
		for (Point point : points) {
			point.setX(point.getX() + xDirection);
			point.setY(point.getY() +yDirection);
		}
	}

	@Override
	protected void calculateRotation(double angle) {
		Point[] points = this.getPoints();
		//get the max x and max y
		double maxX=Double.MIN_VALUE;
		double maxY=Double.MIN_VALUE;
		for (int i=0;i<points.length;i++) {
			if(points[i].getX()>maxX) maxX=points[i].getX();
			if(points[i].getY()>maxY) maxY=points[i].getY();
		}
		//get the min x and min y
		double minX=Double.MAX_VALUE;
		double minY=Double.MAX_VALUE;
		for (int i=0;i<points.length;i++) {
			if(points[i].getX()<minX) minX=points[i].getX();
			if(points[i].getY()<minY) minY=points[i].getY();
		}
		//centerpoint of this geometric
		double centerX = (maxX+minX)/2;
		double centerY = (maxY+minY)/2;
		
		
//		[cosa -sina (1-cosa)tx+tysina]	[x]
//		[sina cosa (1-cosa)ty-txsina)]	[y]
//				[0 0 1]					[1]
//		x'=(x-cx)cosa+(y-cy)(-sina)+cx
//		y'=(x-cx)sina+(y-cy)cosa+cy
		for (Point point : points) {
			double tx=point.getX() - centerX;
			double ty=point.getY() - centerY;
			double x =point.getX();
			double y =point.getY();
			point.setX(tx * Math.cos(angle/360*Math.PI)- ty * Math.sin(angle/360*Math.PI)+centerX);
			point.setY(tx * Math.sin(angle/360*Math.PI)+ ty * Math.cos(angle/360*Math.PI)+centerY);
		}
		

	}

	@Override
	protected void calculateScale(double factor) {
		Point[] points = this.getPoints();
		//get the max x and max y
		double maxX=Double.MIN_VALUE;
		double maxY=Double.MIN_VALUE;
		for (int i=0;i<points.length;i++) {
			if(points[i].getX()>maxX) maxX=points[i].getX();
			if(points[i].getY()>maxY) maxY=points[i].getY();
		}
		//get the min x and min y
		double minX=Double.MAX_VALUE;
		double minY=Double.MAX_VALUE;
		for (int i=0;i<points.length;i++) {
			if(points[i].getX()<minX) minX=points[i].getX();
			if(points[i].getY()<minY) minY=points[i].getY();
		}
		//centerpoint of this geometric
		double centerX = (maxX+minX)/2;
		double centerY = (maxY+minY)/2;
		for (Point point : points) {
			point.setX((point.getX() - centerX) * factor + centerX);
			point.setY((point.getY() - centerY) * factor + centerY);
		}
	}

	@Override
	protected void calculateMirroring(String axis) {
		if (!axis.equalsIgnoreCase("x") && !axis.equalsIgnoreCase("y")) return ;
		Point[] points = this.getPoints();
		//get the max x and max y
		double maxX=Double.MIN_VALUE;
		double maxY=Double.MIN_VALUE;
		for (int i=0;i<points.length;i++) {
			if(points[i].getX()>maxX) maxX=points[i].getX();
			if(points[i].getY()>maxY) maxY=points[i].getY();
		}
		//get the min x and min y
		double minX=Double.MAX_VALUE;
		double minY=Double.MAX_VALUE;
		for (int i=0;i<points.length;i++) {
			if(points[i].getX()<minX) minX=points[i].getX();
			if(points[i].getY()<minY) minY=points[i].getY();
		}
		double axisY = (maxX+minX)/2;
		double axisX = (maxY+minY)/2;
		if (axis.equalsIgnoreCase("x")) {
			for(Point p: points) {
				//y
				p.setY(axisX*2-p.getY());
			}
		}else if(axis.equalsIgnoreCase("y")){
			for(Point p: points) {
				//x
				p.setX(axisY*2-p.getX());
			}
		}
	}
	
	
	
}