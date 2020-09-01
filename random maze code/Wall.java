/**
 * The Wall class contains the variables and methods of wall
 */

import java.awt.Point;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Wall {

	// instance variables
	private int[][] walls;
	// this is the maze used in the game
	private int[][] maze = new int[33][33];
					 

	/**
	 * This is the constructor for the wall class
	 * It sets the walls to the level1 array, which is a maze
	 */
	public Wall() {
		generateMaze();		
		this.walls = maze;

	}


	/**
	 * Getter for the point's location
	 * @return the array containing the maze
	 */
	public int[][] getWalls() {
		return this.walls;
	}



//////////////////////////////////////////////////
	public void generateMaze() {
	// first generate 32x32 maze filled with 1 (all walls)
		for (int i = 0; i <= 32; i++) {
			for (int j = 0; j <= 32; j++) {
				maze[i][j] = 1;
			}
		}
		
		maze[1][1] = 0;
		recursion(1, 1);
	}

	public void recursion(int x, int y) {
		ArrayList<Character> possibleMoves = new ArrayList<Character>(Arrays.asList('L', 'R', 'U', 'D'));
		Collections.shuffle(possibleMoves);
		
		for (int i = 0; i < possibleMoves.size(); i++) {
			
			switch(possibleMoves.get(i)) {
				case 'U':
					if (!( (y <= 2) || (maze[y-2][x] == 0) )) {
						maze[y-1][x] = 0;
						maze[y-2][x] = 0;
						recursion(x, y-2);
					}
					break;
				case 'D':
					if (!( (y >= 30) || (maze[y+2][x] == 0) )) {
						maze[y+1][x] = 0;
						maze[y+2][x] = 0;
						recursion(x, y+2);
					}
					break;
				case 'L':
					if (!( (x <= 2) || (maze[y][x-2] == 0) )) {
						maze[y][x-1] = 0;
						maze[y][x-2] = 0;
						recursion(x-2, y);
					
					}
					break;
				case 'R':
                                        if (!( (x >= 30) || (maze[y][x+2] == 0) )) {
                                                maze[y][x+1] = 0;
                                                maze[y][x+2] = 0;
                                                recursion(x+2, y);

                                        }
                                        break;
	
			}
		
		
		
		
		}		
	
	
		
	}










}
