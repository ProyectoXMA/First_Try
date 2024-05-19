package com.mygdx.game.model;

import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

public interface CollidableVisitor {
    void visitObstacle(Obstacle obstacle);
    void visitBoat(Boat boat);
    void visitPowerUp(PowerUp powerUp);
}
