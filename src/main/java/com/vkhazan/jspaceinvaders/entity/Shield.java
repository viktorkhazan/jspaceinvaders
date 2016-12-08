package com.vkhazan.jspaceinvaders.entity;


import com.vkhazan.jspaceinvaders.amination.Animation;
import com.vkhazan.jspaceinvaders.main.Game;
import com.vkhazan.jspaceinvaders.resource.util.Assets;
import com.vkhazan.jspaceinvaders.util.collection.Array;

import java.awt.image.BufferedImage;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Shield extends AbstractEntity {

    private Array<BufferedImage[]> animations;

    private final int[] numFrames = {
        1, 7
    } ;
    
    public Shield(int x, int y) {

        width = 20;
        height = 20;

        cHeight = 30;
        cWidth = 30;

        this.x = x;
        this.y = y;

        animations = new Array<>();
        for (int i = 0; i < numFrames.length; i++) {
            BufferedImage[] tmp = new BufferedImage[numFrames[i]];
            for (int j = 0; j < numFrames[i]; j++) {
                tmp[j] = Assets.shieldSpriteSheet.getSubimage(j * width, i * height, width, height);
            }
            animations.add(tmp);
        }
        animation = new Animation();
        animation.setAnimation(animations.get(1));
        animation.setDelay(5);
        animation.setLoop(true);
    }

    @Override
    public void update() {
        animation.update();
    }

    @Override
    public void draw() {
        
        Game.scene.drawImage(animation.getCurrentFrame(), (int)x, (int)y, width, height);
        Game.scene.setColor(0, 0, 255);
                
        Game.scene.drawRect((int)x, (int)y, width, height);
    }

    @Override
    public void interactedBy(AbstractEntity entity) {
        
    }

}
