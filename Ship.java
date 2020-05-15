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
    private int totalPoints;
    private boolean dead;
    public Ship ()
    {
        shots = new ArrayList<AffineTransform>();
        totalPoints = 0;
        dead = false;
    }

    public void moveForward(AffineTransform t, AffineTransform t2, AffineTransform t3)
    {
        t.translate(2, 2);
        t2.translate(2, 2);
        t3.translate(2, 2);
    }

    public void moveBackward(AffineTransform t, AffineTransform t2, AffineTransform t3)
    {
        t.translate(-2, -2);
        t2.translate(-2, -2);
        t3.translate(-2, -2);
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
    
    public void removeShots()
    {
        for (int i = shots.size() - 1; i >= 0; i--)
        {
            shots.remove(i);
        }
    }

    public int getTotalPoints()
    {
        return totalPoints;
    }

    public boolean getDead()
    {
        return dead;
    }

    public void setDead(boolean dead2)
    {
        dead = dead2;
    }

    public void setTotalPoints(int totalPoints2)
    {
        totalPoints = totalPoints2;
    }

    public void checkForShipHit(ArrayList<Asteroid> asteroids, AffineTransform t, GamePanel panel1)
    {
        for (int i = 0; i < asteroids.size(); i++)
        {
            double diameterAsteroid = asteroids.get(i).getSize2() * 31;
            double shipDimension = 31;
            if(i == asteroids.size())
            {
                i--;
            }
            try
            {
                if (asteroids.get(i).getT().getTranslateX() - 39 < t.getTranslateX() && asteroids.get(i).getT().getTranslateX() + diameterAsteroid + 39 > t.getTranslateX() + shipDimension     && asteroids.get(i).getT().getTranslateY() - 39 < t.getTranslateY() && asteroids.get(i).getT().getTranslateY() + diameterAsteroid + 39 > t.getTranslateY() + shipDimension)
                {
                    //System.out.println("Dead");
                    for (int j = asteroids.size() - 1; j >= 0; j--)
                    {
                        asteroids.remove(j);
                    }
                    panel1.setCountEnters(4);
                    panel1.setNumberAsteroids(0);
                    panel1.setNumberAsteroidsOnScreen2(0);
                    panel1.setNumberAsteroidsOriginal(0);
                    panel1.setAsteroidsToAdd(10);
                    dead = true;
                }
                if (asteroids.get(i).getT().getTranslateX() > t.getTranslateX() - 39 && asteroids.get(i).getT().getTranslateX() + diameterAsteroid < t.getTranslateX() + shipDimension + 39    && asteroids.get(i).getT().getTranslateY() > t.getTranslateY() - 39 && asteroids.get(i).getT().getTranslateY() + diameterAsteroid < t.getTranslateY() + shipDimension + 39)
                {
                    //System.out.println("Dead");
                    for (int j = asteroids.size() - 1; j >= 0; j--)
                    {
                        asteroids.remove(j);
                    }
                    panel1.setCountEnters(4);
                    panel1.setNumberAsteroids(0);
                    panel1.setNumberAsteroidsOnScreen2(0);
                    panel1.setNumberAsteroidsOriginal(0);
                    panel1.setAsteroidsToAdd(10);
                    dead = true;
                }
            }
            catch (Exception e)
            {
                i = 0;
            }
        }
    }

    public void checkForHit(ArrayList<Asteroid> asteroids, GamePanel panel1)
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
                        totalPoints += asteroids.get(j).getPointValue();
                        asteroids.get(j).setPointValue(200);
                    }
                    else if (asteroids.get(j).getSize() == 2 && shots.get(i).getTranslateX() > asteroids.get(j).getT().getTranslateX() - 15 && shots.get(i).getTranslateX() + diameterShot < asteroids.get(j).getT().getTranslateX() + diameterAsteroid + 15 && shots.get(i).getTranslateY() > asteroids.get(j).getT().getTranslateY() - 15 && shots.get(i).getTranslateY() + diameterShot < asteroids.get(j).getT().getTranslateY() + diameterAsteroid + 15)
                    {
                        asteroids.get(j).getT().scale(10.0 / 8.0, 10.0 / 8.0);
                        asteroids.get(j).getT().scale(.6, .6);
                        asteroids.get(j).setSize(3);
                        shots.remove(i);
                        totalPoints += asteroids.get(j).getPointValue();
                        asteroids.get(j).setPointValue(300);
                    }
                    else if (asteroids.get(j).getSize() == 3 && shots.get(i).getTranslateX() > asteroids.get(j).getT().getTranslateX() - 15 && shots.get(i).getTranslateX() + diameterShot < asteroids.get(j).getT().getTranslateX() + diameterAsteroid + 15 && shots.get(i).getTranslateY() > asteroids.get(j).getT().getTranslateY() - 15 && shots.get(i).getTranslateY() + diameterShot < asteroids.get(j).getT().getTranslateY() + diameterAsteroid + 15)
                    {
                        asteroids.get(j).getT().scale(10.0 / 6.0, 10.0 / 6.0);
                        asteroids.get(j).getT().scale(.4, .4);
                        asteroids.get(j).setSize(4);
                        shots.remove(i);
                        totalPoints += asteroids.get(j).getPointValue();
                        asteroids.get(j).setPointValue(400);
                    }
                    else if (shots.get(i).getTranslateX() > asteroids.get(j).getT().getTranslateX() - 15 && shots.get(i).getTranslateX() + diameterShot < asteroids.get(j).getT().getTranslateX() + diameterAsteroid + 15 && shots.get(i).getTranslateY() > asteroids.get(j).getT().getTranslateY() - 15 && shots.get(i).getTranslateY() + diameterShot < asteroids.get(j).getT().getTranslateY() + diameterAsteroid + 15)
                    {
                        totalPoints += asteroids.get(j).getPointValue();
                        asteroids.remove(j);
                        shots.remove(i);
                        panel1.setNumberAsteroidsOnScreen();
                    }
                }
                catch (Exception e)
                {
                    //System.out.println("J: " + j);
                    //System.out.println("I: " + i);
                    //System.out.println("Asteroids size: " + asteroids.size());
                    //System.out.println("Shots size: " + shots.size());
                }
            }
        }
    }

    public void teleportShots(AffineTransform t4)
    {
        for (int i = shots.size() - 1; i >= 0; i--)
        {
            if (shots.get(i).getTranslateX() > 2020)
            {
                t4.setTransform(shots.get(i));
                while (t4.getTranslateX() > -60 && t4.getTranslateY() > -60 && t4.getTranslateY() < 1140)
                {
                    t4.translate(-2, -2);
                }
                shots.get(i).setTransform(t4);
            }
            if(shots.get(i).getTranslateX() < -100)
            {
                t4.setTransform(shots.get(i));
                while(t4.getTranslateX() < 1970 && t4.getTranslateY() > -60 && t4.getTranslateY() < 1140) //1875
                {
                    t4.translate(-2, -2);
                }
                shots.get(i).setTransform(t4);
            }
            if (shots.get(i).getTranslateY() > 1180) //1080, + 35, 1045
            {
                t4.setTransform(shots.get(i));
                while(t4.getTranslateX() > -60 && t4.getTranslateX() < 1970 && t4.getTranslateY() > -60) //35
                {
                    t4.translate(-2, -2);
                }
                shots.get(i).setTransform(t4); //-1130
            }
            if(shots.get(i).getTranslateY() < -100)
            {
                t4.setTransform(shots.get(i));
                while(t4.getTranslateX() > -60 && t4.getTranslateX() < 1970 && t4.getTranslateY() < 1140) //1045
                {
                    t4.translate(-2, -2);
                }
                shots.get(i).setTransform(t4);
            }
        }
    }

    // public void teleportShip(AffineTransform t, AffineTransform t2, AffineTransform t3, AffineTransform original, AffineTransform t7)
    // {
        // if (t.getTranslateX() > 2020)
        // {
            // t7.setTransform(t);
            // while (t7.getTranslateX() > -60 && t7.getTranslateY() > -60 && t7.getTranslateY() < 1140)
            // {
                // t7.translate(-2, -2);
            // }
            // t.setTransform(t7);
        // }
        // if(t.getTranslateX() < -100)
        // {
            // t7.setTransform(t);
            // while(t7.getTranslateX() < 1970 && t7.getTranslateY() > -60 && t7.getTranslateY() < 1140) //1875
            // {
                // t7.translate(-2, -2);
            // }
            // t.setTransform(t7);
        // }
        // if (t.getTranslateY() > 1180) //1080, + 35, 1045
        // {
            // t7.setTransform(t);
            // while(t7.getTranslateX() > -60 && t7.getTranslateX() < 1970 && t7.getTranslateY() > -60) //35
            // {
                // t7.translate(-2, -2);
            // }
            // t.setTransform(t7); //-1130
        // }
        // if(t.getTranslateY() < -100)
        // {
            // t7.setTransform(t);
            // while(t7.getTranslateX() > -60 && t7.getTranslateX() < 1970 && t7.getTranslateY() < 1140) //1045
            // {
                // t7.translate(-2, -2);
            // }
            // t.setTransform(t7);
        // }
    // }

}
