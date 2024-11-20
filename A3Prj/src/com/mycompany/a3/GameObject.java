package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * The GameObject class serves as a base class for all objects in the game world.
 * It provides basic properties such as size, location, and color, along with 
 * methods to access and modify these properties.
 */
public abstract class GameObject implements IDrawable, ICollider, ISelectable{
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
    
    /**
     * Checks if this GameObject collides with another GameObject using bounding box collision detection.
     * The method calculates the bounding boxes of both objects and determines if they overlap.
     *
     * @param otherObject The other GameObject to check for collision with this object.
     * @return true if this GameObject collides with the other GameObject, false otherwise.
     */
    @Override
    public boolean collidesWith(GameObject otherObject) {
        // Initialize the result as false (no collision by default)
        boolean result = false;

        // Bounding box for this object
        int thisRightX = (int) (this.getX() + this.size);   // Right X boundary of this object
        int thisLeftX = (int) (this.getX());                // Left X boundary of this object
        int thisTopY = (int) (this.getY());                 // Top Y boundary of this object
        int thisBottomY = (int) (this.getY() + this.size);  // Bottom Y boundary of this object

        // Bounding box for the other object
        int otherRightX = (int) (otherObject.getX() + otherObject.size); // Right X boundary of other object
        int otherLeftX = (int) (otherObject.getX());                     // Left X boundary of other object
        int otherTopY = (int) (otherObject.getY());                      // Top Y boundary of other object
        int otherBottomY = (int) (otherObject.getY() + otherObject.size);// Bottom Y boundary of other object

        /**
         * Check for overlap between the bounding boxes:
         * - No overlap (no collision) occurs if:
         *   1. This object's right side is left of the other object's left side.
         *   2. This object's left side is right of the other object's right side.
         *   3. This object's bottom side is above the other object's top side.
         *   4. This object's top side is below the other object's bottom side.
         *
         * If any of these conditions are true, there is no collision.
         * Using the negation of these conditions, we determine if there is an overlap (collision).
         */
        result = !(thisRightX < otherLeftX || 
                   thisLeftX > otherRightX || 
                   thisBottomY < otherTopY || 
                   thisTopY > otherBottomY);

        // Return the result of the collision check
        return result;
    }
}
