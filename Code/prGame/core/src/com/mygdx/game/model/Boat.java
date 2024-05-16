package com.mygdx.game.model;

import com.mygdx.game.model.movement.*;

public class Boat implements Movable {
    private MovementStrategy movementStrategy;
    //TODO
    @Override
    public void move() {
        movementStrategy.move();
    }

    @Override
    public void setMovementStrategy(MovementStrategy movementStrategy) {

    }

    @Override
    public MovementStrategy getMovementStrategy() {
        return null;
    }
}
