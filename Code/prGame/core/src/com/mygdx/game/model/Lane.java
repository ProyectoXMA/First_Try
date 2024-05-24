package com.mygdx.game.model;

import com.mygdx.game.model.movement.MoveObstacleVisitor;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class represents a lane in the game.
 * It contains all the objects that are present in the lane (obstacles, power-ups, boats).
 * It also stores a reference to the assigned boat, and a List of all boats present in the lane, used to subtract health to invaders boats.
 */
public class Lane {
    private final int laneId;
    private Set<Obstacle> obstacles;
    private Set<PowerUp> powerUps;
    private final Boat boat; //Boat asigned to this lane
    private List<Boat> boats; //All boats present in this lane (including the boat assigned)

    public Lane(int laneId, Set<Obstacle> obstacles, Set<PowerUp> powerUps, Boat boat) {
        this.laneId = laneId;
        this.obstacles = obstacles;
        this.powerUps = powerUps;
        this.boat = boat;
        this.boats = new LinkedList<>();
        boats.add(boat);
    }

    /**
     * This method should be called by the race class, that should handle a pool of obstacles to share among all lanes.
     * @param obstacle the obstacle to add to the lane
     */
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * This method should be called by the race class, that should handle a pool of power-ups to share among all lanes.
     * @param powerUp the power-up to add to the lane
     */
    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }

    /**
     * This method should be called by the race class, that should handle a pool of boats to share among all lanes.
     * @param obstacle the obstacle to be removed form the obstacle list
     * @return the obstacle that was removed to be handled by the race class
     */
    public Obstacle removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);
        return obstacle;
    }

    /**
     * This method should be called by the race class, that should handle a pool of power-ups to share among all lanes.
     * @param powerUp the power-up to be removed form the power-up list
     * @return the power-up that was removed to be handled by the race class
     */
    public PowerUp removePowerUp(PowerUp powerUp) {
        powerUps.remove(powerUp);
        return powerUp;
    }

    /**
     * This method iterates over all boats to check for collisions.
     * The handler is responsible for checking if a collision has occurred and handling it.
     */
    public void applyCollisions() {
        CollisionHandler handler = new CollisionHandler();
        for (Boat boat : boats) {
            handler.setBoat(boat);
            searchCollisions(handler);
        }
    }

    /**
     * This method iterates over all obstacles and power-ups to be visited by the handler.
     * @param handler the visitor responsible for handling the collisions for its boat. Should be accepted by all Collidable objects.
     */
    private void searchCollisions(CollisionHandler handler) {
        for (Obstacle obstacle : obstacles) {
            handler.visitObstacle(obstacle);
        }
        for (PowerUp powerUp : powerUps) {
            handler.visitPowerUp(powerUp);
        }
        for(Boat otherBoat : boats) {
            if(handler.getBoat().equals(otherBoat)) continue;
            handler.visitBoat(otherBoat);
        }
    }

    public void update(float delta) {
        MoveObstacleVisitor moveVisitor = new MoveObstacleVisitor(delta);
        for (Obstacle obstacle : obstacles) {
            obstacle.accept(moveVisitor);
        }
    }
}
