package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class SettingModel implements Disposable {
    private boolean isMute;
    private final Texture mute;
    private final Texture unMute;

    public SettingModel(boolean isMute) {
        this.isMute=isMute;
        mute = new Texture("boatMenu0.png");
        unMute = new Texture("boatMenu1.png");

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
}

