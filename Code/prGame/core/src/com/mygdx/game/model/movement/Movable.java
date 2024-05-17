package com.mygdx.game.model.movement;

public interface Movable {
    public void move(float delta);
    public void setMovementStrategy(MovementStrategy movementStrategy);
    public MovementStrategy getMovementStrategy();
}
