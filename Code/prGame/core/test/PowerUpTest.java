import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.model.powerUps.PowerUpType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerUpTest {

    private PowerUp speedBoost;
    private PowerUp invincibility;
    private PowerUp healthBoost;
    private Boat boat;

    @BeforeEach
    void setUp() {
        speedBoost = PowerUp.createPowerUp(PowerUpType.SPEED, 0, 0);
        invincibility = PowerUp.createPowerUp(PowerUpType.INVINCIBILITY, 0, 0);
        healthBoost = PowerUp.createPowerUp(PowerUpType.HEALTH, 0, 0);
        boat = Boat.createBoat(BoatType.FAST);
    }

    @Test
    void testCreatePowerUp() {
        assertNotNull(speedBoost);
        assertNotNull(invincibility);
        assertNotNull(healthBoost);
    }

    @Test
    void testApplySpeedBoost() {
        speedBoost.applyPowerUp(boat);
        assertEquals(100, boat.getSpeed());
    }

    @Test
    void testApplyInvincibility() {
        invincibility.applyPowerUp(boat);
        assertTrue(boat.isInvencible());
    }

    @Test
    void testApplyHealthBoost() {
        boat.adjustHealth(-50);
        healthBoost.applyPowerUp(boat);
        assertEquals(100, boat.getHealth());
    }

}
