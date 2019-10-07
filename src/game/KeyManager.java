/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; //Allows us to get input for keys

/**
 *
 * @author theme
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right; //Directions for our player
   
    public KeyManager()
    {
        keys = new boolean[256];
    }
    
    //Controls for movement
    //Finds the keyCode within our array and sets value to 0 or 1 if its
    //pressed or released
    //This looks for WASD for movement
    public void tick()
    {
         up = keys[KeyEvent.VK_W];
         down = keys[KeyEvent.VK_S];
         left = keys[KeyEvent.VK_A];
         right = keys[KeyEvent.VK_D];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //This is whenever the key is pressed down
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        
    }
    
    //Whenever a key is let go
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
}
