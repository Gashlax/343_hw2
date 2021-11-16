//-----------------------------------------------------
// Title: Stack Class (Task2)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 2
// Description: This class is from our Algorithms, 4th Edition book I used this class to store  
// cycles as we learned on lectures.
// This is the book solution of this case
//-----------------------------------------------------

 //  @author Robert Sedgewick
 //  @author Kevin Wayne



package task2;

import java.util.Iterator;


public class Stack<Item> implements Iterable<Item>
{
	private Node first; // top of stack (most recently added node)
	private int N; // number of items
	private class Node
	{ // nested class to define nodes
		Item item;
		Node next;
	}
	public boolean isEmpty() { return first == null; } // Or: N == 0.
	public int size() { return N; }
	public void push(Item item)
	{ // Add item to top of stack.
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	public Item pop()
	{ // Remove item from top of stack.
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

	public Iterator<Item> iterator()
	{ return new ListIterator(); }
	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;
		public boolean hasNext()
		{ return current != null; }
		public void remove() { }
		public Item next()
		{
			Item item = current.item;
			current = current.next;
			return item;
		}
	}// See page 155 for iterator() implementation.
	// See page 147 for test client main().
}