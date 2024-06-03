import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.powerUps.Invincibility;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.model.powerUps.PowerUpType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvincibilityBoostTest {

    @Test
    public void invincibilityPowerUpMakesBoatInvincible() {
        Boat boat = Boat.createBoat(BoatType.FAST);
        Invincibility invincibility = (Invincibility) PowerUp.createPowerUp(PowerUpType.INVINCIBILITY);

        invincibility.applyPowerUp(boat);

        assertTrue(boat.isInvencible());
    }

    @Test
    public void invincibilityPowerUpDoesNotAffectAlreadyInvincibleBoat() {
        Boat boat = Boat.createBoat(BoatType.FAST);
        boat.setInvincible(true);
        Invincibility invincibility = (Invincibility) PowerUp.createPowerUp(PowerUpType.INVINCIBILITY);

        invincibility.applyPowerUp(boat);

        assertTrue(boat.isInvencible());
    }

    @Test
    public void invincibilityPowerUpDoesNotAffectNonInvincibleBoat() {
        Boat boat = Boat.createBoat(BoatType.FAST);
        boat.setInvincible(false);
        Invincibility invincibility = (Invincibility) PowerUp.createPowerUp(PowerUpType.INVINCIBILITY);

        invincibility.applyPowerUp(boat);

        assertTrue(boat.isInvencible());
    }
}