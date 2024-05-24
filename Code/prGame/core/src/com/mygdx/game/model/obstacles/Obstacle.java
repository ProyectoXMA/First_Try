package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Collidable;
import com.mygdx.game.model.CollidableVisitor;
import com.mygdx.game.model.GameObject;
/**
 * The Obstacle class represents an obstacle in the game.
 * It implements the Collidable and GameObject interfaces.
 * An obstacle has a damage value, a hitbox, and a state of whether it was hit.
 */
public abstract class Obstacle implements Collidable,GameObject {
    private int damage;
    private Rectangle hitbox;
    private boolean wasHit = false;

    /**
     * Constructs an Obstacle with the specified damage and hitbox.
     * @param damage the damage value of the obstacle
     * @param hitbox the hitbox of the obstacle
     */
    public Obstacle(int damage, Rectangle hitbox){
        this.damage = damage;
        this.hitbox = hitbox;
    }
    public Rectangle getHitbox(){
        return hitbox;
    }
    public boolean getWasHit(){
        return wasHit;
    }
    public int getDamage(){
        return damage;
    }

    /**
     * Accepts a visitor of type ObstacleVisitor.
     * The specific behavior of this method is determined by the implementing class.
     * @param visitor the visitor to accept
     */
    public abstract void accept(ObstacleVisitor visitor);

    /**
     * Accepts a visitor of type CollidableVisitor and calls its visitObstacle method.
     * @param visitor the visitor to accept
     */
    public void accept(CollidableVisitor visitor){
        visitor.visitObstacle(this);
    }
}

