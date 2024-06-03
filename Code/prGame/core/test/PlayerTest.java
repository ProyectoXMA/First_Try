import com.mygdx.game.model.BoatType;
import com.mygdx.game.model.Player;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testDefaultConstructor() {
        Player player = new Player();
        assertEquals(BoatType.CLASSIC, player.getBoatType(), "The boat type selected should be CLASSIC");
    }

    @Test
    void testParameterizedConstructor() {
        Player player = new Player(BoatType.FAST);
        assertEquals(BoatType.FAST, player.getBoatType(), "The boat type selected should be FAST");
    }

    @Test
    void testSetBoatType() {
        Player player = new Player();
        player.setBoatType(BoatType.STRONG);
        assertEquals(BoatType.STRONG, player.getBoatType(), "The boat type selected should be STRONG");
    }
}
