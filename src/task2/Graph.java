//-----------------------------------------------------
// Title: Graph Class (Task2)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 2
// Description: It is generic Graph Class with basic methods
//-----------------------------------------------------


package task2;

import java.util.Iterator;

public class Graph
{
	private final int V; // number of vertices
	private int E; // number of edges
	private Bag<Integer>[] adj; // adjacency lists
	public Graph(int V)
	{
		this.V = V; this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
		for (int v = 0; v < V; v++) // Initialize all lists
			adj[v] = new Bag<Integer>(); // to empty.
	}

	public int V() {
		return V; 
	}

	public int E() {
		return E; 
	}

	// This method creates edges between vertices.
	public void addEdge(int v, int w)
	{
		adj[v].add(w); // Add w to v’s list.
		adj[w].add(v); // Add v to w’s list.
		E++;
		//System.out.println(v+" "+w);
	}
	public Iterable<Integer> adj(int v)
	{ return adj[v]; }



}