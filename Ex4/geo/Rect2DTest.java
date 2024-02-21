package Exe.Ex4.geo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class Rect2DTest {
	Point2D Origin= new Point2D (0,0);
	Point2D tp1= new Point2D (4,4);
	Point2D tp2= new Point2D (8,8);
	Point2D tp3= new Point2D (1,2);
	Point2D tp4= new Point2D (4,5);
	Point2D moveVec = new Point2D (0,1);
	Point2D scaleTo = new Point2D (4,0);
	Rect2D tRect1 = new Rect2D (tp1, tp2);
	Rect2D tRect2 = new Rect2D (tp3, tp4);


	@Test
	public void testContains() {
		Point2D tpFalse= new Point2D (9,9);
		Point2D tpTrue= new Point2D (6,6);
		assertEquals(tRect1.contains(tpTrue),true);
		assertEquals(tRect1.contains(tpFalse),false);
	}

	@Test
	public void testArea() {
		assertEquals(tRect1.area(),16,0);
		assertEquals(tRect2.area(),9,0);
	}

	@Test
	public void testPerimeter() {
		assertEquals(tRect1.perimeter(),16,0);
		assertEquals(tRect2.perimeter(),12,0);
	}

	@Test
	public void testToString() {
		String tR1 =tRect1.toString();
		Point2D[] arrayOriginal = tRect1.getAllPoints();
		String tR1check = arrayOriginal[0].toString() + "," + arrayOriginal[1].toString() + "," + arrayOriginal[2].toString() + "," + arrayOriginal[3].toString();
		assertEquals(tR1,tR1check);
	}

	@Test
	public void testMove() {
		tRect1.move(moveVec);
		Point2D movedtp1= new Point2D (4,5);
		Point2D movedtp2= new Point2D (8,9);
		Point2D movedtp3= new Point2D (8,5);
		Point2D movedtp4= new Point2D (4,9);
		Point2D[] arrayMoved = tRect1.getAllPoints();
		assertEquals(arrayMoved[0],movedtp1);
		assertEquals(arrayMoved[1],movedtp3);
		assertEquals(arrayMoved[2],movedtp2);
		assertEquals(arrayMoved[3],movedtp4);
	}

	@Test
	public void testCopy() {
		Rect2D tRect1Copy;
		tRect1Copy = (Rect2D) tRect1.copy();
		Point2D[] arrayOriginal = tRect1.getPoints();
		Point2D[] arrayCopy = tRect1Copy.getPoints();
		assertEquals(arrayCopy[0],arrayOriginal[0]);
		assertEquals(arrayCopy[1],arrayOriginal[1]);

	}

	@Test
	public void testScale() {
		tRect1.scale(scaleTo, 0.90);

		Point2D scaletp1= new Point2D (4,3.6);
		Point2D scaletp2= new Point2D (7.6,7.2);
		Point2D scaletp3= new Point2D (7.6,3.6);
		Point2D scaletp4= new Point2D (4,7.2);

		Point2D[] scaledArray = tRect1.getAllPoints();

		assertEquals(scaledArray[0],scaletp1);
		assertEquals(scaledArray[1],scaletp3);
		assertEquals(scaledArray[2],scaletp2);
		assertEquals(scaledArray[3],scaletp4);

	}

	@Test
	public void testRotate() {
		tRect1.rotate(Origin, 90);
		Point2D rotatedtp1= new Point2D (-4,4);
		Point2D rotatedtp2= new Point2D (-8,8);
		Point2D rotatedtp3= new Point2D (-4,8);
		Point2D rotatedtp4= new Point2D (-8,4);
		Point2D[] rotatedArray = tRect1.getAllPoints();

		boolean isEqualsp1 = rotatedArray[0].close2equals(rotatedtp1);
		boolean isEqualsp2 = rotatedArray[1].close2equals(rotatedtp3);
		boolean isEqualsp3 = rotatedArray[2].close2equals(rotatedtp2);
		boolean isEqualsp4 = rotatedArray[3].close2equals(rotatedtp4);

		assertEquals(isEqualsp1,true);
		assertEquals(isEqualsp2,true);
		assertEquals(isEqualsp3,true);
		assertEquals(isEqualsp4,true);

	}

	@Test
	public void testGetPoints() {
		Rect2D tRect2Copy;
		tRect2Copy = (Rect2D) tRect2.copy();
		Point2D[] arrayOriginal = tRect2.getPoints();
		Point2D[] arrayCopy = tRect2Copy.getPoints();
		assertEquals(arrayCopy[0],arrayOriginal[0]);
		assertEquals(arrayCopy[1],arrayOriginal[1]);

	}
}
