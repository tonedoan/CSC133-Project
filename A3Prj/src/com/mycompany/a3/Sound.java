package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * The Sound class is a command that interacts with the GameWorld to toggle sound settings.
 * It provides methods to play and pause the sound when triggered.
 */
public class Sound {
    private Media m;

    /**
     * Constructs a Sound command with the specified file name.
     *
     * @param fileName the name of the audio file to be played
     */
    public Sound(String fileName) {
    	while (m == null) {
			try {
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
				this.m = MediaManager.createMedia(is, "audio/mp3");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }

    /**
     * Plays the sound if it is properly initialized.
     */
    public void play() {
        if (m != null) {
            m.play();
        } else {
            System.err.println("Media not loaded.");
        }
    }

    /**
     * Pauses the sound if it is currently playing.
     */
    public void pause() {
        if (m != null) {
            m.pause();
        }
    }

    /**
     * Gets the media object representing the sound.
     *
     * @return the Media object
     */
    public Media getMedia() {
        return m;
    }
}
