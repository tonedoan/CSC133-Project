package com.mycompany.a2;

import com.codename1.ui.Container;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents a view of the game map, which updates in response to changes in the GameWorld.
 * This class implements the Observer interface to listen for changes in the observable GameWorld.
 */
public class MapView extends Container implements Observer {
    
    private GameWorld gw; // The GameWorld instance to be observed

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
        gw.map(); // Calls the map method on the GameWorld to refresh the map view
    }
}
