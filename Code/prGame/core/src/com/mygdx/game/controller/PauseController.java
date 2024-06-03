package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
import com.mygdx.game.view.PauseView;

public class PauseController implements Screen{

    private final MyGdxGame game;
    private PauseView pauseView;
    private GeneralController generalController;

    private final Stage stage;
    private SpriteBatch batch;
    private final int BUTTON_WIDTH = Config.getWidth()/4;
    private final int BUTTON_HEIGHT = Config.getHeight()/8;
    private final float textSize = (float) BUTTON_HEIGHT / 135;

    //initialise skin
    Skin skin;
    //menu table
    Table menuPause;

    public PauseController(MyGdxGame game){
        this.game = game;
        this.pauseView = new PauseView(game, this);
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        // skin loading
        skin = new Skin(Gdx.files.internal("skins/glassy-ui.json"));
        batch = new SpriteBatch();
        generalController = GeneralController.getInstance(game);
    }

    /**
     * Creates a Table and all the Buttons necessary for the menu
     * It adds the listeners for the buttons
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Create a menu table that fills the screen. Everything menu related will go inside this table.
        batch = new SpriteBatch();
        menuPause = new Table();
        menuPause.setVisible(true);
        menuPause.setFillParent(true);
        menuPause.setDebug(true);
        stage.addActor(menuPause);
        //create buttons
        TextButton play = new TextButton("RESUME", skin);
        play.getLabel().setFontScale(textSize);
        
        
        TextButton quit = new TextButton("QUIT", skin);
        quit.getLabel().setFontScale(textSize);

        //create game Title
        Label gameTitle = new Label("", skin);
        gameTitle.setStyle(skin.get("gameTitle", Label.LabelStyle.class));

        // add buttons to table
        menuPause.add(gameTitle).size((float) (BUTTON_WIDTH*3.5), BUTTON_HEIGHT).colspan(2).pad(0,0,100,0);
        menuPause.row().pad(0,0,0,0);
        menuPause.add(play).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(0,0,0,0).align(Align.center);
        menuPause.row().pad(0,0,0,0);
        menuPause.add(quit).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(0,0,80,0).align(Align.center);
        menuPause.row().pad(0,0,0,0);
        batch.begin();
        menuPause.draw(batch, BUTTON_HEIGHT);
        batch.end();
        // create button mouse listeners
        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                generalController.resetLeg();
                generalController.showMainMenu();
            }
        });

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pauseView.dispose();
                generalController.showLegScreen();
            }
        });
        pauseView.show();

    }

    /**
     * Calls the MenuView to draw everything on screen
     * @param delta delta
     */
    @Override
    public void render(float delta) {
        pauseView.update();
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
        if (stage != null) stage.clear();
        if (batch != null) batch.dispose();
    }

    /**
     * Removes current instance from memory avoids crashes,
     * should be called when moving through Screens
     */
    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
        skin.dispose();
        batch.dispose();
    }

}