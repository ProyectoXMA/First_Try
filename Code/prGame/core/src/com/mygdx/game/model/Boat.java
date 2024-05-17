package com.mygdx.game.model;

import com.mygdx.game.model.movement.*;

public class Boat implements Movable {
    //ALL THIS STUFF IS FOR THE MOVEMENT
    private MovementStrategy movementStrategy;
    //MovementStrategy is made in the MovementStrategy class, this is the strategy pattern
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
        return this.movementStrategy;
    }
    //Constants for the power ups that can be given to the boat
    private final double CONSTANT_HEALTH_INCREASE = 100;
    private final double CONSTANT_SPEED_INCREASE = 2;
    //atributes for the boat, they are the base stats of the boat
    private double baseHealth;
    private int baseResistance;
    private int baseHandling;
    private double baseSpeed;
    private double baseAcceleration;

    //current atributes for the boat, they can be changed by the power ups and collisions
    private double currentHealth;
    private int currentResistance;
    private double currentSpeed;
    private double currenAcceleration;
    private boolean isInvencible;
    //Constructor for the boat
    public Boat (double health,int resistance, int handling, double speed, double acceleration){
        this.baseHealth = health;
        this.baseResistance = resistance;
        this.handling = baseHandling;
        this.speed = baseSpeed;
        this.acceleration = baseAcceleration;
        currentHealth = baseHealth;
        currenAcceleration = baseAcceleration;
        currentResistance = baseResistance;
        currentSpeed = baseSpeed;
        isInvencible = false; //not invencible at the beginning, just when it is hit by an invencible power up
    }
    //Getters for the atributes of the boat
    public double getHealth(){
        return currentHealth; //get the current health
    }
    public int getResistance(){
        return currentResistance; //get the current resistance
    }
    public double getSpeed(){
        return currentSpeed; //get the current speed
    }
    public double getAcceleration(){
        return currenAcceleration; //get the current acceleration
    }
    //Check that the boat is invencible just to make sure 
    //that when colliding with an obstacle the boat is not decreesing its health
    public boolean isInvencible(){
        return this.isInvencible;
    }
    //Verify if the boat is dead, no more health left
    public boolean dead(){
        return this.currentHealth == 0;
    }
    //When the boat is hit by an obstacle, it decreases its health if it has hit it and if it is not invencible
    public double decreaseHealth(Obstacle ob){
        if(ob.getWasHit() || !isInvencible()){
            this.currentHealth -= ob.getDamageOnHit();
    }
    }   
    //When the boat collides with a power up, its stats are increased depending on the type of power up it hits
    public double applyPowerUp(PowerUpType powerUp){
        if(powerUp == PowerUpType.HEALTH){
            this.currentHealth += CONSTANT_HEALTH_INCREASE; //hitting a health power up increases the boat's health
        }else if(powerUp == PowerUpType.SPEED){
            this.currentSpeed += CONSTANT_SPEED_INCREASE; //hitting a speed power up increases the boat's speed
        }else{
            this.isInvencible = true; //hitting an invencible power up makes the boat invencible for some time
        }
    }
}

