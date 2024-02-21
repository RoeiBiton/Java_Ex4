package Exe.Ex4.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class Circle2DTest {
	Point2D center1= new Point2D (5,5);
	Point2D center2= new Point2D (0,0);
	double rad1 = 2;
	double rad2 = 4;
	Circle2D circle1 = new Circle2D (center1, rad1);
	Circle2D circle2 = new Circle2D (center2, rad2);
	double EPS = 0.0001;
	
	@Test
	void testGetRadius() {
		assertEquals(circle1.getRadius(), 2,0); 
	}

	@Test
	void testToString() {
		String testString = circle1.toString();
		
		String tT1check = center1.toString()+", "+rad1;
	assertEquals(testString,tT1check);
	}
	

	@Test
	void testContains() {
		Point2D tpFalse= new Point2D (0,0);
		Point2D tpTrue= new Point2D (6,6); 
		Point2D tpTrue2= new Point2D (7,5); 
		assertEquals(circle1.contains(tpTrue),true);
		assertEquals(circle1.contains(tpFalse),false);
		assertEquals(circle1.contains(tpTrue2),true);
	}
	
	@Test
	void testArea() {
		assertEquals(circle1.area(),12.56637,EPS);
		assertEquals(circle2.area(),50.26548,EPS); 
	}

	@Test
	void testPerimeter() {
		assertEquals(circle1.perimeter(),12.56637,EPS);
		assertEquals(circle2.perimeter(),25.13274,EPS);
	}

	@Test
	void testMove() {
		Point2D vec= new Point2D (1,0); 
		circle1.move(vec);
		Point2D[] arrayMove = circle1.getPoints();
		Point2D newCenter= new Point2D (6,5);
		Point2D newRad= new Point2D (6,7); 
		assertEquals(arrayMove[0],newCenter);
		assertEquals(arrayMove[1],newRad);
		
		Point2D vec2= new Point2D (-1,0); 
		circle2.move(vec2);
		Point2D[] arrayMove2 = circle2.getPoints();
		Point2D newCenter2= new Point2D (-1,0);
		Point2D newRad2= new Point2D (-1,4); 
		assertEquals(arrayMove2[0],newCenter2);
		assertEquals(arrayMove2[1],newRad2);
		
	}

	@Test
	void testCopy() {
		Circle2D circle1Copy = (Circle2D) circle1.copy();
		Point2D[] arrayOriginal = circle1.getPoints();
		Point2D[] arrayCopy = circle1Copy.getPoints();
		assertEquals(arrayCopy[0],arrayOriginal[0]);
		assertEquals(arrayCopy[1],arrayOriginal[1]);
	}

	@Test
	void testGetPoints() {
		Point2D[] arrayPoints = circle1.getPoints();
		assertEquals(arrayPoints[0],center1);
		Point2D highest = new Point2D (center1.x(), center1.y()+rad1);
		assertEquals(arrayPoints[1],highest);
	}

	@Test
	void testScale() {
		Point2D center= new Point2D (5,0);
		
		circle1.scale(center, 1.1);
	
		Point2D[] array11Scale = circle1.getPoints();
		Point2D p11= new Point2D (5,5.5);
		Point2D p12= new Point2D (5,7.7); 
	
	
		assertEquals(array11Scale[0],p11);
		assertEquals(array11Scale[1],p12);

		
	}

	@Test
	void testRotate() {
		Point2D Origin = new Point2D (0,0);
		circle1.rotate(Origin, 90);
		Point2D rotatedtp1= new Point2D (-5,5);
		Point2D rotatedtp2= new Point2D (-5,7);
		

		Point2D[] rotatedArray = circle1.getPoints();
		
		boolean isEqualsp1 = rotatedArray[0].close2equals(rotatedtp1);
		boolean isEqualsp2 = rotatedArray[1].close2equals(rotatedtp2);
	
		assertEquals(isEqualsp1,true);
		assertEquals(isEqualsp2,true);
		
		
		
	}

	

}