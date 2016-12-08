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
public class Beam extends AbstractEntity {

    public static final int EDLE = 0;
    public static final int HIT = 1;
    private Array<BufferedImage[]> sprites;
    private boolean remove;
    private boolean hit;
    private final int numFrames[] = {
        2, 5
    };

    public Beam(double x, double y) {
        this.x = x;
        this.y = y;

        width = 5;
        height = 7;

        dy = 3;

        sprites = new Array<>();

        for (int i = 0; i < numFrames.length; i++) {
            BufferedImage[] tmp = new BufferedImage[numFrames[i]];
            for (int j = 0; j < numFrames[i]; j++) {
                tmp[j] = Assets.beamSpriteSheet.getSubimage(j * width, i * height, width, height);
            }
            sprites.add(tmp);
        }

        animation = new Animation();
        animation.setAnimation(sprites.get(EDLE));
        animation.setDelay(100);

        hit = false;

    }

    @Override
    public void update() {

        animation.update();

        y += dy;
        fixBounds();

        ///if (!hit && (y == GamePanel.GAME_PANEL_HEIGHT - 10)) {
        //     setHit();
        // }
        if (hit && animation.hasPlayedOnce()) {
            remove = true;
        }
    }

    @Override
    public void draw() {
//        Game.getScene().setColor(0, 255, 0);
//        Game.getScene().drawRect((int) x, (int) y, width, height);
        Game.getScene().drawImage(animation.getCurrentFrame(), (int) x, (int) y, width, height);
    }

    @Override
    public void interactedBy(AbstractEntity entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void fixBounds() {
    }

    public void setHit() {
        if (hit) {
            return;
        }
        dy = 0;
        hit = true;
        animation.setAnimation(sprites.get(HIT));
        animation.setDelay(70);
    }

    public boolean getHit() {
        return hit;
    }

    public boolean toRemove() {
        return remove;
    }
}
