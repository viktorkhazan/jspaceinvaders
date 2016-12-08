package com.vkhazan.jspaceinvaders.util;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Scene {

    private Canvas canvas;
    private Graphics2D g2d;
    private BufferStrategy bs;
    private int width;
    private int height;
    private int scale;

    public Scene(int width, int height, int scale) {

        this.width = width;
        this.height = height;
        this.scale = scale;

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width * scale, height * scale));
    }

    public void takeNewGraphicsContext() {
        if (bs == null) {
            bs = canvas.getBufferStrategy();
        }
        g2d = (Graphics2D) bs.getDrawGraphics();
    }

    public void disposeGraphics() {
        g2d.dispose();
        bs.show();
    }

    // getters and setters
    public Canvas getCanvas() {
        return canvas;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Graphics implements
    public void setColor(int r, int g, int b) {
        g2d.setColor(new Color(r, g, b));
    }
    
    public void drawString(String str, int x, int y) {
        g2d.drawString(str, x * scale, y * scale);
    }

    public void drawRect(int x, int y, int width, int height) {
        g2d.drawRect(x * scale, y * scale, width * scale, height * scale);
    }
    
    public void fillRect(int x, int y, int width, int height) {
        g2d.fillRect(x * scale, y * scale, width * scale, height * scale);
    }
    
    public void drawImage(Image img, int x, int y, int width, int height) {
        g2d.drawImage(img, x * scale, y * scale, width * scale, height * scale, null);
    }
}
