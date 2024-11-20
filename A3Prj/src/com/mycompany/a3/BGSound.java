package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * BGSound handles background music playback in the game.
 * It initializes the media and provides methods to play and pause the background sound.
 */
public class BGSound implements Runnable{
	private Media m;
	
	/**
     * Constructor for the BGSound class.
     * Tries to load the audio file and initializes the Media object.
     * @param fileName the name of the audio file to be played
     */
	public BGSound(String fileName) {
		while (m == null) {
			try {
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
				m = MediaManager.createMedia(is, "audio/mp3", this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
     * Pauses the background sound.
     */
	public void pause() {
		m.pause();
	}

	/**
     * Plays the background sound.
     */
	public void play() {
		m.play();
	}
	
	/**
     * Gets the Media object for the background sound.
     * @return the Media object
     */
	public Media getMedia() {
		return m;
	}
	
	/**
     * The run method starts the media playback from the beginning.
     * This is used to reset the audio and restart it from the beginning.
     * Currently not working as expected.
     */
	@Override
	public void run() {
		m.setTime(0);	// Reset the media to the start
        m.play();		// Play the media from the beginning
	}
}