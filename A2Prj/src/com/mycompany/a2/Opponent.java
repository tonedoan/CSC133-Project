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
        point.setX(point.getX() + (float) deltaX);
        point.setY(point.getY() + (float) deltaY);
        int addSub = rand.nextInt(1);
        if (addSub == 1) {
            this.direction += 5;
        } else {
            this.direction -= 5;
        }

        // Check boundaries and change direction accordingly
        if (point.getX() == 0) {
            this.direction -= 180;
        } else if (point.getX() == maxWidth) {
            this.direction += 180;
        }

        if (point.getY() == 0) {
            this.direction -= 180;
        } else if (point.getY() == maxHeight) {
            this.direction += 180;
        }
    }
    
    /**
     *  Sets size of Opponent but opponents aren't supposed to change size after creation.
     */
    @Override
    public void setSize(int size) {
    }
}
