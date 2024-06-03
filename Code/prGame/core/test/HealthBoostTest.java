import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.powerUps.HealthBoost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthBoostTest {

    private HealthBoost healthBoost;
    private Boat boat;

    @BeforeEach
    void setUp() {
        healthBoost = new HealthBoost(new Rectangle(0, 0, 10, 10));
        boat = Boat.createBoat(BoatType.FAST);
    }

    @Test
    void testApplyPowerUp() {
        boat.adjustHealth(-50);
        healthBoost.applyPowerUp(boat);
        assertEquals(100, boat.getHealth());
    }
}