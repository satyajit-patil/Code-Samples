///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Reciever.java
// File:             PacketLinkedList.java
// Semester:         CS367 Spring 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A Single-linked linkedlist with a "dumb" header node (no data in the node),
 * but without a tail node. It implements ListADT<E> and returns
 * PacketLinkedListIterator when requiring a iterator.
 */
public class PacketLinkedList<E> implements ListADT<E>
{

	private Listnode<E> packets;
	private int numPackets;

	/**
	 * Constructs a empty PacketLinkedList with a header node
	 */
	public PacketLinkedList()
	{
		packets = new Listnode<E>(null);
		numPackets = 0;
	}

	/**
	 * Adds item to end of the List
	 * 
	 * @param item
	 *            the item to add
	 */
	public void add(E item)
	{
		// IF item is null, throw IllegalArgumentException
		if (item == null)
		{
			throw new IllegalArgumentException();
		}

		// traverse to last node of the List
		Listnode<E> curr = packets;
		while (curr.getNext() != null)
		{
			curr = curr.getNext();
		}
		// set next to new Listnode containing item
		curr.setNext(new Listnode<E>(item));
		numPackets++;
	}

	/**
	 * Adds item at position pos in the List, moving the items originally in
	 * positions pos through size()-1 one place to the right to make room.
	 * 
	 * @param pos
	 *            the position at which to add the item
	 * @param item
	 *            the item to add
	 */
	public void add(int pos, E item)
	{
		// check for bad position
		if (pos < 0 || pos > numPackets)
		{
			throw new IndexOutOfBoundsException();
		}

		// IF item is null, throw IllegalArgumentException
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		// IF asked to add to end, let the other add method do the work
		if (pos == numPackets)
		{
			add(item);
			return;
		} else
		{
			// find the node n after which to add a new node and add the new
			// node
			Listnode<E> curr = packets;
			for (int i = 0; i < pos; i++)
			{
				curr = curr.getNext();
			}
			curr.setNext(new Listnode<E>(item, curr.getNext()));
			numPackets++;
		}
	}

	/**
	 * Returns true iff item is in the List (i.e., there is an item x in the
	 * List such that x.equals(item))
	 * 
	 * @param item
	 *            the item to check
	 * @return true if item is in the List, false otherwise
	 */
	public boolean contains(E item)
	{
		// IF item is null, throw IllegalArgumentException
		if (item == null)
		{
			throw new IllegalArgumentException();
		}

		Listnode<E> curr = packets;
		curr = curr.getNext();

		while (curr.getNext() != null)
		{
			if (curr.getData().equals(item))
			{
				return true;
			} else
			{
				curr = curr.getNext();
			}
		}
		return false;
	}

	/**
	 * Returns the item at position pos in the List.
	 * 
	 * @param pos
	 *            the position of the item to return
	 * @return the item at position pos
	 */
	public E get(int pos)
	{
		// check for bad position
		if (pos < 0 || pos >= numPackets)
		{
			throw new IndexOutOfBoundsException();
		}

		Listnode<E> curr = packets;
		curr = curr.getNext();
		for (int i = 0; i < pos; i++)
		{
			curr = curr.getNext();
		}
		return curr.getData();
	}

	/**
	 * Returns true iff the List is empty.
	 * 
	 * @return true if the List is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		Listnode<E> curr = packets;

		if (packets.getNext().equals(null))
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * Removes and returns the item at position pos in the List, moving the
	 * items originally in positions pos+1 through size() - 1 one place to the
	 * left to fill in the gap.
	 * 
	 * @param pos
	 *            the position at which to remove the item
	 * @return the item at position pos
	 */
	public E remove(int pos)
	{
		// check for bad position
		if (pos < 0 || pos > numPackets)
		{
			throw new IndexOutOfBoundsException();
		}

		Listnode<E> curr = packets;

		// IF pos is the last node, traverse to second last node and setNext to
		// null
		if (pos == numPackets - 1)
		{
			for (int i = 0; i < numPackets - 1; i++)
			{
				curr = curr.getNext();
			}
			curr.setNext(null);
			numPackets--;
		}
		// ELSE
		else
		{
			for (int i = 0; i < pos; i++)
			{
				curr = curr.getNext();
			}
			curr.setNext(curr.getNext().getNext());
			numPackets--;
		}
		return curr.getData();
	}

	/**
	 * Returns the number of items in the List.
	 * 
	 * @return the number of items in the List.
	 */
	public int size()
	{
		return numPackets;
	}

	/**
	 * Creates a new iterator for the PacketLinkedList
	 * 
	 * @return PacketLinkedListIterator<E>
	 */
	public PacketLinkedListIterator<E> iterator()
	{
		return (new PacketLinkedListIterator<>(packets));
	}

}
