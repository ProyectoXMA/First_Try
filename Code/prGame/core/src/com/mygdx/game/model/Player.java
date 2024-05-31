package com.mygdx.game.model;

import com.mygdx.game.model.movement.PlayerControlledStrategy;

public class Player {
    private static final BoatType DEFAULT_BOAT_TYPE = BoatType.CLASSIC;
    private BoatType boatType;
    private Boat boat;
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
    public void setBoat(Boat boat) {
        this.boat = boat;
    }
    public Boat getBoat() {
        return boat;
    }

}
