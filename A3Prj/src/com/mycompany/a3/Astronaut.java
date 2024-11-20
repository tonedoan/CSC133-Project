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
    private int px, py, xLoc, yLoc;
    private boolean selected;
    private boolean vulnerable;

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
        selected = false;
        vulnerable = true;
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

        if(!isSelected() || !gw.isPaused()) {
        	g.fillPolygon(xPoints, yPoints, 3);
        } else {
        	g.drawPolygon(xPoints, yPoints, 3);
        }
        if(!gw.isPaused()) {
        	setSelected(false);
        }
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

	@Override
	public void setSelected(boolean b) {
		selected = b;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		px = (int) pPtrRelPrnt.getX();
		py = (int) pPtrRelPrnt.getY();
		xLoc = (int) pCmpRelPrnt.getX() + (int) point.getX() - size / 2;
		yLoc = (int) pCmpRelPrnt.getY() + (int) point.getY() - size / 2;
		// Calculate the three vertices of the triangle
	    int x0 = xLoc, y0 = yLoc + size;
	    int x1 = xLoc + size, y1 = yLoc + size;
	    int x2 = xLoc + size / 2, y2 = yLoc;

	    // Check if the point is inside the triangle using the area method
	    int area = Math.abs((x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0)); // Total area of the triangle
	    int area1 = Math.abs((px - x0) * (y1 - y0) - (x1 - x0) * (py - y0)); // Area with point and edge (x0, y0) -> (x1, y1)
	    int area2 = Math.abs((px - x1) * (y2 - y1) - (x2 - x1) * (py - y1)); // Area with point and edge (x1, y1) -> (x2, y2)
	    int area3 = Math.abs((px - x2) * (y0 - y2) - (x0 - x2) * (py - y2)); // Area with point and edge (x2, y2) -> (x0, y0)

	    // If the sum of the areas is equal to the total area, the point is inside the triangle
	    if (area == (area1 + area2 + area3)) {
	        return true;
	    } else {
	        return false;
	    }
	}

	public void heal() {
		if(gw.isPaused()) {
			this.health = 5;
	        this.color = ColorUtil.rgb(0, 0, blue);
		}
		
	}
}
