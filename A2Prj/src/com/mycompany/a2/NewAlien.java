package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NewAlien extends Command{
	private GameWorld gw;
	public NewAlien(GameWorld gw) {
		super("NewAlien");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.alienClone();
	}
}
