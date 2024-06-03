package com.mygdx.game.model.obstacles;

public enum ObstacleType {
    LOG, DUCK, STONE;
    public static ObstacleType getRandomType() {
        return values()[(int) (Math.random() * values().length)];
    }
}
