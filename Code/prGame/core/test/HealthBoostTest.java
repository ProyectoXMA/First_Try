import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.powerUps.HealthBoost;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.model.powerUps.PowerUpType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealthBoostTest {

    @Test
    public void testHealthBoostCreation() {
        Rectangle hitbox = new Rectangle(0, 0, 10, 10);
        HealthBoost healthBoost = new HealthBoost(hitbox);
        assertEquals(hitbox, healthBoost.getHitbox());
    }

    @Test
    public void testApplyHealthBoost() {

        Boat boat = Boat.createBoat(BoatType.CLASSIC);
        boat.setHealth(50); // We set the boat health to 50
        int initialHealth = boat.getHealth();
        int expectedHealth = initialHealth + 100; // Expected health (previous setHealth(50) + 100 after applying the boost

        HealthBoost healthBoost = (HealthBoost) PowerUp.createPowerUp(PowerUpType.HEALTH); // We create a health boost with 100 health points
        healthBoost.applyPowerUp(boat);

        assertEquals(expectedHealth, boat.getHealth());
    }
}
