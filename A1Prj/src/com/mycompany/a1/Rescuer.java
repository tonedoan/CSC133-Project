package com.mycompany.a1;

public abstract class Rescuer extends GameObject implements IGuided {
	/**
     * Moves the Rescuer 10 units to the left along the x-axis.
     */
    public void moveLeft() {
        setX(getX() - 10);
    }

    /**
     * Moves the Rescuer 10 units to the right along the x-axis.
     */
    public void moveRight() {
        setX(getX() + 10);
    }

    /**
     * Moves the Rescuer 10 units up along the y-axis.
     */
    public void moveUp() {
        setY(getY() + 10);
    }

    /**
     * Moves the Rescuer 10 units down along the y-axis.
     */
    public void moveDown() {
        setY(getY() - 10);
    }

    /**
     * Placeholder method for jumping to a specific location.
     * @param point this.point is set to the point of object.
     */
    @Override
    public void jumpToLocation() {
    	// Need to implement this.
    }
}
