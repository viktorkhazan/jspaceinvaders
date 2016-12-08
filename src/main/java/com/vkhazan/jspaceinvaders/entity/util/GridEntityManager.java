package com.vkhazan.jspaceinvaders.entity.util;


import com.vkhazan.jspaceinvaders.entity.AbstractEntity;
import com.vkhazan.jspaceinvaders.entity.Enemy;
import com.vkhazan.jspaceinvaders.util.collection.Array;
import com.vkhazan.jspaceinvaders.util.collection.Matrix;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class GridEntityManager implements Manager {

    private Matrix<AbstractEntity> entities;
    private Array<AbstractEntity> destroy;
    private Array<AbstractEntity> create;

    public GridEntityManager() {

    }

    public void createGrid(Class clazz, int nRow, int nCol) {

        destroy = new Array<>();
        create = new Array<>();

        entities = new Matrix<>(nRow, nCol);

        if (clazz == Enemy.class) {
            for (int i = 0; i < nRow; i++) {
                for (int j = 0; j < nCol; j++) {
                    entities.add(new Enemy(i, j));
                }
            }
        }
        System.out.println(entities);
    }

    @Override
    public void interactedBy(Manager interactables) {
        for (int i = 0; i < entities.getRow(); i++) {
            for (int j = 0; j < entities.getCol(); j++) {
                for (int k = 0; k < interactables.size(); k++) {
                    if (interactables.get(k).collision(entities.get(i, j))) {
                        toDestroy(entities.get(i, j));
                    }
                }
            }
        }
    }

    @Override
    public void update() {

        for (int i = 0; i < destroy.size(); i++) {
            if (entities.contains(destroy.get(i))) {
                entities.remove(destroy.get(i));
            }
        }
        destroy.clear();

//        for (int i = 0; i < create.size(); i++) {
//            if (!entities.contains(create.get(i))) {
//                entities.add(create.get(i));
//            }
//        }
//        create.clear();
        for (int i = 0; i < entities.getRow(); i++) {
            for (int j = 0; j < entities.getCol(); j++) {
                if (entities.get(i, j) != null) {
                    entities.get(i, j).update();
                }
            }
        }
    }

    @Override
    public void draw() {

        for (int i = 0; i < entities.getRow(); i++) {
            for (int j = 0; j < entities.getCol(); j++) {
                if (entities.get(i, j) != null) {
                    entities.get(i, j).draw();
                }
            }
        }
    }

    @Override
    public void toCreate(AbstractEntity entity
    ) {
        create.add(entity);
    }

    @Override
    public void toDestroy(AbstractEntity entity
    ) {
        destroy.add(entity);
    }

    @Override
    public AbstractEntity get(int index
    ) {

        return null;
    }

    @Override
    public int size() {
        return entities.size();
    }

    public int getCol() {
        return entities.getCol();
    }

    public int getRow() {
        return entities.getRow();
    }

}
