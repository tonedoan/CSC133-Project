package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * This interface is used to specify drawing methods.
 * 
 */
public interface IDrawable {
	/**
	 * Draws object g and determine the location by pCmpRelPrnt
	 * @param g
	 * @param pCmpRelPrnt
	 */
	void draw(Graphics g, Point pCmpRelPrnt);
}
