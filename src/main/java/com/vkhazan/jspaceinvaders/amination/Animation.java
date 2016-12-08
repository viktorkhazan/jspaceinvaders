package com.vkhazan.jspaceinvaders.amination;

import java.awt.image.BufferedImage;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Animation {

    private BufferedImage[] animation;
    private int currentFrame;

    private boolean playedOnce;

    private int delay;

    private int frameElapsed;
    
    private boolean loop;

    public Animation() {
        playedOnce = false;
    }

    public void update() {

        if (++frameElapsed < delay) {
            return;
        }
        frameElapsed = 0;
        if (++currentFrame >= animation.length) {
            playedOnce = true;
            if (loop) {
                currentFrame = 0;
            } else {
            currentFrame = animation.length - 1;
            }
        }
    }

    public void setAnimation(BufferedImage[] animation) {
        this.animation = animation;
        currentFrame = 0;
        playedOnce = false;
    }

    public void reset() {
        currentFrame = 0;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    
    public void setFrame(int index) {
        currentFrame = index;
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }

    public BufferedImage getCurrentFrame() {
        return animation[currentFrame];
    }
}
