package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ExitGame extends Command{
	Command cOk = new Command("Ok");
	Command cCancel = new Command("Cancel");
	public ExitGame() {
		super("Exit Game");
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {		
		Command bOk = Dialog.show("Confirm Quit", "Are you sure you want to quit?", cOk, cCancel);
		
		if(bOk == cOk) {
			Display.getInstance().exitApplication();
		}	
	}
}
