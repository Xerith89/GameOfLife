package Main;

import java.util.Random;

public class Randomiser {
    
    private final int minRow = 0;
    private final int minCol = 0;
    private final Random rand;
    private final int cellDimensions;
    
    Randomiser(int gridWidth, int gridHeight, int cellDimensions)
    {
        rand = new Random();
        this.cellDimensions = cellDimensions;
    }
    
    public void generateRandomCells(int numOfCells, CellManager cMan)
    {
        int totalCols = Main.getWindowWidth()/cellDimensions;
        int totalRows = Main.getWindowHeight()/cellDimensions;
       
        for (int i = 0; i < numOfCells; i++)
        {
            cMan.createCell((rand.nextInt(totalCols)+0)*cellDimensions,(rand.nextInt(totalRows)+0)*cellDimensions, true);
        }
        
    }
}
