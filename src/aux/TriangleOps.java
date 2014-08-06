package aux;

import java.awt.geom.Path2D;
import java.util.ArrayList;

import main.Point;

public class TriangleOps {

	public static ArrayList<Point> findTangentPoints(Point pivot, ArrayList<Point> convexPoints) {
		ArrayList<Point> boundaryPoints = new ArrayList<Point>();
		Path2D path = createPath(convexPoints);
		
		for(Point p : convexPoints) {
			if(!path.contains(p.getX(), p.getY())) {
				boundaryPoints.add(p);
			}
		}
		
		Point [] tangents = findTangents(pivot, boundaryPoints);
		ArrayList<Point> points = findIntermediatePoints(pivot, tangents, boundaryPoints);
		
		return points;
	}
	
	
	private static Path2D createPath(ArrayList<Point> convexPoints) {
		Path2D path = new Path2D.Double();
		
		for(Point p : convexPoints) {
			path.moveTo(p.getX(), p.getY());
		}
		
		return path;
	}
	
	
	private static Point [] findTangents(Point pivot, ArrayList<Point> boundaryPoints) {
		Point [] tangents = new Point [2];

		for(Point p : boundaryPoints) {
			boolean negative = false;
			boolean positive = false;
			
			for(Point pp : boundaryPoints) {
				if(!p.equals(pp)) {
					double value = isLeft(pivot, p, pp);
					
					if(!negative) {
						negative = value < 0;
					}
					
					if(!positive) {
						positive = value > 0;
					}
				}
			}
			if(positive & !negative) {
				tangents[1] = p;
			}
			
			if(!positive & negative) {
				tangents[0] = p;
			}
		}
		
		return tangents;
	}
	
	
	private static ArrayList<Point> findIntermediatePoints(Point pivot, 
			Point[] tangents, ArrayList<Point> boundaryPoints) {
		
		ArrayList<Point> allPoints = new ArrayList<Point>();
		allPoints.add(tangents[0]);
		allPoints.add(tangents[1]);
		
		Path2D path = new Path2D.Double();
		
		path.moveTo(tangents[0].getX(), tangents[0].getY());
		path.lineTo(tangents[1].getX(), tangents[1].getY());
		path.lineTo(pivot.getX(), pivot.getY());
		path.closePath();
		
		for(Point p : boundaryPoints) {
			if(path.contains(p.getX(), p.getY())) {
				if(!allPoints.contains(p)) {
					allPoints.add(p);
				}
			}
		}
		
		return allPoints;
	}
	
	
	/**
	 * test if a point is Left|On|Right of an infinite line. Input:  three points P0, P1, and P2.
	 * Return:  >0 for P2 left of the line through P0 and P1
            	=0 for P2 on the line
            	<0 for P2 right of the line
     *
	 **/
	public static double isLeft(Point P0, Point P1, Point P2) {
	    return (P1.getX() - P0.getX())*(P2.getY() - P0.getY()) - (P2.getX() - P0.getX())*(P1.getY() - P0.getY());
	}
}
