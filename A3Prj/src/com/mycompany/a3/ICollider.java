package com.mycompany.a3;

/**
 * The ICollider interface defines the contract for objects that are capable of detecting and handling collisions with other game objects.
 * Any class implementing this interface must provide implementations for checking if it collides with another object and for handling that collision.
 */
public interface ICollider {
    
    /**
     * Checks if the current object collides with another game object.
     *
     * @param otherObject the other GameObject to check for collision
     * @return true if a collision occurs, false otherwise
     */
    boolean collidesWith(GameObject otherObject);
    
    /**
     * Handles the collision between the current object and another game object.
     *
     * @param otherObject the other GameObject involved in the collision
     */
    void handleCollision(GameObject otherObject);
}

