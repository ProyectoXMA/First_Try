import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.movement.Movable;
import com.mygdx.game.model.obstacles.Obstacle;
import com.mygdx.game.model.obstacles.ObstacleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleTest {

    private Obstacle log;
    private Obstacle duck;
    private Obstacle stone;

    @BeforeEach
    void setUp() {
        log = Obstacle.createObstacle(ObstacleType.LOG);
        duck = Obstacle.createObstacle(ObstacleType.DUCK);
        stone = Obstacle.createObstacle(ObstacleType.STONE);
    }

    @Test
    void testCreateObstacle() {
        assertNotNull(log);
        assertNotNull(duck);
        assertNotNull(stone);
    }

    @Test
    void testLogAttributes() {
        assertEquals(10, log.getDamage());
        assertEquals(50, log.getSpeedModifier());
    }

    @Test
    void testDuckAttributes() {
        assertEquals(40, duck.getDamage());
        assertEquals(50, duck.getSpeedModifier());
        assertTrue(duck instanceof Movable);
    }

    @Test
    void testStoneAttributes() {
        assertEquals(100, stone.getDamage());
        assertEquals(150, stone.getSpeedModifier());
    }
}