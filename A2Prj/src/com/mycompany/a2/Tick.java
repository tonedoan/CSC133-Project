package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * The Tick class represents a command that triggers a game tick,
 * which increments time by 1 in the GameWorld.
 * It extends the Command class to define a specific action for advancing the game state.
 */
public class Tick extends Command {
    private GameWorld gw;

    /**
     * Constructs a Tick command with the specified GameWorld.
     *
     * @param gw the GameWorld instance that this command interacts with
     */
    public Tick(GameWorld gw) {
        super("Tick");
        this.gw = gw;
    }

    /**
     * Executes the action associated with this command.
     * This method is called when the Tick command is triggered, 
     * advancing the game state by calling the gameTick method in GameWorld.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.gameTick();
    }
}
