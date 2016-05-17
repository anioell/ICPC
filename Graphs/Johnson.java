/* Johnsons Algorithm
** |V|^2 \log V + VE
*
*/

//START
public static int[][] johnson(Vertex[] G) {

    //compute a new graph G'
    Vertex[] Gd = new Vertex[G.length+1];
    int s = G.length;
    for(int i = 0; i < G.length; i++) {
	Gd[i] = G[i];
    }
    //init new vertex with zero-weight-edges to each vertex
    Vertex S = new Vertex(G.length);
    for(int i = 0; i < G.length) {
	S.adj.add(new Edge(Gd[i], 0));
    }

    //bellman-ford to check for neg-weight-cycles and to adapt edges to enable running dijkstra
    if(!bellmanFord(G, s)) {
	System.out.println("False");
	return;
    }
    //change weights
    for(int i = 0; i < G.length; i++) {
	for(Edge e : Gd[i].adj) {
	    e.w = e.w + Gd[i].dist - e.t.dist;
	}
    }
    //store distances to invert this step later
    int[] h = new int[G.length];
    for(int i = 0; i < G.length; i++) {
	h[i] = G[i].dist;
    }
    
    //create shortest path matrix
    int[][] apsp = new int[G.length][G.length];

    //now use original graph G
    //start a dijkstra for each vertex
    for(int i = 0; i < G.length; i++) {
	//reset weights, maybe we should put that in the dijkstra
	for(int j = 0; j < G.length; j++) {
	    G[j].vis = false;
	    G[j].dist = Integer.MAX_VALUE;
	}
	dijkstra(G, i);
	for(int j = 0; j < G.length; j++) {
	    apsp[i][j] = G[j].dist + h[j] - h[i];
	}
    }
    return apsp;
}
