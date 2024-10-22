package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Score extends Command{
	private GameWorld gw;
	public Score(GameWorld gw) {
		super("Score");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.openDoor();
	}
}
