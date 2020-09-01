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
 * This class contains the window that will be displayed once the player wins the game
 * This window will contain a winning message, a score, a button that restarts the game
 * and a button that will end the application
 */

public class WinBox {


    /**
     * The display method displays the winning the window
     * This window contains a winning message, a score, a button that restarts the game
     * and a button that will end the application
     */
    public static void display(String title, String message, int score) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        //window.setMinWidth(250);

        Label messageLabel = new Label();
        messageLabel.setText(message);
	messageLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
	messageLabel.setTextFill(Color.BLACK);

	Label scoreLabel = new Label();
	scoreLabel.setText("Score: " + score);
	scoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
	scoreLabel.setTextFill(Color.BLACK);

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
	layout.setSpacing(50);

	Image image = new Image("winner.jpg");
	ImageView imView = new ImageView(image);

	StackPane stack = new StackPane();
	stack.setAlignment(Pos.CENTER);
	stack.getChildren().addAll(imView, layout);

	VBox vbox = new VBox();
	vbox.setAlignment(Pos.CENTER);
	vbox.getChildren().add(stack);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(vbox, 480, 420);
        window.setScene(scene);
        window.show();
	
    }

}
