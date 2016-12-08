package com.vkhazan.jspaceinvaders.entity;


import com.vkhazan.jspaceinvaders.amination.Animation;
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
public class Bullet extends AbstractEntity {

    public static final int IDLE = 0;
    public static final int HIT = 1;
    private Array<BufferedImage[]> sprites;
    private boolean hit;
    private final int[] numFrames = {
        1, 4
    };

    public Bullet() {
        this(0, 0);
    }

    public Bullet(double x, double y) {


        this.x = x;
        this.y = y;

        sprites = new Array<>();
        width = 9;
        height = 8;


        for (int i = 0; i < numFrames.length; i++) {
            BufferedImage[] tmp = new BufferedImage[numFrames[i]];
            for (int j = 0; j < numFrames[i]; j++) {
                tmp[j] = Assets.bulletSpriteSheet.getSubimage(j * width, i * height, width, height);
            }
            sprites.add(tmp);
        }


        dy = 7;

        hit = false;

        animation = new Animation();
        animation.setAnimation(sprites.get(IDLE));
        animation.setDelay(1000);
    }

    @Override
    public void update() {

        animation.update();

        if (!hit && y <= R.topOffset) {
            setHit();
        }

        if (hit && animation.hasPlayedOnce()) {
            Level1.bullets.toDestroy(this);
        }

        y -= dy;

        fixBounds();
    }

    @Override
    public void draw() {
//        Game.scene.setColor(255, 0, 0);
//        Game.scene.drawRect((int) x, (int) y, width, height);
        Game.scene.drawImage(animation.getCurrentFrame(), (int) x, (int) y, width, height);
    }

    public void setHit() {
        if (hit) {
            return;
        }
        dy = 0;
        hit = true;
        animation.setAnimation(sprites.get(HIT));
        animation.setDelay(4);
    }

    public boolean isHit() {
        return hit;
    }
    
    private void fixBounds() {
        if (y < R.topOffset) {
            y = R.topOffset;
        }
    }

    public boolean intersection(AbstractEntity entity) {
        if (entity == null) {
            return false;
        }

        double tw = this.getWidth();
        double th = this.getHeight();
        double rw = entity.getWidth();
        double rh = entity.getHeight();

        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }

        double tx = this.getX();
        double ty = this.getY();
        double rx = entity.getX();
        double ry = entity.getY();

        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;

        return ((rw < rx || rw > tx)
                && (rh < ry || rh > ty)
                && (tw < tx || tw > rx)
                && (th < ty || th > ry));

    }

    @Override
    public void interactedBy(AbstractEntity entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
