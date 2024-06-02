package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.util.Config;

public class AIControlledStrategy implements MovementStrategy {
    private static final float DEFAULT_EVASION = 0.5f; // Default evasion chance
    private static final float REACTION_DISTANCE = 200.0f; // Distance at which the AI starts to react to obstacles, boats, and power-ups
    private static final float COLLISION_DISTANCE = 50.0f; // Minimum distance to avoid collision
    private static final int leftLIMIT = 10;
    private static final int rightLIMIT = Config.getWidth() - 10;
    private Lane visibleLane;
    private float evasionChance; // Chance of evading an obstacle or boat
    private float angle; // Movement angle in radians

    public AIControlledStrategy(Lane currentLane, float evasionChance) {
        this.visibleLane = currentLane;
        this.evasionChance = evasionChance; // Must be between 0 and 1
        this.angle = MathUtils.random(0.0f, 2.0f * (float) Math.PI); // Random initial movement angle
    }

    public AIControlledStrategy(Lane currentLane) {
        this.visibleLane = currentLane;
        this.evasionChance = DEFAULT_EVASION; // Must be between 0 and 1
        this.angle = MathUtils.random(0.0f, 2.0f * (float) Math.PI); // Random initial movement angle
    }

    @Override
    public void move(Movable movable, float delta) {
        float movableLeftLimit = movable.getHitbox().x;
        float movableRightLimit = movable.getHitbox().x + movable.getHitbox().width;
        int speed = movable.getSpeed();

        // Fixed vertical movement
        movable.adjustY(speed * delta);

        // Initial horizontal movement
        float dx = MathUtils.cos(angle) * speed * delta;

        // Check and adjust movement to avoid obstacles, boats, and power-ups
        dx = adjustForObstacles(movable, dx, delta);
        dx = adjustForBoats(movable, dx, delta);
        dx = adjustForPowerUps(movable, dx, delta);

        // Apply the horizontal movement
        movable.adjustX(dx);

        // Check if the boat is within the lane limits and adjust direction if necessary
        if (movableLeftLimit + dx <= leftLIMIT) {
            angle = MathUtils.PI - angle; // Reflect angle horizontally
        } else if (movableRightLimit + dx >= rightLIMIT) {
            angle = MathUtils.PI - angle; // Reflect angle horizontally
        }
    }

    private float adjustForObstacles(Movable movable, float dx, float delta) {
        for (Obstacle obstacle : visibleLane.getObstacles()) {
            if (isNear(movable.getHitbox(), obstacle.getHitbox(), REACTION_DISTANCE)) {
                if (MathUtils.random() < evasionChance) {
                    angle += MathUtils.random(-(float) Math.PI / 4, (float) Math.PI / 4); // Slightly change the angle
                    dx = MathUtils.cos(angle) * movable.getSpeed() * delta; // Update dx based on new angle
                }
            }
        }
        return dx;
    }

    private float adjustForBoats(Movable movable, float dx, float delta) {
        for (Boat otherBoat : visibleLane.getBoats()) {
            if (!otherBoat.equals(movable) && isNear(movable.getHitbox(), otherBoat.getHitbox(), REACTION_DISTANCE)) {
                if (MathUtils.random() < evasionChance) {
                    angle += MathUtils.random(-(float) Math.PI / 4, (float) Math.PI / 4); // Slightly change the angle
                    dx = MathUtils.cos(angle) * movable.getSpeed() * delta; // Update dx based on new angle
                }
            }
        }
        return dx;
    }

    private float adjustForPowerUps(Movable movable, float dx, float delta) {
        for (PowerUp powerUp : visibleLane.getPowerUps()) {
            if (isNear(movable.getHitbox(), powerUp.getHitbox(), REACTION_DISTANCE)) {
                // Move towards the power-up
                if (movable.getHitbox().x < powerUp.getHitbox().x) {
                    angle -= MathUtils.random(0, (float) Math.PI / 4); // Slightly change the angle towards the power-up
                } else {
                    angle += MathUtils.random(0, (float) Math.PI / 4); // Slightly change the angle towards the power-up
                }
                dx = MathUtils.cos(angle) * movable.getSpeed() * delta; // Update dx based on new angle
            }
        }
        return dx;
    }

    private boolean isNear(Rectangle rect1, Rectangle rect2, float distance) {
        return rect1.overlaps(rect2) || rect1.contains(rect2) || rect2.contains(rect1) ||
                rect1.x <= rect2.x + rect2.width + distance && rect1.x + rect1.width >= rect2.x - distance &&
                        rect1.y <= rect2.y + rect2.height + distance && rect1.y + rect1.height >= rect2.y - distance;
    }
}