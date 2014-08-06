package aux;

import java.util.Comparator;

import main.Point;

public class XComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
    	if(o1.getX() <= o2.getX()) {
    		return  -1;
    	}
    	else {
    		return 1;
    	}
    }
}