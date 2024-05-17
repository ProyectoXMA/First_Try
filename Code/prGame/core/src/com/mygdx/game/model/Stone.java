package com.mygdx.game.model;

public class Stone extends Obstacle {
    //Attributes for the Stone obstacle
    private double damageOnHit;
    private double speed;
    private boolean wasHit;
    //Constructor for the Stone obstacle
    public Stone(double damageOnHit, double speed, boolean wasHit){
        this.damageOnHit = damageOnHit;
        this.speed = speed;
        this.wasHit = wasHit;
    }
    //Methods for the Stone obstacle
    public double getDamageOnHit(){
        return damageOnHit; //return the damage on hit
    }
    public double getSpeed(){
        return speed; //return the speed
    }
    public boolean getWasHit(){
        return wasHit; //return if the stone was hit or not
    }
}
