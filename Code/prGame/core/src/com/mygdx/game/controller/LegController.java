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
    private boolean first = true;
    private Music raceMusic = Gdx.audio.newMusic(Gdx.files.internal("raceSound.mp3"));

    public LegController(final MyGdxGame game){
        Gdx.app.log("Input","LegScreen created");
        this.game = game;
        this.leg = new Leg(1, game.getPlayer());
        this.view = new LegView(game, leg);
        this.generalController = GeneralController.getInstance(game);
    }
    @Override
    public void show() {
        if(first) {
            first = false;
            this.inputManager = new InputManager();
            this.inputManager.addSubscriber(game.getPlayer());
        }
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
        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            raceMusic.stop();
            raceMusic.dispose();
            generalController.showPauseScreen();
        }
        if(game.getPlayer().getBoat().isDead()) {
            raceMusic.stop();
            raceMusic.dispose();
            if (game.getPlayer().getBoat().hasReturnToLife())
                generalController.showLoseScreen();
            else {
                playerBoat.adjustHealth(playerBoat.getBaseHealth());
                generalController.showMinigameTutorial();
            }
        }
        leg.update(delta);
        view.render();
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
