package com.mygdx.game.model.movement;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.controller.InputSubscribed;
import com.mygdx.game.model.Boat;
import com.mygdx.game.util.UserAction;

/**
 * Observer pattern: This class will subscribe to the event manager and will be notified by it.
 * This class is not directly conected to the inputManager, but will be notified by the Player class
 * about the events that the user has triggered (the Player class stores a reference to the Boat of the Player).
 */
public class PlayerControlledStrategy implements MovementStrategy, InputSubscribed {
    private UserAction nextAction;

    /**
     * The movable will call this method to move itself, passing its instance to the strategy.
     * @param movable the object that will be moved and calls this method
     * @param delta the time elapsed since the last update
     */
    @Override
    public void move(Movable movable, float delta) {
        //TODO: I dont think we will have more PlayerControlled objects, but if we do, this should be refactored.
        //The compute the lateral and vertical speed of the movable object. In case of boat, horizontal is the handling.
        float horizontalSpeed = movable instanceof Boat ? ((Boat) movable).getHandling() : movable.getSpeed();
        //In any case, the vertical speed is the speed of the object.
        float verticalSpeed = movable.getSpeed();
        switch (nextAction) {
            case MOVE_LEFT:
                movable.adjustX(-horizontalSpeed * delta);
                movable.adjustY(verticalSpeed * delta);
                Gdx.app.log("Input","Moving to the left");
                break;
            case MOVE_RIGHT:
                movable.adjustX(horizontalSpeed * delta);
                movable.adjustY(verticalSpeed * delta);
                Gdx.app.log("Input","Moving to the right");
                break;
            default:
                //In case no user action is detected, the object will continue moving in the same direction (upwards).
                movable.adjustY(verticalSpeed * delta);
                break;
        }
    }

    /**
     * This method will be called by the Player class when the user sends an input.
     * @param press whether the key is pressed or released
     * @param action the action that the user has triggered (LEFT,RIGHT,UP,DOWN,ENTER,ESC)
     */
    @Override
    public void listen(boolean press, UserAction action) {
        nextAction = press? action : null;
    }
}
