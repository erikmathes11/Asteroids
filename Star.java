import java.awt.*;
public class Star
{
    private int x;
    private int y;
    private int radius;
    private Color c;
    public Star(int x, int y, int radius, Color c)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.c = c;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getRadius()
    {
        return radius;
    }
    
    public Color getColor()
    {
        return c;
    }
}