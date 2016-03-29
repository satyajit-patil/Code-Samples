
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Whatsapp.java
// File:             BroadcastList.java
// Semester:         CS367 Spring 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.List;

/**
 * This is the broadcast list class which captures information of a broadcast
 * list
 *
 * @author jmishra
 */
public class BroadcastList
{
	// class fields
	private String blistNickname;
	private List<String> blistMembers;

	/**
	 * Constructs a new instance of this class. nickname cannot be null or
	 * empty. members cannot be null.
	 *
	 * @param nickname
	 *            the nickname of the broadcast list
	 * @param members
	 *            the list of nicknames of all members of this list
	 * @throws WhatsAppRuntimeException
	 *             throw a new instance of this with CANT_BE_EMPTY_OR_NULL
	 *             message if the validation of nickname or members fails
	 *
	 */
	public BroadcastList(String nickname, List<String> members)
			throws WhatsAppRuntimeException
	{
		if (nickname == null || members == null || nickname.isEmpty())
		{
			throw new WhatsAppRuntimeException(Config.CANT_BE_EMPTY_OR_NULL);
		}
		blistNickname = nickname;
		blistMembers = members;
	}

	/**
	 * A getter of the nickname
	 *
	 * @return the nickname of the broadcast list
	 */
	public String getNickname()
	{
		return blistNickname;
	}

	/**
	 * A setter of the nickname of this broadcast list
	 *
	 * @param nickname
	 *            the nickname of this broadcast list
	 */
	public void setNickname(String nickname)
	{
		blistNickname = nickname;
	}

	/**
	 * A getter of the list of members of this broadcast list
	 *
	 * @return the list of members of this broadcast list
	 */
	public List<String> getMembers()
	{
		return blistMembers;
	}

	/**
	 * A setter of the list of members of this broadcast list
	 *
	 * @param members
	 *            the list of members of this broadcast list
	 */
	public void setMembers(List<String> members)
	{
		blistMembers = members;
	}

}
