package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class BoatFactory {
    public static Boat createBoat(BoatType type) {
        switch (type) {
            case FAST:
                return new Boat(100, 10, 100, 200, 10, new Rectangle());
            case STRONG:
                return new Boat(200, 5, 50, 140, 5, new Rectangle());
            case CLASSIC:
                return new Boat(150, 7, 70, 150, 7, new Rectangle());
            default:
                throw new IllegalArgumentException("Tipo de barco no válido");
        }
    }
}
