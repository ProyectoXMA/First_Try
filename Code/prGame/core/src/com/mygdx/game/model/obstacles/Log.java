package com.mygdx.game.model;
//This class is not dynamic because a log cannot move
public class Log extends Obstacle {
    //Attributes for the log obstacle
    private double damageOnHit;
    private double speed;
    private boolean wasHit;
    //Constructor for the log obstacle
    public Log(double damage, double speed){
        this.damageOnHit = damage;
        this.speed = speed;
        this.wasHit = false;
    }
    //Methods for the log obstacle
    public boolean getWasHit(){
        return wasHit; //Returns of the log was hit or not
    }
    public double getDamageOnHit(){
        return damageOnHit; //Returns the damage on hit of the log obstacle
    }
    public double getSpeed(){
        return speed; //Returns the speed of the log obstacle
    }
}
