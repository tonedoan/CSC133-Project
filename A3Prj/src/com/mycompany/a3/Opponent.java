package com.mycompany.a3;

import java.util.Random;

public abstract class Opponent extends GameObject implements IMoving{
	protected GameWorld gw;
	protected int direction;
	protected int speed;
	protected int count;
	protected boolean result;
	protected int thisRightX;
	protected int thisLeftX;
	protected int thisTopY;
	protected int thisBottomY;
	protected int otherRightX;
	protected int otherLeftX;
	protected int otherTopY;
	protected int otherBottomY;
    protected final int constant = 10; // Use this to adjust speed of astronauts
    Random rand = new Random();
    protected boolean hasMoved;

    /**
     * Constructor to initialize maxWidth and maxHeight for boundary checks.
     * 
     * @param maxWidth the maximum width of the game area
     * @param maxHeight the maximum height of the game area
     * @param gw 
     */
    public Opponent(int maxWidth, int maxHeight, GameWorld gw) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.direction = rand.nextInt(360); // Initialize with a random direction
        this.speed = 5; // Default speed (can be adjusted)
        this.gw = gw;
    }

    
    /**
     * Moves the opponent based on its current speed and direction.
     * Updates the opponent's position by calculating the change in x and y coordinates.
     * @param timerSec passes the timer's millisecond
     */
    public void move(int timerSec) {
    	// Check if the opponent should move (speed should be greater than 0)
        if (speed <= 0) {
            return;  // If speed is 0, stop movement and exit the method
        }
        double theta = 90 - direction;
        double thetaRadians = Math.toRadians(theta);
        double deltaX = Math.cos(thetaRadians) * speed * (timerSec/1000.0); 
        double deltaY = Math.sin(thetaRadians) * speed * (timerSec/1000.0);
        
        if (rand.nextInt(100) < 10) { // 10% chance to change direction
            direction += rand.nextInt(30) - 15; // Random change between -15 and +15 degrees
        }

        // Update position
        float newX = point.getX() + (float) deltaX;
        float newY = point.getY() + (float) deltaY;

        // Check boundaries and wrap or bounce
        if (newX < 0) {
            newX = 0; // Stop at left boundary
            direction += 180; // Reverse direction
        } else if (newX > maxWidth) {
            newX = maxWidth; // Stop at right boundary
            direction += 180; // Reverse direction
        }

        if (newY < 0) {
            newY = 0; // Stop at top boundary
            direction += 180; // Reverse direction
        } else if (newY > maxHeight) {
            newY = maxHeight; // Stop at bottom boundary
            direction += 180; // Reverse direction
        }

        // Update the point with the new position
        point.setX(newX);
        point.setY(newY);

        // Normalize the direction to keep it within 0-360 degrees
        if (this.direction >= 360) {
            this.direction -= 360;
        } else if (this.direction < 0) {
            this.direction += 360;
        }
        
        gw.notifyMovement();
        count = 0;	// Reset count after the move
    }

    
    /**
     *  Sets size of Opponent but opponents aren't supposed to change size after creation.
     */
    @Override
    public void setSize(int size) {
    }
}
