package com.vkhazan.jspaceinvaders.entity.util;


import com.vkhazan.jspaceinvaders.entity.AbstractEntity;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public interface Manager {
    public void interactedBy(Manager interactables);
    
    public void update();
    
    public void draw();
    
    public void toCreate(AbstractEntity entity);
    
    public void toDestroy(AbstractEntity entity);
    
    public AbstractEntity get(int index);
    
    public int size();
    
}
