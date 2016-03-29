
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Snake.java
// Semester:         302 Fall 2015
//
// Author:           Satyajit Patil (spatil5@wisc.edu)
// CS Login:         jit
// Lecturer's Name:  Debra Deppler
// Lab Section:      322
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList; //imports the 'ArrayList' class

/**
 * The Snake class represents the player-controlled snake.
 *
 * The Game class instantiates this class exactly once, when a new level is
 * loaded.
 */
public class Snake
{
	private GraphicObject head;// private variable to hold the GraphicObject
								// associated with the snake's head
	ArrayList<GraphicObject> bodySegments;// private variable to hold an
											// ArrayList of GraphicObjects
											// associated with the snake's body

	/**
	 * Initializes a new Snake object at the specified (x,y) position.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param x
	 *            the initial x position of the snake
	 * @param y
	 *            the initial y position of the snake
	 */
	public Snake(float x, float y)
	{
		// Initialize new ArrayList to hold body segments
		bodySegments = new ArrayList<GraphicObject>();

		// Initialize the head
		head = new GraphicObject("HEAD", x, y);

		// Set the speed of the head to '2'
		head.setSpeed(2);

		// Set the direction of the head to '90'
		head.setDirection(90);

		// Add the head to the list(array) of body segments
		bodySegments.add(head);

		// Add four body segments (grow the snake four times)
		grow();
		grow();
		grow();
		grow();
	}

	/**
	 * Returns the GraphicObject associated with the head of this snake.
	 * 
	 * TODO: Implement this.
	 * 
	 * @return the GraphicObject associated with the head of this snake
	 */
	public GraphicObject getGraphicObject()
	{
		return head;// returns the head of the snake
	}

	/**
	 * Grows the snake by one body segment.
	 * 
	 * TODO: Implement this.
	 */
	public void grow()
	{
		// Creates a new GraphicObject with type "BODY"
		GraphicObject body = new GraphicObject("BODY", 0, 0);

		// Finds the last body segment in the list of body segments
		GraphicObject lastBody = bodySegments.get(bodySegments.size() - 1);

		// Set the leader of the new body segment to be the last body segment
		body.setLeader(lastBody);

		// Add the new body segment to the end of the list(array) of body
		// segments
		bodySegments.add(body);
	}

	/**
	 * Reads keyboard input and changes the snake's direction as necessary.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param controlType
	 *            - 1: classic, 2: analog, 3: slither
	 */
	public void updateMoveDirection(int controlType)
	{

		// IF controlType is one
		if (controlType == 1)
		{
			// implementation for Classic Control
			if (Engine.isKeyPressed("LEFT"))
			{
				head.setDirection(head.getDirection() + 90);
			}

			if (Engine.isKeyPressed("RIGHT"))
			{
				head.setDirection(head.getDirection() - 90);
			}

		}

		// IF controlType is two
		if (controlType == 2)
		{
			// implementation for Analog Controls
			if (Engine.isKeyHeld("LEFT"))
			{
				head.setDirection(head.getDirection() + 6);
			}

			if (Engine.isKeyHeld("RIGHT"))
			{
				head.setDirection(head.getDirection() - 6);
			}

		}

		// IF controlType is three
		if (controlType == 3)
		{
			// implementation for Slither Controls
			if (Engine.isKeyHeld("SPACE"))
			{
				head.setDirection(head.getDirection() + 6);
			}

			else
			{
				head.setDirection(head.getDirection() - 6);
			}

		}
	}

	/**
	 * Kills the snake if the head is colliding with any of the body segments.
	 * 
	 * TODO: Implement this.
	 */
	public void dieIfCollidingWithOwnBody()
	{
		// For each game object in the body
		for (GraphicObject object : bodySegments)
		{
			// if the head is colliding with this object(segment)
			if (head.isCollidingWith(object))
			{
				// the snake will die
				die();
			}
		}
	}

	/**
	 * Kills the snake.
	 * 
	 * TODO: Implement this.
	 */
	public void die()
	{
		// Set the head's type to "DEAD"
		head.setType("DEAD");

		// For each GraphicObject in the snake's body, set its type to "DEAD"
		for (GraphicObject object : bodySegments)
		{
			object.setType("DEAD");
		}
	}

	/**
	 * Returns true if the snake is dead.
	 * 
	 * TODO: Implement this.
	 * 
	 * @return true if the snake is dead, false otherwise
	 */
	public boolean isDead()
	{
		if (head.getType() == "DEAD")
		{
			return true;
		} else
		{
			return false;
		}
	}
}
