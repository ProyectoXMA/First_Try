package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.movement.Movable;
import com.mygdx.game.model.movement.MovementStrategy;

public class Duck extends Obstacle implements Movable {
    //MovementStrategy is not implemented here, just the pattern
    MovementStrategy movementStrategy;
    //attributes of the obstacle Duck
    private int speed;
    @Override
    public void move(float delta) {
        movementStrategy.move(this, delta);
    }

    @Override
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    @Override
    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }
    //Constructor for the obstacle Duck
    public Duck(int damage, int speed){
        super(damage);
        this.speed = speed;
    }
    //Getters for the exclusive attributes of the obstacle Duck
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public void accept(ObstacleVisitor visitor) {
        visitor.visitDuck(this);
    }
}