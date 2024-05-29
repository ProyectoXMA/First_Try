package com.mygdx.game.controller;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.Leg;
import com.mygdx.game.view.LegView;

public class LegScreen implements Screen, InputProcessor {
    final MyGdxGame game;
    LegView view;

    Sound dropSound;
    Music rainMusic;
    Rectangle bucket;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;

    public LegScreen(final MyGdxGame game){
        Gdx.app.log("Input","LegScreen created");
        this.game = game;
        this.view = new LegView(game, new Leg(1));

        // Load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);

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

    public void update(float delta) {
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
                dropSound.play();
                iter.remove();
            }
        }
    }
    @Override
    public void render(float delta) {
        update(delta);
        view.render(bucket, raindrops, dropsGathered);
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

    public void show() {
        // Start the playback of the background music when the screen is shown
        Gdx.input.setInputProcessor(this);
        rainMusic.play();
    }

    public void dispose() {
        view.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("Input","Key pressed");
        if (keycode == Keys.LEFT) {
            // Handle left key press
            // Update model and view accordingly
            Gdx.app.log("Input","Left key pressed");
        } else if (keycode == Keys.RIGHT) {
            // Handle right key press
            Gdx.app.log("Input","Right key pressed");
        }
        // handle other keys
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.LEFT) {
            // Handle left key release
            Gdx.app.log("Input","Left key released");
        } else if (keycode == Keys.RIGHT) {
            // Handle right key release
            Gdx.app.log("Input","Right key released");
        }
        // handle other keys
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
