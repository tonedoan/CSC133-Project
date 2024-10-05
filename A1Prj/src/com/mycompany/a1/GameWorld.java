package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.models.Point;

import java.util.ArrayList;


public class GameWorld {
	private int clock;
	private int score;
	private int astronautRes;
	private int alienRes;
	private int astronautRem;
	private int alienRem;
	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private Spaceship spaceship = new Spaceship();
	Random rand = new Random();
	
	/**
     * Initializes the game world with a spaceship, 4 astronauts, and 3 aliens.
     */
	public void init() {
		gameObjects.add(spaceship);
		for(int i = 0; i < 4; i++) { 
			gameObjects.add(new Astronaut());
		}
		for(int i = 0; i < 3; i++) { 
			gameObjects.add(new Alien());
		}
	}
	
	/**
     * Expands the size of the spaceship.
     */
	public void expand() {
		System.out.println("Spaceship doors expanding...");
		spaceship.setSize(1000);
	}
	
	/**
     * Contracts the size of the spaceship.
     */
	public void contract() {
		System.out.println("Spaceship doors contracting...");
		spaceship.setSize(50);
	}
	
	/**
     * Moves the spaceship to the right.
     */
	public void moveSpaceRight() {
		System.out.println("Spaceship moving right...");
		spaceship.moveRight();
	}
	
	/**
     * Moves the spaceship to the left.
     */
	public void moveSpaceLeft() {
		System.out.println("Spaceship moving left...");
		spaceship.moveLeft();
	}
	
	/**
     * Moves the spaceship up.
     */
	public void moveSpaceUp() {
		System.out.println("Spaceship moving up...");
		spaceship.moveUp();
	}
	
	/**
     * Moves the spaceship down.
     */
	public void moveSpaceDown() {
		System.out.println("Spaceship moving down...");
		spaceship.moveDown();
	}
	
	/**
     * Returns a random alien from the list of game objects.
     *
     * @return a random Alien object if available, or null if no aliens are present.
     */
	public GameObject getRandomAlien() {
        Random rand = new Random();
        ArrayList<Alien> aliens = new ArrayList<>();
        
        // Populate the list with aliens
        for(GameObject gameObj : gameObjects) {
            if(gameObj instanceof Alien) {
                aliens.add((Alien) gameObj);
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
        Random rand = new Random();
        ArrayList<Astronaut> astros = new ArrayList<>();
        
        // Populate the list with astronauts
        for(GameObject gameObj : gameObjects) {
            if(gameObj instanceof Astronaut) {
                astros.add((Astronaut) gameObj);
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
    * Returns the list of all game objects.
    *
    * @return the list of game objects.
    */
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	/**
     * Prints the state of all game objects in the world.
     */
	public void map() {
		for(int i=0; i <gameObjects.size(); i++) {
			System.out.println(gameObjects.get(i).toString());
		}
	}
	
	/**
     * Prints the current game state, including clock, score, rescued astronauts, and remaining aliens.
     */
	public void printState() {
		System.out.println("Clock: " + getClock());
		System.out.println("Current Score: " + getScore());
		System.out.println("Astronauts Rescued: " + getAstronautRes());
		System.out.println("Aliens snuck: " + getAlienRes());
		System.out.println("Astronauts left: " + countObj("as"));
		System.out.println("Aliens left: " + countObj("a"));
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
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Astronaut) {
				astroRem++;
			}
			if(gameObjects.get(i) instanceof Alien) {
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
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof IMoving) {
				IMoving mObj = (IMoving) gameObjects.get(i);
				mObj.move();
			}
		}
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
	    float boundLeft = spaceshipX;
	    float boundBottom = spaceshipY;
	    float boundRight = spaceshipX + spaceshipSize;
	    float boundTop = spaceshipY + spaceshipSize;
	    
	    for (int i = gameObjects.size() - 1; i >= 0; i--) { // Iterate in reverse order to prevent index out of bounds
	        GameObject obj = gameObjects.get(i);
	        
	        float objX = obj.getX();
	        float objY = obj.getY();
	        if(boundLeft <= objX && boundRight >= objX && boundBottom <= objY && boundTop >= objY) {
	        	if(obj instanceof Astronaut) {
	        		Astronaut a = (Astronaut) obj;
	        		score += a.getHealth() * 10;
	        		astronautRes += 1;
	        	} else {
	        		score -= 10;
	        		alienRes += 1;
	        	}
	        	gameObjects.remove(i);
	        }
	        
	    }
	    if (countObj("as") == 0) {
	    	System.out.println("All astronauts rescued. Game Over.");
	    	printState();
	    	System.exit(0);
	    }
	}

	/**
     * Attempts to create a new alien at a random position if there are at least two aliens in the world.
     */
	public void alienClone() {
		ArrayList<Alien> aliens = new ArrayList<>();
        
        for(GameObject gameObj : gameObjects) {
            if(gameObj instanceof Alien) {
                aliens.add((Alien) gameObj);
            }
        }
        
		if(aliens.size() >= 2) {
			Point nearbyPoint = getRandomAlien().getPoint();
			Alien babyAlien = new Alien();
			int randomPos = rand.nextInt(1);
			if(randomPos == 0) {
				randomPos = 5;
			} else {
				randomPos = -5;
			}
			babyAlien.setX(nearbyPoint.getX() + randomPos);
			babyAlien.setX(nearbyPoint.getY() + randomPos);
			gameObjects.add(babyAlien);
			System.out.println("Another alien appeared!");
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
	    for (GameObject gameObj : gameObjects) {
	        if (gameObj instanceof Astronaut) {
	            astronauts.add((Astronaut) gameObj);
	        }
	    }

	    if (!astronauts.isEmpty()) {
			System.out.println("Astronaut getting attacked.");
	        int randomIndex = rand.nextInt(astronauts.size());
	        Astronaut selectedAstronaut = astronauts.get(randomIndex);

	        // Decrease the health and speed of the selected astronaut
	        if(selectedAstronaut.getHealth() > 0) {
	        	selectedAstronaut.setHealth(selectedAstronaut.getHealth() - 1);
	        	selectedAstronaut.setSpeed(selectedAstronaut.getSpeed() - 1);
	        	selectedAstronaut.fadeColor();
	        }
	    } else {
	    	System.out.println("No astronauts available.");
	    }
	}

	
}
