package com.mygdx.game.util;

/**
 * The Config class holds the game configuration constants.
 * It uses the LibGDX Gdx.graphics to get the width and height of the application window.
 */
public class Config {
    public static final int FPS = 30; //NFR002

    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;
    public static KeyBindings keyBinds;

    public static boolean muted = false;
    public static float BoatRelativeSize = 0.025f * Config.WIDTH;
    public static float StoneRelativeSize = 0.025f * Config.WIDTH;
    public static float LogRelativeSize = 0.03f * Config.WIDTH;
    public static float DuckRelativeSize = 0.01f * Config.WIDTH;
    public static float PowerUpRelativeSize = 1.5f * Config.WIDTH;

    public static float LegBorderRelativeWidth = 0.173f * Config.WIDTH;
    public static float LaneRelativeWidth = 0.163f * Config.WIDTH;
    public static float LaneRelativeHeight = 4f * Config.HEIGHT;

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