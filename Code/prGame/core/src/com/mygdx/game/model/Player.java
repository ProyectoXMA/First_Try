package com.mygdx.game.model;

import com.mygdx.game.model.movement.PlayerControlledStrategy;

public class Player {
    private BoatType boatType;
    private static final BoatType DEFAULT_BOAT_TYPE = BoatType.CLASSIC;
    public Player(BoatType boatType) {
        this.boatType = boatType;
    }
    public Player(){
        this.boatType = DEFAULT_BOAT_TYPE;
    }
    public BoatType getBoatType() {
        return boatType;
    }
    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }

}
