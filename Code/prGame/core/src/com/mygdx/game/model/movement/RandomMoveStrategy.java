package com.mygdx.game.model.movement;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;



public class RandomMoveStrategy implements MovementStrategy {
    private static final float MOVEMENT_TIME_LIMIT = 2.0f; // Time limit for each movement direction (in seconds)
    private static final int leftLIMIT = 0;
    private static final int rightLIMIT = 3200;
    private boolean isMovingLeft;
    private float movementTimer; // Timer to track how long the object has been moving in the current direction

    public RandomMoveStrategy() {
        this.isMovingLeft = MathUtils.randomBoolean(); // Randomly select initial movement direction
        this.movementTimer = 0.0f; // Initialize timer
    }

    @Override
    public void move(Movable movable, float delta) {
        float movableLeftLimit = movable.getHitbox().x;
        float movableRightLimit = movable.getHitbox().x + movable.getHitbox().width;
        int horizontalSpeed = movable.getSpeed();

        // Update movement timer
        movementTimer += delta;

        // Check if it's time to change direction
        if (movementTimer >= MOVEMENT_TIME_LIMIT) {
            isMovingLeft = MathUtils.randomBoolean(); // Randomly decide new movement direction
            movementTimer = 0.0f; // Reset timer
        }

        // Move the object based on the current direction
        if (isMovingLeft) {
            if (movableLeftLimit <= leftLIMIT) {
                isMovingLeft = false;
                movable.adjustX(horizontalSpeed * delta);
            } else {
                movable.adjustX(-horizontalSpeed * delta);
            }
        } else {
            if (movableRightLimit >= rightLIMIT) {
                isMovingLeft = true;
                movable.adjustX(-horizontalSpeed * delta);
            } else {
                movable.adjustX(horizontalSpeed * delta);
            }
        }
    }
}

    }

