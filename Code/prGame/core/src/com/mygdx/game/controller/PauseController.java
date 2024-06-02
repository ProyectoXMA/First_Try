package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.util.Config;
import com.mygdx.game.view.MenuView;
import com.mygdx.game.view.MinigameScreen;
import com.mygdx.game.view.PauseViewScreen;
import com.mygdx.game.view.minigameTutorial;

public class PauseController implements Screen{

    private final MyGdxGame game;
    private final MenuView view;
    private PauseViewScreen pauseScreen;

    private final Stage stage;
    private final int BUTTON_WIDTH = Config.getWidth()/4;
    private final int BUTTON_HEIGHT = Config.getHeight()/8;
    private final float textSize = (float) BUTTON_HEIGHT / 135;

    //initialise skin
    Skin skin;

    //Booleans to see credits and tutorial

    //menu table
    Table menuPause;

    public PauseController(MyGdxGame game,PauseViewScreen pauseScreen){
        this.game = game;
        this.pauseScreen = pauseScreen;
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // create a view for the settings
        view = new MenuView(stage);

        // skin loading
        skin = new Skin(Gdx.files.internal("skins/glassy-ui.json"));
        // text to be shown in credits
    }

    /**
     * Creates a Table and all the Buttons necessary for the menu
     * It adds the listeners for the buttons
     */
    @Override
    public void show() {
        // Create a menu table that fills the screen. Everything menu related will go inside this table.
        menuPause = new Table();
        menuPause.setVisible(true);
        menuPause.setFillParent(true);
        menuPause.setDebug(false);
        stage.addActor(menuPause);

        //create buttons
        TextButton play = new TextButton("RESUME", skin);
        play.getLabel().setFontScale(textSize);
        
        TextButton settings = new TextButton("SETTINGS", skin);
        settings.getLabel().setFontScale(textSize);
        
        TextButton quit = new TextButton("QUIT", skin);
        quit.getLabel().setFontScale(textSize);

        //create game Title
        Label gameTitle = new Label("", skin);
        gameTitle.setStyle(skin.get("gameTitle", Label.LabelStyle.class));

        // add buttons to table
        menuPause.add(gameTitle).size((float) (BUTTON_WIDTH*3.5), BUTTON_HEIGHT).colspan(2).pad(0,0,100,0);
        menuPause.row().pad(0,0,20,0);
        menuPause.add(play).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(0,0,0,0).align(Align.right);
        menuPause.add(settings).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(0,0,0,0).align(Align.left);
        menuPause.row().pad(0,0,5,0);
        menuPause.add(quit).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);
        // create button mouse listeners
        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menuPause.setVisible(false);
                game.resume();
            }
        });

        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new SettingController(game));
            }
        });
    }

    /**
     * Calls the MenuView to draw everything on screen
     * @param delta delta
     */
    @Override
    public void render(float delta) {
        view.update();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
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
    public void hide() {
        // TODO Auto-generated method stub

    }

    /**
     * Removes current instance from memory avoids crashes,
     * should be called when moving through Screens
     */
    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
    }

}