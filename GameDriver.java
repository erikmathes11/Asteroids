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
        JLabel waveLabel = new JLabel(" Wave: 0");
        scoreLabel.setForeground(Color.WHITE);
        waveLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), scoreLabel.getFont().getStyle(), 20));
        waveLabel.setFont(new Font(scoreLabel.getFont().getName(), scoreLabel.getFont().getStyle(), 20));
        panel1.add(scoreLabel);
        panel1.add(waveLabel);
        frame1.add(panel1);
        frame1.pack();
        frame1.setVisible(true);
        while (panel1.getWindowClose() == false)
        {
            if (panel1.getShip().getDead() == true)
            {
                scoreLabel.setText(panel1.getShip().getTotalPoints() + "");
                waveLabel.setText(panel1.getWave() + "");
                scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), scoreLabel.getFont().getStyle(), 100));
                waveLabel.setFont(new Font(scoreLabel.getFont().getName(), scoreLabel.getFont().getStyle(), 100));
                scoreLabel.setBounds(805, 425 , 900, 200);
                waveLabel.setBounds(805, 660 , 900, 200);
            }
            else
            {
                if (panel1.getResetScore() == true)
                {
                    //System.out.println("Reset Points");
                    panel1.getShip().setTotalPoints(0);
                    panel1.setWave(1);
                    panel1.setResetScore(false);
                }
                scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), scoreLabel.getFont().getStyle(), 20));
                waveLabel.setFont(new Font(scoreLabel.getFont().getName(), scoreLabel.getFont().getStyle(), 20));
                scoreLabel.setText("Score: " + panel1.getShip().getTotalPoints());
                waveLabel.setText(" Wave: " + panel1.getWave());
            }
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