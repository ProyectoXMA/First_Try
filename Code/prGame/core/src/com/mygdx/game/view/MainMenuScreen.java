package com.mygdx.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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

    //texture definition for buttons
    Texture playLevelButton;
    Texture playLevelButtonSel;
    Texture selectionButton;
    Texture selectionButtonSel;
    Texture tutorialButton;
    Texture tutorialButtonSel;
    Texture settingsButton;
    Texture settingsButtonSel;
    Texture quitButton;
    Texture quitButtonSel;
    //default button dimensions
    private int BUTTONWIDTH = Config.WIDTH/5;
    private int BUTTONHEIGHT = Config.HEIGHT/13;
    private float BUTTONX = (float) (Config.WIDTH - BUTTONWIDTH) /2;
    private float BUTTONY = ((float) Config.HEIGHT /2);

    //MARGINS FOR BUTTONS
    private float MARGIN = (float) BUTTONHEIGHT/4;

    //Coordinates for the play button
    private float playButtonX = BUTTONX;
    private float playButtonY = BUTTONY;

    //Coordinates for the selection menu button
    private float selectionButtonX = BUTTONX;
    private float selectionButtonY = playButtonY - BUTTONHEIGHT - MARGIN;

    //Coordinates for the tutorial button
    private float tutorialButtonX = BUTTONX;
    private float tutorialButtonY = selectionButtonY - BUTTONHEIGHT - MARGIN;

    //Coordinates for the settings button
    private float settingsButtonX = BUTTONX;
    private float settingsButtonY = tutorialButtonY - BUTTONHEIGHT - MARGIN;

    //Coordinates for the quit button
    private float quitButtonX = BUTTONX;
    private float quitButtonY = settingsButtonY - BUTTONHEIGHT - MARGIN;

    public MainMenuScreen(final MyGdxGame game) {
        this.game = game;
        this.controller = new MenuController(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WIDTH, Config.HEIGHT);
        MenuImage = new Texture(Gdx.files.internal("dragon.jpeg"));

        //initialisation of buttons
        playLevelButton = new Texture(Gdx.files.internal("PLAY.png"));
        playLevelButtonSel = new Texture(Gdx.files.internal("PLAY_SEL.png"));
        selectionButton = new Texture(Gdx.files.internal("BOATS.png"));
        selectionButtonSel = new Texture(Gdx.files.internal("BOATS_SEL.png"));
        tutorialButton = new Texture(Gdx.files.internal("TUTORIAL.png"));
        tutorialButtonSel = new Texture(Gdx.files.internal("TUTORIAL_SEL.png"));
        settingsButton = new Texture(Gdx.files.internal("SETTINGS.png"));
        settingsButtonSel = new Texture(Gdx.files.internal("SETTINGS_SEL.png"));
        quitButton = new Texture(Gdx.files.internal("QUIT.png"));
        quitButtonSel = new Texture(Gdx.files.internal("QUIT_SEL.png"));
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

        //mouse coordinates
        float mouseX = Gdx.input.getX();
        float mouseY = Config.HEIGHT - Gdx.input.getY();

        //Starts drawing all images
        game.batch.begin();
        //draws the initial Main background dragon image
        game.batch.draw(MenuImage, 0, 0, Config.WIDTH, Config.HEIGHT);

        //draws the buttons
        drawButtons(mouseX, mouseY);

        //ends the draw
        game.batch.end();

        //logic to detect if buttons are clicked
        if (Gdx.input.isTouched()) {
            onButtonClicked(mouseX, mouseY);
        }
    }

    /**
     * This method is used to check whether the user is hovering over a button.
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     * @param x X position of the button
     * @param y Y position of the button
     * @return True if the mouse is inside the button, false otherwise.
     */
    private boolean isInsideButton(float mouseX, float mouseY, float x, float y) {
        return mouseX >= x && mouseX <= x + BUTTONWIDTH &&
                mouseY >= y && mouseY <= y + BUTTONHEIGHT;
    }

    /**
     * This method is used to draw a button on the screen, taking into account if its hovered or not.
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     * @param x X position of the button
     * @param y Y position of the button
     * @param defaultTexture Default texture of the button
     * @param hoveredTexture Hovered texture of the button
     */
    private void drawButton(float mouseX, float mouseY, float x, float y, float buttonX, float buttonY,Texture defaultTexture, Texture hoveredTexture) {
        boolean isInside = isInsideButton(mouseX, mouseY, x, y);
        Texture buttonTexture = isInside? hoveredTexture : defaultTexture;
        game.batch.draw(buttonTexture, buttonX, buttonY, BUTTONWIDTH, BUTTONHEIGHT);
    }

    /**
     * This method is called constantly to draw the 5 main buttons on the screen,
     * it will detect if the mouse is hovered on top and make the buttons change colour.
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     */
    private void drawButtons(float mouseX, float mouseY) {
        //Logic to see if buttons are hovered
        //Play
        drawButton(mouseX, mouseY, playButtonX, playButtonY, playButtonX, playButtonY, playLevelButton, playLevelButtonSel);
        //Choose Boat
        drawButton(mouseX, mouseY, selectionButtonX, selectionButtonY, selectionButtonX, selectionButtonY, selectionButton, selectionButtonSel);
        //Tutorial
        drawButton(mouseX, mouseY, tutorialButtonX, tutorialButtonY, tutorialButtonX, tutorialButtonY, tutorialButton, tutorialButtonSel);
        //Settings
        drawButton(mouseX, mouseY, settingsButtonX, settingsButtonY, settingsButtonX, settingsButtonY, settingsButton, settingsButtonSel);
        //Quit
        drawButton(mouseX, mouseY, quitButtonX, quitButtonY, quitButtonX, quitButtonY, quitButton, quitButtonSel);
    }

    /**
     * This method is called if input of the mouse is received.
     * Depending on the selected button it will do certain action.
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     */
    private void onButtonClicked(float mouseX, float mouseY) {
        boolean insidePlay = mouseX >= playButtonX && mouseX <= playButtonX + BUTTONWIDTH &&
                mouseY >= playButtonY && mouseY <= playButtonY + BUTTONHEIGHT;
        boolean insideBoat = mouseX >= selectionButtonX && mouseX <= selectionButtonX + BUTTONWIDTH &&
                mouseY >= selectionButtonY && mouseY <= selectionButtonY + BUTTONHEIGHT;
        boolean insideTutorial = mouseX >= tutorialButtonX && mouseX <= tutorialButtonX + BUTTONWIDTH &&
                mouseY >= tutorialButtonY && mouseY <= tutorialButtonY + BUTTONHEIGHT;
        boolean insideSettings = mouseX >= settingsButtonX && mouseX <= settingsButtonX + BUTTONWIDTH &&
                mouseY >= settingsButtonY && mouseY <= settingsButtonY + BUTTONHEIGHT;
        boolean insideQuit = mouseX >= quitButtonX && mouseX <= quitButtonX + BUTTONWIDTH &&
                mouseY >= quitButtonY && mouseY <= quitButtonY + BUTTONHEIGHT;

        //Play
        if (insidePlay) {
            game.setScreen(new RaceScreen(game));
            dispose();
        }

        //Choose Boat
        if (insideBoat) {
            game.setScreen(new RaceScreen(game));
            dispose();
        }

        //Tutorial
        if (insideTutorial) {
            game.setScreen(new RaceScreen(game));
            dispose();
        }

        //Settings
        if (insideSettings) {
            settingsMenu();
            dispose();
        }

        //Quit
        if (insideQuit) {
            Gdx.app.exit();
        }
    }

    /**
     *
     */
    private void settingsMenu() {
    }

    /**
     * This method is called when the screen should resize itself.
     * It should never be used (we have fixed resolutions).
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