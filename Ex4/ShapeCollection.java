package Exe.Ex4;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.gui.Ex4;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	@Override
	//A function that gets the shape in the index i
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	//A function that return the size of the shape collection
	public int size() {
		return _shapes.size();
	}

	@Override
	//A function that remove the shape at index i
	public GUI_Shapeable removeElementAt(int i) {
		//////////add your code below ///////////
		_shapes.remove(i);
		return null;
		//////////////////////////////////////////
	}

	@Override
	//A function that add the shape to index i
	public void addAt(GUI_Shapeable s, int i) {
		//////////add your code below ///////////
		if(s!=null && s.getShape()!=null) {
			_shapes.add(i, s);
		}
		//////////////////////////////////////////
	}
	@Override
	//A function that add the shape to the shape collection
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	//A function that return a copy of the shape collection
	public ShapeCollectionable copy() {
		//////////add your code below ///////////
			ShapeCollection copy = new ShapeCollection();
			for (int i = 0; i < _shapes.size(); i++) {
				copy.add(_shapes.get(i));
			}
			return copy;
		
		//////////////////////////////////////////
	}

	@Override
	//A function that sort the shapes by comparator
	public void sort(Comparator<GUI_Shapeable> comp) {
		//////////add your code below ///////////
		_shapes.sort(comp);
		//////////////////////////////////////////
	}

	@Override
	//A function that remove all the shapes from the shape collection
	public void removeAll() {
		//////////add your code below ///////////
		while(_shapes.size()!= 0) {
			_shapes.remove(0);
		}
		//////////////////////////////////////////
	}

	@Override
	//A function that save the shape collection as a text file
	public void save(String file) {
		//////////add your code below ///////////
			Ex4 ex4 = Ex4.getInstance();
			BufferedWriter myWriter;

			try {
				// Get the file
				myWriter = new BufferedWriter(new FileWriter(file, true));
				// Get the current shapes 
				ShapeCollectionable currentShapes = ex4.getShape_Collection();
				// Write each shape to toString
				for (int i = 0; i < currentShapes.size(); i++) {
					myWriter.append(currentShapes.get(i).toString());
					// Down to new line
					myWriter.newLine();
				}
				// Close the writing to a file
				myWriter.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	//A function that load a text file and extract its data to the shape collection
	public void load(String file){
		////////// add your code below ///////////
		
			// Remove all the existing shapes
			this.removeAll();

			// Get the file
			try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
				String stringData;
				GeoShapeable shape = null;

				// Get and read each line
				while ((stringData = reader.readLine()) != null) {
					// Find the shape and its values
					this.getShape(stringData, shape);

				}
				// Close the reading
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	@Override
	//A function that creating the minimal rectangle that contain all the shapes in the shape collection
	public Rect2D getBoundingBox() {
		//////////add your code below ///////////
		//defining minimum and maximum
		Rect2D ans = null;
		double minX=10;
		double minY=10;
		double maxX=0;
		double maxY=0;
		//checking if the shape collection is not empty or null
		if(_shapes.size()>0 && _shapes!=null) {
			// scanning all the shapes
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				//if the shape is circle
				if(s.getShape() instanceof Circle2D) {
					Point2D[] checkPoints = s.getShape().getPoints();
					double radius = checkPoints[1].y()-checkPoints[0].y();
					double cenX = checkPoints[0].x();
					double cenY = checkPoints[0].y();
					//checking the limits of the rectangle ( if the circle is out of bound)
					if(cenX-radius<=0) {
						minX=0;
					}
					else {
						minX= Math.min(minX, cenX-radius);
					}
					if(cenY-radius<=0) {
						minY=0;
					}
					else {
						minY= Math.min(minY, cenY-radius);
					}
					if(cenX+radius>=10) {
						maxX=10;
					}
					else {
						maxX= Math.max(maxX, cenX+radius);
					}
					if(cenY+radius>=10) {
						maxY=10;
					}
					else {
						maxY= Math.max(minY, cenY+radius);
					}
				}
				//if the shape is rectangle
				if(s.getShape() instanceof Rect2D) {
					Rect2D r = (Rect2D) s.getShape();
					Point2D[] checkPoints = r.getAllPoints();
					for(int z=0; z<checkPoints.length; z++) {
						double ax = Math.min(checkPoints[z].x(), checkPoints[z+1].x());
						minX=Math.min(minX, ax);
						double bx = Math.max(checkPoints[z].x(), checkPoints[z+1].x());
						maxX=Math.max(maxX, bx);
						double ay = Math.min(checkPoints[z].y(), checkPoints[z+1].y());
						minY=Math.min(minY, ay);
						double by = Math.max(checkPoints[z].y(), checkPoints[z+1].y());
						maxY=Math.max(maxY, by);

					}
				}
				//if the shape is not rectangle or circle
				Point2D[] checkPoints = s.getShape().getPoints();
				for(int z=0; z<checkPoints.length; z++) {
					double ax = Math.min(checkPoints[z].x(), checkPoints[z+1].x());
					minX=Math.min(minX, ax);
					double bx = Math.max(checkPoints[z].x(), checkPoints[z+1].x());
					maxX=Math.max(maxX, bx);
					double ay = Math.min(checkPoints[z].y(), checkPoints[z+1].y());
					minY=Math.min(minY, ay);
					double by = Math.max(checkPoints[z].y(), checkPoints[z+1].y());
					maxY=Math.max(maxY, by);

				}


			}
			if(minX<=0) {
				minX=0;
			}
			if(minY<=0) {
				minY=0;
			}
			if(maxX>=10) {
				maxX=10;
			}
			if(maxY>=10) {
				maxY=10;
			}
			Point2D minPoint = new Point2D (minX,minY);
			Point2D maxPoint = new Point2D (maxX,maxY);
			ans = new Rect2D(minPoint, maxPoint);
		}
		//////////////////////////////////////////
		return ans;
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	//A function that extracting the shape data to an actual shape with all the details
	private void getShape(String dataString, GeoShapeable shape) {
		// Split the line to get each data
		String[] shapeData = dataString.split(",");
		// Check if the shape is circle
		if (shapeData[4].equals("Circle2D")) {
			// Get the circle point and radius
			Point2D p1 = new Point2D(Double.parseDouble(shapeData[5]), Double.parseDouble(shapeData[6]));
			double r = Double.parseDouble(shapeData[7]);
			// Define shape as circle
			shape = new Circle2D(p1, r);
			// Check if the shape is rectangle
		} else if (shapeData[4].equals("Rect2D")) {
			// Get the rectangle points
			Point2D p1 = new Point2D(Double.parseDouble(shapeData[5]), Double.parseDouble(shapeData[6]));
			Point2D p2 = new Point2D(Double.parseDouble(shapeData[9]), Double.parseDouble(shapeData[10]));
			// Define the shape as rectangle
			shape = new Rect2D(p1, p2);
			// Check if the shape is segment
		} else if (shapeData[4].equals("Segment2D")) {
			// Get the segment points
			Point2D p1 = new Point2D(Double.parseDouble(shapeData[5]), Double.parseDouble(shapeData[6]));
			Point2D p2 = new Point2D(Double.parseDouble(shapeData[7]), Double.parseDouble(shapeData[8]));
			// Define the shape as segment
			shape = new Segment2D(p1, p2);
			// Check if the shape is polygon
		} else if (shapeData[4].equals("Polygon2D")) {
			// Get the polygon points and insert it to arraylist
			ArrayList<Point2D> points = new ArrayList<Point2D>();
			for (int i = 5; i < shapeData.length; i += 2) {
				Point2D p = new Point2D(Double.parseDouble(shapeData[i]), Double.parseDouble(shapeData[i + 1]));
				points.add(p);
			}
			// Define the shape as polygon
			shape = new Polygon2D(points);
			// Check if the shape is triangle
		} else if (shapeData[4].equals("Triangle2D")) {
			// Get the triangle points
			Point2D p1 = new Point2D(Double.parseDouble(shapeData[5]), Double.parseDouble(shapeData[6]));
			Point2D p2 = new Point2D(Double.parseDouble(shapeData[7]), Double.parseDouble(shapeData[8]));
			Point2D p3 = new Point2D(Double.parseDouble(shapeData[9]), Double.parseDouble(shapeData[10]));
			// Define the shape as triangle
			shape = new Triangle2D(p1, p2, p3);
		}

		// Get the shape color
		Color color = new Color(Integer.parseInt(shapeData[1]));
		// Check if the shape color is filled
		boolean fill = Boolean.parseBoolean(shapeData[2]);
		// Check the shape tag
		int tag = Integer.parseInt(shapeData[3]);
		// Create the shape by all the data we've been extract
		GUI_Shapeable _gs = new GUIShape(shape, fill, color, tag);
		// Add the shape to the shape collection
		this.add(_gs);
	}
}
