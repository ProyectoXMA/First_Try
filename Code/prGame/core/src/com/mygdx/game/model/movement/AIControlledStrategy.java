package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.Leg;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.util.Config;

public class AIControlledStrategy implements MovementStrategy {
    private static final float DEFAULT_EVASION = 0.99f; // Default evasion chance
    private static final float REACTION_DISTANCE = 50.0f; // Distance at which the AI starts to react to obstacles, boats, and power-ups
    private static final float SPEED_FACTOR = 1.5f;
    private static final float leftLIMIT = Leg.BORDER_WIDTH;
    private static final float rightLIMIT = Config.getWidth() - Leg.BORDER_WIDTH;
    private Lane visibleLane;
    private float evasionChance; // Chance of evading an obstacle or boat

    public AIControlledStrategy(Lane currentLane, float evasionChance) {
        this.visibleLane = currentLane;
        this.evasionChance = evasionChance; // Must be between 0 and 1
    }

    public AIControlledStrategy(Lane currentLane) {
        this.visibleLane = currentLane;
        this.evasionChance = DEFAULT_EVASION; // Must be between 0 and 1
    }

    @Override
    public void move(Movable movable, float delta) {
        float laneLeftLimit = visibleLane.getLanePosition();
        float laneRightLimit = laneLeftLimit + Lane.WIDTH;
        float movableLeftLimit = movable.getHitbox().x;
        float movableRightLimit = movable.getHitbox().x + movable.getHitbox().width;
        int speed = movable.getSpeed();

        // Fixed vertical movement
        movable.adjustY(speed * delta);

        // Initial horizontal movement
        float dx = 0;

        // Check and adjust movement to avoid obstacles and collect power-ups
        dx = adjustForObstacles(movable, dx, delta, laneLeftLimit, laneRightLimit);
        dx = adjustForPowerUps(movable, dx, delta, laneLeftLimit, laneRightLimit);

        // Apply the horizontal movement while ensuring it stays within the lane limits
        if (movableLeftLimit + dx < laneLeftLimit) {
            dx = laneLeftLimit - movableLeftLimit;
        } else if (movableRightLimit + dx > laneRightLimit) {
            dx = laneRightLimit - movableRightLimit;
        }

        movable.adjustX(dx);
    }

    private float adjustForObstacles(Movable movable, float dx, float delta, float laneLeftLimit, float laneRightLimit) {
        for (Obstacle obstacle : visibleLane.getObstacles()) {
            if (isNear(movable.getHitbox(), obstacle.getHitbox(), REACTION_DISTANCE)) {
                if (MathUtils.random() < evasionChance) {
                    if (movable.getHitbox().x < obstacle.getHitbox().x) {
                        dx -= movable.getSpeed() * delta * SPEED_FACTOR; // Move left to evade
                    } else {
                        dx += movable.getSpeed() * delta * SPEED_FACTOR; // Move right to evade
                    }
                }
            }
        }
        return ensureWithinLaneLimits(movable, dx, laneLeftLimit, laneRightLimit);
    }

    private float adjustForPowerUps(Movable movable, float dx, float delta, float laneLeftLimit, float laneRightLimit) {
        for (PowerUp powerUp : visibleLane.getPowerUps()) {
            if (isNear(movable.getHitbox(), powerUp.getHitbox(), REACTION_DISTANCE)) {
                if (movable.getHitbox().x < powerUp.getHitbox().x) {
                    dx += movable.getSpeed() * delta * SPEED_FACTOR; // Move right to collect
                } else {
                    dx -= movable.getSpeed() * delta * SPEED_FACTOR; // Move left to collect
                }
            }
        }
        return ensureWithinLaneLimits(movable, dx, laneLeftLimit, laneRightLimit);
    }

    private float ensureWithinLaneLimits(Movable movable, float dx, float laneLeftLimit, float laneRightLimit) {
        float newLeftLimit = movable.getHitbox().x + dx;
        float newRightLimit = movable.getHitbox().x + movable.getHitbox().width + dx;

        if (newLeftLimit < laneLeftLimit) {
            dx = laneLeftLimit - movable.getHitbox().x;
        } else if (newRightLimit > laneRightLimit) {
            dx = laneRightLimit - (movable.getHitbox().x + movable.getHitbox().width);
        }

        return dx;
    }

    private boolean isNear(Rectangle rect1, Rectangle rect2, float distance) {
        return rect1.overlaps(rect2) || rect1.contains(rect2) || rect2.contains(rect1) ||
                rect1.x <= rect2.x + rect2.width + distance && rect1.x + rect1.width >= rect2.x - distance &&
                rect1.y <= rect2.y + rect2.height + distance && rect1.y + rect1.height >= rect2.y - distance;
    }
}