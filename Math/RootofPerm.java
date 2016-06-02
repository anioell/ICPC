/* Root of permutation
 * Calculates the K'th root of permutation of size N. Number at place i indicates where this dancer ended.
 * needs commenting
 */

public static int[] rop(int[] perm, int N, int K) {
    boolean[] incyc = new boolean[N];
    int[] cntcyc = new int[N+1];
    int[] g = new int[N+1];
    int[] needed = new int[N+1];
    for(int i = 1; i < N+1; i++) {
	int j = i;
	int k = K;
	int div;
	while(k > 1 && (div = gcd(k, i)) > 1) {
	    k /= div;
	    j *= div;
	}
	needed[i] = j;
	g[i] = gcd(K, j);
    }
    
    HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
    for(int i = 0; i < N; i++) {
	if(incyc[i]) continue;
	ArrayList<Integer> cyc = new ArrayList<Integer>();
	cyc.add(i);
	incyc[i] = true;
	int newelem = perm[i];
	while(newelem != i) {
	    cyc.add(newelem);
	    incyc[newelem] = true;
	    newelem = perm[newelem];
	}
	int len = cyc.size();
	cntcyc[len]++;
	if(hm.containsKey(len)) {
	    hm.get(len).addAll(cyc);
	} else {
	    hm.put(len, cyc);
	}
    }
    boolean end = false;
    for(int i = 1; i < N+1; i++) {
	if(cntcyc[i] % g[i] != 0) end = true;
    }
    if(end) {
	//not possible
	return null;
    } else {
	int[] out = new int[N];
	for(int length = 0; length < N; length++) {
	    if(!hm.containsKey(length)) continue;
	    ArrayList<Integer> p = hm.get(length);
	    int totalsize = p.size();
	    int diffcyc = totalsize / needed[length];
	    for(int i = 0; i < diffcyc; i++) {
		int[] c = new int[needed[length]];
		for(int it = 0; it < needed[length]; it++) {
		    c[it] = p.get(it + i * needed[length]);
		}
		int move = K / (needed[length]/length);
		int[] rewind = new int[needed[length]];
		for(int set = 0; set < needed[length]/length; set++) {
		    int pos = set * length;
		    for(int it = 0; it < length; it++) {
			rewind[pos] = c[it + set * length];
			pos = ((pos - set * length + move) % length)+ set * length;
		    }
		}
		int[] merge = new int[needed[length]];
		for(int it = 0; it < needed[length]/length; it++) {
		    for(int set = 0; set < length; set++) {
			merge[set * needed[length] / length + it] = rewind[it * length + set];
		    }
		}
		for(int it = 0; it < needed[length]; it++) {
		    out[merge[it]] = merge[(it+1) % needed[length]];
		}
	    }
	}
	return out;
    }
}
