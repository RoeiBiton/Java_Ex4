package Exe.Ex4.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Polygon2DTest {
	double EPS = 0.0001;
	
	@Test
	void testToString() {
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);

		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);

		Polygon2D poly1 = new Polygon2D (poly);
		String poly1String = poly1.toString();
		String poly1Expected= "1.0,1.0,1.0,4.0,3.0,6.0";
		assertEquals(poly1String,poly1Expected);
	}

	@Test
	void testContains() {
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);
		Point2D tp4= new Point2D (5,4);
		Point2D tp5= new Point2D (5,1);
		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);
		poly.add(tp4);
		poly.add(tp5);
		Polygon2D poly1 = new Polygon2D (poly);
		
		Point2D tpFalse= new Point2D (0,0);
		Point2D tpTrue= new Point2D (2,1); 
		Point2D tpTrue2= new Point2D (3,3); 
		assertEquals(poly1.contains(tpTrue),true);
		assertEquals(poly1.contains(tpFalse),false);
		assertEquals(poly1.contains(tpTrue2),true);
	}

	@Test
	void testArea() {
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);
		Point2D tp4= new Point2D (5,4);
		Point2D tp5= new Point2D (5,1);
		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);
		poly.add(tp4);
		poly.add(tp5);
		Polygon2D poly1 = new Polygon2D (poly);
		
		assertEquals(poly1.area(),16,0); 
	}

	@Test
	void testPerimeter() {
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);
		Point2D tp4= new Point2D (5,4);
		Point2D tp5= new Point2D (5,1);
		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);
		poly.add(tp4);
		poly.add(tp5);
		Polygon2D poly1 = new Polygon2D (poly);
		
		assertEquals(poly1.perimeter(),15.65685,EPS); 
		
	}

	@Test
	void testMove() {
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);
		Point2D tp4= new Point2D (5,4);
		Point2D tp5= new Point2D (5,1);
		Point2D tp6= new Point2D (1,0);
		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);
		poly.add(tp4);
		poly.add(tp5);
		Polygon2D poly1 = new Polygon2D (poly);
		
		poly1.move(tp6);
		Point2D[] arrayMove = poly1.getPoints();
		Point2D p1= new Point2D (2,1);
		Point2D p2= new Point2D (2,4); 
		Point2D p3= new Point2D (4,6); 
		Point2D p4= new Point2D (6,4); 
		Point2D p5= new Point2D (6,1); 
		assertEquals(arrayMove[0],p1);
		assertEquals(arrayMove[1],p2);
		assertEquals(arrayMove[2],p3);
		assertEquals(arrayMove[3],p4);
		assertEquals(arrayMove[4],p5);
	}

	@Test
	void testCopy() {
		
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);

		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);

		Polygon2D poly1 = new Polygon2D (poly);

		
		Polygon2D poly1Copy = (Polygon2D) poly1.copy();
		Point2D[] arrayOriginal = poly1.getPoints();
		Point2D[] arrayCopy = poly1Copy.getPoints();
		assertEquals(arrayCopy[0],arrayOriginal[0]);
		assertEquals(arrayCopy[1],arrayOriginal[1]);
		assertEquals(arrayCopy[2],arrayOriginal[2]);
		
	
	}

	@Test
	void testScale() {
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);
		Point2D tpScale= new Point2D (1,0);
		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);

		Polygon2D poly1 = new Polygon2D (poly);
		poly1.scale(tpScale, 0.9);
		
		Point2D[] array11Scale = poly1.getPoints();
		Point2D scaletp1= new Point2D (1,0.9);
		Point2D scaletp2= new Point2D (1,3.6); 
		Point2D scaletp3= new Point2D (2.8,5.4);
	
		assertEquals(array11Scale[0],scaletp1);
		assertEquals(array11Scale[1],scaletp2);
		assertEquals(array11Scale[2],scaletp3);
		
	}

	@Test
	void testRotate() {
		Point2D Origin = new Point2D (0,0);
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);

		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);

		Polygon2D poly1 = new Polygon2D (poly);
		poly1.rotate(Origin, 90);
		
		Point2D rotatedtp1= new Point2D (-1,1);
		Point2D rotatedtp2= new Point2D (-4,1);
		Point2D rotatedtp3= new Point2D (-6,3);

		Point2D[] rotatedArray = poly1.getPoints();
		
		boolean isEqualsp1 = rotatedArray[0].close2equals(rotatedtp1);
		boolean isEqualsp2 = rotatedArray[1].close2equals(rotatedtp2);
		boolean isEqualsp3 = rotatedArray[2].close2equals(rotatedtp3);

		
		assertEquals(isEqualsp1,true);
		assertEquals(isEqualsp2,true);
		assertEquals(isEqualsp3,true);
	}

	@Test
	void testGetPoints() {
	
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);

		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);

		Polygon2D poly1 = new Polygon2D (poly);
		
		Point2D[] arrayPoints = poly1.getPoints();
		assertEquals(arrayPoints[0],tp1);
		assertEquals(arrayPoints[1],tp2);
		assertEquals(arrayPoints[2],tp3);
	}
	

	@Test
	void testGetPointsx() {
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);

		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);

		Polygon2D poly1 = new Polygon2D (poly);
		
		Point2D[] arrayPoints = poly1.getPoints();
		assertEquals(arrayPoints[0].x(),tp1.x(),0);
		assertEquals(arrayPoints[1].x(),tp2.x(),0);
		assertEquals(arrayPoints[2].x(),tp3.x(),0);
	}

	@Test
	void testGetPointsy() {
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);

		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);

		Polygon2D poly1 = new Polygon2D (poly);
		
		Point2D[] arrayPoints = poly1.getPoints();
		assertEquals(arrayPoints[0].y(),tp1.y(),0);
		assertEquals(arrayPoints[1].y(),tp2.y(),0);
		assertEquals(arrayPoints[2].y(),tp3.y(),0);
	}

	@Test
	void testIsContainCheck() {
		Point2D tp1= new Point2D (1,1);
		Point2D tp2= new Point2D (1,4);
		Point2D tp3= new Point2D (3,6);
		Point2D tp4= new Point2D (0,3);
		ArrayList <Point2D> poly= new ArrayList <Point2D>();
		poly.add(tp1);
		poly.add(tp2);
		poly.add(tp3);

		Polygon2D poly1 = new Polygon2D (poly);
		boolean testFalse =poly1.isContainCheck(tp1, tp2, tp3);
		boolean testTrue =poly1.isContainCheck(tp1, tp2, tp4);
		assertEquals(testFalse,false);
		assertEquals(testTrue,true);
	}

}