package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

/**
 * The Collidable interface enforces the implementation of the getHitbox method and the accept method
 * needed for the visitor pattern in collision handling.
 */
public interface Collidable {

    public boolean getWasHit();
    public void setWasHit(boolean hit); //When a b
    public Rectangle getHitbox();
}
