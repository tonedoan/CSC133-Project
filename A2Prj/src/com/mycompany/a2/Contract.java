package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * The Contract class represents a command in the game that triggers
 * changing the size of the door to 50 of the Spaceship.
 */
public class Contract extends Command {
    private GameWorld gw;

    /**
     * Constructs a new Contract command with a reference to the GameWorld.
     *
     * @param gw the GameWorld instance that this command will interact with
     */
    public Contract(GameWorld gw) {
        super("Contract");
        this.gw = gw;
    }

    /**
     * Executes the contract action in the game world when the command is triggered.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.contract();
    }
}
