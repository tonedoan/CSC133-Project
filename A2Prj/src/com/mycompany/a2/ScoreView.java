package com.mycompany.a2;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

import java.util.Observable;
import java.util.Observer;

public class ScoreView extends Container implements Observer {
    private Label clockLabel;
    private Label scoreLabel;
    private Label astronautRescuedLabel;
    private Label alienCapturedLabel;
    private Label astronautsLeftLabel;
    private Label aliensLeftLabel;
    private Label soundStatusLabel;
    private GameWorld gw;

    public ScoreView(GameWorld gw) {
    	this.gw = gw;
        // Set the layout for this container to be vertical (BoxLayout.Y_AXIS)
        this.setLayout(new FlowLayout(CENTER));
        
        // Initialize the labels
        clockLabel = new Label("Clock: " + gw.getClock());
        scoreLabel = new Label("Score: " + gw.getScore());
        astronautRescuedLabel = new Label("Astronauts Rescued: " + gw.getAstronautRes());
        alienCapturedLabel = new Label("Aliens Sneaked In: " + gw.getAlienRes());
        astronautsLeftLabel = new Label("Astronauts Remaining:" + gw.countObj("as"));
        aliensLeftLabel = new Label("Aliens Remaining: "+ gw.countObj("a"));
        soundStatusLabel = new Label("Sound: " + gw.printSoundStatus());

        // Setting font to blue
        clockLabel.getAllStyles().setFgColor(0x0000FF); // Set color to blue
        scoreLabel.getAllStyles().setFgColor(0x0000FF);
        astronautRescuedLabel.getAllStyles().setFgColor(0x0000FF);
        alienCapturedLabel.getAllStyles().setFgColor(0x0000FF);
        astronautsLeftLabel.getAllStyles().setFgColor(0x0000FF);
        aliensLeftLabel.getAllStyles().setFgColor(0x0000FF);
        soundStatusLabel.getAllStyles().setFgColor(0x0000FF);
        
        // Add labels to the container
        this.add(clockLabel);
        this.add(scoreLabel);
        this.add(astronautRescuedLabel);
        this.add(alienCapturedLabel);
        this.add(astronautsLeftLabel);
        this.add(aliensLeftLabel);
        this.add(soundStatusLabel);
        
        this.revalidate();
        
    }

    /**
     * This method is called when the observed object is updated.
     */
    @Override
    public void update(Observable observable, Object data) {
    	
        // Update the labels with the latest values from the GameWorld
        clockLabel.setText("Clock: " + gw.getClock());
        scoreLabel.setText("Score: " + gw.getScore());
        astronautRescuedLabel.setText("Astronauts Rescued: " + gw.getAstronautRes());
        alienCapturedLabel.setText("Aliens sneaked in: " + gw.getAlienRes());
        astronautsLeftLabel.setText("Astronauts Remaining: " + gw.countObj("as"));
        aliensLeftLabel.setText("Aliens Remaining: " + gw.countObj("a"));
        soundStatusLabel.setText("Sound: " + gw.printSoundStatus());
        
        // Revalidate the container to reflect the updated information
        this.revalidate();
    }
}
