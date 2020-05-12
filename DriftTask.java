import java.util.*;
import java.awt.geom.*;
public class DriftTask extends TimerTask
{
    private boolean changeDrift;
    public DriftTask()
    {
        changeDrift = false;
    }
    
    public void run()
    {
        changeDrift = true;
    }
    
    public void setChangeDrift(boolean changeDrift2)
    {
        changeDrift = changeDrift2;
    }
    
    public boolean getChangeDrift()
    {
        return changeDrift;
    }
    
    
    
    // private Timer timer;
    // private AffineTransform t;
    // private AffineTransform t2;
    // private AffineTransform t3;
    // private static boolean decreaseSpeed;
    // public DriftTask(AffineTransform t, AffineTransform t2, AffineTransform t3)
    // {
        // super();
        // timer = new Timer();
        // this.t = t;
        // this.t2 = t2;
        // this.t3 = t3;
        // decreaseSpeed = false;
    // }

    // public void run()
    // {
        // timer.schedule(new DriftTask2(t, t2, t3), 5);
        // decreaseSpeed = true;
        // System.out.println("decreaseSpeed true");
    // }
    
    // public static boolean getDecreaseSpeed()
    // {
        // return decreaseSpeed;
    // }
    
    // public static void setDecreaseSpeed(boolean decreaseSpeed2)
    // {
        // decreaseSpeed = decreaseSpeed2;
    // }
}