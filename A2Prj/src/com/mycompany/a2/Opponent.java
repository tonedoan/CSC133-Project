package com.mycompany.a2;

import java.util.Random;

public abstract class Opponent extends GameObject implements IMoving{
	protected int direction;
	protected int speed;
    protected final int constant = 1;
    Random rand = new Random();

    /**
     * Constructor to initialize maxWidth and maxHeight for boundary checks.
     * 
     * @param maxWidth the maximum width of the game area
     * @param maxHeight the maximum height of the game area
     */
    public Opponent(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.direction = rand.nextInt(360); // Initialize with a random direction
        this.speed = 5; // Default speed (can be adjusted)
    }

    
    /**
     * Moves the opponent based on its current speed and direction.
     * Updates the opponent's position by calculating the change in x and y coordinates.
     */
    public void move() {
        double theta = 90 - direction;
        double thetaRadians = Math.toRadians(theta);
        double deltaX = Math.cos(thetaRadians) * speed;
        double deltaY = Math.sin(thetaRadians) * speed;

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

        // Randomly adjust the direction
        int addSub = rand.nextInt(2); // Changed to 2 to allow equal chance of +5 or -5
        if (addSub == 1) {
            this.direction += 5;
        } else {
            this.direction -= 5;
        }

        // Normalize the direction to keep it within 0-360 degrees
        if (this.direction >= 360) {
            this.direction -= 360;
        } else if (this.direction < 0) {
            this.direction += 360;
        }
    }

    
    /**
     *  Sets size of Opponent but opponents aren't supposed to change size after creation.
     */
    @Override
    public void setSize(int size) {
    }
}
