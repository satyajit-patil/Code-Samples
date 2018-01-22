
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             BSTDictionaryIterator.java
// Semester:         CS367 Fall 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;

/**
 * BSTDictionaryIterator implements an iterator for a binary search tree (BST)
 * implementation of a Dictionary. The iterator iterates over the tree in order
 * of the key values (from smallest to largest).
 */
public class BSTDictionaryIterator<K> implements Iterator<K>
{

	Stack<BSTnode> stack;

	/**
	 * BSTDictionaryIterator constructor; pushes all nodes into a stack
	 * 
	 * @param root
	 *            root node
	 */
	public BSTDictionaryIterator(BSTnode<K> root)
	{
		stack = new Stack<BSTnode>();
		while (root != null)
		{
			stack.push(root);
			root = root.getLeft();
		}
	}

	/**
	 * Checks whether stack is empty
	 */
	public boolean hasNext()
	{
		return !stack.isEmpty();
	}

	/**
	 * Gets the next node by popping nodes from the stack
	 */
	public K next()
	{
		BSTnode<K> node = stack.pop();
		K key = node.getKey();
		if (node.getRight() != null)
		{
			node = node.getRight();
			while (node != null)
			{
				stack.push(node);
				node = node.getLeft();
			}
		}
		return key;
	}

	public void remove()
	{
		// DO NOT CHANGE: you do not need to implement this method
		throw new UnsupportedOperationException();
	}
}
