import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.powerUps.HealthBoost;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.powerUps.PowerUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LaneTest {

    private Lane lane;
    private Boat boat;
    private Obstacle log;
    private PowerUp healthBoost;

    @BeforeEach
    void setUp() {
        boat = Boat.createBoat(BoatType.CLASSIC, 0, 0);
        Set<Obstacle> obstacles = new HashSet<>();
        log = new Log(50, 0);
        obstacles.add(log);
        Set<PowerUp> powerUps = new HashSet<>();
        healthBoost = new HealthBoost(new Rectangle(100, 0, 10, 10));
        powerUps.add(healthBoost);
        lane = new Lane(1, obstacles, powerUps, boat);
    }

    @Test
    void testLaneCreation() {
        assertNotNull(lane);
        assertEquals(1, lane.getLaneId());
    }

    @Test
    void testGetBoat() {
        assertEquals(boat, lane.getBoat());
    }

    @Test
    void testGetObstacles() {
        assertTrue(lane.getObstacles().contains(log));
    }

    @Test
    void testGetPowerUps() {
        assertTrue(lane.getPowerUps().contains(healthBoost));
    }
}
