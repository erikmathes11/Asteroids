import javax.swing.*;
import java.awt.*;
public class Test
{
    public static void main(String[] args)
    {
        int score = 0;
        JFrame frame1 = new JFrame("Game");
        Test2 panel1 = new Test2();
        JLabel label = new JLabel("Score: " + score);
        panel1.add(label);
        panel1.setPreferredSize(new Dimension(1920,1080));
        frame1.add(panel1);
        frame1.pack();
        frame1.setVisible(true);
        // while (score < 10)
        // {
            // score++;
            // label.setText(score + " ");
        // }
    }
}