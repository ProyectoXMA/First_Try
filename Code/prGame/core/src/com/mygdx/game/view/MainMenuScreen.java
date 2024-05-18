package com.mygdx.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.MenuController;
import com.mygdx.game.util.Config;

/**
 * This class represents the main menu screen of the game.
 * It listens to the events and notify the controller.
 * It implements the Screen interface from the LibGDX library.
 */
public class MainMenuScreen implements Screen {
    private final MyGdxGame game;
    private final MenuController controller;
    OrthographicCamera camera;
    private final Texture MenuImage;

    //texture defination for buttons
    Texture playLevelButton;
    Texture quitButton;

    public MainMenuScreen(final MyGdxGame game) {
        this.game = game;
        this.controller = new MenuController(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WIDTH, Config.HEIGHT);
        MenuImage = new Texture(Gdx.files.internal("dragon2.jpeg"));

        //initialization of buttons
        playLevelButton = new Texture("button.png");
        quitButton = new Texture("button.png");

        //Creacion de bottones
//        Skin buttonSkin = new Skin();
//        buttonSkin.add("button", new Texture("droplet.png"));
//        TextButton button1 = new TextButton("Test button", buttonSkin);
    }

    /**
     * This method is called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {

    }

    /**
     * This method is called by the game loop from the application every time rendering should be performed.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(MenuImage, 0, 0, Config.WIDTH, Config.HEIGHT);
        game.batch.draw(playLevelButton, 100, 100, 50, 50);
        game.batch.draw(quitButton, 100, 150, 50, 50);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new RaceScreen(game));
            dispose();
        }

    }

    /**
     * This method is called when the screen should resize itself.
     * @param width The new width in pixels.
     * @param height The new height in pixels.
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * This method is called when the application is paused.
     */
    @Override
    public void pause() {

    }

    /**
     * This method is called when the application is resumed from a paused state.
     */
    @Override
    public void resume() {

    }

    /**
     * This method is called when this screen is no longer the current screen for a Game.
     */
    @Override
    public void hide() {

    }

    /**
     * This method is called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}