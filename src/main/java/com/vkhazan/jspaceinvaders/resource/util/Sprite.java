package com.vkhazan.jspaceinvaders.resource.util;

import java.awt.image.BufferedImage;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Sprite {
    
    private BufferedImage image;

    public Sprite(BufferedImage sprite) {
        this.image = sprite;
    }

    public BufferedImage getSprite() {
        return image;
    }
}
