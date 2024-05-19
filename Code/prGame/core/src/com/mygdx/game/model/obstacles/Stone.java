package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;

public class Stone extends Obstacle {
    //Attributes for the Stone obstacle
    //Constructor for the Stone obstacle
    public Stone(int damage, Rectangle hitBox){
        super(damage, hitBox);
    }
    public void accept(ObstacleVisitor visitor) {
        visitor.visitStone(this);
    }
}
