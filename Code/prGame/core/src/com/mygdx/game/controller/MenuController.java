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

public class MenuController implements Screen{

    private final MyGdxGame game;
    private final MenuView view;
    private final Stage stage;
    private final int BUTTON_WIDTH = Config.getWidth()/4;
    private final int BUTTON_HEIGHT = Config.getHeight()/8;
    private final float textSize = (float) BUTTON_HEIGHT / 135;

    //initialise skin
    Skin skin;

    //Booleans to see credits and tutorial

    //menu table
    Table menuTable;

    //credits table
    Table creditsTable;

    //tutorial table
    Table tutorialTable;

    //credits text
    String creditsText;

    public MenuController(MyGdxGame game){
        this.game = game;

        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // create a view for the settings
        view = new MenuView(stage);

        // skin loading
        skin = new Skin(Gdx.files.internal("skins/glassy-ui.json"));
        // text to be shown in credits
        creditsText = "Allitt Lopez Ricardo Juan\nBarrios Moreno Manuel\nBayon Pazos Angel\nEscano Lopez Angel Nicolas" +
                "\nHormigo Jimenez Pablo\nJorda Garay Francisco Javier\nSicre Cortizo Diego\nSultan Muhammad Abdullah\nTorres Gomez Juan";
    }

    /**
     * Creates a Table and all the Buttons necessary for the menu
     * It adds the listeners for the buttons
     */
    @Override
    public void show() {
        // Create a menu table that fills the screen. Everything menu related will go inside this table.
        menuTable = new Table();
        menuTable.setVisible(true);
        menuTable.setFillParent(true);
        menuTable.setDebug(false);
        stage.addActor(menuTable);

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

        // add buttons to table
        menuTable.row().pad(0,0,0,0);
        menuTable.add(play).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(Config.getHeight()*0.46f,0,0,0).align(Align.right);
        menuTable.add(tutorial).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(Config.getHeight()*0.46f,0,0,0).align(Align.left);
        menuTable.row().pad(0,0,20,0);
        menuTable.add(boats).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(0,0,0,0).align(Align.right);
        menuTable.add(settings).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(0,0,0,0).align(Align.left);
        menuTable.row().pad(0,0,5,0);
        menuTable.add(credits).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);
        menuTable.row().pad(0,0,5,0);
        menuTable.add(quit).center().size(BUTTON_WIDTH, BUTTON_HEIGHT).colspan(2);

        // credits Table creation
        creditsTable = new Table();
        creditsTable.setPosition(0, -Config.getHeight());
        creditsTable.setFillParent(true);
        creditsTable.setDebug(false);
        stage.addActor(creditsTable);

        //credits text
        Label creditsLabel = new Label(creditsText, skin);
        creditsLabel.setStyle(skin.get("creditsLabel", Label.LabelStyle.class));
        creditsLabel.setAlignment(Align.center);
        creditsLabel.setFontScale(textSize);

        // back button to exit credits
        TextButton backCredits = new TextButton("BACK", skin);
        backCredits.getLabel().setFontScale(textSize);

        // add button to credits table
        creditsTable.add(creditsLabel).center().size(BUTTON_WIDTH*3, BUTTON_HEIGHT*6);
        creditsTable.row();
        creditsTable.add(backCredits).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);

        // Tutorial Table creation
        tutorialTable = new Table();
        tutorialTable.setPosition(0, Config.getHeight());
        tutorialTable.setFillParent(true);
        tutorialTable.setDebug(false);
        stage.addActor(tutorialTable);

        //tutorial text
        Label tutorialLabel = new Label("This is the Tutorial", skin);
        tutorialLabel.setStyle(skin.get("creditsLabel", Label.LabelStyle.class));
        tutorialLabel.setAlignment(Align.center);
        tutorialLabel.setFontScale(textSize);

        // back button to exit tutorial
        TextButton backTutorial = new TextButton("BACK", skin);
        backTutorial.getLabel().setFontScale(textSize);

        // add button to tutorial table
        tutorialTable.add(tutorialLabel).center().size(BUTTON_WIDTH*3, BUTTON_HEIGHT*6);
        tutorialTable.row();
        tutorialTable.add(backTutorial).center().size(BUTTON_WIDTH, BUTTON_HEIGHT);


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
                tutorial();
            }
        });

        boats.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new BoatSelectionController(game));
            }
        });

        credits.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                credits();
            }
        });

        backTutorial.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    exitTutorial();
            }
        });

        backCredits.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    exitCredits();
            }
        });

    }

    private void tutorial() {
        System.out.println("Exit Tutorial");
        //make menu not interactive
        menuTable.setTouchable(Touchable.disabled);
        //start animations
        tutorialTable.addAction(Actions.moveTo(0, 0, 1f, Interpolation.bounceOut));
        menuTable.addAction(Actions.moveTo(0, -Config.getHeight(), 1f, Interpolation.bounceOut));
    }

    /**
     *
     */
    private void credits() {
        System.out.println("Enter Credits");
        // Activates flag to start showing credits loop
        //make menu not interactive
        menuTable.setTouchable(Touchable.disabled);
        //start animations
        creditsTable.addAction(Actions.moveTo(0, 0, 1f, Interpolation.bounceOut));
        menuTable.addAction(Actions.moveTo(0, Config.getHeight(), 1f, Interpolation.bounceOut));
    }

    /**
     *
     */
    private  void exitCredits() {
        System.out.println("Exit Credits");
        //stop showing credits

        //make menu interactive again
        menuTable.setTouchable(Touchable.enabled);
        //start animations
        menuTable.addAction(Actions.moveTo(0, 0, 1f, Interpolation.bounceOut));
        creditsTable.addAction(Actions.moveTo(0, -Config.getHeight(), 1f, Interpolation.bounceOut));
    }

    /**
     *
     */
    private  void exitTutorial() {
        System.out.println("Exit Tutorial");
        //stop showing credits

        //make menu interactive again
        menuTable.setTouchable(Touchable.enabled);
        //start animations
        menuTable.addAction(Actions.moveTo(0, 0, 1f, Interpolation.bounceOut));
        tutorialTable.addAction(Actions.moveTo(0, Config.getHeight(), 1f, Interpolation.bounceOut));
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