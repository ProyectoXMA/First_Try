package com.mygdx.game.controller;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.minigameLogic.MinigameLogic;
import com.mygdx.game.view.LoseScreen;
import com.mygdx.game.view.RaceScreen;

public class MinigameController {
    private final MyGdxGame game;
    private final MinigameLogic minigameLogic;

    public MinigameController(MyGdxGame game, MinigameLogic minigameLogic) {
        this.game = game;
        this.minigameLogic = minigameLogic;
    }

    public void checkScreenTransition() {
        minigameLogic.run();
        int res = minigameLogic.checkGameState();
        if (res == 1) {
            game.setScreen(new RaceScreen(game));
        } else if (res == 3) {
            game.setScreen(new LoseScreen(game));
        }
    }
}
