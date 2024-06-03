package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.Gdx;
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
    private int speedModifier;

    /**
     * Constructs an Obstacle with the specified damage and hitbox.
     * @param damage the damage value of the obstacle
     * @param hitbox the hitbox of the obstacle
     */
    protected Obstacle(int damage, int speedModifier, Rectangle hitbox){
        super(hitbox);
        this.damage = damage;
        this.speedModifier = speedModifier;
    }

    /**
     * Factory method to create an obstacle given its type.
     * @param type the type of obstacle to create
     * @return the created obstacle
     */
    public static Obstacle createObstacle(ObstacleType type) {
        switch (type) {
            case LOG:
                return new Log();
            case DUCK:
                return new Duck();
            case STONE:
                return new Stone();
            default:
                throw new IllegalArgumentException("Invalid obstacle type");
        }
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
    @Override
    public void destroy() {
        Gdx.app.log("Obstacle", "Obstacle destroyed");
        //throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
    public void setSpeedModifier(int speedModifier) {
        this.speedModifier = speedModifier;
    }
    public int getSpeedModifier() {
        return speedModifier;
    }
}

