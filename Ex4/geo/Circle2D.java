package Exe.Ex4.geo;

/** 
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle2D implements GeoShapeable{
	private Point2D _center;
	private double _radius;
	//Defining that Circle2D is created by a center point and a radius
	public Circle2D(Point2D cen, double rad) {
		this._center = new Point2D(cen);
		this._radius = rad;
	}
	//A function that getting the radius of the circle
	public double getRadius() {return this._radius;}
	@Override
	//A function that write the circle details on a string and send it 
	public String toString()
	{ return _center.toString()+", "+_radius;}
	@Override
	//A function that check if the point ot is inside the circle
	public boolean contains(Point2D ot) {
		double dist = ot.distance(this._center);
		return dist<=this._radius;
	}

	@Override
	//A function that computes the area of the circle
	public double area() {
		double ans = Math.PI * Math.pow(this._radius, 2);
		return ans;
	}
	@Override
	//A function that computes the perimeter of the circle
	public double perimeter() {
		double ans = Math.PI * 2 * this._radius;
		return ans;
	}
	@Override
	//A function that move the circle by a vector
	public void move(Point2D vec) {
		_center.move(vec);
	}
	@Override
	//A function that create a copy of the circle in another memory
	public GeoShapeable copy() {
		return new Circle2D(_center, _radius);
	}
	@Override
	//A function that get the points of the circle, the center point and the highest point by Y axle
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[2];
		ans[0] =new Point2D(this._center);
		ans[1] = new Point2D(ans[0].x(), ans[0].y()+this._radius);
		return ans;
	}
	@Override
	//A function that change the scale of the circle by ratio and moved it a bit to the center of the clickpoint
	public void scale(Point2D center, double ratio) {
		//////////add your code below ///////////
		this._center.scale(center, ratio);
		this._radius= this._radius * ratio;

		//////////////////////////////////////////
	}
	//A function that rotate the circle by center axle and degrees
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		//////////add your code below ///////////
		this._center.rotate(center, angleDegrees);
		//////////////////////////////////////////
	}

}
