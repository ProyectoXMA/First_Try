package com.mygdx.game;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.mygdx.game.util.Config;

/**
* This class is the entry point of the game, it contains the main method, which will be executed when launching
* the game.
*/
public class DesktopLauncher {

	//The frames per second at which the game will run.
	public static final int FRAMES_PER_SECOND = 30; //NFR002
	//Title of the game.
	public static final String GAME_TITLE = "Dragon Boat Racing";

	/**
	* The main method which is the entry point of the Java application.
	* @param arg The command line arguments passed to the application.
	*/
	public static void main (String[] arg) {
		// Create a new configuration object for the application
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		// Set the foreground frames per second for the application
		config.setForegroundFPS(FRAMES_PER_SECOND);

		// Set the title of the application window
		config.setTitle(GAME_TITLE);
		config.setResizable(false);

		//Set the game to execute automatically at 1920x1080 of resolution

		config.setFullscreenMode(chooseDisplayMode(Config.WIDTH, Config.HEIGHT));

		// Create a new application instance and start the game loop
		new Lwjgl3Application(new MyGdxGame(), config);
	}

	/**
	 * this functions sets the fullscreen resolution of the game
	 * @param width resolution width
	 * @param height resolution height
	 * @return
	 */
	public static Graphics.DisplayMode chooseDisplayMode(int width, int height) {
		// List all available display modes
		Lwjgl3Graphics.DisplayMode[] displayModes = Lwjgl3ApplicationConfiguration.getDisplayModes();
		Lwjgl3Graphics.DisplayMode selectedMode = null;

		// If not found, try to find 1920x1080 at any refresh rate
        for (Lwjgl3Graphics.DisplayMode displayMode : displayModes) {
            if (displayMode.width == width && displayMode.height == height) {
                selectedMode = displayMode;
                break;
            }
        }

        if (selectedMode != null) {
			return selectedMode;
		} else {
			System.err.println("No suitable display mode found for 1920x1080");
			return null;
		}
	}
}
