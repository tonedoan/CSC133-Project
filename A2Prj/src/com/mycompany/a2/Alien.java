package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

/**
 * The Alien class represents an alien in the game world.
 * Aliens have speed, direction, and can move within the game world.
 */
public class Alien extends Opponent {

    /**
     * Constructs a new Alien with randomized properties such as size, location, and direction.
     * Sets default values for speed and color.
     * @param maxHeight 
     * @param maxWidth 
     */
	public Alien(int maxWidth, int maxHeight) {
	    super(maxWidth, maxHeight);
	    size = rand.nextInt(31) + 20; // Set size to a random number between 20-50
	    float xPoint = rand.nextFloat() * maxWidth; // Ensure within bounds
	    float yPoint = rand.nextFloat() * maxHeight; // Ensure within bounds
	    point = new Point(xPoint, yPoint);
	    color = ColorUtil.rgb(255, 0, 0); // Aliens are colored red
	    speed = 5; // Set speed directly
	    direction = rand.nextInt(360);
	    type = "Alien";
	}

    
    /** 
     * Overrides the setColor() from GameObject class to nothing since Alien colors do not change.
     * @param color the new color of the spaceship.
     */
    @Override
    public void setColor(int Color) {
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
               "] size=" + size + " speed=" + speed + " dir=" + direction;
    }
}
