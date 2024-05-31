package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

/**
 * This class enforces certain methods to be implemented by all game-objects classes.
 */
public abstract class GameObject implements Collidable{
    private float x, y;
    private Rectangle hitbox;
    private final float width, height;
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
    public GameObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);
    }
    public GameObject(Rectangle hitbox) {
        this.hitbox = hitbox;
        this.x = hitbox.x;
        this.y = hitbox.y;
        this.width = hitbox.width;
        this.height = hitbox.height;
    }
    public abstract void destroy();
}