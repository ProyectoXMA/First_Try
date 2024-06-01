package com.mygdx.game.model.movement;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.powerUps.PowerUp;

public interface Movable {
    public void adjustX(float x);
    public void adjustY(float y);
    public void move(float delta);
    public void setMovementStrategy(MovementStrategy movementStrategy);
    public MovementStrategy getMovementStrategy();
    public Rectangle getHitbox();


    //Getters for the exclusive attributes of the obstacle Duck
    int getSpeed();
    ;
    void setSpeed(int speed);


}
