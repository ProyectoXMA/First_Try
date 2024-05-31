package com.mygdx.game.model.powerUps;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;

public class SpeedBoost extends PowerUp{
    private static final int SPEED_INCREASE = 100;
    public SpeedBoost(Rectangle hitBox) {
        super(hitBox);
    }

    @Override
    public void applyPowerUp(Boat boat) {
        boat.adjustSpeed(SPEED_INCREASE);
    }
}
