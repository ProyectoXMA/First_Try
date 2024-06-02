package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.PauseController;
import com.mygdx.game.util.Config;

public class PauseViewScreen implements Screen{
    // attributes for the screen
    private Stage stage;
    private Viewport viewport;
    OrthographicCamera camera;
    //attributes for the screen
    private final MyGdxGame game;
    private Texture backgroundImage;
    private PauseController pause;
    public PauseViewScreen(final MyGdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Config.getWidth(),Config.getHeight());
        backgroundImage = new Texture(Gdx.files.internal("menu_background.jpeg"));
        pause = new PauseController(game,this);
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(Config.getWidth(), Config.getHeight());
        stage = new Stage(viewport);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();
        game.batch.begin();
        game.batch.draw(backgroundImage,-10,0,Config.getWidth()+10,Config.getHeight());
        pause.show();
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(Config.getWidth(), Config.getHeight(), true);
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
