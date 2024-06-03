package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.Leg;
import com.mygdx.game.util.Config;
import com.mygdx.game.view.LegView;

public class LegController implements Screen {
    final MyGdxGame game;
    GeneralController generalController;
    LegView view;
    Leg leg;
    InputManager inputManager;
    private Boat playerBoat;
    private boolean pause = false;
    private Music raceMusic = Gdx.audio.newMusic(Gdx.files.internal("raceSound.mp3"));
    private int LEVEL = 1;

    public LegController(final MyGdxGame game){
        Gdx.app.log("Input","LegScreen created");
        this.game = game;
        this.generalController = GeneralController.getInstance(game);
    }
    @Override
    public void show() {
        if(!pause) {
            this.inputManager = new InputManager();
            this.inputManager.addSubscriber(game.getPlayer());
            this.leg = new Leg(LEVEL, game.getPlayer());
            this.view = new LegView(game, leg);
        }
        pause = false;
        // Start the playback of the background music when the screen is shown
        if(!Config.muted) raceMusic.setVolume((float)0.1);
        else raceMusic.setVolume(0);
        raceMusic.setLooping(true);
        raceMusic.play();

        Gdx.input.setInputProcessor(this.inputManager);
        playerBoat = game.getPlayer().getBoat();
    }
    @Override
    public void render(float delta) {//This render is updating the model, by means of update, and the view.
        if(LEVEL > 3) {
            generalController.showMainMenu();
        }
        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            raceMusic.stop();
            raceMusic.dispose();
            pause = true;
            generalController.showPauseScreen();
        }
        if(game.getPlayer().getBoat().isDead()) {
            raceMusic.stop();
            raceMusic.dispose();
            if (game.getPlayer().getBoat().hasReturnToLife())
                generalController.showLoseScreen();
            else {
                pause = true;
                playerBoat.resetCharacteristics();
                generalController.showMinigameTutorial();
            }
        } else if (leg.hasReachedGoal()) {
            LEVEL++;
            raceMusic.stop();
            raceMusic.dispose();
            generalController.showWinningScreen();
        }
        leg.update(delta);
        view.render(delta);
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
        view.dispose();
    }


}
