package com.mygdx.game.model;

public class PowerUp {
    private final int HEALTH_INCREASE = 100;
    private final int SPEED_INCREASE = 2;
    //A powerUp has a powerUpType which is an enum that represents the type of powerUp we are colliding
    private final PowerUpType type;
    //The constructor for the powerUp class
    public PowerUp(PowerUpType type){
        this.type = type;
    }
    //Getter for the powerUpType
    public PowerUpType getPowerUpType(){
        return type;
    }
    public void applyPowerUp(Boat boat){
        //Switch case for the powerUpType
        switch (type){
            case HEALTH:
                //Increase the health of the boat
                boat.adjustHealth(HEALTH_INCREASE);
                break;
            case SPEED:
                //Increase the speed of the boat
                boat.adjustSpeed(SPEED_INCREASE);
                break;
            case INVINCIBLE:
                //Make the boat invincible
                boat.setInvincible(true);
                break;
            default:
                throw new IllegalArgumentException("Invalid power up type");
        }
    }
}