package com.mycompany.a2;

import java.util.Vector;

public class GameObjectIterator implements IIterator{
	private Vector<GameObject> gameObjects;
	private int currElementIndex;
	
	public GameObjectIterator(Vector<GameObject> gameObjectCollection) {
		this.gameObjects = gameObjectCollection;
		this.currElementIndex = -1;
	}
	
	@Override
	public boolean hasNext() {
		return currElementIndex + 1< gameObjects.size();
	}

	@Override
    public GameObject getNext() {
        if (hasNext()) {
            return gameObjects.get(currElementIndex++);
        }
        else {
        	return null;
        }
    }
	
	@Override
    public void remove() {
        if (currElementIndex >= 0 && currElementIndex < gameObjects.size()) {
            gameObjects.removeElementAt(currElementIndex);
            currElementIndex--; // Adjust index to avoid skipping the next element
        } else {
            throw new IllegalStateException("No element to remove or invalid index.");
        }
    }
}
