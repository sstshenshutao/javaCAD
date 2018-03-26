package Aufgabe_1.C;

import java.util.Comparator;

import model.angled.AngledGeometricElement;
import model.Point;

/**
 * Die Klasse implementiert einen java.util.Comparator, welcher den Vergleich anhang des Umfangs durchführt. Dabei ist
 * ein kleinerer Umfang als kleiner zu bewerten.
 */
public class ComparatorRange implements Comparator<AngledGeometricElement> {
	/**
	 * Implementieren Sie die Metode compare der Klasse ComparatorRange, welche einen java.util.Comparator
	 * implementiert. Die Methode führt den Vergleich anhand des Umfangs durch. Dabei ist ein kleinerer Umfang als
	 * kleiner zu bewerten.
	 */
	public double getLength(Point[] points) {
	    double distance = 0;
	    for (int i = 0; i < points.length; i++) {
	        Point itemA = points[i];
		    int index = i+1;
		    if (i == points.length - 1) {
			    index = 0;
			}
		    Point itemB = points[index];
            distance += Math.sqrt(Math.pow((itemA.getX()-itemB.getX()), 2) + Math.pow((itemA.getY()-itemB.getY()), 2));
	    }
		return distance;
	}
		
	@Override
	public int compare(AngledGeometricElement elem1, AngledGeometricElement elem2) {
	    if(getLength(elem1.getPoints()) == getLength(elem2.getPoints())) {
		   return 0;
	    }else if(getLength(elem1.getPoints()) > getLength(elem2.getPoints())) {    	
		         return 1;
	    }else {  
		       return -1;  
		}  
	}
}
