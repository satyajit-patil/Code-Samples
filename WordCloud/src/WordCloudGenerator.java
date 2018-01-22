
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Programming Assignment 2: Word Cloud
// Files:            (list of source files)
// Semester:         CS367 Fall 2016
//
// Author:           Satyajit Patil
// Email:            spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
import java.io.*;

public class WordCloudGenerator
{
	/**
	 * The main method generates a word cloud as described in the program
	 * write-up. You will need to add to the code given here.
	 * 
	 * @param args
	 *            the command-line arguments that determine where input and
	 *            output is done:
	 *            <ul>
	 *            <li>args[0] is the name of the input file</li>
	 *            <li>args[1] is the name of the output file</li>
	 *            <li>args[2] is the name of the file containing the words to
	 *            ignore when generating the word cloud</li>
	 *            <li>args[3] is the maximum number of words to include in the
	 *            word cloud</li>
	 *            </ul>
	 * @throws FileNotFoundException
	 * @throws DuplicateException
	 */
	public static void main(String[] args)
			throws FileNotFoundException, DuplicateException
	{
		Scanner in = null; // for input from text file
		PrintStream out = null; // for output to html file
		Scanner inIgnore = null; // for input from ignore file
		DictionaryADT<KeyWord> dictionary = new BSTDictionary<KeyWord>();

		// Check the command-line arguments and set up the input and output
		if (args.length != 4)
		{
			System.out.println(
					"Four arguments required: inputFileName outputFileName ignoreFileName maxWords");
			System.exit(1);
		}
		if (args.length == 4)
		{
			File inputFile = new File(args[0]);
			if (!inputFile.exists() || !inputFile.canRead())
			{
				System.err.println("Error: cannot access file " + args[0]);
				System.exit(1);
			}
			in = new Scanner(inputFile);

			File ignoreFile = new File(args[2]);
			if (!ignoreFile.exists() || !ignoreFile.canRead())
			{
				System.err.println("Error: cannot access file " + args[2]);
				System.exit(1);
			}
			inIgnore = new Scanner(ignoreFile);

			int maxWords = Integer.parseInt(args[3]);
			if (maxWords <= 0)
			{
				System.err
						.println("Error: maxWords must be a positive integer");
				System.exit(1);
			}

			File outFile = new File(args[1]);
			out = new PrintStream(outFile);
		}

		// Create the dictionary of words to ignore
		DictionaryADT<String> ignore = new BSTDictionary<String>();
		while (inIgnore.hasNext())
		{
			try
			{
				ignore.insert(inIgnore.next().toLowerCase());
			} catch (DuplicateException e)
			{
				// if there is a duplicate, ignore it
			}
		}

		// Process the input file line by line
		while (in.hasNext())
		{
			String line = in.nextLine();
			List<String> words = parseLine(line);

			for (String word : words)
			{
				word = word.toLowerCase();
				KeyWord dicWord = new KeyWord(word);
				if (ignore.lookup(word) == null)
				{
					try
					{
						dictionary.insert(dicWord);
					} catch (DuplicateException e)
					{
						Iterator<KeyWord> itr = dictionary.iterator();
						while (itr.hasNext())
						{
							KeyWord next = itr.next();
							if (next.getWord().equals(word))
							{
								dictionary.delete(next);
								next.increment();
								dictionary.insert(next);
							}
						}
					}
				}
			}
		} // end while

		// # of keys
		System.out.println("# keys: " + dictionary.size());

		// average path length
		System.out.println("avg path length: "
				+ dictionary.totalPathLength() / dictionary.size());

		// linear average path length
		System.out.println("linear avg path: " + (1 + dictionary.size()) / 2);

		// Put the dictionary into a priority queue
		ArrayHeap<KeyWord> pq = new ArrayHeap<KeyWord>(dictionary.size());
		Iterator<KeyWord> itr = dictionary.iterator();
		while (itr.hasNext())
		{
			KeyWord word = (KeyWord) itr.next();
			pq.insert(word);
		}

		// Use the priority queue to create a list of KeyWords of
		// the appropriate length
		DictionaryADT<KeyWord> trimDict = new BSTDictionary<KeyWord>();
		int maxWords = Integer.parseInt(args[3]);
		if (maxWords > pq.size())
		{
			maxWords = pq.size();
		}
		for (int i = 0; i < maxWords; i++)
		{
			KeyWord max = pq.removeMax();
			try
			{
				trimDict.insert(max);
			} catch (DuplicateException e)
			{
			}
		}

		//Generate the html output file
		generateHtml(trimDict, out);

		// Close everything
		if (in != null)
			in.close();
		if (inIgnore != null)
			inIgnore.close();
		if (out != null)
			out.close();
	}

	/**
	 * Parses the given line into an array of words.
	 * 
	 * @param line
	 *            a line of input to parse
	 * @return a list of words extracted from the line of input in the order
	 *         they appear in the line
	 * 
	 *         DO NOT CHANGE THIS METHOD.
	 */
	private static List<String> parseLine(String line)
	{
		String[] tokens = line.split("[ ]+");
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < tokens.length; i++)
		{ // for each word

			// find index of first digit/letter
			boolean done = false;
			int first = 0;
			String word = tokens[i];
			while (first < word.length() && !done)
			{
				if (Character.isDigit(word.charAt(first))
						|| Character.isLetter(word.charAt(first)))
					done = true;
				else
					first++;
			}

			// find index of last digit/letter
			int last = word.length() - 1;
			done = false;
			while (last > first && !done)
			{
				if (Character.isDigit(word.charAt(last))
						|| Character.isLetter(word.charAt(last)))
					done = true;
				else
					last--;
			}

			// trim from beginning and end of string so that is starts and
			// ends with a letter or digit
			word = word.substring(first, last + 1);

			// make sure there is at least one letter in the word
			done = false;
			first = 0;
			while (first < word.length() && !done)
				if (Character.isLetter(word.charAt(first)))
					done = true;
				else
					first++;
			if (done)
				words.add(word);
		}

		return words;
	}

	/**
	 * Generates the html file using the given list of words. The html file is
	 * printed to the provided PrintStream.
	 * 
	 * @param words
	 *            a list of KeyWords
	 * @param out
	 *            the PrintStream to print the html file to
	 * 
	 *            DO NOT CHANGE THIS METHOD
	 */
	private static void generateHtml(DictionaryADT<KeyWord> words,
			PrintStream out)
	{
		String[] colors =
		{ "6F", "6A", "65", "60", "5F", "5A", "55", "50", "4F", "4A", "45",
				"40", "3F", "3A", "35", "30", "2F", "2A", "25", "20", "1F",
				"1A", "15", "10", "0F", "0A", "05", "00" };
		int initFontSize = 80;

		// Print the header information including the styles
		out.println("<head>\n<title>Word Cloud</title>");
		out.println("<style type=\"text/css\">");
		out.println("body { font-family: Arial }");

		// Each style is of the form:
		// .styleN {
		// font-size: X%;
		// color: #YYAA;
		// }
		// where N and X are integers and Y is two hexadecimal digits
		for (int i = 0; i < colors.length; i++)
			out.println(".style" + i + " {\n    font-size: "
					+ (initFontSize + i * 20) + "%;\n    color: #" + colors[i]
					+ colors[i] + "AA;\n}");

		out.println("</style>\n</head>\n<body><p>");

		// Find the minimum and maximum values in the collection of words
		int min = Integer.MAX_VALUE, max = 0;
		for (KeyWord word : words)
		{
			int occur = word.getOccurences();
			if (occur > max)
				max = occur;
			else if (occur < min)
				min = occur;
		}

		double slope = (colors.length - 1.0) / (max - min);

		for (KeyWord word : words)
		{
			out.print("<span class=\"style");

			// Determine the appropriate style for this value using
			// linear interpolation
			// y = slope *(x - min) (rounded to nearest integer)
			// where y = the style number
			// and x = number of occurrences
			int index = (int) Math.round(slope * (word.getOccurences() - min));

			out.println(index + "\">" + word.getWord() + "</span>&nbsp;");
		}

		// Print the closing tags
		out.println("</p></body>\n</html>");
	}
}
