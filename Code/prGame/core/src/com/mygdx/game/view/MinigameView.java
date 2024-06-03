package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.MinigameController;
import com.mygdx.game.controller.PauseController;
import com.mygdx.game.model.minigameLogic.MinigameLogic;
import com.mygdx.game.util.Config;


public class MinigameView {
// Attributes for the screen
    private Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private OrthographicCamera camera;
// Attributes for the minigame
    private final MyGdxGame game;
    private final MinigameLogic minigameLogic;
// Dimensions of assets
    //private final int panelHeight = 400;
    //private final int panelWidth = 350;
    private final int timerHeight = 20;
    private final int timerWidth = 20;

// Assets on screen
    private Texture backgroundImage;
    //private Texture textOutput;
    //private Texture playerInput;
    private Texture timerIcon;
    private BitmapFont font;
    Music epicMusic;
    GlyphLayout glyphLayout;


// Constructor
    public MinigameView(final MyGdxGame game, MinigameLogic minigameLogic) {
        this.game = game;
        this.minigameLogic = minigameLogic;
        stage = new Stage();
        batch = new SpriteBatch();

        epicMusic = Gdx.audio.newMusic(Gdx.files.internal("countdownMusic.mp3"));
        epicMusic.setLooping(true);
        if(!Config.muted) epicMusic.setVolume((float)0.1);
        else epicMusic.setVolume(0);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.getWidth(), Config.getHeight());

        backgroundImage = new Texture(Gdx.files.internal("minigameBackground.png"));
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
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        font.getData().setScale(10,10);
    }


// Methods
    /**
     * This method is called by the controller when the screen is set
     * Viewport is used to determine screen dimensions and has various methods to implement a responsive behavior
     * stage displays all the actors involved in the screen (UI, buttons, labels, etc)
     */
    public void show() {
        viewport = new ExtendViewport(Config.getWidth(), Config.getHeight());
        stage = new Stage(viewport);
        epicMusic.play();
    }

    public void update() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();

        batch.begin();
        batch.draw(backgroundImage,0,0, Config.getWidth(), Config.getHeight());
        if(Config.getHeight() >= 800 && Config.getWidth() >= 1500){
            String text = "MEMORIZE-TYPE!!";
            glyphLayout.setText(font, text);                                                  
            font.draw(batch,text,(Config.getWidth()/2)-70,Config.getHeight()-70);
        }else{
            String text = "MEMORIZE-TYPE!!";
            glyphLayout.setText(font, text);                                                  
            font.draw(batch,text,(Config.getWidth()/2)-70,Config.getHeight()-50);
        }
        // Background sceneary

        // Calls the first word to display
        if(Config.getHeight() >= 800 && Config.getWidth() >= 1500){
            String character = minigameLogic.getShowedString();
            glyphLayout.setText(font, character);
            font.draw(batch, character,(Config.getWidth()/2)-10,Config.getHeight()-120);
        }else{
            String character = minigameLogic.getShowedString();
            glyphLayout.setText(font, character);
            font.draw(batch, character,(Config.getWidth()/2)-10,Config.getHeight()-90);
        }
        // Displays on real time user input
        if(Config.getHeight() >= 800 && Config.getWidth() >= 1500){
            String reply = "Reply: " + minigameLogic.getTypedWord();
            glyphLayout.setText(font, reply);
            font.draw(batch,reply, (Config.getWidth()/2)-80,(Config.getHeight()/17)+100);
        }else{
            String reply = "Reply: " + minigameLogic.getTypedWord();
            glyphLayout.setText(font, reply);
            font.draw(batch,reply, (Config.getWidth()/2)-80,(Config.getHeight()/17)+60);
        }
        // Displays timer countdown        
        if(Config.getHeight() >= 800 && Config.getWidth() >= 1500){
            String timer = String.format("[%.2f]", minigameLogic.getRemainingTime());
            glyphLayout.setText(font, timer);           
            batch.draw(timerIcon, Config.getWidth()-70, Config.getHeight()-45, timerWidth, timerHeight);
            font.draw(batch,timer, Config.getWidth()-40, Config.getHeight()-30);
        }else{
            String timer = String.format("[%.2f]", minigameLogic.getRemainingTime());
            glyphLayout.setText(font, timer);           
            batch.draw(timerIcon, Config.getWidth()-60, Config.getHeight()-25, timerWidth, timerHeight);
            font.draw(batch,timer, Config.getWidth()-40, Config.getHeight()-10);
        }
        // Displays to the user the attempts left to try to complete the minigame before Game Over        
        if(Config.getHeight() >= 800 && Config.getWidth() >= 1500){
            String timeLeft = "Remaining attempts: " + (3-minigameLogic.getFailCounter());
            glyphLayout.setText(font, timeLeft);
            font.draw(batch,timeLeft,(Config.getWidth()/2)-80,(Config.getHeight()/17));
        }else{
            String timeLeft = "Remaining attempts: " + (3-minigameLogic.getFailCounter());
            glyphLayout.setText(font, timeLeft);
            font.draw(batch,timeLeft,(Config.getWidth()/2)-80,(Config.getHeight()/17));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            epicMusic.stop();
            game.setScreen(new PauseController(game));
        }
        batch.end();
    }
    public void dispose() {
        epicMusic.stop();
        epicMusic.dispose();
        backgroundImage.dispose();
        stage.dispose();
        font.dispose();
        batch.dispose();
    }
    
}
