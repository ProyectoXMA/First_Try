package com.mygdx.game.model.powerUps;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;

public class HealthBoost extends PowerUp {
    private static final int HEALTH_INCREASE = 100;
    public HealthBoost(Rectangle hitBox) {
        super(hitBox);
    }
    @Override
    public void applyPowerUp(Boat boat) {
        boat.adjustHealth(HEALTH_INCREASE);
    }
    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}
