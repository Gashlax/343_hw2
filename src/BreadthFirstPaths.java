//-----------------------------------------------------
// Title: BreadthFirstPaths Class (Task1)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 2
// Description: It is generic BFS Class from textbook but I changed some methods for my purposes 
//-----------------------------------------------------

/** Wİth the help of:
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path
    public int[] forbidens;

    /**
     * Computes the shortest path between the source vertex with the help of BFS
     * and it records every other vertex in the graph.
     * @param G the graph
     * @param s the source vertex
     * @param takes forbidens array to marked them
     */
    public BreadthFirstPaths(Graph G, int s, int[] forbidens) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s, forbidens);

        //assert check(G, s);
    }

    /**
     * Computes the shortest path with single source vertices in the graph
     * before making necessary operations it first marks the forbiden nodes as true
     * then makes generic bfs moves
     * @param G the graph
     * @param sources the source vertices
     * @param forbidens array
     */
    private void bfs(Graph G, int s, int[] forbidens) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);
        for(int t=0;t<forbidens.length;t++) {
        	marked[forbidens[t]]=true;
        	//System.out.println(forbidens[t]);
        }

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }



    /**
     * Is there a path between the source vertex to destination vertex
     * to know that is uses marked array
     * @param v the destination vertex
     * @return retrun true if there is a path, and return false otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path between the source vertex 
     * since only have 1 source vertex we can reach it with basic distTo array
     * @param v the vertex
     * @return the number of edges in such a shortest path (or {@code Integer.MAX_VALUE} if there is no such path)
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


}