//-----------------------------------------------------
// Title: Graph Class (Task1)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 2
// Description: It is generic Graph Class from textbook but I changed some methods for my purposes 
//-----------------------------------------------------

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

	public int V() { return V; }
	
	public int E() { return E; }
	
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
	
	
	//These bellow methods helps to main class. 
    /**
     *Takes the source integer and increases the first digit
     * 
     * @param int source
     *  @return new value
     */
	public int increaseFirst(int src) {
		int temp=src;
		temp=temp%10;
		if(temp==9) {
			src+=1;
			src-=10;
		}else {
			src+=1;
		}
		return src;
	}
	
    /**
     *Takes the source integer and increases the second digit
     * 
     * @param int source
     *  @return new value
     */
	public int increaseSecond(int src) {
		int temp=src;
		temp=(temp/10)%10;
		if(temp==9) {
			src+=10;
			src-=100;
			
		}else {
			src+=10;
		}
		return src;
	}
    /**
     *Takes the source integer and increases the third digit
     * 
     * @param int source
     *  @return new value
     */
	public int increaseThird(int src) {
		int temp=src;
		temp=(temp/100)%10;
		if(temp==9) {
			src+=100;
			src-=1000;
			
		}else {
			src+=100;
		}
		return src;
	}
	
	
    /**
     *Takes the source integer and increases the forth digit
     * 
     * @param int source
     * @return new value
     */
	public int increaseForth(int src) {
		int temp=src;
		temp=(temp/1000)%10;
		if(temp==9) {
			src+=1000;
			src-=10000;
			
		}else {
			src+=1000;
		}
		return src;
	}

    /**
     *Takes the source integer and decreases the first digit
     * 
     * @param int source
     * @return new value
     */
	public int decreaseFirst(int src) {
		int temp=src;
		temp=(temp)%10;
		if(temp==0) {
			src+=9;			
		}else {
			src-=1;
		}
		return src;
	}
	
    /**
     *Takes the source integer and decreases the second digit
     * 
     * @param int source
     * @return new value
     */
	public int decreaseSecond(int src) {
		int temp=src;
		temp=(temp/10)%10;
		if(temp==0) {
			src+=90;			
		}else {
			src-=10;
		}
		return src;
	}
	
	
    /**
     *Takes the source integer and decreases the third digit
     * 
     * @param int source
     * @return new value
     */
	public int decreaseThird(int src) {
		int temp=src;
		temp=(temp/100)%10;
		if(temp==0) {
			src+=900;			
		}else {
			src-=100;
		}
		return src;
	}
	
    /**
     *Takes the source integer and decreases the forth digit
     * 
     * @param int source
     * @return new value
     */
	public int decreaseForth(int src) {
		int temp=src;
		temp=(temp/1000)%10;
		if(temp==0) {
			src+=9000;			
		}else {
			src-=1000;
		}
		return src;
	}
	
}