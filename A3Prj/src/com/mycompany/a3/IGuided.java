package com.mycompany.a3;

import com.codename1.charts.models.Point;

/**
 * Represents an interface for guided entities that can move in various directions
 * and jump to specific locations.
 */
public interface IGuided {
    
    /**
     * Moves the guided entity to the left.
     */
    void moveLeft();
    
    /**
     * Moves the guided entity to the right.
     */
    void moveRight();
    
    /**
     * Moves the guided entity upward.
     */
    void moveUp();
    
    /**
     * Moves the guided entity downward.
     */
    void moveDown();
    
    /**
     * Makes the guided entity jump to a specific location.
     */
    void jumpToLocation();

	/**
	 * Placeholder method for jumping to a specific location.
	 * @param point this.point is set to the point of object.
	 */
	void jumpToLocation(Point target);
}
