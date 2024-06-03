package com.mygdx.game.model.obstacles;

/**
 * The ObstacleVisitor interface is part of the Visitor design pattern.
 * This interface declares visit methods for each type of Obstacle in the game.
 * Each Obstacle class (Log, Stone, Duck) will implement an accept method that takes an ObstacleVisitor.
 * The ObstacleVisitor will then be able to perform operations specific to the type of Obstacle, for example, movement.
 */
public interface ObstacleVisitor {
    void visitLog(Log log);
    void visitStone(Stone stone);
    void visitDuck(Duck duck);
}
