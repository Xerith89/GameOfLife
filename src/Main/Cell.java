package Main;

public class Cell {
    private int x, y;
    private boolean state = false;
    
    public Cell(int x, int y, boolean state)
    {
       this.x = x;
       this.y = y;
       this.state = state;
    }
        
    public final int getX()
    {
        return x;
    }
    
    public void setX(int new_x)
    {
         x = new_x;
    }
    public final int getY()
    {
        return y;
    }
    
    public void setY(int new_y)
    {
         y = new_y;
    }
    
    public void setState(boolean state)
    {
        this.state = state;
    }
    
    public boolean getState()
    {
        return this.state;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        return (obj instanceof Cell && ((Cell) obj).x == this.x && ((Cell) obj).y == this.y );
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.x;
        hash = 19 * hash + this.y;
        hash = 19 * hash + (this.state ? 1 : 0);
        return hash;
    }
}
