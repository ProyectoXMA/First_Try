package com.mygdx.game.model;

import com.mygdx.game.model.movement.Movable;
import com.mygdx.game.model.movement.MovementStrategy;

public class Duck extends Obstacle implements Movable {
    //MovementStrategy is not implemented here, just the pattern
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
    //attributes of the obstacle Duck
    private double damageOnHit;
    private double speed;
    private boolean wasHit;
    //Constructor for the obstacle Duck
    public Duck(double damage, double speed){
        this.damageOnHit = damage;
        this.speed = speed;
        this.wasHit = false;
    }
    //Getters for the attributes of the obstacle Duck
    public double getDamageOnHit(){
        return damageOnHit; //returns the damage on hit
    }
    public double getSpeed(){
        return speed; //returns the speed
    }
    public boolean getWasHit(){
        return wasHit; //returns if the obstacle was hit or not
    }
}
