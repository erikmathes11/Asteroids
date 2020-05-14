import java.util.*;
public class WaveTask extends TimerTask
{
    private boolean showMessage;
    public WaveTask()
    {
        super();
        showMessage = true;
    }
    
    public void run()
    {
        showMessage = false;
    }
    
    public void setShowMessage(boolean showMessage2)
    {
        showMessage = showMessage2;
    }
    
    public boolean getShowMessage()
    {
        return showMessage;
    }
    
}