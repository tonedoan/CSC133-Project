package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class for creating a new alien in the GameWorld.
 * This class extends Command and triggers the alienClone method in GameWorld when executed.
 */
public class NewAlien extends Command {
    
    private GameWorld gw; // The GameWorld instance that will be manipulated by this command

    /**
     * Constructs a NewAlien command with the specified GameWorld.
     *
     * @param gw the GameWorld instance to be manipulated by this command
     */
    public NewAlien(GameWorld gw) {
        super("NewAlien");
        this.gw = gw;
    }

    /**
     * Executes the action associated with this command, which creates a new alien.
     *
     * @param evt the ActionEvent triggered by this command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.alienClone(); // Calls the alienClone method on the GameWorld to create a new alien
    }
}
