package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.ChooseBoatModel;
import com.mygdx.game.view.MainMenuScreen;

public class ChooseBoatController {
    private final MyGdxGame game;
    private boolean clickNotPressed = true;

    public ChooseBoatController(MyGdxGame game) {
        this.game = game;
    }

    public void handleInput(float mouseX, float mouseY, float leftButtonX, float leftButtonY, float arrowWidth, float arrowHeight,
                            float rightButtonX, float rightButtonY, float chooseButtonX, float chooseButtonY,
                            float chooseButtonWidth, float chooseButtonHeight, ChooseBoatModel model) {
        if (Gdx.input.isTouched()) {
            if (clickNotPressed) {
                clickNotPressed = false;

                // LeftButtonPressed
                if (mouseX >= leftButtonX && mouseX <= leftButtonX + arrowWidth &&
                        mouseY >= leftButtonY && mouseY <= leftButtonY + arrowHeight) {
                    model.previousBoat();
                }

                // RightButtonPressed
                if (mouseX >= rightButtonX && mouseX <= rightButtonX + arrowWidth &&
                        mouseY >= rightButtonY && mouseY <= rightButtonY + arrowHeight) {
                    model.nextBoat();
                }

                // ChooseButtonPressed
                if (mouseX >= chooseButtonX && mouseX <= chooseButtonX + chooseButtonWidth &&
                        mouseY >= chooseButtonY && mouseY <= chooseButtonY + chooseButtonHeight) {
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        } else {
            clickNotPressed = true;
        }
    }
}
