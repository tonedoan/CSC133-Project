package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Fight extends Command{
	private GameWorld gw;
	public Fight(GameWorld gw) {
		super("Fight");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.attack();
	}
}
