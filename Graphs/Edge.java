/*Reference for Edge classes
 *Used for example in Dijkstra algorithm, implements edges with weight. Needs testing.
 */

//START
//for Kruskal we need to sort edges, use:
class Edge implements Comparable<Edge> {}

class Edge {

    //for Kruskal it is helpful to store the start as well
    //moreover we might not need the vertex class
    int s;
    int t;
    
    public Edge(int s, int t, int w) {...}
    public int compareTo(Edge other) {
	return Integer.compare(this.w, other.w);
    }

    //for EKarp we also want to store residual weights
    int rw;
    
    Vertex t;
    int w;

    public Edge(Vertex t, int w) {
	this.t = t;
	this.w = w;
	this.rw = w;
    }

}
