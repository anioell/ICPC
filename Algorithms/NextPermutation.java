/* NextPermutation
 * n
 * Returns true if there is another permutation. Can also be used to compute the nextPermutation of an array.
 */
class Main {
  public static void main(String[] args) {
  }

//START
public static boolean nextPermutation(char[] a) {
   int i = a.length - 1;
   while(i > 0 && a[i-1] >= a[i]) {
      i--;
   }
   if(i <= 0) {
      return false;
   }
   int j = a.length - 1;
   while (a[j] <=  a[i-1]) {
      j--;
   }
   char tmp = a[i - 1];
   a[i - 1] = a[j];
   a[j] = tmp;

   j = a.length - 1;
   while(i < j) {
      tmp = a[i];
      a[i] = a[j];
      a[j] = tmp;
      i++;
      j--;
   }
   return true;
}
//END

}
