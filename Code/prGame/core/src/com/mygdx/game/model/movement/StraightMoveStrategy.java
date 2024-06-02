package com.mygdx.game.model.movement;

import com.mygdx.game.util.Config;

import static com.badlogic.gdx.math.MathUtils.random;

public class StraightMoveStrategy implements MovementStrategy {
    private static final int leftLIMIT = 10;
    private static final int rightLIMIT = Config.getWidth() - 10;
    private boolean isMovingLeft;

    public StraightMoveStrategy() { //When the strategy is created the isMovingLeft is randomly selected.
        this.isMovingLeft = random.nextBoolean(); ;
    }
    @Override
    public void move(Movable movable, float delta) {
        float movableLeftLimit = movable.getHitbox().x;
        float movableRightLimit = movable.getHitbox().x + movable.getHitbox().width;
        int horizontalSpeed = movable.getSpeed();
        //I need to know if the movable was moving left of right previously.
        if(isMovingLeft) {// //The movable is moving left
            if(movableLeftLimit <= leftLIMIT){ //The object has reached its limit and must change the direction.
                isMovingLeft = false;
                movable.adjustX(horizontalSpeed * delta); //Starts  moving left
            } else {
                movable.adjustX(-horizontalSpeed * delta); //Keeps moving left
            }
        } else { //The movable is moving right
            if(movableRightLimit >= rightLIMIT) { //Has collided with right limit must move left
                isMovingLeft = true;
                movable.adjustX(-horizontalSpeed * delta);
            } else {
                movable.adjustX(horizontalSpeed * delta);
            }
        }
    }
}

