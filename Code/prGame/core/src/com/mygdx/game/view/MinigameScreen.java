package com.mygdx.game.view;

import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
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
import com.mygdx.game.model.minigameLogic.minigameLogic;


public class MinigameScreen implements Screen {
// attributes for the screen
    private Stage stage;
    private Viewport viewport;

    OrthographicCamera camera;

// attributes for the minigame
    private final MyGdxGame game;
    private GameState gameState;
    private minigameLogic minigameLogic;
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
        epicMusic = Gdx.audio.newMusic(Gdx.files.internal("countdownMusic.mp3"));
        epicMusic.setLooping(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.minigameLogic = new minigameLogic(gameState);

        backgroundImage = new Texture(Gdx.files.internal("possible_background.png"));
        textOutput = new Texture(Gdx.files.internal("textPanel2.png"));
        playerInput = new Texture(Gdx.files.internal("textPanel2.png"));
        timerIcon = new Texture(Gdx.files.internal("timerIcon.png"));
        /// @brief Gdx.files.internal("Daydream.ttf") cannot be loaded as an arguemnt for the BitmapFont yet
        glyphLayout = new GlyphLayout();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(10);
    }


// Methods

    @Override
    public void show() {
        // viewport is used to determine screen dimensions and has various methods to implement a responsive behavior
        viewport = new ExtendViewport(800, 480);
        // stage displays all the actors involved in the screen (UI, buttons, labels, etc)
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
        minigameLogic.run();
        int res = minigameLogic.checkGameState();
        if(res == 1){
            epicMusic.stop();
            game.setScreen(new RaceScreen(game));
        }else if(res == 3){
            epicMusic.stop();
            game.setScreen(new LoseScreen(game));
        }
        game.batch.begin();
        // Background sceneary
        //
        // @var viewport.getScreenHeight(), viewport.getScreenWidth() adjusts the image to fit the current screen dimensions
        game.batch.draw(backgroundImage,-100, -100, 1000, 800);
        // Shows on screen the panel with the word to type
        game.batch.draw(textOutput, 250, 250, panelWidth, panelHeight);
        String text = "TYPE THE WORD!!";
        glyphLayout.setText(font, text);                                                  
        game.font.draw(game.batch,text,325,400);
        // Calls the firt word to display
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
