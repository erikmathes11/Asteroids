import javax.swing.*;
import java.awt.*;
import java.lang.Math.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class Asteroid
{
    private double size;
    private int pointValue;
    private AffineTransform t;
    private AffineTransform t2;
    private int typeAsteroid;
    private BufferedImage asteroid;
    private double scale;
    public Asteroid()
    {
        t = new AffineTransform();
        t2 = new AffineTransform();
        t.translate(800,800);
        t.rotate((Math.random() * 2 * Math.PI) + 0);
        scale = 0;
        try
        {
            typeAsteroid = (int)(Math.random() * 100) + 0;
            if (typeAsteroid > 0 && typeAsteroid <= 20)
            {
                asteroid = ImageIO.read(new File("asteroid (1).png"));
            }
            else if (typeAsteroid > 20 && typeAsteroid <= 40)
            {
                asteroid = ImageIO.read(new File("asteroid (5).png"));
            }
            else if (typeAsteroid > 40 && typeAsteroid <= 60)
            {
                asteroid = ImageIO.read(new File("asteroid (6).png"));
            }
            else if (typeAsteroid > 60 && typeAsteroid <= 80)
            {
                asteroid = ImageIO.read(new File("asteroid (7).png"));
            }
            else if (typeAsteroid > 80 && typeAsteroid <= 85)
            {
                asteroid = ImageIO.read(new File("asteroid (2).png"));
            }
            else if (typeAsteroid > 85 && typeAsteroid <= 90)
            {
                asteroid = ImageIO.read(new File("asteroid (3).png"));
            }
            else if (typeAsteroid > 90 && typeAsteroid <= 95)
            {
                asteroid = ImageIO.read(new File("asteroid (4).png"));
            }
            else
            {
                asteroid = ImageIO.read(new File("asteroid (8).png"));
            }
        }
        catch (Exception e)
        {

        }
        size = (int)(Math.random() * 5) + 1;
        if (size == 1)
        {
            pointValue = 100;
            t.scale(1, 1);
            scale = 1;
        }
        else if (size == 2)
        {
            pointValue = 200;
            t.scale(.8, .8);
            scale = .8;
        }
        else if (size == 3)
        {
            pointValue = 300;
            t.scale(.6, .6);
            scale = .6;
        }
        else if (size == 4)
        {
            pointValue = 400;
            t.scale(.4, .4);
            scale = .4;
        }
        else
        {
            pointValue = 500;
            t.scale(.2, .2);
            scale = .2;
        }

    }

    public void drawAsteroid(Graphics2D g2D, GamePanel panel1, BufferedImage asteroid)
    {
        t.translate(2, 2);
        g2D.drawImage(asteroid, t, panel1);
    }

    public BufferedImage getAsteroid()
    {
        return asteroid;
    }

    public void teleportAsteroid()
    {
        //System.out.println("Get Translate x: " + t.getTranslateX());
        //System.out.println("Get Translate y: " + t.getTranslateY());
        if (t.getTranslateX() > 2020) //1920, +35, 1875
        {
            //somewhat works with just first statements in whiles
            t2.setTransform(t);
            while(t2.getTranslateX() > -60 && t2.getTranslateY() > -60 && t2.getTranslateY() < 1140) //35
            {
                t2.translate(-2, -2);
                //t.setTransform(t2);
                //System.out.println("t2 x Location: " + t2.getTranslateX() + " t2 y Location: " +);
            }
            t.setTransform(t2); //-1950
            //t.translate(-2, -2);
        }
        if(t.getTranslateX() < -100)
        {
            t2.setTransform(t);
            while(t2.getTranslateX() < 1970 && t2.getTranslateY() > -60 && t2.getTranslateY() < 1140) //1875
            {
                t2.translate(-2, -2);
                //t.setTransform(t2);
            }
            t.setTransform(t2);
        }
        if (t.getTranslateY() > 1180) //1080, + 35, 1045
        {
            t2.setTransform(t);
            while(t2.getTranslateX() > -60 && t2.getTranslateX() < 1970 && t2.getTranslateY() > -60) //35
            {
                t2.translate(-2, -2);
                //t.setTransform(t2);
            }
            t.setTransform(t2); //-1130
            //t.translate(-2, -2);
        }
        if(t.getTranslateY() < -100)
        {
            t2.setTransform(t);
            while(t2.getTranslateX() > -60 && t2.getTranslateX() < 1970 && t2.getTranslateY() < 1140) //1045
            {
                t2.translate(-2, -2);
                //t.setTransform(t2);
            }
            t.setTransform(t2);
        }
    }
    
    public AffineTransform getT()
    {
        return t;
    }
    
    public double getScale()
    {
        return scale;
    }
    
    
    
    
    
    
    
}