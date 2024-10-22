package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Expand extends Command{
	private GameWorld gw;
	public Expand(GameWorld gw) {
		super("Expand");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.expand();
	}
}
