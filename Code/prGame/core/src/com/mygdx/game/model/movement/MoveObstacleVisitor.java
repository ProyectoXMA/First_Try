package com.mygdx.game.model.movement;

import com.mygdx.game.model.obstacles.Duck;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.obstacles.ObstacleVisitor;
import com.mygdx.game.model.obstacles.Stone;

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
