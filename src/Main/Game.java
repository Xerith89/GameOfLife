package Main;

import static Main.Main.getWindowHeight;
import static Main.Main.getWindowWidth;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class Game {
    
    private final GraphicsContext gc;
    private final int cellDimensions;
        
    private final Camera cam;
    private final Grid grid;
    private final CellManager cMan;
    private final Randomiser rand;
    private final Rules rules;
    private final Scene scene;
      
    Game(GraphicsContext gc, int cellDimensions,Scene scene)
    {
        this.gc = gc;
        this.cellDimensions = cellDimensions;
        cam = new Camera(0,0,getWindowWidth(),getWindowHeight(),cellDimensions, scene);
        grid = new Grid();
        cMan = new CellManager(cam);
        rand = new Randomiser(getWindowWidth(),getWindowHeight(), cam.getCellDimensions());
        rules = new Rules(cMan);
        this.scene = scene;
    }
        
    public void init()
    {    
        rand.generateRandomCells(1000,cMan);
        cMan.swap();             
    }
    
    private void update()
    { 
        rules.underpopulation();
        rules.overcrowding();
        rules.creation();
        cMan.swap();   
        cam.updateCamera(cMan);
    }
    
    private void render()
    {
        clearScreen(gc);
        grid.renderGrid(gc,cam);
        cMan.renderCells(gc);
    }
    
    public void run()
    {
        update();
        render();
    }
    
    private void clearScreen(GraphicsContext gc)
    {
        gc.clearRect(cam.getStartX(), cam.getStartY(), Main.getWindowWidth(), Main.getWindowHeight());
    }
    
}
