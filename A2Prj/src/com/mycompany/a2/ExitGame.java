package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ExitGame extends Command{
	private GameWorld gw;
	public ExitGame(GameWorld gw) {
		super("ExitGame");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.exit(1);
	}
}
