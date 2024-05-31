package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.CollidableVisitor;

//This class is not dynamic because a log cannot move
public class Log extends Obstacle {
    private static final int DAMAGE = 0;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    //Attributes for the log obstacle
    //Constructor for the log obstacle
    public Log(int x, int y){
        super(DAMAGE, new Rectangle(x,y,WIDTH,HEIGHT));
    }
    public Log(){
        this(0,0);
    }
    @Override
    public void accept(ObstacleVisitor visitor) {
        visitor.visitLog(this);
    }
    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}
