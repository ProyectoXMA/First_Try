package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.Rectangle;

public interface Movable {
    void adjustX(float x);
    void adjustY(float y);
    void move(float delta);
    void setMovementStrategy(MovementStrategy movementStrategy);
    MovementStrategy getMovementStrategy();
    Rectangle getHitbox();
    float getSpeed();
    void setSpeed(float speed);
}
