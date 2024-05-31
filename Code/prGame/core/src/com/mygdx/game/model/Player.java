package com.mygdx.game.model;

import com.mygdx.game.controller.InputSubscribed;
import com.mygdx.game.model.movement.MovementStrategy;
import com.mygdx.game.model.movement.PlayerControlledStrategy;
import com.mygdx.game.util.UserAction;

/**
 * Player class will store objects important for the player thoughout the game. As this objects will be controlled by the player,
 * this class will be notified by the corresponding inputManager when the user sends and input. The player will then notify its objects.
 */
public class Player implements InputSubscribed {
    private static final BoatType DEFAULT_BOAT_TYPE = BoatType.CLASSIC;
    private Boat boat;
    public Player(BoatType boatType) {
        boat = Boat.createBoat(boatType);
        setBoat(boat);
    }
    public Player(){
        this(DEFAULT_BOAT_TYPE);
    }
    public void setBoat(Boat boat) {
        MovementStrategy playerControlledStrategy = new PlayerControlledStrategy();
        boat.setMovementStrategy(playerControlledStrategy);
        this.boat = boat;
    }
    public Boat getBoat() {
        return boat;
    }

    @Override
    public void listen(boolean press, UserAction keycode) {
        ((PlayerControlledStrategy) boat.getMovementStrategy()).listen(press, keycode);
    }
}
