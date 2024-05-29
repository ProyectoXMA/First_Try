package com.mygdx.game.model;

import com.mygdx.game.model.movement.MoveObstacleVisitor;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * This class represents a lane in the game.
 * It contains all the objects that are present in the lane (obstacles, power-ups, boats).
 * It also stores a reference to the assigned boat, and a List of all boats present in the lane, used to subtract health to invaders boats.
 */
public class Lane {
    private static final int WIDTH = 800;
    private final int laneId;
    private final float lanePosition; //Horizontal position of the lane from the left side of the screen to the left side of the lane
    private final Boat boat; //Boat asigned to this lane
    private Set<Obstacle> obstacles;
    private Set<PowerUp> powerUps;
    private Set<Boat> boats; //All boats present in this lane (including the boat assigned)
    private Set<GameObject> partiallyOutBounds; //Objects that are partially out of bounds of the lane but continue to be part of it

    public Lane(int laneId, Set<Obstacle> obstacles, Set<PowerUp> powerUps, Boat boat) {
        this.laneId = laneId;
        this.lanePosition = laneId * WIDTH;
        this.obstacles = obstacles;
        this.powerUps = powerUps;
        this.boat = boat;
        this.boats = new HashSet<>();
        boats.add(boat);
        this.partiallyOutBounds = new HashSet<>();
    }

    public int getLaneId() {
        return laneId;
    }
    public float getLanePosition() {
        return lanePosition;
    }
    public float getWidth() {
        return WIDTH;
    }
    public Set<Boat> getBoats() {
        return boats;
    }
    public Set<Obstacle> getObstacles() {
        return obstacles;
    }
    public Set<PowerUp> getPowerUps() {
        return powerUps;
    }

    /**
     * This method adds a GameObject to the lane. It is called by the Leg class.
     * It uses specific private methods to add the object to the corresponding Set.
     * @param gameObject the object to add to the lane.
     */
    public void addGameObject(GameObject gameObject) {
        //TODO: This can be improved, maybe Visitor pattern?
        if(gameObject instanceof Obstacle) {
            addObstacle((Obstacle) gameObject);
        } else if(gameObject instanceof PowerUp) {
            addPowerUp((PowerUp) gameObject);
        } else if(gameObject instanceof Boat) {
            addBoat((Boat) gameObject);
        }
    }

    /**
     * This method removes a GameObject from the lane. It is called by the Leg class.
     * It uses specific private methods to remove the object from the corresponding set.
     * @param gameObject the object to remove from the lane.
     */
    public void removeGameObject(GameObject gameObject) {
        //TODO: This can be improved, maybe Visitor pattern?
        if(gameObject instanceof Obstacle) {
            removeObstacle((Obstacle) gameObject);
        } else if(gameObject instanceof PowerUp) {
            removePowerUp((PowerUp) gameObject);
        } else if(gameObject instanceof Boat) {
            removeBoat((Boat) gameObject);
        }
    }
    private void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
    private void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }
    private void addBoat(Boat boat) {
        boats.add(boat);
    }
    private void removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);
        partiallyOutBounds.remove(obstacle);
    }
    private void removePowerUp(PowerUp powerUp) {
        powerUps.remove(powerUp);
        partiallyOutBounds.remove(powerUp);
    }
    private void removeBoat(Boat boat) {
        boats.remove(boat);
        partiallyOutBounds.remove(boat);
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
    public void moveObstacles(float delta) {
        MoveObstacleVisitor moveVisitor = new MoveObstacleVisitor(delta);
        for (Obstacle obstacle : obstacles) {
            obstacle.accept(moveVisitor);
        }
    }
    public void moveBoats(float delta) {
        for (Boat boat : boats) {
            boat.move(delta);
        }
    }

    /**
     * This method checks if any object is totally out of bounds and returns a set with all the objects that are.
     * The idea is that the Leg class removes the completely out-of-bounds objects from the lane.
     * @return a set with all the objects that are totally out of bounds
     */
    public Set<GameObject> getTotallyOutBounds() {
        Set<GameObject> outBounds = new HashSet<>();
        for(Obstacle obstacle : obstacles) {
            totallyOutBounds(obstacle, outBounds);
        }
        for(PowerUp powerUp : powerUps) {
            totallyOutBounds(powerUp, outBounds);
        }
        for(Boat boat : boats) {
            totallyOutBounds(boat, outBounds);
        }
        return outBounds;
    }

    /**
     * This method checks if an object is totally out of bounds and adds it to the outBounds set if it is.
     * It also removes the object from the partially out of bounds set, as it is managed by the Lane and not the Leg.
     * @param object the object to check if it is out of bounds
     * @param outBounds the set to add the object if it is out of bounds
     */
    private void totallyOutBounds(GameObject object, Set<GameObject> outBounds) {
        if (object.getX() > lanePosition + WIDTH || (object.getX() + object.getWidth()) < lanePosition) {
            outBounds.add(object);
            partiallyOutBounds.remove(object);
        }
    }

    /**
     * This method checks if any object is partially out of bounds and returns a set with all the objects that are.
     * @return a set with all the objects that are partially out of bounds
     */
    public Set<GameObject> getPartiallyOutBounds() {
        partiallyOutBounds.addAll(getNewPartiallyOutBounds());
        return partiallyOutBounds;
    }

    /**
     * This method returns only the objects that have just become partially out of bounds to be added to other lanes.
     * @return a set with all the new objects that are partially out of bounds
     */
    public Set<GameObject> getNewPartiallyOutBounds() {
        Set<GameObject> newOutBounds = new HashSet<>();
        for(Obstacle obstacle : obstacles) {
            partiallyOutBounds(obstacle, newOutBounds);
        }
        for(PowerUp powerUp : powerUps) {
            partiallyOutBounds(powerUp, newOutBounds);
        }
        for(Boat boat : boats) {
            partiallyOutBounds(boat, newOutBounds);
        }
        //TODO: This is not very efficient(not the worse either), think of another way
        newOutBounds.removeAll(partiallyOutBounds);
        return newOutBounds;
    }
    private void partiallyOutBounds(GameObject object, Set<GameObject> outBounds) {
        if (object.getX() + object.getWidth() > lanePosition + WIDTH || object.getX() < lanePosition) {
            outBounds.add(object);
        }
    }
}
