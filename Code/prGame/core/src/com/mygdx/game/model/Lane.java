package com.mygdx.game.model;

import com.mygdx.game.model.movement.MoveObstacleVisitor;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;


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

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }
    public Obstacle removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);
        return obstacle;
    }
    public PowerUp removePowerUp(PowerUp powerUp) {
        powerUps.remove(powerUp);
        return powerUp;
    }
    public void applyCollisions() {
        CollisionHandler handler = new CollisionHandler();
        for (Boat boat : boats) {
            handler.setBoat(boat);
            searchCollisions(handler);
        }
    }
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
