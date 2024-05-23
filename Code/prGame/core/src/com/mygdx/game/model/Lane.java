package com.mygdx.game.model;

import com.mygdx.game.model.movement.MoveVisitor;
import com.mygdx.game.model.obstacles.Obstacle;

import java.util.Set;

public class Lane {
    private Set<Obstacle> obstacles;

    public void update(float delta) {
        for (Obstacle obstacle : obstacles) {
            obstacle.accept(new MoveVisitor(delta));
        }
    }
}
