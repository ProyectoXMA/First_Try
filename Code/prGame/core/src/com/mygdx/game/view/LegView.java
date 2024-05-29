package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.Leg;
import com.mygdx.game.model.obstacles.Duck;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.obstacles.Stone;

import java.util.Set;

public class LegView {
    final MyGdxGame game;
    private Leg leg;
    OrthographicCamera camera;
    private static final Texture boatImage = new Texture("boats/resistanceBoat.png");
    private static final Texture duckImage = new Texture("obstacles/duck.png");
    private static final Texture logImage = new Texture("obstacles/logHorizontal.png");
    private static final Texture stoneImage = new Texture("obstacles/stone.png");
    private static final Texture backgroundImage = new Texture("dragon.jpeg");
    private static final Texture laneLimitImage = stoneImage;

    public LegView(final MyGdxGame game, Leg leg) {
        this.game = game;
        this.leg = leg;
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
        game.font.draw(game.batch, "Some text here " + dropsGathered, 0, 480);
        game.batch.draw(boatImage, bucket.x, bucket.y, bucket.width, bucket.height);
        for (Rectangle raindrop : raindrops) {
            game.batch.draw(duckImage, raindrop.x, raindrop.y);
        }
        game.batch.end();
    }
    public void render() {
        // Clear the screen with a dark blue color
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Tell the camera to update its matrices
        camera.update();

        // Tell the SpriteBatch to render in the coordinate system specified by the camera
        game.batch.setProjectionMatrix(camera.combined);

        // Begin a new batch and draw the bucket and all drops
        game.batch.begin();
        drawBackground();
        drawLeg();
        drawUI();
        game.batch.end();
    }

    private void drawBackground() {
        game.batch.draw(backgroundImage, 0, 0);
    }
    private void drawLeg() {
        for(Lane lane : leg.getLanes()) {
            drawLane(lane);
        }
    }
    private void drawLane(Lane lane) {
        drawLimits(lane.getLanePosition(), lane.getWidth());
        drawObstacles(lane.getObstacles());
        drawBoat(lane.getBoats());
    }
    private void drawLimits(float lanePosition, float laneWidth) {
        //TODO: Revise this and the image Limits
        game.batch.draw(laneLimitImage, lanePosition, 0);
        game.batch.draw(laneLimitImage, lanePosition + laneWidth, 0);
    }
    private void drawObstacles(Set<Obstacle> obstacles) {
        //TODO: Revise this, switch should be better. But how?
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Duck) {
                game.batch.draw(duckImage, obstacle.getX(), obstacle.getY());
            } else if (obstacle instanceof Log) {
                game.batch.draw(logImage, obstacle.getX(), obstacle.getY());
            } else if (obstacle instanceof Stone) {
                game.batch.draw(stoneImage, obstacle.getX(), obstacle.getY());
            }
        }
    }
    private void drawBoat(Set<Boat> boats) {
        for (Boat boat : boats) {
            switch (boat.getType()) {
                //TODO: Change the boatImage to the actual types images
                case FAST:
                    game.batch.draw(boatImage, boat.getX(), boat.getY());
                case STRONG:
                    game.batch.draw(boatImage, boat.getX(), boat.getY());
                case CLASSIC:
                    game.batch.draw(boatImage, boat.getX(), boat.getY());
                default:
                    throw new IllegalArgumentException("Not a valid boat type");
            }
        }
    }
    private void drawUI() {
        drawTimer();
        drawHealth();
        drawScore();
        //...
    }
    private void drawTimer() {
        //TODO
    }
    private void drawHealth() {
        //TODO
    }
    private void drawScore() {
        //TODO
    }
    public void dispose() {
        boatImage.dispose();
        duckImage.dispose();
        logImage.dispose();
        stoneImage.dispose();
        backgroundImage.dispose();
        laneLimitImage.dispose();
    }
}