package com.mygdx.game.model;

import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

/**
 * This interface is implemented by all visitors that visit Collidable objects.
 * It has three methods, one for each type of Collidable object (Obstacle, Boat, PowerUp).
 */
public interface CollidableVisitor {
    void visitObstacle(Obstacle obstacle);
    void visitBoat(Boat boat);
    void visitPowerUp(PowerUp powerUp);
}
