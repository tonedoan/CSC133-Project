package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class for moving the spaceship right in the GameWorld.
 * This class extends Command and triggers the moveSpaceRight method in GameWorld when executed.
 */
public class MoveRight extends Command {
    
    private GameWorld gw; // The GameWorld instance that will be manipulated by this command

    /**
     * Constructs a MoveRight command with the specified GameWorld.
     *
     * @param gw the GameWorld instance to be manipulated by this command
     */
    public MoveRight(GameWorld gw) {
        super("Right");
        this.gw = gw;
    }

    /**
     * Executes the action associated with this command, which moves the spaceship right.
     *
     * @param evt the ActionEvent triggered by this command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveSpaceRight(); // Calls the moveSpaceRight method on the GameWorld to move the spaceship right
    }
}
