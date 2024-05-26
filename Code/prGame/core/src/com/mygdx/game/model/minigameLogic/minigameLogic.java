package com.mygdx.game.model.minigameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    private Sound incorrectSound;
    private Sound savedSound;
    private Sound typeSound;
    public MinigameLogic(GameState gameState) {
        this.gameState = gameState;
        this.words = Arrays.asList("Dragon", "Boat", "Racing", "Duck", "Ancient", "Ritualistic", "China", "Competition", "River", "Tradition");
        successCounter = 0;
        failCounter = 0;
        typedWord = "";
        timeLimit = 10000;
        incorrectSound = Gdx.audio.newSound(Gdx.files.internal("incorrectSound.mp3"));
        savedSound = Gdx.audio.newSound(Gdx.files.internal("respawnSound.mp3"));
        typeSound = Gdx.audio.newSound(Gdx.files.internal("typeSound.mp3"));
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
                // Handle input character
                if (character != '\b' && character != '\r' && character != '\n') {// Any key other than 'Enter' or 'Backspace'
                    typedWord = typedWord + character; // Add the character to typedWord
                    checkPartialWord();
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
      public void checkPartialWord(){
          typeSound.play();
          // Check that typedWord matches the beginning of currentWord
        if (currentWord.startsWith(typedWord)) {
            //startTime = System.currentTimeMillis();
            checkGameState();
            if (typedWord.equals(currentWord)) { //If the whole word is matched
                successCounter++;
            }
        } else {
            incorrectSound.play();
            failCounter++;
            checkGameState();
            generateNewWord();
            startTime = System.currentTimeMillis();
        }
    }

    /**
      * Method to check and return the result of the minigame
      */
    public int checkGameState(){
        int state = 0;
        if(successCounter == 1){
            //gameState.setMinigamePlaysLeft(0);
            //gameState.getMyBoat().adjustHealth(gameState.getBaseHealth());
            typeSound.dispose();
            incorrectSound.dispose();
            savedSound.dispose();
            state = successCounter;
            savedSound.play();
        }else if(failCounter == 3){
            typeSound.dispose();
            incorrectSound.dispose();
            savedSound.dispose();
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
            incorrectSound.play();
            failCounter++;
            checkGameState();
            generateNewWord();
            startTime = System.currentTimeMillis();
        }
    }

}
