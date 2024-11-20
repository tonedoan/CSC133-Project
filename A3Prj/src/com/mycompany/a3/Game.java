package com.mycompany.a3;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

import java.util.Vector;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar; 

/**
 * The Game class represents the main game interface, managing the layout and controls.
 * It initializes the GameWorld, MapView, and ScoreView, and sets up the user interface.
 */
public class Game extends Form  implements Runnable{
    private GameWorld gw;    // The game world instance
    private MapView mv;      // The map view instance
    private ScoreView sv;    // The score view instance
    private int maxWidth;    // Maximum width for the game area
    private int maxHeight;   // Maximum height for the game area
    private int timerSec;
    private Vector<Button> buttonVector = new Vector<>(); // Vector to hold buttons
    private Vector<Button> pausedButtonVector = new Vector<>(); // Vector to hold buttons
	// Declare the buttons as class fields
	private Button jumpToAstronautButton;
	private Button moveLeftButton;
	private Button moveUpButton;
	private Button expandButton;
	private Button scoreButton;
	private Button jumpToAlienButton;
	private Button moveRightButton;
	private Button moveDownButton;
	private Button contractButton;
	private Button healButton;
	private Button pauseButton;
	private Command quit; 

    
    /**
     * Constructs the Game interface by initializing components and setting up the layout.
     */
    public Game() {
        gw = new GameWorld();
        mv = new MapView(gw);
        sv = new ScoreView(gw);
        gw.addObserver(sv);
        gw.addObserver(mv);

        
        this.setLayout(new BorderLayout());
        Container mainContainer = new Container();
        mainContainer.setLayout(new BorderLayout());
        
        // Create containers for layout
        Container northContainer = new Container(new BorderLayout());
        Container southContainer = new Container(new FlowLayout(CENTER));
        Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        // Create buttons and associate commands
        jumpToAstronautButton = new Button(new JumpToRandomAstronaut(gw));
        moveLeftButton = new Button(new MoveLeft(gw));
        moveUpButton = new Button(new MoveUp(gw));
        expandButton = new Button(new Expand(gw));
        
        scoreButton = new Button(new Score(gw));
        jumpToAlienButton = new Button(new JumpToRandomAlien(gw));
        moveRightButton = new Button(new MoveRight(gw));
        moveDownButton = new Button(new MoveDown(gw));
        contractButton = new Button(new Contract(gw));
        
        healButton = new Button(new Heal(gw));
        pauseButton = new Button(new Pause(gw));
        
        // Style center container for MapView
        mv.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255,0,0)));
        
        // Add components to their respective containers
        
        northContainer.add(BorderLayout.NORTH, sv); // Add ScoreView to the north section
        southContainer.add(healButton);
        southContainer.add(pauseButton);
        
        westContainer.add(expandButton);
        westContainer.add(moveUpButton);
        westContainer.add(moveLeftButton);
        westContainer.add(jumpToAstronautButton);
        
        eastContainer.add(scoreButton);
        eastContainer.add(jumpToAlienButton);
        eastContainer.add(moveRightButton);
        eastContainer.add(moveDownButton);
        eastContainer.add(contractButton);
        
        // Add buttons to vector and style them
        buttonVector.add(expandButton);
        buttonVector.add(contractButton);
        buttonVector.add(moveUpButton);
        buttonVector.add(moveDownButton);
        buttonVector.add(moveLeftButton);
        buttonVector.add(moveRightButton);
        buttonVector.add(jumpToAstronautButton);
        buttonVector.add(jumpToAlienButton);
        buttonVector.add(scoreButton);
        buttonVector.add(healButton);
        buttonVector.add(pauseButton);
        
        // Add buttons to vector used when enabling and disabling buttons
        pausedButtonVector.add(expandButton);
        pausedButtonVector.add(contractButton);
        pausedButtonVector.add(moveUpButton);
        pausedButtonVector.add(moveDownButton);
        pausedButtonVector.add(moveLeftButton);
        pausedButtonVector.add(moveRightButton);
        pausedButtonVector.add(jumpToAstronautButton);
        pausedButtonVector.add(jumpToAlienButton);
        pausedButtonVector.add(scoreButton);
        
        // Used to style button
        for (Button b : buttonVector) {
            styleButton(b);
        }

        // Assemble the main layout
        mainContainer.add(BorderLayout.CENTER, mv);
        mainContainer.add(BorderLayout.NORTH, northContainer);
        mainContainer.add(BorderLayout.SOUTH, southContainer);
        mainContainer.add(BorderLayout.WEST, westContainer);
        mainContainer.add(BorderLayout.EAST, eastContainer);
        
        this.add(BorderLayout.CENTER, mainContainer);
        
        // Toolbar setup
        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        Command help = new Help();
        quit = new ExitGame(); 
        CheckBox sound = new CheckBox();
        sound.getAllStyles().setBgTransparency(255);
        sound.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        Command soundBox = new soundCheck(gw);
        Command about = new About();
        
        toolbar.setTitle("The Rescue Game");
        toolbar.addCommandToSideMenu(about);
        
        sound.setCommand(soundBox);
        toolbar.addComponentToSideMenu(sound);
        
        toolbar.addCommandToRightBar(help);
        toolbar.addCommandToSideMenu(quit);
        
        // Key Bindings and Listeners
        addKeyListener('e', expandButton.getCommand());
        addKeyListener('c', contractButton.getCommand());
        addKeyListener('s', scoreButton.getCommand());
        addKeyListener('r', moveRightButton.getCommand());
        addKeyListener('l', moveLeftButton.getCommand());
        addKeyListener('u', moveUpButton.getCommand());
        addKeyListener('d', moveDownButton.getCommand());
        addKeyListener('o', jumpToAstronautButton.getCommand());
        addKeyListener('a', jumpToAlienButton.getCommand());
        addKeyListener('h', healButton.getCommand());
        addKeyListener('f', pauseButton.getCommand());
        addKeyListener('x', quit);
        
        // Show the game form
        this.show();
        maxWidth = mv.getWidth();
        maxHeight = mv.getHeight();
        gw.setHeight(maxHeight);
        gw.setWidth(maxWidth);
        gw.init();
        gw.createSounds();
        revalidate();
        this.timerSec = gw.getTimerSec();
        UITimer timer = new UITimer(this);
        timer.schedule(timerSec, true, this);
        
    }
    
    /**
     * Styles a button with a blue background, white text, and padding.
     *
     * @param button the button to style
     */
    private void styleButton(Button button) {
        button.getAllStyles().setBgColor(ColorUtil.BLUE);
        button.getAllStyles().setBgTransparency(255);
        button.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        button.getAllStyles().setPadding(Component.TOP, 5);
        button.getAllStyles().setPadding(Component.BOTTOM, 5);
        button.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.GRAY));
    }
    
    /**
     * Disables all game-related buttons.
     */
    private void disableButtons() {
        for (Button b : pausedButtonVector) {
            b.setEnabled(false);
            b.getAllStyles().setBgColor(ColorUtil.GRAY); // Set gray color when disabled
            b.getAllStyles().setBgTransparency(255);  // Make it look disabled
        }
     // Key Bindings and Listeners
        removeKeyListener('e', expandButton.getCommand());
        removeKeyListener('c', contractButton.getCommand());
        removeKeyListener('s', scoreButton.getCommand());
        removeKeyListener('r', moveRightButton.getCommand());
        removeKeyListener('l', moveLeftButton.getCommand());
        removeKeyListener('u', moveUpButton.getCommand());
        removeKeyListener('d', moveDownButton.getCommand());
        removeKeyListener('o', jumpToAstronautButton.getCommand());
        removeKeyListener('a', jumpToAlienButton.getCommand());
        pauseButton.setText("Play"); // Change the text to "Play" when paused
    }

    /**
     * Enables all game-related buttons.
     */
    private void enableButtons() {
        for (Button b : pausedButtonVector) {
            b.setEnabled(true);
            styleButton(b);
        }
     // Key Bindings and Listeners
        addKeyListener('e', expandButton.getCommand());
        addKeyListener('c', contractButton.getCommand());
        addKeyListener('s', scoreButton.getCommand());
        addKeyListener('r', moveRightButton.getCommand());
        addKeyListener('l', moveLeftButton.getCommand());
        addKeyListener('u', moveUpButton.getCommand());
        addKeyListener('d', moveDownButton.getCommand());
        addKeyListener('o', jumpToAstronautButton.getCommand());
        addKeyListener('a', jumpToAlienButton.getCommand());
        pauseButton.setText("Pause"); // Change the text to "Pause" when played
    }

    /**
     * The run method is responsible for updating the game view and managing the state of game-related buttons.
     * It checks if the game is paused and disables/enables buttons accordingly.
     * It also repaints the game view.
     */
    @Override
    public void run() {
        // Repaint the map view to update the screen
        mv.repaint();

        // Check if the game is paused
        if (gw.isPaused()) {
            // If the game is paused, disable all relevant buttons
            disableButtons();
        } else {
            // If the game is not paused, enable all relevant buttons
            enableButtons();
        }
    }

}
