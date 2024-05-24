package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameState;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.MinigameController;
import com.mygdx.game.model.minigameLogic.MinigameLogic;


public class MinigameScreen implements Screen {
// Attributes for the screen
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;

// Attributes for the minigame
    private final MyGdxGame game;
    private final GameState gameState;
    private final MinigameLogic minigameLogic;
    private final MinigameController minigameController;

// Dimensions of assets
    private final int panelHeight = 180;
    private final int panelWidth = 300;
    private final int timerHeight = 20;
    private final int timerWidth = 20;

// Assets on screen
    private Texture backgroundImage;
    private Texture textOutput;
    private Texture playerInput;
    private Texture timerIcon;
    private BitmapFont font;
    Music epicMusic;
    GlyphLayout glyphLayout;


// Constructor
    public MinigameScreen(final MyGdxGame game, GameState gameState) {
        this.game = game;
        this.gameState = gameState;
        this.minigameLogic = new MinigameLogic(gameState);
        this.minigameController = new MinigameController(game, minigameLogic);

        epicMusic = Gdx.audio.newMusic(Gdx.files.internal("epicMusic.mp3"));
        epicMusic.setLooping(true);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        backgroundImage = new Texture(Gdx.files.internal("possible_background.png"));
        textOutput = new Texture(Gdx.files.internal("textPanel2.png"));
        playerInput = new Texture(Gdx.files.internal("textPanel2.png"));
        timerIcon = new Texture(Gdx.files.internal("timerIcon.png"));
        /**
         * Text formatting for the minigame
         * 
         * Daydream.ttf is the candidate font (still deciding)
         * 
         * BitmapFont is used to draw text on the screen and 
         * GlyphLayout is used to compute the size of the text.
         * font creates a new BitmapFont for drawing text.
         */  
        glyphLayout = new GlyphLayout();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(10);
    }


// Methods

    @Override
    public void show() {
        /**
         * viewport is used to determine screen dimensions and has various methods to implement a responsive behavior
         * stage displays all the actors involved in the screen (UI, buttons, labels, etc)
         */
        viewport = new ExtendViewport(800, 480);
        stage = new Stage(viewport);
        minigameLogic.generateAdapter();
        epicMusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();
        
        // Checks whether the player can return to the main race of end run on Game Over
        minigameController.checkScreenTransition();

        game.batch.begin();

        // Background sceneary
        game.batch.draw(backgroundImage,-100, -100, 1000, 800);
        // Shows on screen the panel with the word to type
        game.batch.draw(textOutput, 250, 250, panelWidth, panelHeight);
        String text = "TYPE THE WORD!!";
        glyphLayout.setText(font, text);                                                  
        game.font.draw(game.batch,text,325,400);
        // Calls the first word to display
        String character = minigameLogic.getCurrentWord();
        glyphLayout.setText(font, character);
        game.font.draw(game.batch, character, 325, 350);
        // Displays on real time user input
        String reply = "Reply: " + minigameLogic.getTypedWord();
        glyphLayout.setText(font, reply);
        game.batch.draw(playerInput, 250, 50, panelWidth, 80);
        game.font.draw(game.batch,reply, 275,100);
        // Displays timer countdown
        String timer = String.format("[%.2f]", minigameLogic.getRemainingTime());
        glyphLayout.setText(font, timer);           
        game.batch.draw(timerIcon, 725, 454, timerWidth, timerHeight);
        game.font.draw(game.batch,timer, 750, 470);
        // Displays to the user the attempts left to try to complete the minigame before Game Over
        String timeLeft = "Remaining attempts: " + (3-minigameLogic.getFailCounter());
        glyphLayout.setText(font, timeLeft);
        game.font.draw(game.batch,timeLeft,300,50);

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
