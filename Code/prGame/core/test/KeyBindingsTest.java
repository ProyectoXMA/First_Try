import com.badlogic.gdx.Input;
import com.mygdx.game.util.KeyBindings;
import com.mygdx.game.util.UserAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeyBindingsTest {
    private KeyBindings keyBindings;

    @BeforeEach
    void setUp() {
        keyBindings = new KeyBindings();
    }

    @Test
    void testDefaultKeyBindings() {
        assertEquals(Input.Keys.LEFT, keyBindings.getKeyForAction(UserAction.MOVE_LEFT));
        assertEquals(Input.Keys.RIGHT, keyBindings.getKeyForAction(UserAction.MOVE_RIGHT));
        assertEquals(Input.Keys.UP, keyBindings.getKeyForAction(UserAction.MOVE_UP));
        assertEquals(Input.Keys.DOWN, keyBindings.getKeyForAction(UserAction.MOVE_DOWN));
        assertEquals(Input.Keys.ESCAPE, keyBindings.getKeyForAction(UserAction.ESCAPE));
        assertEquals(Input.Keys.ENTER, keyBindings.getKeyForAction(UserAction.ENTER));
    }

    @Test
    void testSetKeyBinding() {
        keyBindings.setKeyBinding(UserAction.MOVE_LEFT, Input.Keys.A);
        assertEquals(Input.Keys.A, keyBindings.getKeyForAction(UserAction.MOVE_LEFT));

        keyBindings.setKeyBinding(UserAction.MOVE_RIGHT, Input.Keys.D);
        assertEquals(Input.Keys.D, keyBindings.getKeyForAction(UserAction.MOVE_RIGHT));
    }
}