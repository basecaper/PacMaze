import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import java.awt.Point;

/**
 * The Pellet class contains the variables and methods of Pellet objects
 * By pellet, we mean the white dots that pacman collects in the original game.
 */

public class Pellet {
	// instance variables
	private ArrayList<Point> location = new ArrayList<Point>();

	/**
	 * Generates the pellets into the gameboard
	 * @param gameBoard the 2d array that contains the wall locations
	 */
	public Pellet(int[][] gameBoard) {
		this.generatePellet(gameBoard);			
	}

	/**
	 * Checks if one of the Pellet locations overlaps with Avatar location
	 * @param avatar the Avatar object whose location will be checked for overlapping
	 * @return a boolean that states whether there is overlapping or not
	 */
	public boolean overlapsWith(Avatar avatar) {
		for (Point eachPoint : location) {
			if (eachPoint.equals(avatar.getLocation())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Fills the empty spaces in the gameBoard with pellets
	 * @param gameBoard 2d array that contains the wall locations
	 * @return gameboard, which is now filled with pellet locations
	 */
	public int[][] generatePellet(int[][] gameBoard) {
		for (int row = 0; row < gameBoard.length; row++) {
			for (int column = 0; column < gameBoard[0].length; column++ ) {
				if (gameBoard[row][column] != 1)
					gameBoard[row][column] = 2;
			}
		}
		return gameBoard;
	}

	/** 
	 * Removes a single pellet from the location ArrayList
	 * @param p the point that will be removed from the location ArrayList
	 */
	public void removePellet(Point p) {
		this.location.remove(p);
	}

	
	







}
