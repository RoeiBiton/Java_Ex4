package Exe.Ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	private Point2D _P1;
	private Point2D _P2;
	private Point2D _P3;
	private Point2D[] triPoints= new Point2D[3];
	private double EPS = 0.0001;
	// A constructor that define triangle by 3 points
	public Triangle2D(Point2D p1 , Point2D p2, Point2D p3) {
		this._P1 = new Point2D (p1);
		this._P2 = new Point2D (p2);
		this._P3 = new Point2D (p3);
		// creating an array of the triangle points
		triPoints[0] = this._P1;
		triPoints[1] = this._P2;
		triPoints[2] = this._P3;

	}
	//A function that write the triangle points on a string and send it 
	public String toString() {
		String ans = _P1.toString() + "," + _P2.toString() + "," + _P3.toString();
		return ans;
	}
	@Override
	// A function that check if the triangle contain the point ot
	public boolean contains(Point2D ot) {
		boolean isContain= false;
		int counter = 0;
		//For loop that send each 2 points in the triangle to a sub function that checks how many time the point X axle cross the triangle shape
		for(int i =0; i<triPoints.length-1; i++) {
			Point2D p1= triPoints[i];
			Point2D p2= triPoints[i+1];
			isContain= isContainCheck(p1, p2, ot);
			if(isContain==true) {
				counter++;
			}
		}
		//Checking the first point and the last
		if(isContainCheck(triPoints[2], triPoints[0],ot)==true) {
			counter++;
		}
		//checking if the number of time that the point X axle cross the polygon is divide by 2, if it is its not contain, else its contain
		if(counter%2!=0) {
			return true;
		}
		return false;
		// TODO Auto-generated method stub
	}

	@Override
	//A function that computes the area of the triangle
	public double area() {
		// TODO Auto-generated method stub
		double area = 0;
		double a=0;
		double b=0;

		for (int i=0; i<triPoints.length-1; i++){
		a +=triPoints[i].x()* triPoints[i+1].y();
		b +=triPoints[i].y()* triPoints[i+1].x();

		}
		a +=triPoints[2].x()*triPoints[0].y();
		b +=triPoints[2].y()*triPoints[0].x();
		area = Math.abs(a-b);
		area = area/2;
		return area;
	}

	@Override
	//A function that computes the perimeter of the triangle
	public double perimeter() {
		double perimeter = 0;
		//for loop that checking the distance between every two pointws
		for(int i =0; i<triPoints.length-1; i++) {
			Point2D p1= triPoints[i];
			Point2D p2= triPoints[i+1];
			double dist = p1.distance(p2);
			perimeter =perimeter + dist;
		}
		perimeter = perimeter +( triPoints[2].distance(triPoints[0]));

		// TODO Auto-generated method stub
		return perimeter;
	}

	@Override
	// A function that move all the Points of the trianle in a vector
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		_P1.move(vec);
		_P2.move(vec);
		_P3.move(vec);

	}

	@Override
	//A function that creates a copy of the triangle in another place of memory
	public GeoShapeable copy() {
		return new Triangle2D(_P1, _P2, _P3);
		// TODO Auto-generated method stub
	}

	@Override
	// A function that change the scale of the triangle by a center and a ratio
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		this._P1.scale(center, ratio);
		this._P2.scale(center, ratio);
		this._P3.scale(center, ratio);
	}

	@Override
	// A function that rotate the triangle by a center axle and angle degrees
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		this._P1.rotate(center, angleDegrees);
		this._P2.rotate(center, angleDegrees);
		this._P3.rotate(center, angleDegrees);

	}

	@Override
	// A function that gets an array of the triangle points
	public Point2D[] getPoints() {
		this.triPoints= new Point2D[3];
		triPoints[0] = this._P1;
		triPoints[1] = this._P2;
		triPoints[2] = this._P3;
		// TODO Auto-generated method stub
		return triPoints;
	}
	// A function that check if the horizontal axle of p3 is crossing the segment of p1 and p2
	public boolean isContainCheck(Point2D p1, Point2D p2, Point2D p3) {
		//define maximum and minimum Y  values of p1 and p2
		double maxY = Math.max(p1.y(), p2.y());
		double minY = Math.min(p1.y(), p2.y());
		// define dist12 as the distance between p1 and p2
		double dist12= p1.distance(p2);
		//checking if p3 is on the "segment" of p1 and p2
		if((p3.distance(p1)+p3.distance(p2))-dist12<=EPS) {
			return true;
		}
		//Checking if the Y value of p3 is between the Y values of p1 and p2
		if(p3.y()>minY && p3.y()<maxY) {
			//checking a case of p1 and p2 a verticle segment (with the same X value)
			if(Math.abs(p1.x()-p2.x())==0 && p3.x()<p1.x()) {
				return true;
			}
			//checking if p3 is on the left of the segment so it will cross it by axle
			double a = (p2.y()-p1.y())/(p2.x()-p1.x());
			double b = p1.y()-p1.x()*a;
			double x = (p3.y()-b)/a;
			if(x>=p3.x()){
				return true;
			}
		}



		return false;
	}

}
