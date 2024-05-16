package com.mygdx.game.model.movement;

public interface Movable {
    public void move();
    public void setMovementStrategy(MovementStrategy movementStrategy);
    public MovementStrategy getMovementStrategy();
}
