package com.mygdx.game.model;

import com.mygdx.game.controller.InputSubscribed;
import com.mygdx.game.model.movement.MovementStrategy;
import com.mygdx.game.model.movement.PlayerControlledStrategy;
import com.mygdx.game.util.UserAction;

/**
 * Player class will store objects important for the player along the game. As this objects will be controlled by the player,
 * this class will be notified by the corresponding inputManager when the user sends and input. The player will then notify its objects.
 */
public class Player implements InputSubscribed {
    public static final BoatType DEFAULT_BOAT_TYPE = BoatType.CLASSIC;
    private Boat boat;
    public Player(BoatType boatType) {
        boat = Boat.createBoat(boatType);
        setBoat(boat);
    }
    public Player(){
        this(DEFAULT_BOAT_TYPE);
    }
    public void reset(){
        BoatType type = boat.getType();
        boat = Boat.createBoat(type);
        setBoat(boat);
    }

    /**
     * This method will set the boat of the player. As the playerBoat needs to be controlled by the player,
     * the movement strategy of the boat will be set to PlayerControlledStrategy.
     * @param boat the boat that will be assigned to the player (no movement strategy assigned yet)
     */
    public void setBoat(Boat boat) {
        MovementStrategy playerControlledStrategy = new PlayerControlledStrategy();
        boat.setMovementStrategy(playerControlledStrategy);
        this.boat = boat;
    }
    public Boat getBoat() {
        return boat;
    }

    /**
     * The Player is subscribed to the inputManager, so it will be notified by it when the user sends an input.
     * Then the player will redirect the input to the PlayerControlledStrategy of the boat.
     * @param press whether the key is pressed or released
     * @param action the action that the user has triggered (LEFT,RIGHT,UP,DOWN,ENTER,ESC)
     */
    @Override
    public void listen(boolean press, UserAction action) {
        //Check if the action is a movement action, if so, notify the boat
        if (action == UserAction.MOVE_LEFT || action == UserAction.MOVE_RIGHT)
            ((PlayerControlledStrategy) boat.getMovementStrategy()).listen(press, action);
    }
}
