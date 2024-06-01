package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.util.Config;
import java.util.Set;

public class AIControlledStrategy implements MovementStrategy {
    private static final float DEFAULT_EVASION = 0.5f; // Default evasion chance
    private static final float REACTION_DISTANCE = 100.0f; // Distance at which the AI starts to react to obstacles, boats, and power-ups
    private static final float COLLISION_DISTANCE = 20.0f; // Minimum distance to avoid collision
    private static final int leftLIMIT = 10;
    private static final int rightLIMIT = Config.getWidth() - 10;
    private Lane visibleLane;
    private float evasionChance; // Chance of evading an obstacle or boat

    public AIControlledStrategy(Lane currentLane, float evasionChance) {
        this.visibleLane = currentLane;
        this.evasionChance = evasionChance; //Must be between 0 and 1
    }
    public AIControlledStrategy(Lane currentLane) {
        this.visibleLane = currentLane;
        this.evasionChance = DEFAULT_EVASION; //Must be between 0 and 1
    }

    @Override
    public void move(Movable movable, float delta) {
        float movableLeftLimit = movable.getHitbox().x;
        float movableRightLimit = movable.getHitbox().x + movable.getHitbox().width;
        int speed = movable.getSpeed();

        boolean isMovingLeft = MathUtils.randomBoolean(); // Random initial direction
        
        movable.adjustY(speed * delta); //We first move it upwards in the y axes

        for (Obstacle obstacle : visibleLane.getObstacles()) {
            if (isNear(movable.getHitbox(), obstacle.getHitbox(), REACTION_DISTANCE)) {
                if (MathUtils.random() < evasionChance) {
                    if (isMovingLeft) {
                        movable.adjustX(-speed * delta); // Move left to evade
                    } else {
                        movable.adjustX(speed * delta); // Move right to evade
                    }
                }
            }
        }

        for (Boat otherBoat : visibleLane.getBoats()) {
            if (!otherBoat.equals(movable) && isNear(movable.getHitbox(), otherBoat.getHitbox(), REACTION_DISTANCE)) {
                if (MathUtils.random() < evasionChance) {
                    if (isMovingLeft) {
                        movable.adjustX(-speed * delta); // Move left to evade
                    } else {
                        movable.adjustX(speed * delta); // Move right to evade
                    }
                }
            }
        }

        for (PowerUp powerUp : visibleLane.getPowerUps()) {
            if (isNear(movable.getHitbox(), powerUp.getHitbox(), REACTION_DISTANCE)) {
                if (MathUtils.random() < evasionChance) {
                    // Move towards the power-up
                    if (movable.getHitbox().x < powerUp.getHitbox().x) {
                        movable.adjustX(speed * delta); // Move right to collect
                    } else {
                        movable.adjustX(-speed * delta); // Move left to collect
                    }
                }
            }
        }

        // Check if the boat is within the lane limits and adjust direction if necessary
        if (movableLeftLimit <= leftLIMIT) {
            isMovingLeft = false;
            movable.adjustX(speed * delta);
        } else if (movableRightLimit >= rightLIMIT) {
            isMovingLeft = true;
            movable.adjustX(-speed * delta);
        } else {
            // Continue moving in the current direction if within limits
            if (isMovingLeft) {
                movable.adjustX(-speed * delta);
            } else {
                movable.adjustX(speed * delta);
            }
        }
    }
    private boolean isNear(Rectangle rect1, Rectangle rect2, float distance) {
        return rect1.overlaps(rect2) || rect1.contains(rect2) || rect2.contains(rect1) ||
               rect1.x <= rect2.x + rect2.width + distance && rect1.x + rect1.width >= rect2.x - distance &&
               rect1.y <= rect2.y + rect2.height + distance && rect1.y + rect1.height >= rect2.y - distance;
    }
}