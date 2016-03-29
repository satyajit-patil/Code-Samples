
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Programming Assignment 2: Receiving an Image
// Files:            (list of source files)
// Semester:         CS367 Spring 2016
//
// Author:           Satyajit Patil
// Email:            spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.IOException;
import java.util.Iterator;

/**
 * The main class. It simulates a application (image viewer) receiver by
 * maintaining a list buffer. It collects packets from the queue of InputDriver
 * and arrange them properly, and then reconstructs the image file from its list
 * buffer.
 */
public class Receiver
{
	private InputDriver input;
	private ImageDriver img;
	private PacketLinkedList<SimplePacket> list;

	/**
	 * Constructs a Receiver to obtain the image file transmitted.
	 * 
	 * @param file
	 *            the filename you want to receive
	 */
	public Receiver(String file)
	{
		try
		{
			input = new InputDriver(file, true);
		} catch (IOException e)
		{
			System.out.println(
					"The file, " + file + ", isn't existed on the server.");
			System.exit(0);
		}
		img = new ImageDriver(input);
		list = new PacketLinkedList<>();
	}

	/**
	 * Returns the PacketLinkedList buffer in the receiver
	 * 
	 * @return the PacketLinkedList object
	 */
	public PacketLinkedList<SimplePacket> getListBuffer()
	{
		return list;
	}

	/**
	 * Asks for retransmitting the packet. The new packet with the sequence
	 * number will arrive later by using {@link #askForNextPacket()}. Notice
	 * that ONLY packet with invalid checksum will be retransmitted.
	 * 
	 * @param pkt
	 *            the packet with bad checksum
	 * @return true if the requested packet is added in the receiving queue;
	 *         otherwise, false
	 */
	public boolean askForRetransmit(SimplePacket pkt)
	{
		return input.resendPacket(pkt);
	}

	/**
	 * Asks for retransmitting the packet with a sequence number. The requested
	 * packet will arrive later by using {@link #askForNextPacket()}. Notice
	 * that ONLY missing packet will be retransmitted. Pass seq=0 if the missing
	 * packet is the "End of Streaming Notification" packet.
	 * 
	 * @param seq
	 *            the sequence number of the requested missing packet
	 * @return true if the requested packet is added in the receiving queue;
	 *         otherwise, false
	 */
	public boolean askForMissingPacket(int seq)
	{
		return input.resendMissingPacket(seq);
	}

	/**
	 * Returns the next packet.
	 * 
	 * @return the next SimplePacket object; returns null if no more packet to
	 *         receive
	 */
	public SimplePacket askForNextPacket()
	{
		return input.getNextPacket();
	}

	/**
	 * Returns true if the maintained list buffer has a valid image content.
	 * Notice that when it returns false, the image buffer could either has a
	 * bad header, or just bad body, or both.
	 * 
	 * @return true if the maintained list buffer has a valid image content;
	 *         otherwise, false
	 */
	public boolean validImageContent()
	{
		return input.validFile(list);
	}

	/**
	 * Returns if the maintained list buffer has a valid image header
	 * 
	 * @return true if the maintained list buffer has a valid image header;
	 *         otherwise, false
	 */
	public boolean validImageHeader()
	{
		return input.validHeader(list.get(0));
	}

	/**
	 * Outputs the formatted content in the PacketLinkedList buffer. This method
	 * is used to assist in debugging
	 */
	public void displayList()
	{
		PacketLinkedList<SimplePacket> curr = list;
		Iterator<SimplePacket> itr = curr.iterator(); // iterator to traverse
														// through 'curr'

		// WHILE curr has a SimplePacket
		while (itr.hasNext())
		{
			SimplePacket packet = itr.next();

			// IF SimplePacket is valid checksum, print packet's sequence number
			// with no braces
			if (packet.isValidCheckSum())
			{
				System.out.print(packet.getSeq() + ",");
			}
			// ELSE IF SimplePacket is invalid checksum, print packet's sequence
			// number with braces
			else
			{
				System.out.print("[" + packet.getSeq() + "],");
			}
		}
	}

	/**
	 * Reconstructs the file by arranging the PacketLinkedList in correct order.
	 * It uses askForNextPacket() to get packets until there are no more packet
	 * to receive. It eliminates the duplicate packets and asks for
	 * retransmitting when getting a packet with invalid checksum.
	 */
	public void reconstructFile()
	{
		// Gets the first packet and makes sure it is not null
		SimplePacket packet = askForNextPacket();
		while (packet == null)
		{
			packet = askForNextPacket();
		}

		// WHILE the next packet does not have a negative sequence number
		while (!(packet.getSeq() <= 0))
		{
			// IF packet is NOT valid checksum
			if (!packet.isValidCheckSum())
			{
				// remove duplicates
				for (int i = 0; i < list.size(); i++)
				{
					SimplePacket duplicate = list.get(i);
					if (duplicate.getSeq() == packet.getSeq())
					{
						list.remove(i);
					}
				}
				askForRetransmit(packet); // ask for retransmit
			} else // ELSE packet IS valid checksum
			{
				// remove duplicates
				for (int i = 0; i < list.size(); i++)
				{
					SimplePacket duplicate = list.get(i);
					if (duplicate.getSeq() == packet.getSeq())
					{
						list.remove(i);
					}
				}

				// add packet to list
				if (list.size() == 0
						|| list.get(list.size() - 1).getSeq() < packet.getSeq())
				{
					list.add(packet);
				} else
				{
					for (int i = 0; i < list.size(); i++)
					{
						if (packet.getSeq() < list.get(i).getSeq())
						{
							list.add(i, packet);
							break;
						}
					}
				}
			}
			packet = askForNextPacket();// ask for next packet
		}

		// Get a valid End of Streaming Notification Packet
		while (packet == null)
		{
			askForMissingPacket(0);
			packet = askForNextPacket();
		}

		// Check whether packets whose sequence numbers range from 1 to the
		// negative sequence value of the 'End of Sequence Notification Packet'
		// are present in the list
		for (int i = 1; i <= (-(packet.getSeq())); i++)
		{
			// variable 'missing' is used to keep track of whether a packet with
			// the sequence number 'i' is found
			boolean missing = true;

			// iterator to traverse through the list
			Iterator<SimplePacket> itr = list.iterator();

			// traverse through the list to check whether a packet with sequence
			// number 'i' exists
			while (itr.hasNext())
			{
				if (itr.next().getSeq() == i)
				{
					missing = false; // 'missing' is set to false if packet
										// exists
				}
			}

			// WHILE a packet with sequence number 'i' is missing
			while (missing)
			{
				// ask and get missing packet
				askForMissingPacket(i);
				SimplePacket missingPacket = askForNextPacket();

				// IF the new packet is NOT a valid checksum
				if (!(missingPacket.isValidCheckSum()))
				{
					// remove duplicates
					for (int j = 0; j < list.size(); j++)
					{
						SimplePacket duplicate = list.get(j);
						if (duplicate.getSeq() == missingPacket.getSeq())
						{
							list.remove(j);
						}
					}
					askForRetransmit(missingPacket);// ask for retransmit

				} else // ELSE new packet IS valid checksum
				{
					// remove duplicates
					for (int j = 0; j < list.size(); j++)
					{
						SimplePacket duplicate = list.get(j);
						if (duplicate.getSeq() == missingPacket.getSeq())
						{
							list.remove(j);
						}
					}

					// add packet to list
					if (list.size() == 0 || list.get(list.size() - 1)
							.getSeq() < missingPacket.getSeq())
					{
						list.add(missingPacket);
						missing = false;
					} else
					{
						for (int j = 0; j < list.size(); j++)
						{
							if (missingPacket.getSeq() < list.get(j).getSeq())
							{
								list.add(j, missingPacket);
								missing = false;
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Opens the image file by merging the content in the maintained list
	 * buffer.
	 * 
	 * @throws BadImageHeaderException
	 *             if header is invalid
	 * @throws BadImageContentException
	 *             if content is invalid
	 */
	public void openImage()
			throws BadImageHeaderException, BadImageContentException
	{
		// TRY to open image
		try
		{
			img.openImage(list);
		}

		// CATCH BrokenImageException
		catch (BrokenImageException ex)
		{
			// IF list has an invalid header, THROW BadImageHeaderException
			if (!validImageHeader())
			{
				throw new BadImageHeaderException(
						"The image is broken due to a damaged header");
			}
			// ELSE IF list has invalid content, THROW BadImageContentException
			else if (!validImageContent())
			{
				throw new BadImageContentException(
						"The image is broken due to corrupt content");
			}
		}
	}

	/**
	 * Initiates a Receiver to reconstruct collected packets and open the Image
	 * file, which is specified by args[0].
	 * 
	 * @param args
	 *            command line arguments
	 * @throws BadImageContentException
	 * @throws BadImageHeaderException
	 */
	public static void main(String[] args)
			throws BadImageHeaderException, BadImageContentException
	{
		if (args.length != 1)
		{
			System.out.println("Usage: java Receiver [filename_on_server]");
			return;
		}
		Receiver recv = new Receiver(args[0]);
		recv.reconstructFile();
		// recv.displayList(); // use for debugging
		recv.openImage();
	}
}
