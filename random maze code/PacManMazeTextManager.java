import java.util.Scanner;

/**
 * Manager class that launches the text version of PacManMaze
 */

public class PacManMazeTextManager {
	/**
	 * Main method that uses the methods from the text logic file to run the game
	 * @param args
	 */
	public static void main(String[] args) {
		PacManMazeTextLogic main = new PacManMazeTextLogic();
		main.initialize();
		Scanner keyboard = new Scanner(System.in);
		// game loop		
		while (true) {
			String input = keyboard.nextLine();
			main.getInputAndMove(input);
			main.updateScore();
			main.printCurrentState();
		}
	}
}
