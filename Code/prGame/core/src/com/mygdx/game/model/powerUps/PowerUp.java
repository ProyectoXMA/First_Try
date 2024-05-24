package com.mygdx.game.model.powerUps;

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
public abstract class PowerUp implements Collidable,GameObject {
    //A powerUp has a powerUpType which is an enum that represents the type of powerUp we are colliding
    private final Rectangle hitBox;
    //The constructor for the powerUp class
    public PowerUp(Rectangle hitBox){
        this.hitBox = hitBox;
    }
    public abstract void applyPowerUp(Boat boat);
    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }
    @Override
    public void accept(CollidableVisitor visitor) {
        visitor.visitPowerUp(this);
    }
}