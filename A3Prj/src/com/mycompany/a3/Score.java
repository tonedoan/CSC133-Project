package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * The Score class is a command that interacts with the GameWorld.
 * It is used to open a door in the game when the associated action is performed.
 */
public class Score extends Command {
    private GameWorld gw;

    /**
     * Constructs a Score command with the specified GameWorld.
     *
     * @param gw the GameWorld instance that this command interacts with
     */
    public Score(GameWorld gw) {
        super("Score");
        this.gw = gw;
    }

    /**
     * Executes the action associated with this command.
     * Opens the door in the GameWorld when triggered.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.openDoor();
    }
}
