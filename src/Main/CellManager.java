package Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public final class CellManager {
   
    private List<Cell> cells; 
    public List<Cell> cellBuffer;
    public List<Cell> deadCells;
        
    private final Camera cam;
    private float timer = 0;
    private final float target = Main.getFrameRate();
    private final int columns;
    private final int row;
    boolean canPress = true;
    
      
    public CellManager(Camera cam)
    {
       cells = new ArrayList();
       cellBuffer = new ArrayList();
       deadCells = new ArrayList();
              
       columns = Main.getWindowWidth()/cam.getCellDimensions();
       row = Main.getWindowHeight()/cam.getCellDimensions();
         
       this.cam = cam;
    }
    
    public List getCellList()
    {
        return cells;
    }
    
    public void createCell(int col, int row, boolean state)
    {
        Cell cell = new Cell(col,row,state);
        cellBuffer.add(cell);
    }
     
    public void swap()
    {
        Iterator<Cell> itr = cellBuffer.iterator(); 
        while (itr.hasNext()) 
        { 
           Cell p = itr.next();
           if (p.getState()==false) { 
               itr.remove();
           }
        }
        cells = cellBuffer;
    }
       
    public int checkNeighbours(Cell cell)
    {
        int count = 0;
        
        int startx = cell.getX()-cam.getCellDimensions();
        int starty = cell.getY()-cam.getCellDimensions();
        int endx = cell.getX()+(cam.getCellDimensions()*2);
        int endy = cell.getY()+(cam.getCellDimensions()*2);
            
            for (int i = starty; i < endy; i+=cam.getCellDimensions())
            {
                for (int j = startx; j < endx; j+=cam.getCellDimensions())
                {
                    deadCells.add(new Cell(j,i,false));
                    for (Cell c: cells)
                    {
                        if (c.getX()==j && c.getY() == i )
                        {
                            count++;
                            break;
                        }
                    }
                }
            }
        --count;
        return count;
    }
    
    private boolean checkBounds(Cell cell)
    {
        return cell.getX() >= cam.getStartX() && cell.getX() < cam.getEndX() 
                && cell.getY() >= cam.getStartY() && cell.getY() < cam.getEndY();
    }
                    
    public boolean checkLive(int x, int y)
    {
        return cellBuffer.stream().anyMatch((cell) -> (x == cell.getX() && y == cell.getY()));
    }
       
    public void renderCells(GraphicsContext gc)
    {               
        cells.stream().filter((c) -> (checkBounds(c))).forEachOrdered((c) -> {
        int startx = c.getX();
        int starty = c.getY();
        for (int i = starty; i < starty+cam.getCellDimensions(); i++)
        {
            for (int j = startx; j < startx+cam.getCellDimensions(); j++)
            {
                gc.getPixelWriter().setColor(j, i, Color.BLACK);
            }
        }});    
    }            
    
    public void updateCells(Scene scene)
    {
        if (canPress = true)
        {
            timer = 0;
            canPress = false;
            timer+=0.5f;
            scene.addEventFilter(KeyEvent.ANY, keyEvent -> {
            if (null != keyEvent.getCode()) 
                switch (keyEvent.getCode()) {
                    case DOWN:
                        cells.forEach((c) -> {
                            c.setY(c.getY()-1);
                        });     break;
                    case UP:
                        cells.forEach((c) -> {
                            c.setY(c.getY()+1);
                        });     break;
                    case LEFT:
                        cells.forEach((c) -> {
                            c.setX(c.getX()+1);
                        });     break;
                    case RIGHT:
                        cells.forEach((c) -> {
                            c.setX(c.getX()-1);
                        });     break;
                    default:
                        break;
                }}); 
            if (timer >= target)
            { canPress = true;}
        }
    }
}
