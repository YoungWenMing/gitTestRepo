import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;

public class MSD {

    //private String[] a;     //storage all strings
    private static String[] aux;
    private static int R = 256;
    private static int M = 2;

    public static void  main(String[] args){
        In in = new In(args[0]);
        int n = in.readInt();
        String[] s = new String[n];

        System.out.println("all strings before sorted:");
        for (;n > 0; ) {
            s[--n] = in.readLine();
            System.out.println(s[n]);
        }

        sort(s);
        System.out.println("all strings after sorted:");
        for (int i = 0; i < s.length; i +=1)
            System.out.println(s[i]);
    }

    public static void sort(String[] a){
        int N = a.length;
        aux = new  String[N];
        sort(a, 0, N -1, 0);
    }


    /**string array a is of all strings to be sorted,
     * lo is the start position and hi the end position for sorting
     * d is the digit numbers based on which to sort
     * */
    private static void sort(String[] a, int lo, int hi, int d) {
        if (lo + M >= hi) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int[] count = new int[R + 2];       //we need 2 extra position to storage those string with length of d and rightwards move

        /**do statistics about each character*/
        for (int i = lo; i <= hi; i++) {
                int p = charAt(a[i], d);        //if d is illegal index, -1 will be returned
                count[p + 2]++;
            }
            /**count every character's starting position in aux*/
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }

            /**put every string in aux temporarily */
            for (int i = lo; i <= hi; i++) {
                int p = count[charAt(a[i], d) + 1]++;      //a string's position is at pos one step leftwards
                aux[p] = a[i];
            }

            /**move partly sorted strings to a*/
            for (int i = lo; i <= hi; i++) {
                a[i] = aux[i - lo];         //note that aux start at 0, because we only count a fragment of all strings each round
            }

            /**recursively call this process on all groups divided by characters on the dth position*/
            for (int r = 0; r < R; r++) {
                sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
            }

        }

        private static int charAt (String s,int d){
            return s.length() <= d ? -1 : s.charAt(d);
        }

}