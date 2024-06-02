package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.util.Config;
import com.mygdx.game.util.KeyBindings;

import javax.swing.event.DocumentEvent;

import java.awt.event.InputMethodListener;
import java.util.HashSet;
import java.util.Set;

public class InputManager implements InputProcessor {
    private Set<InputSubscribed> listeners;
    private final KeyBindings keyBindings;
    public InputManager(){
        this.listeners = new HashSet<>();
        this.keyBindings = Config.keyBinds;
    }

    /**
     * Subscriber method to keep track of the objects that want to be notified of the input events
     * @param listener Object that wants to be notified of the input events (in our case, the PlayerControlledStrategy class)
     */
    public void addSubscriber(InputSubscribed listener){
        listeners.add(listener);
    }

    /**
     * Method to unsubscribe an object from the listener list.
     * This method should be seldon use in our game.
     * @param listener
     */
    public void removeSubscriber(InputSubscribed  listener){
        listeners.remove(listener);
    }
    private void notifyListeners(boolean press, int keycode){
        for (InputSubscribed listener : listeners) {
            listener.listen(press, keyBindings.getActionForKey(keycode));
        }
    }
    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("Input","Key pressed");
        notifyListeners(true, keycode);
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
        Gdx.app.log("Input","Key released");
        notifyListeners(false, keycode);
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
