import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.Boat;
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

    /*
    @Test
    void testSetAndGetBoat() throws Exception {
        Player player = new Player();
        Rectangle hitBox = new Rectangle(0, 0, 50, 50);

        // Use reflection to access the private constructor of Boat
        Constructor<Boat> constructor = Boat.class.getDeclaredConstructor(BoatType.class, int.class, int.class, int.class, int.class, int.class, Rectangle.class);
        constructor.setAccessible(true);
        Boat boat = constructor.newInstance(BoatType.CLASSIC, 100, 10, 5, 5, 5, hitBox);

        player.setBoat(boat);
        assertEquals(boat, player.getBoat(), "The boat should be the same instance that was set");
    }

     */
}
