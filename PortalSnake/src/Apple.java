
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Apple.java
// Semester:         302 Fall 2015
//
// Author:           Satyajit Patil (spatil5@wisc.edu)
// CS Login:         jit
// Lecturer's Name:  Debra Deppler
// Lab Section:      322
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * The Apple class represents an apple in the game.
 * 
 * The Game class instantiates this class once for each apple present when a new
 * level is loaded.
 */
public class Apple
{
	// Creates private field to hold the GraphicObject associated with this
	// apple
	private GraphicObject apple;

	/**
	 * Initializes a new Apple object.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param x
	 *            the x position of the apple
	 * @param y
	 *            the y position of the apple
	 */
	public Apple(float x, float y)
	{
		// Initializes this apple's associated GraphicObject with type "APPLE"
		// at this apple's x and y coordinates
		apple = new GraphicObject("APPLE", x, y);
	}

	/**
	 * Checks if this apple is colliding with the specified snake.
	 * 
	 * If the GraphicObject associated with this apple is colliding with the
	 * GraphicObject associated with the specified snake's head, grows the
	 * snake, destroys the GraphicObject associated with this apple (causing it
	 * to disappear from the screen), and returns true. Otherwise, returns
	 * false.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param snake
	 *            snake to check for collisions with
	 * @return true after eating an apple, otherwise false
	 */
	public boolean getEatenBySnakeIfColliding(Snake snake)
	{
		// IF this apple is colliding with the snake's head's GraphicObject
		if (apple.isCollidingWith(snake.getGraphicObject()))
		{
			// grow the snake once and destroy this apple's GraphicObject
			snake.grow();
			apple.destroy();

			// then, return true
			return true;
		}
		// ELSE return false
		else
		{
			return false;
		}
	}
}
