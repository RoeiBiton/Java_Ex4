package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Segment2DTest {
	Point2D Origin = new Point2D (0,0);
	Point2D vecMove = new Point2D (3,0);
	Point2D tp1 = new Point2D (2,2);
	Point2D tp2 = new Point2D (4,2);
	Point2D tp1move = new Point2D (5,2);
	Point2D tp2move = new Point2D (7,2);
	Point2D tp12Contain = new Point2D (3,2);
	Point2D tp12NotContain = new Point2D (3,3);
	
	Segment2D segment1 = new Segment2D (tp1, tp2);
	
	

	@Test
	void testToString() {
	String test = segment1.toString();
	String original = tp1.toString()+","+tp2.toString();
	assertEquals(test,original);
	}

	@Test
	void testContains() {
		boolean isContainTrue = segment1.contains(tp12Contain);
		boolean isContainFalse = segment1.contains(tp12NotContain);
		assertEquals(isContainTrue,true);
		assertEquals(isContainFalse,false);
		
		
	}

	@Test
	void testMove() {
		segment1.move(vecMove);
		Point2D[] segPoints = segment1.getPoints();
		assertEquals(segPoints[0],tp1move);
		assertEquals(segPoints[1],tp2move);
	}

	@Test
	void testCopy() {
		Segment2D segCopy = (Segment2D) segment1.copy();
		Point2D[] segPoints = segment1.getPoints();
		Point2D[] segCopyPoints = segCopy.getPoints();
		assertEquals(segPoints[0],segCopyPoints[0]);
		assertEquals(segPoints[1],segCopyPoints[1]);
	}

	@Test
	void testScale() {
		Point2D scale09P1 = new Point2D (2.5,2);
		Point2D scale09P2 = new Point2D (4.3,2);

		segment1.scale(tp2move, 0.90);
		Point2D[] segPoints = segment1.getPoints();
		
		assertEquals(segPoints[0],scale09P1);
		assertEquals(segPoints[1],scale09P2);
		
	}

	@Test
	void testRotate() {
		Point2D rotateAxle = new Point2D (0,2);
		Point2D rotatedP1 = new Point2D (0,4);
		Point2D rotatedP2 = new Point2D (0,6);
		
	segment1.rotate(rotateAxle, 90);
	Point2D[] segPoints = segment1.getPoints();
	boolean isEqualsp1 = segPoints[0].close2equals(rotatedP1);
	boolean isEqualsp2 = segPoints[1].close2equals(rotatedP2);
	assertEquals(isEqualsp1,true);
	assertEquals(isEqualsp2,true);
	}

	@Test
	void testGetPoints() {
		Point2D[] segPoints = segment1.getPoints();
		assertEquals(segPoints[0],tp1);
		assertEquals(segPoints[1],tp2);
		
	}

}
