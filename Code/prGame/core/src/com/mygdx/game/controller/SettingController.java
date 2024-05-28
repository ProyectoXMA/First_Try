package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.SettingModel;
import com.mygdx.game.view.MainMenuScreen;

import javax.swing.*;

public class SettingController {
    private final MyGdxGame game;
    private boolean clickNotPressed = true;

    public SettingController(MyGdxGame game) {
        this.game = game;
    }

    public void handleInput(float mouseX, float mouseY, float changeLeftX, float changeLeftY,
                            float changeRightX, float changeRightY, float muteButtonX, float muteButtonY,
                            float changeButtonWidth, float changeButtonHeight, boolean isMute, SettingModel model) {

        //Return to main Menu if "Esc" Key is pressed
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenuScreen(game));
        }

        if (Gdx.input.isTouched()) {
            if (clickNotPressed) {
                clickNotPressed = false;

                // ChangeLeftKey
                if (mouseX >= changeLeftX && mouseX <= changeLeftX + changeButtonWidth &&
                        mouseY >= changeLeftY && mouseY <= changeLeftY + changeButtonHeight) {
                    //
                    
                }

                // ChangeRightKey
                if (mouseX >= changeRightX && mouseX <= changeRightX + changeButtonWidth &&
                        mouseY >= changeRightY && mouseY <= changeRightY + changeButtonHeight) {

                }

                // ChangeMuteValue
                if (mouseX >= muteButtonX && mouseX <= muteButtonX + changeButtonWidth &&
                        mouseY >= muteButtonY && mouseY <= muteButtonY + changeButtonHeight) {
                    model.changeMute();
                }
            }
        } else {
            clickNotPressed = true;
        }
    }
}
