import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.powerUps.Invincibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InvincibilityTest {

    private Invincibility invincibility;
    private Boat boat;

    @BeforeEach
    void setUp() {
        invincibility = new Invincibility(new Rectangle(0, 0, 10, 10));
        boat = Boat.createBoat(BoatType.FAST);
    }

    @Test
    void testApplyPowerUp() {
        invincibility.applyPowerUp(boat);
        assertTrue(boat.isInvencible());
    }
}
