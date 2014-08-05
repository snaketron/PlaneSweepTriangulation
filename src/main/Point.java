package main;

import java.io.Serializable;
import java.util.ArrayList;


public class Point implements Serializable {
	private static final long serialVersionUID = 1L;
	private double x;
	private double y;
	private ArrayList<Point> neighbors;
	
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.neighbors = new ArrayList<Point>();
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void addNeighbor(Point p) {
		if(!this.neighbors.contains(p)) {
			this.neighbors.add(p);
		}
		else {
			System.err.print("point already added as neighbor");
		}
	}
	
	public void removeNeighbor(Point p) {
		if(this.neighbors.contains(p)) {
			this.neighbors.remove(p);
		}
		else {
			System.err.print("point not a neighbor");
		}
	}

	public ArrayList<Point> getNeighbors() {
		return neighbors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "P [x=" + x + ", y=" + y + "]";
	}

	public String toCoord() {
		return("x:" + x + "-y:" + y);
	}
}
