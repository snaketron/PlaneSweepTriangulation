package temp;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

import main.Point;
import main.TriangleOps;

public class TempClass {

	public static void main(String[] args) {
		Point p1 = new Point(20, 20);
		Point p2 = new Point(25, 60);
		Point p3 = new Point(40, 35);
		Point p4 = new Point(45, 10);
		
		
		Path2D path = new Path2D.Double();
		path.moveTo(p1.getX(), p1.getY());
		path.lineTo(p2.getX(), p2.getY());
		path.lineTo(p3.getX(), p3.getY());
		path.lineTo(p4.getX(), p4.getY());
//		path.closePath();
		
		
//		Polygon pol = new Polygon();
//		pol.addPoint((int)p1.getX(), (int)p1.getY());
//		pol.addPoint((int)p2.getX(), (int)p2.getY());
//		pol.addPoint((int)p3.getX(), (int)p3.getY());
//		pol.addPoint((int)p4.getX(), (int)p4.getY());
//		
		
		Point pivot = new Point(50, 35);
//		System.out.println(pol.contains(p2.getX(), p2.getY()));

		System.out.println(path.contains(21, 21));
//		System.out.println(path.contains(p2.getX(), p2.getY()));
		
		

	}

}
