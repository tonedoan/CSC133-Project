package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MoveUp extends Command{
	private GameWorld gw;
	public MoveUp(GameWorld gw) {
		super("Up");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.moveSpaceUp();
	}
}
