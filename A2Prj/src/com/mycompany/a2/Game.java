package com.mycompany.a2;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar; 
public class Game extends Form {
    private GameWorld gw;
    private MapView mv;
    private ScoreView sv;
    private int maxWidth;
    private int maxHeight;
	private Vector<Button> buttonVector = new Vector<>();
    
    public Game() {
        gw = new GameWorld();
        mv = new MapView(gw);
        sv = new ScoreView(gw);
        gw.addObserver(mv);
        gw.addObserver(sv);
        
        
        this.setLayout(new BorderLayout());
        Container mainContainer = new Container();
        mainContainer.setLayout(new BorderLayout());
        
        // North, South, East, West, Center containers
        Container northContainer = new Container(new BorderLayout());
        Container southContainer = new Container(new FlowLayout(CENTER));
        Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container centerContainer = new Container();
        
        // Create buttons
        Button jumpToAstronautButton = new Button(new JumpToRandomAstronaut(gw));
        Button moveLeftButton = new Button(new MoveLeft(gw));
        Button moveUpButton = new Button(new MoveUp(gw));
        Button expandButton = new Button(new Expand(gw));
        
        Button scoreButton = new Button(new Score(gw));
        Button jumpToAlienButton = new Button(new JumpToRandomAlien(gw));
        Button moveRightButton = new Button(new MoveRight(gw));
        Button moveDownButton = new Button(new MoveDown(gw));
        Button contractButton = new Button(new Contract(gw));
        
        Button newAlienButton = new Button(new NewAlien(gw));
        Button fightButton = new Button(new Fight(gw));
        Button tickButton = new Button(new Tick(gw));
        
        // Setting width and height of center container for mv
        centerContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255,0,0)));
        

        // Adding buttons to containers
        centerContainer.add(mv);
        northContainer.add(BorderLayout.NORTH, sv); // Add ScoreView to the north section
        southContainer.add(newAlienButton);
        southContainer.add(fightButton);
        southContainer.add(tickButton);
        
        westContainer.add(expandButton);
        westContainer.add(moveUpButton);
        westContainer.add(moveLeftButton);
        westContainer.add(jumpToAstronautButton);
        
        eastContainer.add(scoreButton);
        eastContainer.add(jumpToAlienButton);
        eastContainer.add(moveRightButton);
        eastContainer.add(moveDownButton);
        eastContainer.add(contractButton);
        
        buttonVector.add(expandButton);
        buttonVector.add(contractButton);
        buttonVector.add(moveUpButton);
        buttonVector.add(moveDownButton);
        buttonVector.add(moveLeftButton);
        buttonVector.add(moveRightButton);
        buttonVector.add(jumpToAstronautButton);
        buttonVector.add(jumpToAlienButton);
        buttonVector.add(scoreButton);
        buttonVector.add(newAlienButton);
        buttonVector.add(fightButton);
        buttonVector.add(tickButton);
        
        for(Button b : buttonVector) {
        	styleButton(b);
        }

        
        // Add the main containers to the main layout
        mainContainer.add(BorderLayout.CENTER, centerContainer);
        mainContainer.add(BorderLayout.NORTH, northContainer);
        mainContainer.add(BorderLayout.SOUTH, southContainer);
        mainContainer.add(BorderLayout.WEST, westContainer);
        mainContainer.add(BorderLayout.EAST, eastContainer);
        
        // Final assembly of the form
        this.add(BorderLayout.CENTER, mainContainer);
        
        // Using this to test center container.
        Label centerLabel = new Label("Width: " + maxWidth + " Height: " + maxHeight + "     ");
        centerContainer.add(centerLabel);
        
        // Toolbar setup
        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        Command help = new Help();
        Command quit = new ExitGame(); // Pass gw to ExitGame if needed
        CheckBox sound = new CheckBox();
        sound.getAllStyles().setBgTransparency(255);
        sound.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        Command soundBox = new Sound(gw);
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
        addKeyListener('w', newAlienButton.getCommand());
        addKeyListener('f', fightButton.getCommand());
        addKeyListener('t', tickButton.getCommand());
        
        // Show the game form
        this.show();
        maxWidth = centerContainer.getWidth();
        maxHeight = centerContainer.getHeight();
        gw.setHeight(maxHeight);
        gw.setWidth(maxWidth);
        gw.init();
        centerLabel.setText("Width: " + maxWidth + " Height: " + maxHeight);
    }
    
    private void styleButton(Button button) {
    	button.getAllStyles().setBgColor(ColorUtil.BLUE);
    	button.getAllStyles().setBgTransparency(255);
    	button.getAllStyles().setFgColor(ColorUtil.rgb(255,255,255));
    	button.getAllStyles().setPadding(Component.TOP, 5);
    	button.getAllStyles().setPadding(Component.BOTTOM, 5);
    	button.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.GRAY));
    }
}
