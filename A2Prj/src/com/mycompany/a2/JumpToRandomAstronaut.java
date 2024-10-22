package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class JumpToRandomAstronaut extends Command {
	private GameWorld gw;
	public JumpToRandomAstronaut(GameWorld gw) {
		super("MoveToAstronaut");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.jumpToRandomAstro();
	}
}