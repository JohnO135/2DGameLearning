/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The purpose of this class is to load images as BufferedImage objects of the images we save as resources
 * @author John
 */
public class ImageLoader {
    
    public static BufferedImage loadImage(String path)
    {
        try{
            return ImageIO.read(ImageLoader.class.getResource(path)); //Accesses the path from another package
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
