package com.mygdx.game.model.obstacles;

public abstract class Obstacle {
    //TODO: Implement abstract class Obstacle from which Duck, Stone and Log will inherit
    private int damage;
    private boolean wasHit = false;
    public Obstacle(int damage){
        this.damage = damage;
    }
    public boolean getWasHit(){
        return wasHit;
    }
    public int getDamage(){
        return damage;
    }
    public abstract void accept(ObstacleVisitor visitor);
}

