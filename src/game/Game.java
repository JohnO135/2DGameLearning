/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

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
    
    public Game(String title, int width, int height)
    {
       this.width = width;
       this.height = height;
       this.title = title;
       
    }
    
    private void init()
    {
        window = new GameWindow(title, width, height);
    }
    
    private void tick() //updater for values
    {
    }
    
    private void render() //renders the images to canvas
    {
    }
    

    //The run method is necessary for the game to work from within the main
    public void run() 
    {
        init();
        
        while(running) //While game is running the game is updating (Game loop
        {
            tick();
            render();
        }
        
        stop();//Just in case the running value is false and game doesn't stop on it's own as a result
        
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
