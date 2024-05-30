package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.SettingModel;
import com.mygdx.game.view.MainMenuView;

public class SettingController {
    private final MyGdxGame game;
    private boolean clickNotPressed = true;
    private boolean awaitingKeyChange = false;
    private String keyChangeTarget = "";

    public SettingController(MyGdxGame game) {
        this.game = game;
    }

    public void handleInput(float mouseX, float mouseY, float changeLeftX, float changeLeftY,
                            float changeRightX, float changeRightY, float muteButtonX, float muteButtonY,
                            float changeButtonWidth, float changeButtonHeight, SettingModel model) {

        // Return to main Menu if "Esc" Key is pressed
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenuView(game));
        }

        // Handle awaiting key change
        if (awaitingKeyChange) {
            for (int i = 0; i < Input.Keys.MAX_KEYCODE; i++) {
                if (Gdx.input.isKeyJustPressed(i)) {
                    String key = Input.Keys.toString(i);
                    if (keyChangeTarget.equals("left")) {
                        model.setLeftKey(key);
                    } else if (keyChangeTarget.equals("right")) {
                        model.setRightKey(key);
                    }
                    awaitingKeyChange = false;
                    keyChangeTarget = "";
                    //When the new assignment of the key is done stop showing message for change it.
                    model.setTextLeftChange(false);
                    model.setTextRightChange(false);

                    break;
                }
            }
        }

        if (Gdx.input.isTouched()) {
            if (clickNotPressed) {
                clickNotPressed = false;

                // ChangeLeftKey
                if (mouseX >= changeLeftX && mouseX <= changeLeftX + changeButtonWidth &&
                        mouseY >= changeLeftY && mouseY <= changeLeftY + changeButtonHeight) {
                    model.setTextRightChange(false);
                    awaitingKeyChange = true;
                    model.setTextLeftChange(true);
                    keyChangeTarget = "left";
                }

                // ChangeRightKey
                if (mouseX >= changeRightX && mouseX <= changeRightX + changeButtonWidth &&
                        mouseY >= changeRightY && mouseY <= changeRightY + changeButtonHeight) {
                    model.setTextLeftChange(false);
                    awaitingKeyChange = true;
                    model.setTextRightChange(true);
                    keyChangeTarget = "right";
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
