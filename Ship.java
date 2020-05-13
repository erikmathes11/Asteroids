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
            shots.get(i).translate(4,4);
            g2D.drawImage(shot, shots.get(i), panel1);
        }
    }

    public void checkForHit(ArrayList<Asteroid> asteroids)
    {
        for (int i = 0; i < shots.size(); i++)
        {
            for (int j = 0; j < asteroids.size(); j++)
            {
                double diameterAsteroid = asteroids.get(j).getSize2() * 31;
                double diameterShot = 7;
                if(j == asteroids.size())
                {
                    j--;
                }
                if(i == shots.size())
                {
                    i--;
                }
                try
                {
                    if (asteroids.get(j).getSize() == 1 && shots.get(i).getTranslateX() > asteroids.get(j).getT().getTranslateX() - 15 && shots.get(i).getTranslateX() + diameterShot < asteroids.get(j).getT().getTranslateX() + diameterAsteroid + 15 && shots.get(i).getTranslateY() > asteroids.get(j).getT().getTranslateY() - 15 && shots.get(i).getTranslateY() + diameterShot < asteroids.get(j).getT().getTranslateY() + diameterAsteroid + 15)
                    {
                        asteroids.get(j).getT().scale(1, 1);
                        asteroids.get(j).getT().scale(.8, .8);
                        asteroids.get(j).setSize(2);
                        shots.remove(i);
                    }
                    else if (asteroids.get(j).getSize() == 2 && shots.get(i).getTranslateX() > asteroids.get(j).getT().getTranslateX() - 15 && shots.get(i).getTranslateX() + diameterShot < asteroids.get(j).getT().getTranslateX() + diameterAsteroid + 15 && shots.get(i).getTranslateY() > asteroids.get(j).getT().getTranslateY() - 15 && shots.get(i).getTranslateY() + diameterShot < asteroids.get(j).getT().getTranslateY() + diameterAsteroid + 15)
                    {
                        asteroids.get(j).getT().scale(10.0 / 8.0, 10.0 / 8.0);
                        asteroids.get(j).getT().scale(.6, .6);
                        asteroids.get(j).setSize(3);
                        shots.remove(i);
                    }
                    else if (asteroids.get(j).getSize() == 3 && shots.get(i).getTranslateX() > asteroids.get(j).getT().getTranslateX() - 15 && shots.get(i).getTranslateX() + diameterShot < asteroids.get(j).getT().getTranslateX() + diameterAsteroid + 15 && shots.get(i).getTranslateY() > asteroids.get(j).getT().getTranslateY() - 15 && shots.get(i).getTranslateY() + diameterShot < asteroids.get(j).getT().getTranslateY() + diameterAsteroid + 15)
                    {
                        asteroids.get(j).getT().scale(10.0 / 6.0, 10.0 / 6.0);
                        asteroids.get(j).getT().scale(.4, .4);
                        asteroids.get(j).setSize(4);
                        shots.remove(i);
                    }
                    else if (shots.get(i).getTranslateX() > asteroids.get(j).getT().getTranslateX() - 15 && shots.get(i).getTranslateX() + diameterShot < asteroids.get(j).getT().getTranslateX() + diameterAsteroid + 15 && shots.get(i).getTranslateY() > asteroids.get(j).getT().getTranslateY() - 15 && shots.get(i).getTranslateY() + diameterShot < asteroids.get(j).getT().getTranslateY() + diameterAsteroid + 15)
                    {
                        asteroids.remove(j);
                        shots.remove(i);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("J: " + j);
                    System.out.println("I: " + i);
                    System.out.println("Asteroids size: " + asteroids.size());
                    System.out.println("Shots size: " + shots.size());
                }
            }
        }
    }

    // public void checkForHit(ArrayList<Asteroid> asteroids)
    // {
    // int shotsSize = shots.size();
    // int asteroidsSize = asteroids.size();
    // if (shotsSize != 0 && asteroidsSize != 0)
    // {
    // for (int i = 0; i < shotsSize; i++)
    // {
    // for (int j = 0; j < asteroidsSize; j++)
    // {
    // double shotX = 0;
    // double shotY = 0;

    // double asteroidX = 0;
    // double asteroidY = 0;
    // try
    // {
    // shotX = shots.get(i).getTranslateX();
    // shotY = shots.get(i).getTranslateY();
    // }
    // catch (Exception e)
    // {
    // System.out.println("Error with i"); //i-- didn't work
    // System.out.println("Before");
    // System.out.println("J: " + j);
    // System.out.println("I: " + i);
    // System.out.println("Asteroids Size: " + asteroidsSize);
    // System.out.println("Shots Size: " + shotsSize);
    // i--;
    // System.out.println("After");
    // System.out.println("J: " + j);
    // System.out.println("I: " + i);
    // System.out.println("Asteroids Size: " + asteroidsSize);
    // System.out.println("Shots Size: " + shotsSize);
    // }
    // try
    // {
    // asteroidX = asteroids.get(j).getT().getTranslateX();
    // asteroidY = asteroids.get(j).getT().getTranslateY();
    // }
    // catch (Exception e)
    // {
    // System.out.println("Error with j");
    // System.out.println("Before");
    // System.out.println("J: " + j);
    // System.out.println("I: " + i);
    // System.out.println("Asteroids Size: " + asteroidsSize);
    // System.out.println("Shots Size: " + shotsSize);
    // j--;
    // System.out.println("J: " + j);
    // System.out.println("I: " + i);
    // System.out.println("Asteroids Size: " + asteroidsSize);
    // System.out.println("Shots Size: " + shotsSize);
    // }

    // //still problems with if statements
    // System.out.println("Before Checking for Hit");
    // System.out.println("Asteroids Size: " + asteroidsSize);
    // System.out.println("J: " + j);
    // if (shotX > asteroidX && shotX < asteroidX + (31 * asteroids.get(j).getScale()))
    // {
    // asteroids.remove(j);
    // shots.remove(i);
    // shotsSize--;
    // asteroidsSize--;
    // }
    // if (shotY > asteroidY && shotY < asteroidY + (31 * asteroids.get(j).getScale()))
    // {
    // asteroids.remove(j);
    // shots.remove(i);
    // shotsSize--;
    // asteroidsSize--;
    // }
    // }
    // }
    // }
    // }

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
