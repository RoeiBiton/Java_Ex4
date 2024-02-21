package Exe.Ex4.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class Triangle2DTest {
	Point2D Origin= new Point2D (0,0);
	Point2D tp1= new Point2D (2,2);
	Point2D tp2= new Point2D (6,2);
	Point2D tp3= new Point2D (4,4);
	Point2D tp4= new Point2D (6,4);
	Point2D tp5= new Point2D (1,0);
	Point2D tp6= new Point2D (0,0);
	Point2D tp7= new Point2D (10,0);
	Point2D tp8= new Point2D (5,5);
	Point2D tpisContain= new Point2D (4,3);
	Triangle2D tTriangle1 = new Triangle2D (tp1, tp2, tp3);
	Triangle2D tTriangle2 = new Triangle2D (tp1, tp2,  tp4);
	Triangle2D tTriangle3 = new Triangle2D (tp6, tp7,  tp8);
	double EPS = 0.0001;
	
	
	@Test
	void testToString() {
		String testString = tTriangle1.toString();
		Point2D[] arrayOriginal = tTriangle1.getPoints();
		String tT1check = arrayOriginal[0].toString() + "," + arrayOriginal[1].toString() + "," + arrayOriginal[2].toString();
	assertEquals(testString,tT1check);
	}

	@Test
	void testContains() {
		Point2D tpFalse= new Point2D (3,4);
		Point2D tpTrue= new Point2D (4,2); 
		Point2D tpTrue2= new Point2D (4,3); 
		assertEquals(tTriangle1.contains(tpTrue),true);
		assertEquals(tTriangle1.contains(tpFalse),false);
		assertEquals(tTriangle1.contains(tpTrue2),true);
	}

	@Test
	void testArea() {
		assertEquals(tTriangle1.area(),4,0);
		assertEquals(tTriangle2.area(),4,0);
	}

	@Test
	void testPerimeter() {
		assertEquals(tTriangle1.perimeter(),9.65685,EPS);
		assertEquals(tTriangle2.perimeter(),10.47213,EPS);
	}

	@Test
	void testMove() {
		tTriangle1.move(tp5);
		Point2D[] arrayMove = tTriangle1.getPoints();
		Point2D p1= new Point2D (3,2);
		Point2D p2= new Point2D (7,2); 
		Point2D p3= new Point2D (5,4); 
		assertEquals(arrayMove[0],p1);
		assertEquals(arrayMove[1],p2);
		assertEquals(arrayMove[2],p3);
	}

	@Test
	void testCopy() {
		Triangle2D tTriangle1Copy = (Triangle2D) tTriangle1.copy();
		Point2D[] arrayOriginal = tTriangle1.getPoints();
		Point2D[] arrayCopy = tTriangle1Copy.getPoints();
		assertEquals(arrayCopy[0],arrayOriginal[0]);
		assertEquals(arrayCopy[1],arrayOriginal[1]);
		assertEquals(arrayCopy[2],arrayOriginal[2]);
		
	}

	@Test
	void testScale() {
		
		
		Point2D center= new Point2D (5,0);
		
		Triangle2D tTriangle11Scale = tTriangle3;
		tTriangle11Scale.scale(center, 1.1);
	
		Point2D[] array11Scale = tTriangle11Scale.getPoints();
		Point2D p11= new Point2D (-0.5,0);
		Point2D p12= new Point2D (10.5,0); 
		Point2D p13= new Point2D (5,5.5);
	
		assertEquals(array11Scale[0],p11);
		assertEquals(array11Scale[1],p12);
		assertEquals(array11Scale[2],p13);
	}

	@Test
	void testRotate() {
		tTriangle1.rotate(Origin, 90);
		Point2D rotatedtp1= new Point2D (-2,2);
		Point2D rotatedtp2= new Point2D (-2,6);
		Point2D rotatedtp3= new Point2D (-4,4);

		Point2D[] rotatedArray = tTriangle1.getPoints();
		
		boolean isEqualsp1 = rotatedArray[0].close2equals(rotatedtp1);
		boolean isEqualsp2 = rotatedArray[1].close2equals(rotatedtp2);
		boolean isEqualsp3 = rotatedArray[2].close2equals(rotatedtp3);
		
		assertEquals(isEqualsp1,true);
		assertEquals(isEqualsp2,true);
		assertEquals(isEqualsp3,true);
		
		
	}

	@Test
	void testGetPoints() {
		Point2D[] arrayPoints = tTriangle1.getPoints();
		assertEquals(arrayPoints[0],tp1);
		assertEquals(arrayPoints[1],tp2);
		assertEquals(arrayPoints[2],tp3);
	}

	@Test
	void testIsContainCheck() {
		boolean checkTrue = (boolean) tTriangle1.isContainCheck(tp2, tp4, tpisContain);
		boolean checkFalse = (boolean) tTriangle1.isContainCheck(tp2, tp4, tp7);
		assertEquals(checkTrue,true);
		assertEquals(checkFalse,false);
		
	}

}