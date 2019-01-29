package Main;

import javafx.scene.Scene;

public class Camera {

    private final int startX,startY,endX,endY,cellDimensions;      
    private final Scene scene;
         
    Camera(int left, int top, int right, int bottom, int cellDimensions, Scene scene)
    {
        startX = left;
        startY = top;
        endX = right;
        endY = bottom;
        this.scene = scene;
        this.cellDimensions = cellDimensions;
    }
    
    public final int getCellDimensions()
    {
        return cellDimensions;
    }
    
    public final int getStartX()
    {
        return startX;
    }
    
    public final int getStartY()
    {
        return startY;
    }
    
    public final int getEndX()
    {
        return endX;
    }
    
    public final int getEndY()
    {
        return endY;
    }
        
    public void updateCamera(CellManager cMan)
    {        
        cMan.updateCells(scene);
    }
}
