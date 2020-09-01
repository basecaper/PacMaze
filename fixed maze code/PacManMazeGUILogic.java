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
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;


/**
 * This class contains the main logic of the pacmaze gui application
 */

public class PacManMazeGUILogic extends Application{

	/***
	 * Instance variables
	 */
	
	private final int rectangleScale = 20;
	
	private Avatar avatar;
	private Ghost[] ghost;
	private Wall wall;
	private Pellet pellet;
	private GameWin gameWin;

	private int[][] wallList;
	private Circle[][] pelletCircleList; //keeps the IDs of the pellets
	private Circle avatarCircle;
	private Rectangle[] ghostRectangle;
	private Rectangle gameWinRectangle;
	private Pane layout;
	
	private Timeline timeline; // this takes care of the ghost's animation
	

	/**
	 * Start method that creates the GUI
	 */
	@Override
	public void start(Stage primaryStage) {

		// initialize all the variables and create helper variables
		wall = new Wall();
		wallList = wall.getWalls();
		pellet = new Pellet(this.wallList);	
		pelletCircleList = new Circle[wallList.length][wallList[0].length];

		// set title  and create pane layout
		primaryStage.setTitle("PacManMaze");
		layout = new Pane();
		
		// initialize the avatar, ghosts, gameWin and display the walls and pellets
		displayWallsAndPellets();
		createAvatar();
		createGhost();		
		createGameWin();
				
		Label scoreCounterlbl = new Label("Score: " + avatar.getScore());
		scoreCounterlbl.setTextFill(Color.WHITE);
		scoreCounterlbl.setLayoutX(avatar.getX());
		scoreCounterlbl.setLayoutY(avatar.getY());
		layout.getChildren().add(scoreCounterlbl);

		//Create new scene, add the pane to scene, add the scene to stage
		Scene scene = new Scene(layout, 800, 800);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();		

		// show the stage
		primaryStage.show();
		
		// handle keyboard input
		scene.setOnKeyPressed(event -> {
			// get the key entered
			String input = event.getText();
			
			if (event.getCode() == KeyCode.ESCAPE) {
				System.exit(0);
			}
			// move the avatar
			if (avatar.checkAndMove(input, wallList)) {
				avatarCircle.setCenterX((avatar.getX() * rectangleScale) + (rectangleScale/2));
				avatarCircle.setCenterY((avatar.getY() * rectangleScale) + (rectangleScale/2));
				if (wallList[(int) avatar.getY()][(int) avatar.getX()] == 2) {
					avatar.addScore(10);
					wallList[(int) avatar.getY()][(int) avatar.getX()] = 0;
					layout.getChildren().remove(pelletCircleList[(int) avatar.getY()][(int) avatar.getX()]);
					scoreCounterlbl.setText("Score: " + avatar.getScore());
				}
				// check if player and ghost collide
				if (ghost[0].overlapsWith(avatar) || ghost[1].overlapsWith(avatar) || ghost[2].overlapsWith(avatar) || ghost[3].overlapsWith(avatar)) {
                                        timeline.stop();  // this ensures the animation stops and the ghosts stop moving
                                        primaryStage.close();  // close the game window
                                        // display the winner window
                                        LoseBox.display("GAME OVER", "Game Over, You Lose!!", avatar.getScore());
                                }
				// check if player reaches endpoint
				if (gameWin.isAtEndPoint(avatar)) {
					timeline.stop();  // this ensures the animation stops and the ghosts stop moving
					primaryStage.close();  // close the game window
					// display the winner window
					WinBox.display("Winner", "Congratulations, You Win!!", avatar.getScore());
				}
			}
		});


		// implement the ghost animation
		timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), event -> {
			// make the ghost move
			for (int x = 0; x < 4; x++) {
				ghost[x].move(ghost[x].pickMove(this.wallList, this.avatar));
                        	ghostRectangle[x].setX(ghost[x].getX() * rectangleScale);
                        	ghostRectangle[x].setY(ghost[x].getY() * rectangleScale);
			}
			// check if player and ghost collide
                                if (ghost[0].overlapsWith(avatar) || ghost[1].overlapsWith(avatar) || ghost[2].overlapsWith(avatar) || ghost[3].overlapsWith(avatar)) {
                                        timeline.stop();  // this ensures the animation stops and the ghosts stop moving
                                        primaryStage.close();  // close the game window
                                        // display the winner window
                                        LoseBox.display("GAME OVER", "Game Over, You Lose!!", avatar.getScore());
                                }
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	/**
	 * Method that will display the walls and pellets in the window
	 */
	public void displayWallsAndPellets() {
		// turn wall points into rectangle and add them to pane
		int scale = rectangleScale;
		Circle currentCircle = null;
		
		for (int row = 0; row < wallList.length; row++) {
			for (int column = 0; column < wallList[0].length; column++ ) {
				if (wallList[row][column] == 1)
					layout.getChildren().add(new Rectangle(column*scale, row*scale, scale, scale));
				else if (wallList[row][column] == 2) {
					currentCircle = new Circle(column*scale + scale / 2, row*scale +  scale/ 2, scale/10, Color.GREEN);
					layout.getChildren().add(currentCircle);
					pelletCircleList[row][column] = currentCircle;
				}
			}
		}
	}
	
	/**
	 * Method that initializes the avatar and creates a rectangle that will represent the avatar on screen
	 */
	public void createAvatar() {
		int scale = rectangleScale;
		this.avatar = new Avatar();
		this.avatarCircle = new Circle(avatar.getX()*scale + scale/2, avatar.getY()*scale + scale/2, scale/2, Color.GOLD);
		layout.getChildren().add(avatarCircle);
	}
	
	/**
	 * Method that fills the ghost array with 4 ghosts and creates four rectangles that will represent the 4 ghosts on screen
	 */
	public void createGhost() {
		this.ghost = new Ghost[4];
		this.ghostRectangle = new Rectangle[4];
		for (int x = 0; x < 4; x++) {
			ghost[x] = new Ghost(wallList);
			ghostRectangle[x] = new Rectangle(ghost[x].getY() * rectangleScale, ghost[x].getX() * rectangleScale, rectangleScale, rectangleScale);
			if (x == 0) {
					ghostRectangle[x].setFill(Color.RED);		
			}
			if (x == 1) {
					ghostRectangle[x].setFill(Color.HOTPINK);		
			}
			if (x == 2) {
					ghostRectangle[x].setFill(Color.CYAN);		
			}
			if (x == 3) {
					ghostRectangle[x].setFill(Color.DARKORANGE);		
			}
			layout.getChildren().add(ghostRectangle[x]);	
		}
	}

	/**
	 * Method that initializes the gameWin object and creates a rectangle that will represent the gameWin object on the screen
	 */
	public void createGameWin() {
		this.gameWin = new GameWin();
		this.gameWinRectangle = new Rectangle(gameWin.getY() * rectangleScale, gameWin.getX() * rectangleScale, rectangleScale, rectangleScale);
		gameWinRectangle.setFill(Color.CRIMSON);
		layout.getChildren().add(gameWinRectangle);
		gameWinRectangle.setX(gameWin.getX() * rectangleScale);
		gameWinRectangle.setY(gameWin.getY() * rectangleScale);

	}














}
