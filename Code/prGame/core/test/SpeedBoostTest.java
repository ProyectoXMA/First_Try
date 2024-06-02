import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.powerUps.SpeedBoost;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpeedBoostTest {

    @Test
    public void testSpeedBoostCreation() {
        Rectangle hitbox = new Rectangle(0, 0, 10, 10);
        SpeedBoost speedBoost = new SpeedBoost(hitbox);
        assertEquals(hitbox, speedBoost.getHitbox());
    }

    @Test
    public void testApplySpeedBoost() {
        Rectangle boatHitbox = new Rectangle(0, 0, 50, 50);
        Boat boat = Boat.createBoat(BoatType.FAST, boatHitbox);
        boat.setSpeed(100);
        int initialSpeed = boat.getSpeed();

        Rectangle powerUpHitbox = new Rectangle(10, 10, 10, 10);
        SpeedBoost speedBoost = new SpeedBoost(powerUpHitbox);

        speedBoost.applyPowerUp(boat);
        assertEquals(initialSpeed + 100, boat.getSpeed());
    }
}

