package com.mygdx.game.model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class SettingModel implements Disposable {
    private boolean isMute;
    private final Texture mute;
    private final Texture unMute;
    private String leftKey;
    private String rightKey;

    //For seeing the text message for change the key assignment:
    private boolean textLeftChange;
    private boolean textRightChange;

    public SettingModel(boolean isMute) {
        this.isMute=isMute;
        leftKey= Input.Keys.toString(Input.Keys.LEFT);
        rightKey= Input.Keys.toString(Input.Keys.RIGHT);
        mute = new Texture("mute.png");
        unMute = new Texture("unMute.png");
        textLeftChange=false;
        textRightChange=false;
    }

    @Override
    public void dispose() {
        mute.dispose();
        unMute.dispose();
    }

    public void changeMute() {
        isMute = !isMute;
    }

    public Texture getCurrentVolumeTexture() {
        if (isMute){
            return mute;
        }else return unMute;
    }

    public String getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(String leftKey) {
        this.leftKey = leftKey;
    }

    public String getRightKey() {
        return rightKey;
    }

    public void setRightKey(String rightKey) {
        this.rightKey = rightKey;
    }

    public boolean isTextRightChange() {
        return textRightChange;
    }

    public void setTextRightChange(boolean textRightChange) {
        this.textRightChange = textRightChange;
    }

    public boolean isTextLeftChange() {
        return textLeftChange;
    }

    public void setTextLeftChange(boolean textLeftChange) {
        this.textLeftChange = textLeftChange;
    }
}

