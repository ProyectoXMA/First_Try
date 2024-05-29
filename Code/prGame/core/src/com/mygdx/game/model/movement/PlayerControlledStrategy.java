package com.mygdx.game.model.movement;

import com.mygdx.game.controller.InputSubscribed;
import com.mygdx.game.util.UserAction;

/**
 * Observer pattern: This class will subscribe to the event manager and will be notified by it.
 */
public class PlayerControlledStrategy implements MovementStrategy, InputSubscribed {
    private UserAction nextAction;
    @Override
    public void move(Movable movable, float delta) {
        //TODO

    }

    @Override
    public void listen(boolean press, UserAction keycode) {
        nextAction = press? keycode : null;
    }
}
