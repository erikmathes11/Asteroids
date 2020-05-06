import javax.swing.*;
import java.awt.*;
public class Test
{
    public static void main(String[] args)
    {
        JFrame frame1 = new JFrame("Game");
        Test2 panel1 = new Test2();
        panel1.setPreferredSize(new Dimension(1920,1080));
        frame1.add(panel1);
        frame1.pack();
        frame1.setVisible(true);
    }
}