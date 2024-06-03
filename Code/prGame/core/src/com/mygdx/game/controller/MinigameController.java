package com.mygdx.game.controller;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.minigameLogic.MinigameLogic;
import com.mygdx.game.view.LoseScreen;
import com.mygdx.game.view.MinigameView;

public class MinigameController implements Screen {
    
    private final MyGdxGame game;
    private final MinigameLogic minigameLogic;
    private final MinigameView minigameView;
    private GeneralController generalController;
    public MinigameController(MyGdxGame game) {
        this.game = game;
        this.minigameLogic = new MinigameLogic();
        this.minigameView = new MinigameView(game, minigameLogic);
        this.generalController = GeneralController.getInstance(game);
    }

    private void checkScreenTransition() {
        minigameLogic.run();
        int res = minigameLogic.checkGameState();
        if(res == 0) return;
        this.minigameView.dispose();
        if (res == 1) generalController.showLegScreen(); //Player has won the minigame and returns to life
        else generalController.showLoseScreen();
    }

    @Override
    public void show() {
        minigameLogic.generateAdapter();
        minigameView.show();
    }

    @Override
    public void render(float delta) {
        checkScreenTransition();
        minigameView.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

    }
}
