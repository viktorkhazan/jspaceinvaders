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
public class Enemy extends AbstractEntity {

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

    public Enemy(Manager gem) {
        //this(gem, 0, 0);
    }

    //public Enemy(Manager gem, int iy, int ix) {
    public Enemy(int iy, int ix) {
        //this.gem = gem;

        width = 14;
        height = 14;

        this.ix = ix;
        this.iy = iy;

        leftAbsOffset = ix * 20;
        rightAbsOffset = R.width - (Level1.NUMBER_ENTITIES_IN_HORIZONTAL - ix) * 20 + 10;
        x = 10 + ix * 20;
        y = R.topOffset + 10 + iy * 20;

        animations = new Array<>();
        for (int i = 0; i < numFrames.length; i++) {
            BufferedImage[] tmp = new BufferedImage[numFrames[i]];
            for (int j = 0; j < numFrames[i]; j++) {
                tmp[j] = Assets.enemySpriteSheet.getSubimage(j * width, i * height, width, height);
            }
            animations.add(tmp);
        }

        //Random r = new Random();
        //  int rand = r.nextInt(11) / 10;
        // System.out.println(rand);
        animation = new Animation();
        animation.setAnimation(animations.get(IDLE));
        //animation.setFrame(rand);
        animation.setLoop(true);
        animation.setDelay(10);

        actionDelay = 3000;

        //
        dx = -10;
        //

        hit = false;

        updateDelay = 50;
    }

    @Override
    public void update() {
        animation.update();

        if (hit && animation.hasPlayedOnce()) {
            Level1.enemies.toDestroy(this);
        }

        if (++frameElapsed < updateDelay) {
            return;
        }
        frameElapsed = 0;
        x += dx;
        fixBounds();

        if (!hit && x <= leftAbsOffset) {
            // x = leftAbsOffset;
            dx = -dx;
        }

        if (!hit && x >= rightAbsOffset) {
            //x = rightAbsOffset;//R.width - width;// - (Level1.NUMBER_ENTITIES_IN_HORIZONTAL - ix) * 20;
            dx = -dx;
        }

    }

    @Override
    public void draw() {

//        Game.scene.setColor(255, 0, 0);
//        Game.scene.drawRect((int) (x - 3), (int) (y - 3), width + 6, height + 6);

        Game.scene.drawImage(animation.getCurrentFrame(), (int) x, (int) y, width, height);
//        Game.scene.setColor(0, 255, 0);
//        Game.scene.drawString(ix + ", " + iy, (int) x, (int) (y + 5));
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

/*        if (entity instanceof Enemy) {
            Enemy enemy = (Enemy) entity;
            System.out.println("!!");
            if (this == enemy) {
                return;
            }
            if (dx > 0) {
                if (x - 3 + width + 6 >= enemy.getX() - 3) {
                    dx = 0;
                    x = enemy.getX() - 3;
                }
            } else if (dx < 0) {
                if (x - 3 <= enemy.getX() - 3 + enemy.getWidth() + 6) {
                    dx = 0;
                    x = enemy.getX() - 3 + enemy.getWidth() + 6;
                }
            }
        }*/
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
