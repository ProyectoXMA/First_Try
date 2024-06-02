import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.powerUps.Invincibility;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvincibilityBoostTest {

    @Test
    public void testInvincibilityCreation() {
        Rectangle hitbox = new Rectangle(0, 0, 10, 10);
        Invincibility invincibility = new Invincibility(hitbox);
        assertEquals(hitbox, invincibility.getHitbox());
    }

    @Test
    public void testApplyInvincibility() {

        Rectangle boatHitbox = new Rectangle(0, 0, 50, 50);
        Boat boat = Boat.createBoat(BoatType.FAST, boatHitbox);
        boolean initialInvincibility = boat.isInvencible();


        Rectangle powerUpHitbox = new Rectangle(10, 10, 10, 10);
        Invincibility invincibility = new Invincibility(powerUpHitbox);
        invincibility.applyPowerUp(boat); //We apply the powerUp


        assertFalse(initialInvincibility); //At the creation of the boat, the invincibility advantage is false
        assertTrue(boat.isInvencible()); //After applying the invincibility powerUp, the boat becomes invincible=true
    }
}
