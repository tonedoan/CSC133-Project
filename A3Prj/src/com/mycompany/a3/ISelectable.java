package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * The ISelectable interface defines the contract for objects that can be selected, 
 * tested for selection, and drawn on the screen based on their selection state. 
 * This is used to enable selection and interaction with game objects in the game world.
 */
public interface ISelectable {
    
    /**
     * Marks the object as selected or not selected.
     *
     * @param b true to mark the object as selected, false to unselect it
     */
    public void setSelected(boolean b);
    
    /**
     * Checks whether the object is currently selected.
     *
     * @return true if the object is selected, false otherwise
     */
    public boolean isSelected();
    
    /**
     * Determines if a given pointer is inside the boundaries of the object.
     *
     * @param pPtrRelPrnt the position of the pointer relative to the object's parent
     * @param pCmpRelPrnt the position of the component relative to its parent
     * @return true if the pointer is inside the object, false otherwise
     */
    public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
    
    /**
     * Draws the object on the screen, with different drawing styles depending on whether it is selected.
     *
     * @param g the Graphics object used for drawing
     * @param pCmpRelPrnt the position of the component relative to its parent
     */
    public void draw(Graphics g, Point pCmpRelPrnt);
}

