package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.movement.Movable;
import com.mygdx.game.model.movement.MovementStrategy;
import com.mygdx.game.model.movement.RandomMoveStrategy;
import com.mygdx.game.util.Config;

public class Duck extends Obstacle implements Movable {
    private static final int DAMAGE = 40;
    private static final int SPEED_MODIFIER = 50;
    private static final float WIDTH = 1.0f*Config.DuckRelativeSize;
    private static final float HEIGHT = 0.82f*Config.DuckRelativeSize;
    private static final float SPEED = 100;
    //MovementStrategy is not implemented here, just the pattern
    MovementStrategy movementStrategy;
    //attributes of the obstacle Duck
    private float speed;
    //Constructor for the obstacle Duck
    public Duck(int x, int y){
        super(DAMAGE, SPEED_MODIFIER, new Rectangle(x, y, WIDTH, HEIGHT));
        this.speed = SPEED;
        this.setMovementStrategy(new RandomMoveStrategy());
    }
    public Duck(){
        this(0,0);
    }
    //Getters for the exclusive attributes of the obstacle Duck
    @Override
    public float getSpeed(){
        return speed;
    }
    @Override
    public void setSpeed(float speed){
        this.speed = speed;
    }
    public void accept(ObstacleVisitor visitor) {
        visitor.visitDuck(this);
    }
    @Override
    public void adjustX(float x) {
        super.setX(super.getX() + x);
    }
    @Override
    public void adjustY(float y) {
        super.setY(super.getY() + y);
    }
    @Override
    public void move(float delta) {
        movementStrategy.move(this, delta);
    }
    @Override
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
    @Override
    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }
    @Override
    public void destroy() {
        Gdx.app.log("Duck", "Duck destroyed");
        //throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}