package com.mygdx.game.model;

import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

/**
 * This class is responsible for handling collisions between objects.
 * It implements the Visitor pattern, it visits all Collidable objects (Obstacle, Boat, PowerUp),
 * check if a collision has occurred and handles it.
 */
public class CollisionHandler{
    private Boat boat;
    public void setBoat(Boat boat) {
        this.boat = boat;
    }
    public Boat getBoat() {
        return boat;
    }

    public void checkObstacleCollision(Obstacle obstacle) {


        if (!obstacle.getWasHit()) { //In case the obstacle wasn´t hit yet, we look for the collision
            if(boat.getHitbox().overlaps(obstacle.getHitbox())) //In case of collision
            {
                boat.adjustHealth(-obstacle.getDamage()); //Once we apply the effect to the boat and obstacles it is applied to all the lanes, as the object is passed by reference
                obstacle.setWasHit(true); //We activate the flag to eliminate it, and to ensure no other lane applies the effects
            }

        }
    }


    public void checkBoatCollision(Boat boat) {
        if(!boat.getWasHit()) //In case it wasn´t hit, and therefore the boat associated to the handler neither
            if(this.boat.getHitbox().overlaps(boat.getHitbox())) { //By the client´s order when a boat collides with another both must be destroyed
                //First lane to detect the collision sets both objets wasHit to true
                this.boat.setWasHit(true);
                boat.setWasHit(true);
            }
    }


    public void checkPowerUpCollision(PowerUp powerUp) {

        if(!powerUp.getWasHit()) //In case it wasn´t hit yet, we look for the collision
            if (boat.getHitbox().overlaps(powerUp.getHitbox())) {

                //It only enters if an overlap in items hitboxes was detected
                powerUp.applyPowerUp(boat);
                powerUp.setWasHit(true); //We activate the flag to ensure it is eliminated
            }
    }
}
