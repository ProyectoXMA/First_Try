package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.util.Config;
import com.mygdx.game.util.UserAction;
import com.mygdx.game.view.BoatSelectionView;

public class BoatSelectionController implements Screen{

    private final MyGdxGame game;
    private final BoatSelectionView view;
    private final Stage stage;
    private final int BUTTON_WIDTH = Config.getWidth()/3;
    private final int BUTTON_HEIGHT = Config.getHeight()/8;
    private final float imageSize = (float) ((float) Config.getWidth() * 0.31);
    private final float textSize = (float) BUTTON_HEIGHT / 135;
    private final Texture classicBoat = new Texture(Gdx.files.internal("boats/ClassicBoat_large.png"));
    private final Texture resistanceBoat = new Texture(Gdx.files.internal("boats/resistanceBoat_large.png"));
    private final Texture speedBoat = new Texture(Gdx.files.internal("boats/speedBoat_large.png"));

    //Lists for cycling
    private final Texture[] boatList = new Texture[3];
    private final String[] boatNameList = new String[3];

    //integer to know what is the list item being seen
    private int i = 0;

    //changing menu
    Label boatName;
    Image boat;
    Image boat1;
    Image boat2;

    public BoatSelectionController(MyGdxGame game){
        this.game = game;

        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // create a view for the election menu
        view = new BoatSelectionView(stage);

        // creates the lists
        boatList[0] = classicBoat;
        boatList[1] = resistanceBoat;
        boatList[2] = speedBoat;
        boatNameList[0] = "Classic Boat";
        boatNameList[1] = "Resistance Boat";
        boatNameList[2] = "Speed Boat";
    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        // temporary until we have asset manager in
        // loads a skin to organise all UI assets
        Skin skin = new Skin(Gdx.files.internal("skins/glassy-ui.json"));

        //create buttons
        TextButton changeLeft = new TextButton("<", skin);
        changeLeft.getLabel().setFontScale(textSize);
        changeLeft.setStyle(skin.get("small", TextButton.TextButtonStyle.class));

        TextButton changeRight = new TextButton(">", skin);
        changeRight.getLabel().setFontScale(textSize);
        changeRight.setStyle(skin.get("small", TextButton.TextButtonStyle.class));

        TextButton back = new TextButton("BACK", skin);
        back.getLabel().setFontScale(textSize);

        //create Boat Image
        boat = new Image(boatList[0]);

        //Create labels
        boatName = new Label(boatNameList[0], skin);
        boatName.setFontScale(textSize);
        boatName.setAlignment(Align.center);

        //add buttons to table
        table.add(boatName).size(BUTTON_WIDTH*2, BUTTON_HEIGHT).colspan(3);
        table.row().pad(0, 0, 10, 0);
        table.add(changeLeft).center().size((float) BUTTON_WIDTH /3, BUTTON_HEIGHT);
        table.add(boat).size(imageSize);
        table.add(changeRight).center().size((float) BUTTON_WIDTH /3, BUTTON_HEIGHT);
        table.row().pad(0, 0, 10, 0);
        table.add(back).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(3);


        // create button listeners
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new MenuController(game));
            }
        });

        changeLeft.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO
                i=i-1;
                if(i <= -1) {
                    i = 2;
                }
                changeTable();
            }
        });

        changeRight.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO
                i=(i+1)%3;
                changeTable();
            }
        });

    }

    private void changeTable() {
        boatName.setText(boatNameList[i]);
        boat.setDrawable(new SpriteDrawable(new Sprite(boatList[i])));
    }

    @Override
    public void render(float delta) {
        view.update();

    }

    /**
     * Resizes all game instances to the resolution selected
     * @param width screen width
     * @param height screen height
     */
    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
        Config.setHeight(height);
        Config.setWidth(width);
        Gdx.graphics.setWindowedMode(Config.getWidth(), Config.getHeight());
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
     * Function to remove the current screen
     */
    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
    }

}