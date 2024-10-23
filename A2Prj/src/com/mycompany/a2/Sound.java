package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Sound extends Command {
	private GameWorld gw;
	public Sound(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.toggleSound();
	}
}
