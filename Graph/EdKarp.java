/* EdmondsKarp
 ** |V|^2 \cdot |E|
 * Finds the greatest flow in a graph. Capacities must be positive.
 */

//START
public static boolean BFS(Vertex[] G, int s, int t) {
   int N = G.length;
   for(int i = 0; i < N; i++) {
      G[i].vis = false;
   }
   
   Queue<Vertex> q = new LinkedList<Vertex>();
   G[s].vis = true;
   G[s].pre = -1;
   queue.add(G[s]);
   
   while(!q.isEmpty()) {
      Vertex u = queue.poll();
      if(u.id == t) return true;
      for(int i : u.adj.keySet()) {
	  Edge e = u.adj.get(i);
	  Vertex v = e.t;
	  if(!v.vis) {
	      v.vis = true;
	      v.pre = u.id;
	      q.add(v);
	  }
      }
   }
   return (G[t].vis);
}
//END

//START
//We store the edges in the graph in a hashmap
public static int fordFulkerson(Vertex[] G, int s, int t) {

   int maxflow = 0;
   while(BFS(rgraph, s, t)) {
      int pathflow = Integer.MAX_VALUE;
      for(int v = t; v!= s; v = v.pre) {
         int u = v.pre;
	 pathflow = Math.min(pathflow, G[u].adj.get(v).rw);
      }

      for(int v = t; v != s; v = v.pre) {
         int u = v.pre;
	 G[u].adj.get(v).rw -= pathflow;
	 G[v].adj.get(u).rw += pathflow;
      }

      maxflow += pathflow;
   }
   return maxflow;
}
//END