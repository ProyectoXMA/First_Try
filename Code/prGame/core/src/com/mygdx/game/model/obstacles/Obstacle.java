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
public abstract class Obstacle extends GameObject implements Collidable {
    private int damage;


    /**
     * Constructs an Obstacle with the specified damage and hitbox.
     * @param damage the damage value of the obstacle
     * @param hitbox the hitbox of the obstacle
     */
    public Obstacle(int damage, Rectangle hitbox){
        super(hitbox);
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }

    /**
     * Accepts a visitor of type ObstacleVisitor.
     * The specific behavior of this method is determined by the implementing class.
     * @param visitor the visitor to accept
     */
    //Maybe we should delete it
    public abstract void accept(ObstacleVisitor visitor);

    /**
     * Accepts a visitor of type CollidableVisitor and calls its visitObstacle method.
     * @param visitor the visitor to accept
     */

    @Override
    public void accept(CollidableVisitor visitor){
        visitor.visitObstacle(this);
    }
}

