package main;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int vertices = 49;
//		ArrayList<Point> points = sortPoints(generatePoints(vertices));
//		saveObject(points);
//		System.exit(0);
		ArrayList<Point> points = readObject();
		
		
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
		
		
		JFrame frame = new JFrame("Plot");
		frame.setSize(700, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Scanner sc = new Scanner(System.in);
		
		for(int x = 3; x < vertices; x++) {
			Point p = points.get(x);
			
			JPanel view = createPanel(p, points, convexHull);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(view);

			view.validate();
			view.repaint();
			frame.validate();
			frame.repaint();
			
			
//			sc.next();
			
			
			DrawPanel view2 = new DrawPanel(convexHull);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(view2);

			view2.validate();
			view2.repaint();
			frame.validate();
			frame.repaint();
			
//			sc.next();
			
			ArrayList<Point> connections = TriangleOps.findTangentPoints(p, convexHull);
			
			for(Point cp : connections) {
				convexHull.get(convexHull.indexOf(cp)).addNeighbor(p);
				p.addNeighbor(cp);
			}
			
			convexHull.add(p);
		}
		
		
		
	}
	
	private static ArrayList<Point> generatePoints(int n) {
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Integer> xss = new ArrayList<Integer>();
		
		Random rand = new Random();
		
		for(int pi = 0; pi < n; pi++) {
			int x = 25 + rand.nextInt(600);
			int y = 25 + rand.nextInt(200);
			Point point = new Point(x, y);
			
			if(!xss.contains(x)) {
				if(!points.contains(point)) {
					xss.add(x);
					xss.add(x+1);
					xss.add(x+2);
					xss.add(x+3);
					xss.add(x+4);
					xss.add(x+5);
					xss.add(x+6);
					xss.add(x-1);
					xss.add(x-2);
					xss.add(x-3);
					xss.add(x-4);
					xss.add(x-5);
					xss.add(x-6);
					points.add(point);
				}
				else {
					pi--;
				}
			}
			else {
				pi--;
			}
		}
		
		return points;
	}
	
	private static ArrayList<Point> sortPoints(ArrayList<Point> points) {
		Collections.sort(points, new XComparator());
		return points;
	}

	private static void saveObject(ArrayList<Point> points) throws IOException {
		FileOutputStream out = new FileOutputStream("myobject.data");
		ObjectOutputStream objOut = new ObjectOutputStream (out);
		objOut.writeObject(points);
		objOut.close();
	}
	
	@SuppressWarnings("unchecked")
	private static ArrayList<Point> readObject() throws IOException, ClassNotFoundException {
		FileInputStream inputFile = new FileInputStream("myobject.data");
		ObjectInputStream save = new ObjectInputStream(inputFile);
		ArrayList<Point> points = (ArrayList<Point>) save.readObject();
		
		save.close();
		return points;
	}
	
	
	private static JPanel createPanel(Point pivot, ArrayList<Point> points, ArrayList<Point> convexHull) {
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
		
		return panel;
	}
}
