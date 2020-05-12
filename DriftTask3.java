import java.util.*;
import java.awt.geom.*;
public class DriftTask3 extends TimerTask
{
    private Timer timer;
    private AffineTransform t;
    private AffineTransform t2;
    private AffineTransform t3;
    private static boolean decreaseSpeed3;
    public DriftTask3(AffineTransform t, AffineTransform t2, AffineTransform t3)
    {
        super();
        timer = new Timer();
        this.t = t;
        this.t2 = t2;
        this.t3 = t3;
    }

    public void run()
    {
        timer.schedule(new DriftTask4(t, t2, t3), 5);
        decreaseSpeed3 = true;
        System.out.println("decreaseSpeed3 true");
    }

    public static boolean getDecreaseSpeed3()
    {
        return decreaseSpeed3;
    }

    public static void setDecreaseSpeed3(boolean decreaseSpeed4)
    {
        decreaseSpeed3 = decreaseSpeed4;
    }
}