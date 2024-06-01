package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.MathUtils;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.CollisionHandler;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

import com.mygdx.game.util.Config;

import java.util.Set;



public class AIControlledStrategy implements MovementStrategy {
    private static final float REACTION_DISTANCE = 100.0f; // Distance at which the AI starts to react to obstacles, boats, and power-ups
    private static final float COLLISION_DISTANCE = 20.0f; // Minimum distance to avoid collision
    private static final int leftLIMIT = 10;
    private static final int rightLIMIT = Config.getWidth() - 10;
    private Set<Obstacle> obstacles;
    private Set<Boat> boats;
    private Set<PowerUp> powerUps;
    private float evasionChance; // Chance of evading an obstacle or boat
    private CollisionHandler collisionHandler;

    public AIControlledStrategy(Set<Obstacle> obstacles, Set<Boat> boats, Set<PowerUp> powerUps, float evasionChance) {

        this.obstacles = obstacles;
        this.boats = boats;
        this.powerUps = powerUps;
        this.evasionChance = evasionChance;
        this.collisionHandler = new CollisionHandler();
    }

    @Override
    public void move(Movable movable, float delta) {
        if (!(movable instanceof Boat)) {
            throw new IllegalArgumentException("Movable must be an instance of Boat");
        }

        Boat boat = (Boat) movable;
        collisionHandler.setBoat(boat);

        float movableLeftLimit = boat.getHitbox().x;
        float movableRightLimit = boat.getHitbox().x + boat.getHitbox().width;
        int speed = boat.getSpeed();
        boolean isMovingLeft = MathUtils.randomBoolean(); // Random initial direction

        for (Obstacle obstacle : obstacles) {
            if (isNear(boat.getHitbox(), obstacle.getHitbox(), REACTION_DISTANCE)) {
                if (MathUtils.random() < evasionChance) {
                    if (isMovingLeft) {
                        boat.adjustX(-speed * delta); // Move left to evade
                    } else {
                        boat.adjustX(speed * delta); // Move right to evade
                    }
                } else {
                    collisionHandler.checkObstacleCollision(obstacle);
                }
            }
        }

        for (Boat otherBoat : boats) {
            if (otherBoat != boat && isNear(boat.getHitbox(), otherBoat.getHitbox(), REACTION_DISTANCE)) {
                if (MathUtils.random() < evasionChance) {
                    if (isMovingLeft) {
                        boat.adjustX(-speed * delta); // Move left to evade
                    } else {
                        boat.adjustX(speed * delta); // Move right to evade
                    }
                } else {
                    collisionHandler.checkBoatCollision(otherBoat);
                }
            }
        }

        for (PowerUp powerUp : powerUps) {
            if (isNear(boat.getHitbox(), powerUp.getHitbox(), REACTION_DISTANCE)) {
                if (MathUtils.random() < evasionChance) {
                    // Move towards the power-up
                    if (boat.getHitbox().x < powerUp.getHitbox().x) {
                        boat.adjustX(speed * delta); // Move right to collect
                    } else {
                        boat.adjustX(-speed * delta); // Move left to collect
                    }
                } else {
                    collisionHandler.checkPowerUpCollision(powerUp);
                }
            }
        }

        // Check if the boat is within the lane limits and adjust direction if necessary
        if (movableLeftLimit <= leftLIMIT) {
            isMovingLeft = false;
            boat.adjustX(speed * delta);
        } else if (movableRightLimit >= rightLIMIT) {
            isMovingLeft = true;
            boat.adjustX(-speed * delta);
        } else {
            // Continue moving in the current direction if within limits
            if (isMovingLeft) {
                boat.adjustX(-speed * delta);
            } else {
                boat.adjustX(speed * delta);
            }
        }
    }

    private boolean isNear(Rectangle rect1, Rectangle rect2, float distance) {
        return rect1.overlaps(rect2) || rect1.contains(rect2) || rect2.contains(rect1) ||
               rect1.x <= rect2.x + rect2.width + distance && rect1.x + rect1.width >= rect2.x - distance &&
               rect1.y <= rect2.y + rect2.height + distance && rect1.y + rect1.height >= rect2.y - distance;
    }
}