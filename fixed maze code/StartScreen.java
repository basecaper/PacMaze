import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle; 
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import java.util.Scanner;
import javafx.scene.control.TextField;
import java.awt.Point;
import javafx.geometry.Pos;
import java.util.ArrayList;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This is the class through which we launch our GUI application
 * This class opens a window with 2 buttons
 * One button if clicked contains the game instructions
 * The other button if clicked launches the game
 */
public class StartScreen {

	/**
	 * The display method shows a screen where one button starts the game and another button gives the instructions
	 *
	 */	
	public static void display() {
		Stage primaryStage = new Stage();
		
		primaryStage.setTitle("PACMAZE");
		
		VBox root = new VBox();

		Label label = new Label("PACMAZE");
		label.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
		
		// create a button that starts the game
		Button button = new Button("Start Game");
		button.setOnAction( new EventHandler<ActionEvent>() {	
		@Override
			public void handle(ActionEvent event) {
				Stage menuStage = new Stage();
				PacManMazeGUILogic menu = new PacManMazeGUILogic();
				menu.start(menuStage);
				menuStage.show();
				primaryStage.close();
			}
		}
		);

		// create a button that displays the instructions
		Button button2 = new Button("Instructions");
		button2.setOnAction( new EventHandler<ActionEvent>() {	
		@Override
			public void handle(ActionEvent event) {
				Stage menuStage = new Stage();
				InstructionsScreen menu = new InstructionsScreen();
				menu.start(menuStage);
				menuStage.show();
			}

		});

		root.setAlignment(Pos.CENTER);
		root.setSpacing(40);
		root.getChildren().addAll(label, button, button2);

		Image image = new Image("pacman.png");
		ImageView imView = new ImageView(image);

		StackPane stack = new StackPane();
		stack.setAlignment(Pos.CENTER);
		stack.getChildren().addAll(imView, root);
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(stack);

		Scene scene = new Scene(vbox, 350, 350);
		
		primaryStage.setScene(scene);
		primaryStage.show();			
	}
}
	











