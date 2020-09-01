import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

/**
 * This class contains the screen that is displayed when the user loses the game
 * The user has a choice to play the game again or quit the application
 */


public class LoseBox {
	

	/**
	 * The display method will display a window that shows the lose message,
	 * points, and 2 buttons to play the game again, or to quit the program
	 * @param title the title of the window
	 * @param message the message displayed on screen
	 * @param score the score of the player
	 */
    	public static void display(String title, String message, int score) {
        	Stage window = new Stage();

        	//Block events to other windows
        	window.initModality(Modality.APPLICATION_MODAL);
        	window.setTitle(title);
        	//window.setMinWidth(250);

	        Label messageLabel = new Label();
	        messageLabel.setText(message);
		messageLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 45));
		messageLabel.setTextFill(Color.rgb(153, 0, 0));

		Label scoreLabel = new Label();
		scoreLabel.setText("Score: " + score);
		scoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		scoreLabel.setTextFill(Color.rgb(153, 0, 0));


	        Button playButton = new Button("Play Again");
	        playButton.setOnAction(e -> {
			window.close();
			StartScreen.display();
		
		});

		Button closeButton = new Button("Quit");
		closeButton.setOnAction(e -> {
			System.exit(0);
	
		});

		HBox buttonLayout = new HBox();
		buttonLayout.getChildren().addAll(playButton, closeButton);
		buttonLayout.setAlignment(Pos.CENTER);
		buttonLayout.setSpacing(50);

	        VBox layout = new VBox();
	        layout.getChildren().addAll(messageLabel, scoreLabel, buttonLayout);
	        layout.setAlignment(Pos.CENTER);
		layout.setSpacing(30);

		Image image = new Image("over.jpg");
		ImageView imView = new ImageView(image);

		StackPane stack = new StackPane();
		stack.setAlignment(Pos.CENTER);
		stack.getChildren().addAll(imView, layout);

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(stack);

	        //Display window and wait for it to be closed before returning
	        Scene scene = new Scene(vbox, 600, 600);
	        window.setScene(scene);
	        window.show();
	}

}
