
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             KeyWord.java
// Semester:         CS367 Fall 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

public class KeyWord implements Comparable<KeyWord>, Prioritizable
{
	private String word;
	private int occurences;

	/**
	 * Constructs a KeyWord
	 * 
	 * @param word
	 *            name of the word
	 */
	public KeyWord(String word)
	{
		if (word == null || word.isEmpty())
		{
			throw new IllegalArgumentException();
		} else
		{
			this.word = word.toLowerCase();
			occurences = 0;
		}
	}

	/**
	 * Gets the word
	 * 
	 * @return the word
	 */
	public String getWord()
	{
		return word;
	}

	/**
	 * Gets the occurences
	 * 
	 * @return number of occurences
	 */
	public int getOccurences()
	{
		return occurences;
	}

	/**
	 * Increments the number of occurences
	 */
	public void increment()
	{
		occurences++;
	}

	/**
	 * Gets the priority of the KeyWord
	 */
	public int getPriority()
	{
		return occurences;
	}

	/**
	 * Allows for KeyWords to be compared
	 */
	public int compareTo(KeyWord arg0)
	{
		String lWord = word.toLowerCase();
		String lArg0 = arg0.getWord().toLowerCase();

		return (lWord.compareTo(lArg0));
	}

	/**
	 * Checks if one KeyWord is equal to the others
	 */
	public boolean equals(Object other)
	{
		if (other != null && other instanceof KeyWord)
		{
			if (this.compareTo((KeyWord) other) == 0)
			{
				return true;
			}
		}
		return false;
	}

}
