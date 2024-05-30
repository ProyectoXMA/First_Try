package com.mygdx.game.view;

import java.security.Key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class LoseScreen implements Screen {
    // attributes for the screen
    private Stage stage;
    private Viewport viewport;
    private Music losingMusic;
    OrthographicCamera camera;
// attributes for the screen
    private final MyGdxGame game;
    private Texture backgroundImage;
    public LoseScreen(final MyGdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
        backgroundImage = new Texture(Gdx.files.internal("LosingScreen.jpg"));
        losingMusic = Gdx.audio.newMusic(Gdx.files.internal("LosingSound.mp3"));
        losingMusic.setLooping(true);

    }

    @Override
    public void show() {
        viewport = new ExtendViewport(800, 480);
        stage = new Stage(viewport);
        losingMusic.play();
        losingMusic.setVolume((float)0.1);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            losingMusic.stop();
            game.setScreen(new MainMenuView(game));
        }
        game.batch.begin();
        game.batch.draw(backgroundImage,0,50,800,480);
        game.font.draw(game.batch, "Press ENTER to go to MAIN MENU", 300,30);
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }
}
