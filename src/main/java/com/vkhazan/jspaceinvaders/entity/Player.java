package com.vkhazan.jspaceinvaders.entity;

import com.vkhazan.jspaceinvaders.amination.Animation;
import com.vkhazan.jspaceinvaders.gamestate.Level1;
import com.vkhazan.jspaceinvaders.main.Game;
import com.vkhazan.jspaceinvaders.resource.R;
import com.vkhazan.jspaceinvaders.resource.util.Assets;
import com.vkhazan.jspaceinvaders.util.Controls;
import com.vkhazan.jspaceinvaders.util.collection.Array;

import java.awt.image.BufferedImage;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Player extends AbstractEntity {

    private Array<BufferedImage[]> animations;
    private final int[] numFrames = {
            1, 6
    };

    public Player() {
        this(0, 0);
    }

    public Player(double x, double y) {

        this.x = x;
        this.y = y;

        width = 21;
        height = 11;

        animations = new Array<>();

        for (int i = 0; i < numFrames.length; i++) {
            BufferedImage[] sprites = new BufferedImage[numFrames[i]];
            for (int j = 0; j < numFrames[i]; j++) {
                sprites[j] = Assets.playerSpriteSheet.getSubimage(j * width, i * height, width, height);
            }
            animations.add(sprites);
        }

        xSpeed = 5;
        ySpeed = 0;

        xMaxSpeed = 5.0;
        yMaxSpeed = 0.0;

        xAcceleration = 0.5;
        yAcceleration = 0.0;

        animation = new Animation();
        animation.setAnimation(animations.get(0));
        animation.setDelay(100);

    }

    @Override
    public void update() {
        animation.update();

        getNextPosition();
        fixBounds();

        if (Game.controls.getPressed(Controls.SPACE)) {
            Level1.bullets.toCreate(new Bullet(x, y));
        }

    }

    @Override
    public void draw() {
//        Game.getScene().setColor(255, 0, 0);
//        Game.getScene().drawRect((int) x, (int) y, width, height);
        Game.getScene().drawImage(animation.getCurrentFrame(), (int) x, (int) y, width, height);
    }

    @Override
    public void interactedBy(AbstractEntity entity) {
        if (entity instanceof Beam) {
            Beam beam = (Beam) entity;
            if (beam.collision(this)) {
                this.setHit();
                beam.setHit();
            }
        }
    }

    private void setHit() {
        Level1.players.toDestroy(this);
    }

    private void getNextPosition() {
        if (Game.getControls().getPushed(Controls.LEFT)) {
            x += xSpeed;
//            xSpeed += xAcceleration;
//            if (xSpeed > xMaxSpeed) {
//                xSpeed = xMaxSpeed;
//            }
        } else if (Game.getControls().getPushed(Controls.RIGHT)) {
            x -= xSpeed;
//            xSpeed -= xAcceleration;
//            if (xSpeed < xMaxSpeed) {
//                xSpeed = -xMaxSpeed;
//            }
        } else {
            //xSpeed = 0;
        }
        //System.out.println("Speed "  + xSpeed );
        // x += xSpeed;


    }

    private void fixBounds() {
        if (x >= R.width - width) {
            x = R.width - width;
        }
        if (x <= 0) {
            x = 0;
        }
    }
}
