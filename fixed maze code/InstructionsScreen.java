import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle; 
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Node;
import java.util.Scanner;
import javafx.geometry.Pos;
import java.awt.Point;
import java.util.ArrayList;


/**
 * this is the window that contains the game instructions
 */
public class InstructionsScreen extends Application {
	/**
	 * the start method of this javafx class
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Instructions");

		VBox root = new VBox();
			
		Label label = new Label("Instructions");
		Label label1 = new Label("1) You are the Yellow Circle");
		Label label2 = new Label ("2) Use the w a s d keys to move:");
		Label label3 = new Label ("3) w-up s-down a-left d-right");
		Label label4 = new Label ("4) The ghosts are the colourful moving squares");
		Label label5 = new Label ("5) The objective is to get to the end of maze (the red square) without hitting");
		Label label6 = new Label ("     the ghosts");

		root.getChildren().addAll(label, label1, label2, label3, label4, label5, label6);

		root.setSpacing(15);

		Scene scene = new Scene(root, 550 ,550);

		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}

	

	
