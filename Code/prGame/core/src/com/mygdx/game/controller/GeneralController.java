package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.view.LoseScreen;
import com.mygdx.game.view.WinningScreen;
import com.mygdx.game.view.minigameTutorial;
import com.mygdx.game.view.minigameTutorial2;

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
    private minigameTutorial2 mTutorial2;
    private BoatSelectionController boatSelectionController;
    private WinningScreen winScreen;

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
        mTutorial2 = new minigameTutorial2(game);
        boatSelectionController = new BoatSelectionController(game);
        winScreen = new WinningScreen(game);
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
        resetLeg();
        game.setScreen(menuController);
    }
    public void showLegScreen(){
        game.setScreen(legController);
    }
    public void resetLeg(){
        legController = new LegController(game);
        minigameController = new MinigameController(game);
        mTutorial = new minigameTutorial(game);
        mTutorial2 = new minigameTutorial2(game);
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
    public void showMiniGameTutorial2(){
        game.setScreen(mTutorial2);
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
    public void showWinningScreen(){
        game.setScreen(winScreen);
    }
}
