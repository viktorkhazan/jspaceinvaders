package com.vkhazan.jspaceinvaders.util;

import javax.swing.JFrame;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Window {
    private JFrame frame;
    private String title;

    public Window(String title, Scene scene, Controls controls) {
        
        this.title = title;
        
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.setVisible(true);
        
        frame.addKeyListener(controls);

        frame.add(scene.getCanvas());
        frame.pack();
        scene.getCanvas().createBufferStrategy(2);
        
    }
}
