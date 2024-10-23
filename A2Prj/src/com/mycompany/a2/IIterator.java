package com.mycompany.a2;

/**
 * Represents an iterator for traversing a collection of GameObject instances.
 */
public interface IIterator {
    
    /**
     * Checks if there are more elements to iterate over.
     *
     * @return true if there are more elements; false otherwise.
     */
    public boolean hasNext();
    
    /**
     * Retrieves the next GameObject in the iteration.
     *
     * @return the next GameObject.
     * @throws NoSuchElementException if the iteration has no more elements.
     */
    public GameObject getNext();
    
    /**
     * Removes the last returned GameObject from the underlying collection.
     * This method can only be called after a call to getNext().
     *
     * @throws IllegalStateException if the method is called before getNext()
     *                                or if the last call to remove was already
     *                                called after the last getNext().
     */
    public void remove();
}
