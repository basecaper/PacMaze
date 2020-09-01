import java.awt.Point;

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
	
	// constructor that takes win location as parameter
	public GameWin(Point endPoint) {
		winLocation = new Point(endPoint);
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


