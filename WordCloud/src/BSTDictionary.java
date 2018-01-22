
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             BSTDictionary.java
// Semester:         CS367 Fall 2016
//
// Author:           Satyajit Patil / spatil5@wisc.edu
// CS Login:         jit
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC - 003
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Iterator;

public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K>
{
	private BSTnode<K> root; // the root node
	private int numItems; // the number of items in the dictionary

	/**
	 * No argument BSTDictionary constructor
	 */
	public BSTDictionary()
	{
		root = null;
		numItems = 0;
	}

	/**
	 * Insert key into BSTDictionary using a helper method
	 */
	public void insert(K key) throws DuplicateException
	{
		if (key == null)
		{
			throw new IllegalArgumentException();
		} else
		{
			root = insert(root, key);
			numItems++;
		}
	}

	/**
	 * Helper insert method
	 * 
	 * @param n
	 *            root node
	 * @param key
	 *            key to be inserted
	 * @return modified root node
	 * @throws DuplicateException
	 */
	private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException
	{
		if (n == null)
		{
			return new BSTnode<K>(key, null, null);
		}

		if (n.getKey().equals(key))
		{
			throw new DuplicateException();
		}
		if (key.compareTo(n.getKey()) < 0)
		{
			n.setLeft(insert(n.getLeft(), key));
			return n;
		} else
		{
			n.setRight(insert(n.getRight(), key));
			return n;
		}
	}

	/**
	 * Deletes key from BSTDictionary using a helper method
	 */
	public boolean delete(K key)
	{
		BSTnode<K> after = delete(root, key);
		if (after.equals(root))
		{
			return false;
		} else
		{
			numItems--;
			root = after;
			return true;
		}
	}

	/**
	 * Delete helper method
	 * 
	 * @param n
	 *            root node
	 * @param key
	 *            key to be deleted
	 * @return modified root node
	 */
	private BSTnode<K> delete(BSTnode<K> n, K key)
	{
		if (n == null)
		{
			return null;
		}
		if (key.equals(n.getKey()))
		{
			if (n.getLeft() == null && n.getRight() == null)
			{
				return null;
			}
			if (n.getLeft() == null)
			{
				return n.getRight();
			}
			if (n.getRight() == null)
			{
				return n.getLeft();
			}

			K smallVal = smallest(n.getRight());
			n.setKey(smallVal);
			n.setRight(delete(n.getRight(), smallVal));
			return n;
		} else if (key.compareTo(n.getKey()) < 0)
		{
			n.setLeft(delete(n.getLeft(), key));
			return n;
		} else
		{
			n.setRight(delete(n.getRight(), key));
			return n;
		}
	}

	/**
	 * Gets the smallest key in BSTDictionary
	 * 
	 * @param n
	 *            root node
	 * @return smallest key
	 */
	private K smallest(BSTnode<K> n)
	{
		if (n.getLeft() == null)
		{
			return n.getKey();
		} else
		{
			return smallest(n.getLeft());
		}
	}

	/**
	 * Looks up for a key in the BSTDictionary using a helper method
	 */
	public K lookup(K key)
	{
		if (lookup(root, key))
		{
			return key;
		} else
		{
			return null;
		}
	}

	/**
	 * Helper lookup method
	 * @param n
	 * 	root node
	 * @param key
	 * 	key being looked for
	 * @return
	 * 	whether key was found
	 */
	private boolean lookup(BSTnode<K> n, K key)
	{
		if (n == null)
		{
			return false;
		}

		if (n.getKey().equals(key))
		{
			return true;
		}

		if (key.compareTo(n.getKey()) < 0)
		{
			return lookup(n.getLeft(), key);
		}

		else
		{
			return lookup(n.getRight(), key);
		}
	}

	/**
	 * Check if BSTDictionary is empty
	 */
	public boolean isEmpty()
	{
		if (numItems == 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * Gets the size of the BSTDictionary
	 */
	public int size()
	{
		return numItems;
	}

	/**
	 * Gets the total path length of the BSTDictionary using a helper method
	 */
	public int totalPathLength()
	{
		int sum = 0;
		int level = 1;
		sum = totalPathLength(root, level);
		return sum;
	}

	/**
	 * TotalPathLength helper method
	 * @param n
	 * 	root node
	 * @param depth
	 * 	depth currently in
	 * @return
	 * 	total path length
	 */
	private int totalPathLength(BSTnode<K> n, int depth)
	{
		if (n == null)
		{
			return 0;
		} else
		{
			return depth + totalPathLength(n.getLeft(), depth + 1)
					+ totalPathLength(n.getRight(), depth + 1);
		}
	}

	/**
	 * Creates a BSTDictionary iterator
	 */
	public Iterator<K> iterator()
	{
		Iterator<K> itr = new BSTDictionaryIterator<K>(root);
		return itr;
	}
}
