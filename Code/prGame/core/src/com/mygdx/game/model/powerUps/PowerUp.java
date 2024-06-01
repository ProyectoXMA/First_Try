package com.mygdx.game.model.powerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.Collidable;
import com.mygdx.game.model.CollidableVisitor;
import com.mygdx.game.model.GameObject;

/**
 * The PowerUp class represents a powerUp in the game.
 * It implements the Collidable and GameObject interfaces.
 * It is responsible for applying the powerUp to the boat when a collision occurs though method applyPowerUp.
 * It accepts a visitor of type CollidableVisitor to detect and handle collisions.
 */
public abstract class PowerUp extends GameObject implements Collidable {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    //A powerUp has a powerUpType which is an enum that represents the type of powerUp we are colliding
    //The constructor for the powerUp class
    protected PowerUp(Rectangle hitbox){
        super(hitbox);
    }
    public static PowerUp createPowerUp(PowerUpType powerUpType, float x, float y){
        switch (powerUpType){
            case SPEED:
                return new SpeedBoost(new Rectangle(x, y, WIDTH, HEIGHT));
            case INVINCIBILITY:
                return new Invincibility(new Rectangle(x, y, WIDTH, HEIGHT));
            case HEALTH:
                return new HealthBoost(new Rectangle(x, y, WIDTH, HEIGHT));
            default:
                throw new IllegalArgumentException("Invalid powerUpType");
        }
    }
    public static PowerUp createPowerUp(PowerUpType powerUpType) {
        return createPowerUp(powerUpType, 0, 0);
    }
    public abstract void applyPowerUp(Boat boat);
    @Override
    public void destroy(){
        Gdx.app.log("PowerUp", "PowerUp destroyed");
        //throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}