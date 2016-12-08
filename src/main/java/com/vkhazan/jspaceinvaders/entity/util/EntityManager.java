package com.vkhazan.jspaceinvaders.entity.util;


import com.vkhazan.jspaceinvaders.entity.AbstractEntity;
import com.vkhazan.jspaceinvaders.util.collection.Array;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class EntityManager implements Manager {

    private Array<AbstractEntity> entities;
    private Array<AbstractEntity> destroy;
    private Array<AbstractEntity> create;

    public EntityManager() {
        entities = new Array<>();
        destroy = new Array<>();
        create = new Array<>();
    }

    @Override
    public void interactedBy(Manager interactables) {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = 0; j < interactables.size(); j++) {
                entities.get(i).interactedBy(interactables.get(j));
            }
        }
    }

    @Override
    public void update() {

        for (int i = 0; i < create.size(); i++) {
            if (!entities.contains(create.get(i))) {
                entities.add(create.get(i));
            }
        }
        create.clear();

        for (int i = 0; i < destroy.size(); i++) {
            if (entities.contains(destroy.get(i))) {
                entities.remove(destroy.get(i));
            }
        }
        destroy.clear();

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    @Override
    public void draw() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw();
        }
    }

    @Override
    public void toCreate(AbstractEntity entity) {
        create.add(entity);
    }

    @Override
    public void toDestroy(AbstractEntity entity) {
        destroy.add(entity);
    }

    @Override
    public AbstractEntity get(int index) {
        return entities.get(index);
    }

    @Override
    public int size() {
        return entities.size();
    }
}
