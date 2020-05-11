import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList; //use to just be java.util.*;
import java.util.Timer;
import java.util.TimerTask;
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
    private SetTrueTask task;
    private boolean schedule;
    private boolean addKeyCode40;
    private boolean addKeyCode38;
    private boolean addKeyCode39;
    private boolean addKeyCode37;
    private ArrayList<Integer> keyCodes;
    //private int typeAsteroid;
    //private Asteroid random;
    public GamePanel (Color c)
    {
        //hello again 2
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
        timer = new Timer("Shot Timer");

        schedule = true;
        addKeyCode40 = true;
        addKeyCode38 = true;
        addKeyCode39 = true;
        addKeyCode37 = true;
        keyCodes = new ArrayList<Integer>();
        //random = new Asteroid(.5, 100);
        this.setBackground(c);
        this.setFocusable(true);
        this.addKeyListener(new Turn());
        t.translate(500, 500);
        t.scale(1,1);
        t.rotate(-Math.PI/4, 16, 16);
        t2.translate(516, 500); //first # was 516
        t2.scale(1,1);
        t2.rotate(-Math.PI/4, 16, 16);
        t3.translate(516, 500);
        t3.scale(1,1);
        t3.rotate(-Math.PI/4, 16 ,16);
        task = new SetTrueTask(player, t3);
        //this.isFocusable();
    }

    public GamePanel ()
    {

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        try
        {
            ship = ImageIO.read(new File("download (315).png"));
            shot = ImageIO.read(new File("shot.png"));
        }
        catch (Exception e)
        {

        }
        g2D.drawImage(ship, t, this);
        //g2D.drawImage(shot, t3, this);
        player.drawShot(g2D, shot, this);
        //random.drawAsteroid(g2D, this, asteroid);
        while (numberAsteroids > 0)
        {
            asteroids.add(new Asteroid());
            numberAsteroids--;
        }
        for(int i = 0; i < asteroids.size(); i++)
        {
            asteroids.get(i).drawAsteroid(g2D, this, asteroids.get(i).getAsteroid());
            asteroids.get(i).teleportAsteroid();
        }
        //player.teleportShots(t4);
        //player.checkForHit(asteroids);

        // random.makeAsteroids(asteroids); //, g2D, this);
        // for (int i = 0; i < asteroids.size(); i++)
        // {
        // //System.out.println(i);
        // System.out.println(asteroids.get(i).toString());
        // asteroids.get(i).drawAsteroid(g2D,this);

        // }

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
            System.out.println(e.getKeyCode());
            //when you press one key and then press another it thinks the other was release and the new one is being pressed
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
                keyCodes.add(32);
                while (schedule == true)
                {
                    timer.scheduleAtFixedRate(task, 0 , 500);
                    schedule = false;
                }
                task.setCanShoot(true);
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
                else
                {
                    player.rotateCounterClockwise(t, t2, t3, original);
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
                    }
                }

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
                keyCodes.add(100);
                task.setCanShoot(false);
            }
        }

    }
}