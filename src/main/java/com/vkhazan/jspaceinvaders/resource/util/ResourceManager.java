package com.vkhazan.jspaceinvaders.resource.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class ResourceManager {

    public static BufferedImage loadSprite(String path) {
        BufferedImage sprite = null;
        try {
            /*
             * Originally it was: sprite = ImageIO.read(new File(path));
             */
            sprite = ImageIO.read(ResourceManager.class.getResource(path));
        } catch (IOException ex) {
            System.out.println("Exception: " + path);
        }
        return sprite;
    }

//    public static Sprite loadSprite(String path) {
//        Sprite sprite = null;
//        try {
//            sprite = new Sprite(ImageIO.read(new File(path)));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return sprite;
//    }
}
