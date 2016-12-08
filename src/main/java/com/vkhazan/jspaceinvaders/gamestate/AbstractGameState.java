package com.vkhazan.jspaceinvaders.gamestate;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public abstract class AbstractGameState {

    public abstract void init();

    public abstract void update();

    public abstract void draw();
    

}
