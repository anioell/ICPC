/* Range Maximum Query
 ** N\log N, 1
 * $process$ processes an array $A$ of length $N$ in O($N\log N$) such that $query$ can compute the maximum value of $A$ in interval $[i,j]$. Therefore $M[a,b]$ stores the maximum value of interval [a,a+2^b-1]\\
 * \emph{Input:} dynamic table $M$, array to search $A$, length $N$ of $A$, start index $i$ and end index $j$\\
 * \emph{Output:} filled dynamic table $M$ or the maximum value of $A$ in interval [i,j]
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Erase {
//START
public static void process(int[][] M, int[] A, int N) {
	// init
	for(int i = 0; i < N; i++)
		M[i][0] = i;
	// filling table M
    // M[i][j] = max( M[i][j-1], M[i+(1<<(j-1))][j-1] ),
	// da [i,i+2^j-1] = [i,i+2^(j-1)-1] u [i+2^(i-1), (i+2^(i-1))+2^(j-1)-1]
	for(int j = 1; 1 << j <= N; j++) {
		for(int i = 0; i + (1 << j) - 1 < N; i++) {
			if(A[M[i][j-1]] >= A[M[i+(1 << (j-1))][j-1]])
				M[i][j] = M[i][j-1];
			else
				M[i][j] = M[i + (1 << (j-1))][j-1];
		}
	}
}

public static int query(int[][] M, int[] A, int N, int i, int j) {
	int k = (int) (Math.log(j - i + 1) / Math.log(2)); // k = |_ log_2(j-i+1) _|
	if(A[M[i][k]] >= A[M[j- (1 << k) + 1][k]])
		return M[i][k];
	else
		return M[j - (1 << k) + 1][k];
}
//END
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringBuilder sb = new StringBuilder();
            String fin = br.readLine();
            String[] FIN = fin.split(" ");
            int N = Integer.parseInt(FIN[0]);
            int D = Integer.parseInt(FIN[1]);
            if(N == 0 && D == 0) {
                break;
            }
            String in = br.readLine();
            int[] nums = new int[in.length()];
            for(int i = 0; i < in.length(); i++) {
                nums[i] = Integer.parseInt(Character.toString(in.charAt(i)));
            }
            int size = (int) (Math.log(N)/Math.log(2)) + 1;
            int[][] M = new int[N][size];
            process(M, nums, N);
            int startind = 0;
            while(D > 0 && startind + D < N) {
                int max = query(M, nums, N, startind, startind+D);
                sb.append(nums[max]);
                D -= max - startind;
                startind = max+1;
            }
            for(int i = startind; i < N && N - i > D; i++) {
                sb.append(nums[i]);
            }
            System.out.println(sb.toString());
        }
    }

}
