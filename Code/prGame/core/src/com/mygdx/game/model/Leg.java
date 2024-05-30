package com.mygdx.game.model;

import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.movement.PlayerControlledStrategy;
import com.mygdx.game.model.obstacles.*;
import com.mygdx.game.model.powerUps.PowerUp;

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

    public Leg(int level, Player player){
        this.level = level;
        this.lanes = new ArrayList<>(NUMBER_OF_LANES);
        this.unusedDucks = new HashSet<>();
        this.unusedLogs = new HashSet<>();
        this.unusedStones = new HashSet<>();

        //TODO: A bit harcoded this...
        float centerOfLane = (float) ((PLAYER_LANE + 0.5) * Lane.WIDTH);
        Boat playerBoat = Boat.createBoat(player.getBoatType(), centerOfLane-Boat.WIDTH, 0);
        playerBoat.setMovementStrategy(new PlayerControlledStrategy());

        for (int i = 0; i < NUMBER_OF_LANES; i++) {
            centerOfLane = (float) ((i + 0.5) * Lane.WIDTH);
            if(i == PLAYER_LANE)
                lanes.add(new Lane(i, new HashSet<>(), new HashSet<>(), playerBoat));
            else
                lanes.add(new Lane(i, new HashSet<>(), new HashSet<>(), Boat.createBoat(BoatType.getRandomType(), centerOfLane-Boat.WIDTH, 0)));
        }
    }
    public List<Lane> getLanes() {
        return lanes;
    }

    /**
     * This method updates the state of the Leg as the model
     * It should be called by the controller in each frame.
     * @param delta the time elapsed since the last update
     */
    public void update(float delta){
        for (Lane lane : lanes) {
            update(lane, delta);
        }
    }

    /**
     * This method updates the state of a lane based on the time elapsed.
     * It moves the obtacles nad boats, applies collisions and handles lane transitions from one lane to another.
     * @param lane the lane to update
     * @param delta the time elapsed since the last update
     */
    private void update(Lane lane, float delta){
        lane.moveBoats(delta);
        lane.moveObstacles(delta);
        lane.applyCollisions();
        handleLaneTransitions(lane);
    }

    /**
     * This method handles the transitions of GameObjects between lanes.
     * If an object invades partially another lane, it will be added to the new lane and considered as part of both.
     * If it invades totally another lane, it will be removed from the previous lane.
     * Only newly invaders will be considered (for total invaders this is straight forward, as they are removed.)
     * If an object invades partially the bounds of the screen, it will be removed from the game.
     * @param lane the lane in which to handle the transitions
     */
    private void handleLaneTransitions(Lane lane) {
        Set<GameObject> totalOutOfBounds = lane.getTotallyOutBounds();
        for(GameObject gameObject : totalOutOfBounds){
            lane.removeGameObject(gameObject);
        }
        Set<GameObject> newPartialOutOfBounds = lane.getNewPartiallyOutBounds();
        for(GameObject gameObject : newPartialOutOfBounds){
            Lane newLane = determineNewLaneByPosition(gameObject, lane);
            if (newLane == null) {
                lane.removeGameObject(gameObject);
                gameObject.destroy();
            } else {
                newLane.addGameObject(gameObject);
            }
        }
    }

    /**
     * This method determines the lane to which a GameObject should be added based on its position.
     * Only partially out of bounds objects will be passed.
     * If the new lane is an invalid lane, the object will be destroyed and removed from the game.
     * @param gameObject the object to determine the new lane
     * @param previousLane the id of the lane where the object was before
     * @return the lane to be added if it is valid, null otherwise
     */
    private Lane determineNewLaneByPosition(GameObject gameObject, Lane previousLane){
        float leftCorner = gameObject.getX();
        //The object can only be partially out of bounds, and will have to be added to one of the lanes at the sides
        if(leftCorner < previousLane.getLanePosition())
            //Its the left lane. If we reach the end of the screen return null
            return previousLane.getLaneId() == 0 ? null : lanes.get(previousLane.getLaneId() - 1);
        else
            //Its the right lane. If we reach the end of the screen return null
            return previousLane.getLaneId() == NUMBER_OF_LANES - 1 ? null : lanes.get(previousLane.getLaneId() + 1);
    }
}
