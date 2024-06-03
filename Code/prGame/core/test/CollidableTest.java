import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollidableTest {

    private Boat boat;

    @BeforeEach
    void setUp() {
        boat = Boat.createBoat(BoatType.FAST, 0, 0);
    }

    @Test
    void testGetWasHit() {
        assertFalse(boat.getWasHit());
        boat.setWasHit(true);
        assertTrue(boat.getWasHit());
    }

    @Test
    void testSetWasHit() {
        boat.setWasHit(true);
        assertTrue(boat.getWasHit());
        boat.setWasHit(false);
        assertFalse(boat.getWasHit());
    }

    @Test
    void testGetHitbox() {
        Rectangle hitbox = boat.getHitbox();
        assertNotNull(hitbox);
    }
}