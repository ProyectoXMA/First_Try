package com.mygdx.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.MenuController;
import com.mygdx.game.util.Config;

/**
 * This class represents the main menu screen of the game.
 * It listens to the events and notify the controller.
 * It implements the Screen interface from the LibGDX library.
 */
public class MainMenuView implements Screen {
    private final MyGdxGame game;
    private final MenuController controller;
    OrthographicCamera camera;
    Viewport viewport;
    private final Texture MenuImage = new Texture(Gdx.files.internal("menu_background.jpeg"));

    //texture definition for buttons
    private final Texture playLevelButton = new Texture(Gdx.files.internal("buttons/PLAY.png"));
    private final Texture playLevelButtonSel = new Texture(Gdx.files.internal("buttons/PLAY_SEL.png"));
    private final Texture selectionButton = new Texture(Gdx.files.internal("buttons/BOATS.png"));
    private final Texture selectionButtonSel = new Texture(Gdx.files.internal("buttons/BOATS_SEL.png"));
    private final Texture tutorialButton = new Texture(Gdx.files.internal("buttons/TUTORIAL.png"));
    private final Texture tutorialButtonSel = new Texture(Gdx.files.internal("buttons/TUTORIAL_SEL.png"));
    private final Texture settingsButton = new Texture(Gdx.files.internal("buttons/SETTINGS.png"));
    private final Texture settingsButtonSel = new Texture(Gdx.files.internal("buttons/SETTINGS_SEL.png"));
    private final Texture quitButton= new Texture(Gdx.files.internal("buttons/QUIT.png"));
    private final Texture quitButtonSel = new Texture(Gdx.files.internal("buttons/QUIT_SEL.png"));

    //Coordinates for the play button
    private float playButtonY;

    //Coordinates for the selection menu button
    private float selectionButtonY;

    //Coordinates for the tutorial button
    private float tutorialButtonY;

    //Coordinates for the settings button
    private float settingsButtonY;

    //Coordinates for the quit button
    private float quitButtonY;

    public MainMenuView(final MyGdxGame game) {
        this.game = game;
        this.controller = new MenuController(game);

        //Create the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.getWidth(), Config.getHeight());

        //Creating viewport
        viewport = new FitViewport(Config.getWidth(), Config.getHeight(), camera);
    }


    /**
     * This method is called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        //MARGINS FOR BUTTONS
        float margin = (float) controller.BUTTON_HEIGHT / 4;

        playButtonY = controller.getButtonY();
        selectionButtonY = playButtonY - controller.BUTTON_HEIGHT - margin;
        tutorialButtonY = selectionButtonY - controller.BUTTON_HEIGHT - margin;
        settingsButtonY = tutorialButtonY - controller.BUTTON_HEIGHT - margin;
        quitButtonY = settingsButtonY - controller.BUTTON_HEIGHT - margin;
    }

    /**
     * This method is called by the game loop from the application every time rendering should be performed.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Update the camera
        camera.update();

        // Set the viewport's dimensions
        viewport.update(Config.getWidth() , Config.getHeight());

        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set the projection matrix of the batch to the combined matrix of the camera and viewport
        game.batch.setProjectionMatrix(viewport.getCamera().combined);

        // Get mouse coordinates
        float mouseX = Gdx.input.getX();
        float mouseY = Config.getHeight() - Gdx.input.getY();

        // Start drawing all images
        game.batch.begin();

        // Draw the initial main background dragon image
        game.batch.draw(MenuImage, 0, 0, Config.getWidth(), Config.getHeight());

        // Draw the buttons
        drawButtons(mouseX, mouseY);

        // End the draw
        game.batch.end();

        controller.checkInput(mouseX, mouseY);
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
        boolean isInside = controller.isInsideButton(mouseX, mouseY, x, y);
        Texture buttonTexture = isInside? hoveredTexture : defaultTexture;
        game.batch.draw(buttonTexture, buttonX, buttonY, controller.BUTTON_WIDTH, controller.BUTTON_HEIGHT);
    }

    /**
     * This method is called constantly to draw the 5 main buttons on the screen,
     * it will detect if the mouse is hovered on top and make the buttons change colour.
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     */
    private void drawButtons(float mouseX, float mouseY) {
        //Play
        drawButton(mouseX, mouseY, controller.getButtonX(), playButtonY,
                controller.getButtonX(), playButtonY, playLevelButton, playLevelButtonSel);
        //Choose Boat
        drawButton(mouseX, mouseY, controller.getButtonX(), selectionButtonY,
                controller.getButtonX(), selectionButtonY, selectionButton, selectionButtonSel);
        //Tutorial
        drawButton(mouseX, mouseY, controller.getButtonX(), tutorialButtonY,
                controller.getButtonX(), tutorialButtonY, tutorialButton, tutorialButtonSel);
        //Settings
        drawButton(mouseX, mouseY, controller.getButtonX(), settingsButtonY,
                controller.getButtonX(), settingsButtonY, settingsButton, settingsButtonSel);
        //Quit
        drawButton(mouseX, mouseY, controller.getButtonX(), quitButtonY,
                controller.getButtonX(), quitButtonY, quitButton, quitButtonSel);
    }

    /**
     * This method is called when the screen should resize itself.
     * It should never be used (we have fixed resolutions).
     * @param width The new width in pixels.
     * @param height The new height in pixels.
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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