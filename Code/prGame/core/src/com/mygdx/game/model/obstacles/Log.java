package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.CollidableVisitor;
import com.mygdx.game.util.Config;

//This class is not dynamic because a log cannot move
public class Log extends Obstacle {
    private static final int DAMAGE = 10;
    private static final float WIDTH = 1.0f*Config.LogRelativeSize;
    private static final float HEIGHT = 0.4f*Config.LogRelativeSize;
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
        Gdx.app.log("Log", "Log destroyed");
        //throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}
