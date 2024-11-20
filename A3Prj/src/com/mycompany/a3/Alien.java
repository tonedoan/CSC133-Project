package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;

/**
 * The Alien class represents an alien in the game world.
 * Aliens have speed, direction, and can move within the game world.
 */
public class Alien extends Opponent {
    private boolean canSpawn;
    /**
     * Constructs a new Alien with randomized properties such as size, location, and direction.
     * Sets default values for speed and color.
     *
     * @param maxWidth  the maximum width for the alien's spawning area
     * @param maxHeight the maximum height for the alien's spawning area
     */
    public Alien(int maxWidth, int maxHeight, GameWorld gw) {
        super(maxWidth, maxHeight, gw);
        //size = rand.nextInt(31) + 20; // Set size to a random number between 20-50
        size = 100;
        float xPoint = rand.nextFloat() * maxWidth; // Ensure within bounds
        float yPoint = rand.nextFloat() * maxHeight; // Ensure within bounds
        point = new Point(xPoint, yPoint);
        color = ColorUtil.rgb(255, 0, 0); // Aliens are colored red
        speed = 30; // Set speed directly
        direction = rand.nextInt(360);
        type = "Alien";
        this.canSpawn = false;
    }

    /** 
     * Overrides the setColor() from GameObject class to do nothing since Alien colors do not change.
     * 
     * @param color the new color of the alien (not used)
     */
    @Override
    public void setColor(int color) {
        // No implementation needed since alien colors are constant
    }

    /**
     * Returns a string representation of the alien's state, including its location, color, size, speed, and direction.
     *
     * @return a string representing the state of the alien.
     */
    @Override
    public String toString() {
        return type + ": loc=" + Math.round(point.getX() * 10.0) / 10.0 + ", " + Math.round(point.getY() * 10.0) / 10.0 + 
               " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + ", " + ColorUtil.blue(color) + 
               "] size=" + size + " speed=" + speed + " dir=" + direction + " spawn=" + canSpawn;
    }
    
    /**
     * Draws an Alien shape in the container.
     * Aliens are square.
     * @param g is what is going to be drawn
     * @param pCmpRelPrnt is the location of where it will be drawn
     */
    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
    	// Sets color for alien to red.
    	g.setColor(ColorUtil.rgb(255, 0, 0));
    	
    	// Adjust the position to ensure the circle is drawn with its center at (pCmpRelPrnt)
        int x = (int)(pCmpRelPrnt.getX() - size / 2); // X-coordinate for the upper-left corner
        int y = (int)(pCmpRelPrnt.getY() - size / 2); // Y-coordinate for the upper-left corner
    	g.fillRect(x, y, size, size);
    }
    
    @Override
    public void handleCollision(GameObject otherObject) {
        if (otherObject instanceof Alien) {
            Alien otherAlien = (Alien) otherObject;
            
            if(canSpawn == true) {
                // Only one alien should spawn a baby alien. Use this alien to create the baby.
                gw.spawnBabyAlien(this); // Pass 'this' to indicate it's the current alien spawning the baby
                
                // After parents spawn once, canSpawn is set to false
                this.canSpawn = false;
                otherAlien.canSpawn = false;
            }
        }
    }
    
    /**
     * Flag to determine if this alien can create other aliens.
     * @param value 
     */
    public void setSpawn(boolean value) {
    	this.canSpawn = value;
    }
}
