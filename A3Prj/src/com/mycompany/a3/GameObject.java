package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * The GameObject class serves as a base class for all objects in the game world.
 * It provides basic properties such as size, location, and color, along with 
 * methods to access and modify these properties.
 */
public abstract class GameObject {
    protected int size;     // Size of the game object
    protected Point point;  // Location (x, y) of the game object
    protected int color;    // Color of the game object
    protected String type = "GameObject";
    protected float xPoint;
    protected float yPoint;
    protected int maxWidth;
    protected int maxHeight;

    /**
     * Default constructor initializing the game object with default values.
     * Sets the size to 0, location to (0, 0), and color to black.
     */
    public GameObject() {
        size = 0;
        point = new Point(0, 0); // Default location (0, 0)
        color = ColorUtil.rgb(0, 0, 0); // Default color black
    }

    /**
     * Returns the size of the game object.
     *
     * @return the size of the game object.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the location (point) of the game object.
     *
     * @return the Point object representing the location of the game object.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Returns the color of the game object.
     *
     * @return the color of the game object as an integer.
     */
    public int getColor() {
        return color;
    }

    /**
     * Sets the size of the game object.
     *
     * @param size the new size of the game object.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Sets the location of the game object.
     *
     * @param point the Point object representing the new location of the game object.
     */
    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * Sets the color of the game object.
     *
     * @param color the new color of the game object as an integer.
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Sets the x-coordinate of the game object's location.
     *
     * @param x the new x-coordinate of the game object.
     */
    public void setX(float x) {
        this.point.setX(x);
    }

    /**
     * Sets the y-coordinate of the game object's location.
     *
     * @param y the new y-coordinate of the game object.
     */
    public void setY(float y) {
        this.point.setY(y);
    }

    /**
     * Returns the x-coordinate of the game object's location.
     *
     * @return the x-coordinate as a float.
     */
    public float getX() {
        return point.getX();
    }

    /**
     * Returns the y-coordinate of the game object's location.
     *
     * @return the y-coordinate as a float.
     */
    public float getY() {
        return point.getY();
    }

    /**
     * Returns a string representation of the game object's state, including
     * its location, color, and size.
     *
     * @return a string representing the state of the game object.
     */
    public String toString() {
        return type + ": loc=" + Math.round(point.getX() * 10.0) / 10.0 + ", " +
               Math.round(point.getY() * 10.0) / 10.0 + " color=[" +
               ColorUtil.red(color) + "," + ColorUtil.green(color) + ", " +
               ColorUtil.blue(color) + "] size=" + size;
    }
}
