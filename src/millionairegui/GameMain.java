
package millionairegui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Initializes model, view and controller and adds a observer in model class of parameter view
 * @author Yashjeet Bajwa 17995368, Ranish Chand 17981697
 */
public class GameMain {
    /**
     * Default constructor initializing all class objects.
     */
    GameMain(){
        try {
            View view = new View();
            Model model = new Model();
            Controller controller = new Controller(view, model);
            model.addObserver(view);
        } catch (IOException ex) {
            Logger.getLogger(GameMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args){
        GameMain game = new GameMain();
        
    }
}
