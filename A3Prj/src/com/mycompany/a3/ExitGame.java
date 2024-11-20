package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

/**
 * The ExitGame class represents a command to exit the game.
 * It prompts the user for confirmation before quitting.
 */
public class ExitGame extends Command {
    Command cOk = new Command("Ok");
    Command cCancel = new Command("Cancel");

    /**
     * Constructs a new ExitGame command.
     */
    public ExitGame() {
        super("Exit Game");
    }

    /**
     * Displays a confirmation dialog when the command is triggered.
     * If the user confirms, the application will exit.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {        
        Command bOk = Dialog.show("Confirm Quit", "Are you sure you want to quit?", cOk, cCancel);
        
        if(bOk == cOk) {
            Display.getInstance().exitApplication();
        }    
    }
}
