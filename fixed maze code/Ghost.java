import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


/**
 * This class contains all the instance variables and methods of ghost objects
 */

public class Ghost {
	// 2 instance variables: location and previous move
	private Point location;
	private String previousMove;


	/**
	 * ghost constructor
	 */
	public Ghost(int[][] wallList) {
		this.setSpawnLocation(wallList);
		previousMove = " ";
	}


	/**
	 * The getter method of the x coordinate of location instance variable
	 * @return the the x coordinate of location instance variable
	 */
	public double getX() {
                return location.getX();
        }

	/**
         * The getter method of the y coordinate of location instance variable
         * @return the the y coordinate of location instance variable
         */
	public double getY() {
                return location.getY();
        }


	/**
	 * The getter method for the location instance variable of the ghost in point form
	 * @return the location of the ghost in point form
	 */
	public Point getLocation() {
		return new Point(this.location);
	}

	

	/**
	 * This is essentially the ghost ai
	 * The method chooses which direction to move in a smart way
	 * If the ghost is in the players line of sight, it return the direction towards the player
	 * If the ghost is not in the player's line of sight, it will choose a pseudo-random direction
	 * @param wallList is an array that contains all the walls and pellets
	 * @param avatar is the avatar object
	 * @return the direction that the ghost should move towards (w, a, s, d)
	 */
	public String pickMove(int[][] wallList, Avatar avatar) {
		
		/////// first, chase the avatar if it is in the ghost's line of sight	//////	
		
		int avatarX = (int) avatar.getX();
		int avatarY = (int) avatar.getY();

		int ghostX = location.x;
		int ghostY = location.y;
	
		// check if avatar is directly to the left of the ghost which no walls in between
		// if that is true, then return "a" 
		
		if ( (avatarY == ghostY) && (avatarX < ghostX) ) {
			// check if there are no walls between avatar and ghost
			boolean noWalls = true;
			for (int i = avatarX; i < ghostX; i++) {
				if (wallList[avatarY][i] == 1) {
					noWalls = false;
				}
			}
			if (noWalls == true) {
				return "a";
			}
		}

		
		// check if avatar is directly to the right of the ghost with no walls in between
		// if that is true, then return "d"
		if ( (avatarY == ghostY) && (avatarX > ghostX) ) {
			//check if there are no walls in between
			boolean noWalls = true;
			for (int i = ghostX; i < avatarX; i++) {
				if (wallList[avatarY][i] == 1) {
					noWalls = false;
				}
			}
			if (noWalls == true) {
				return "d";
			}
		}		
	
			
		// check if avatar is directly on top of the ghost with no walls in between
		// if that is true, return "w"

		if ( (avatarX == ghostX) && (avatarY < ghostY) ) {
			// check if there are no walls in between
			boolean noWalls = true;
			for (int i = avatarY; i < ghostY; i++) {
				if (wallList[i][avatarX] == 1) {
					noWalls = false;
				}
			}
			if (noWalls == true) {
				return "w";
			}
		}


		// check if avatar is directly below the ghost with no walls in between
		// if that is true, return "d"

		if ( (avatarX == ghostX) && (avatarY > ghostY) ) {
			// check if there are no walls in between 
			boolean noWalls = true;
			for (int i = ghostY; i < ghostX; i++) {
				if (wallList[i][avatarX] == 1) {
					noWalls = false;
				}
			}
			if (noWalls == true) {
				return "s";
			}
		
		}



		////// if avatar is not in line of sight, then pick random move //////

		// first clreate a list that contains all potential moves
		ArrayList<String> potentialMoves = new ArrayList<String>();
		potentialMoves.add("w");
		potentialMoves.add("s");
		potentialMoves.add("a");
		potentialMoves.add("d");

		//if a wall is preventing a move, remove that move from potentialMoves
		if (wallList[location.y - 1][location.x] == 1) {
			potentialMoves.remove("w");
		}
		if (wallList[location.y + 1][location.x] == 1) {
                        potentialMoves.remove("s");
                }
		if (wallList[location.y][location.x - 1] == 1) {
			potentialMoves.remove("a");
		}
		if (wallList[location.y][location.x + 1] == 1) {
			potentialMoves.remove("d");
		}

		
		// see if the only option remaining is to do the previous move
		if ((potentialMoves.size() == 1) && (potentialMoves.get(0) == this.previousMove)) {
			return previousMove;
		}

		//
		else {
			potentialMoves.remove(previousMove);
			Random r = new Random();
			int randomIndex = r.nextInt(potentialMoves.size());
			return potentialMoves.get(randomIndex);
		}


	}


	/**
	 * Moves the ghost location according to the direction specified
	 * This method is used in conjunction with chooseMove method
	 * @param direction is the direction specified
	 */
	public void move(String direction) {
		if (direction == "w") {
			location.translate(0, -1);
			this.previousMove = "s";	
		}
		if (direction == "s") {
                        location.translate(0, 1);
                        this.previousMove = "w";
                }
		if (direction == "a") {
                        location.translate(-1, 0);
                        this.previousMove = "d";
                }
		if (direction == "d") {
                        location.translate(1, 0);
                        this.previousMove = "a";
                }
	}

	/**
	 * checks if the player and ghost location overlaps
	 * if overlap occurs, the game ends
	 * @return collision a boolean that says whether collision has occured or not
	 */
	public boolean overlapsWith(Avatar player) {
                boolean collision = false;

                if (location.getX() == player.getX() && location.getY() == player.getY()) {
                        collision = true;
                }
                return collision;
        }

	
	/** 
	 * Sets the initial location to a random, valid location
	 */
	public void setSpawnLocation(int[][] wallList) {
		ArrayList<Point> possibleSpawnLocations = new ArrayList<Point>();		
		
		for (int y = 10; y < wallList.length; y++) {
			for (int x = 0; x < wallList[y].length; x++) {
				if (wallList[y][x] != 1) {
					possibleSpawnLocations.add(new Point(x, y));
				}
			}
		}

		// now we will choose a random spawn location from possibleSpawnLocations
		Random r = new Random();
                int randomIndex = r.nextInt(possibleSpawnLocations.size());
                this.location = possibleSpawnLocations.get(randomIndex);


	}




}
