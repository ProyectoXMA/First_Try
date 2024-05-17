package com.mygdx.game.model;

public abstract class Obstacle {
    //Attributes that all obstacles are gonna share
    private double damageOnHit;
    private boolean wasHit;
    private double speed;
    //Constructor for obstacles
    public Obstacle(double damage, boolean wasHit){
        this.damageOnHit = damage;
        this.wasHit = wasHit;
    }
    //Methods for obstacles
    public boolean getWasHit(){
        return wasHit; //return true if it was hit
    }
    public double getDamageOnHit(){
        return damageOnHit; //return the damage on hit
    }
}

