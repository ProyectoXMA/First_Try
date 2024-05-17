package com.mygdx.game.model;

public abstract class Obstacle {
    //TODO: Implement abstract class Obstacle from which Duck, Stone and Log will inherit
    private double damageOnHit;
    private boolean wasHit;
    private double speed;
    public Obstacle(double damage, boolean wasHit){
        this.damageOnHit = damage;
        this.wasHit = wasHit;
    }
    public boolean getWasHit(){
        return wasHit;
    }
    public double getDamageOnHit(){
        return damageOnHit;
    }
}

