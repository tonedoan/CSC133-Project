package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * The Sound class is a command that interacts with the GameWorld to toggle sound settings.
 * It extends the Command class to define a specific action for sound control.
 */
public class Sound extends Command {
    private GameWorld gw;

    /**
     * Constructs a Sound command with the specified GameWorld.
     *
     * @param gw the GameWorld instance that this command interacts with
     */
    public Sound(GameWorld gw) {
        super("Sound");
        this.gw = gw;
    }

    /**
     * Executes the action associated with this command.
     * Toggles the sound status in the GameWorld when triggered.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.toggleSound();
    }
}
