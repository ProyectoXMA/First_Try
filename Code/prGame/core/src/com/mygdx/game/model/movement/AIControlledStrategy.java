package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.util.Config;

public class AIControlledStrategy implements MovementStrategy {
    private static final float DEFAULT_EVASION = 0.99f; // Default evasion chance
    private static final float REACTION_DISTANCE = 50.0f; // Distance at which the AI starts to react to obstacles, boats, and power-ups
    private static final float SPEED_FACTOR = 0.5f;
    private static final int leftLIMIT = 10;
    private static final int rightLIMIT = Config.getWidth() - 10;
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

    }


}