
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            PortalSnake
// Files:            (list of source files)
// Semester:         302 Fall 2015
//
// Author:           Satyajit Patil
// Email:            spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Debra Deppler
// Lab Section:      322
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;//imports all classes within java.util

/**
 * The Game class represents a running instance of the PortalSnake game. It
 * keeps track of the Snake object, lists of Apple, Rock, and PortalPair
 * objects, the current score, and whether the player has won.
 * 
 * The game engine (which we've written for you) will create a new instance of
 * this class when the player chooses a level to play.
 * 
 * At each iteration of the game loop, the game engine calls the update() method
 * in the Game class. The update() method tells each of the objects in the game
 * to update itself based on the rules of the game. It then checks if the game
 * is over or not.
 */
public class Game
{
	// Store the instances of the game objects (snake, apples, rocks, portals)
	// that you create for your game in these member variables:
	private Snake snake;
	private ArrayList<Apple> apples;
	private ArrayList<Rock> rocks;
	private ArrayList<PortalPair> portals;
	Random rng = new Random(); // random generator used to randomly locate
								// objects

	int controlRules; // instance variable to track controlType
	int score; // instance variable to track score (ie number of apples eaten by
				// the snake)
	boolean win; // instance variable to track whether or not the game has been
					// won

	/**
	 * 
	 * TODO: Have students implement this
	 * 
	 * @param level
	 *            - "RANDOM" or descriptions of the object to load
	 * @param controlType
	 *            - 1: classic, 2: analog, 3: slither
	 */
	public Game(String level, int controlType)
	{

		controlRules = controlType; // controlRules is initialized to
									// controlType so it can be used throughout
									// the class

		// IF level contains "RANDOM", createRandomLevel();
		if (level.contains("RANDOM"))
		{
			createRandomLevel();
		}
		// ELSE load the objects described in the string level
		else
		{
			loadLevel(level);
		}
	}

	/**
	 * TODO: Have students implement this. create a new level with randomly
	 * positioned: Snake(1), Rocks(20), Apples(8), and PortalPairs(3)
	 */
	public void createRandomLevel()
	{
		// creates a snake positioned at the center
		{
			float x = Engine.getWidth() / 2;
			float y = Engine.getHeight() / 2;
			snake = new Snake(x, y);
		}

		// creates 20 randomly positioned rocks
		rocks = new ArrayList<Rock>();
		for (int i = 0; i < 20; i++)
		{
			float x = rng.nextFloat() * Engine.getWidth();
			float y = rng.nextFloat() * Engine.getHeight();
			Rock rock = new Rock(x, y);

			rocks.add(rock);
		}

		// creates 8 randomly positioned apples
		apples = new ArrayList<Apple>();
		for (int i = 0; i < 8; i++)
		{
			float x = rng.nextFloat() * Engine.getWidth();
			float y = rng.nextFloat() * Engine.getHeight();
			Apple apple = new Apple(x, y);

			apples.add(apple);
		}

		// creates 3 randomly positioned portal pairs
		portals = new ArrayList<PortalPair>();
		for (int i = 0; i < 3; i++)
		{
			// names portal pairs in capitalized alphabetical order
			char alphabet = (char) (65 + i);
			String name = "" + (char) alphabet;

			float portalBlueX = rng.nextFloat() * Engine.getWidth();
			float portalBlueY = rng.nextFloat() * Engine.getHeight();
			float portalOrangeX = rng.nextFloat() * Engine.getWidth();
			float portalOrangeY = rng.nextFloat() * Engine.getHeight();
			PortalPair portalPair = new PortalPair(name, portalBlueX,
					portalBlueY, portalOrangeX, portalOrangeY);

			portals.add(portalPair);
		}
	}

	/**
	 * Loads a level from a String description.
	 * 
	 * Initializes all of the class private fields which hold the Snake object
	 * and the lists of Apple, Rock, and PortalPair objects from the provided
	 * String which contains
	 * 
	 * TODO: Implement this method
	 * 
	 * @param level
	 *            - a string containing the names and locations of objects
	 */
	public void loadLevel(String level)
	{
		// Initialize Rock, Apple, and PortalPair ArrayLists
		rocks = new ArrayList<Rock>();
		apples = new ArrayList<Apple>();
		portals = new ArrayList<PortalPair>();

		// Create a new scanner to read the level description
		Scanner scnr = new Scanner(level);

		// WHILE Loop through lines in the level description
		while (scnr.hasNext())
		{
			scnr.useDelimiter("\n");// sets the delimiter to "\n" for String
									// level.
									// "\n" is used to tokenize String level.
									// String level is split into multiple
									// tokens.
			// Gets the next line
			String line = scnr.next();

			// Splits the line into tokens to determine(isolate) object type and
			// coordinates
			String[] tokens = line.split(",");

			// Determines the type of object to add to the level
			// IF it's a snake, create a new snake at the x and y
			// coordinates specified by the second and third tokens
			if (tokens[0].equals("Snake"))
			{
				String x = tokens[1];
				float x1 = Float.parseFloat(x);
				String y = tokens[2];
				float y1 = Float.parseFloat(y);
				snake = new Snake(x1, y1);
			}

			// IF it's an apple, create a new apple at the x and y
			// coordinates specified by the second and third tokens, and add
			// it to the list of apples
			if (tokens[0].equals("Apple"))
			{
				String x = tokens[1];
				float x1 = Float.parseFloat(x);
				String y = tokens[2];
				float y1 = Float.parseFloat(y);
				Apple apple = new Apple(x1, y1);

				apples.add(apple);
			}

			// IF it's a rock, create a new rock at the x and y coordinates
			// specified by the second and third tokens and add it to the
			// list of rocks
			if (tokens[0].equals("Rock"))
			{
				String x = tokens[1];
				float x1 = Float.parseFloat(x);
				String y = tokens[2];
				float y1 = Float.parseFloat(y);
				Rock rock = new Rock(x1, y1);

				rocks.add(rock);
			}

			// IF it's a portal pair, create a new PortalPair with the
			// name equal to the second token, with the first portal at the
			// x and y coordinates specified by the third and fourth
			// tokens, and the second portal at the x and y coordinates
			// specified by the fifth and sixth tokens
			if (tokens[0].equals("PortalPair"))
			{
				String name = tokens[1].trim();

				String blueX = tokens[2];
				float portalBlueX = Float.parseFloat(blueX);
				String blueY = tokens[3];
				float portalBlueY = Float.parseFloat(blueY);

				String orangeX = tokens[4];
				float portalOrangeX = Float.parseFloat(orangeX);
				String orangeY = tokens[5];
				float portalOrangeY = Float.parseFloat(orangeY);

				PortalPair portalPair = new PortalPair(name, portalBlueX,
						portalBlueY, portalOrangeX, portalOrangeY);

				portals.add(portalPair);
			}

			// ELSE ignore the line
			else
			{
				scnr.nextLine();
			}
		}

		// Close the scanner
		scnr.close();
	}

	/**
	 * Updates the game objects.
	 * 
	 * Goes through each of the objects--the snake, rocks, apples, and portals--
	 * and tells them to behave according to the rules of the game. This method
	 * returns true if the game should continue, or false if the game is over.
	 * 
	 * TODO: Implement this
	 * 
	 * @return - false if the game is over, otherwise true
	 */
	public boolean update()
	{
		// Tells the snake to update itself
		snake.updateMoveDirection(controlRules);

		// Tells the snake to die if it's colliding with itself
		snake.dieIfCollidingWithOwnBody();

		// FOR each rock, tell the rock to kill the snake if the two are
		// colliding
		for (Rock object : rocks)
		{
			// For each game object in the body...
			object.killSnakeIfColliding(snake);
		}

		// FOR each apple, tell the apple to be eaten by the snake if the two
		// are colliding, and if so update the score
		for (Apple object : apples)
		{
			object.getEatenBySnakeIfColliding(snake);
			getScore();
		}

		// FOR each portal pair, tell the pair to teleport the snake if the two
		// are colliding
		for (PortalPair object : portals)
		{
			object.teleportSnakeIfColliding(snake);
		}

		// Check for win/loss
		playerHasWon();

		// IF the score is equal to the number of apples, make sure
		// playerHasWon() will return true and then return false
		if (score == apples.size())
		{
			playerHasWon();
			return false;
		}

		// ELSE IF the snake is dead, make sure playerHasWon() will return
		// false and then return false
		else if (snake.isDead() == true)
		{
			playerHasWon();
			return false;
		}

		// If the game isn't over, return true
		return true;
	}

	/**
	 * Returns true if the player has won
	 * 
	 * TODO: Implement this
	 * 
	 * @return true when the player has won, and false when they have lost or
	 *         the game is not over
	 */
	public boolean playerHasWon()
	{
		// IF the score equals to the number of apples, player has won
		if (score == apples.size())
		{
			win = true;
		}

		// ELSE, player has not won
		else
		{
			win = false;
		}
		return win;
	}

	/**
	 * Returns the player's score.
	 * 
	 * TODO: Implement this.
	 * 
	 * @return the current score (number of apples eaten)
	 */
	public int getScore()
	{
		score = (snake.bodySegments).size() - 5;// score equals the number of
												// times the snake has grew
		return score;
	}

	/**
	 * There is nothing left to implement in this method, it simply calls
	 * Engine.startEngineAndGame(), which in turn starts the Engine and creates
	 * an instance of this Game class. The engine will then repeatedly call the
	 * update() method on this Game until it returns false.
	 * 
	 * If you want to turn off the logging you can change the parameter being
	 * passed to startEngineAndGame to false.
	 * 
	 * @param args
	 *            - command line arguments
	 */
	public static void main(String[] args)
	{
		Application.startEngineAndGame(true);
	}
}
