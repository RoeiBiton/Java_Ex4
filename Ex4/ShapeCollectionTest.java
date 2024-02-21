package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;

class ShapeCollectionTest {
	
	
	
	ArrayList<GUI_Shapeable> testShapes;
	Point2D tp1 = new Point2D (2,2);
	Point2D tp2 = new Point2D (4,2);
	Point2D trect1 = new Point2D (3,2);
	Point2D trect2 = new Point2D (4,3);
	 GeoShapeable _segment =new Segment2D(tp1,tp2);
	 GeoShapeable _rect =new Rect2D(trect1,trect2);
	 boolean _fill = true;
	 Color blue = Color.blue;
	 Color black = Color.BLACK;
	 Color white = Color.WHITE;
	 int _tag10 = 10 ;
	 int _tag5 = 5;
	 int _tag0 = 0;
	 boolean _isSelected= false;
	 GUIShape test10blue = new GUIShape (_segment, _fill, blue, _tag10);
	 GUIShape test5black = new GUIShape (_segment, _fill, blue, _tag5);
	 GUIShape test0white = new GUIShape (_segment, _fill, blue, _tag0);
	 GUIShape testRect = new GUIShape (_rect, _fill, blue, _tag0);
	 

	

	@Test
	void testGet() {
		testShapes = new ArrayList<GUI_Shapeable>();
	testShapes.add(test5black);
	assertEquals(testShapes.get(0),test5black);
	}

	@Test
	void testSize() {
		testShapes = new ArrayList<GUI_Shapeable>();
		testShapes.add(test5black);
		testShapes.add(test10blue);
		assertEquals(testShapes.size(),2);
		
	}

	@Test
	void testRemoveElementAt() {
		testShapes = new ArrayList<GUI_Shapeable>();
		testShapes.add(0, test5black);
		testShapes.add(1, test10blue);
		testShapes.add(2, test0white);
		assertEquals(testShapes.size(),3);
		
		testShapes.remove(1);
		assertEquals(testShapes.size(),2);
		assertEquals(testShapes.get(0),test5black);
		assertEquals(testShapes.get(1),test0white);
	}

	@Test
	void testAddAt() {
		testShapes = new ArrayList<GUI_Shapeable>();
		testShapes.add(0, test5black);
		testShapes.add(1, test0white);
		assertEquals(testShapes.get(0),test5black);
		assertEquals(testShapes.get(1),test0white);
		
	}

	@Test
	void testAdd() {
		testShapes = new ArrayList<GUI_Shapeable>();
		assertEquals(testShapes.size(),0);
		testShapes.add(test5black);
		testShapes.add(test10blue);
		assertEquals(testShapes.size(),2);
		
	}

	@Test
	void testSort() {
		testShapes = new ArrayList<GUI_Shapeable>();
		testShapes.add(0, test5black);
		testShapes.add(1, test10blue);
		testShapes.add(2, test0white);
		testShapes.sort(ShapeComp.CompByTag);
		
		assertEquals(testShapes.get(0),test0white);
		assertEquals(testShapes.get(1),test5black);
		assertEquals(testShapes.get(2),test10blue);
		
		testShapes.sort(ShapeComp.CompByAntiTag);
		
		assertEquals(testShapes.get(0),test10blue);
		assertEquals(testShapes.get(1),test5black);
		assertEquals(testShapes.get(2),test0white);
		
	}

	@Test
	void testRemoveAll() {
		testShapes = new ArrayList<GUI_Shapeable>();
		testShapes.add(0, test5black);
		testShapes.add(1, test10blue);
		testShapes.add(2, test0white);
		assertEquals(testShapes.size(),3);
		
		testShapes.removeAll(testShapes);
		assertEquals(testShapes.size(),0);
	}

	
	@Test
	void testToString() {
		testShapes = new ArrayList<GUI_Shapeable>();
		testShapes.add(0, test5black);
		testShapes.add(1, test10blue);
		testShapes.add(2, test0white);
		String testString = testShapes.toString();
		String expectedString = "[GUIShape,-16776961,true,5,Segment2D,2.0,2.0,4.0,2.0, GUIShape,-16776961,true,10,Segment2D,2.0,2.0,4.0,2.0, GUIShape,-16776961,true,0,Segment2D,2.0,2.0,4.0,2.0]";
		assertEquals(testString,expectedString);
	}
	

}
