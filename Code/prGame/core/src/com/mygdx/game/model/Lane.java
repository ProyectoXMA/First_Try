package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.movement.MoveObstacleVisitor;
import com.mygdx.game.model.obstacles.Duck;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.obstacles.Stone;
import com.mygdx.game.model.powerUps.HealthBoost;
import com.mygdx.game.model.powerUps.Invincibility;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.model.powerUps.SpeedBoost;
import com.mygdx.game.util.Config;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * This class represents a lane in the game.
 * It contains all the objects that are present in the lane (obstacles, power-ups, boats).
 * It also stores a reference to the assigned boat, and a List of all boats present in the lane, used to subtract health to invaders boats.
 */
public class Lane {
    private static final Random RND = new Random();
    public static final int WIDTH = Config.getWidth()/4;
    public static final int HEIGHT = Config.getHeight()*3;
    public static final int NUMBER_OBSTACLES = 40;
    public static final int NUMBER_POWERUPS = 5;

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
        setCentralPosition(boat);
        this.partiallyOutBounds = new HashSet<>();
    }
    public static Lane createLane(int laneId, Set<Obstacle> obstacles, Set<PowerUp> powerUps, Boat boat) {
        obstacles.forEach(obstacle -> setRandomPosition(obstacle, laneId));
        powerUps.forEach(powerUp -> setRandomPosition(powerUp, laneId));
        return new Lane(laneId, obstacles, powerUps, boat);
    }
    private void setCentralPosition(GameObject object){
        object.setPosition(lanePosition + (float) WIDTH / 2 - boat.getWidth() / 2, 0);
    }
    private static void setRandomPosition(GameObject object, int laneId){
        object.setPosition(Lane.WIDTH * laneId + RND.nextInt(Lane.WIDTH), RND.nextInt(Lane.HEIGHT));
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
    public Boat getBoat() {
        return boat;
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
            boat.destroy();
            removeBoat((Boat) gameObject);
        }
    }
    private void addObstacle(Obstacle obstacle) {
        Gdx.app.debug("Lane " + getLaneId(), "Obstacle added to lane " + getLaneId());
        obstacles.add(obstacle);
    }
    private void addPowerUp(PowerUp powerUp) {
        Gdx.app.debug("Lane " + getLaneId(), "PowerUp added to lane " + getLaneId());
        powerUps.add(powerUp);
    }
    private void addBoat(Boat boat) {
        Gdx.app.debug("Lane " + getLaneId(), "Boat " + boat.getType() + " added to lane " + getLaneId());
        boats.add(boat);
    }
    private void removeObstacle(Obstacle obstacle) {
        Gdx.app.debug("Lane " + getLaneId(), "Obstacle removed from lane " + getLaneId());
        obstacles.remove(obstacle);
        partiallyOutBounds.remove(obstacle);
    }
    private void removePowerUp(PowerUp powerUp) {
        Gdx.app.debug("Lane " + getLaneId(), "PowerUp removed from lane " + getLaneId());
        powerUps.remove(powerUp);
        partiallyOutBounds.remove(powerUp);
    }
    private void removeBoat(Boat boat) {
        Gdx.app.debug("Lane " + getLaneId(), "Boat " + boat.getType() + " removed from lane " + getLaneId());
        boats.remove(boat);
        partiallyOutBounds.remove(boat);
    }

    /**
     * This method iterates over all boats to check for collisions.
     * The handler is responsible for checking if a collision has occurred and handling it.
     */
    public void applyCollisions() {
        CollisionHandler handler = new CollisionHandler();
        /*for (Boat boat : boats) {
            handler.setBoat(boat); //Assigns to handler a specific boat
            if(boat.getWasHit()) continue;
            searchCollisions(handler);
        }*/
        boats.stream()
                .filter(boat -> !boat.getWasHit()) //If the boat was hit (by another boat), we don't need to check for collisions as it will be destroyed.
                //Java streams use lazy evaluation, so the filter of an element will be evaluated just after the previous element is processed.
                .forEach(boat -> {
                    handler.setBoat(boat);
                    searchCollisions(handler);
                });
        boats.stream().filter(Boat::getWasHit).forEach(boat -> System.out.println("Removing boat: " + boat));//After the handler has checked all the boats, we remove the ones that were hit
        boats.removeIf(Boat::getWasHit);
    }

    /**
     * This method iterates over all obstacles and power-ups to be visited by the handler.
     * @param handler the visitor responsible for handling the collisions for its boat. Should be accepted by all Collidable objects.
     */
    private void searchCollisions(CollisionHandler handler) { //Lane must be aware of an object´s destruction to eliminate it from the corresponding set
        /*for (Obstacle obstacle : obstacles) {
            handler.checkObstacleCollision(obstacle);
            if(obstacle.getWasHit()) obstacles.remove(obstacle); //If it was hit, we need to remove it from the obstacles set
        }*/
        obstacles.forEach(handler::checkObstacleCollision); //Passes each of the obstacles to the handler
        obstacles.removeIf(Obstacle::getWasHit); //After the handler has checked all the obstacles, we remove the ones that were hit

        /*for (PowerUp powerUp : powerUps) {
           handler.checkPowerUpCollision(powerUp);
           if(powerUp.getWasHit()) powerUps.remove(powerUp);
        }*/
        powerUps.forEach(handler::checkPowerUpCollision); //Passes each of the power-ups to the handler
        powerUps.removeIf(PowerUp::getWasHit); //After the handler has checked all the power-ups, we remove the ones that were hit
        /*for(Boat otherBoat : boats) {
            if(handler.getBoat().equals(otherBoat)) continue;
            handler.checkBoatCollision(otherBoat);
            if(otherBoat.getWasHit()) boats.remove(otherBoat);
            if(handler.getBoat().getWasHit()) boats.remove(handler.getBoat());
        }*/
        boats.stream()
                .filter(otherBoat -> !handler.getBoat().equals(otherBoat)) //Filter the boat that is not the same as the one assigned to the handler
                .peek(otherBoat -> Gdx.app.debug("Lane " + getLaneId(), "Analyzing invader boat " + otherBoat.getType()))
                .forEach(handler::checkBoatCollision); //Passes each of the boats to the handler
        //We don't yet remove the boats that were hit, as that would change the collection we are iterating over (the boats), so we remove them later.
    }

    /**
     * This method checks if any object is totally out of bounds and returns a set with all the objects that are.
     * The idea is that the Leg class removes the completely out-of-bounds objects from the lane.
     * @return a set with all the objects that are totally out of bounds
     */
    public Set<GameObject> getTotallyOutBounds() {
        Set<GameObject> outBounds = new HashSet<>();
        /*for(Obstacle obstacle : obstacles) {
            totallyOutBounds(obstacle, outBounds);
        }
        for(PowerUp powerUp : powerUps) {
            totallyOutBounds(powerUp, outBounds);
        }
        for(Boat boat : boats) {
            totallyOutBounds(boat, outBounds);
        }*/
        obstacles   .forEach(obstacle   -> totallyOutBounds(obstacle, outBounds));
        powerUps    .forEach(powerUp    -> totallyOutBounds(powerUp, outBounds));
        boats       .forEach(boat       -> totallyOutBounds(boat, outBounds));
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
     * It uses java streams first to combine all the objects in a single stream and then to efficiently filtering the
     * objects that are partially out of bounds and were not before.
     * @return a set with all the new objects that are partially out of bounds
     */
    public Set<GameObject> getNewPartiallyOutBounds() {
        //Add all partially out of bounds objects (even the ones that were already partially out of bounds)
        /*Set<GameObject> newOutBounds = new HashSet<>();
        for(Obstacle obstacle : obstacles) {
            partiallyOutBounds(obstacle, newOutBounds);
        }
        for(PowerUp powerUp : powerUps) {
            partiallyOutBounds(powerUp, newOutBounds);
        }
        for(Boat boat : boats) {
            partiallyOutBounds(boat, newOutBounds);
        }
        //This is not very efficient(not the worse either), think of another way
        //Remove the objects that were already partially out of bounds
        newOutBounds.removeAll(partiallyOutBounds);*/
        return Stream.of(
                obstacles   .stream(),
                powerUps    .stream(),
                boats       .stream()
                ).flatMap(s -> s) //Combine the streams in a single stream
                .filter(this::partiallyOutBounds) //Filter the objects that are partially out of bounds
                .filter(gameObject -> !partiallyOutBounds.contains(gameObject)) //Filter the objects that were already partially out of bounds
                .collect(Collectors.toSet()); //Collect the objects in a set
    }
    /**
     * This method checks if an object is partially out of bounds and adds it to the outBounds set if it is.
     * It compares the left and right sides of the object with the left and right sides of the lane.
     */
    private boolean partiallyOutBounds(GameObject object) {
        return (object.getX() + object.getWidth() > lanePosition + WIDTH || object.getX() < lanePosition);
    }
}
