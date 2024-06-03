import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoatTest {

    private Boat fastBoat;
    private Boat strongBoat;
    private Boat classicBoat;

    @BeforeEach
    public void setUp() {
        fastBoat = Boat.createBoat(BoatType.FAST, 0, 0);
        strongBoat = Boat.createBoat(BoatType.STRONG, 0, 0);
        classicBoat = Boat.createBoat(BoatType.CLASSIC, 0, 0);
    }

    @Test
    public void testBoatCreation() {
        assertNotNull(fastBoat);
        assertNotNull(strongBoat);
        assertNotNull(classicBoat);

        assertEquals(BoatType.FAST, fastBoat.getType());
        assertEquals(BoatType.STRONG, strongBoat.getType());
        assertEquals(BoatType.CLASSIC, classicBoat.getType());
    }

    @Test
    void testSetAttributes() {
        fastBoat.setHealth(200);
        fastBoat.setResistance(250);
        fastBoat.setSpeed(150);
        assertEquals(200, fastBoat.getHealth());
        assertEquals(250, fastBoat.getResistance());
        assertEquals(150,fastBoat.getSpeed());
    }

    @Test
    void testAdjustAttributes() {
        classicBoat.setHealth(100);
        classicBoat.setResistance(5);
        classicBoat.adjustHealth(50);
        classicBoat.adjustResistance(1);
        assertEquals(150, classicBoat.getHealth());
        assertEquals(6, classicBoat.getResistance());
    }

    @Test
    public void testInvincibility() {
        assertFalse(fastBoat.isInvencible());
        fastBoat.setInvincible(true);
        assertTrue(fastBoat.isInvencible());
        fastBoat.setInvincible(false);
        assertFalse(fastBoat.isInvencible());
    }

    @Test
    public void testDead() {
        assertFalse(fastBoat.isDead());
        fastBoat.setHealth(0);
        assertTrue(fastBoat.isDead());
        fastBoat.setHealth(10);
        assertFalse(fastBoat.isDead());
    }

    @Test
    public void testAdjustPosition() {
        float initialX = fastBoat.getX();
        float initialY = fastBoat.getY();

        fastBoat.adjustX(10.0f);
        assertEquals(initialX + 10.0f, fastBoat.getX(), 0.01f);

        fastBoat.adjustY(20.0f);
        assertEquals(initialY + 20.0f, fastBoat.getY(), 0.01f);
    }

    @Test
    public void testDestroy() {
        assertThrows(UnsupportedOperationException.class, () -> fastBoat.destroy());
        assertThrows(UnsupportedOperationException.class,  () -> strongBoat.destroy());
        assertThrows(UnsupportedOperationException.class,  () -> classicBoat.destroy());
    }
}