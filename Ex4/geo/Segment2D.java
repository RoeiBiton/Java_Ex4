package Exe.Ex4.geo;


/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D _P1;
	private Point2D _P2;
	private Point2D[] segPoints= new Point2D[2];
	private double EPS = 0.001;
	
	//A constructor that define that segment is created by two points
	public Segment2D(Point2D p1, Point2D p2) {
		this._P1=p1;
		this._P2=p2;
		
		this.segPoints[0] = p1;
		this.segPoints[1] = p2;
		
		
	}
	//A function that write the segment points on a string and send it 
	public String toString() {
		String ans = _P1.toString()+","+_P2.toString();
		return ans;
	}

	@Override
	// A function that check if the segment contain "ot point in a distance of Epsilon 0.001
	public boolean contains(Point2D ot) {
		double dist12= _P1.distance(_P2);
		if((ot.distance(_P1)+ot.distance(_P2))-dist12<=EPS) {
			return true;
			
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	//A function that move the segment by a vector
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		_P1.move(vec);
		_P2.move(vec);
		
	}

	@Override
	//A function that create a copy of the segment in another place of memory
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Segment2D(_P1,_P2);
	}

	@Override
	//A function that change the scale of the segment by a center and ratio
	public void scale(Point2D center, double ratio) {
		_P1.scale(center,ratio);
		_P2.scale(center,ratio);
		// TODO Auto-generated method stub
		
	}

	@Override
	// A function that rotate the segment by a center axle and angle degrees
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		_P1.rotate(center, angleDegrees);;
		_P2.rotate(center, angleDegrees);;
		
	}

	@Override
	// A function that gets an array of the segment Points
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		return segPoints;
	}
	
}