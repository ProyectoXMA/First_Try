package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.SettingController;
import com.mygdx.game.model.SettingModel;
import com.mygdx.game.util.Config;

public class SettingScreen implements Screen {
    private final MyGdxGame game;
    private final SettingController controller;
    private final SettingModel model;
    OrthographicCamera camera;

    // Attributes for the screen
    private Stage stage;
    private Viewport viewport;

    //Initialize for put font into the screen instead of a button.
    private final BitmapFont font;

    //KeyAssignements
    private String leftKey;
    private String rightKey;
    private boolean isMute;
    private boolean textLeftChange;
    private boolean textRightChange;

    // Texture for Button
    Texture changeLeftButton;
    Texture changeLeftButtonSel;
    Texture changeRightButton;
    Texture changeRightButtonSel;
    Texture muteButton;
    Texture muteButtonSel;
    Texture saveButton;
    Texture saveButtonSel;

    // Dimension for Button
    private final int changeButtonHeight = 70;
    private final int changeButtonWidth = 100;

    private final int changeLeftX = (Config.WIDTH) - (Config.WIDTH/3) -changeButtonWidth;
    private final int changeLeftY = (Config.HEIGHT/2) + changeButtonHeight + 20;
    private final int changeRightX = (Config.WIDTH) - (Config.WIDTH/3) - changeButtonWidth;
    private final int changeRightY = (Config.HEIGHT/2);
    private final int muteButtonX = (Config.WIDTH) - (Config.WIDTH/3) -changeButtonWidth;
    private final int muteButtonY = (Config.HEIGHT/2) - changeButtonHeight - 20;
    private final int saveButtonX = (Config.WIDTH) - (Config.WIDTH/3) -changeButtonWidth;
    private final int saveButtonY = (Config.HEIGHT/2) - changeButtonHeight*2 - 80;

    //Constructor
    /**
     * This method is the constructor.
     * @param game as usually received the game.
     * @param leftKey gives the actual assignment of the key dedicated to move to left.
     * @param rightKey gives the actual assignment of the key dedicated to move to right.
     * @param isMute is an attribute that tell us if the game is mute or not while accessing to settings.
     */
    public SettingScreen(final MyGdxGame game, String leftKey, String rightKey, boolean isMute) {
        this.game = game;

        this.model = new SettingModel(isMute);
        this.controller = new SettingController(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WIDTH, Config.HEIGHT);

        //Key assignation
        this.isMute=isMute;
        this.leftKey=leftKey;
        this.rightKey=rightKey;
        textLeftChange=false;
        textRightChange=false;


        //Initialize Font
        font = new BitmapFont();

        // Initialize Buttons
        changeLeftButton = new Texture(Gdx.files.internal("changeButton.png"));
        changeLeftButtonSel = new Texture(Gdx.files.internal("changeButtonSel.png"));
        changeRightButton = new Texture(Gdx.files.internal("changeButton.png"));
        changeRightButtonSel = new Texture(Gdx.files.internal("changeButtonSel.png"));
        muteButton = new Texture(Gdx.files.internal("muteButton.png"));
        muteButtonSel = new Texture(Gdx.files.internal("muteButtonSel.png"));

        saveButton = new Texture(Gdx.files.internal("saveButton.png"));
        saveButtonSel = new Texture(Gdx.files.internal("saveButtonSel.png"));
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
    }

    private boolean isInsideButton(float mouseX, float mouseY, float x, float y, float width, float height) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }

    private void drawButton(float mouseX, float mouseY, float x, float y, float width, float height, Texture defaultTexture, Texture hoveredTexture) {
        boolean isInside = isInsideButton(mouseX, mouseY, x, y, width, height);
        Texture buttonTexture = isInside ? hoveredTexture : defaultTexture;
        game.batch.draw(buttonTexture, x, y, width, height);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();

        // Mouse coordinates
        float mouseX = Gdx.input.getX();
        float mouseY = Config.HEIGHT - Gdx.input.getY();

        //Draw buttons
        game.batch.begin();
        drawButton(mouseX, mouseY, changeLeftX, changeLeftY, changeButtonWidth, changeButtonHeight, changeLeftButton, changeLeftButtonSel);
        drawButton(mouseX, mouseY, changeRightX, changeRightY, changeButtonWidth, changeButtonHeight, changeRightButton, changeRightButtonSel);
        drawButton(mouseX, mouseY, muteButtonX, muteButtonY, changeButtonWidth, changeButtonHeight, muteButton, muteButtonSel);
        drawButton(mouseX, mouseY, saveButtonX, saveButtonY, changeButtonWidth, changeButtonWidth, saveButton, saveButtonSel);

        game.batch.draw(model.getCurrentVolumeTexture(), muteButtonX-100, muteButtonY, 90, 90);
        //Draw fonts
            font.setColor(new Color(0,0,0,1));
            font.getData().setScale(500);


            game.font.draw(game.batch,("Left Key:  ") + "[ " + model.getLeftKey()+ " ]",(Config.WIDTH) - (Config.WIDTH/3) -changeButtonWidth -150, (Config.HEIGHT/2) + changeButtonHeight + 20 + changeButtonHeight/2);
            game.font.draw(game.batch,("Right Key:  "+ "[ " +model.getRightKey()+" ]"),(Config.WIDTH) - (Config.WIDTH/3) -changeButtonWidth -150, (Config.HEIGHT/2) + changeButtonHeight/2);
            game.font.draw(game.batch,"Volume:  ",(Config.WIDTH) - (Config.WIDTH/3) -changeButtonWidth -150, (Config.HEIGHT/2) - changeButtonHeight - 20 + changeButtonHeight/2);

            game.font.draw(game.batch,"Press Esc to return to Main Menu ",(Config.WIDTH/2)-90, 20);

            //When you want to change the key settings a message is display otherwise not.
            if (model.isTextLeftChange()){
                game.font.draw(game.batch,"Press the new Key to assigned to Right movement. Remember to save changes.",(Config.WIDTH) - (Config.WIDTH/3) + 20, (Config.HEIGHT/2) +  changeButtonHeight + 20 + changeButtonHeight/2);
            }
            if (model.isTextRightChange()){
                game.font.draw(game.batch,"Press the new Key to assigned to Right movement. Remember to save changes.",(Config.WIDTH) - (Config.WIDTH/3) + 20, (Config.HEIGHT/2) + changeButtonHeight/2);
            }


        game.batch.end();

        // Handle input
        controller.handleInput(mouseX, mouseY, changeLeftX, changeLeftY, changeRightX, changeRightY, muteButtonX, muteButtonY,changeButtonWidth, changeButtonHeight, model);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        changeLeftButton.dispose();
        changeLeftButton.dispose();
        muteButton.dispose();
        saveButton.dispose();
        model.dispose();
        font.dispose();
    }
}
