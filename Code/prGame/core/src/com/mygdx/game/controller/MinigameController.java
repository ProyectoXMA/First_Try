package com.mygdx.game.controller;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.minigameLogic.MinigameLogic;
import com.mygdx.game.view.LoseScreen;
import com.mygdx.game.view.MinigameScreen;
import com.mygdx.game.view.RaceScreen;

public class MinigameController {
    private final MyGdxGame game;
    private final MinigameLogic minigameLogic;
    private final MinigameScreen MinigameScreen;
    public MinigameController(MyGdxGame game, MinigameLogic minigameLogic,MinigameScreen minigameScreen) {
        this.game = game;
        this.minigameLogic = minigameLogic;
        this.MinigameScreen = minigameScreen;
    }

    public void checkScreenTransition() {
        minigameLogic.run();
        int res = minigameLogic.checkGameState();
        if (res == 1) {
            this.MinigameScreen.dispose();
            game.setScreen(new RaceScreen(game));
        } else if (res == 3) {
            this.MinigameScreen.dispose();
            game.setScreen(new LoseScreen(game));
        }
    }
}
