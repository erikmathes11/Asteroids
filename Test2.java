import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.lang.Math.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.lang.*;
public class Test2 extends JPanel
{
    private BufferedImage image;
    private AffineTransform t;
    private AffineTransform t2;
    private ClassLoader classLoader;
    public Test2()
    {
        super();
        t = new AffineTransform();
        t.translate(500,500);
        t2 = new AffineTransform();
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
        //g2D.drawImage(image, t2, this);
        g.drawImage(image, 517, 517, this); //works
        try
        {
            Thread.sleep(40);
        }
        catch(Exception e)
        {

        }
        this.repaint();
    }
}