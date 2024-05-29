package com.mygdx.game.view;

import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameState;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.LegScreen;


public class MinigameScreen implements Screen {
// attributes for the screen
    private Stage stage;
    private Viewport viewport;

    OrthographicCamera camera;

// attributes for the minigame
    private final MyGdxGame game;
    private GameState gameState;
    private java.util.List<String> words;
    private int successCounter = 0;
    private int failCounter = 0;
    private String currentWord;
    private String typedWord = "";
    private double startTime;
    private double timeLimit;
    private double remainingTime;
    private int r = new Random().nextInt(10);
    // Dimensions of assets
    private final int panelHeight = 180;
    private final int panelWidth = 300;
    private final int timerHeight = 100;
    private final int timerWidth = 100;
    // Assets on screen
    private Texture textOutput;
    private Texture playerInput;
    private Texture timerIcon;
    private BitmapFont font;

// Constructor
    public MinigameScreen(final MyGdxGame game, GameState gameState) {
        this.game = game;
        this.gameState = gameState;
        this.words = Arrays.asList("Dragon", "Boat", "Racing", "Myhtic", "Ancient", "Ritualistic", "China", "Competition", "River", "Tradition");
        this.timeLimit = 8000; // 8 seconds to type the word

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        textOutput = new Texture(Gdx.files.internal("textPanel2.png"));
        playerInput = new Texture(Gdx.files.internal("textPanel2.png"));
        timerIcon = new Texture(Gdx.files.internal("timerIcon.png"));
        /// @brief Gdx.files.internal("Daydream.ttf") cannot be loaded as an arguemnt for the BitmapFont yet
        font = new BitmapFont();
    }


// Methods

    @Override
    public void show() {
        // viewport is used to determine screen dimensions and has various methods to implement a responsive behavior
        viewport = new ExtendViewport(800, 480);
        // stage displays all the actors involved in the screen (UI, buttons, labels, etc)
        stage = new Stage(viewport);

        // Method to read Player input
        //
        // @param character stores the key pressed by the user
        // @method checkWord() process the keystrokes stored in character after 'Enter' key is pressed
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyTyped(char character) {
                // Check word after 'Enter' is pressed (carriage return or newline button)
                if(character == '\r' || character == '\n') {
                    checkWord();
                } else { // Any other key other than 'Enter' is pressed
                    typedWord = typedWord + character; // Register such key on the string
                }

                // If the 'Backspace' button is pressed nothing has to be done
                if(character == '\b'){
                    //TODO avoid visual bugs on execution
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
        private void generateNewWord() {
            currentWord = words.get(r);
            typedWord = "";
        }

        // Method to verify typed words read from Player input
        //
        private void checkWord() {
            // Time to type the word has runned out
            if (remainingTime <= 0){
                failCounter++;
            } else {
                // Verify that typed word is the one showed in the panel
                if (typedWord.equals(currentWord)) {
                    successCounter++;
                } else {
                    failCounter++;
                }
            }
            // After each word check verify if success or fail conditions are met
            checkGameState();
            // Then if needed, show next word
            generateNewWord();
            startTime = System.currentTimeMillis();
        }

        private void checkGameState() {
            if (successCounter == 1) {
                gameState.setBaseHealth(100); // Restore Boat's HP
                gameState.setMinigamePlaysLeft(0); // Signal main game that it cannot return to the minigame (unless a PowerUp updates value)
                game.setScreen(new LegScreen(game)); // Return to the main race
                // TODO
                gameState.getDeathPoint(); // Respawn at death point
                gameState.getMyBoat();     // Return to with previous selected Boat
                gameState.getRivals();     // Return Rivals to the main race
            } else if (failCounter >= 3) {
                // game.setScreen(new GameOverScreen(game)); TODO
            }
        }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();

        // Calculates the remaining time to display real time coundown
        remainingTime = (timeLimit - (System.currentTimeMillis() - startTime)) / 1000f;
        // TODO fix visuals on screen XD
        game.batch.begin();
        // Shows on screen the panel with the word to type
        game.batch.draw(textOutput, 200, 200, panelWidth, panelHeight);
        font.draw(game.batch,"TYPE THE WORD!!",320,370);
        // Calls the firt word to display
        font.draw(game.batch, currentWord, 350, 325);
        // Displays on real time user input
        game.batch.draw(playerInput, 250, 300, panelWidth, panelHeight);
        font.draw(game.batch, "Reply: " + typedWord, 230, 235);
        // Displays timer countdown
        game.batch.draw(timerIcon, 7200, 400, timerWidth, timerHeight);
        font.draw(game.batch, String.format("[%.2f]", remainingTime), 750, 470);

        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }
    
}
