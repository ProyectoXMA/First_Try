package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.movement.Movable;
import com.mygdx.game.model.movement.MovementStrategy;
import com.mygdx.game.model.movement.RandomMoveStrategy;

public class Duck extends Obstacle implements Movable {
    private static final int DAMAGE = 40;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static final int SPEED = 100;
    //MovementStrategy is not implemented here, just the pattern
    MovementStrategy movementStrategy;
    //attributes of the obstacle Duck
    private int speed;
    //Constructor for the obstacle Duck
    public Duck(int x, int y){
        super(DAMAGE, new Rectangle(x, y, WIDTH, HEIGHT));
        this.speed = SPEED;
        this.setMovementStrategy(new RandomMoveStrategy());
    }
    public Duck(){
        this(0,0);
    }
    //Getters for the exclusive attributes of the obstacle Duck
    @Override
    public int getSpeed(){
        return speed;
    }
    @Override
    public void setSpeed(int speed){
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