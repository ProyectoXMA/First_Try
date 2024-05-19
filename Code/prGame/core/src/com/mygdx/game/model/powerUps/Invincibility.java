package com.mygdx.game.model.powerUps;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;

public class Invincibility extends PowerUp{
    public Invincibility(Rectangle hitBox) {
        super(hitBox);
    }

    @Override
    public void applyPowerUp(Boat boat) {
        boat.setInvincible(true);
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }
}
