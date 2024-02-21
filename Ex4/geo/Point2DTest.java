package Exe.Ex4.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Point2DTest {
	Point2D Origin= new Point2D (0,0);
	Point2D tp1= new Point2D (2,2);
	Point2D tp2= new Point2D (6,2);
	Point2D tp3= new Point2D (4,4);
	Point2D moveVec= new Point2D (2,0);
	@Test
	void testMove() {
		Point2D movedTp1 = new Point2D (4,2);
		tp1.move(moveVec);
		assertEquals(tp1,movedTp1);
		movedTp1.move(moveVec);
		assertEquals(tp2,movedTp1);
		
	}

	@Test
	void testScale() {
		Point2D scaledTp1 = new Point2D (2,1.8);
		tp1.scale(moveVec, 0.90);
		assertEquals(tp1,scaledTp1);

	}

	@Test
	void testRotate() {
		Point2D rotatedTp1 = new Point2D (-2,2);
		tp1.rotate(Origin, 90);
		boolean isEquals = tp1.close2equals(rotatedTp1);
		
		assertEquals(isEquals,true);
	}

}
