package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;

/**
 * Represents a command to display help information in the game.
 * This command, when executed, opens a dialog displaying key mappings and instructions for the game.
 */
public class Help extends Command {
    
    /**
     * Constructs a Help command with the label "Help".
     */
    public Help() {
        super("Help");
    }
    
    /**
     * Called when the Help command is triggered.
     * This method creates and displays a dialog containing game instructions
     * and key mappings, along with an "OK" button to close the dialog.
     *
     * @param evt the action event that triggered this command.
     */
    public void actionPerformed(ActionEvent evt) {
        // Create a dialog
        Dialog helpDialog = new Dialog("Help");

        // Create the help content (TextArea) with instructions or key mappings
        TextArea helpText = new TextArea("Keys:\n"
                + "a - Jump to random alien\n"
                + "o - Jump to random astronaut\n"
                + "r - Move spaceship right\n"
                + "l - Move spaceship left\n"
                + "u - Move spaceship up\n"
                + "d - Move spaceship down\n"
                + "e - Expand spaceship\n"
                + "c - Contract spaceship\n"
                + "s - Open door\n"
                + "w - Create new alien\n"
                + "f - Attack\n");
        
        // Set the TextArea to be non-editable and wrap text
        helpText.setEditable(false);

        // Create an "OK" button
        Button okButton = new Button("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpDialog.dispose(); // Close the dialog
            }
        });

        // Add the helpText and button to the dialog
        helpDialog.setLayout(new BorderLayout());
        helpDialog.add(BorderLayout.CENTER, helpText);
        helpDialog.add(BorderLayout.SOUTH, okButton); // Add the button at the bottom
        helpDialog.show(); // Show the dialog
    }
}
