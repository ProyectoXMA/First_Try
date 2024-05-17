package com.mygdx.game.model.obstacles;

public abstract class Obstacle {
    //TODO: Implement abstract class Obstacle from which Duck, Stone and Log will inherit
    public abstract void accept(ObstacleVisitor visitor);
}

