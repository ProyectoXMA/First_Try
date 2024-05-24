package com.mygdx.game.model;

import com.badlogic.gdx.graphics.TextureData;
import com.mygdx.game.model.obstacles.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents the control logic of each Leg.
 * It should handle a pool of obstacles, power-ups and boats to share among all lanes, that store their specific objects.
 */
public class Leg {
    private static final int NUMBER_OF_LANES = 4;
    private static final int PLAYER_LANE = 0;

    private int level;
    private List<Lane> lanes;
    private Set<Duck> unusedDucks;
    private Set<Log> unusedLogs;
    private Set<Stone> unusedStones;

    public Leg(int level){
        this.level = level;
        this.lanes = new ArrayList<>(NUMBER_OF_LANES);
        this.unusedDucks = new HashSet<>();
        this.unusedLogs = new HashSet<>();
        this.unusedStones = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_LANES; i++) {
            lanes.add(new Lane(i, new HashSet<>(), new HashSet<>(), BoatFactory.createBoat(BoatType.getRandomType())));
        }
    }

}
