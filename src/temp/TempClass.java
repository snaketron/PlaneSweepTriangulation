package temp;

import java.util.ArrayList;

import main.Point;

public class TempClass {

	public static void main(String[] args) {
		Point p1 = new Point(20, 20);
		Point p2 = new Point(25, 60);
		Point p3 = new Point(40, 35);
		Point p4 = new Point(45, 10);
		
		
		Point pivot = new Point(80, 30);
		
		ArrayList<Point> convexHull = new ArrayList<Point>();
		convexHull.add(p1);
		convexHull.add(p2);
		convexHull.add(p3);
		convexHull.add(p4);
		
		
		for(Point p : convexHull) {
			for(Point pp : convexHull) {
				if(!p.equals(pp)) {
					double value = isLeft(pivot, p, pp);
					System.out.println(value + "  to:" + p.toCoord() + " comp:" + pp.toCoord());
				}
			}
		}

	}
	
	public static double isLeft(Point P0, Point P1, Point P2) {
	    return (P1.getX() - P0.getX())*(P2.getY() - P0.getY()) - (P2.getX() - P0.getX())*(P1.getY() - P0.getY());
	}

}
