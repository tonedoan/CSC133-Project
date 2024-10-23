package com.mycompany.a2;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.ui.Dialog;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class GameWorld extends Observable {
	private int clock;
	private int score;
	private int astronautRes;
	private int alienRes;
	private int astronautRem;
	private int alienRem;
	private int maxWidth;
	private int maxHeight;
	private boolean sound = false;
	private ArrayList<Observer> observers = new ArrayList<>();
	private GameObjectCollection gameObjects = new GameObjectCollection();
	private Spaceship spaceship;
	Random rand = new Random();
	
	/**
     * Initializes the game world with a spaceship, 4 astronauts, and 3 aliens.
     */
	public void init() {
		// Add the Spaceship to the game world
        spaceship = Spaceship.getSpaceship(maxWidth, maxHeight);
        gameObjects.add(spaceship);		
        for(int i = 0; i < 4; i++) { 
			gameObjects.add(new Astronaut(maxWidth, maxHeight));
		}
		for(int i = 0; i < 3; i++) { 
			gameObjects.add(new Alien(maxWidth, maxHeight));
		}
		gameStateChanged();
	}
	
	/**
     * Expands the size of the spaceship.
     */
	public void expand() {
		System.out.println("Spaceship doors expanding...");
		spaceship.setSize(1000);
		gameStateChanged();
	}
	
	/**
     * Contracts the size of the spaceship.
     */
	public void contract() {
		System.out.println("Spaceship doors contracting...");
		spaceship.setSize(50);
		gameStateChanged();
	}
	
	/**
     * Moves the spaceship to the right.
     */
	public void moveSpaceRight() {
		System.out.println("Spaceship moving right...");
		spaceship.moveRight();
		gameStateChanged();
	}
	
	/**
     * Moves the spaceship to the left.
     */
	public void moveSpaceLeft() {
		System.out.println("Spaceship moving left...");
		spaceship.moveLeft();
		gameStateChanged();
	}
	
	/**
     * Moves the spaceship up.
     */
	public void moveSpaceUp() {
		System.out.println("Spaceship moving up...");
		spaceship.moveUp();
		gameStateChanged();
	}
	
	/**
     * Moves the spaceship down.
     */
	public void moveSpaceDown() {
		System.out.println("Spaceship moving down...");
		spaceship.moveDown();
		gameStateChanged();
	}
	
	/**
     * Returns a random alien from the list of game objects.
     *
     * @return a random Alien object if available, or null if no aliens are present.
     */
	public GameObject getRandomAlien() {
        ArrayList<Alien> aliens = new ArrayList<>();
        IIterator goi = gameObjects.getIterator();
        GameObject curr = null;
        
        while(goi.hasNext()) {
        	curr = goi.getNext();
	        // Populate the list with aliens
	        if(curr instanceof Alien) {
	        	aliens.add((Alien) curr);
	        }
        }
        
        // Return a random alien if available
        if (!aliens.isEmpty()) {
            int randomIndex = rand.nextInt(aliens.size());
            return aliens.get(randomIndex);
        }
        return null; // No aliens available
    }

	/**
     * Returns a random astronaut from the list of game objects.
     *
     * @return a random Astronaut object if available, or null if no astronauts are present.
     */
    public GameObject getRandomAstro() {
        ArrayList<Astronaut> astros = new ArrayList<>();
        IIterator goi = gameObjects.getIterator();
        GameObject curr = null;
        
        while(goi.hasNext()) {
        	curr = goi.getNext();
        
	        // Populate the list with astronauts
	        if(curr instanceof Astronaut) {
	        	astros.add((Astronaut) curr);
	        }
        }
        // Return a random astronaut if available
        if (!astros.isEmpty()) {
            int randomIndex = rand.nextInt(astros.size());
            return astros.get(randomIndex);
        }
        return null; // No astronauts available
    }

    /**
     * Jumps the spaceship to a random alien's position.
     */
    public void jumpToRandomAlien() {
		System.out.println("Jumping to random alien.");
        Alien alien = (Alien) getRandomAlien();
        if (alien != null) {
            spaceship.setPoint(alien.getPoint());
        }
		gameStateChanged();
    }

    /**
     * Jumps the spaceship to a random astronaut's position.
     */
    public void jumpToRandomAstro() {
		System.out.println("Jumping to random astronaut.");
        Astronaut astronaut = (Astronaut) getRandomAstro();
        if (astronaut != null) {
            spaceship.setPoint(astronaut.getPoint());
        }
		gameStateChanged();
    }
	
    /**
     * Gets the current game clock.
     *
     * @return the current game clock value.
     */
	public int getClock() {
		return clock;
	}
	
	/**
     * Gets the current score.
     *
     * @return the current score.
     */
	public int getScore() {
		return score;
	}
	
	/**
     * Gets the number of astronauts rescued.
     *
     * @return the number of astronauts rescued.
     */
	public int getAstronautRes() {
		return astronautRes;
	}
	
	/**
     * Gets the number of aliens rescued.
     *
     * @return the number of aliens rescued.
     */
	public int getAlienRes() {
		return alienRes;
	}
	
	/**
     * Gets the number of astronauts remaining.
     *
     * @return the number of astronauts remaining.
     */
	public int getAstronautRem() {
		return astronautRem;
	}
	
	/**
     * Gets the number of aliens remaining.
     *
     * @return the number of aliens remaining.
     */
	public int getAlienRem() {
		return alienRem;
	}
	
	
	
	/**
     * Prints the state of all game objects in the world.
     */
	public void map() {
		IIterator goi = gameObjects.getIterator();
        GameObject curr = null;
        
        while(goi.hasNext()) {
        	curr = goi.getNext();
        	System.out.println(curr.toString());
        }
	}

	/**
     * Counts the remaining astronauts or aliens based on the type.
     *
     * @param type the type of object to count ("as" for astronauts, "a" for aliens).
     * @return the count of remaining objects of the specified type.
     */
	public int countObj(String type) {
		int astroRem = 0;
		int alienRem = 0;
		IIterator goi = gameObjects.getIterator();
        GameObject curr = null;
        
        while(goi.hasNext()) {
        	curr = goi.getNext();
        	if(curr instanceof Astronaut) {
				astroRem++;
			}
			if(curr instanceof Alien) {
				alienRem++;
			}
        }
		if(type.equals("a")) {
			return alienRem;
		} else {
			return astroRem;
		}
	}
	
	/**
     * Advances the game clock and moves all moving objects in the world.
     */
	public void gameTick() {
		System.out.println("Clock has ticked.");
		this.clock += 1;
		IIterator goi = gameObjects.getIterator();
        GameObject curr = null;
        
        while(goi.hasNext()) {
        	curr = goi.getNext();
        	if(curr instanceof IMoving) {
				IMoving mObj = (IMoving) curr;
				mObj.move();
			}
        }
        setChanged();
        notifyObservers();
	}
	
	/**
     * Opens the spaceship doors and processes the rescue or capture of nearby astronauts and aliens.
     * Updates the score and removes rescued or captured objects from the game world.
     */
	public void openDoor() {
	    System.out.println("Spaceship doors opening...");
	    
	    float spaceshipX = spaceship.getX();
	    float spaceshipY = spaceship.getY();
	    float spaceshipSize = spaceship.getSize();

	    // Define boundaries based on spaceship position and size
	    float boundLeft = spaceshipX;
	    float boundBottom = spaceshipY;
	    float boundRight = spaceshipX + spaceshipSize;
	    float boundTop = spaceshipY + spaceshipSize;

	    IIterator goi = gameObjects.getIterator();
	    GameObject curr;

	    while (goi.hasNext()) {
	        curr = goi.getNext();
	        float objX = curr.getX();
	        float objY = curr.getY();

	        // Check if the object is within the bounds of the spaceship
	        if (boundLeft <= objX && boundRight >= objX && boundBottom <= objY && boundTop >= objY) {
	            if (curr instanceof Astronaut) {
	                Astronaut a = (Astronaut) curr;
	                score += a.getHealth() * 10; // Update score based on astronaut health
	                astronautRes += 1; // Increment rescued astronaut count
		            goi.remove(); // Remove the current object from the game world
	            } else if (curr instanceof Alien) {
	                score -= 10; // Penalize for capturing an alien
	                alienRes += 1; // Increment captured alien count
		            goi.remove(); // Remove the current object from the game world
	            }
	        }
	    }
	    
	    // Now check if all astronauts are rescued after processing removals
	    if (countObj("as") == 0) {
	        Dialog.show("Game Over", "All astronauts have been rescued!", "OK", null);
	        System.exit(0); // Exit the game
	    }
	    
	    gameStateChanged();
	}


	/**
     * Attempts to create a new alien at a random position if there are at least two aliens in the world.
     */
	public void alienClone() {
		ArrayList<Alien> aliens = new ArrayList<>();
		IIterator goi = gameObjects.getIterator();
        GameObject curr = null;
        
        while(goi.hasNext()) {
        	curr = goi.getNext();
        	if(curr instanceof Alien) {
                aliens.add((Alien) curr);
            }
        }
        if(aliens.size() >= 2) {
			Point nearbyPoint = getRandomAlien().getPoint();
			Alien babyAlien = new Alien(maxWidth, maxHeight);
			int randomPos = rand.nextInt(1);
			if(randomPos == 0) {
				randomPos = 5;
			} else {
				randomPos = -5;
			}
			babyAlien.setX(nearbyPoint.getX() + randomPos);
			babyAlien.setY(nearbyPoint.getY() + randomPos);
			gameObjects.add(babyAlien);
			System.out.println("Another alien appeared!");
			gameStateChanged();
		} else {
			System.out.println("Alien count less than 2. No aliens created.");
		}
	}
	
	/**
     * Mimics the attempt for whether an astronaut is attacked by an alien and 
     * decreases health and speed 
     */
	public void attack() {
	    ArrayList<Astronaut> astronauts = new ArrayList<>();
	    IIterator goi = gameObjects.getIterator();
	    GameObject curr = null;

	    // Collect all astronauts
	    while (goi.hasNext()) {
	        curr = goi.getNext();
	        if (curr instanceof Astronaut) {
	            astronauts.add((Astronaut) curr);
	        }
	    }
	    
	    if (!astronauts.isEmpty()) {
	        System.out.println("Astronaut getting attacked.");
	        
	        // Filter astronauts with health greater than 0
	        ArrayList<Astronaut> aliveAstronauts = new ArrayList<>();
	        for (Astronaut astronaut : astronauts) {
	            if (astronaut.getHealth() > 0) {
	                aliveAstronauts.add(astronaut);
	            }
	        }

	        // Check if there are any astronauts left with health > 0
	        if (!aliveAstronauts.isEmpty()) {
	            int randomIndex = rand.nextInt(aliveAstronauts.size());
	            Astronaut selectedAstronaut = aliveAstronauts.get(randomIndex);

	            // Decrease the health and speed of the selected astronaut
	            selectedAstronaut.setHealth(selectedAstronaut.getHealth() - 1);
	            selectedAstronaut.setSpeed(selectedAstronaut.getSpeed() - 1);
	            selectedAstronaut.fadeColor();

	            gameStateChanged();
	        } else {
	            System.out.println("No astronauts available.");
	        }
	    } else {
	        System.out.println("No astronauts available.");
	    }
	}


	/**
	 * Adds an observer to the list of observers.
	 *
	 * @param observer the observer to be added.
	 */
	public void addObserver(Observer observer) {
	    observers.add(observer);
	}

	/**
	 * Removes an observer from the list of observers.
	 *
	 * @param observer the observer to be removed.
	 */
	public void removeObserver(Observer observer) {
	    observers.remove(observer);
	}

	/**
	 * Notifies all observers of a change in the game state.
	 * This method marks the observable as changed and 
	 * notifies all registered observers.
	 */
	public void gameStateChanged() {
	    setChanged();
	    notifyObservers();
	}

	/**
	 * Gets the current sound state.
	 *
	 * @return true if sound is enabled, false otherwise.
	 */
	public boolean getSound() {
	    return sound;
	}

	/**
	 * Returns the current sound status as a string.
	 *
	 * @return "ON" if sound is enabled, "OFF" if sound is disabled.
	 */
	public String printSoundStatus() {
	    if (sound) {
	        return "ON";
	    } else {
	        return "OFF";
	    }
	}

	/**
	 * Toggles the sound state between ON and OFF.
	 * If the sound is currently ON, it will be turned OFF, and vice versa.
	 * Notifies observers of the state change.
	 */
	public void toggleSound() {
	    sound = !sound;
	    gameStateChanged();
	}

	/**
	 * Sets the maximum width of the game world.
	 *
	 * @param width the maximum width to set.
	 */
	public void setWidth(int width) {
	    maxWidth = width;
	}

	/**
	 * Sets the maximum height of the game world.
	 *
	 * @param height the maximum height to set.
	 */
	public void setHeight(int height) {
	    maxHeight = height;
	}

	/**
	 * Gets the maximum width of the game world.
	 *
	 * @return the maximum width of the game world.
	 */
	public int getWidth() {
	    return maxWidth;
	}

	/**
	 * Gets the maximum height of the game world.
	 *
	 * @return the maximum height of the game world.
	 */
	public int getHeight() {
	    return maxHeight;
	}
}
