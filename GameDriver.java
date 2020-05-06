import javax.swing.*;
import java.awt.*;
import java.lang.Math.*;
public class GameDriver
{
    public static void mian(String[] args)
    {
        JFrame frame1 = new JFrame("Game");
        GamePanel panel1 = new GamePanel(Color.BLACK);
        panel1.setPreferredSize(new Dimension(1920,1080));
        frame1.add(panel1);
        frame1.pack();
        frame1.setVisible(true);
        
    }
}