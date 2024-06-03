package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.MinigameController;
import com.mygdx.game.util.Config;

public class minigameTutorial2 implements Screen{
     // attributes for the screen
    private Stage stage;
    private Viewport viewport;
    OrthographicCamera camera;
// attributes for the screen
    private final MyGdxGame game;
    private Texture backgroundImage;
    public minigameTutorial2(final MyGdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Config.getWidth(),Config.getHeight());
        backgroundImage = new Texture(Gdx.files.internal("secondPhase.png"));
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            game.setScreen(new MinigameController(game));
        }
        game.batch.begin();
        game.batch.draw(backgroundImage,0,0,Config.getWidth(),Config.getHeight());
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

