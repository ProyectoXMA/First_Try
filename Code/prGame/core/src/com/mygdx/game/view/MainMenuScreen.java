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
    Texture selectionButton;
    Texture tutorialButton;
    Texture settingsButton;
    Texture quitButton;
    //default button dimensions
    private int BUTTONWIDTH = Config.WIDTH/5;
    private int BUTTONHEIGHT = Config.HEIGHT/10;
    private float BUTTONX = (Config.WIDTH - BUTTONWIDTH) / 2;
    private float BUTTONY = (Config.HEIGHT/2);

    public MainMenuScreen(final MyGdxGame game) {
        this.game = game;
        this.controller = new MenuController(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WIDTH, Config.HEIGHT);
        MenuImage = new Texture(Gdx.files.internal("dragon2.jpeg"));

        //initialization of buttons
            playLevelButton = new Texture(Gdx.files.internal("playButton.png"));
            selectionButton = new Texture(Gdx.files.internal("playButton.png"));
            tutorialButton = new Texture(Gdx.files.internal("playButton.png"));
            settingsButton = new Texture(Gdx.files.internal("playButton.png"));
            quitButton = new Texture(Gdx.files.internal("quitButton.png"));
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

        //MARGINS FOR BUTTONS
        float MARGIN = (float) BUTTONHEIGHT/2;

        //Coordinates for the play button
        float playButtonX = BUTTONX;
        float playButtonY = BUTTONY;

        //Coordinates for the selection menu button
        float selectionButtonX = BUTTONX;
        float selectionButtonY = playButtonY - BUTTONHEIGHT - MARGIN;

        //Coordinates for the tutorial button
        float tutorialButtonX = BUTTONX;
        float tutorialButtonY = selectionButtonY - BUTTONHEIGHT - MARGIN;

        //Coordinates for the settings button
        float settingsButtonX = BUTTONX;
        float settingsButtonY = tutorialButtonY - BUTTONHEIGHT - MARGIN;

        //Coordinates for the quit button
        float quitButtonX = BUTTONX;
        float quitButtonY = settingsButtonY - BUTTONHEIGHT - MARGIN;

        game.batch.begin();

        //draws the initial Main background dragon image
        game.batch.draw(MenuImage, 0, 0, Config.WIDTH, Config.HEIGHT);

        //draws the buttons
        game.batch.draw(playLevelButton, BUTTONX, BUTTONY, BUTTONWIDTH, BUTTONHEIGHT);
        game.batch.draw(selectionButton, BUTTONX, selectionButtonY, BUTTONWIDTH, BUTTONHEIGHT);
        game.batch.draw(tutorialButton, BUTTONX, tutorialButtonY, BUTTONWIDTH, BUTTONHEIGHT);
        game.batch.draw(settingsButton, BUTTONX, settingsButtonY, BUTTONWIDTH, BUTTONHEIGHT);
        game.batch.draw(quitButton, BUTTONX, quitButtonY, BUTTONWIDTH, BUTTONHEIGHT);

        game.batch.end();

        //mouse coordinates
        float mouseX = Gdx.input.getX();
        float mouseY = Config.HEIGHT - Gdx.input.getY();

        //logic to detect if buttons are clicked
        if (Gdx.input.isTouched()) {

            //Play
            if (mouseX >= playButtonX && mouseX <= playButtonX + BUTTONWIDTH &&
                    mouseY >= playButtonY && mouseY <= playButtonY + BUTTONHEIGHT) {
                        game.setScreen(new MinigameScreen(game, null));
                dispose();
            }
            //Choose Boat
            if (mouseX >= selectionButtonX && mouseX <= selectionButtonX + BUTTONWIDTH &&
                    mouseY >= selectionButtonX && mouseY <= selectionButtonY + BUTTONHEIGHT) {
                        game.setScreen(new RaceScreen(game));
                dispose();
            }
            //Tutorial
            if (mouseX >= tutorialButtonX && mouseX <= tutorialButtonX + BUTTONWIDTH &&
                    mouseY >= tutorialButtonX && mouseY <= tutorialButtonY + BUTTONHEIGHT) {
                game.setScreen(new RaceScreen(game));
                dispose();
            }
            //Settings
            if (mouseX >= settingsButtonX && mouseX <= settingsButtonX + BUTTONWIDTH &&
                    mouseY >= settingsButtonX && mouseY <= settingsButtonY + BUTTONHEIGHT) {
                game.setScreen(new RaceScreen(game));
                dispose();
            }
            //Quit
            if (mouseX >= quitButtonX && mouseX <= quitButtonX + BUTTONWIDTH &&
                    mouseY >= quitButtonY && mouseY <= quitButtonY + BUTTONHEIGHT) {
                Gdx.app.exit();
            }
        }
    }

    /**
     * This method is called when the screen should resize itself.
     * @param width The new width in pixels.
     * @param height The new height in pixels.
     */
    @Override
    public void resize(int width, int height) {
        Config.HEIGHT = height;
        Config.WIDTH = width;
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