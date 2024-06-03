import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.powerUps.SpeedBoost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpeedBoostTest {

    private SpeedBoost speedBoost;
    private Boat boat;

    @BeforeEach
    void setUp() {
        speedBoost = new SpeedBoost(new Rectangle(0, 0, 10, 10));
        boat = Boat.createBoat(BoatType.FAST);
    }

    @Test
    void testApplyPowerUp() {
        speedBoost.applyPowerUp(boat);
        assertEquals(100, boat.getSpeed());
    }
}
