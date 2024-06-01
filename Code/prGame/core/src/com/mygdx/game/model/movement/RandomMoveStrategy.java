package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class RandomMoveStrategy implements MovementStrategy {
    private static final float MOVEMENT_TIME_LIMIT = 2.0f; // Time limit for each movement direction (in seconds)
    private static final int leftLIMIT = 0;
    private static final int rightLIMIT = 3200;
    private static final int bottomLIMIT = 0;
    private static final int topLIMIT = 1800; // Example top limit, we do not know yey which it is
    private boolean isMovingLeft;
    private boolean isMovingUp;
    private float movementTimer; // Timer to track how long the object has been moving in the current direction

    public RandomMoveStrategy() {
        this.isMovingLeft = MathUtils.randomBoolean(); // Randomly select initial horizontal movement direction
        this.isMovingUp = MathUtils.randomBoolean(); // Randomly select initial vertical movement direction
        this.movementTimer = 0.0f; // Initialize timer
    }

    @Override
    public void move(Movable movable, float delta) {
        float movableLeftLimit = movable.getHitbox().x;
        float movableRightLimit = movable.getHitbox().x + movable.getHitbox().width;
        float movableBottomLimit = movable.getHitbox().y;
        float movableTopLimit = movable.getHitbox().y + movable.getHitbox().height;
        int speed = movable.getSpeed();

        // Update movement timer
        movementTimer += delta;

        // Check if it's time to change direction
        if (movementTimer >= MOVEMENT_TIME_LIMIT) {
            isMovingLeft = MathUtils.randomBoolean(); // Randomly decide new horizontal movement direction
            isMovingUp = MathUtils.randomBoolean(); // Randomly decide new vertical movement direction
            movementTimer = 0.0f; // Reset timer
        }

        // Move the object horizontally based on the current direction
        if (isMovingLeft) {
            if (movableLeftLimit <= leftLIMIT) {
                isMovingLeft = false;
                movable.adjustX(speed * delta);
            } else {
                movable.adjustX(-speed * delta);
            }
        } else {
        } else {
            if (movableRightLimit >= rightLIMIT) {
                isMovingLeft = true;
                movable.adjustX(-speed * delta);
            } else {
                movable.adjustX(speed * delta);
            }
        }

        // Move the object vertically based on the current direction
        if (isMovingUp) {
            if (movableTopLimit >= topLIMIT) {
                isMovingUp = false;
                movable.adjustY(-speed * delta);
            } else {
                movable.adjustY(speed * delta);
            }
        } else {
            if (movableBottomLimit <= bottomLIMIT) {
                isMovingUp = true;
                movable.adjustY(speed * delta);
            } else {
                movable.adjustY(-speed * delta);
            }
        }
    }
}