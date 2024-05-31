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

    //KeyAssignments variable
    private String leftKey;
    private String rightKey;
    private boolean isMute;
    private boolean textLeftChange;
    private boolean textRightChange;

    // Texture for Button
    Texture changeButton;
    Texture changeButtonSel;
    Texture muteButton;
    Texture muteButtonSel;
    Texture saveButton;
    Texture saveButtonSel;

    // Dimension for Button
    private final int buttonHeight = Config.getHeight()/13;
    private final int buttonWidth = Config.getWidth()/5;
    private final int changeLeftX = (Config.getWidth()) - (Config.getWidth()/3) - buttonWidth;
    private final int changeLeftY = (Config.getHeight()/2) + buttonHeight + 20;
    private final int changeRightX = (Config.getWidth()) - (Config.getWidth()/3) - buttonWidth;
    private final int changeRightY = (Config.getHeight()/2);
    private final int muteButtonX = (Config.getWidth()) - (Config.getWidth()/3) - buttonWidth;
    private final int muteButtonY = (Config.getHeight()/2) - buttonHeight - 20;
    private final int saveButtonX = (Config.getWidth()) - (Config.getWidth()/3) - buttonWidth;
    private final int saveButtonY = (Config.getHeight()/2) - buttonHeight *2 - 80;
    private final int volumeImageX = muteButtonX-100;
    private final int volumeImageY = (Config.getHeight()/2) - buttonHeight - 20;
    private final int volumeImageWidth = 90;
    private final int volumeImageHeight = 90;

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
        camera.setToOrtho(false, Config.getWidth(), Config.getHeight());

        //Key assignation
        this.isMute=isMute;
        this.leftKey=leftKey;
        this.rightKey=rightKey;
        textLeftChange=false;
        textRightChange=false;


        //Initialize Font
        font = new BitmapFont();

        // Initialize Buttons
        changeButton = new Texture(Gdx.files.internal("buttons/change.png"));
        changeButtonSel = new Texture(Gdx.files.internal("buttons/change_sel.png"));
        muteButton = new Texture(Gdx.files.internal("buttons/mute.png"));
        muteButtonSel = new Texture(Gdx.files.internal("buttons/mute_sel.png"));
        saveButton = new Texture(Gdx.files.internal("buttons/save.png"));
        saveButtonSel = new Texture(Gdx.files.internal("buttons/save_sel.png"));
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(Config.getWidth(), Config.getHeight());
        stage = new Stage(viewport);
    }

    /**
     * This method consider if the mouse is inside a button.
     * @param mouseX position in the axis x of the mouse
     * @param mouseY position in the axis y of the mouse
     * @param x position in the axis x of the button
     * @param y position in the axis y of the button
     * @param width total width of the button
     * @param height total height of the button
     **/
    private boolean isInsideButton(float mouseX, float mouseY, float x, float y, float width, float height) {
        return (mouseX >= x && mouseX <= x + width) && (mouseY >= y && mouseY <= y + height);
    }

    /**
     * This method draw a button considering the original image
     * and the one when the mouse is of that button.
     * @param mouseX position in the axis x of the mouse
     * @param mouseY position in the axis y of the mouse
     * @param x position in the axis x of the button
     * @param y position in the axis y of the button
     * @param width total width of the button
     * @param height total height of the button
     * @param defaultTexture is the original image
     * @param hoveredTexture is the image to represent that the mouse is inside a button.
     **/
    private void drawButton(float mouseX, float mouseY, float x, float y, float width, float height, Texture defaultTexture, Texture hoveredTexture) {
        boolean isInside = isInsideButton(mouseX, mouseY, x, y, width, height);
        Texture buttonTexture = isInside ? hoveredTexture : defaultTexture;
        game.batch.draw(buttonTexture, x, y, width, height);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.5f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();

        // Mouse coordinates
        float mouseX = Gdx.input.getX();
        float mouseY = Config.getHeight() - Gdx.input.getY();

        //Draw buttons
        game.batch.begin();
        drawButton(mouseX, mouseY, changeLeftX, changeLeftY, buttonWidth, buttonHeight, changeButton, changeButtonSel);
        drawButton(mouseX, mouseY, changeRightX, changeRightY, buttonWidth, buttonHeight, changeButton, changeButtonSel);
        drawButton(mouseX, mouseY, muteButtonX, muteButtonY, buttonWidth, buttonHeight, muteButton, muteButtonSel);
        drawButton(mouseX, mouseY, saveButtonX, saveButtonY, buttonWidth, buttonHeight, saveButton, saveButtonSel);
        game.batch.draw(model.getCurrentVolumeTexture(), volumeImageX, volumeImageY, volumeImageWidth, volumeImageHeight);

        //Draw fonts
        font.setColor(new Color(0,0,0,1));
        font.getData().setScale(500);

        //Variables for the font which is next to the buttons.
        float fontKeyLeftX =(Config.getWidth()) - (Config.getWidth() /3) - buttonWidth -150;
        float fontKeyLeftY =(Config.getHeight() /2) + buttonHeight + 20 + ((float) buttonHeight /2);
        float fontKeyRightX =(Config.getWidth()) - ((float) Config.getWidth() /3) - buttonWidth -150;
        float fontKeyRightY =((float) Config.getHeight() /2) + ((float) buttonHeight /2);
        float fontVolumeX =((Config.getWidth()) - ((float) Config.getWidth() /3) - buttonWidth -150);
        float fontVolumeY =((float) Config.getHeight() /2) - buttonHeight - 20 + ((float) buttonHeight /2);
        float fontEscX =((float) Config.getWidth() /2)-90;
        float fontEscY =20;

        game.font.draw(game.batch,("Left Key:  ") + "[ " + model.getLeftKey()+ " ]", fontKeyLeftX, fontKeyLeftY);
        game.font.draw(game.batch,("Right Key:  "+ "[ " +model.getRightKey()+" ]"), fontKeyRightX, fontKeyRightY);
        game.font.draw(game.batch,"Volume:  ", fontVolumeX, fontVolumeY);
        game.font.draw(game.batch,"Press Esc to return to Main Menu ",fontEscX, fontEscY);

        //When you want to change the key settings a message is display otherwise not.
        if (model.isTextLeftChange()){
            float fontConfX = (Config.getWidth()) - ((float) Config.getWidth() /3) + 20;
            float fontConfY = ((float) Config.getHeight() /2) + buttonHeight + 20 + ((float) buttonHeight /2);
            game.font.draw(game.batch,"Press the new Key to assigned to Right movement. Remember to save changes.", fontConfX, fontConfY);
        }
        if (model.isTextRightChange()){
            float fontConfX = (Config.getWidth()) - ((float) Config.getWidth() /3) + 20;
            float fontConfY = ((float) Config.getHeight() /2) + ((float) buttonHeight /2);
            game.font.draw(game.batch,"Press the new Key to assigned to Right movement. Remember to save changes.", fontConfX, fontConfY);

        }

        game.batch.end();

        // Handle the input in SettingController
        controller.handleInput(mouseX, mouseY, changeLeftX, changeLeftY, changeRightX,
                changeRightY, muteButtonX, muteButtonY, saveButtonX, saveButtonY, buttonWidth, buttonHeight, model);
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
        changeButton.dispose();
        changeButton.dispose();
        muteButton.dispose();
        saveButton.dispose();
        model.dispose();
        font.dispose();
    }
}
