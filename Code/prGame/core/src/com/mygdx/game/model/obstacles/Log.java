package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.CollidableVisitor;

//This class is not dynamic because a log cannot move
public class Log extends Obstacle {
    //Attributes for the log obstacle
    //Constructor for the log obstacle
    public Log(int damage, Rectangle hitBox){
        super(damage, hitBox);
    }
    public void accept(ObstacleVisitor visitor) {
        visitor.visitLog(this);
    }

    @Override
    public void accept(CollidableVisitor visitor) {
        visitor.visitObstacle(this);
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}
