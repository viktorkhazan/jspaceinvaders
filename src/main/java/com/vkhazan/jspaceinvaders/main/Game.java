package com.vkhazan.jspaceinvaders.main;


import com.vkhazan.jspaceinvaders.gamestate.Level1;
import com.vkhazan.jspaceinvaders.resource.R;
import com.vkhazan.jspaceinvaders.util.Controls;
import com.vkhazan.jspaceinvaders.util.Scene;
import com.vkhazan.jspaceinvaders.util.Window;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Game implements Runnable {

    private Window window;
    public final static Controls controls = new Controls();
    public final static Scene scene = new Scene(R.width, R.height, R.scale);
    private boolean inAction;
    private final int FPS = 60;
    private final long targetTime = 1000 / FPS;
    private Level1 level1;

    public Game() {

        //controls = new Controls();
        //scene = new Scene(R.width, R.height, R.scale);
        window = new Window("JSpaceInvaders 2: New A.R.C.H.I.T.E.C.T.U.R.E.", scene, controls);

        inAction = true;

        level1 = new Level1();
        level1.init();
    }

    private void gameLoop() {

        long start = 0;
        long elapsed = 0;
        long wait = 0;

        int fps = 0;
        long updateLength = 0;
        long lastLoopTime = 0;
        long lastFpsTime = 0;
        
        while (inAction) {
            start = System.nanoTime();

            scene.takeNewGraphicsContext();
            update();
            draw();
            controls.reset();
            scene.disposeGraphics();

            wait = targetTime - elapsed / 10000000;

            if (wait < 0) {
                wait = 5;
            }

            
            updateLength = start - lastLoopTime;
            lastLoopTime = start;
            lastFpsTime += updateLength;
            fps++;
            
            if (lastFpsTime >= 1000000000) {
                System.out.println("FPS " + fps);
                fps = 0;
                lastFpsTime = 0;
            }
            
            try {
                Thread.sleep(wait);
            } catch (Exception e) {
            }
        }
    }

    // update & draw
    public void update() {
        level1.update();
    }

    public void draw() {
        level1.draw();
    }

    // implementing
    @Override
    public void run() {
        gameLoop();
    }

    // getters
    public static Controls getControls() {
        return controls;
    }

    public static Scene getScene() {
        return scene;
    }
}
