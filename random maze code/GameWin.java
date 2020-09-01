import java.awt.Point;
import java.util.Random;

/**
 * This class contains the gameWin instance variables and methods of the game
 */

public class GameWin {
	// instance variables
	private Point winLocation;

	// deafault constructor
	public GameWin() {
		winLocation = new Point(29, 2);
	}
	
	
	// constructor that takes the maze as parameter, and randomly selects gameWin location
	public GameWin(int[][] maze) {
		// randomly generages the win tile at any location, then sets that location to 0 (no wall) in case there is a wall there
		// note than this can generate the win tile at any corner in which case the game cannot be won
		Random r = new Random();
		int x = r.nextInt(maze[0].length);
		int y = r.nextInt(maze.length);						
		
		maze[y][x] = 0;
		winLocation = new Point(x, y);	
	}
	
	/**
	 * checks if avatar is at winLocation
	 * @return win a boolan which says if avatar is at winLocation or not
	 */
	public boolean isAtEndPoint(Avatar player) {
		boolean win = false;
		
		if (winLocation.getX() == player.getX() && winLocation.getY() == player.getY()) {
			win = true;
			//System.out.println("WINNER");
			//System.exit(0);
		}
		return win;
	}
	
	/**
	 * getter method for the x-coordinate of win location
	 * @return the x-coordinate of win location
	 */
	public double getX() {
                return winLocation.getX();
	}
	
	/**
	 * getter method for the y-coordinate of the win location
	 * @return the y-coordinate of win location
	 */
	public double getY() {
                return winLocation.getY();
	}
}


