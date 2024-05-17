package com.mygdx.game.model;

import com.mygdx.game.model.movement.MoveObstacleVisitor;
import com.mygdx.game.model.obstacles.Obstacle;

import java.util.Set;

public class Lane {
    private Set<Obstacle> obstacles;

    public void update(float delta) {
        MoveObstacleVisitor moveVisitor = new MoveObstacleVisitor(delta);
        for (Obstacle obstacle : obstacles) {
            obstacle.accept(moveVisitor);
        }
    }
}
