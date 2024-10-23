package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * The Fight class represents a command that triggers an attack action 
 * in the game world that simulates Astronauts being attacked by Aliens
 */
public class Fight extends Command {
    private GameWorld gw;

    /**
     * Constructs a new Fight command with a reference to the GameWorld.
     *
     * @param gw the GameWorld instance that this command will interact with
     */
    public Fight(GameWorld gw) {
        super("Fight");
        this.gw = gw;
    }

    /**
     * Executes the attack action in the game world when the command is triggered.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.attack();
    }
}
