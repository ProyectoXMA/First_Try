import com.mygdx.game.model.obstacles.Duck;
import com.mygdx.game.model.obstacles.Log;
import com.mygdx.game.model.obstacles.ObstacleVisitor;
import com.mygdx.game.model.obstacles.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleVisitorTest {

    private ObstacleVisitor visitor;
    private Log log;
    private Duck duck;
    private Stone stone;

    @BeforeEach
    void setUp() {
        visitor = new ObstacleVisitor() {
            @Override
            public void visitLog(Log log) {
                log.setWasHit(true);
            }

            @Override
            public void visitStone(Stone stone) {
                stone.setWasHit(true);
            }

            @Override
            public void visitDuck(Duck duck) {
                duck.setWasHit(true);
            }
        };
        log = new Log();
        duck = new Duck();
        stone = new Stone();
    }

    @Test
    void testVisitLog() {
        assertFalse(log.getWasHit());
        log.accept(visitor);
        assertTrue(log.getWasHit());
    }

    @Test
    void testVisitDuck() {
        assertFalse(duck.getWasHit());
        duck.accept(visitor);
        assertTrue(duck.getWasHit());
    }

    @Test
    void testVisitStone() {
        assertFalse(stone.getWasHit());
        stone.accept(visitor);
        assertTrue(stone.getWasHit());
    }
}