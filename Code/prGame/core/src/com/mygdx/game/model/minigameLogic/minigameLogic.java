package com.mygdx.game.model.minigameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.GameState;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class minigameLogic {
    private GameState gameState;
    private int successCounter;
    private int failCounter;
    private String currentWord;
    private String typedWord;
    private double startTime;
    private double timeLimit;
    private double remainingTime;
    private int r;
    private List<String> words;

    public minigameLogic(GameState gameState) {
        this.gameState = gameState;
        successCounter = 0;
        failCounter = 0;
        this.words = Arrays.asList("Dragon", "Boat", "Racing", "Duck", "Ancient", "Ritualistic", "China", "Competition", "River", "Tradition");
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

    // Method to read Player input
    //
    // @param character stores the key pressed by the user
    // @method checkWord() process the keystrokes stored in character after 'Enter' key is pressed
    public void generateAdapter(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyTyped(char character) {
                // Check word after 'Enter' is pressed (carriage return or newline button)
                if(character == '\r' || character == '\n') {
                    checkWord();
                } else if (character != '\b') { // Any other key other than 'Enter' is pressed
                    typedWord = typedWord + character; // Register such key on the string
                }
                return true;
            }
        });
        // @method to show the next word to type
        // @var startTime is restarted on every the word call
        generateNewWord();
        startTime = System.currentTimeMillis();
    }
    // Method to display the next word to the Player
    //
    // @var currentWord will take the vale of any word from the predefined words list
    // @var typedWord is reseted on every function call
    public void generateNewWord(){
        r = new Random().nextInt(10);
        currentWord = words.get(r);
        typedWord = "";
    }
    // Method to verify typed words read from Player input
    //
    public void checkWord(){
        // Verify that typed word is the one showed in the panel
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

    public int checkGameState(){
        if(successCounter == 1){
            return successCounter;
        }else if(failCounter == 3){
            return failCounter;
        }
        return 0;
    }

    public void run(){
        // Calculates the remaining time to display real time coundown
        remainingTime = (timeLimit - (System.currentTimeMillis() - startTime)) / 1000f;
        if(remainingTime <= 0){
            failCounter++;
            checkGameState();
            generateNewWord();
            startTime = System.currentTimeMillis();
        }
    }
}
