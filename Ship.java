import javax.swing.*;
import java.awt.*;
import java.lang.Math.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.image.*;
public class Ship
{
    private static int degree;
    private ArrayList<AffineTransform> shots;
    public Ship ()
    {
        shots = new ArrayList<AffineTransform>();
    }

    public void moveForward(AffineTransform t, AffineTransform t2, AffineTransform t3)
    {
        t.translate(1, 1);
        t2.translate(1, 1);
        t3.translate(1, 1);
    }

    public void moveBackward(AffineTransform t, AffineTransform t2, AffineTransform t3)
    {
        t.translate(-1, -1);
        t2.translate(-1, -1);
        t3.translate(-1, -1);
    }

    public void rotateClockwise(AffineTransform t, AffineTransform t2, AffineTransform t3, AffineTransform original)
    {
        t.rotate(Math.PI/45, 16, 16);
        t2.rotate(Math.PI/45, 4, 4);
        original.setTransform(t2);
        t2.translate(12, 12);
        t3.setTransform(t2);
        t2.setTransform(original);
    }

    public void rotateCounterClockwise(AffineTransform t, AffineTransform t2, AffineTransform t3, AffineTransform original)
    {
        t.rotate(-Math.PI/45,16, 16);
        t2.rotate(-Math.PI/45, 4, 4);
        original.setTransform(t2);
        t2.translate(12, 12);
        t3.setTransform(t2);
        t2.setTransform(original);
    }

    public void shoot(AffineTransform t3)
    {
        AffineTransform random = new AffineTransform();
        random.setTransform(t3);
        shots.add(random);
    }

    public void drawShot(Graphics2D g2D, BufferedImage shot, GamePanel panel1)
    {
        for (int i = 0; i < shots.size(); i++)
        {
            // if(shots.get(i).getTranslateX() > 1920 || shots.get(i).getTranslateX() + 35 < 0 || shots.get(i).getTranslateY() > 1080 || shots.get(i).getTranslateY() + 35 < 0)
            // {
            // shots.remove(i);
            // }
            // else
            // {
            // shots.get(i).translate(3,3);
            // g2D.drawImage(shot, shots.get(i), panel1);
            // }
            shots.get(i).translate(3,3);
            g2D.drawImage(shot, shots.get(i), panel1);
        }
    }

    public void checkForHit(ArrayList<Asteroid> asteroids)
    {
        if (shots.size() != 0 && asteroids.size() != 0)
        {
            for (int i = 0; i < shots.size() - 1; i++)
            {
                for (int j = 0; j < asteroids.size() - 1; j++)
                {
                    System.out.println("I: " + i);
                    System.out.println("J: " + j);//why does it say J starts at 2?
                    double shotX = shots.get(i).getTranslateX();
                    double shotY = shots.get(i).getTranslateY();
                    double asteroidX = asteroids.get(i).getT().getTranslateX();
                    double asteroidY = asteroids.get(i).getT().getTranslateY();
                    if (shotX > asteroidX && shotX < asteroidX + 35)
                    {
                        System.out.println("Shots Size: " + shots.size());
                        System.out.println("Asteroids Size: " + asteroids.size());
                        System.out.println("Asteroids index: " + j);
                        System.out.println("Shots index: " + i);
                        //asteroids.remove(j);
                        //shots.remove(i);
                    }
                    if (shotY > asteroidY && shotY < asteroidY + 35)
                    {
                        System.out.println("Shots Size: " + shots.size());
                        System.out.println("Asteroids Size: " + asteroids.size());
                        System.out.println("Asteroids index: " + j);
                        System.out.println("Shots index: " + i);
                        //asteroids.remove(j);
                        //shots.remove(i);
                    }
                }
            }
        }
    }

    // public void teleportShots(AffineTransform t4)
    // {
        // for (int i = 0; i < shots.size(); i++)
        // {
            // if (shots.get(i).getTranslateX() > 2020) //1920, +35, 1875
            // {
                // //somewhat works with just first statements in whiles
                // t4.setTransform(shots.get(i));
                // while(t4.getTranslateX() > -60 && t4.getTranslateY() > -60 && t4.getTranslateY() < 1140) //35
                // {
                    // t4.translate(-2, -2);
                    // //t.setTransform(t2);
                    // //System.out.println("t2 x Location: " + t2.getTranslateX() + " t2 y Location: " +);
                // }
                // shots.get(i).setTransform(t4); //-1950
                // //t.translate(-2, -2);
            // }
            // if(shots.get(i).getTranslateX() < -100)
            // {
                // t4.setTransform(shots.get(i));
                // while(t4.getTranslateX() < 1970 && t4.getTranslateY() > -60 && t4.getTranslateY() < 1140) //1875
                // {
                    // t4.translate(-2, -2);
                    // //t.setTransform(t2);
                // }
                // shots.get(i).setTransform(t4);
            // }
            // if (shots.get(i).getTranslateY() > 1180) //1080, + 35, 1045
            // {
                // t4.setTransform(shots.get(i));
                // while(t4.getTranslateX() > -60 && t4.getTranslateX() < 1970 && t4.getTranslateY() > -60) //35
                // {
                    // t4.translate(-2, -2);
                    // //t.setTransform(t2);
                // }
                // shots.get(i).setTransform(t4); //-1130
                // //t.translate(-2, -2);
            // }
            // if(t4.getTranslateY() < -100)
            // {
                // t4.setTransform(shots.get(i));
                // while(t4.getTranslateX() > -60 && t4.getTranslateX() < 1970 && t4.getTranslateY() < 1140) //1045
                // {
                    // t4.translate(-2, -2);
                    // //t.setTransform(t2);
                // }
                // shots.get(i).setTransform(t4);
            // }
        // }
    // }

}
