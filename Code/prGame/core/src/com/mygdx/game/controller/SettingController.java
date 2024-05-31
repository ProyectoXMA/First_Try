package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.util.Config;
import com.mygdx.game.util.KeyBindings;
import com.mygdx.game.util.UserAction;
import com.mygdx.game.view.SettingView;

public class SettingController implements Screen{

    private final MyGdxGame game;
    private final SettingView view;
    private final Stage stage;
    private final int BUTTON_WIDTH = Config.getWidth()/3;
    private final int BUTTON_HEIGHT = Config.getHeight()/8;
    private final float textSize = (float) BUTTON_HEIGHT / 135;
    private boolean awaitingKeyChange = false;

    //Keybinds class
    KeyBindings keyBinds;

    public SettingController(MyGdxGame game){
        this.game = game;
        keyBinds = new KeyBindings();

        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // create a view for the settings
        view = new SettingView(stage);
    }

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
        TextButton changeLeft = new TextButton("CHANGE", skin);
        changeLeft.getLabel().setFontScale(textSize);
        TextButton changeRight = new TextButton("CHANGE", skin);
        changeRight.getLabel().setFontScale(textSize);

        //resolution selectBox
        String[] availableResolutions = new String[]{"RESOLUTION","1920x1080","1280x720","1024x768","800x600"};
        SelectBox<String> resolution = new SelectBox<>(skin);
        resolution.setItems(availableResolutions);
        resolution.getStyle().font.getData().setScale(textSize);
        resolution.setAlignment(Align.center);
        resolution.getStyle().listStyle.font.getData().scale(textSize);

        TextButton back = new TextButton("BACK", skin);
        back.getLabel().setFontScale(textSize);

        //Create text
        Label moveLeftText = new Label(Input.Keys.toString(keyBinds.getKeyForAction(UserAction.MOVE_LEFT)) , skin);
        moveLeftText.setFontScale(textSize);
        moveLeftText.setAlignment(Align.center);
        Label moveRightText = new Label(Input.Keys.toString(keyBinds.getKeyForAction(UserAction.MOVE_RIGHT)) , skin);
        moveRightText.setFontScale(textSize);
        moveRightText.setAlignment(Align.center);
        //add buttons to table

        table.add(moveLeftText).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.add(changeLeft).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.row().pad(10, 0, 10, 5);
        table.add(moveRightText).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.add(changeRight).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.row();
        table.add(resolution).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);
        table.row();
        table.add(back).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);


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
                awaitingKeyChange = true;
                inputHandler(UserAction.MOVE_LEFT);
                moveLeftText.getText().clear();
                moveLeftText.getText().insert(0, keyBinds.getKeyForAction(UserAction.MOVE_LEFT));
            }
        });

        changeRight.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO
                awaitingKeyChange = true;
                inputHandler(UserAction.MOVE_RIGHT);
                moveRightText.getText().clear();
                moveRightText.getText().insert(0, keyBinds.getKeyForAction(UserAction.MOVE_RIGHT));
            }
        });

        resolution.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO
                String selectedResolution = resolution.getSelected();
                switch (selectedResolution) {
                    case "1920x1080":
                        resize(1920, 1080);
                        //Resets settings UI to avoid resizing
                        game.setScreen(new SettingController(game));
                        dispose();
                        break;
                    case "1280x720":
                        resize(1280, 720);
                        //Resets settings UI to avoid resizing
                        game.setScreen(new SettingController(game));
                        dispose();
                        break;
                    case "1024x768":
                        resize(1024, 768);
                        //Resets settings UI to avoid resizing
                        game.setScreen(new SettingController(game));
                        dispose();
                        break;
                    case "800x600":
                        resize(800, 600);
                        //Resets settings UI to avoid resizing
                        game.setScreen(new SettingController(game));
                        dispose();
                        break;
                    default:
                        System.out.println("Unsupported resolution selected.");
                        break;
                }
            }
        });

    }

    private void inputHandler(UserAction action){

        if(awaitingKeyChange) {
            for (int i = 0; i < Input.Keys.MAX_KEYCODE; i++) {
                if (Gdx.input.isKeyJustPressed(i)) {

                    keyBinds.setKeyBinding(action, i); //Sets new key as action
                    //When the new assignment of the key is done stop showing message for change it.
                    awaitingKeyChange = false;
                }
            }
        }

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

    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
    }

}