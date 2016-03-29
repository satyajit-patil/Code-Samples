///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Reciever.java
// File:             BrokenImageException.java
// Semester:         CS367 Spring 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This exception should be thrown whenever the received image buffer has any
 * broken part.
 * 
 * @author zarcen (Wei-Chen Chen)
 */
public class BrokenImageException extends Exception {

	/**
	 * Constructs a BrokenImageException with a message
	 * @param s the error message
	 */
	public BrokenImageException(String s) {
		super(s);
	}

	/**
	 * Constructs a BrokenImageException
	 */
	public BrokenImageException() {
	}
}
