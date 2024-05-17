package com.mygdx.game.model.obstacles;

public class Log extends Obstacle {
    @Override
    public void accept(ObstacleVisitor visitor) {
        visitor.visit(this);
    }
    //TODO: Implement class Log (dynamic/static? obstacle)
}
