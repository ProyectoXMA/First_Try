package com.mygdx.game.model.obstacles;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.CollidableVisitor;
import com.mygdx.game.model.movement.Movable;
import com.mygdx.game.model.movement.MovementStrategy;

import static com.badlogic.gdx.math.MathUtils.random;


    //DONDE SE INICIALIZA EL MOVEMENT STRATEGY DEL PATO?????
public class Duck extends Obstacle implements Movable {
    //MovementStrategy is not implemented here, just the pattern
    MovementStrategy movementStrategy;
    //attributes of the obstacle Duck
    private int speed;
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



    //Constructor for the obstacle Duck
    public Duck(int damage, int speed, Rectangle hitBox){
        super(damage, hitBox);
        this.speed = speed;
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
    public void destroy() {
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}