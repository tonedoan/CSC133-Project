package com.mycompany.a2;

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
}
