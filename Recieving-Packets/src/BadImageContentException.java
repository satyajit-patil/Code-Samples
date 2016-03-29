///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Reciever.java
// File:             BadImageContentException.java
// Semester:         CS367 Spring 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This exception should be thrown whenever the image ONLY has errors in its
 * body.
 *
 * @author jit (Satyajit Patil)
 */
public class BadImageContentException extends RuntimeException
{
	/**
	 * Constructs a BadImageContentException
	 */
	public BadImageContentException()
	{
	}

	/**
	 * Constructs a BadImageContentException with a message
	 * 
	 * @param s
	 *            the error message
	 */
	public BadImageContentException(String s)
	{
		super(s);
	}

}
