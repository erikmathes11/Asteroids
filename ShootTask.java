import java.util.*;
import java.awt.geom.*;
public class ShootTask extends TimerTask
{
    private boolean canShoot;
    private Ship player;
    private AffineTransform t3;
    public ShootTask(Ship player, AffineTransform t3)
    {
        super();
        canShoot = false;
        this.player = player;
        this.t3 = t3;
    }

    public void run()
    {
        if (canShoot == true)
        {
            player.shoot(t3);
        }
    }

    public void setCanShoot(boolean canShoot2)
    {
        canShoot = canShoot2;
    }

    public boolean getCanShoot()
    {
        return canShoot;
    }
}