package com.mycompany.a3;

import java.util.Vector;

/**
 * The GameObjectCollection class implements the ICollection interface,
 * providing a collection to manage GameObject instances.
 */
public class GameObjectCollection implements ICollection {
    private Vector<GameObject> gameObjects;

    /**
     * Constructs a new GameObjectCollection, initializing an empty Vector.
     */
    public GameObjectCollection() {
        this.gameObjects = new Vector<>();
    }

    /**
     * Adds a new GameObject to the collection.
     *
     * @param newObject the GameObject to be added
     * @throws IllegalArgumentException if newObject is null
     */
    @Override
    public void add(GameObject newObject) {
        if (newObject == null) {
            throw new IllegalArgumentException("GameObject cannot be null");
        }
        gameObjects.addElement(newObject);
    }

    /**
     * Returns an iterator for the collection of GameObjects.
     *
     * @return an IIterator for the GameObjectCollection
     */
    @Override
    public IIterator getIterator() {
        return new GameObjectIterator(gameObjects);
    }

    /**
     * Returns the number of GameObjects in the collection.
     *
     * @return the size of the collection
     */
    public int size() {
        return gameObjects.size();
    }

    /**
     * Removes a GameObject from the collection.
     *
     * @param object the GameObject to be removed
     */
    public void remove(GameObject object) {
        gameObjects.remove(object);
    }

    /**
     * Clears all GameObjects from the collection.
     */
    public void clear() {
        gameObjects.clear();
    }
}
