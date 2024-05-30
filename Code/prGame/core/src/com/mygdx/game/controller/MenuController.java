package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.util.Config;
import com.mygdx.game.view.RaceScreen;

public class MenuController {
    //This variable is used to store the game object.
    private final MyGdxGame game;

    //default button dimensions
    //some of them were put as public in order to be used in other menu screens
    public int BUTTON_WIDTH = Config.getWidth()/5;
    public int BUTTON_HEIGHT = Config.getHeight()/10;

    private final int buttonX = (Config.getWidth() - BUTTON_WIDTH) /2;
    private final int buttonY =  ((Config.getHeight())/2) + BUTTON_HEIGHT;


    /**
     * This constructor is used to create a new MenuController object.
     * @param game The game object.
     */
    public MenuController(MyGdxGame game) {
        this.game = game;
    }

    /**
     * This method is used to check whether the user is hovering over a button.
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     * @param x X position of the button
     * @param y Y position of the button
     * @return True if the mouse is inside the button, false otherwise.
     */
    public boolean isInsideButton(float mouseX, float mouseY, float x, float y) {
        //Logic to see if buttons are hovered
        return mouseX >= x && mouseX <= x + BUTTON_WIDTH &&
                mouseY >= y && mouseY <= y + BUTTON_HEIGHT;
    }



    /**
     * This function checks if the mouse is clicked
     * @param mouseX mouse x value
     * @param mouseY mouse y value
     */
    public void checkInput(float mouseX, float mouseY) {
        // Logic to detect if buttons are clicked
        if (Gdx.input.isTouched()) onButtonClicked(mouseX, mouseY);
    }

    /**
     * This method is called if input of the mouse is received.
     * Depending on the selected button it will do certain action.
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     */
    public void onButtonClicked(float mouseX, float mouseY) {
        float margin = (float) BUTTON_HEIGHT / 4;
        float playButtonY = getButtonY();
        float selectionButtonY = playButtonY - BUTTON_HEIGHT - margin;
        float tutorialButtonY = selectionButtonY - BUTTON_HEIGHT - margin;
        float settingsButtonY = tutorialButtonY - BUTTON_HEIGHT - margin;
        float quitButtonY = settingsButtonY - BUTTON_HEIGHT - margin;

        boolean insidePlay = isInsideButton(mouseX, mouseY, getButtonX(), playButtonY);
        boolean insideBoat = isInsideButton(mouseX, mouseY, getButtonX(), selectionButtonY);
        boolean insideTutorial = isInsideButton(mouseX, mouseY, getButtonX(), tutorialButtonY);
        boolean insideSettings = isInsideButton(mouseX, mouseY, getButtonX(), settingsButtonY);
        boolean insideQuit = isInsideButton(mouseX, mouseY, getButtonX(), quitButtonY);

        //Play
        if (insidePlay) {
            game.setScreen(new RaceScreen(game));
        }
        //Choose Boat
        if (insideBoat) {
            game.setScreen(new RaceScreen(game));
        }
        //Tutorial
        if (insideTutorial) {
            game.setScreen(new RaceScreen(game));
        }
        //Settings
        if (insideSettings) {
            //settingsMenu();
            game.dispose();
        }
        //Quit
        if (insideQuit) {
            Gdx.app.exit();
        }
    }

    //all the getters
    public int getButtonX() {
        return buttonX;
    }

    public int getButtonY() {
        return buttonY;
    }

}
