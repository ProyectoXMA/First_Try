//package com.mygdx.game.model;

//import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.minigameLogic.MinigameLogic;
import org.junit.jupiter.api.Test;
import com.mygdx.game.GameState;

import static org.junit.jupiter.api.Assertions.*;

public class MinigameLogicTest {

    MinigameLogic minigame = new MinigameLogic(null);

    @Test
    public void TimeTest(){
        //MinigameLogic minigame = new MinigameLogic(null);
        assertTrue(minigame.getRemainingTime() > 0);
        assertEquals(minigame.getTimeLimit(), 3000);
    }

    @Test
    public void FailCounterTest(){
        //MinigameLogic minigame = new MinigameLogic(null);
        assertEquals(minigame.getFailCounter(), 0);
    }

    @Test
    public void generateNewWordTest(){
        minigame.generateNewWord();
        assertNotNull(minigame.getCurrentWord());
    }

}
