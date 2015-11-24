/* Binomial Coefficient
 ** k
 * Gives binomial coefficient (n choose k)
 */
 
//START
public static long bin(int n, int k) {
	if (k == 0) {
		return 1;
	} else if (k > n/2) {
		return bin(n, n-k);
	} else {
		return n*bin(n-1, k-1)/k;
	}
}
//END
