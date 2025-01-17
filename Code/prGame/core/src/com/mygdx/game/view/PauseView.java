package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.PauseController;
import com.mygdx.game.util.Config;

public class PauseView {
    // attributes for the screen
    private Stage stage;
    private Viewport viewport;
    OrthographicCamera camera;
    //attributes for the screen
    private final MyGdxGame game;
    private SpriteBatch batch;
    private Texture backgroundImage;
    private PauseController pauseController;
    public PauseView(final MyGdxGame game, PauseController pauseController){
        this.game = game;
        this.pauseController = pauseController;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Config.getWidth(),Config.getHeight());
        backgroundImage = new Texture(Gdx.files.internal("menu_background.jpeg"));
    }

    public void show() {
        viewport = new ExtendViewport(Config.getWidth(), Config.getHeight());
        stage = new Stage(viewport);
        batch = new SpriteBatch();
    }

    public void update() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();
        batch.begin();
        batch.draw(backgroundImage,-10,0,Config.getWidth()+10,Config.getHeight());
        batch.end();
        pauseController.show();
    }
    public void dispose() {
        if (stage != null) stage.dispose();
        if (batch != null) batch.dispose();
    }
}
