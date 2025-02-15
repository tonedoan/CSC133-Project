package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents a view of the game map, which updates in response to changes in the GameWorld.
 * This class implements the Observer interface to listen for changes in the observable GameWorld.
 */
public class MapView extends Container implements Observer {
    
    private GameWorld gw; // The GameWorld instance to be observed
	private int timerSec;

    /**
     * Constructs a MapView with the specified GameWorld.
     *
     * @param gw the GameWorld instance to be observed by this MapView
     */
    public MapView(GameWorld gw) {
        this.gw = gw;
    }

    /**
     * Updates the MapView based on changes in the GameWorld.
     * This method is called whenever the observable GameWorld notifies its observers.
     *
     * @param observable the observable object (GameWorld) that has changed
     * @param data additional data provided by the observable (can be null)
     */
    @Override
    public void update(Observable observable, Object data) {
        this.repaint(); // Call repaint to update view based on the new game state
    }
    
    /**
     * Paints the GameObjects within the MapView container.
     * This method iterates through all the GameObjects in the GameWorld
     * and draws them relative to the position of the MapView container.
     *
     * @param g The Graphics object used for drawing the objects.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call the superclass's paint method to ensure proper painting behavior.
        this.timerSec = gw.getTimerSec();
        gw.gameTick(timerSec);
        
        // Create a Point representing the position of the MapView relative to its parent.
        Point pCmpRelPrnt = new Point(getX(), getY());

        // Get an iterator for the collection of GameObjects in the GameWorld.
        IIterator goi = gw.getGameObjects().getIterator();

        // Iterate through each GameObject in the GameWorld.
        while(goi.hasNext()) {
            // Get the next GameObject.
            GameObject gameObject = goi.getNext();

            // Retrieve the position of the current GameObject.
            Point gameObjectPoint = new Point(gameObject.getX(), gameObject.getY());

            // Adjust the GameObject's position relative to the MapView's position
            // and pass the adjusted point to the draw method for proper rendering.
            gameObject.draw(g, new Point(
                pCmpRelPrnt.getX() + gameObjectPoint.getX(), // Adjust X position
                pCmpRelPrnt.getY() + gameObjectPoint.getY()  // Adjust Y position
            ));
        }
    }
    
    /**
     * Handles the pointerPressed event, which is triggered when a user clicks on the screen.
     * This method processes the click to check if it was on an astronaut and toggles the selection 
     * state of the astronaut. If no astronaut was clicked, all astronauts are deselected.
     *
     * @param x the x-coordinate of the pointer in the MapView
     * @param y the y-coordinate of the pointer in the MapView
     */
    @Override
    public void pointerPressed(int x, int y) {
        boolean clickedOnAstronaut = false;
        
        // Adjust the pointer coordinates based on MapView's position relative to its parent container
        x = x - getParent().getAbsoluteX();
        y = y - getParent().getAbsoluteY();
        
        // Create points to represent the pointer's position relative to the parent and component
        Point pPtrRelPrnt = new Point(x, y);
        Point pCmpRelPrnt = new Point(getX(), getY());

        // Iterate through each GameObject in the GameWorld.
        IIterator goi = gw.getGameObjects().getIterator();
        while(goi.hasNext()) {
            // Get the next GameObject.
            GameObject gameObject = goi.getNext();

            // Check if the object is selectable and if the pointer is within its bounds
            if(gameObject instanceof ISelectable) {
                if (((ISelectable) gameObject).contains(pPtrRelPrnt, pCmpRelPrnt) && gw.isPaused()) {  // Check if game is paused and if pointer is inside the object
                    if (gameObject instanceof Astronaut) {
                        Astronaut astronaut = (Astronaut) gameObject;

                        // Toggle the astronaut's selection state (select or deselect)
                        astronaut.setSelected(!astronaut.isSelected());
                        
                        clickedOnAstronaut = true;
                        // After making changes, repaint the MapView to update the view
                    }
                }
                
                // If no astronaut was clicked, deselect all astronauts
                if (!clickedOnAstronaut) {
                    IIterator goiDeselect = gw.getGameObjects().getIterator();
                    while (goiDeselect.hasNext()) {
                        // Get the next GameObject.
                        GameObject gameObject1 = goiDeselect.getNext();

                        if (gameObject1 instanceof Astronaut) {
                            Astronaut astronaut = (Astronaut) gameObject1;
                            // Deselect all astronauts
                            astronaut.setSelected(false);
                        }
                    }
                }
            }
        }
        
        // Repaint the MapView to reflect the changes
        repaint();
    }

} 

