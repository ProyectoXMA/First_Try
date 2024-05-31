package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

/**
 * This class enforces certain methods to be implemented by all game-objects classes.
 */
public abstract class GameObject implements Collidable{
    private float x, y;
    private Rectangle hitbox;
    private final float width, height;
    private boolean wasHit;
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public boolean getWasHit() {
        return wasHit;
    }
    public void setWasHit(boolean wasHit) {
        this.wasHit = wasHit;
    }
    public GameObject(float x, float y, float width, float height) {
        this.wasHit = false;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);
    }
    public GameObject(Rectangle hitbox) {
        this(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
    public abstract void destroy();
}