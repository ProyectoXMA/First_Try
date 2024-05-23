package com.mygdx.game.model;

import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

public class CollisionHandler implements CollidableVisitor{
    private Boat boat;
    public void setBoat(Boat boat) {
        this.boat = boat;
    }
    public Boat getBoat() {
        return boat;
    }
    @Override
    public void visitObstacle(Obstacle obstacle) {
        if (boat.getHitbox().overlaps(obstacle.getHitbox())) {
            boat.adjustHealth(-obstacle.getDamage());
            obstacle.destroy();
        }
    }

    @Override
    public void visitBoat(Boat boat) {
        if(this.boat.getHitbox().overlaps(boat.getHitbox())) {
            this.boat.destroy();
            boat.destroy();
        }
    }

    @Override
    public void visitPowerUp(PowerUp powerUp) {
        if (boat.getHitbox().overlaps(powerUp.getHitbox())) {
            powerUp.applyPowerUp(boat);
            powerUp.destroy();
        }
    }
}
