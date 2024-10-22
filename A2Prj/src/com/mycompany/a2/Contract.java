package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Contract extends Command{
	private GameWorld gw;
	public Contract(GameWorld gw) {
		super("Contract");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.contract();
	}
}
