
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             ArrayHeap.java
// Semester:         CS367 Fall 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.NoSuchElementException;

public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E>
{

	// default number of items the heap can hold before expanding
	private static final int INIT_SIZE = 100;
	private E[] array;
	private int size;

	/**
	 * No argument ArrayHeap constructor with default size
	 */
	public ArrayHeap()
	{
		array = (E[]) (new Prioritizable[INIT_SIZE]);
		size = 0;
	}

	/**
	 * Constructs ArrayHeap
	 * 
	 * @param size
	 *            size of ArrayHeap
	 */
	public ArrayHeap(int size)
	{
		if (size < 0)
		{
			throw new IllegalArgumentException();
		} else
		{
			array = (E[]) (new Prioritizable[size]);
			size = 0;
		}
	}

	/**
	 * Check if ArrayHeap is empty
	 */
	public boolean isEmpty()
	{
		if (size == 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * insert item into the ArrayHeap
	 */
	public void insert(E item)
	{
		if (size >= array.length - 1)
		{
			resize();
		}
		array[size] = item;
		int i = size;
		int p = (i - 1) / 2;

		while (i != 0 && array[i].getPriority() > array[p].getPriority())
		{
			E temp = array[i];
			array[i] = array[p];
			array[p] = temp;
			i = p;
			p = (i - 1) / 2;
		}
		++size;
	}

	/**
	 * Remove item with max priority from the ArrayHeap
	 */
	public E removeMax()
	{
		--size;

		E temp = array[0];
		array[0] = array[size];
		array[size] = temp;
		int i = 0;

		int mc = maxChild(i);
		while ((2 * i + 1 < size)
				&& array[i].getPriority() < array[mc].getPriority())
		{
			E tmp = array[i];
			array[i] = array[mc];
			array[mc] = tmp;
			i = mc;
			mc = maxChild(i);
		}

		return array[size];
	}

	/**
	 * Gets the max child of a parent in the ArrayHeap
	 * 
	 * @param i
	 *            parent
	 * @return location of maxChild
	 */
	private int maxChild(int i)
	{
		if ((2 * i + 1) >= size)
		{
			return -1;
		}
		if ((2 * i + 2) >= size)
		{
			return (2 * i + 1);
		} else if (array[2 * i + 1].getPriority() > array[2 * i + 2]
				.getPriority())
		{
			return (2 * i + 1);
		} else
		{
			return (2 * i + 2);
		}
	}

	/**
	 * Gets item of maximum priority
	 */
	public E getMax()
	{
		E max = array[0];
		return max;
	}

	/**
	 * Gets the size of the ArrayHeap
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Doubles the size of the ArrayHeap when it becomes full
	 */
	private void resize()
	{
		E[] B = (E[]) (new Prioritizable[array.length * 2]);
		for (int i = 0; i < array.length; ++i)
		{
			B[i] = array[i];
		}
		array = B;
	}
}
