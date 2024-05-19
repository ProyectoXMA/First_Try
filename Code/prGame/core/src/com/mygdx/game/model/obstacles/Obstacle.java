package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Collidable;
import com.mygdx.game.model.CollidableVisitor;

public abstract class Obstacle implements Collidable {
    private int damage;
    private Rectangle hitbox;
    private boolean wasHit = false;
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
    public abstract void accept(ObstacleVisitor visitor);
    public void accept(CollidableVisitor visitor){
        visitor.visitObstacle(this);
    }
}

