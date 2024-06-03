package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.view.LoseScreen;

public class GeneralController {
    public static GeneralController instance;
    private MyGdxGame game;
    private LegController legController;
    private MenuController menuController;
    private MinigameController minigameController;
    private PauseController pauseController;
    private SettingsController settingsController;
    private LoseScreen loseScreen;

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
        game.setScreen(new MenuController(game));
    }
    public void showLegScreen(){
        game.setScreen(legController);
    }
    public void showMinigameScreen(){
        game.setScreen(minigameController);
    }
    public void showPauseScreen(){
        game.getScreen().pause();
        game.setScreen(pauseController);
    }
    public void showSettingsScreen(){
        game.setScreen(settingsController);
    }
    public void showLoseScreen(){
        game.setScreen(loseScreen);
    }
    public void quitGame(){
        game.dispose();
        Gdx.app.exit();
    }
    public void dispose(){
        legController.dispose();
        menuController.dispose();
        minigameController.dispose();
        pauseController.dispose();
        settingsController.dispose();
    }

    public void reset(){
        dispose();
        instance = null;
    }
}
