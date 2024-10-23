package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * The Astronaut class represents an astronaut in the game world.
 * Astronauts have health, speed, and can move within the game world.
 */
public class Astronaut extends Opponent {
    private int health;
    private int blue;

    /**
     * Constructs a new Astronaut with randomized properties such as size, location, and direction.
     * Sets default values for health and color.
     *
     * @param maxWidth  the maximum width for the astronaut's spawning area
     * @param maxHeight the maximum height for the astronaut's spawning area
     */
    public Astronaut(int maxWidth, int maxHeight) {
        super(maxWidth, maxHeight);
        blue = 255;
        size = rand.nextInt(31) + 20; // Set size to a random number between 20-50
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
        this.blue -= 20; // Decrease blue value
        if (this.blue < 0) {
            this.blue = 0; // Ensure blue does not go below 0
        }
        // Update the color using the modified blue component
        this.color = ColorUtil.rgb(0, 0, this.blue);
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
}
