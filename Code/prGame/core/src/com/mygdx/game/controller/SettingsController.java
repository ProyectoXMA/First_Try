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
import com.sun.tools.javac.jvm.Gen;

public class SettingsController implements Screen{

    private final MyGdxGame game;
    private final SettingView view;
    private final Stage stage;
    private GeneralController generalController;
    private final int BUTTON_WIDTH = Config.getWidth()/3;
    private final int BUTTON_HEIGHT = Config.getHeight()/8;
    private final float textSize = (float) BUTTON_HEIGHT / 135;

    //booleans to check if user wants to change key binds
    private boolean awaitingLeftKeyChange = false;
    private boolean awaitingRightKeyChange = false;

    //labels of key binds
    Label moveLeftText;
    Label moveRightText;

    //Key binds class
    KeyBindings keyBinds = Config.keyBinds;

    public SettingsController(MyGdxGame game){
        this.game = game;
        generalController = GeneralController.getInstance(game);
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        // create a view for the settings
        view = new SettingView(stage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        // temporary until we have asset manager in
        // loads a skin to organise all UI assets
        Skin skin = new Skin(Gdx.files.internal("skins/glassy-ui.json"));

        //create buttons
        TextButton changeLeft = new TextButton("CHANGE", skin);
        changeLeft.getLabel().setFontScale(textSize);

        TextButton changeRight = new TextButton("CHANGE", skin);
        changeRight.getLabel().setFontScale(textSize);

        TextButton mute = new TextButton("MUTE", skin);
        changeRight.getLabel().setFontScale(textSize);

        TextButton back = new TextButton("BACK", skin);
        back.getLabel().setFontScale(textSize);

        //resolution selectBox
        String[] availableResolutions = new String[]{"RESOLUTION","1920x1080","1280x720","1024x768","800x600"};
        SelectBox<String> resolution = new SelectBox<>(skin);
        resolution.setItems(availableResolutions);
        resolution.getStyle().font.getData().setScale(textSize);
        resolution.setAlignment(Align.center);
        resolution.getStyle().listStyle.font.getData().scale(textSize);

        //Create labels
        moveLeftText = new Label(Input.Keys.toString(keyBinds.getKeyForAction(UserAction.MOVE_LEFT)) , skin);
        moveLeftText.setFontScale(textSize);
        moveLeftText.setAlignment(Align.center);

        moveRightText = new Label(Input.Keys.toString(keyBinds.getKeyForAction(UserAction.MOVE_RIGHT)) , skin);
        moveRightText.setFontScale(textSize);
        moveRightText.setAlignment(Align.center);

        Label moveLeftTitle = new Label("MOVE LEFT", skin);
        moveLeftTitle.setFontScale(textSize);
        moveLeftTitle.setAlignment(Align.center);

        Label moveRightTitle = new Label("MOVE RIGHT", skin);
        moveRightTitle.setFontScale(textSize);
        moveRightTitle.setAlignment(Align.center);

        //add buttons to table
        table.add(moveLeftTitle).size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);
        table.row().pad(0, 0, 10, 0);
        table.add(moveLeftText).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.add(changeLeft).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.row().pad(0, 0, 10, 0);
        table.add(moveRightTitle).size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);
        table.row().pad(0, 0, 10, 0);
        table.add(moveRightText).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.add(changeRight).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);
        table.row();
        table.add(mute).size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);
        table.row();
        table.add(resolution).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);
        table.row();
        table.add(back).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);


        // create button listeners
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                generalController.showMainMenu();
            }
        });

        changeLeft.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //Sets Left Movement to changing
                awaitingLeftKeyChange = true;
            }
        });

        changeRight.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //Sets Right Movement to changing
                awaitingRightKeyChange = true;
            }
        });

        mute.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO
                Config.muted = !Config.muted;
                System.out.println("muted: " + Config.muted);

                if(Config.muted) {
                    mute.setText("UNMUTE");
                } else {
                    mute.setText("MUTE");
                }
            }
        });

        //Checking which resolution was chosen in order to change it appropriately
        resolution.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String selectedResolution = resolution.getSelected();
                switch (selectedResolution) {
                    case "1920x1080":
                        resize(1920, 1080);
                        break;
                    case "1280x720":
                        resize(1280, 720);
                        break;
                    case "1024x768":
                        resize(1024, 768);
                        break;
                    case "800x600":
                        resize(800, 600);
                        break;
                    default:
                        //In case it fails
                        System.out.println("Unsupported resolution selected.");
                        break;
                }
                generalController.reset();
                generalController = GeneralController.getInstance(game);
                generalController.showSettingsScreen();
            }
        });

    }

    /**
     * changeControls consists in a loop to check which is the next key pressed by the user
     * depending on what key is awaiting to be changed
     * @param action what action is changing key
     */
    private void changeControls(UserAction action){
        for(int i = 0; i < Input.Keys.MAX_KEYCODE; i++){
            if (Gdx.input.isKeyJustPressed(i)) {
                if(awaitingLeftKeyChange) {
                    if(i != keyBinds.getKeyForAction(UserAction.MOVE_RIGHT)) {
                        keyBinds.setKeyBinding(action, i); //Sets new key as action
                        awaitingLeftKeyChange = false;
                    } else {
                        awaitingLeftKeyChange = true;
                    }
                }

                if(awaitingRightKeyChange) {
                    if(i != keyBinds.getKeyForAction(UserAction.MOVE_LEFT)) {
                        keyBinds.setKeyBinding(action, i); //Sets new key as action
                        awaitingRightKeyChange = false;
                    } else {
                        awaitingRightKeyChange = true;
                    }
                }

                //When the new assignment of the key is done stop showing message for changing it.
            } if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if(action == UserAction.MOVE_RIGHT) {
                    keyBinds.setKeyBinding(action, keyBinds.getKeyForAction(UserAction.MOVE_RIGHT));
                } else {
                    keyBinds.setKeyBinding(action, keyBinds.getKeyForAction(UserAction.MOVE_LEFT));
                }

                awaitingLeftKeyChange = false;
                awaitingRightKeyChange = false;
            }

        }

    }

    @Override
    public void render(float delta) {
        view.update();
        //Check if controls have to be changed, if so put ... as label text
        if(awaitingLeftKeyChange) {
            changeControls(UserAction.MOVE_LEFT);
            moveLeftText.setText("...");
        } else if(awaitingRightKeyChange) {
            changeControls(UserAction.MOVE_RIGHT);
            moveRightText.setText("...");
        } else {
            moveLeftText.setText(Input.Keys.toString(keyBinds.getKeyForAction(UserAction.MOVE_LEFT)));
            moveRightText.setText(Input.Keys.toString(keyBinds.getKeyForAction(UserAction.MOVE_RIGHT)));
        }
    }

    /**
     * Resizes all game instances to the resolution selected
     * @param width screen width
     * @param height screen height
     */
    @Override
    public void resize(int width, int height) {
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
        if(stage != null) stage.clear();
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