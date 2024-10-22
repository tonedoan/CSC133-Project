package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MoveRight extends Command{
	private GameWorld gw;
	public MoveRight(GameWorld gw) {
		super("Right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.moveSpaceRight();
	}
}
