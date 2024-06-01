package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

/**
 * This class enforces certain methods to be implemented by all game-objects classes.
 */
public abstract class GameObject implements Collidable{
    private Rectangle hitbox;
    private boolean wasHit;
    public float getX() {
        return hitbox.getX();
    }
    public float getY() {
        return hitbox.getY();
    }
    public float getWidth() {
        return hitbox.getWidth();
    }
    public float getHeight() {
        return hitbox.getHeight();
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
    public void setX(float x) {
        this.hitbox.x = x;
    }
    public void setY(float y) {
        this.hitbox.y = y;
    }
    public boolean getWasHit() {
        return wasHit;
    }
    public void setWasHit(boolean wasHit) {
        this.wasHit = wasHit;
    }
    public GameObject(float x, float y, float width, float height) {
        this.wasHit = false; //When created it hasn´t been hit
        this.hitbox = new Rectangle(x, y, width, height);
    }
    public GameObject(Rectangle hitbox) {
        this(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
    public abstract void destroy();
}