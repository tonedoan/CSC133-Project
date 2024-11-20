package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * A command to initiate a jump to a random alien within the game.
 */
public class JumpToRandomAlien extends Command {
    
    private GameWorld gw; // The GameWorld instance to interact with

    /**
     * Constructs a JumpToRandomAlien command.
     *
     * @param gw the GameWorld instance to be used for the jump action
     */
    public JumpToRandomAlien(GameWorld gw) {
        super("MoveToAlien");
        this.gw = gw;
    }

    /**
     * Executes the jump action to a random alien when the command is triggered.
     *
     * @param evt the action event that triggered the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.jumpToRandomAlien();
    }
}
