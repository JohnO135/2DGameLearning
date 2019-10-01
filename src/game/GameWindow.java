/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.JFrame;

/**
 *
 * @author John
 */
public class GameWindow {
    
    private JFrame frame;
    
    private String title;
    private int width;
    private int height;
    
    public GameWindow(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        
        createWindow();
    }
    
    private void createWindow()
    {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
    }
    
}
