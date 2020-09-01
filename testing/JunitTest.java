/**
 * This is a JUnit test that completely tests all the methods of the PacManMazeTextLogic class
 */

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Point;

public class JunitTest {

	
	/**
	 * tests if all the instance variables were initialized after invoking the initialize() method
	 */
	@Test
	public void test_allInstanceVariablesInitialized() {
		PacManMazeTextLogic main = new PacManMazeTextLogic();
		main.initialize();
		assertNotNull("the avatar instance variable should not be null", main.getAvatar());
		assertNotNull("the wall instance variable should not be null", main.getWall());
		assertNotNull("the gameBoard instance variable should not be null", main.getGameBoard());
		assertNotNull("the pellet instance variable should not be null", main.getPellet());
	
	}
	
	
	/**
	 * tests that the initial location of pacman is at (1, 1)
	 */
	@Test
	public void test_initializationLocation() {
		PacManMazeTextLogic main = new PacManMazeTextLogic();
		main.initialize();
		Avatar avatar = main.getAvatar();
		Point expectedLocation = new Point(1, 1);
		Point actualLocation = avatar.getLocation();
		assertTrue("at the beginning, avatar location should be (1, 1)", expectedLocation.equals(actualLocation));	
	}

	
	/** 
	 * tests that the avatar can move left, right, up, down
	 */
	@Test
	public void test_avatarMove() {
		PacManMazeTextLogic main = new PacManMazeTextLogic();
                main.initialize();

		Point expectedLocation;
		Point actualLocation;

		// check if avatar can move right
		main.getInputAndMove("d");
		expectedLocation = new Point(2, 1);
		actualLocation = main.getAvatar().getLocation();
		assertEquals("if d is pressed, avatar should move from (1, 1) to (2, 1)" ,expectedLocation, actualLocation);

		// check if avatar can move left
                main.getInputAndMove("a");
                expectedLocation = new Point(1, 1);
                actualLocation = main.getAvatar().getLocation();
                assertEquals("if a is pressed, avatar should move from (2, 1) to (1, 1)" ,expectedLocation, actualLocation);

		// check if avatar can move down
                main.getInputAndMove("s");
                expectedLocation = new Point(1, 2);
                actualLocation = main.getAvatar().getLocation();
                assertEquals("If d is pressed, avatar should move from (1, 1) to (1, 2)" ,expectedLocation, actualLocation);
		
		// check if avatar can move up
		main.getInputAndMove("w");
                expectedLocation = new Point(1, 1);
                actualLocation = main.getAvatar().getLocation();
                assertEquals("If d is pressed, avatar should move from (1, 2) to (1, 1)" ,expectedLocation, actualLocation);

	}

	/**
	 * test if avatar does not move into wall
	 */
	@Test
	public void test_cannotMoveIntoWall() {
		PacManMazeTextLogic main = new PacManMazeTextLogic();
                main.initialize();
                main.getInputAndMove("a");

		Point expectedLocation = new Point(1, 1);
		Point actualLocation = main.getAvatar().getLocation();

		assertEquals("the avatar's move is blocked by a wall, so it's location should be the same", expectedLocation, actualLocation);
	
	} 

	/**
	 * tests that the avatar does not move when invalid input is entered
	 */
	@Test
	public void test_invalidInput() {
		PacManMazeTextLogic main = new PacManMazeTextLogic();
                main.initialize();
                
		main.getInputAndMove("m");
		Point expectedLocation = new Point(1, 1);
		Point actualLocation = main.getAvatar().getLocation();
		assertEquals("if the user enters 'm', that is an invalid input and the avatar should not move", expectedLocation, actualLocation);

		main.getInputAndMove("8");
		expectedLocation = new Point(1, 1);
		actualLocation = main.getAvatar().getLocation();
		assertEquals("if the user enters '8', that is an invalid input and the avatar should not move", expectedLocation, actualLocation);
	
		main.getInputAndMove("@");
		expectedLocation = new Point(1, 1);
		actualLocation = main.getAvatar().getLocation();
		assertEquals("if the user enters '@', that is an invalid input and the avatar should not move", expectedLocation, actualLocation);
	
		main.getInputAndMove("wa");
		expectedLocation = new Point(1, 1);
		actualLocation = main.getAvatar().getLocation();
		assertEquals("if the user enters 'wa', that is an invalid input and the avatar should not move", expectedLocation, actualLocation);
	
	
	}

	/**
	 * test if the avatar's initial score is 0
	 */
	@Test
	public void test_initialPointIsZero() {
		PacManMazeTextLogic main = new PacManMazeTextLogic();
                main.initialize();

		int expectedScore = 0;
		int actualScore = main.getAvatar().getScore();

		assertEquals("The avatar initial score should be 0", expectedScore, actualScore);
	}

	/**
	 * tests if the avatar can collect score
	 */
	@Test
	public void test_PointCollection() {
		PacManMazeTextLogic main = new PacManMazeTextLogic();
                main.initialize();
		main.getInputAndMove("d");
		main.updateScore();

		int expectedScore = 10;
                int actualScore = main.getAvatar().getScore();
		assertEquals("if the avatar's first move is to left, it should have 10 points", expectedScore, actualScore);

		main.getInputAndMove("d");
		main.updateScore();
		expectedScore = 20;
		actualScore = main.getAvatar().getScore();
		assertEquals("if the avatar's first moves left twice, it should have 20 points", expectedScore, actualScore);

		main.getInputAndMove("a");
		main.updateScore();
		expectedScore = 20;
		actualScore = main.getAvatar().getScore();
		assertEquals("if the avatar's first moves left twice, and moves back right once, it should have 20 points", expectedScore, actualScore);
	}

	/** 
	 * tests if the ghost is able to move
	 */
	@Test
	public void test_ghostMove() {
		PacManMazeTextLogic main = new PacManMazeTextLogic();
                main.initialize();
                
		Point initialGhostLocation = main.getGhost().getLocation();
		System.out.println(initialGhostLocation);
		main.getInputAndMove("a");
		Point finalGhostLocation = main.getGhost().getLocation();
		System.out.println(finalGhostLocation);
		System.out.println(initialGhostLocation);

		assertFalse("the ghost location should change everytime the avatar moves", initialGhostLocation.equals(finalGhostLocation));
	}




















}
