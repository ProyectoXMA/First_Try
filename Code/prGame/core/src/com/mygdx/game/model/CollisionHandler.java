package com.mygdx.game.model;

import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

/**
 * This class is responsible for handling collisions between objects.
 * It implements the Visitor pattern, it visits all Collidable objects (Obstacle, Boat, PowerUp),
 * check if a collision has occurred and handles it.
 */
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
            //Devuelve un true para que la lane sepa que se lo tiene que cargar
            obstacle.destroy();//Pone la flag de wasHit a true
        }
    }

    @Override
    public void visitBoat(Boat boat) {
        if(this.boat.getHitbox().overlaps(boat.getHitbox())) { //By the client´s order when a boat collides with another both must be destroyed
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
