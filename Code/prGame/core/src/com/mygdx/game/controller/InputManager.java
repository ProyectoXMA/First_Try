package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("Input","Key pressed");
        if (keycode == Input.Keys.LEFT) {
            // Handle left key press
            // Update model and view accordingly
            Gdx.app.log("Input","Left key pressed");
        } else if (keycode == Input.Keys.RIGHT) {
            // Handle right key press
            Gdx.app.log("Input","Right key pressed");
        }
        // handle other keys
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            // Handle left key release
            Gdx.app.log("Input","Left key released");
        } else if (keycode == Input.Keys.RIGHT) {
            // Handle right key release
            Gdx.app.log("Input","Right key released");
        }
        // handle other keys
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
