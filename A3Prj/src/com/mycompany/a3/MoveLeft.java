package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class for moving the spaceship left in the GameWorld.
 * This class extends Command and triggers the moveSpaceLeft method in GameWorld when executed.
 */
public class MoveLeft extends Command {
    
    private GameWorld gw; // The GameWorld instance that will be manipulated by this command

    /**
     * Constructs a MoveLeft command with the specified GameWorld.
     *
     * @param gw the GameWorld instance to be manipulated by this command
     */
    public MoveLeft(GameWorld gw) {
        super("Left");
        this.gw = gw;
    }

    /**
     * Executes the action associated with this command, which moves the spaceship left.
     *
     * @param evt the ActionEvent triggered by this command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveSpaceLeft(); // Calls the moveSpaceLeft method on the GameWorld to move the spaceship left
    }
}
