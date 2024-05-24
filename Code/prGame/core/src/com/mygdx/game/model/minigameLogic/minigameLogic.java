package com.mygdx.game.model.minigameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.GameState;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MinigameLogic {
    private GameState gameState;
    private List<String> words;
    private String currentWord;
    private String typedWord;
    private int successCounter;
    private int failCounter;
    private int r;
    private double startTime;
    private double timeLimit;
    private double remainingTime;

    public MinigameLogic(GameState gameState) {
        this.gameState = gameState;
        this.words = Arrays.asList("Dragon", "Boat", "Racing", "Duck", "Ancient", "Ritualistic", "China", "Competition", "River", "Tradition");
        successCounter = 0;
        failCounter = 0;
        typedWord = "";
        timeLimit = 10000;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public int getFailCounter() {
        return failCounter;
    }

    public List<String> getWords() {
        return words;
    }

    public String getTypedWord() {
        return typedWord;
    }

    public GameState getGameState() {
        return gameState;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    /**
     * Method to set up the input processor for reading player input.
     * When the 'Enter' key is pressed, it processes the typed word.
     * 
     * @param character the key pressed by the user
     */
    public void generateAdapter(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyTyped(char character) {
                // Check word after 'Enter' is pressed (carriage return or newline button)
                if(character == '\r' || character == '\n') {
                    checkWord();
                } else if (character != '\b') { // Any other key other than 'Enter' or 'Backspace' is pressed
                    typedWord = typedWord + character; // Register such key on the string
                }
                return true;
            }
        });
        // Calls method to show the next word to type
        generateNewWord();
        // Resets the start time for the new word
        startTime = System.currentTimeMillis();
    }

    /**
     * Method to display the next word to the Player
     * 
     * currentWord will take the vale of any word from the predefined words list
     * typedWord is reseted on every function call
     */
    public void generateNewWord(){
        r = new Random().nextInt(10);
        currentWord = words.get(r);
        typedWord = "";
    }

     /**
      * Method to verify typed words read from Player input
      */
    public void checkWord(){
        // Check that typed word is the one showed in the panel
        if (typedWord.equals(currentWord)) {
            successCounter++;
        } else {
            failCounter++;
        }
        // After each word check verify if success or fail conditions are met
        checkGameState();
        // Then if needed, show next word
        generateNewWord();
        startTime = System.currentTimeMillis();
    }

    /**
      * Method to check and return the result of the minigame
      */
    public int checkGameState(){
        int state = 0;
        if(successCounter == 1){
            //gameState.setMinigamePlaysLeft(0);
            //gameState.getMyBoat().adjustHealth(gameState.getBaseHealth());
            state = successCounter;
        }else if(failCounter == 3){
            state = failCounter;
        }
        return state;
    }

    /**
      * Method to calculate the remaining time to display real time coundown
      */
    public void run(){
        remainingTime = (timeLimit - (System.currentTimeMillis() - startTime)) / 1000f;
        if(remainingTime <= 0){
            failCounter++;
            checkGameState();
            generateNewWord();
            startTime = System.currentTimeMillis();
        }
    }

}
