/* Topological Sort
** |V| + |E|
* 
*/

//START

public static LinkedList<Integer> TS(Vertex[] G) {

    LinkedList<Integer> sorting = new LinkedList<Integer>();
    for(int i = 0; i < G.length; i++) {
	if(!G[i].vis) {
	    recTS(G[i], sorting);
	}
    }
    //check sorting for a -1 if the graph is not necessarily dag
    //maybe checking if there are too many values in sorting is easier?!
    return sorting;
}

public static LinkedList<Integer> recTS(Vertex u, LinkedList<Integer> sorting) {

    u.vis = true;
    for(Vertex v : u.adj) {
	if(v.vis) {
	    //the -1 indicates that it will not be possible to find an TS
	    //there might be a much faster and elegant way (flag?!)
	    sorting.addFirst(-1);
	} else {
	    recTS(v, sorting);
	}
    }
    sorting.addFirst(u.id);
    return sorting;
}
//END
