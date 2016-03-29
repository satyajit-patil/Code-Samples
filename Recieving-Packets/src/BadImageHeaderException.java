///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Reciever.java
// File:             BadImageHeaderException.java
// Semester:         CS367 Spring 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This exception should be thrown whenever the image ONLY has errors in its
 * header.
 *
 * @author jit (Satyajit Patil)
 */
public class BadImageHeaderException extends RuntimeException
{

	/**
	 * Constructs a BadImageHeaderException
	 */
	public BadImageHeaderException()
	{
	}

	/**
	 * Constructs a BadImageHeaderException with a message
	 * 
	 * @param s
	 *            the error message
	 */
	public BadImageHeaderException(String s)
	{
		super(s);
	}

}
