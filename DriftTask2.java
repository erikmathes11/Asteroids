import java.util.*;
import java.awt.geom.*;
public class DriftTask2 extends TimerTask
{
    private Timer timer;
    private AffineTransform t;
    private AffineTransform t2;
    private AffineTransform t3;
    private static boolean decreaseSpeed2;
    public DriftTask2(AffineTransform t, AffineTransform t2, AffineTransform t3)
    {
        super();
        timer = new Timer();
        this.t = t;
        this.t2 = t2;
        this.t3 = t3;
    }

    public void run()
    {
        timer.schedule(new DriftTask3(t, t2, t3), 250);
        decreaseSpeed2 = true;
    }

    public static boolean getDecreaseSpeed2()
    {
        return decreaseSpeed2;
    }

    public static void setDecreaseSpeed2(boolean decreaseSpeed3)
    {
        decreaseSpeed2 = decreaseSpeed3;
    }
}