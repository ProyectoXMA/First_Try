package com.mygdx.game.model.obstacles;

public interface ObstacleVisitor {
    void visit(Log log);
    void visit(Stone stone);
    void visit(Duck duck);
}
