import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Test{
	private int[][] maze = new int[32][32];

		
	public void generateMaze() {
		// first generate 32x32 maze filled with 1 (all walls)
		for (int i = 0; i <= 31; i++) {
			for (int j = 0; j <= 31; j++) {
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
					if (!( (y >= 29) || (maze[y+2][x] == 0) )) {
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
                                        if (!( (x >= 29) || (maze[y][x+2] == 0) )) {
                                                maze[y][x+1] = 0;
                                                maze[y][x+2] = 0;
                                                recursion(x+2, y);

                                        }
                                        break;
	
			}
		
		}		
	
	}
	// to test if the code works
	public static void main(String[] args) {
		Test t = new Test();
		t.generateMaze();
		System.out.println(Arrays.deepToString(t.maze));
	
	}









}
