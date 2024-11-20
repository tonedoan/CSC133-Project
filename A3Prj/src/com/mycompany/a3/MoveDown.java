package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class for moving the spaceship down in the GameWorld.
 * This class extends Command and triggers the moveSpaceDown method in GameWorld when executed.
 */
public class MoveDown extends Command {
    
    private GameWorld gw; // The GameWorld instance that will be manipulated by this command

    /**
     * Constructs a MoveDown command with the specified GameWorld.
     *
     * @param gw the GameWorld instance to be manipulated by this command
     */
    public MoveDown(GameWorld gw) {
        super("Down");
        this.gw = gw;
    }

    /**
     * Executes the action associated with this command, which moves the spaceship down.
     *
     * @param evt the ActionEvent triggered by this command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveSpaceDown(); // Calls the moveSpaceDown method on the GameWorld to move the spaceship down
    }
}
