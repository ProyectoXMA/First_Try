package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public interface Collidable {
    public Rectangle getHitbox();
    public void accept(CollidableVisitor visitor);
}
