package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.movement.*;
import com.mygdx.game.util.Config;

/**
 * The Boat class represents a boat in the game.
 * It implements the Movable, Collidable, and GameObject interfaces.
 * It is responsible for storing the boat's attributes.
 * It delegates the movement (as all Movable objects) to a MovementStrategy.
 */
public class Boat extends GameObject implements Movable{
    private MovementStrategy movementStrategy;

    private final BoatType type;
    //Attributes for the boat, they are the base stats of the boat
    private final int baseHealth;
    private final int baseResistance;
    private final int baseHandling;
    private final float baseSpeed;
    private final int baseAcceleration;
    //Current attributes for the boat, they can be changed by the power ups and collisions
    private int currentHealth;
    private int currentResistance;
    private float currentSpeed;
    private int currenAcceleration;
    private boolean returnToLife = false;
    private boolean isInvencible = false;
    private boolean destroyed = false;

    /**
     * Private constructor of class Boat, the only way to create a boat is using the factory method createBoat
     * It receives the base stats of the boat and the hitbox of the boat
     * @param health health of the boat
     * @param resistance resistance to damage
     * @param handling horizontal movement speed
     * @param speed vertical movement speed
     * @param acceleration acceleration of the boat
     */
    private Boat (BoatType type, int health, int resistance, int handling, float speed, int acceleration, Rectangle hitBox){
        super(hitBox);
        this.type = type;
        this.baseHealth = health;
        this.baseResistance = resistance;
        this.baseHandling = handling;
        this.baseSpeed = speed;
        this.baseAcceleration = acceleration;
        currentHealth = baseHealth;
        currenAcceleration = baseAcceleration;
        currentResistance = baseResistance;
        currentSpeed = 0;
    }

    /**
     * Factory method to create a boat of a given type. The Boat contructor is private, so with his method we restrict the creation of boats to provided types.
     * @param type the type of boat to create on which the stats depend
     * @param x the x coordinate of the boat
     * @param y the y coordinate of the boat
     * @return a new boat of the given type at the given position (x,y)
     */

    public static Boat createBoat(BoatType type, float x, float y) {
        Boat newBoat;
        switch (type) {
            case FAST:
                newBoat = new Boat(type,100, 10, 150, 150, 10, new Rectangle(x, y, 0.6f * Config.BoatRelativeSize, 1.0f * Config.BoatRelativeSize)); //Adjusted to percentage of the images
                break;
            case STRONG:
                newBoat = new Boat(type,200, 5, 70, 100, 10, new Rectangle(x, y, 0.5f * Config.BoatRelativeSize, 1.0f * Config.BoatRelativeSize)); //Adjusted to percentage of the images
                break;
            case CLASSIC:
                newBoat=  new Boat(type,150, 7, 100, 130, 18, new Rectangle(x, y, 0.9f * Config.BoatRelativeSize, 1.0f * Config.BoatRelativeSize)); //Adjusted to percentage of the images
                break;
            default:
                throw new IllegalArgumentException("Not a valid boat type");
        }
        return newBoat;
    }
    /**
     * Factory method to create a boat of a given type. The Boat contructor is private, so with his method we restrict the creation of boats to provided types.
     * @param type the type of boat to create on which the stats depend
     * @return a new boat of the given type with default position (0,0)
     */

    public static Boat createBoat(BoatType type) {
        return createBoat(type, 0, 0);
    }
    public void resetCharacteristics() {
        currentHealth = baseHealth;
        currenAcceleration = baseAcceleration;
        currentResistance = baseResistance;
        currentSpeed = 0;
        isInvencible = false;
    }

    //Getters for the attributes of the boat
    public BoatType getType(){
        return type; //get the type of the boat
    }
    public int getHealth(){
        return currentHealth; //get the current health
    }
    public int getResistance(){
        return currentResistance; //get the current resistance
    }
    public int getHandling(){
        return baseHandling; //get the current resistance
    }
    public float getSpeed(){
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
    public void setSpeed(float speed) {
        this.currentSpeed = speed;
    }//set the current speed
    public void setAcceleration(int acceleration) {
        this.currenAcceleration = acceleration; //set the current acceleration
    }
    public int getBaseHealth(){
        return baseHealth;
    }

    public boolean hasReturnToLife() {
        if(!returnToLife && !destroyed) {
            returnToLife = true;
            return false;
        }
        return true;
    }
    //Check that the boat is invencible just to make sure
    //that when colliding with an obstacle the boat is not decreesing its health
    public boolean isInvencible(){
        return this.isInvencible;
    }
    //Verify if the boat is dead, no more health left
    public boolean isDead(){
        return this.currentHealth <= 0;
    }
    //When the boat is hit by an obstacle, it decreases its health if it has hit it and if it is not invencible
    //Modify(increment or decrement) the atributes of the boat
    public void adjustHealth(int healthDelta) {
        this.currentHealth += healthDelta; //increase the current health
        currentHealth = Math.max(currentHealth, 0); //if the current health is less than 0, set the current health to 0
        currentHealth = Math.min(currentHealth, baseHealth); //if the current health is greater than the base health, set the current health to the base health
    }
    public void adjustResistance(int resistanceDelta) {
        this.currentResistance += resistanceDelta; //increase the current resistance
        currentResistance = Math.min(currentResistance, baseResistance); //if the current resistance is greater than the base resistance, set the current resistance to the base resistance
    }
    public void adjustSpeed(float speedDelta) {
        this.currentSpeed += speedDelta; //increase the current speed
        currentSpeed = Math.max(currentSpeed, 0); //if the current speed is less than 0, set the current speed to 0
        currentSpeed = Math.min(currentSpeed, baseSpeed); //if the current speed is greater than the base speed, set the current speed to the base speed
    }
    public void adjustAcceleration(int accelerationDelta) {
        this.currenAcceleration += accelerationDelta; //increase the current acceleration
        currenAcceleration = Math.min(currenAcceleration, baseAcceleration); //if the current acceleration is greater than the base acceleration, set the current acceleration to the base acceleration
    }

    public void setInvincible(boolean b) {
        this.isInvencible = b;
    }

    /**
     * Accepts a visitor of type CollidableVisitor to handle the collisions
     */

    //In this case as there are no boat type subclasses the implementation of the accept method is done at the right level
    @Override
    public void adjustX(float x) {
        super.setX(super.getX() + x);
    }
    @Override
    public void adjustY(float y) {
        super.setY(super.getY() + y);
    }

    /**
     * Delegate the movement to the MovementStrategy
     * @param delta the time passed since the last frame
     */
    @Override
    public void move(float delta) {
        movementStrategy.move(this, delta);
        adjustSpeed(getAcceleration()*delta);
    }

    @Override
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    @Override
    public MovementStrategy getMovementStrategy() {
        return this.movementStrategy;
    }
    @Override
    public void destroy() {
        currentHealth = 0;
        destroyed = true;
        Gdx.app.log("Boat", "Boat " + getType() + "destroyed");
        //throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}
