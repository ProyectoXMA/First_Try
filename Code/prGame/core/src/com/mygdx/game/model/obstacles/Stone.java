package com.mygdx.game.model.obstacles;

public class Stone extends Obstacle {
    //Attributes for the Stone obstacle
    //Constructor for the Stone obstacle
    public Stone(int damage){
        super(damage);
    }
    public void accept(ObstacleVisitor visitor) {
        visitor.visitStone(this);
    }
}
