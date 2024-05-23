package com.mygdx.game.view;

import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameState;
import com.mygdx.game.MyGdxGame;


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
    private String currentWord;
    private String typedWord = "";
    private long startTime;
    private long timeLimit;
    private int r = new Random().nextInt(3);
    // Dimensions of the panel to display instructions and user input
    private final int panelHeight = 240;
    private final int panelWidth = 400;
    private Texture textoutput;
    private BitmapFont font;

// Constructor
    public MinigameScreen(final MyGdxGame game, GameState gameState) {
        this.game = game;
        this.gameState = gameState;
        this.words = Arrays.asList("Dragon", "Boat", "Racing", "Myhtic", "Ancient", "Ritualistic", "China", "Competition", "River", "Tradition");
        this.timeLimit = 8000; // 8 seconds to type the word

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        textoutput = new Texture(Gdx.files.internal("textPanel.png"));
        /// @brief Gdx.files.internal("Daydream.ttf") cannot be loaded as an arguemnt for the BitmapFont yet
        font = new BitmapFont();
    }


// Methods

    /// @param stage displays all the actors involved in the screen (UI, buttons, labels, etc)
    @Override
    public void show() {
        viewport = new ExtendViewport(800, 480);
        stage = new Stage(viewport);

        // Method to read user input
        //
        // @param character stores the key pressed by the user
        // @method checkWord() process the keystrokes stored in character after 'Enter' key is pressed
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyTyped(char character) {
                // Check word after 'Enter' is pressed
                if (character == '\r' || character == '\n') {
                    //checkWord(); //TODO
                } else { // Any other key other than 'Enter' is pressed
                    typedWord += character; // Register such key on the string
                }

                return true;
            }
        });

    }

    // for now just creates a blank screen
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();

        game.batch.begin();
        game.batch.draw(textoutput, 200, 200, panelWidth, panelHeight);
        font.draw(game.batch,"TYPE THE WORD!!",320,370);
        game.font.draw(game.batch,words.get(r),350,325);
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
