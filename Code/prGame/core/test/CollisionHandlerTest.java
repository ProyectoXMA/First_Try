import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;
import com.mygdx.game.model.CollisionHandler;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.*;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.powerUps.HealthBoost;
public class CollisionHandlerTest {

    private CollisionHandler collisionHandler;
    private Boat boat;

    @BeforeEach
    public void setUp() {
        collisionHandler = new CollisionHandler();
        boat = Boat.createBoat(BoatType.FAST, 0, 0);
        collisionHandler.setBoat(boat);
    }

    @Test
    public void testVisitObstacle_NoCollisionOccurred() {
        Log obstacle = new Log(0, new Rectangle(100, 100, 10, 10));
        collisionHandler.visitObstacle(obstacle);

        assertEquals(100, boat.getHealth());
    }

    @Test
    public void testVisitBoat_NoCollisionOccurred() {
        Boat otherBoat = Boat.createBoat(BoatType.STRONG, 100, 100);
        collisionHandler.visitBoat(otherBoat);

        assertFalse(boat.dead());
        assertFalse(otherBoat.dead());
    }

    @Test
    public void testVisitPowerUp_NoCollisionOccurred() {
        HealthBoost hp = new HealthBoost(new Rectangle(100, 100, 10, 10));
        collisionHandler.visitPowerUp(hp);

        assertEquals(100, boat.getHealth());
    }
}