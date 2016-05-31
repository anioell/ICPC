/*Iterative DFS
**|V| + |E|
* Simple iterative DFS, the recursive variant is a bit fancier. Not tested.
*/

//START
//if we want to start the DFS for different connected components, there is such a method
//in the recursive variant of DFS

public static boolean ItDFS(Vertex[] G, int s, int t) {

    //take care that all the nodes are not visited at the beginning
    
    Stack<Integer> S = new Stack<Integer>();
    s.push(s);
    while(!S.isEmpty()) {
	int u = S.pop();
	if(u.id == t) return true;
	if(!G[u].vis) {
	    G[u].vis = true;
	    for(Vertex v : G[u].adj) {
		if(!v.vis) S.push(v.id);
	    }
	}
    }

    return false;
}