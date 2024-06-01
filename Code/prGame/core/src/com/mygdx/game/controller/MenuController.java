package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.util.Config;
import com.mygdx.game.view.MenuView;
import com.mygdx.game.controller.LegController;
import com.mygdx.game.view.MinigameScreen;

public class MenuController implements Screen{

    private final MyGdxGame game;
    private final MenuView view;
    private final Stage stage;
    private final int BUTTON_WIDTH = Config.getWidth()/4;
    private final int BUTTON_HEIGHT = Config.getHeight()/8;
    private final float textSize = (float) BUTTON_HEIGHT / 135;

    public MenuController(MyGdxGame game){
        this.game = game;

        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // create a view for the settings
        view = new MenuView(stage);
    }

    /**
     * Creates a Table and all the Buttons necessary for the menu
     * It adds the listeners for the buttons
     */
    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skins/glassy-ui.json"));

        //create buttons
        TextButton play = new TextButton("PLAY", skin);
        play.getLabel().setFontScale(textSize);
        TextButton boats = new TextButton("BOATS", skin);
        boats.getLabel().setFontScale(textSize);
        TextButton tutorial = new TextButton("TUTORIAL", skin);
        tutorial.getLabel().setFontScale(textSize);
        TextButton settings = new TextButton("SETTINGS", skin);
        settings.getLabel().setFontScale(textSize);
        TextButton credits = new TextButton("CREDITS", skin);
        credits.getLabel().setFontScale(textSize);
        TextButton quit = new TextButton("QUIT", skin);
        quit.getLabel().setFontScale(textSize);

        //add buttons to table
        table.add(play).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.row();
        table.add(boats).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.row();
        table.add(tutorial).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.row();
        table.add(settings).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.row();
        table.add(credits).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.row();
        table.add(quit).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);


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
                game.setScreen(new LegController(game));
            }
        });

        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new SettingController(game));
            }
        });

        tutorial.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO
                dispose();
                game.setScreen(new MinigameScreen(game, null));
            }
        });

        boats.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO
            }
        });

        //Create button keyboard listeners



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