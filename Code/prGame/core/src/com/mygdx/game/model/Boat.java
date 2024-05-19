package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.movement.*;

public class Boat implements Movable, Collidable{
    //ALL THIS STUFF IS FOR THE MOVEMENT
    private MovementStrategy movementStrategy;
    private Rectangle hitBox;

    //Constants for the power ups that can be given to the boat
    //atributes for the boat, they are the base stats of the boat
    private final int baseHealth;
    private final int baseResistance;
    private final int baseHandling;
    private final int baseSpeed;
    private final int baseAcceleration;
    //current atributes for the boat, they can be changed by the power ups and collisions
    private int currentHealth;
    private int currentResistance;
    private int currentSpeed;
    private int currenAcceleration;
    private boolean isInvencible;
    //Constructor for the boat
    public Boat (int health,int resistance, int handling, int speed, int acceleration, Rectangle hitBox){
        this.baseHealth = health;
        this.baseResistance = resistance;
        this.baseHandling = handling;
        this.baseSpeed = speed;
        this.baseAcceleration = acceleration;
        this.hitBox = hitBox;
        currentHealth = baseHealth;
        currenAcceleration = baseAcceleration;
        currentResistance = baseResistance;
        currentSpeed = baseSpeed;
        isInvencible = false; //not invencible at the beginning, just when it is hit by an invencible power up
    }
    //Getters for the atributes of the boat
    public int getHealth(){
        return currentHealth; //get the current health
    }
    public int getResistance(){
        return currentResistance; //get the current resistance
    }
    public int getSpeed(){
        return currentSpeed; //get the current speed
    }
    public int getAcceleration(){
        return currenAcceleration; //get the current acceleration
    }
    public void setHealth(int health) {
        this.currentHealth = health; //set the current health
    }
    public void setResistance(int resistance) {
        this.currentResistance = resistance; //set the current resistance
    }
    public void setSpeed(int speed) {
        this.currentSpeed = speed;
    }//set the current speed
    public void setAcceleration(int acceleration) {
        this.currenAcceleration = acceleration; //set the current acceleration
    }

    //Check that the boat is invencible just to make sure
    //that when colliding with an obstacle the boat is not decreesing its health
    public boolean isInvencible(){
        return this.isInvencible;
    }
    //Verify if the boat is dead, no more health left
    public boolean dead(){
        return this.currentHealth <= 0;
    }
    //When the boat is hit by an obstacle, it decreases its health if it has hit it and if it is not invencible
    //Modify(increment or decrement) the atributes of the boat
    public void adjustHealth(int healthDelta) {
        this.currentHealth += healthDelta; //increase the current health
        currentHealth = Math.min(currentHealth, baseHealth); //if the current health is greater than the base health, set the current health to the base health
    }
    public void adjustResistance(int resistanceDelta) {
        this.currentResistance += resistanceDelta; //increase the current resistance
        currentResistance = Math.min(currentResistance, baseResistance); //if the current resistance is greater than the base resistance, set the current resistance to the base resistance
    }
    public void adjustSpeed(int speedDelta) {
        this.currentSpeed += speedDelta; //increase the current speed
        currentSpeed = Math.min(currentSpeed, baseSpeed); //if the current speed is greater than the base speed, set the current speed to the base speed
    }
    public void adjustAcceleration(int accelerationDelta) {
        this.currenAcceleration += accelerationDelta; //increase the current acceleration
        currenAcceleration = Math.min(currenAcceleration, baseAcceleration); //if the current acceleration is greater than the base acceleration, set the current acceleration to the base acceleration
    }

    public void setInvincible(boolean b) {
        this.isInvencible = b;
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }

    @Override
    public void accept(CollidableVisitor visitor) {
        visitor.visitBoat(this);
    }

    //MovementStrategy is made in the MovementStrategy class, this is the strategy pattern
    @Override
    public void move(float delta) {
        movementStrategy.move(this, delta);
    }

    @Override
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    @Override
    public MovementStrategy getMovementStrategy() {
        return this.movementStrategy;
    }
}
