package com.mycompany.a2;

import java.util.Vector;

public class GameObjectCollection implements ICollection {
	private Vector<GameObject> gameObjects;
	
	public GameObjectCollection() {
		this.gameObjects = new Vector<>();
	}
	
	@Override
	public void add(GameObject newObject) {
		gameObjects.addElement(newObject);
	}
	
	@Override
	public IIterator getIterator() {
		return new GameObjectIterator(gameObjects);
	}
	
	public int size() {
		return gameObjects.size();
	}
}
