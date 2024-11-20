package com.mycompany.a3;

/**
 * Represents a collection of game objects that can be iterated over.
 * This interface provides methods to add game objects and retrieve an iterator for the collection.
 */
public interface ICollection {
    
    /**
     * Returns an iterator for the collection of game objects.
     *
     * @return an instance of IIterator to iterate over the collection.
     */
    public IIterator getIterator();
    
    /**
     * Adds a new game object to the collection.
     *
     * @param newObject the game object to be added to the collection.
     */
    void add(GameObject newObject);
}
