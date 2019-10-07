/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author theme
 */
public class Player extends Entity {

    private BufferedImage sprite; //Sets the private value for the sprite for players
    private Game game; //Creates a private game so we can access the game methods from within this class by passing the
    //main game object into the contructor.
    
    public Player(Game game, float x, float y)
    {
        super(x, y);
        this.game = game; //game is set to main game method
        ImageLoader IM = new ImageLoader(); //Creates an image loader
        sprite = IM.loadImage("\\resources\\test.png"); //Sets the sprite for the player
        
    }
    
    //This is the individual position updater
    @Override
    public void tick() {
        if(game.getKeyManager().up)
        {
            y -= 3;
        }
        if(game.getKeyManager().down)
        {
            y += 3;
        }
        if(game.getKeyManager().left)
        {
            x -= 3;
        }if(game.getKeyManager().right)
        {
            x += 3;
        }
    }

    //Individual render method for simplicity in main
    @Override
    public void render(Graphics g) {
        g.drawImage(sprite, (int)x, (int)y, null); //The method needs integer values so we cast it
    }
    
    
}
