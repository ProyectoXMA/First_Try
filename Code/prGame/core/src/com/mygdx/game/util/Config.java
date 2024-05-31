package com.mygdx.game.util;

/**
 * The Config class holds the game configuration constants.
 * It uses the LibGDX Gdx.graphics to get the width and height of the application window.
 */
public class Config {

    /**
     * The WIDTH constant is set to the current width of the application window at the time of class loading.
     */
    private static int WIDTH = 800;

    /**
     * The HEIGHT constant is set to the current height of the application window at the time of class loading.
     */
    private static int HEIGHT = 600;

    public Config(int width, int height) {

    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static void setWidth(int width) {
        WIDTH = width;
    }

    public static void setHeight(int height) {
        HEIGHT = height;
    }
}