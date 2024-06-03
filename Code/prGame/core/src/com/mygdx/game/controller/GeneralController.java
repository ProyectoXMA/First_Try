package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.view.LoseScreen;
import com.mygdx.game.view.minigameTutorial;

public class GeneralController {
    public static GeneralController instance;
    private MyGdxGame game;
    private LegController legController;
    private MenuController menuController;
    private MinigameController minigameController;
    private PauseController pauseController;
    private SettingsController settingsController;
    private LoseScreen loseScreen;
    private minigameTutorial mTutorial;
    private BoatSelectionController boatSelectionController;
    private Screen previousScreen;

    private GeneralController(MyGdxGame game){
        this.game = game;
    }
    private void initialize(MyGdxGame game) {
        legController = new LegController(game);
        menuController = new MenuController(game);
        minigameController = new MinigameController(game);
        pauseController = new PauseController(game);
        settingsController = new SettingsController(game);
        loseScreen = new LoseScreen(game);
        mTutorial = new minigameTutorial(game);
        boatSelectionController = new BoatSelectionController(game);
    }
    /**
     * Singleton pattern to ensure there is only one instance of the GeneralController that is accessible for all classes.
     * @param game The game instance
     * @return The instance of the GeneralController
     */
    public static GeneralController getInstance(MyGdxGame game){
        if(instance == null){
            instance = new GeneralController(game);
            instance.initialize(game);
        }
        return instance;
    }
    public void showMainMenu(){
        game.setScreen(menuController);
    }
    public void showLegScreen(){
        game.setScreen(legController);
    }
    public void resetLeg(){
        legController = new LegController(game);
    }
    public void showMinigameScreen(){
        game.setScreen(minigameController);
    }
    public void showBoatSelectionScreen(){
        game.setScreen(boatSelectionController);
    }
    public void showPauseScreen(){
        game.setScreen(pauseController);
    }
    public void showSettingsScreen(){
        game.setScreen(settingsController);
    }
    public void showLoseScreen(){
        game.setScreen(loseScreen);
    }
    public void quitGame(){
        Gdx.app.exit();
    }

    public void reset(){
        instance = null;
    }

    public void showMinigameTutorial(){
        game.setScreen(mTutorial);
    }
}
