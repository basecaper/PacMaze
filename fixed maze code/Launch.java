import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The game is launched through this class
 * In order to play the game, we run this class
 * This class launches the startscreen window
 */
public class Launch extends Application{

	public static void main(String[] args) {
		launch(args);
	
	}

	@Override
    	public void start(Stage window) {
      		StartScreen.display();		

    }

















}
