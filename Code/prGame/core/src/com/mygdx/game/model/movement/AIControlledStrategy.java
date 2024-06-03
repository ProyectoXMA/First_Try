package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.Leg;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.util.Config;

public class AIControlledStrategy implements MovementStrategy {
    private static final float BASE_EVASION = 0.4f; // Default evasion chance
    private static final float REACTION_DISTANCE = 50.0f; // Distance at which the AI starts to react to obstacles, boats, and power-ups
    private static final float leftLIMIT = Leg.BORDER_WIDTH;
    private static final float rightLIMIT = Config.getWidth() - Leg.BORDER_WIDTH;
    private final float evasionChance;
    private final float reactionDistance;
    float horizontalSpeed;
    private Lane visibleLane;

    public AIControlledStrategy(Lane currentLane, int level) {
        this.visibleLane = currentLane;
        this.evasionChance = 0.5f + level * 0.30f; // Must be between 0 and 1
        this.reactionDistance = 30.0f + REACTION_DISTANCE * level * 0.33f;
    }

    public AIControlledStrategy(Lane currentLane) {
        this.visibleLane = currentLane;
        this.evasionChance = BASE_EVASION; // Must be between 0 and 1
        this.reactionDistance = REACTION_DISTANCE;
    }

    @Override
    public void move(Movable movable, float delta) {
        float laneLeftLimit = visibleLane.getLanePosition();
        float laneRightLimit = laneLeftLimit + Lane.WIDTH;
        float movableLeftLimit = movable.getHitbox().x;
        float movableRightLimit = movable.getHitbox().x + movable.getHitbox().width;
        float speed = movable.getSpeed();
        horizontalSpeed = movable instanceof Boat ? ((Boat) movable).getHandling() : movable.getSpeed();
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
            if (isNear(movable.getHitbox(), obstacle.getHitbox(), reactionDistance)) {
                if (MathUtils.random() < evasionChance) {
                    if (movable.getHitbox().x < obstacle.getHitbox().x) {
                        dx -=  horizontalSpeed * delta; // Move left to evade
                    } else {
                        dx += horizontalSpeed * delta; // Move right to evade
                    }
                }
            }
        }
        return ensureWithinLaneLimits(movable, dx, laneLeftLimit, laneRightLimit);
    }

    private float adjustForPowerUps(Movable movable, float dx, float delta, float laneLeftLimit, float laneRightLimit) {
        for (PowerUp powerUp : visibleLane.getPowerUps()) {
            if (isNear(movable.getHitbox(), powerUp.getHitbox(), reactionDistance)) {
                if (movable.getHitbox().x < powerUp.getHitbox().x) {
                    dx += horizontalSpeed * delta; // Move right to collect
                } else {
                    dx -= horizontalSpeed * delta; // Move left to collect
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