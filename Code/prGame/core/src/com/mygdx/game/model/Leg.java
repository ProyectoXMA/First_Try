package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.movement.AIControlledStrategy;
import com.mygdx.game.model.movement.MoveObstacleVisitor;
import com.mygdx.game.model.obstacles.*;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.model.powerUps.PowerUpType;
import com.mygdx.game.util.Config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents the control logic of each Leg.
 * It should handle a pool of obstacles, power-ups and boats to share among all lanes, that store their specific objects.
 */
public class Leg {
    public static final int NUMBER_OF_LANES = 4;
    public static final int PLAYER_LANE = 1;

    public static float BORDER_WIDTH = Config.LegBorderRelativeWidth;
    private Player player;

    private int level;
    private List<Lane> lanes;
    private Set<Obstacle> allObstacles;
    private Set<PowerUp> allPowerUps;
    private Set<Boat> allBoats;

    /**
     * The constructor of the Leg class.
     * It generates the obstacles, powerUps and boats for each lane, and passes them to the lanes upon creation.
     * First it generates all the obstacles and powerUps, and then iteratively creates the lanes and their corresponding boat.
     * The player's boat is stored in the Player class, while the other boats have to be created with the static factory method.
     * To split the gameObjects evenly, we make use of the java streams. We create a stream from the whole set, skip the objects
     * assigned to previous lanes and get the first n objects for the current lane.
     * Performance is not an issue as streams are lazy.
     * @param level level of the game
     * @param player the player object that stores the player's boat
     */
    public Leg(int level, Player player){
        this.level = level;
        this.lanes = new ArrayList<>(NUMBER_OF_LANES);
        this.allObstacles = createObstacles(Lane.NUMBER_OBSTACLES * NUMBER_OF_LANES * level);
        this.allPowerUps = createPowerUps(Lane.NUMBER_POWERUPS * NUMBER_OF_LANES * level);
        this.allBoats = new HashSet<>();
        this.player = player;
        for (int i = 0; i < NUMBER_OF_LANES; i++) {
            Boat laneBoat = i == PLAYER_LANE ? player.getBoat() : Boat.createBoat(BoatType.getRandomType());
            allBoats.add(laneBoat);
            Gdx.app.debug("Leg", "Boats created: " + allBoats.size());
            Lane newLane = Lane.createLane(i,
                    allObstacles.stream()
                            .skip(i * Lane.NUMBER_OBSTACLES * level) //Skip the obstacles of the previous lanes
                            .limit(Lane.NUMBER_OBSTACLES * level) //Get the number of needed obstacles for this lane
                            .collect(Collectors.toSet()), //Convert the stream into a set
                    allPowerUps.stream()
                            .skip(i * Lane.NUMBER_POWERUPS* level) //Skip the powerUps of the previous lanes
                            .limit(Lane.NUMBER_POWERUPS * level) //Get the number of needed powerUps for this lane
                            .collect(Collectors.toSet()), //Convert the stream into a set
                    laneBoat);
            lanes.add(newLane);
            if(i != PLAYER_LANE) laneBoat.setMovementStrategy(new AIControlledStrategy(newLane, level));
            //If the lane is the player's lane, its movement strategy has been assigned by the Player class
        }
    }

    /**
     * This method creates a set of obstacles of a given size.
     * It uses the static factory method provided by the Obstacle class to create the obstacles.
     * @param numObstacles the number of obstacles to create
     * @return a set of obstacles
     */
    private Set<Obstacle> createObstacles(int numObstacles){
        Set<Obstacle> obstacles = new HashSet<>();
        for (int i = 0; i < numObstacles; i++) {
            obstacles.add(Obstacle.createObstacle(ObstacleType.getRandomType()));
        }
        return obstacles;
    }

    /**
     * This method creates a set of powerUps of a given size.
     * It uses the static factory method provided by the PowerUp class to create the powerUps.
     * @param numPowerUps the number of powerUps to create
     * @return a set of powerUps
     */
    private Set<PowerUp> createPowerUps(int numPowerUps){
        Set<PowerUp> powerUps = new HashSet<>();
        for (int i = 0; i < numPowerUps; i++) {
            powerUps.add(PowerUp.createPowerUp(PowerUpType.getRandomType()));
        }
        return powerUps;
    }
    public List<Lane> getLanes() {
        return lanes;
    }

    /**
     * This method updates the state of the Leg as the model:
     * It moves the boats and obstacles, applies collisions and handles lane transitions.
     * It should be called by the controller in each frame.
     * @param delta the time elapsed since the last update
     */
    public void update(float delta){
        moveBoats(delta);
        moveObstacles(delta);
        for (Lane lane : lanes) {
            lane.applyCollisions();
            handleLaneTransitions(lane);
            lane.penalizeInvaders(delta);
        }
    }

    /**
     * This method iterates over all obstacles in the lane and sends them a visitor to move them.
     * When each obstacle accepts the visitor, the visitor will move the obstacle.
     * @param delta time elapsed since last frame
     */
    public void moveObstacles(float delta) {
        MoveObstacleVisitor moveVisitor = new MoveObstacleVisitor(delta);
        allObstacles.stream().peek(obstacle -> Gdx.app.debug("Obstacle", obstacle.toString())).forEach(obstacle -> obstacle.accept(moveVisitor));
    }

    /**
     * This method iterates over all boats in the lane and call their move method.
     * @param delta time elapsed since last frame
     */
    public void moveBoats(float delta) {
        allBoats.forEach(boat -> boat.move(delta));
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
            Gdx.app.debug("Leg", "Object " + gameObject.getClass() + " totally out of bounds");
            lane.removeGameObject(gameObject);
        }
        Set<GameObject> newPartialOutOfBounds = lane.getNewPartiallyOutBounds();
        for(GameObject gameObject : newPartialOutOfBounds){
            Lane newLane = determineNewLaneByPosition(gameObject, lane);
            //If the lane to which the object should be added is a limit one, it is removed
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

    public boolean hasReachedGoal() {
        return player.getBoat().getY() + player.getBoat().getHeight() >= Lane.HEIGHT;
    }

    public int getLevel() {
        return level;
    }
}
