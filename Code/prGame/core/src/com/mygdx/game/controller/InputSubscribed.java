package com.mygdx.game.controller;

import com.mygdx.game.util.UserAction;

public interface InputSubscribed {
    public void listen(boolean press, UserAction keycode);
}
