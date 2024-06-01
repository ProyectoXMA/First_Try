package com.mygdx.game.model.movement;

public interface Movable {
    public void adjustX(float x);
    public void adjustY(float y);
    public void move(float delta);
    public void setMovementStrategy(MovementStrategy movementStrategy);
    public MovementStrategy getMovementStrategy();

    //Getters for the exclusive attributes of the obstacle Duck
    int getSpeed();

    void setSpeed(int speed);
}
