package com.mygdx.game.model.movement;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.controller.InputSubscribed;
import com.mygdx.game.util.UserAction;

/**
 * Observer pattern: This class will subscribe to the event manager and will be notified by it.
 */
public class PlayerControlledStrategy implements MovementStrategy, InputSubscribed {
    private UserAction nextAction;
    @Override
    public void move(Movable movable, float delta) {
        if (nextAction == null) {
            return;
        }
        float distance = movable.getSpeed() * delta;;
        switch (nextAction) {
            case MOVE_LEFT:
                movable.adjustX(-distance);
                Gdx.app.log("Input","Moving to the left");
                break;
            case MOVE_RIGHT:
                movable.adjustX(distance);
                Gdx.app.log("Input","Moving to the right");
                break;
        }
    }
    @Override
    public void listen(boolean press, UserAction keycode) {
        nextAction = press? keycode : null;
    }
}
