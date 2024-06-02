package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.CollidableVisitor;

public class Stone extends Obstacle {
    private static final int DAMAGE = 100;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    //Attributes for the Stone obstacle
    //Constructor for the Stone obstacle
    public Stone(int x, int y) {
        super(DAMAGE, new Rectangle(x, y, WIDTH, HEIGHT));
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
