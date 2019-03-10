package Main;

import java.util.Arrays;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

public class Main extends Application {
    
    private static final int WINDOWWIDTH = 700;
    private static final int WINDOWHEIGHT = 500;
    private static final int FRAMERATE = 1000000;

    Game game;
    
    public static final int getWindowWidth()
    {
        return WINDOWWIDTH;
    }
    
    public static final int getWindowHeight()
    {
        return WINDOWHEIGHT;
    }  
    
    public static final int getFrameRate()
    {
        return FRAMERATE;
    }  
        
    @Override
    public void start(Stage primaryStage)throws Exception {
                
        Group root = new Group();
        Canvas canvas = new Canvas(WINDOWWIDTH,WINDOWHEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, WINDOWWIDTH,WINDOWHEIGHT);
        game = new Game(gc,5,scene);
                
        game.init();
        
        root.getChildren().add(canvas);
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(scene);
        
        primaryStage.show();
        try {
            new AnimationTimer() {
               private long lastUpdate = 0 ;
                @Override
                public void handle(long now) {
                        if (now - lastUpdate >= FRAMERATE) {
                            game.run();
                            lastUpdate = now ;
                        }
                }}.start();
        }catch (Exception e){
            System.err.print("Exception in Anim Timer Loop " + e.getMessage() + "Stack Trace: " + Arrays.toString(e.getStackTrace()));
        }   
    }
         
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try { launch(args);
        } catch (Exception e)
        {
            System.err.print("Exception caught in main loop " + e.getMessage());
        }
    }
    
}
