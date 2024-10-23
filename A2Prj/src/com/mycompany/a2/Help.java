package com.mycompany.a2;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;

public class Help extends Command {
    public Help() {
        super("Help");
    }
    
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
                + "t - Advance game time\n"
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
