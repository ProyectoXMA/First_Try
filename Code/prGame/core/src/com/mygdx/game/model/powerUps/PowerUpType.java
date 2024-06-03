package com.mygdx.game.model.powerUps;

public enum PowerUpType {
    SPEED, INVINCIBILITY, HEALTH;
    public static PowerUpType getRandomType() {
        return values()[(int) (Math.random() * values().length)];
    }
}
