package Exe.Ex4.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	private Point2D _p4;
	private Point2D[] rectPoints= new Point2D[4];
	private double EPS = 0.0001;
	
	// A constructor that define that rectangle is created by two points
	public Rect2D(Point2D p1, Point2D p3) {
		this._p1= new Point2D (p1);
		this._p2= new Point2D (p3.x(),p1.y());
		this._p3= new Point2D (p3);
		this._p4= new Point2D (p1.x(),p3.y());

		rectPoints[0] = this._p1;
		rectPoints[1] = this._p2;
		rectPoints[2] = this._p3;
		rectPoints[3] = this._p4;
	}
	//A function that write the rectangle points on a string and send it 
	public String toString(){
	String ans = _p1.toString() + "," + _p2.toString() + "," + _p3.toString() + "," + _p4.toString();
		return ans;
}
	@Override
	// A function that check if the segment contain "ot" point
	public boolean contains(Point2D ot) {
		boolean isContain= false;
		int counter = 0;
		//For loop that send each 2 points in the triangle to a sub function that checks how many time the point X axle cross the triangle shape
		for(int i =0; i<rectPoints.length-1; i++) {
			Point2D p1= rectPoints[i];
			Point2D p2= rectPoints[i+1];
			isContain= isContainCheck(p1, p2, ot);
			if(isContain==true) {
				counter++;
			}
		}
		//Checking the first point and the last
		if(isContainCheck(rectPoints[3], rectPoints[0],ot)==true) {
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
	//A function that computes the area of the rectangle
	public double area() {

		double area = 0;
		double a=0;
		double b=0;

		for (int i=0; i<rectPoints.length-1; i++){
		a +=rectPoints[i].x()* rectPoints[i+1].y();
		b +=rectPoints[i].y()* rectPoints[i+1].x();

		}
		a +=rectPoints[3].x()* rectPoints[0].y();
		b +=rectPoints[3].y()* rectPoints[0].x();
		area = Math.abs(a-b);
		area = area/2;
		return area;
	}

	@Override
	//A function that computes the area of the rectangle
	public double perimeter() {
		double perimeter = 0;

		for(int i =0; i<rectPoints.length-1; i++) {
			Point2D p1= rectPoints[i];
			Point2D p2= rectPoints[i+1];
			double dist = p1.distance(p2);
			perimeter =perimeter + dist;
		}
		perimeter =perimeter +( rectPoints[3].distance(rectPoints[0]));

		// TODO Auto-generated method stub
		return perimeter;

	}

	@Override
	// A function that move all the Points of the rectangle in a vector
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
		_p4.move(vec);
		// TODO Auto-generated method stub

	}

	@Override
	//A function that creates a copy of the rectangle in another place of memory
	public GeoShapeable copy() {
		return new Rect2D(_p1,_p3);
	}

	@Override
	// A function that change the scale of the rectangle by a center and a ratio
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
		this._p4.scale(center, ratio);
	}

	@Override
	// A function that rotate the rectangle by a center axle and angle degrees
	public void rotate(Point2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
		this._p4.rotate(center, angleDegrees);
		// TODO Auto-generated method stub

	}

	@Override
	// A function that gets an array of the rectangle "CREATION" points
	public Point2D[] getPoints() {
		Point2D[] pArray = new Point2D[2];
		pArray[0]=this._p1;
		pArray[1]=this._p3;
		// TODO Auto-generated method stub
		return pArray;
	}
	// A function that gets an array of the ALL the rectangle points
	public Point2D[] getAllPoints() {

		rectPoints[0]=this._p1;
		rectPoints[1]=this._p2;
		rectPoints[2]=this._p3;
		rectPoints[3]=this._p4;
		// TODO Auto-generated method stub
		return rectPoints;
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
