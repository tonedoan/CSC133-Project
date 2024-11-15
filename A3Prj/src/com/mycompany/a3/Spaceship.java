package com.mycompany.a2;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * The Spaceship class represents a guided spaceship object in the game world.
 * The spaceship can move in all four cardinal directions and can change its size and location.
 */
public class Spaceship extends Rescuer {
    private static Spaceship spaceship;
    Random rand = new Random();

    /**
     * Constructs a new Spaceship with a randomized location and a default size and color.
     *
     * @param maxWidth the maximum width of the game world
     * @param maxHeight the maximum height of the game world
     */
    private Spaceship(int maxWidth, int maxHeight) {
        xPoint = rand.nextFloat() * maxWidth;
        yPoint = rand.nextFloat() * maxHeight;
        size = 100;
        point = new Point(xPoint, yPoint);
        color = ColorUtil.rgb(0, 100, 0); // Spaceship is colored green
        type = "Spaceship";
    }
    
    /**
     * Retrieves the singleton instance of the Spaceship, creating it if it does not exist.
     *
     * @param maxWidth the maximum width of the game world
     * @param maxHeight the maximum height of the game world
     * @return the singleton instance of the Spaceship
     */
    public static Spaceship getSpaceship(int maxWidth, int maxHeight) {
        if (spaceship == null) {
            spaceship = new Spaceship(maxWidth, maxHeight);
        }
        return spaceship;
    }

    /**
     * Sets the x-coordinate of the spaceship's position.
     *
     * @param x the new x-coordinate of the spaceship
     */
    @Override
    public void setX(float x) {
        point.setX(x);
    }

    /**
     * Sets the y-coordinate of the spaceship's position.
     *
     * @param y the new y-coordinate of the spaceship
     */
    @Override
    public void setY(float y) {
        point.setY(y);
    }

    /**
     * Returns a string representation of the spaceship's state, including its location, color, and size.
     *
     * @return a string representing the state of the spaceship
     */
    @Override
    public String toString() {
        return type + ": loc=" + Math.round(point.getX() * 10.0) / 10.0 + ", " + Math.round(point.getY() * 10.0) / 10.0 +
               " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + ", " + ColorUtil.blue(color) + "]" +
               " size=" + size;
    }

    /**
     * Sets the size of the spaceship.
     *
     * @param size the new size of the spaceship
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /** 
     * Overrides the setColor() method from the GameObject class.
     * This method does nothing since the color of the spaceship does not change.
     *
     * @param color the new color of the spaceship (ignored)
     */
    @Override
    public void setColor(int color) {
        // No operation: Spaceship colors do not change
    }
}
