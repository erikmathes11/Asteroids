import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Math.*;
public class GamePanel extends JPanel
{
    private Color c;
    private Ship player;
    private BufferedImage ship;
    private BufferedImage shot;
    private BufferedImage asteroid;
    private AffineTransform t;
    private AffineTransform t2;
    private AffineTransform t3;
    private AffineTransform t4;
    private AffineTransform original;
    private ArrayList<Asteroid> asteroids;
    private int numberAsteroids;
    private Timer timer;
    private ShootTask task;
    private DriftTask task2;

    private boolean schedule;
    private boolean schedule2;

    private boolean addKeyCode40;
    private boolean addKeyCode38;
    private boolean addKeyCode39;
    private boolean addKeyCode37;
    private boolean addKeyCode32;
    private int starsNumber;
    private ArrayList<Star> stars;
    private ArrayList<Integer> keyCodes;
    public GamePanel (Color c)
    {
        super();
        this.c = c;
        player = new Ship();
        t = new AffineTransform();
        t2 = new AffineTransform();
        t3 = new AffineTransform();
        t4 = new AffineTransform();
        original = new AffineTransform();
        asteroids = new ArrayList<Asteroid>();
        numberAsteroids = 30;
        timer = new Timer();

        schedule = true;
        schedule2 = true;

        addKeyCode40 = true;
        addKeyCode38 = true;
        addKeyCode39 = true;
        addKeyCode37 = true;
        addKeyCode32 = true;
        starsNumber = 300;
        stars = new ArrayList<Star>();
        keyCodes = new ArrayList<Integer>();
        this.setBackground(c);
        this.setFocusable(true);
        this.addKeyListener(new Turn());
        t.translate(500, 500);
        t.scale(1,1);
        t.rotate(-Math.PI/4, 16, 16);
        t2.translate(516, 500);
        t2.scale(1,1);
        t2.rotate(-Math.PI/4, 16, 16);
        t3.translate(516, 500);
        t3.scale(1,1);
        t3.rotate(-Math.PI/4, 16 ,16);
        task = new ShootTask(player, t3);
        task2 = new DriftTask();
        while (starsNumber > 0)
        {
            stars.add(new Star((int)(Math.random() * 1921) + 0, (int)(Math.random() * 1081) + 0, (int)(Math.random() * 6) + 1, new Color((int)(Math.random() * 241) + 0, (int)(Math.random() * 241) + 0, 0)));
            starsNumber--;
        }
        try
        {
            ship = ImageIO.read(new File("download (315).png"));
            shot = ImageIO.read(new File("shot.png"));
        }
        catch (Exception e)
        {

        }
    }

    public GamePanel ()
    {

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for (int i = 0; i < stars.size(); i++)
        {
            g.setColor(stars.get(i).getColor());
            g.fillOval(stars.get(i).getX(), stars.get(i).getY(), stars.get(i).getRadius(), stars.get(i).getRadius());
        }
        g2D.drawImage(ship, t, this);
        player.drawShot(g2D, shot, this);
        while (numberAsteroids > 0)
        {
            asteroids.add(new Asteroid());
            numberAsteroids--;
        }
        for (int i = 0; i < asteroids.size(); i++)
        {
            asteroids.get(i).drawAsteroid(g2D, this, asteroids.get(i).getAsteroid());
            asteroids.get(i).teleportAsteroid();
        }
        //player.teleportShots(t4);
        //player.checkForHit(asteroids);
        try
        {
            Thread.sleep(40);
        }
        catch(Exception e)
        {

        }
        this.repaint();
    }

    public class Turn extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            //System.out.println(e.getKeyCode());
            if(e.getKeyCode() == 40)//reverse
            {
                while (addKeyCode40 == true)
                {
                    keyCodes.add(40);
                    addKeyCode40 = false;
                }

            }
            if(e.getKeyCode() == 38)//forward
            {

                while (addKeyCode38 == true)
                {
                    keyCodes.add(38);
                    addKeyCode38 = false;
                    try
                    {
                        ship = ImageIO.read(new File("shipMoving (315).png"));
                    }
                    catch (Exception error)
                    {

                    }
                }

            }
            if(e.getKeyCode() == 39)//clockwise
            {
                while (addKeyCode39 == true)
                {
                    keyCodes.add(39);
                    addKeyCode39 = false;
                }

            }
            if(e.getKeyCode() == 37)//counter clockwise
            {
                while (addKeyCode37 == true)
                {
                    keyCodes.add(37);
                    addKeyCode37 = false;
                }

            }
            if(e.getKeyCode() == 32)//shoot
            {
                while (addKeyCode32 == true)
                {
                    keyCodes.add(32);
                    addKeyCode32 = false;
                }

            }
            for (int i = 0; i < keyCodes.size(); i++)
            {
                if (keyCodes.get(i) == 40)
                {
                    player.moveBackward(t, t2, t3);
                }
                else if (keyCodes.get(i) == 38)
                {
                    player.moveForward(t, t2, t3);
                }
                else if (keyCodes.get(i) == 39)
                {
                    player.rotateClockwise(t, t2, t3, original);
                }
                else if (keyCodes.get(i) == 37)
                {
                    player.rotateCounterClockwise(t, t2, t3, original);
                }
                else
                {
                    while (schedule == true)
                    {
                        timer.scheduleAtFixedRate(task, 0 , 500);
                        schedule = false;
                    }
                    task.setCanShoot(true);
                }
            }
        }

        public void keyReleased(KeyEvent e)
        {
            System.out.println(e.getKeyCode());
            if(e.getKeyCode() == 40)//reverse
            {
                for (int i = 0; i < keyCodes.size(); i++)
                {
                    if(keyCodes.get(i) == 40)
                    {
                        keyCodes.remove(i);
                        addKeyCode40 = true;
                    }
                }

            }
            if(e.getKeyCode() == 38)//forward
            {

                for (int i = 0; i < keyCodes.size(); i++)
                {
                    if(keyCodes.get(i) == 38)
                    {
                        keyCodes.remove(i);
                        addKeyCode38 = true;
                        try
                        {
                            ship = ImageIO.read(new File("download (315).png"));
                        }
                        catch (Exception error)
                        {

                        }
                    }
                }
                int drift = 1;
                if (schedule2 == true) //if you realease again before the time is up it glitches out
                {
                    timer.scheduleAtFixedRate(task2, 0, 10000);
                    schedule2 = false;
                }

                while(drift > .1)
                {
                    System.out.println("Drift");
                    t.translate(drift, drift);
                    t2.translate(drift, drift);
                    t3.translate(drift, drift);
                    if (task2.getChangeDrift() == true)
                    {
                        drift /= 2;
                        System.out.println("Change Drift");
                        task2.setChangeDrift(false);
                    }
                }

                // timer.schedule(new DriftTask(t, t2, t3), 0);
                // boolean repeat = true;
                // while(repeat == true)
                // {
                // // System.out.println("DecreaseSpeed: " + DriftTask.getDecreaseSpeed());
                // // System.out.println("DecreaseSpeed2: " + DriftTask2.getDecreaseSpeed2());
                // // System.out.println("DecreaseSpeed3: " + DriftTask3.getDecreaseSpeed3());
                // // System.out.println("DecreaseSpeed4: " + DriftTask4.getDecreaseSpeed4());
                // if(DriftTask.getDecreaseSpeed() == true && DriftTask2.getDecreaseSpeed2() == false)
                // {
                // System.out.println("Translate .5");
                // // t.translate(.5, .5);
                // // t2.translate(.5, .5);
                // // t3.translate(.5, .5);
                // }
                // else if(DriftTask2.getDecreaseSpeed2() == true && DriftTask3.getDecreaseSpeed3() == false)
                // {
                // DriftTask.setDecreaseSpeed(false);
                // System.out.println("Translate .25");
                // // t.translate(.25, .25);
                // // t2.translate(.25, .25);
                // // t3.translate(.25, .25);
                // }
                // else if(DriftTask3.getDecreaseSpeed3() == true && DriftTask4.getDecreaseSpeed4() == false)
                // {
                // DriftTask2.setDecreaseSpeed2(false);
                // System.out.println("Translate .125");
                // // t.translate(.125, .125);
                // // t2.translate(.125, .125);
                // // t3.translate(.125, .125);
                // }
                // else
                // {
                // System.out.println("stop");
                // DriftTask3.setDecreaseSpeed3(false);
                // DriftTask4.setDecreaseSpeed4(false);
                // repeat = false;
                // }
                // // System.out.println("x: " + t.getTranslateX());
                // // System.out.println("y: " + t.getTranslateY());
                // }
                // // DriftTask.setDecreaseSpeed(false);
                // // DriftTask2.setDecreaseSpeed2(false);
                // // DriftTask3.setDecreaseSpeed3(false);
                // // DriftTask4.setDecreaseSpeed4(false);
            }
            if(e.getKeyCode() == 39)//clockwise
            {
                for (int i = 0; i < keyCodes.size(); i++)
                {
                    if(keyCodes.get(i) == 39)
                    {
                        keyCodes.remove(i);
                        addKeyCode39 = true;
                    }
                }

            }
            if(e.getKeyCode() == 37)//counter clockwise
            {
                for (int i = 0; i < keyCodes.size(); i++)
                {
                    if(keyCodes.get(i) == 37)
                    {
                        keyCodes.remove(i);
                        addKeyCode37 = true;
                    }
                }

            }
            if (e.getKeyCode() == 32) //shoot
            {
                for (int i = 0; i < keyCodes.size(); i++)
                {
                    if(keyCodes.get(i) == 32)
                    {
                        keyCodes.remove(i);
                        addKeyCode32 = true;
                    }
                }
                task.setCanShoot(false);
            }
        }

    }
}