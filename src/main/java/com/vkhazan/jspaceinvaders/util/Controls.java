package com.vkhazan.jspaceinvaders.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Controls implements KeyListener {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int SPACE = 4;
    
    private int count = 5;
    
    private boolean[] pushed;
    private boolean[] pressed;

    public Controls() {
        pushed = new boolean[count];
        pressed = new boolean[count];

    }

    public boolean getPressed(int keyCode) {
        return pressed[keyCode];
    }
    
    public boolean getPushed(int keyCode) {
        return pushed[keyCode];
    }
    
    public void reset() {
        for (int i = 0; i < pressed.length; i++) {
            pressed[i] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_W) {
            if (pushed[UP] == false) {
                pressed[UP] = true;
            }
            pushed[UP] = true;
        }
        if (k == KeyEvent.VK_S) {
            if (pushed[DOWN] == false) {
                pressed[DOWN] = true;
            }
            pushed[DOWN] = true;
        }
        if (k == KeyEvent.VK_D) {
            if (pushed[LEFT] == false) {
                pressed[LEFT] = true;
            }
            pushed[LEFT] = true;
        }
        if (k == KeyEvent.VK_A) {
            if (pushed[RIGHT] == false) {
                pressed[RIGHT] = true;
            }
            pushed[RIGHT] = true;
        }

        if (k == KeyEvent.VK_SPACE) {
            if (pushed[SPACE] == false) {
                pressed[SPACE] = true;
            }
            pushed[SPACE] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_W) {
            pushed[UP] = false;
            pressed[UP] = false;
        }
        if (k == KeyEvent.VK_S) {
            pressed[DOWN] = false;
            pushed[DOWN] = false;
        }
        if (k == KeyEvent.VK_D) {
            pressed[LEFT] = false;
            pushed[LEFT] = false;
        }
        if (k == KeyEvent.VK_A) {
            pressed[RIGHT] = false;
            pushed[RIGHT] = false;
        }

        if (k == KeyEvent.VK_SPACE) {
            pressed[SPACE] = false;
            pushed[SPACE] = false;
        }
    }
}
