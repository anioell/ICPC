/* LongestIncreasingSubsequence
 ** n^2
 * Computes the length of the longest increasing subsequence and is easy to be adapted.\\
 * \emph{Input:} array $arr$ containig a sequence of length $N$\\
 * \emph{Output:} lenght of the longest increasing subsequence in $arr$
 */

//START
// This has not been tested yet
// (adapted from tested C++ Murcia Code)
public static int LISeasy(int[] arr, int N) {
	int[] m = new int[N];
	for (int i = N - 1; i >= 0; i--) {
		m[i] = 1; //init table
		for (int j = i + 1; j < N; j++) {
			// if arr[i] increases the length
			// of subsequence from array[j]
			if (arr[j] > arr[i])
				if (m[i] < m[j] + 1)
					// store lenght of new subseq
					m[i] = m[j] + 1;
		}
	}
	// find max in array
	int longest = 0;
	for (int i = 0; i < N; i++) {
		if (m[i] > longest)
			longest = m[i];
	}
	return longest;
}
//END