package com.mycompany.a3;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * The GameObjectIterator class implements the IIterator interface,
 * providing an iterator for a collection of GameObject instances.
 */
public class GameObjectIterator implements IIterator {
    private Vector<GameObject> gameObjects;
    private int currElementIndex;
    private int lastReturnedIndex = -1; // Track the last returned index for remove

    /**
     * Constructs a new GameObjectIterator for the given collection of GameObjects.
     *
     * @param gameObjectCollection the collection of GameObjects to iterate over
     */
    public GameObjectIterator(Vector<GameObject> gameObjectCollection) {
        this.gameObjects = gameObjectCollection;
        this.currElementIndex = 0;
    }

    /**
     * Checks if there are more GameObjects to iterate over.
     *
     * @return true if there are more elements, false otherwise
     */
    @Override
    public boolean hasNext() {
        return currElementIndex < gameObjects.size();
    }

    /**
     * Retrieves the next GameObject in the iteration.
     *
     * @return the next GameObject
     * @throws NoSuchElementException if there are no more elements to iterate
     */
    @Override
    public GameObject getNext() {
        if (hasNext()) {
            GameObject currentObject = gameObjects.get(currElementIndex);
            lastReturnedIndex = currElementIndex; // Store the index of the last returned object
            currElementIndex++;  // Increment after accessing the object
            return currentObject;
        } else {
            throw new NoSuchElementException("No more elements to iterate.");
        }
    }

    /**
     * Removes the last GameObject returned by the iterator.
     *
     * @throws IllegalStateException if the remove operation is invalid
     */
    @Override
    public void remove() {
        if (lastReturnedIndex < 0 || lastReturnedIndex >= gameObjects.size()) {
            throw new IllegalStateException("No element to remove or invalid index.");
        }
        gameObjects.removeElementAt(lastReturnedIndex);
        // Reset lastReturnedIndex to prevent invalid state
        lastReturnedIndex = -1; 
        currElementIndex--; // Adjust the current index
    }
}
