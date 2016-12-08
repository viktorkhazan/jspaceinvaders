/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vkhazan.jspaceinvaders.amination.factory;


import com.vkhazan.jspaceinvaders.amination.Animation;
import com.vkhazan.jspaceinvaders.resource.util.Assets;
import com.vkhazan.jspaceinvaders.util.collection.Array;

import java.awt.image.BufferedImage;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class SimpleAnimationFactory {

    public static final int ENEMY = 0;



    public SimpleAnimationFactory() {
    }

    public static Array<Animation> loadAnimation(int type, int width, int height, int[] numFrames) {
        
        Array<Animation> animations = new Array<>();
        Animation animation;
        for (int i = 0; i < numFrames.length; i++) {
            BufferedImage[] tmp = new BufferedImage[numFrames[i]];
            animation = new Animation();
            for (int j = 0; j < numFrames[i]; j++) {
                tmp[j] = Assets.enemySpriteSheet.getSubimage(j * width, i * height, width, height);
            }
            animation.setAnimation(tmp);
            animations.add(animation);
        }
        return animations;
    }
}
