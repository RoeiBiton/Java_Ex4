package Exe.Ex4.gui;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 *Students submits
 *Roei Biton ID: 206400426
 *Yair Yaakov ID: 207723198
 */
public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";

	private int tagCounter=0;
	private  Point2D _p1;
	private  Point2D _p2;
	private ArrayList <Point2D> polyPoints= new ArrayList <Point2D>();


	private  static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		//
		if(_shapes!=null) {
			//
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable sh = _shapes.get(i);

				drawShape(sh);
				//
			}
			//
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		//If the shape is circle
		if(gs instanceof Circle2D) {
			Circle2D c = (Circle2D)gs;
			//Extracting the center point and the radius of the circle
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			//If it should be draw filled
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			//Else it should be draw not filled
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		//If the shape is rectangle
		if(gs instanceof Rect2D) {
			Rect2D r = (Rect2D)gs;
			//Extracting the X,Y of the rectangle points
			double[] rectX = new double[4];
			double[] rectY = new double[4];
			Point2D[] allPoints = r.getAllPoints();
			rectX[0] = allPoints[0].x();
			rectX[1] = allPoints[1].x();
			rectX[2] = allPoints[2].x();
			rectX[3] = allPoints[3].x();

			rectY[0] = allPoints[0].y();
			rectY[1] = allPoints[1].y();
			rectY[2] = allPoints[2].y();
			rectY[3] = allPoints[3].y();
			//If it should be draw filled
			if(isFill) {
				StdDraw_Ex4.filledPolygon(rectX, rectY);
			}
			//Else it should be draw not filled
			else { 
				StdDraw_Ex4.polygon(rectX, rectY);
			}
		}
		//If the shape is polygon
		if(gs instanceof Polygon2D) {
			Polygon2D p = (Polygon2D)gs;
			//Extracting the X,Y of the polygon points
			double[] polyX = p.getPointsx();
			double[] polyY = p.getPointsy();
			//If it should be draw filled
			if(isFill) {
				StdDraw_Ex4.filledPolygon(polyX, polyY);
			}
			//eEse it should be draw not filled
			else { 
				StdDraw_Ex4.polygon(polyX, polyY);
			}
		}
		//If the shape is triangle
		if(gs instanceof Triangle2D) {
			Triangle2D t = (Triangle2D)gs;
			//Extracting the X,Y of the triangle points
			double[] TriX = new double[3];
			double[] TriY = new double[3];
			Point2D[] triPoints = t.getPoints();
			TriX[0]= triPoints[0].x();
			TriX[1]= triPoints[1].x();
			TriX[2]= triPoints[2].x();

			TriY[0]= triPoints[0].y();
			TriY[1]= triPoints[1].y();
			TriY[2]= triPoints[2].y();

			//If it should be draw filled
			if(isFill) {
				StdDraw_Ex4.filledPolygon(TriX, TriY);
			}
			//Else it should be draw not filled
			else { 
				StdDraw_Ex4.polygon(TriX, TriY);
			}
		}
		//If the shape is segment
		if(gs instanceof Segment2D) {
			//Extracting the points of the segment
			Segment2D s = (Segment2D)gs;
			Point2D _p1 = s.getPoints()[0];
			Point2D _p2 = s.getPoints()[1];
			//Draw the segment
			StdDraw_Ex4.line(_p1.x(), _p1.y(), _p2.x(), _p2.y());

		}
	}
	private void setColor(Color c) {
		//For loop that runs on all the shape collection and if the shape isSelected it setting its color
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		//For loop that runs on all the shape collection and if the shape isSelected it setting if it will be fiiled or not
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}
	//A function that checking which action is performed and navigate to the relevant commands
	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll(); tagCounter=0;}
		if(p.equals("All")) {selectAll();}
		if(p.equals("None")) {noneAll();}
		if(p.equals("Anti")) {selectAnti();}
		if(p.equals("Remove")) {removeElement();}


		if(p.equals("Save")) {
			//Defining FileDialog
			FileDialog dialog = new FileDialog((Frame) null, "Create File to Save");
			// Set mode to save
			dialog.setMode(FileDialog.SAVE);
			// Make the dialog visible
			dialog.setVisible(true);
			// Get the filePath
			String filePath = dialog.getDirectory() + dialog.getFile();
			// Call the save function with the filePath to read the file and write the data
			_shapes.save(filePath);
			// Close the dialog
			dialog.dispose();
		}


		if(p.equals("Load")) {
			
			// Open dialog box so pick a file
			FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
			// Set mode to load
			dialog.setMode(FileDialog.LOAD);
			// Make the dialog visible
			dialog.setVisible(true);
			// Get the filePath
			String filePath = dialog.getDirectory() + dialog.getFile();
			// Close the dialog
			dialog.dispose();
			// Call the load function with the filePath to read the file and get the data
			_shapes.load(filePath);
			// Show the new collection
			this.show();
		}
		//If the user clicked on that kind of sort, call to the relevant functions
		if(p.equals("ByToString")) {
			_shapes.sort(ShapeComp.CompByToString);
		}
		if(p.equals("ByToAntiString")) {
			_shapes.sort(ShapeComp.CompByAntiToString);
		}
		if(p.equals("ByArea")) {
			_shapes.sort(ShapeComp.CompByArea);
		}
		if(p.equals("ByAntiArea")) {
			_shapes.sort(ShapeComp.CompByAntiArea);
		}
		if(p.equals("ByPerimeter")) {
			_shapes.sort(ShapeComp.CompByPerimeter);
		}
		if(p.equals("ByAntiPerimeter")) {
			_shapes.sort(ShapeComp.CompByAntiPerimeter);
		}
		if(p.equals("ByTag")) {
			_shapes.sort(ShapeComp.CompByTag);
		}
		if(p.equals("ByAntiTag")) {
			_shapes.sort(ShapeComp.CompByAntiTag);
		}
		//Draw the shape collection
		drawShapes();

	}

	// A function that define what to do in any mouse click
	public void mouseClicked(Point2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		//If the User clicked on circle
		if(_mode.equals("Circle")) {
			//If _gs is not defined as specific shape
			if(_gs==null) {
				//save the Click point as _p1
				_p1 = new Point2D(p);
			}
			//Else, define the settings of the shape, color, isFilled, and add it to the shape collection
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				tagCounter++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		//If the User clicked on segment
		if(_mode.equals("Segment")) {
			//If _gs is not defined as specific shape
			if(_gs==null) {
				//save the Click point as _p1
				_p1 = new Point2D(p);

			}
			//Else, define the settings of the shape, color, isFilled, and add it to the shape collection
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				tagCounter++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}

		}
		//If the User clicked on rect
		if(_mode.equals("Rect")) {
			//If _gs is not defined as specific shape
			if(_gs==null) {
				//save the Click point as _p1
				_p1 = new Point2D(p);

			}
			//Else, define the settings of the shape, color, isFilled, and add it to the shape collection
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				tagCounter++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}

		}
		//If the User clicked on polygon
		if(_mode.equals("Polygon")) {
			//save the Click point as _p1 and add it to the polygon arraylist
			_p1 = new Point2D(p);
			polyPoints.add(_p1);
			//System.out.println("Click Polygon Added !");
		}
		//If the User clicked on triangle
		if(_mode.equals("Triangle")) {
			//If _gs is not defined as specific shape and p1 is not null and p2 is null
			if(_gs==null || _p1!=null && _p2==null) {
				if(_p1==null) {
					_p1 = new Point2D(p);
				}
				else {
					_p2 = new Point2D(p);
				}
			}
			//Else, define the settings of the shape, color, isFilled, and add it to the shape collection
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				tagCounter++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;
			}
		}
		//If the User clicked on scale_110
		if(_mode.equals("Scale_110%")) {
			scaleShapes(p,1.1);
		}
		//If the User clicked on scale_90
		if(_mode.equals("Scale_90%")) {
			scaleShapes(p,0.9);
		}
		//If the User clicked on move
		if(_mode.equals("Move")) {
			//if p1 is null, define it as the mouseclick point "p"
			if(_p1==null) {_p1 = new Point2D(p);}
			//else define p1 as the formula below and call the function move
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}
		//If the User clicked on rotate
		if(_mode.equals("Rotate")) {
			//if p1 is null, define it as the mouseclick point "p"
			if(_p1==null) {_p1 = new Point2D(p);}
			//else, send p1 and the mouseclick p to the function rotateshapes
			else {
				rotateShapes(_p1,p);
				_p1=null;

			}
		}
		//If the User clicked on point
		if(_mode.equals("Point")) {
			select(p);
		}

		drawShapes();		
	}
	// A function that scanning all the selected shapes and send them to "Scale changer" function
	private void scaleShapes(Point2D center, double ratio) {
		//If the shape collection is not null
		if(_shapes!=null) {
			//For loop that runs on all the shape collection and if the shape isSelected it send the shape to the "scale" function with center point and scale ratio
			for(int i =0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(s.isSelected() && g !=null) {
					g.scale(center, ratio);
				}
			}
		}

	}
	// A function that scanning all the selected shapes and send them to "Rotate" function
	private void rotateShapes(Point2D center, Point2D p) {
		//Computing the angle
		double angle=(Math.atan2(p.y()-center.y(),p.x()-center.x()));
		//If the shape collection is not null
		if(_shapes!=null) {
			//For loop that runs on all the shape collection and if the shape isSelected it send the shape to the rotate function with center and degrees
			for(int i =0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(s.isSelected() && g !=null) {
					g.rotate(center, Math.toDegrees(angle));
				}
			}
		}

	}
	//This function remove a specific element from the shape collection
	private void removeElement() {
		//For loop that runs on all the shape collection and if the shape isSelected it remove the elenment from th shape collection
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected()) {
				_shapes.removeElementAt(i);
			}
		}
	}
	//A function that DisSelected all the shapes, those who was selected and those who not
	private void noneAll() {
		//For loop that runs on all the shape collection and if the shape isSelected it turn it to not selected
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected()) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	//A function that AntiSelected all the shapes, those who was selected turn to be not selected and those who wasnt selected turn to be selected
	private void selectAnti() {
		//For loop that runs on all the shape collection and oppose the isSelected
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			s.setSelected(!s.isSelected());

		}
	}
	// a function that set all the shape collection as selected
	private void selectAll() {
		//For loop that runs on all the shape collection and set every shape to be selected
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(!s.isSelected()) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	//A function that select a specific shape by the point click of the user
	private void select(Point2D p) {
		//For loop that runs on all the shape collection and check if the shape contain the clicked point, if it is its selected it or make it not selected if it was already selected
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	//A function that moves all the selected shapes in the shape collection by a vector
	private void move() {
		//For loop that scanning all the shape collectioni and check who is selected and sending it to the "move" function
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
	}
	//A function that been called by any right click of the user
	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");
		//if the user have been in polygon mode, (clicked on polygon before the right click)
		if(_mode.equals("Polygon")) {
			System.out.println("IN RIGHT CLICK Polygon");
			//if the shape is not null
			if(_gs!=null) {
				//raise the tagcounter by 1
				tagCounter++;
				// defining a new polygon by the points array "polyPoints"
				GeoShapeable polygon = new Polygon2D(polyPoints);
				//defining the polygon shape with all the settings, color ,isfilled, and tag
				_gs = new GUIShape(polygon,false,Color.pink, tagCounter);
				System.out.println("IN RIGHT CLICK Polygon w IF GS !=null w");
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				//add the shape to the shape collection
				_shapes.add(_gs);

				_gs = null;
				_p1 = null;
				//Clear the polyPoints array so it can be reused
				polyPoints.clear();
				drawShapes();	


			}

		}

	}

	// a function that worked while the moues is moving
	public void mouseMoved(MouseEvent e) {
		//if the p1 is not null
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			//	System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1,y1);
			//if the mode is circle, define gs shape as circle by the point p1 and radius
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			
			}
			//if the mode is rect, define gs shape as rectangle by the point p1 and p
			if(_mode.equals("Rect")) {
				gs = new Rect2D(_p1,p);
			}
			//if the mode is segment, define gs shape as segment by the point p1 and p
			if(_mode.equals("Segment")) {
				gs = new Segment2D(_p1,p);
			}
			//if the mode is polygon, define gs shape as polygon by the point Arraylist "polyPoints"
			if(_mode.equals("Polygon")) {
				polyPoints.add(p);
				gs = new Polygon2D(polyPoints);
				polyPoints.remove(p);

			}
			//if the mode is triangle, define gs shape 
			if(_mode.equals("Triangle")) {
				//if p2 is null define the shape as segment by p1 and p
				if(_p2==null) {
					gs = new Segment2D(_p1,p);
				}
				//else define the shape as trinagle by p2 p1 and p
				else {
					gs = new Triangle2D(_p1,_p2,p);
			
				}
			}
			// define the shape by its kind color filled and tag
			_gs = new GUIShape(gs,false, Color.pink, tagCounter);
			drawShapes();
		}
	}
	@Override
	public ShapeCollectionable getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}