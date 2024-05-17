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
    private double damageOnHit;
    private double speed;
    private boolean wasHit;

    public Duck(double damage, double speed){
        this.damageOnHit = damage;
        this.speed = speed;
        this.wasHit = false;
    }

    public double getDamageOnHit(){
        return damageOnHit;
    }
    public double getSpeed(){
        return speed;
    }
    public boolean getWasHit(){
        return wasHit;
    }
}
