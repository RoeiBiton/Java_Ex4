package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;

import Exe.Ex4.geo.GeoShapeable;


public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	//A constructor that define what GUIShape is created from
	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	// define a GUIShape
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	
	@Override
	//A function that gets the shape _g
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	//A function that checking if the shape is Filled
	public boolean isFilled() {
		return _fill;
	}

	@Override
	//A function that define if the shape will be filled or not
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	//A function that checking the color of the shape
	public Color getColor() {
		return _color;
	}

	@Override
	//A function that set color to the shape
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	//A function that gets the tag of the shape
	public int getTag() {
		return _tag;
	}

	@Override
	//A function that set tag to the shape
	public void setTag(int tag) {
		_tag = tag;
		
	}

	@Override
	// A function that create a copy of the shape 
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}
	@Override
	//A function that create a String of the data of the shape,
	//for Example (GUIShape, 100(number of color), true(isFilled), "5"(tagNumber), Cricle, Center point and radius)
	public String toString() {
		
			String shape=	_g.getClass().getSimpleName();
			return "GUIShape" + "," + _color.getRGB() + "," + _fill + "," + _tag + "," + shape + "," + _g.toString();
		
	}
	private void init(String[] ww) {

	}
	@Override
	//A function that checking if the shape is selected or not
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	//A function that define if the shape will be selected or not
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	@Override
	//A function that set the shape of _g
	public void setShape(GeoShapeable g) {
		// TODO Auto-generated method stub
		_g = g;
	}
}
