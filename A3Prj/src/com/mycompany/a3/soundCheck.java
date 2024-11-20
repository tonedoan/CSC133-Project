package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class soundCheck extends Command {
	private GameWorld gw;
    
	public soundCheck(GameWorld gw) {
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
