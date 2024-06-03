package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
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
        Gdx.app.debug("CollisionHandler", "Checking obstacle collision");
        if (!obstacle.getWasHit()) { //In case the obstacle wasn´t hit yet, we look for the collision
            if(boat.getHitbox().overlaps(obstacle.getHitbox())){ //In case of collision
                Gdx.app.log("CollisionHandler", "Obstacle collision detected! :" + obstacle.getClass() + " and " + this.boat.getType());
                obstacle.setWasHit(true); //We activate the flag to eliminate it, and to ensure no other lane applies the effects
                boat.adjustSpeed(-obstacle.getSpeedModifier()); //We apply the effect of the obstacle to the boat
                if(boat.isInvencible()) return; //If the boat is invencible, it doesn´t take damage
                boat.adjustHealth(-obstacle.getDamage()); //Once we apply the effect to the boat and obstacles it is applied to all the lanes, as the object is passed by reference
            }

        }
    }
    public void checkBoatCollision(Boat boat) {
        Gdx.app.debug("CollisionHandler", "Checking boat collision");
        if(!boat.getWasHit()) //In case it wasn´t hit, and therefore the boat associated to the handler neither
        {
            if(this.boat.getHitbox().overlaps(boat.getHitbox())) { //By the client´s order when a boat collides with another both must be destroyed
                Gdx.app.log("CollisionHandler", "Boat collision detected! :" + boat.getType() + " and " + this.boat.getType());
                Gdx.app.debug("CollisionHandler", "Position of boats: " + boat.getHitbox() + " and " + this.boat.getHitbox());
                //First lane to detect the collision sets both objets wasHit to true
                this.boat.setWasHit(true);
                boat.destroy();
                this.boat.destroy();
                boat.setWasHit(true);
            }
        }
    }
    public void checkPowerUpCollision(PowerUp powerUp) {
        Gdx.app.debug("CollisionHandler", "Checking powerUps collision");
        if(!powerUp.getWasHit()) //In case it wasn´t hit yet, we look for the collision
            if (boat.getHitbox().overlaps(powerUp.getHitbox())) {
                Gdx.app.log("CollisionHandler", "Obstacle collision detected! :" + powerUp.getClass() + " and " + this.boat.getType());
                //It only enters if an overlap in items hitboxes was detected
                powerUp.applyPowerUp(boat);
                powerUp.setWasHit(true); //We activate the flag to ensure it is eliminated
            }
    }
}
