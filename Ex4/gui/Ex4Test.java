package Exe.Ex4.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;



class Ex4Test {

	Ex4 _windowEx4 = Ex4.getInstance();

   
	 ShapeCollectionable _shapes = _windowEx4.getShape_Collection();
	 
	Point2D tp1 = new Point2D (1,1);
	Point2D tp2 = new Point2D (4,4);
	Point2D tp3 = new Point2D(3, 4);
	Point2D tp4 = new Point2D(6, 8);
	Point2D tp5 = new Point2D(10, 15);
    Point2D tp6 = new Point2D(16.8, 24.23);
    Point2D tp7 = new Point2D(1, 1);
    Point2D tp8 = new Point2D(1,2);

     Segment2D  tSegment = new Segment2D(tp7,tp8);
     Rect2D tRect =new Rect2D(tp1,tp2);
     Circle2D tCircle1 = new Circle2D(tp3, 2);
	 Circle2D tCircle2 = new Circle2D(tp4, 4);
	     GUI_Shapeable tShape1 = new GUIShape(tCircle1, true, Color.black, 1);
         GUI_Shapeable tShape2 = new GUIShape(tCircle2, false, Color.blue, 2);
         GUI_Shapeable tShape3 = new GUIShape(tRect, false, Color.green, 2);
         GUI_Shapeable tShape4 = new GUIShape(tSegment, false, Color.red, 2);

	@Test
	void testActionPerformed() {

		_shapes.add(tShape1);
		_shapes.add(tShape2);
		_shapes.add(tShape3);
		_windowEx4.actionPerformed("None");
		boolean a =tShape1.isSelected();
		boolean b =tShape2.isSelected();
		boolean c =tShape3.isSelected();
		assertEquals(a, false);
		assertEquals(b, false);
		assertEquals(c, false);
	   
		_windowEx4.actionPerformed("Anti");
		boolean a1 =tShape1.isSelected();
		boolean b2 =tShape2.isSelected();
		boolean c3 =tShape3.isSelected();
		assertEquals(a1, true);
		assertEquals(b2, true);
		assertEquals(c3, true);
	   
		_windowEx4.actionPerformed("Clear");
		assertEquals(_shapes.size(), 0);
		_shapes.add(tShape1);
		_shapes.add(tShape2);
		_shapes.add(tShape3);

		_windowEx4.actionPerformed("ByArea");
		assertEquals(_shapes.get(0), tShape3);
		assertEquals(_shapes.get(1), tShape1);
		assertEquals(_shapes.get(2), tShape2);
		
		_windowEx4.actionPerformed("ByAntiArea");
		assertEquals(_shapes.get(0), tShape2);
		assertEquals(_shapes.get(1), tShape1);
		assertEquals(_shapes.get(2), tShape3);
		
		_windowEx4.actionPerformed("ByParemiter");
		assertEquals(_shapes.get(0), tShape2);
		assertEquals(_shapes.get(1), tShape1);
		assertEquals(_shapes.get(2), tShape3);

	}
}
