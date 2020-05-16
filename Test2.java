import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.lang.Math.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.lang.*;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class Test2 extends JPanel
{
    private BufferedImage image;
    private AffineTransform t;
    private AffineTransform t2;
    private Timer timer;
    private TestTask task;
    private TestTask task2;
    public Test2()
    {
        super();
        t = new AffineTransform();
        t.translate(500,500);
        t2 = new AffineTransform();
        timer = new Timer();
        task = new TestTask();
        task2 = new TestTask();
        timer.schedule(task, 10000);
        timer.schedule(task2, 20000);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task3 = () -> System.out.println("Running Task3");
        executor.schedule(task3, 5, TimeUnit.SECONDS);
        executor.shutdown();
        int count = 0;
        while (count < 10)
        {
            count++;
            System.out.println(count);
        }
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(task3, 5, TimeUnit.SECONDS);
        executor.shutdown();
        count = 0;
        while (count < 10)
        {
            count++;
            System.out.println(count);
        }
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        try
        {
            //image = ImageIO.read(new File("E:\\Game\\download (315).png"));
            image = ImageIO.read(new File("download (315).png"));
        }
        catch (Exception e)
        {

        }
        //t.rotate(Math.PI/4, 20, 20);
        //t2.setTransform(t);
        //t.scale(50,50);
        //t.rotate(Math.PI/4,12,12);
        //g2D.setTransform(t);
        //t.translate(.5, .5);
        //t.scale(1.1, 1.1);
        //t.scale(10.0 / 11.0, 10.0 / 11.0);
        g2D.drawImage(image, t, this);
        //t.setToTranslation(900, 900);
        //g.drawImage(image, 517, 517, this); //works
        try
        {
            Thread.sleep(40);
        }
        catch(Exception e)
        {

        }
        this.repaint();
    }
    
        public class WindowListener1 extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            System.out.println("Close");
        }
    }
}