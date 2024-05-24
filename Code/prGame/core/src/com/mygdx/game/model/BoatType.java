package com.mygdx.game.model;

import java.util.Random;

public enum BoatType {
    FAST, STRONG, CLASSIC;

    private static final Random RANDOM = new Random();
    public static BoatType getRandomType() {
        BoatType[] types = values();
        int index = RANDOM.nextInt(types.length);
        return types[index];
    }
}
