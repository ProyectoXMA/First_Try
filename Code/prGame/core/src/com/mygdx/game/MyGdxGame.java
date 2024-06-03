package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.controller.GeneralController;
import com.mygdx.game.model.Player;
import com.mygdx.game.controller.MenuController;

/**
 * The MyGdxGame class extends the Game class from the LibGDX framework.
 * It represents the main entry point of the game application.
 */
public class MyGdxGame extends Game {
	private Player player;
	public Player getPlayer() {
		return player;
	}

	/**
	 * The create method is called when the application is first created.
	 * It initializes the SpriteBatch and BitmapFont objects and sets the initial screen to MainMenuScreen.
	 */
	public void create() {
		player = new Player();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		GeneralController.getInstance(this).showMainMenu();
	}

	/**
	 * The render method is called every frame. It delegates the render call to the current screen.
	 */
	public void render() {
		super.render();
	}

	/**
	 * The dispose method is called when the application is closing.
	 * It releases all the resources used by the SpriteBatch and BitmapFont objects.
	 */
	public void dispose() {
	}
}