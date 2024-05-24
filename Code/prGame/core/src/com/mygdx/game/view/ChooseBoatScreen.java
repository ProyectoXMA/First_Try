package com.mygdx.game.view;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameState;
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

    // Dimension for Button
    private final int arrowHeight = 50;
    private final int arrowWidth = 50;
    private final int leftButtonX = 0;
    private final int leftButtonY = (Config.HEIGHT/2);;
    private final int rightButtonX = (Config.HEIGHT - arrowWidth);
    private final int rightButtonY = (Config.HEIGHT/2);;

    private final int chooseButtonHeight = 50;
    private final int chooseButtonWidth = 200;
    private final int chooseButtonX = (Config.WIDTH/2);
    private final int chooseButtonY = 400;


    // Constructor
    public ChooseBoatScreen(final MyGdxGame game) {
        this.game = game;
        this.controller = new MenuController(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        //Initialize Buttons
        leftButton = new Texture(Gdx.files.internal("playButton.png"));
        rightButton = new Texture(Gdx.files.internal("quitButton.png"));
        chooseButton = new Texture(Gdx.files.internal("bucket.png"));
    }


// Methods

    /// @param stage displays all the actors involved in the screen (UI, buttons, labels, etc.)
    @Override
    public void show() {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
    }

    // for now just creates a blank screen
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();

        game.batch.begin();
        game.batch.draw(leftButton,leftButtonX,leftButtonY, arrowWidth, arrowHeight);
        game.batch.draw(rightButton,rightButtonX,rightButtonY, arrowWidth, arrowHeight);
        game.batch.draw(chooseButton,chooseButtonX,chooseButtonY,chooseButtonWidth,chooseButtonHeight);

        game.batch.end();

        //Mouse coordinates
        float mouseX = Gdx.input.getX();
        float mouseY = Config.HEIGHT - Gdx.input.getY();

        //Logic to detect button clicks
        if (Gdx.input.isTouched()) {
            //LeftButtonPressed
            if (mouseX >= leftButtonX && mouseX <= leftButtonX + arrowWidth&&
                    mouseY >= leftButtonY && mouseY <= leftButtonY + arrowHeight) {
                game.setScreen(new ChooseBoatScreen(game));
                dispose();
            }
            //RightButtonPressed
            if (mouseX >= rightButtonX && mouseX <= rightButtonX + arrowWidth&&
                    mouseY >= rightButtonY && mouseY <= rightButtonY + arrowHeight) {
                game.setScreen(new ChooseBoatScreen(game));
                dispose();
            }
            //ChooseButtonPressed
            if (mouseX >= chooseButtonX && mouseX <= chooseButtonX + arrowWidth&&
                    mouseY >= chooseButtonY && mouseY <= chooseButtonY + arrowHeight) {
                //Assigned new boat to the player and go back to main menu.
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        stage.dispose();
    }


    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }


    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }


    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

}
