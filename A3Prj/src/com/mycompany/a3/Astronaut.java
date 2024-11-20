package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * The Astronaut class represents an astronaut in the game world.
 * Astronauts have health, speed, and can move within the game world.
 */
public class Astronaut extends Opponent {
    private int health;
    private int blue;
    private boolean vulnerable = true;

    /**
     * Constructs a new Astronaut with randomized properties such as size, location, and direction.
     * Sets default values for health and color.
     *
     * @param maxWidth  the maximum width for the astronaut's spawning area
     * @param maxHeight the maximum height for the astronaut's spawning area
     */
    public Astronaut(int maxWidth, int maxHeight, GameWorld gw) {
        super(maxWidth, maxHeight, gw);
        blue = 125;
        //size = rand.nextInt(31) + 20; // Set size to a random number between 20-50
        size = 100;
        float xPoint = rand.nextFloat() * maxWidth; // Ensure within bounds
        float yPoint = rand.nextFloat() * maxHeight; // Ensure within bounds
        point = new Point(xPoint, yPoint);
        color = ColorUtil.rgb(0, 0, blue);
        health = 5;
        speed = health * constant; // Speed based on health
        direction = rand.nextInt(360);
        type = "Astronaut";
    }

    /**
     * Gets the current health of the astronaut.
     *
     * @return the health value of the astronaut.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Sets the health value of the astronaut.
     *
     * @param health the new health value to set.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Fades the color of the astronaut by decreasing the blue component of its color.
     * This simulates a visual indication of decreasing health.
     */
    public void fadeColor() {
        this.blue += 40; // Increase blue value
        if (this.blue > 255) {
            this.blue = 255; // Ensure blue does not go below 0
        }
        // Update the color using the modified blue component
        this.color = ColorUtil.rgb(0, 100, this.blue);
        this.speed -= 10;
    }

    /**
     * Returns a string representation of the astronaut's state, including its location, color, size, speed, direction, and health.
     *
     * @return a string representing the state of the astronaut.
     */
    @Override
    public String toString() {
        return type + ": loc=" + Math.round(point.getX() * 10.0) / 10.0 + ", " + Math.round(point.getY() * 10.0) / 10.0 + 
               " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + ", " + ColorUtil.blue(color) + 
               "] size=" + size + " speed=" + speed + " dir=" + direction + " health=" + health;
    }

    /**
     * Sets the speed of the astronaut.
     *
     * @param speed the new speed value to set.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Gets the current speed of the astronaut.
     *
     * @return the speed value of the astronaut.
     */
    public int getSpeed() {
        return this.speed;
    }
    
    /**
     * Draws an Astronaut shape in the container.
     * Astronauts are isosceles triangles.
     * @param g is what is going to be drawn
     * @param pCmpRelPrnt is the location of where it will be drawn
     */
    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
    	int[] xPoints = new int[3];
    	int[] yPoints = new int[3];
    	// Adjust position of the object by calculating the points relative to its center
        xPoints[0] = (int)(pCmpRelPrnt.getX() - size / 2); // Left point of the triangle
        xPoints[1] = (int)pCmpRelPrnt.getX();              // Top point (centered)
        xPoints[2] = (int)(pCmpRelPrnt.getX() + size / 2); // Right point of the triangle
        
        yPoints[0] = (int)(pCmpRelPrnt.getY() - size / 2); // Top point of the triangle
        yPoints[1] = (int)(pCmpRelPrnt.getY() + size / 2); // Bottom point of the triangle (pointed down)
        yPoints[2] = (int)(pCmpRelPrnt.getY() - size / 2); // Same as yPoints[0]

    	g.setColor(color);
    	g.fillPolygon(xPoints, yPoints, 3);
    }
    
    @Override
    public void handleCollision(GameObject otherObject) {
    	if(otherObject instanceof Alien) {
    		if(vulnerable) {
        		gw.attack(this);
        		setVul(false);
        	}
    	}
    }
    
    public void setVul(boolean value) {
    	this.vulnerable = value;
    }
}
