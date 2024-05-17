package com.mygdx.game.model.movement;

import com.mygdx.game.model.obstacles.Duck;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.obstacles.ObstacleVisitor;
import com.mygdx.game.model.obstacles.Stone;

public class MoveVisitor implements ObstacleVisitor {
    private float delta;
    public MoveVisitor(float delta) {
        this.delta = delta;
    }
    @Override
    public void visit(Log log) {
    }

    @Override
    public void visit(Stone stone) {

    }

    @Override
    public void visit(Duck duck) {
        duck.getMovementStrategy().move(delta);
    }
}
