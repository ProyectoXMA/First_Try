package com.mygdx.game.util;

import com.badlogic.gdx.Input;

import java.util.HashMap;
import java.util.Map;

public class KeyBindings {
    private Map<UserAction, Integer> keyBindings;
    public KeyBindings() {
        keyBindings = new HashMap<>();
        setDefaultBindings();
    }
    private void setDefaultBindings() {
        keyBindings.put(UserAction.MOVE_LEFT, Input.Keys.LEFT);
        keyBindings.put(UserAction.MOVE_RIGHT, Input.Keys.RIGHT);
        keyBindings.put(UserAction.MOVE_UP, Input.Keys.UP);
        keyBindings.put(UserAction.MOVE_DOWN, Input.Keys.DOWN);
        keyBindings.put(UserAction.ESCAPE, Input.Keys.ESCAPE);
        keyBindings.put(UserAction.ENTER, Input.Keys.ENTER);
        // Set default bindings for other actions
    }
    public void setKeyBinding(UserAction action, int key) {
        keyBindings.put(action, key);
    }
    public int getKeyForAction(UserAction action) {
        return keyBindings.getOrDefault(action, -1);
    }
    public UserAction getActionForKey(int key) {
        for (Map.Entry<UserAction, Integer> entry : keyBindings.entrySet()) {
            if (entry.getValue() == key) {
                return entry.getKey();
            }
        }
        return null;
    }
}
