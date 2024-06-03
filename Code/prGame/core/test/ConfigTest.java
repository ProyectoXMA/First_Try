import com.mygdx.game.util.Config;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void testGetWidth() {
        int expectedWidth = 1920;
        assertEquals(expectedWidth, Config.getWidth());
    }

    @Test
    public void testGetHeight() {
        int expectedHeight = 1080;
        assertEquals(expectedHeight, Config.getHeight());
    }

    @Test
    public void testConstructor() {
        int customWidth = 1920; //We select width = 1920
        int customHeight = 1080; //We select height = 1080
        new Config(customWidth, customHeight); // Instantiate Config with custom width and height
        assertEquals(customWidth, Config.getWidth()); // Check if the width is set correctly
        assertEquals(customHeight, Config.getHeight()); // Check if the height is set correctly
    }
}


