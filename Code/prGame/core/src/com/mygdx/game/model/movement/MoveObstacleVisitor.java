package com.mygdx.game.model.movement;

import com.mygdx.game.model.obstacles.Duck;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.obstacles.ObstacleVisitor;
import com.mygdx.game.model.obstacles.Stone;

/**
 * This class is a visitor that moves the obstacle it visits.
 * It implements the Visitor pattern, it visits all Obstacle objects (Log, Stone, Duck).
 * As it delegates the movement itself to the Movement Strategy, maybe it can be refactored.
 */
public class MoveObstacleVisitor implements ObstacleVisitor {
    private float delta;
    public MoveObstacleVisitor(float delta) {
        this.delta = delta;
    }
    @Override
    public void visitLog(Log log) {

    }

    @Override
    public void visitStone(Stone stone) {
    }

    @Override
    public void visitDuck(Duck duck) {
        duck.getMovementStrategy().move(duck, delta);
    }
}
