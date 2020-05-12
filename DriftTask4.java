import java.util.*;
import java.awt.geom.*;
public class DriftTask4 extends TimerTask
{
    private AffineTransform t;
    private AffineTransform t2;
    private AffineTransform t3;
    private static boolean decreaseSpeed4;
    public DriftTask4(AffineTransform t, AffineTransform t2, AffineTransform t3)
    {
        super();
        this.t = t;
        this.t2 = t2;
        this.t3 = t3;
    }

    public void run()
    {
        decreaseSpeed4 = true;
    }

    public static boolean getDecreaseSpeed4()
    {
        return decreaseSpeed4;
    }

    public static void setDecreaseSpeed4(boolean decreaseSpeed5)
    {
        decreaseSpeed4 = decreaseSpeed5;
    }
}