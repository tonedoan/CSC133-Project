package com.mycompany.a3;

/**
 * An interface representing objects that can move within the game.
 */
public interface IMoving {
    
    /**
     * Moves the object according to its specific movement logic.
     * This method should define how the object changes its position.
     */
    void move(int timerSec);
}
