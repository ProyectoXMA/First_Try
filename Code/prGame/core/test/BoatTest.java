import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoatTest {

    private Boat fastBoat;
    private Boat strongBoat;
    private Boat classicBoat;

    @BeforeEach
    void setUp() {
        fastBoat = Boat.createBoat(BoatType.FAST, 0, 0);
        strongBoat = Boat.createBoat(BoatType.STRONG, 0, 0);
        classicBoat = Boat.createBoat(BoatType.CLASSIC, 0, 0);
    }

    @Test
    void testCreateBoat() {
        assertNotNull(fastBoat);
        assertNotNull(strongBoat);
        assertNotNull(classicBoat);
    }

    @Test
    void testInitialAttributes() {
        assertEquals(100, fastBoat.getBaseHealth());
        assertEquals(200, strongBoat.getBaseHealth());
        assertEquals(150, classicBoat.getBaseHealth());

        assertEquals(10, fastBoat.getResistance());
        assertEquals(5, strongBoat.getResistance());
        assertEquals(7, classicBoat.getResistance());

        assertEquals(150, fastBoat.getHandling());
        assertEquals(70, strongBoat.getHandling());
        assertEquals(100, classicBoat.getHandling());

        assertEquals(10, fastBoat.getAcceleration());
        assertEquals(10, strongBoat.getAcceleration());
        assertEquals(18, classicBoat.getAcceleration());
    }

    @Test
    void testAdjustHealth() {
        fastBoat.adjustHealth(-50);
        assertEquals(50, fastBoat.getHealth());

        fastBoat.adjustHealth(100);
        assertEquals(100, fastBoat.getHealth());

        fastBoat.adjustHealth(-150);
        assertEquals(0, fastBoat.getHealth());
    }

    @Test
    void testAdjustResistance() {
        fastBoat.adjustResistance(-5);
        assertEquals(5, fastBoat.getResistance());

        fastBoat.adjustResistance(10);
        assertEquals(10, fastBoat.getResistance());

        fastBoat.adjustResistance(5);
        assertEquals(10, fastBoat.getResistance());
    }

    @Test
    void testAdjustSpeed() {
        fastBoat.adjustSpeed(10);
        assertEquals(10, fastBoat.getSpeed());

        fastBoat.adjustSpeed(150);
        assertEquals(150, fastBoat.getSpeed());

        fastBoat.adjustSpeed(-160);
        assertEquals(0, fastBoat.getSpeed());
    }

    @Test
    void testAdjustAcceleration() {
        fastBoat.adjustAcceleration(5);
        assertEquals(10, fastBoat.getAcceleration());

        fastBoat.adjustAcceleration(-10);
        assertEquals(0, fastBoat.getAcceleration());

        fastBoat.adjustAcceleration(20);
        assertEquals(10, fastBoat.getAcceleration());
    }

    @Test
    void testSetters() {
        fastBoat.setHealth(80);
        assertEquals(80, fastBoat.getHealth());

        fastBoat.setResistance(8);
        assertEquals(8, fastBoat.getResistance());

        fastBoat.setSpeed(120);
        assertEquals(120, fastBoat.getSpeed());

        fastBoat.setAcceleration(15);
        assertEquals(15, fastBoat.getAcceleration());
    }

    @Test
    void testIsInvencible() {
        assertFalse(fastBoat.isInvencible());
        fastBoat.setInvincible(true);
        assertTrue(fastBoat.isInvencible());
    }

    @Test
    void testIsDead() {
        assertFalse(fastBoat.isDead());
        fastBoat.adjustHealth(-100);
        assertTrue(fastBoat.isDead());
    }

    @Test
    void testResetCharacteristics() {
        fastBoat.adjustHealth(-50);
        fastBoat.setInvincible(true);
        fastBoat.resetCharacteristics();

        assertEquals(100, fastBoat.getHealth());
        assertEquals(10, fastBoat.getResistance());
        assertEquals(0, fastBoat.getSpeed());
        assertFalse(fastBoat.isInvencible());
    }
}