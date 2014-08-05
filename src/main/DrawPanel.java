package main;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

class DrawPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Point> points;

	public DrawPanel(ArrayList<Point> points) {
		this.points = points;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(Point p : points) {
			g.fillRect((int)p.getX(), (int)p.getY(), 4, 4);
			
			for(Point np : p.getNeighbors()) {
				g.drawLine((int)p.getX(), (int)p.getY(), (int)np.getX(), (int)np.getY());
			}
		}
	}
}