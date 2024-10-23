package com.mycompany.a2;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;

public class About extends Command {

    public About() {
        super("About Me");
    }
    
    public void actionPerformed(ActionEvent evt) {
        // Create a dialog
        Dialog aboutDialog = new Dialog("About");

        // Create the help content (TextArea) with instructions or key mappings
        TextArea aboutText = new TextArea("Name: Tuan Doan\n"
                + "Course Name: CSC133\n"
                + "Assignment 2");

        // Set the TextArea to be non-editable and wrap text
        aboutText.setEditable(false);

        // Create an "OK" button
        Button okButton = new Button("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutDialog.dispose(); // Close the dialog
            }
        });

        // Add the helpText and button to the dialog
        aboutDialog.setLayout(new BorderLayout());
        aboutDialog.add(BorderLayout.CENTER, aboutText);
        aboutDialog.add(BorderLayout.SOUTH, okButton); // Add the button at the bottom
        aboutDialog.show(); // Show the dialog
    }
}
