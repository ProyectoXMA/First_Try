import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.Lane;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.obstacles.Stone;
import com.mygdx.game.model.powerUps.HealthBoost;
import com.mygdx.game.model.powerUps.PowerUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LaneTest {

    private Lane lane;
    private Boat assignedBoat;
    private Set<Obstacle> obstacles;
    private Set<PowerUp> powerUps;

    @BeforeEach
    void setUp() {
        obstacles = new HashSet<>();
        powerUps = new HashSet<>();
        assignedBoat = Boat.createBoat(BoatType.CLASSIC, 0, 0);
        lane = new Lane(1, obstacles, powerUps, assignedBoat);
    }

    @Test
    void testLaneInitialization() {
        assertEquals(1, lane.getLaneId());
        assertEquals(Lane.WIDTH, lane.getLanePosition());
        assertTrue(lane.getBoats().contains(assignedBoat));
        assertEquals(obstacles, lane.getObstacles());
        assertEquals(powerUps, lane.getPowerUps());
    }

    @Test
    void testAddObstacle() {
        Obstacle obstacle = new Stone(10, new Rectangle(100, 0, 50, 50));
        lane.addGameObject(obstacle);
        assertTrue(lane.getObstacles().contains(obstacle));
    }

    @Test
    void testRemoveObstacle() {
        Obstacle obstacle = new Stone(10, new Rectangle(100, 0, 50, 50));
        lane.addGameObject(obstacle);
        lane.removeGameObject(obstacle);
        assertFalse(lane.getObstacles().contains(obstacle));
    }

    @Test
    void testAddPowerUp() {
        PowerUp powerUp = new HealthBoost(new Rectangle(100, 0, 50, 50));
        lane.addGameObject(powerUp);
        assertTrue(lane.getPowerUps().contains(powerUp));
    }

    @Test
    void testRemovePowerUp() {
        PowerUp powerUp = new HealthBoost(new Rectangle(100, 0, 50, 50));
        lane.addGameObject(powerUp);
        lane.removeGameObject(powerUp);
        assertFalse(lane.getPowerUps().contains(powerUp));
    }

    @Test
    void testAddBoat() {
        Boat boat = Boat.createBoat(BoatType.FAST, 100, 0);
        lane.addGameObject(boat);
        assertTrue(lane.getBoats().contains(boat));
    }

    @Test
    void testRemoveBoat() {
        Boat boat = Boat.createBoat(BoatType.FAST, 100, 0);
        lane.addGameObject(boat);
        lane.removeGameObject(boat);
        assertFalse(lane.getBoats().contains(boat));
    }
}