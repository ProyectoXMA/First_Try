package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.Boat;

//This class is not dynamic because a log cannot move
public class Log extends Obstacle {
    //Attributes for the log obstacle
    //Constructor for the log obstacle
    public Log(int damage){
        super(damage);
    }
    public void accept(ObstacleVisitor visitor) {
        visitor.visitLog(this);
    }
    public void hitLog(Boat boat){
        boat.decreaseHealth(super.getDamage());
    }
}
