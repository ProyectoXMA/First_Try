import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.powerUps.HealthBoost;
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
        // Given
        Rectangle boatHitbox = new Rectangle(0, 0, 50, 50);
        Boat boat = Boat.createBoat(BoatType.CLASSIC, boatHitbox);
        boat.setHealth(50); // We set the boat health to 50
        int initialHealth = boat.getHealth();
        int expectedHealth = initialHealth + 100; // Expected health (previous setHealth(50) + 100 after applying the boost

        // When
        Rectangle powerUpHitbox = new Rectangle(10, 10, 10, 10);
        HealthBoost healthBoost = new HealthBoost(powerUpHitbox);
        healthBoost.applyPowerUp(boat);

        // Then
        assertEquals(expectedHealth, boat.getHealth());
    }
}
