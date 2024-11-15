package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class for moving the spaceship up in the GameWorld.
 * This class extends Command and triggers the moveSpaceUp method in GameWorld when executed.
 */
public class MoveUp extends Command {
    
    private GameWorld gw; // The GameWorld instance that will be manipulated by this command

    /**
     * Constructs a MoveUp command with the specified GameWorld.
     *
     * @param gw the GameWorld instance to be manipulated by this command
     */
    public MoveUp(GameWorld gw) {
        super("Up");
        this.gw = gw;
    }

    /**
     * Executes the action associated with this command, which moves the spaceship up.
     *
     * @param evt the ActionEvent triggered by this command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveSpaceUp(); // Calls the moveSpaceUp method on the GameWorld to move the spaceship up
    }
}
