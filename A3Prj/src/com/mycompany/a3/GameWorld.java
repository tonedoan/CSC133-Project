package com.mycompany.a3;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.ui.Dialog;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class GameWorld extends Observable implements ICollider {
	private int clock;
	private int score;
	private int astronautRes;
	private int alienRes;
	private int astronautRem;
	private int alienRem;
	private int maxWidth;
	private int maxHeight;
	private int movingCount = 0; // Track number of moving objects
    private int totalMovingObjects = 0; // Total number of moving objects
    private int timerSec = 20; // Sets timer to 20 milliseconds, can be changed.
	private int timerCount = 0;
	private boolean sound = false;
	private ArrayList<Observer> observers = new ArrayList<>();
	private GameObjectCollection gameObjects = new GameObjectCollection();
	private Spaceship spaceship;
	Random rand = new Random();
	private Alien lastCreatedAlien;
	private int flagTimer = 0;
    private BGSound bgSound;
	private Sound astroAlien, alienAlien, scoreSound;
    private boolean isPaused; // To track whether the game is paused

	
	/**
     * Initializes the game world with a spaceship, 4 astronauts, and 3 aliens.
     */
	public void init() {
		// Add the Spaceship to the game world
        spaceship = Spaceship.getSpaceship(maxWidth, maxHeight);
        gameObjects.add(spaceship);		
        for(int i = 0; i < 4; i++) { 
			gameObjects.add(new Astronaut(maxWidth, maxHeight, this));
		}
		for(int i = 0; i < 3; i++) { 
			Alien alien = new Alien(maxWidth, maxHeight, this);
			gameObjects.add(alien);
			parentSetSpawn(alien); // To allow first Aliens to spawn
		}
		gameStateChanged();

	}
	
	public void togglePause() {
        isPaused = !isPaused; // Toggle the pause state

        if (isPaused) {
            bgSound.pause();  // Stop background sound when paused
        } else {
        	// Only play background sound if sound is enabled
            if (getSound()) {
                bgSound.play();   // Restart background sound when resumed
            }
        }
    }
	
	
	public boolean isPaused() {
		return isPaused;
	}
	/**
	 * Sets the canSpawn flag to true for a specific alien.
	 * 
	 * @param alien The alien for which to set the canSpawn flag.
	 */
	public void parentSetSpawn(Alien alien) {
	    if (alien != null) {
	        alien.setSpawn(true); // Enable spawning for this alien
	    }
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
            spaceship.jumpToLocation(alien.getPoint());
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
            spaceship.jumpToLocation(astronaut.getPoint());
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
     * No Longer needed for this Assignment
     */
//	public void map() {
//		IIterator goi = gameObjects.getIterator();
//        GameObject curr = null;
//        
//        while(goi.hasNext()) {
//        	curr = goi.getNext();
//        	System.out.println(curr.toString());
//        }
//	}

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
	
	public void gameTick(int timerSec) {
		if (isPaused) {
            return;  // Skip game logic if the game is paused
        }
		
	    prepareForNextTick();
	    
	    timerCount += timerSec;
	    if (timerCount >= 1000) { // To account for overflow
	        this.clock += 1; // Clock is updated per second now dependent on the timerSec.
	        timerCount = 0;
	    }
	    
	    // Move all moving objects in the world
	    IIterator goi = gameObjects.getIterator();
	    GameObject curr = null;
	    
	    while(goi.hasNext()) {
	        curr = goi.getNext();
	        if(curr instanceof IMoving) {
	            IMoving mObj = (IMoving) curr;
	            mObj.move(timerSec); // Move each object based on the time passed
	        }
	    }
	    collisionCheck();
	    resetFlags();
	    if(getSound()) {
			bgSound.play();
		}
	}
	
	public void resetFlags() {
		// Once certain time has passed remove aliens from Vectors to allow spawning again.
	    IIterator goi1 = gameObjects.getIterator();
	    GameObject curr1 = null;
	    flagTimer += timerSec;
	    if(flagTimer >= 20000) {
	    	while(goi1.hasNext()) {
		        curr1 = goi1.getNext();
		        if(curr1 instanceof Alien) {
		            ((Alien) curr1).setSpawn(true);	// Set spawn to true after certain time elapsed.
		        }
		        if(curr1 instanceof Astronaut) {	// Set vulnerable to true after certain time elapsed.
		            ((Astronaut) curr1).setVul(true);
		        }
		    }
	    	flagTimer = 0;
	    }
	}
	
	/**
	 * When aliens collide with astronaut run this method
	 */
	public void attack(Astronaut victim) {
		Sound sound = getAstroAlienSound();
		if (victim != null) {
	        // Decrease the health and speed of the victim astronaut
			if (sound != null) {
			    sound.play();
			} else {
			    System.out.println("AstroAlien sound is null");
			}
	        victim.setHealth(victim.getHealth() - 1);
	        victim.setSpeed(victim.getSpeed() - 1);
	        victim.fadeColor();  // Assuming fadeColor is a visual effect when attacked

	        gameStateChanged();  // Notify that the game state has changed
	    } else {
	        System.out.println("Victim astronaut is null.");
	    }
	}

	public void collisionCheck() {
		// Collision detection pass
		IIterator iter1 = gameObjects.getIterator();
		while (iter1.hasNext()) {
		    GameObject obj1 = iter1.getNext();

		    IIterator iter2 = gameObjects.getIterator();
		    while (iter2.hasNext()) {
		        GameObject obj2 = iter2.getNext();
		        // Skip collision checks with itself
	            if (obj1 != obj2 && obj1.collidesWith(obj2)) {
	                obj1.handleCollision(obj2);
	            }
		    }
		}
	    gameStateChanged();
	}
	
	
	/**
	 * Attempts to create a new alien at it's parent location if there are at least two aliens in the world.
	 */
	public void spawnBabyAlien(Alien parent) {
		int alienCount = countObj("a");
		if(alienCount > 2 && alienCount < 30) {
		    // Use the parent's location (or any other logic you prefer) to spawn the new alien
		    Point parentPosition = parent.getPoint();
		    
		    // Create a new alien with the same world dimensions as the parent
		    Alien babyAlien = new Alien(maxWidth, maxHeight, this);
		    
		    // Set the baby's position to the parent's position (or nearby)
		    babyAlien.setX(parentPosition.getX() + rand.nextInt(3) - 1);  // Adjusting x slightly
		    babyAlien.setY(parentPosition.getY() + rand.nextInt(3) - 1);  // Adjusting y slightly
		    
		    // Add the new baby alien to the game world
		    gameObjects.add(babyAlien);
		    
		    // Save a reference to the last created alien, if needed
		    lastCreatedAlien = babyAlien;
		    
		    // Trigger game state update
		    gameStateChanged();
		}
	}
	
	/**
     * Opens the spaceship doors and processes the rescue or capture of nearby astronauts and aliens.
     * Updates the score and removes rescued or captured objects from the game world.
     */
	public void openDoor() {
		Sound sound = getAlienAlienSound();
	    
	    float spaceshipX = spaceship.getX();
	    float spaceshipY = spaceship.getY();
	    float spaceshipSize = spaceship.getSize();

	    // Define boundaries based on spaceship position and size
	    float halfSize = spaceshipSize / 2;
	    float boundLeft = spaceshipX - halfSize;
	    float boundRight = spaceshipX + halfSize;
	    float boundBottom = spaceshipY - halfSize;
	    float boundTop = spaceshipY + halfSize;

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
	                if(sound != null) {
	                	sound.play();
	                } else {
	                	System.out.print("Score sound is null.");
	                }
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


	public Alien getLastAlien() {
		return lastCreatedAlien;
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
	    if(sound) {
	    	bgSound.play();
	    } else {
	    	bgSound.pause();
	    }
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
	
	
	/**
	 * Gets the gameObjectsCollection
	 * @return the gameObjectsCollection
	 */
	public GameObjectCollection getGameObjects() {
		return gameObjects;
	}
	
	/**
	 * This method is used to see whether the count of the moving objects
	 * equals the total moving objects, if so notify observers.
	 * After, reset the movement tracking.
	 */
	public void notifyMovement() {
        movingCount++; // Increment the count of moving objects
        if (movingCount == totalMovingObjects) {
            // All moving objects have moved
            gameStateChanged(); // Notify state change once
            resetMovementTracking(); // Reset for the next tick
        }
    }
	
	/**
	 * Resets all the numbers of the total moving objects to zero so it can prepare 
	 * for the next game tick, also counts the amount of moving objects.
	 */
	public void prepareForNextTick() {
        IIterator goi = gameObjects.getIterator();
        totalMovingObjects = 0; // Reset total count

        while (goi.hasNext()) {
            GameObject curr = goi.getNext();
            if (curr instanceof IMoving) {
                totalMovingObjects++; // Count each moving object
            }
        }
        movingCount = 0; // Reset count for this tick
    }

	/**
	 * Resets the moving count of the objects to 0.
	 */
    private void resetMovementTracking() {
        movingCount = 0; // Reset the moving count for the next tick
    }

	public int getTimerSec() {
		return timerSec;
	}
	
	public void createSounds() {
        bgSound = new BGSound("music.mp3");
//        astroAlien = new Sound("boing.mp3"); // Inputstream not adding these sounds.
//        alienAlien = new Sound("bang.mp3"); 
//        scoreSound = new Sound("zoop.mp3");
    }
	
	// Getters for the sound objects, to access the specific sounds
    public BGSound getBgSound() {
        return bgSound;
    }

    public Sound getAstroAlienSound() {
        return astroAlien;
    }

    public Sound getAlienAlienSound() {
        return alienAlien;
    }

    public Sound getScoreSound() {
        return scoreSound;
    }

	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}

	public void heal() {
		// TODO Auto-generated method stub
		
	}
}
