package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.util.Config;
import com.mygdx.game.util.KeyBindings;

/**
* This class is the entry point of the game, it contains the main method, which will be executed when launching
* the game.
*/
public class DesktopLauncher {
	//Title of the game.
	public static final String GAME_TITLE = "Dragon Boat Racing";

	/**
	* The main method which is the entry point of the Java application.
	* @param arg The command line arguments passed to the application.
	*/
	public static void main (String[] arg) {
		// Create a new configuration object for the application
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		Config.keyBinds = new KeyBindings();

		// Set the foreground frames per second for the application
		config.setForegroundFPS(Config.FPS);

		// Set the title of the application window
		config.setTitle(GAME_TITLE);
		config.setResizable(false);

		//Set the game to execute automatically at 1920x1080 of resolution
		config.setWindowedMode(Config.getWidth(), Config.getHeight());

		// Create a new application instance and start the game loop
		new Lwjgl3Application(new MyGdxGame(), config);
	}


}
