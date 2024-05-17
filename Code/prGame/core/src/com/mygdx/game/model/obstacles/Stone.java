package com.mygdx.game.model.obstacles;

public class Stone extends Obstacle {
    @Override
    public void accept(ObstacleVisitor visitor) {
        visitor.visit(this);
    }
    //TODO: Implement class Stone (static obstacle)
}
