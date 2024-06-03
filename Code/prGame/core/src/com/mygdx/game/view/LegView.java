package com.mygdx.game.view;

import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.Leg;
import com.mygdx.game.model.obstacles.Duck;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.obstacles.Stone;
import com.mygdx.game.model.powerUps.HealthBoost;
import com.mygdx.game.model.powerUps.Invincibility;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.model.powerUps.SpeedBoost;
import com.mygdx.game.util.Config;

public class LegView {
    final MyGdxGame game;
    private Leg leg;
    OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;



    private float timer;
    private static final Texture boatResistanceImage = new Texture("boats/resistanceBoat.png");
    private static final Texture boatSpeedImage = new Texture("boats/speedBoat.png");
    private static final Texture boatClassicImage = new Texture("boats/classicBoat.png");
    private static final Texture duckImage = new Texture("obstacles/duck.png");
    private static final Texture logImage = new Texture("obstacles/logHorizontal.png");
    private static final Texture stoneImage = new Texture("obstacles/stone.png");
    private static final Texture invincibilityImage = new Texture("powerUps/shield.png");
    private static final Texture healthBoostImage = new Texture("powerUps/health.png");
    private static final Texture speedBoostImage = new Texture("powerUps/speed.png");
    private static final Texture backgroundImage = new Texture("background3.png");
    private static final Texture laneLimitImage = stoneImage;
    private static final Texture timerIcon = new Texture("sandWatch.png");
    private static final Texture heartIcon = new Texture("heartHp.png");
    // The countdown timer

    public LegView(final MyGdxGame game, Leg leg) {
        this.game = game;
        this.leg = leg;
        this.font = new BitmapFont();
        this.font.setColor(Color.BLACK);
        this.timer = 0.0f; // Initialize the timer to 0 seconds
        batch = new SpriteBatch();
        font = new BitmapFont();
        // Create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.getWidth(),  Config.getHeight());


    }
    public Camera getCamera() {
        return camera;
    }
    public void render(float delta) {
        // Clear the screen with a dark blue color
        ScreenUtils.clear(0, 0, 0.2f, 1);
        // Tell the camera to update its matrices
        camera.update();
        // Tell the SpriteBatch to render in the coordinate system specified by the camera
        batch.setProjectionMatrix(camera.combined);
        // Begin a new batch and draw the bucket and all drops
        batch.begin();
        drawBackground();
        drawLeg();
        drawUI();
        drawHealth();
        drawTimer();
        timer += delta;
        batch.end();
    }
    private void drawBackground() {
        batch.draw(backgroundImage, 0, 0, Config.getWidth(), Config.LaneRelativeHeight);
    }
    private void drawLeg() {
        followPlayer();
        for(Lane lane : leg.getLanes()) {
            drawLane(lane);
        }
    }
    private void followPlayer() {
        float playerBoatHeight = leg.getLanes().get(Leg.PLAYER_LANE).getBoat().getY();
        float horizontalCenter = (float) Config.getWidth() / 2;
        float verticalCenter = (float) Config.getHeight() / 2;
        float verticalPosition = Math.min(
                Math.max(playerBoatHeight, verticalCenter),
                Lane.HEIGHT - verticalCenter
        );
        camera.position.set(horizontalCenter, verticalPosition, 0);
        Gdx.app.debug("LegView", "Camara position: " + camera.position.x + " " + camera.position.y);
    }
    private void drawLane(Lane lane) {
        drawObstacles(lane.getObstacles());
        drawPowerUps(lane.getPowerUps());
        drawBoat(lane.getBoats());
    }
    private void drawObstacles(Set<Obstacle> obstacles) {
        //TODO: Revise this, switch should be better. But how?
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Duck) {
                batch.draw(duckImage, obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
            } else if (obstacle instanceof Log) {
                batch.draw(logImage, obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
            } else if (obstacle instanceof Stone) {
                batch.draw(stoneImage, obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
            }
        }
    }
    private void drawPowerUps(Set<PowerUp> powerUps) {
        for(PowerUp powerUp : powerUps) {
            if(powerUp instanceof Invincibility) {
                batch.draw(invincibilityImage, powerUp.getX(), powerUp.getY(), powerUp.getWidth(), powerUp.getHeight());
            } else if (powerUp instanceof HealthBoost) {
                batch.draw(healthBoostImage, powerUp.getX(), powerUp.getY(), powerUp.getWidth(), powerUp.getHeight());
            } else if (powerUp instanceof SpeedBoost) {
                batch.draw(speedBoostImage, powerUp.getX(), powerUp.getY(), powerUp.getWidth(), powerUp.getHeight());
            }
        }
    }
    private void drawBoat(Set<Boat> boats) {
        for (Boat boat : boats) {
            switch (boat.getType()) {
                //TODO: Change the boatImage to the actual types images
                case FAST:
                    batch.draw(boatSpeedImage, boat.getX(), boat.getY(), boat.getWidth(), boat.getHeight());
                    break;
                case STRONG:
                    batch.draw(boatResistanceImage, boat.getX(), boat.getY(), boat.getWidth(), boat.getHeight());
                    break;
                case CLASSIC:
                    batch.draw(boatClassicImage, boat.getX(), boat.getY(), boat.getWidth(), boat.getHeight());
                    break;
                default:
                    throw new IllegalArgumentException("Not a valid boat type" + boat.getType());
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
        font.draw(batch, "" + (int)timer, (camera.position.x + ((float)Config.getWidth() / 2)) - 90, (camera.position.y + ((float)Config.getHeight() / 2)) - 10);
        batch.draw(timerIcon, camera.position.x + ((float)Config.getWidth() / 2) - 125, (camera.position.y + ((float)Config.getHeight() / 2)) - 115, Config.getWidth()/13,Config.getHeight()/11);
    }
    private void drawHealth() {
        int health = leg.getLanes().get(1).getBoat().getHealth();
        String s = Integer.toString(health);
        font.draw(batch,s,camera.position.x-Config.getWidth()/2 + 50,camera.position.y-Config.getHeight()/2+40);
        batch.draw(heartIcon,camera.position.x-Config.getWidth()/2+ 125,camera.position.y-Config.getWidth()/2+150,Config.getWidth()/15,Config.getHeight()/13);
    }
    private void drawScore() {
        //TODO
    }
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}