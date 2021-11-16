//-----------------------------------------------------
// Title: Cycle Class (Task2)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 2
// Description: This is a cycle class to detect and measure the length of the cycle
//-----------------------------------------------------



package task2;

import java.util.ArrayList;



public class Cycle {
	//Generic arrays like marked edgeTo etc
	private boolean[] marked;
	private int[] edgeTo;
	public Stack<Integer> cycle;

	public int counter=0;
	public int cycleCounter=0;
	//I used this arraylist to store lengths of the cycles
	public ArrayList<Integer> cycleLengthStorage=new ArrayList<Integer>();
	public boolean isCyclic=false;

	
	
	/**
	 * Constructor
	 *
	 * uses DFS and mared array to detect cycles.
	 * @param Graph G
	 */
	public Cycle(Graph G) {
		
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		int v = 0;
		for (v = 0; v < G.V(); v++)
			if (!marked[v]) {

				cycle=null;
//				System.out.println("non marked "+v);
				dfs(G, -1, v);
				
			}
							
	}



	

	/**
	 * Returns true if the graph has a cycle.
	 *
	 * @return true if the graph has a cycle; false otherwise
	 */
	public boolean hasCycle() {
		return isCyclic ;
	}


	/**
	 * This dfs method does the whole thing it uses dfs to run across all adjecent verticies
	 * after that if it reaches to vertex that already  marked it detects a cycle.
	 * after finding the cycle it yses edgeTo method to find whole cycle and to push it 
	 * to stcak but I didnt use stack since we dont need in our assignment.
	 * 
	 * it uses conyer and counterCycle to count length and to count cyles.
	 *
	 * @return void method but sets the important values and arrayList
	 */
	
	private void dfs(Graph G, int u, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			
			if (cycle != null) return;

			if (!marked[w]) {
//				System.out.print(w+ " w");
				edgeTo[w] = v;
				dfs(G, v, w);
			}

			// check for cycle (but disregard reverse of edge leading to v)
			else if (w != u) {
				isCyclic=true;
//				System.out.print(w+ " ");
//				System.out.println("_______________________________________________________________________");
//				System.out.println(counter+" count ");

				cycleCounter++;
//				System.out.println(cycleCounter+" cycless "+w);
				cycle = new Stack<Integer>();
//				System.out.print(w+ " w");
				for (int x = v; x != w; x = edgeTo[x]) {
//					cycle.push(x);
//					System.out.println(x+" cycless ");
					counter++;
				}
				counter+=1;
				cycleLengthStorage.add(counter);
//				System.out.println(counter+" count ");
				counter=0;
//				cycle.push(w);
//				cycle.push(v);
				
			}


			
		}
	}
}