/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vkhazan.jspaceinvaders.entity;


import com.vkhazan.jspaceinvaders.amination.Animation;

/**
 *
 * @author гыук
 */
public abstract class AbstractEntity {

    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    protected int width;
    protected int height;
    protected int cWidth;
    protected int cHeight;
    protected int healht;
    protected Animation animation;
    
    protected double xSpeed;
    protected double ySpeed;
    
    protected double xMaxSpeed;
    protected double yMaxSpeed;
    
    protected double xAcceleration;
    protected double yAcceleration;
    
    

    public abstract void update();

    public abstract void draw();

    public abstract void interactedBy(AbstractEntity entity);

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHealht() {
        return healht;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public boolean collision(AbstractEntity entity) {

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
}
