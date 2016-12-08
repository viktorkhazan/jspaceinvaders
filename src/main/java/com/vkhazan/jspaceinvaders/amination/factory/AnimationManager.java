package com.vkhazan.jspaceinvaders.amination.factory;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class AnimationManager {

    
    protected SimpleAnimationFactory saf;
    
    public AnimationManager() {
        
        saf = new SimpleAnimationFactory();
        
       // saf.loadAnimation(SimpleAnimationFactory.ENEMY);
        
        
    }
    
}
