package aux;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import main.Point;

public class ObjParser {
	public static void saveObject(ArrayList<Point> points) throws IOException {
		FileOutputStream out = new FileOutputStream("myobject.data");
		ObjectOutputStream objOut = new ObjectOutputStream (out);
		objOut.writeObject(points);
		objOut.close();
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Point> readObject() throws IOException, ClassNotFoundException {
		FileInputStream inputFile = new FileInputStream("myobject.data");
		ObjectInputStream save = new ObjectInputStream(inputFile);
		ArrayList<Point> points = (ArrayList<Point>) save.readObject();
		
		save.close();
		return points;
	}
}
