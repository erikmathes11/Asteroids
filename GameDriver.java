import javax.swing.*;
import java.awt.*;
import java.lang.Math.*;
public class GameDriver
{
    public static void mian(String[] args)
    {
        JFrame frame1 = new JFrame("Game");
        GamePanel panel1 = new GamePanel(Color.BLACK, frame1);
        panel1.setPreferredSize(new Dimension(1920,1080));
        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.WHITE);
        panel1.add(scoreLabel);
        frame1.add(panel1);
        frame1.pack();
        frame1.setVisible(true);
        while (panel1.getWindowClose() == false)
        {
            scoreLabel.setText("Score: " + panel1.getShip().getTotalPoints());
            try
            {
                Thread.sleep(40);
            }
            catch(Exception e)
            {

            }
        }
    }
}