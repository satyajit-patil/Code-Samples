
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Reciever.java
// File:             PacketLinkedListIterator.java
// Semester:         CS367 Spring 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The iterator implementation for PacketLinkedList.
 */
public class PacketLinkedListIterator<T> implements Iterator<T>
{
	private Listnode<T> curr;
	private int currPos;

	/**
	 * Constructs a PacketLinkedListIterator by passing a head node of
	 * PacketLinkedList.
	 * 
	 * @param head
	 */
	public PacketLinkedListIterator(Listnode<T> head)
	{
		curr = head;
		currPos = 0;
	}

	/**
	 * Returns the next element in the iteration.
	 * 
	 * @return the next element in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more elements
	 */
	public T next()
	{
		if (!hasNext())
		{
			throw new NoSuchElementException();
		}
		T item = curr.getNext().getData();
		curr = curr.getNext();
		currPos++;

		return item;
	}

	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @return true if the iteration has more elements
	 */
	public boolean hasNext()
	{
		return (curr.getNext() != null);
	}

	/**
	 * The remove operation is not supported by this iterator
	 * 
	 * @throws UnsupportedOperationException
	 *             if the remove operation is not supported by this iterator
	 */
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
}
