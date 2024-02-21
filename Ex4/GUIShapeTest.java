package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;


class GUIShapeTest {
	Point2D tp1 = new Point2D (2,2);
	Point2D tp2 = new Point2D (4,2);
	 GeoShapeable _g =new Segment2D(tp1,tp2);;
	 boolean _fill = true;
	 Color _color = Color.blue;;
	 int _tag = 10 ;
	 boolean _isSelected= false;
	 GUIShape test1 = new GUIShape (_g, _fill, _color, _tag);
	
	@Test
	void testGetShape() {
		test1.setShape(_g);
		assertEquals(test1.getShape(),_g);
	}
	@Test
	void testIsFilled() {
		assertEquals(test1.isFilled(),_fill);
	}

	@Test
	void testSetFilled() {
		boolean testFalse= false;
		test1.setFilled(testFalse);
		assertEquals(test1.isFilled(),false);
	}

	@Test
	void testGetColor() {
		test1.setColor(_color);
		assertEquals(test1.getColor(),_color);
	}

	@Test
	void testSetColor() {
		Color testColor = Color.BLACK;
		test1.setColor(testColor);
		assertEquals(test1.getColor(),Color.BLACK);
	}

	@Test
	void testGetTag() {
		assertEquals(test1.getTag(),_tag);
	}

	@Test
	void testSetTag() {
		int testTag = 5;
		test1.setTag(testTag);
		assertEquals(test1.getTag(),testTag);
	}

	@Test
	void testCopy() {
		GUIShape test1Copy = (GUIShape) test1.copy();
		assertEquals(test1Copy.getColor(),test1.getColor());
		assertEquals(test1Copy.isFilled(),test1.isFilled());
		assertEquals(test1Copy.getTag(),test1.getTag());
	}

	@Test
	void testToString() {
		String test1ToString = test1.toString();
		String toStringExpected = "GUIShape,-16776961,true,10,Segment2D,2.0,2.0,4.0,2.0";
		assertEquals(test1ToString,toStringExpected);
		
	}

	@Test
	void testIsSelected() {
		boolean testSelected =test1.isSelected();
		assertEquals(testSelected,false);
	}

	@Test
	void testSetSelected() {
		boolean testSetSelected = true;
		test1.setSelected(testSetSelected);
		assertEquals(test1.isSelected(),true);
	}

	@Test
	void testSetShape() {
		GeoShapeable testShape = new Rect2D(tp1,tp2);;;
		test1.setShape(testShape);
		assertEquals(test1.getShape(),testShape);
	}

}
