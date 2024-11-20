package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Heal extends Command{
	private GameWorld gw;

    /**
     * Constructs a new Fight command with a reference to the GameWorld.
     *
     * @param gw the GameWorld instance that this command will interact with
     */
    public Heal(GameWorld gw) {
        super("Heal");
        this.gw = gw;
    }

    /**
     * Executes the attack action in the game world when the command is triggered.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
    }
}
