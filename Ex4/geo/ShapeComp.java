package Exe.Ex4.geo;

import java.util.ArrayList;
import java.util.Comparator;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shapeable>{
	//////////add your code below ///////////

	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shapeable> CompByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
	public static final Comparator<GUI_Shapeable> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shapeable> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
	public static final Comparator<GUI_Shapeable> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shapeable> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shapeable> CompByPerimeter= new ShapeComp(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;

	}
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		//////////add your code below ///////////

		if(_flag == Ex4_Const.Sort_By_Anti_toString) {
			ans = o1.toString().compareTo(o2.toString());
			if(ans==1) {
				ans=-1;
			}
			if(ans==-1) {
				ans=1;
			}
		}
		if(_flag == Ex4_Const.Sort_By_Tag) {
			int t1 = o1.getTag();
			int t2 = o2.getTag();
			if(t1<t2) {ans = -1;}
			if(t1>t2) {ans = 1;}
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Tag) {
			int t1 = o1.getTag();
			int t2 = o2.getTag();
			if(t1>t2) {ans = -1;}
			if(t1<t2) {ans = 1;}
		}
		if(_flag == Ex4_Const.Sort_By_Area) {

			double d1 = o1.getShape().area();
			double d2 = o2.getShape().area();
			if(d1<d2) {ans = -1;}
			if(d1>d2) {ans = 1;}
			if(d1==d2) {ans = 0;}


		}
		if(_flag == Ex4_Const.Sort_By_Anti_Area) {

			double d1 = o1.getShape().area();
			double d2 = o2.getShape().area();
			if(d1>d2) {ans  = -1;}
			if(d1<d2) {ans = 1;}
			if(d1==d2) {ans = 0;}
		}
		if(_flag == Ex4_Const.Sort_By_Perimeter) {

			double d1 = o1.getShape().perimeter();
			double d2 = o2.getShape().perimeter();
			if(d1<d2) {ans  = -1;}
			if(d1>d2) {ans = 1;}
			if(d1==d2) {ans = 0;}
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {

			double d1 = o1.getShape().perimeter();
			double d2 = o2.getShape().perimeter();
			if(d1>d2) {ans  = -1;}
			if(d1<d2) {ans = 1;}
			if(d1==d2) {ans = 0;}
		}
		//////////////////////////////////////////
		return ans;
	}

}
