package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.Boat;

public class Stone extends Obstacle {
    //Attributes for the Stone obstacle
    //Constructor for the Stone obstacle
    public Stone(int damage){
        super(damage);
    }
    public void accept(ObstacleVisitor visitor) {
        visitor.visitStone(this);
    }
    public void hitStone(Boat boat){
        boat.decreaseHealth(super.getDamage());
    }
}
