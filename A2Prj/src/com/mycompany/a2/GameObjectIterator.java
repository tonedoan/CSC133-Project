package com.mycompany.a2;

import java.util.NoSuchElementException;
import java.util.Vector;

public class GameObjectIterator implements IIterator {
    private Vector<GameObject> gameObjects;
    private int currElementIndex;
    private int lastReturnedIndex = -1; // Track the last returned index for remove

    public GameObjectIterator(Vector<GameObject> gameObjectCollection) {
        this.gameObjects = gameObjectCollection;
        this.currElementIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currElementIndex < gameObjects.size();
    }

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
