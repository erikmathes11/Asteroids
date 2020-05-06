import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
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
    //private int typeAsteroid;
    //private Asteroid random;
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
            ship = ImageIO.read(new File("E:\\Game\\download (315).png"));
            shot = ImageIO.read(new File("E:\\Game\\shot.png"));
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
        player.teleportShots(t4);

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
            if(e.getKeyCode() == 40)//reverse
            {
                player.moveBackward(t, t2, t3);
            }
            if(e.getKeyCode() == 38)//forward
            {
                player.moveForward(t, t2, t3);
            }
            if(e.getKeyCode() == 39)//clockwise
            {
                player.rotateClockwise(t, t2, t3, original);
            }
            if(e.getKeyCode() == 37)//counter clockwise
            {
                player.rotateCounterClockwise(t, t2, t3, original);
            }
            if(e.getKeyCode() == 32)//shoot
            {
                player.shoot(t3);
            }
        }

    }
}