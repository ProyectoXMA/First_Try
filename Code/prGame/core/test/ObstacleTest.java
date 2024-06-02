import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.obstacles.Duck;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.obstacles.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObstacleTest {

    private Log log;
    private Stone stone;
    private Duck duck;
    private Rectangle logHitbox;
    private Rectangle stoneHitbox;
    private Rectangle duckHitbox;

    @BeforeEach
    void setUp() {
        // Initialize test data
        logHitbox = new Rectangle(0, 0, 10, 10);
        stoneHitbox = new Rectangle(5, 5, 15, 15);
        duckHitbox = new Rectangle(10, 10, 20, 20);
        log = new Log(20, logHitbox);
        stone = new Stone(15, stoneHitbox);
        duck = new Duck(10, 5, duckHitbox);  // Damage: 10, Speed: 5
    }

    @Test
    void testLogInitialization() {
        assertEquals(20, log.getDamage(), "Log damage should be initialized correctly");
        assertEquals(logHitbox, log.getHitbox(), "Log hitbox should be initialized correctly");
        assertFalse(log.getWasHit(), "Log wasHit should be initialized to false");
    }

    @Test
    void testStoneInitialization() {
        assertEquals(15, stone.getDamage(), "Stone damage should be initialized correctly");
        assertEquals(stoneHitbox, stone.getHitbox(), "Stone hitbox should be initialized correctly");
        assertFalse(stone.getWasHit(), "Stone wasHit should be initialized to false");
    }

    @Test
    void testDuckInitialization() {
        assertEquals(10, duck.getDamage(), "Duck damage should be initialized correctly");
        assertEquals(5, duck.getSpeed(), "Duck speed should be initialized correctly");
        assertEquals(duckHitbox, duck.getHitbox(), "Duck hitbox should be initialized correctly");
        assertFalse(duck.getWasHit(), "Duck wasHit should be initialized to false");
    }

    @Test
    void testLogAcceptVisitor() {
        ObstacleVisitorTest visitor = new ObstacleVisitorTest();
        log.accept(visitor);
        assertTrue(visitor.visitedLog, "Log should accept visitor and set visitedLog to true");
    }

    @Test
    void testStoneAcceptVisitor() {
        ObstacleVisitorTest visitor = new ObstacleVisitorTest();
        stone.accept(visitor);
        assertTrue(visitor.visitedStone, "Stone should accept visitor and set visitedStone to true");
    }

    @Test
    void testDuckAcceptVisitor() {
        ObstacleVisitorTest visitor = new ObstacleVisitorTest();
        duck.accept(visitor);
        assertTrue(visitor.visitedDuck, "Duck should accept visitor and set visitedDuck to true");
    }

    @Test
    void testDuckAdjustX() {
        float initialX = duck.getX();
        duck.adjustX(5);
        assertEquals(initialX + 5, duck.getX(), "Duck X position should be adjusted correctly");
    }

    @Test
    void testDuckAdjustY() {
        float initialY = duck.getY();
        duck.adjustY(5);
        assertEquals(initialY + 5, duck.getY(), "Duck Y position should be adjusted correctly");
    }
}
