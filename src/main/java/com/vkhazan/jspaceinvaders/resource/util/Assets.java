package com.vkhazan.jspaceinvaders.resource.util;

import com.vkhazan.jspaceinvaders.resource.R;

import java.awt.image.BufferedImage;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Assets {

    public static BufferedImage playerSpriteSheet;
    public static BufferedImage enemySpriteSheet;
    public static BufferedImage beamSpriteSheet;
    public static BufferedImage bulletSpriteSheet;
    public static BufferedImage shieldSpriteSheet;

    public static void load() {
        if (playerSpriteSheet == null) {
            synchronized (Assets.class) {
                playerSpriteSheet = ResourceManager.loadSprite(R.resourceRoot + "playersprites.png");
            }
        }

        if (enemySpriteSheet == null) {
            synchronized (Assets.class) {
                enemySpriteSheet = ResourceManager.loadSprite(R.resourceRoot + "enemysprites1.png");
            }
        }

        if (bulletSpriteSheet == null) {
            synchronized (Assets.class) {
                bulletSpriteSheet = ResourceManager.loadSprite(R.resourceRoot + "bulletsprites.png");
            }
        }

        if (beamSpriteSheet == null) {
            synchronized (Assets.class) {
                beamSpriteSheet = ResourceManager.loadSprite(R.resourceRoot + "beam.png");
            }
        }

        if (shieldSpriteSheet == null) {
            synchronized (Assets.class) {
                shieldSpriteSheet = ResourceManager.loadSprite(R.resourceRoot + "sheld.png");
            }
        }
    }
}
