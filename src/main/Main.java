package main;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import aux.ObjParser;
import aux.TriangleOps;
import aux.XComparator;
import panel.DrawPanel;

public class Main {
	

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int pointsCount = 49;
//		ArrayList<Point> points = generatePoints(vertices);
//		ObjParser.saveObject(points);
//		System.exit(0);
		ArrayList<Point> points = ObjParser.readObject();
		
		JFrame frame = new JFrame("Plot");
		frame.setSize(700, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		performTriangulation(frame, pointsCount, points);
	}
	
	private static ArrayList<Point> performTriangulation(JFrame frame, 
			int pointsCount, ArrayList<Point> points) {
		Point p1 = points.get(0);
		Point p2 = points.get(1);
		Point p3 = points.get(2);
		
		p1.addNeighbor(p2);
		p1.addNeighbor(p3);
		p2.addNeighbor(p1);
		p2.addNeighbor(p3);
		p3.addNeighbor(p1);
		p3.addNeighbor(p2);
		
		ArrayList<Point> convexHull = new ArrayList<Point>();
		convexHull.add(p1);
		convexHull.add(p2);
		convexHull.add(p3);	
		
		Scanner sc = new Scanner(System.in);
		
		for(int x = 3; x < pointsCount; x++) {
			Point p = points.get(x);
			
			createPanel(frame, p, points, convexHull);
			
//			sc.next();
			
			
//			redrawPanel(frame, convexHull);
			
//			sc.next();
			
			ArrayList<Point> connections = TriangleOps.findTangentPoints(p, convexHull);
			
			for(Point cp : connections) {
				convexHull.get(convexHull.indexOf(cp)).addNeighbor(p);
				p.addNeighbor(cp);
			}
			
			convexHull.add(p);
		}
		
		return convexHull;
	}
	
	
	@SuppressWarnings("unused")
	private static ArrayList<Point> generatePoints(int n) {
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Integer> xss = new ArrayList<Integer>();
		
		Random rand = new Random();
		
		for(int pi = 0; pi < n; pi++) {
			int x = 25 + rand.nextInt(600);
			int y = 25 + rand.nextInt(200);
			Point point = new Point(x, y);
			
			if(!xss.contains(x)) {
				xss.add(x);
				points.add(point);
			}
			else {
				pi--;
			}
		}
		
		Collections.sort(points, new XComparator());
		return points;
	}
	
	
	private static void createPanel(JFrame frame, Point pivot, ArrayList<Point> points, 
			ArrayList<Point> convexHull) {
		JPanel panel = new JPanel(null);
		for(Point p : points) {
			JLabel l = new JLabel(p.toCoord());
			l.setOpaque(true);
			l.setBackground(Color.black);
			l.setBounds((int)p.getX(), (int)p.getY(), 6, 6);
			
			if(convexHull.contains(p)) {
				l.setBackground(Color.red);
			}
			
			if(p.equals(pivot)) {
				l.setBackground(Color.green);
			}
			
			l.setToolTipText(p.toCoord());
			panel.add(l);
		}
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panel);

		panel.validate();
		panel.repaint();
		frame.validate();
		frame.repaint();
	}
	
	private static void redrawPanel(JFrame frame, ArrayList<Point> convexHull) {
		DrawPanel view = new DrawPanel(convexHull);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(view);

		view.validate();
		view.repaint();
		frame.validate();
		frame.repaint();
	}
}
