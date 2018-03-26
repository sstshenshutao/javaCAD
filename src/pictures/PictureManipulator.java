package pictures;

import Aufgabe_1.V.V;
import data.ListItem;
import model.GeometricModelElement;
import model.Point;
import model.angled.TriangleElement;

/**
 * Methods for manipulating an existing picture by creating a new one
 * 
 * @author Nora Wester
 */
public class PictureManipulator {

	/**
	 * manipulates the given picture in the manner, that every element is duplicated and moved
	 * 
	 * @param g
	 *            original picture
	 * @return the new changed picture
	 */
	public static ListItem<GeometricModelElement> doSomething(ListItem<GeometricModelElement> g) {
		ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);

		for (int j = 1; j <= g.getSize(); j++) {
			GeometricModelElement e = g.get(j);
			// draw the original element
			newGraphic.insert(e);
			// clone it
			GeometricModelElement cloneE = e.cloneElement();
			// and move it
			cloneE.move(1, 1);
			newGraphic.insert(cloneE);
		}
		return newGraphic;
	}
	
	/**
	 * rotate the whole picture
	 * @param g
	 * 			original picture
	 * @param angle
	 * 			rotate angle
	 * @return
	 */
	public static ListItem<GeometricModelElement> rotatePic(ListItem<GeometricModelElement> g, double angle) {
		try {ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);

		for (int j = 1; j <= g.getSize(); j++) {
			GeometricModelElement e = g.get(j);
			// rotate
			e.rotate(angle);
			newGraphic.insert(e);
		}
		return newGraphic;}catch(Exception e) {
			return g;
		}
	}
	
	/**
	 * move the whole picture
	 * @param g
	 * 			original picture
	 * @param xDirection
	 * 			move xDirection distance
	 * @param yDirection
	 * 			move yDirection distance
	 * @return
	 */
	public static ListItem<GeometricModelElement> movePic(ListItem<GeometricModelElement> g, double xDirection, double yDirection) {
		try{ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);

		for (int j = 1; j <= g.getSize(); j++) {
			GeometricModelElement e = g.get(j);
			// move
			e.move(xDirection, yDirection);
			newGraphic.insert(e);
		}
		return newGraphic;}catch(Exception e) {
			return g;
		}
	}
	
	/**
	 * mirror the whole picture
	 * @param g
	 * 			original picture
	 * @param axis
	 * 			mirror axis
	 * @return
	 */
	public static ListItem<GeometricModelElement> mirrorPic(ListItem<GeometricModelElement> g, String axis) {
		try {
		ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);

		for (int j = 1; j <= g.getSize(); j++) {
			GeometricModelElement e = g.get(j);
			// mirror
			e.mirror(axis);
			newGraphic.insert(e);
		}
		return newGraphic;}catch(Exception e) {
			return g;
		}
	}
	
	/**
	 * scale the whole picture
	 * @param g
	 * 			original picture
	 * @param factor
	 * 			scaled factor
	 * @return
	 */
	public static ListItem<GeometricModelElement> scalePic(ListItem<GeometricModelElement> g, double factor) {
		try {
			ListItem<GeometricModelElement> newGraphic = new ListItem<GeometricModelElement>(null);
			for (int j = 1; j <= g.getSize(); j++) {
				GeometricModelElement e = g.get(j);
				// scale
				e.scale(factor);
				newGraphic.insert(e);
			}
			return newGraphic;}
		catch(Exception e) {
			return g;
		}
	}
	
	/**
	 * draw a parabola of the first item of the List
	 * @param g1
	 * @param speed
	 * @return
	 */
	public static ListItem<GeometricModelElement> parabola (ListItem<GeometricModelElement> g1, double speed) {
		try {double g = -9.8/40;
		double h =0;
		double v= speed/40;
		V<GeometricModelElement> V= new V<>();
		ListItem<GeometricModelElement> ghead= g1;
		ListItem<GeometricModelElement> finalList= new ListItem<GeometricModelElement>(null);
		GeometricModelElement accc= ghead.key;
		finalList.insert(accc);
		int c=1;
		for(int i=0;i<16;i++) {
			h+=g;
			GeometricModelElement cloneE =accc.cloneElement();
			cloneE.move(v, h);
			cloneE.rotate(-10);
			if(cloneE.getColorCode()<=1) c=1;else if (cloneE.getColorCode()>=9) c=-1;
			cloneE.changeColorCode(cloneE.getColorCode()+c);
			finalList.insert(cloneE);
			accc=cloneE;
		}
		
		return finalList;}
		catch(Exception e) {
			return g1;
		}
	}
	
	/**attraction of two object
	 * @param g1
	 * @param speed
	 * @return
	 */
	public static ListItem<GeometricModelElement> attraction (ListItem<GeometricModelElement> g1, double speed) {
		double foctor = 0.05;
		double v= speed/1.5;
		if (g1.getSize() != 2) return g1;
		try {
		ListItem<GeometricModelElement> finalList= new ListItem<GeometricModelElement>(null);
		GeometricModelElement a= g1.key;
		GeometricModelElement b= g1.next.key;
		finalList.insert(a);
		finalList.insert(b);
		int c=1;//color
		double h=0;
		
		for(int i=0;i<16;i++) {
			double y1 = a.getPoints()[0].getY();
			double y2 = b.getPoints()[0].getY();
			double distance = Math.abs(y2-y1);
			double attractPower = foctor*Math.pow(distance, 2);
//			double attracty=attractPower*Math.abs(y2-y1)/distance;
			
			if (y1>y2) {
			h+=attractPower*2;
			}else {h-=attractPower;}
			GeometricModelElement cloneEa =a.cloneElement();
			cloneEa.move(v, h);
			GeometricModelElement cloneEb =b.cloneElement();
			cloneEb.move(v, -h);
			finalList.insert(cloneEa);
			finalList.insert(cloneEb);
			a=cloneEa;
			b=cloneEb;
		}
		
		return finalList;}catch(Exception e) {
			return g1;
		}
	}
	/** make sin function of a object
	 * @param g1
	 * @param factor
	 * @return
	 */
	public static ListItem<GeometricModelElement> sinxpic (ListItem<GeometricModelElement> g1, double factor) {
		try {

		ListItem<GeometricModelElement> ghead= g1;
		ListItem<GeometricModelElement> finalList= new ListItem<GeometricModelElement>(null);
		GeometricModelElement accc= ghead.key;
		finalList.insert(accc);
		int c=1;
		
		for(int i=0;i<50;i++) {
			double x = accc.getPoints()[0].getX();
			double y = accc.getPoints()[0].getY();
			GeometricModelElement cloneE =accc.cloneElement();
			cloneE.move(1.5,factor*( y*Math.sin(i)));
			if(cloneE.getColorCode()<=1) c=1;else if (cloneE.getColorCode()>=9) c=-1;
			cloneE.changeColorCode(cloneE.getColorCode()+c);
			TriangleElement link=new TriangleElement();
			link.setPoints(new Point[] {new Point(x, y),new Point(x+1.5*i, y),new Point(x+1.5, y+factor*( y*Math.sin(i))),});
			link.changeColorCode(cloneE.getColorCode());
			finalList.insert(link);
			finalList.insert(cloneE);
			accc=cloneE;
		}
		
		return finalList;}
		catch(Exception e) {
			return g1;
		}
	}
	
	/** make cos function of a object
	 * @param g1
	 * @param factor
	 * @return
	 */
	public static ListItem<GeometricModelElement> cosxpic (ListItem<GeometricModelElement> g1, double factor) {
		try {

		ListItem<GeometricModelElement> ghead= g1;
		ListItem<GeometricModelElement> finalList= new ListItem<GeometricModelElement>(null);
		GeometricModelElement accc= ghead.key;
		finalList.insert(accc);
		int c=1;
		
		for(int i=0;i<50;i++) {
			double x = accc.getPoints()[0].getX();
			double y = accc.getPoints()[0].getY();
			GeometricModelElement cloneE =accc.cloneElement();
			cloneE.move(1.5,factor*( y*Math.cos(i)));
			if(cloneE.getColorCode()<=1) c=1;else if (cloneE.getColorCode()>=9) c=-1;
			cloneE.changeColorCode(cloneE.getColorCode()+c);
			TriangleElement link=new TriangleElement();
			link.setPoints(new Point[] {new Point(x, y),new Point(x+1.5*i, y),new Point(x+1.5, y+factor*( y*Math.cos(i))),});
			link.changeColorCode(cloneE.getColorCode());
			finalList.insert(link);
			finalList.insert(cloneE);
			accc=cloneE;
		}
		
		return finalList;}
		catch(Exception e) {
			return g1;
		}
	}
	public static ListItem<GeometricModelElement> sunrise (ListItem<GeometricModelElement> g1, double x, double y) {
		try {double g = -9.8/20;
		double h =y;
		double v= x/2;
		V<GeometricModelElement> V= new V<>();
		ListItem<GeometricModelElement> ghead= g1;
		ListItem<GeometricModelElement> finalList= new ListItem<GeometricModelElement>(null);
		GeometricModelElement accc= ghead.key;
		finalList.insert(accc);
		int c=2;
		double sc=1;
		for(int i=0;i<26;i++) {
			h+=g;
			GeometricModelElement cloneE =accc.cloneElement();
			cloneE.move(v, h);
			cloneE.scale(sc);
			if(i<5) sc=1.2;else if (i>7) sc=0.8;
			cloneE.changeColorCode(c);
			finalList.insert(cloneE);
			accc=cloneE;
		}
		
		return finalList;}
		catch(Exception e) {
			return g1;
		}
	}
	
	
	
	
	
}