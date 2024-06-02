package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.CollidableVisitor;
import com.mygdx.game.util.Config;

public class Stone extends Obstacle {
    private static final int DAMAGE = 100;
    private static final int SPEED_MODIFIER = 150;
    private static final float WIDTH = 1.0f*Config.StoneRelativeSize;
    private static final float HEIGHT = 0.66f*Config.StoneRelativeSize;
    //Attributes for the Stone obstacle
    //Constructor for the Stone obstacle
    public Stone(int x, int y) {
        super(DAMAGE, SPEED_MODIFIER, new Rectangle(x, y, WIDTH, HEIGHT));
    }
    public Stone(){
        this(0,0);
    }
    @Override
    public void accept(ObstacleVisitor visitor) {
        visitor.visitStone(this);
    }
    @Override
    public void destroy() {
        Gdx.app.log("Stone", "Stone destroyed");
        //throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}
