package com.mygdx.game.controller;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.Leg;
import com.mygdx.game.util.Config;
import com.mygdx.game.view.LegView;
import com.mygdx.game.view.PauseViewScreen;
import com.mygdx.game.view.minigameTutorial;

public class LegController implements Screen {
    final MyGdxGame game;
    LegView view;
    Leg leg;
    InputManager inputManager;

    Sound dropSound;
    Music rainMusic;
    Rectangle bucket;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
    private Music raceMusic;
    public LegController(final MyGdxGame game){
        Gdx.app.log("Input","LegScreen created");
        this.game = game;
        this.leg = new Leg(1, game.getPlayer());
        this.view = new LegView(game, leg);
        this.inputManager = new InputManager();
        this.inputManager.addSubscriber(game.getPlayer());

        // Load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        raceMusic = Gdx.audio.newMusic(Gdx.files.internal("raceSound.mp3"));
        if(!Config.muted) raceMusic.setVolume((float)0.1);
        else raceMusic.setVolume(0);
        raceMusic.setLooping(true);
        raceMusic.play();

        // Create a Rectangle to logically represent the bucket
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2; // Center the bucket horizontally
        bucket.y = 20; // Bottom left corner of the bucket is 20 pixels above
        bucket.width = 64;
        bucket.height = 64;

        // Create the raindrops array and spawn the first raindrop
        raindrops = new Array<Rectangle>();
        spawnRaindrop();
    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }


    private void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            bucket.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
            Gdx.app.exit();

        // Make sure the bucket stays within the screen bounds
        if (bucket.x < 0)
            bucket.x = 0;
        if (bucket.x > 800 - 64)
            bucket.x = 800 - 64;

        // Check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();

        // Move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the latter case we increase the
        // value of our drops counter and add a sound effect.
        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0)
                iter.remove();
            if (raindrop.overlaps(bucket)) {
                dropsGathered++;
                iter.remove();
            }
        }
    }

    @Override
    public void render(float delta) {//This render is updating the model, by means of update, and the view.
        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            raceMusic.stop();
            raceMusic.dispose();
            game.setScreen(new PauseViewScreen(game));
        }
        //update(delta);
        //view.render(bucket, raindrops, dropsGathered);
        if(leg.getLanes().get(1).getBoat().dead()){
            raceMusic.stop();
            raceMusic.dispose();
            leg.getLanes().get(1).getBoat().setHealth(leg.getLanes().get(1).getBoat().getBaseHealth()/2);
            game.setScreen(new minigameTutorial(game));
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
    public void show() {
        // Start the playback of the background music when the screen is shown
        Gdx.input.setInputProcessor(this.inputManager);
    }
    @Override
    public void dispose() {
        view.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }
}
