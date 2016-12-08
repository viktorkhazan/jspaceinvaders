package com.vkhazan.jspaceinvaders.util.collection;

/**
 * @author Viktor Khazan (<a href="mailto:viktor.khazan@yandex.ru">viktor.khazan@yandex.ru</a>)
 *         23.11.2014
 */
public class Array<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] entities;
    private int size;

    public Array() {
        entities = new Object[DEFAULT_CAPACITY];
    }

    public void add(T entity) {
        ensureCapacity(size + 1);
        entities[size++] = entity;
    }

    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return (T) entities[index];
    }

    public boolean remove(int index) {
        if (index < 0 || index > size) {
            return false;
        }
        int numMuved = size - index - 1;
        if (numMuved > 0) {
            arrayCopy(entities, index + 1, entities, index, numMuved);
        }
        entities[--size] = null;

        return true;
    }

    public boolean remove(T entity) {
        if (entity == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (entity.equals(entities[i])) {
                return remove(i);
            }
        }
        return false;
    }

    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(entities[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            entities[i] = null;
        }
        size = 0;
    }
    
    private void ensureCapacity(int capacity) {
        if (capacity >= entities.length) {
            Object[] newEntities = new Object[capacity * 2];
            arrayCopy(entities, 0, newEntities, 0, entities.length);
            entities = newEntities;
        }
    }

    public static void arrayCopy(Object[] src, int srcPos, Object[] dest, int destPos, int lenght) {
        for (int index = 0; index < lenght; index++) {
            dest[destPos + index] = src[srcPos + index];
        }
    }
}