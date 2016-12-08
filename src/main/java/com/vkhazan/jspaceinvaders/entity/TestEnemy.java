package com.vkhazan.jspaceinvaders.entity;


import com.vkhazan.jspaceinvaders.amination.Animation;
import com.vkhazan.jspaceinvaders.entity.util.Manager;
import com.vkhazan.jspaceinvaders.gamestate.Level1;
import com.vkhazan.jspaceinvaders.main.Game;
import com.vkhazan.jspaceinvaders.resource.R;
import com.vkhazan.jspaceinvaders.resource.util.Assets;
import com.vkhazan.jspaceinvaders.util.collection.Array;

import java.awt.image.BufferedImage;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class TestEnemy extends AbstractEntity {

    private static int count = 0;
    private final int id = count++;

    private static final int IDLE = 0;
    private static final int HIT = 1;
    private static final int ACTION = 2;
    private static final int FLASHING = 3;

    private Array<BufferedImage[]> animations;
    //private GenericsArray<Animation> animations;
    private final int[] numFrames = {
        1, 4
    };
    private boolean hit;
    private long actionDelay;
    private long lastActionTime;
    private int ix;
    private int iy;
    private double leftAbsOffset;
    private double rightAbsOffset;

    private Manager gem;

    private int updateDelay;
    private int frameElapsed;

    private Array<Shield> buffs;

    public TestEnemy(int x, int y, int delay) {

        width = 14;
        height = 14;

        cHeight = 30;
        cWidth = 30;

        this.x = x;
        this.y = y;

        animations = new Array<>();
        for (int i = 0; i < numFrames.length; i++) {
            BufferedImage[] tmp = new BufferedImage[numFrames[i]];
            for (int j = 0; j < numFrames[i]; j++) {
                tmp[j] = Assets.enemySpriteSheet.getSubimage(j * width, i * height, width, height);
            }
            animations.add(tmp);
        }

        animation = new Animation();
        animation.setAnimation(animations.get(IDLE));
        animation.setLoop(true);
        animation.setDelay(10);

        actionDelay = 3000;

        //
        //dx = 0;
        dx = -delay;
        //

        hit = false;

        updateDelay = 50;

        //buffs = new Array<>();
        //  buffs.add(new Shield((x + width / 2) - 10, (y + height / 2) - 10));
        //buffs.add(new Shield(60, 60));
    }

    @Override
    public void update() {
        animation.update();
        //   for (int i = 0; i < buffs.size(); i++) {
        //      buffs.get(i).update();
        //  }
        if (hit && animation.hasPlayedOnce()) {
            Level1.enemies.toDestroy(this);
        }

        if (++frameElapsed < updateDelay) {
            return;
        }
        frameElapsed = 0;
        x += dx;
        fixBounds();
        //
//        if (!hit && x <= leftAbsOffset) {
//            // x = leftAbsOffset;
//            dx = -dx;
//        }
//
//        if (!hit && x >= rightAbsOffset) {
//            //x = rightAbsOffset;//R.width - width;// - (Level1.NUMBER_ENTITIES_IN_HORIZONTAL - ix) * 20;
//            dx = -dx;
//        }

    }

    @Override
    public void draw() {

        Game.scene.setColor(255, 0, 0);
        Game.scene.drawRect((int) (x - 3), (int) (y - 3), width + 6, height + 6);

        BufferedImage image = animation.getCurrentFrame();
//        image.getTransparency();
//        image.getColorModel().
        
        Game.scene.drawImage(animation.getCurrentFrame(), (int) x, (int) y, width, height);
        Game.scene.setColor(0, 255, 0);
        Game.scene.drawString("" + id, (int) x, (int) (y + 5));

        ///    for (int i = 0; i < buffs.size(); i++) {
        //        buffs.get(i).draw();
        //   }
    }

    @Override
    public void interactedBy(AbstractEntity entity) {

        if (entity instanceof Bullet) {
            Bullet bullet = (Bullet) entity;
            if (!bullet.isHit() && this.collision(bullet)) {
                this.setHit();
                bullet.setHit();
            }
        }

        if (entity instanceof TestEnemy) {
            TestEnemy enemy = (TestEnemy) entity;
            //System.out.println("!!");
            if (this == enemy) {
                return;
            }
            if (x >= enemy.getX() && x <= enemy.getX() + enemy.getWidth()) {
                System.out.println(id + " olololo collision!");
                x = enemy.getX() + enemy.getWidth();

            }
//            if (x <= enemy.getX() + enemy.getWidth()) {
//                if (x + width >= enemy.getX() + enemy.getWidth()) {
//                    System.out.println(id + "right collision!");
//                }
//            }
//            if (x + width >= enemy.getX() && x <= enemy.getX()) {
//                System.out.println(id + "left collision!");
//            }
//            if (this.collision(enemy)) {
//                if (dx > 0) {
//                    if (x - 3 + width + 6 >= enemy.getX() - 3) {
//                        dx = 0;
//                        x = enemy.getX() - 3;
//                    }
//                } else if (dx < 0) {
//                    if (x - 3 <= enemy.getX() - 3 + enemy.getWidth() + 6) {
//                        dx = 0;
//                        x = enemy.getX() - 3 + enemy.getWidth() + 6;
//                    }
//                }
//            }
        }
    }

    private void setHit() {
        if (hit) {
            return;
        }
        hit = true;
        dx = 0; // ????
        animation.setAnimation(animations.get(HIT));
        animation.setDelay(3);
        animation.setLoop(false);
    }

    private void fixBounds() {
        if (x < 0) {
            x = 0;
            dx = -dx;
        }

        if (x > R.width - width) {
            x = R.width - width;
            dx = -dx;
        }
    }

    @Override
    public String toString() {
        return ix + "_" + iy;
    }

}
