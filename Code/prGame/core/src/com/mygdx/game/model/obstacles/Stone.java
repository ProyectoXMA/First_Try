package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.CollidableVisitor;

public class Stone extends Obstacle {
    //Attributes for the Stone obstacle
    //Constructor for the Stone obstacle
    public Stone(int damage, Rectangle hitBox){
        super(damage, hitBox);
    }
    @Override
    public void accept(ObstacleVisitor visitor) {
        visitor.visitStone(this);
    }
    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}
