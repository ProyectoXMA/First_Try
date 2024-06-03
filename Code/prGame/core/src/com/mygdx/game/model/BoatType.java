package com.mygdx.game.model;

import java.util.Random;

public enum BoatType {
    FAST, STRONG, CLASSIC;
    public static BoatType getRandomType() {
        return values()[(int) (Math.random() * values().length)];
    }
}
