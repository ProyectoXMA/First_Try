package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class ChooseBoatModel implements Disposable {
    private final Texture[] boatTextures;
    private int actualBoat;

    public ChooseBoatModel() {
        actualBoat = 0;
        boatTextures = new Texture[4];
        for (int i = 0; i < 4; i++) {
            boatTextures[i] = new Texture("boatMenu" + i + ".png");
        }
    }

    public Texture getCurrentBoatTexture() {
        return boatTextures[actualBoat];
    }

    public void nextBoat() {
        actualBoat++;
        if (actualBoat >= boatTextures.length) {
            actualBoat = 0;
        }
    }

    public void previousBoat() {
        actualBoat--;
        if (actualBoat < 0) {
            actualBoat = boatTextures.length - 1;
        }
    }

    @Override
    public void dispose() {
        for (Texture texture : boatTextures) {
            texture.dispose();
        }
    }
}
