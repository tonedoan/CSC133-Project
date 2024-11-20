package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Heal extends Command {
    private GameWorld gw;

    /**
     * Constructs a new Heal command with a reference to the GameWorld.
     *
     * @param gw the GameWorld instance that this command will interact with
     */
    public Heal(GameWorld gw) {
        super("Heal");
        this.gw = gw;
    }

    /**
     * Executes the heal action on the selected astronaut when the command is triggered.
     *
     * @param evt the ActionEvent triggered by the command
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Get the currently selected astronaut from the GameWorld
        Astronaut selectedAstronaut = gw.getSelectedAstronaut();

        // Check if an astronaut is selected
        if (selectedAstronaut != null) {
            // Heal the astronaut (e.g., increase health, reset health, or any healing logic)
            selectedAstronaut.heal();
        }
    }
}
