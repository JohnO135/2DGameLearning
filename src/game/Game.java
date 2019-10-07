/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 *
 * @author John
 */
public class Game implements Runnable{
    
    private GameWindow window; //frame with canvas that the game will run on
    private Thread thread; //thread the game will run on
    
    private boolean running = false;
    public int width;
    public int height;
    public String title;
    
    private BufferStrategy bs;
    private Graphics g;
    
    private BufferedImage testImage;
    private KeyManager keyManager;
    
    private Player player;
    
    public Game(String title, int width, int height)
    {
       this.width = width;
       this.height = height;
       this.title = title;
       
    }
    
    private void init()
    {
        window = new GameWindow(title, width, height);
        keyManager = new KeyManager(); //Initialize the KeyManager
        //testImage = ImageLoader.loadImage("\\resources\\test.png");
        window.getJFrame().addKeyListener(keyManager); //Add to game display jframe
        player = new Player(this, 100, 100); //Initialize a player object
    }
    
    private void tick() //updater for values
    {
      keyManager.tick(); //updates the manager for input
      player.tick(); //update the player values
    }
    
    private void render() //renders the images to canvas
    {
        //This sets the buffer strategy to the buffer strategy of the canvas
        //Adding this buffer prevents flickering by prerendering images
        bs = window.getCanvas().getBufferStrategy();
        if(bs == null)//When first starting up there is no buffer strategy so this will create one
        {
            window.getCanvas().createBufferStrategy(3);
            return;
        } 
        //Allows us to draw. Essentially our drawing tool
        g = bs.getDrawGraphics();
        
        // 0 and 0 are left corner points. width and height tell it to clear the whole screen
        g.clearRect(0, 0, width, height); //Clears the screan within the rectangle

        //Draw commands
        //g.drawImage(testImage, x, y, null);
        player.render(g); //This uses the player's individual render method
        
        //Command to display the commands to canvas
        bs.show(); 
        g.dispose(); // Ensures the graphics object is handled properly
    }
    

    //The run method is necessary for the game to work from within the main
    public void run() 
    {
        init();
        
        int fps = 60; //Limits the speed of gameplay to be consistent between machines
        double timePerTick = 1000000000 / fps; //There are 1 billion nanoseconds per second so helps cap out the framerate
        double  delta = 0;
        long now;
        long lastTime = System.nanoTime(); //Returns current time in nanoseconds
        long timer = 0;
        int ticks = 0;
        
        while(running) //While game is running the game is updating (Game loop
        {
            //This checks to see when there needs to be a tick based off our desired fps
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime; //Amount of time since code above was last called
            lastTime = now;
            if(delta >= 1) //Once the delta goes over 1 then a fram will have passed;
            { 
                tick();
                render();
                ticks++;//To help count how many frames we're running at
                delta--; //Resets the Delta
            }
            if(timer >= 1000000000) //Everytime the timer hits one seconds it prints how many ticks we ran
            {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        
        stop();//Just in case the running value is false and game doesn't stop on it's own as a result
        
    }
    
    //This is so that the player object can have access to the game's key manager
    public KeyManager getKeyManager()
    {
        return keyManager;
    }
    
    public synchronized void start() //synchronized key word allows for threads to work in a synchronized fashion
    {
        if(running == true)
        {
            return; //In case something is wrong and running is already true and start is called, the game won't open a new thread.
        }
        else
        {
            running = true;
            thread = new Thread(this);//runs the game class on a new thread
            thread.start();
        }
    }
    
    public synchronized void stop()
    {
        if(running == false)
        {
            return; //Same concept if the game is already stopped no need to run join method
        }
        else
        {
            try
            {
                thread.join(); //Properly joins/stops threads
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
}
