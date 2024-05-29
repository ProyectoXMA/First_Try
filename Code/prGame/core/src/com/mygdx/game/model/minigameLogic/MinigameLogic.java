package com.mygdx.game.model.minigameLogic;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.GameState;

public class MinigameLogic {

    private GameState gameState;
    private List<String> words;
    private String currentWord;
    private String typedWord;
    private int successCounter = 0;
    private int failCounter = 0;
    private int r;
    private int currentCharIndex = 0;
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

    /**
     * @return word  that will currently be displayed up to currentCharIndex
     * @see checkPartialWord() where on each correct input, the index increases, showing the next character of the word until complete
     * On every incorrect typing of a character, the index is set to 0, efectively showing the first character of the new word
     */
    public String getCurrentWord() {
        return currentWord.substring(0, currentCharIndex + 1); // + 1 to show the first character since currentCharIndex is initalized at 0
    }
    public String getShowedString(){
        return currentWord.substring(currentCharIndex,currentCharIndex + 1);
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
                if(character == '\b' && typedWord.length() > 0){
                    typedWord = typedWord.substring(0, typedWord.length() - 1); // Remove the last character from typedWord
                }else if(character == '\b' && typedWord.length() == 0){
                    //we dont do anything just continue playing
                }
                else if (character != '\r' && character != '\n') {// Any key other than 'Enter'
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
        r = new Random().nextInt(10); // bound to number n of words in List<String> words
        currentWord = words.get(r);
        typedWord = "";
    }

     /**
      * Method to verify typed words read from Player input
      */
      public void checkPartialWord(){
        typeSound.play();

        // First we declare what we expect to be typed in each interation
        String expectedSubstring = getCurrentWord();

        // Then we check if the length of typedWord matches the length of expectedSubstring
        if (typedWord.length() == expectedSubstring.length()){
            // If correct lenghts match, now we check if the chars in typedWord are the same of the expectedSubstring
            if (typedWord.equals(getCurrentWord()) && currentCharIndex < currentWord.length()) { 
                // Finally we verify we are within the bounds of currentWord (excluding the last character).
                if(currentCharIndex < currentWord.length() - 1){
                    startTime = System.currentTimeMillis();
                    // -1 to avoid StringIndexOutOfBoundsException when word is complete
                    currentCharIndex++; // Move index to the next character of the word on every successful typed char
                    typedWord = "";     // Resets the user input field so on each iteration the complete char sequence has to be retyped
                }   
                checkGameState();
                if (typedWord.equals(currentWord) && currentCharIndex == currentWord.length() - 1) { //If the whole word is matched and charIndex now "points" to the whole word
                    successCounter++;
                }
            } else {
                incorrectSound.play();
                currentCharIndex = 0; // On incorrect typing, reset word index to the firt character
                failCounter++;
                checkGameState();
                generateNewWord();
                startTime = System.currentTimeMillis();
            }
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
            currentCharIndex = 0; // On timeout, reset word index to the firt character
            failCounter++;
            checkGameState();
            generateNewWord();
            startTime = System.currentTimeMillis();
        }
    }

}
