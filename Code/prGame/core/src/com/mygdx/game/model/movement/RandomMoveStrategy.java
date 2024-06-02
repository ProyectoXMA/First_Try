package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.Leg;
import com.mygdx.game.util.Config;

public class RandomMoveStrategy implements MovementStrategy {
    private static final float MOVEMENT_TIME_LIMIT = 2.0f; // Time limit for each movement direction (in seconds)
    private static final float STOP_PROBABILITY = 0.05f; // Probability of stopping randomly
    private static final float leftLIMIT = Lane.WIDTH * Leg.NUMBER_OF_LANES * 0.01f;
    private static final float rightLIMIT = Lane.WIDTH * Leg.NUMBER_OF_LANES * 0.99f;
    private static final int bottomLIMIT = 0;
    private static final int topLIMIT = Lane.HEIGHT;
    private static final float SPEED_FACTOR = 0.5f; // Factor to reduce speed, adjust as needed

    private boolean isMoving; // Whether the object is currently moving
    private float movementTimer; // Timer to track how long the object has been moving in the current direction
    private float angle; // Movement angle in radians
    private float desiredAngle; // Desired movement angle in radians

    public RandomMoveStrategy() {
        this.isMoving = true; // Initially, the object is moving
        this.movementTimer = 0.0f; // Initialize timer
        this.angle = MathUtils.random(0.0f, 2.0f * (float) Math.PI); // Random initial movement angle
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

        // Check if it's time to change direction or stop randomly
        if (movementTimer >= MOVEMENT_TIME_LIMIT) {
            movementTimer = 0.0f; // Reset timer
            if (MathUtils.random() < STOP_PROBABILITY) {
                isMoving = false; // Stop moving
                angle %= 2.0f * (float) Math.PI; // Normalize angle
                desiredAngle = angle; // Stop rotating
            } else {
                isMoving = true; // Start moving
                desiredAngle += MathUtils.random(-(float) Math.PI, (float) Math.PI); // Randomly change the angle
            }
        }
        angle += (desiredAngle - angle) / Config.FPS; // Smoothly adjust the angle towards the desired angle

        if (isMoving) {
            // Calculate movement deltas with speed factor
            float dx = MathUtils.cos(angle) * speed * delta * SPEED_FACTOR;
            float dy = MathUtils.sin(angle) * speed * delta * SPEED_FACTOR;

            // Check and adjust horizontal movement
            if (movableLeftLimit + dx <= leftLIMIT) {
                angle = MathUtils.PI - angle; // Reflect angle horizontally
                desiredAngle = MathUtils.PI - desiredAngle; // Reflect desired angle horizontally
                dx = -dx; // Invert horizontal movement
            } else if (movableRightLimit + dx >= rightLIMIT) {
                desiredAngle = MathUtils.PI - desiredAngle; // Reflect desired angle horizontally
                angle = MathUtils.PI - angle; // Reflect angle horizontally
                dx = -dx; // Invert horizontal movement
            }

            // Check and adjust vertical movement
            if (movableBottomLimit + dy <= bottomLIMIT) {
                angle = -angle; // Reflect angle vertically
                desiredAngle = -desiredAngle; // Reflect desired angle vertically
                dy = -dy; // Invert vertical movement
            } else if (movableTopLimit + dy >= topLIMIT) {
                angle = -angle; // Reflect angle vertically
                desiredAngle = -desiredAngle; // Reflect desired angle vertically
                dy = -dy; // Invert vertical movement
            }

            // Apply movement
            movable.adjustX(dx);
            movable.adjustY(dy);
        }
    }
}