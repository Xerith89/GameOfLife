package Main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {
    
    
     
    Grid()
    {
    }
             
    public void renderGrid(GraphicsContext gc, Camera cam)
    { 
        for (int i = cam.getStartX(); i <= cam.getEndX(); i++)
        {
            for (int j = cam.getStartY(); j <= cam.getEndY(); j++)
            {
                gc.getPixelWriter().setColor(j*cam.getCellDimensions(), i*cam.getCellDimensions(), Color.BLACK);
            }
        }
      
    }        
}
