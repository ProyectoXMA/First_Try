package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;

/**
 * The Config class holds the game configuration constants.
 * It uses the LibGDX Gdx.graphics to get the width and height of the application window.
 */
public class Config {
    /**
     * The WIDTH constant is set to the current width of the application window at the time of class loading.
     */
    public static final int WIDTH = Gdx.graphics.getWidth();

    /**
     * The HEIGHT constant is set to the current height of the application window at the time of class loading.
     */
    public static final int HEIGHT = Gdx.graphics.getHeight();
}