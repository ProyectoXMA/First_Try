package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;

public class GameState {
    private Boat myBoat;
    private List<Boat> rivals;
    //private List<Obstacle> obstacles;
    //private List<PowerUp> powerUps;
    private Vector2 deathPoint;
    private int minigamePlaysLeft;
    private int failCounter;
    private int baseHealth;

    public GameState(Boat myBoat, List<Boat> rivals, Vector2 deathPoint){
        this.myBoat = myBoat;
        this.rivals = rivals;
        this.deathPoint = deathPoint;
        this.minigamePlaysLeft = 1;
        this.failCounter = 3;
        this.baseHealth = 100;
    }

    public Boat getMyBoat() {
        return myBoat;
    }

    public int getFailCounter() {
        return failCounter;
    }

    public int getMinigamePlaysLeft() {
        return minigamePlaysLeft;
    }

    public List<Boat> getRivals() {
        return rivals;
    }

    public Vector2 getDeathPoint() {
        return deathPoint;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setFailCounter(int failCounter) {
        this.failCounter = failCounter;
    }

    public void setMinigamePlaysLeft(int minigamePlaysLeft) {
        this.minigamePlaysLeft = minigamePlaysLeft;
    }

    public void setBaseHealth(int baseHealth) {
        this.myBoat.adjustHealth(baseHealth);
    }
    
}


