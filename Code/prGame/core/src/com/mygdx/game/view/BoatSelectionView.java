package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.util.Config;

public class BoatSelectionView {
    private final Stage stage;
    private final Texture MenuImage = new Texture(Gdx.files.internal("backgrounds/boat_selection_background.jpg"));
    public BoatSelectionView(Stage stage) {
        this.stage = stage;
    }

    public void update() {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        // drawing the background image
        stage.getBatch().begin();
        stage.getBatch().draw(MenuImage, 0, 0, Config.getWidth(), Config.getHeight());
        stage.getBatch().end();

        //drawing the stage
        stage.draw();
    }
}
