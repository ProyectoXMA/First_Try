package com.mygdx.game.model;

import com.mygdx.game.model.movement.Movable;
import com.mygdx.game.model.movement.MovementStrategy;

public class Duck extends Obstacle implements Movable {
    //TODO: Implement class Duck (dynamic obstacle)
    MovementStrategy movementStrategy;
    @Override
    public void move() {
        movementStrategy.move();
    }

    @Override
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    @Override
    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

}
