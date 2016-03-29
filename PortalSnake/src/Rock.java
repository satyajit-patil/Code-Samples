
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Rock.java
// Semester:         302 Fall 2015
//
// Author:           Satyajit Patil (spatil5@wisc.edu)
// CS Login:         jit
// Lecturer's Name:  Debra Deppler
// Lab Section:      322
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * The Rock class represents a rock in the game.
 * 
 * The Game class instantiates this class once for each rock present when a new
 * level is loaded.
 */
public class Rock
{
	// Create private field to hold the GraphicObject associated with this rock
	private GraphicObject rock;

	/**
	 * TODO: Implement this.
	 * 
	 * @param x
	 *            the x position of the rock
	 * @param y
	 *            the y position of the rock
	 */
	public Rock(float x, float y)
	{
		// Initialize this rock's associated GraphicObject with type "ROCK" at
		// this rock's x and y coordinates
		rock = new GraphicObject("ROCK", x, y);
	}

	/**
	 * Checks if this rock is colliding with the specified snake.
	 * 
	 * If the GraphicObject associated with this rock is colliding with the head
	 * of the GraphicObject associated with the head of the snake, kills the
	 * snake.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param snake
	 *            snake to check for collisions with
	 */
	public void killSnakeIfColliding(Snake snake)
	{
		// If this rock is colliding with the snake's head's GraphicObject, the
		// snake dies
		if (rock.isCollidingWith(snake.getGraphicObject()))
		{
			snake.die();
		}
	}
}