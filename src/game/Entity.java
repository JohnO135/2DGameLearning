/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;

/**
 *
 * @author theme
 */
public abstract class Entity {
    
    protected float x, y; //These are floats in order to achieve smooth movement
    
    //Any entity in the game will have a position
    public Entity(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}
