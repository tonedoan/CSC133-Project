package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class Pause extends Command {
    private GameWorld gw;
	private Button pauseButton;

	/**
     * Constructs a new Pause command with a reference to the GameWorld and the Pause button.
     *
     * @param gw the GameWorld instance that this command will interact with
     * @param pauseButton the Pause button whose label we need to change
     */
    public Pause(GameWorld gw, Button pauseButton) {
        super("Pause");
        this.gw = gw;
        this.pauseButton = pauseButton;
    }

    /**
     * Executes the attack action in the game world when the command is triggered.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
    	gw.togglePause();
    	
    	// Check the current game mode and update the button label accordingly
        if (gw.isPaused()) {
            pauseButton.setText("Resume"); // Change label to "Play" when paused
        } else {
            pauseButton.setText("Pause"); // Change label to "Pause" when playing
        }
    }
}
