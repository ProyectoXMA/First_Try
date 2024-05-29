package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.MainMenuScreen;

/**
 * The MyGdxGame class extends the Game class from the LibGDX framework.
 * It represents the main entry point of the game application.
 */
public class MyGdxGame extends Game {

	/**
	 * The SpriteBatch object is used to draw 2D images.
	 */
	public SpriteBatch batch;

	/**
	 * The BitmapFont object is used to draw text.
	 */
	public BitmapFont font;

	/**
	 * The create method is called when the application is first created.
	 * It initializes the SpriteBatch and BitmapFont objects and sets the initial screen to MainMenuScreen.
	 */
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // Default Arial font
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.log("Input","Initiating log messages");
		this.setScreen(new MainMenuScreen(this));
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
		batch.dispose();
		font.dispose();
	}
}