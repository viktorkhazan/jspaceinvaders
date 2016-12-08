package com.vkhazan.jspaceinvaders.gamestate;


import com.vkhazan.jspaceinvaders.entity.Enemy;
import com.vkhazan.jspaceinvaders.entity.Player;
import com.vkhazan.jspaceinvaders.entity.util.EntityManager;
import com.vkhazan.jspaceinvaders.main.Game;
import com.vkhazan.jspaceinvaders.resource.R;
import com.vkhazan.jspaceinvaders.resource.util.Assets;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Level1 extends AbstractGameState {


    
    public static final int NUMBER_ENTITIES_IN_HORIZONTAL = 11;
    public static final int NUMBER_ENTITIES_IN_VERTICAL = 5;
    
    public static final EntityManager players = new EntityManager();
    public static final EntityManager enemies = new EntityManager();
    public static final EntityManager bullets = new EntityManager();
    public static final EntityManager beams = new EntityManager();

    public Level1() {

    }

    @Override
    public void init() {

        Assets.load();

        //players = new Interactables();
        players.toCreate(new Player(R.width / 2, R.height - 20));
        for (int i = 0; i < NUMBER_ENTITIES_IN_VERTICAL; i++) {
            for (int j = 0; j < NUMBER_ENTITIES_IN_HORIZONTAL; j++) {
                enemies.toCreate(new Enemy(i, j));
            }
        }
//        enemies.toCreate(new TestEnemy(R.width - 50, 50, 10));
//        enemies.toCreate(new TestEnemy(R.width - 20, 50, 15));
        
    }

    @Override
    public void update() {

        players.update();
        enemies.update();
        bullets.update();
        beams.update();

        enemies.interactedBy(enemies);
        enemies.interactedBy(bullets);
        players.interactedBy(beams);
    }

    @Override
    public void draw() {
        Game.getScene().setColor(127, 127, 127);
        Game.getScene().fillRect(0, 0, R.width, R.height);

        players.draw();
        enemies.draw();
        bullets.draw();
        beams.draw();
    }
}
