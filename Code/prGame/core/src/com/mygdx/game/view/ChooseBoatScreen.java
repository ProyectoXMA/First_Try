package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.MenuController;
import com.mygdx.game.util.Config;

public class ChooseBoatScreen implements Screen {
    private final MyGdxGame game;
    private final MenuController controller;
    OrthographicCamera camera;

    //Attributes for the screen
    private Stage stage;
    private Viewport viewport;

    //Texture for Button
    Texture leftButton;
    Texture rightButton;
    Texture chooseButton;
    Texture chooseButtonSel;

    //Texture for boats
    Texture[] boatTexture = new Texture[4];
    private int actualBoat;
    private boolean clickNotPressed;

    // Dimension for Button
    private final int arrowHeight = 70;
    private final int arrowWidth = 70;
    private final int leftButtonX = 20;
    private final int leftButtonY = (Config.HEIGHT / 2);
    private final int rightButtonX = (Config.WIDTH - arrowWidth - 20);
    private final int rightButtonY = (Config.HEIGHT / 2);

    private final int chooseButtonHeight = 80;
    private final int chooseButtonWidth = 230;
    private final int chooseButtonX = (Config.WIDTH / 2) - (chooseButtonWidth / 2);
    private final int chooseButtonY = 5;

    private final int boatMenuHeight = 370;
    private final int boatMenuWidth = 400;
    private final int boatMenuX = (Config.WIDTH / 2) - (boatMenuWidth / 2);
    private final int boatMenuY = (Config.HEIGHT -boatMenuHeight -10);



    // Constructor
    public ChooseBoatScreen(final MyGdxGame game) {
        this.game = game;
        this.controller = new MenuController(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WIDTH, Config.HEIGHT);

        // Initialize Buttons
        leftButton = new Texture(Gdx.files.internal("arrowLeft.png"));
        rightButton = new Texture(Gdx.files.internal("arrowRight.png"));
        chooseButton = new Texture(Gdx.files.internal("chooseButton.png"));
        chooseButtonSel = new Texture(Gdx.files.internal("chooseButtonSel.png"));

        // Initialize BoatTextureDisplay
        actualBoat = 0;
        clickNotPressed = true;
        for (int i = 0; i < 4; i++) {
            boatTexture[i] = new Texture(Gdx.files.internal("boatMenu" + i + ".png"));
        }
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
    }

    private boolean isInsideButton(float mouseX, float mouseY, float x, float y, float width, float height) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
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
    private void drawButton(float mouseX, float mouseY, float x, float y, float width, float height, Texture defaultTexture, Texture hoveredTexture) {
        boolean isInside = isInsideButton(mouseX, mouseY, x, y, width, height);
        Texture buttonTexture = isInside ? hoveredTexture : defaultTexture;
        game.batch.draw(buttonTexture, x, y, width, height);
    }









    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();

        //Mouse coordinates
        float mouseX = Gdx.input.getX();
        float mouseY = Config.HEIGHT - Gdx.input.getY();

        game.batch.begin();
        game.batch.draw(leftButton, leftButtonX, leftButtonY, arrowWidth, arrowHeight);
        game.batch.draw(rightButton, rightButtonX, rightButtonY, arrowWidth, arrowHeight);
        drawButton(mouseX, mouseY, chooseButtonX, chooseButtonY, chooseButtonWidth, chooseButtonHeight, chooseButton,chooseButtonSel);
        game.batch.draw(boatTexture[actualBoat], boatMenuX, boatMenuY, boatMenuWidth, boatMenuHeight);
        game.batch.end();



        // Logic to detect button clicks
        if (Gdx.input.isTouched()) {
            if (clickNotPressed) {
                clickNotPressed = false;

                // LeftButtonPressed
                if (mouseX >= leftButtonX && mouseX <= leftButtonX + arrowWidth &&
                        mouseY >= leftButtonY && mouseY <= leftButtonY + arrowHeight) {
                    actualBoat--;
                    if (actualBoat < 0) {
                        actualBoat = boatTexture.length - 1;
                    }
                    dispose();
                }

                // RightButtonPressed
                if (mouseX >= rightButtonX && mouseX <= rightButtonX + arrowWidth &&
                        mouseY >= rightButtonY && mouseY <= rightButtonY + arrowHeight) {
                    actualBoat++;
                    if (actualBoat >= boatTexture.length) {
                        actualBoat = 0;
                    }
                    dispose();
                }

                // ChooseButtonPressed
                if (mouseX >= chooseButtonX && mouseX <= chooseButtonX + chooseButtonWidth &&
                        mouseY >= chooseButtonY && mouseY <= chooseButtonY + chooseButtonHeight) {
                    // Assigned new boat to the player and go back to main menu.
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
                }
            }
        } else {
            clickNotPressed = true;
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        }
}