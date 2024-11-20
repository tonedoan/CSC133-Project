package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * The Expand class represents a command that triggers an expansion action 
 * changing the size of the doors to 1000 for the Spaceship.
 */
public class Expand extends Command {
    private GameWorld gw;

    /**
     * Constructs a new Expand command with a reference to the GameWorld.
     *
     * @param gw the GameWorld instance that this command will interact with
     */
    public Expand(GameWorld gw) {
        super("Expand");
        this.gw = gw;
    }

    /**
     * Executes the expand action in the game world when the command is triggered.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.expand();
    }
}
