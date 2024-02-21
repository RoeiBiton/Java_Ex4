package Exe.Ex4.geo;

import java.util.ArrayList;


/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	private ArrayList <Point2D> polPoints= new ArrayList <Point2D>();
	private ArrayList <Double> polPointsX= new ArrayList <Double>();
	private ArrayList <Double> polPointsY= new ArrayList <Double>();
	private double EPS = 0.0001;
	
	//A function that define the polygon as a shape that created by Array list of points
	public Polygon2D(ArrayList <Point2D> curPoints) {
		this.polPoints=new ArrayList <Point2D> (curPoints);
		for(int i=0; i<curPoints.size();i++) {
			Point2D p = curPoints.get(i);
			//create an array of X values of all points
			polPointsX.add(i, p.x());
			//create an array of Y values of all points
			polPointsY.add(i, p.y());
		}
	}
	////A function that write the polygon Points on a string and send it 
	public String toString() {
		String ans = "";
		for (int i = 0; i < this.polPoints.size(); i++) {
			if (i != this.polPoints.size() - 1) {
				ans += this.polPoints.get(i).toString() + ",";
			} else {
				ans += this.polPoints.get(i).toString() ;
			}
		}
		return ans;
	}
	@Override
	//A function that check if the polygon contain this "ot" point inside it
	public boolean contains(Point2D ot) {
		boolean isContain= false;
		int counter = 0;
		//For loop that send each 2 points in the polygon to a sub function that checks how many time the point X axle cross the polygon shape
		for(int i =0; i<polPoints.size()-1; i++) {
			Point2D p1= polPoints.get(i);
			Point2D p2= polPoints.get(i+1);
			isContain= isContainCheck(p1, p2, ot);
			if(isContain==true) {
				counter++;
			}
		}
		//Checking the first point and the last
		if(isContainCheck(polPoints.get(polPoints.size()-1), polPoints.get(0),ot)==true) {
			counter++;
		}
		//checking if the number of time that the point X axle cross the polygon is divide by 2, if it is its not contain, else its contain
		if(counter%2!=0) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	//A function that computes the area of the polygon
	public double area() {
		// TODO Auto-generated method stub
		double area = 0;
		double a=0;
		double b=0;
		
		for (int i=0; i<polPoints.size()-1; i++){
		a +=polPoints.get(i).x()* polPoints.get(i+1).y();
		b +=polPoints.get(i).y()* polPoints.get(i+1).x();

		}
		a +=polPoints.get(polPoints.size()-1).x()*polPoints.get(0).y();
		b +=polPoints.get(polPoints.size()-1).y()*polPoints.get(0).x();
		area = Math.abs(a-b);
		area = area/2;
		return area;
	}

	@Override
	//A function that computes the perimeter of the polygon
	public double perimeter() {
		double perimeter = 0;
		//for loop that checking the distance between every two points
		for(int i =0; i<polPoints.size()-1; i++) {
			Point2D p1= polPoints.get(i);
			Point2D p2= polPoints.get(i+1);
			double dist = p1.distance(p2);
			perimeter =  perimeter + dist;
		}
		perimeter = perimeter +(polPoints.get(polPoints.size()-1).distance(polPoints.get(0)));

		// TODO Auto-generated method stub
		return perimeter;

	}

	@Override
	// A function that move all the Points of the polygon in a vector
	public void move(Point2D vec) {
		for(int i=0; i<this.polPoints.size();i++) {
			polPoints.get(i).move(vec);
		}
		// TODO Auto-generated method stub

	}

	@Override
	//A function that creates a copy of the polygon in another place of memory
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Polygon2D(polPoints);
	}

	@Override
	// A function that change the scale of the polygon by a center and a ratio
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		for(int i=0; i<this.polPoints.size();i++) {
			polPoints.get(i).scale(center, ratio);;
		}
	}

	@Override
	// A function that rotate the polygon by a center axle and angle degrees
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		for(int i=0; i<this.polPoints.size();i++) {
			polPoints.get(i).rotate(center, angleDegrees);;
		}
	}

	@Override
	// a function that get the points of the polygon as array
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] polyPointsArray = new Point2D[polPoints.size()];
		for(int i=0; i<polyPointsArray.length;i++) {
			polyPointsArray[i]=polPoints.get(i);
		}
		return polyPointsArray;
	}
	// A function that get the points X values of the polygon as array
	public double[] getPointsx() {
		// TODO Auto-generated method stub
		double[] PointsX = new double[this.polPoints.size()];
		for(int i=0; i<this.polPoints.size();i++) {
			Point2D p = polPoints.get(i);
			PointsX[i]=p.x();
		}
		return PointsX;
	}
	// A function that get the points Y values of the polygon as array
	public double[] getPointsy() {
		// TODO Auto-generated method stub
		double[] PointsY = new double[this.polPoints.size()];
		for(int i=0; i<this.polPoints.size();i++) {
			Point2D p = polPoints.get(i);
			PointsY[i]=p.y();
		}
		return PointsY;
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
			if((Math.abs(p1.x()-p2.x())==0) && (p3.x()<=p1.x())) {
				System.out.println("true");
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
