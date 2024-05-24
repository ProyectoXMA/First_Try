package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

public class LegView {
    final MyGdxGame game;
    OrthographicCamera camera;
    Texture bucketImage;
    Texture dropImage;

    public LegView(final MyGdxGame game) {
        this.game = game;

        // Load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = new Texture("droplet.png");
        bucketImage = new Texture("bucket.png");

        // Create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }
    public Camera getCamera() {
        return camera;
    }

    public void render(Rectangle bucket, Array<Rectangle> raindrops, int dropsGathered) {
        // Clear the screen with a dark blue color
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Tell the camera to update its matrices
        camera.update();

        // Tell the SpriteBatch to render in the coordinate system specified by the camera
        game.batch.setProjectionMatrix(camera.combined);

        // Begin a new batch and draw the bucket and all drops
        game.batch.begin();
        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
        game.batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);
        for (Rectangle raindrop : raindrops) {
            game.batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        game.batch.end();
    }

    public void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
    }
}