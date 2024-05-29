package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
* This class is the entry point of the game, it contains the main method, which will be executed when launching
* the game.
*/
public class DesktopLauncher {

	//The frames per second at which the game will run.
	public static final int FRAMES_PER_SECOND = 30; //NFR002
	//Title of the game.
	public static final String GAME_TITLE = "Dragon_Boat_Race";

	/**
	* The main method which is the entrfy point of the Java application.
	* @param arg The command line arguments passed to the application.
	*/
	public static void main (String[] arg) {
		// Create a new configuration object for the application
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		// Set the foreground frames per second for the application
		config.setForegroundFPS(FRAMES_PER_SECOND);

		// Set the title of the application window
		config.setTitle(GAME_TITLE);

		// Create a new application instance and start the game loop
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
