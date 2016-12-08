package com.vkhazan.jspaceinvaders.main;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class JSpaceInvaders2NewArchitecture {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread t = new Thread(new Game());
        t.start();
    }
}
