import com.mygdx.game.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LegTest {

    private Player player;
    private Leg leg;

    @BeforeEach
    public void setUp() {
        player = new Player();
        leg = new Leg(1, player);
    }

    @Test
    public void testInitialization() {
        List<Lane> lanes = leg.getLanes();

        assertNotNull(lanes);
        assertEquals(4, lanes.size()); // Ensure there are 4 lanes

        //test each lane to make sure it is not null
        for (Lane lane : lanes) {
            assertNotNull(lane);
        }
    }
}
