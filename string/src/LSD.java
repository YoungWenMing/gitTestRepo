import edu.princeton.cs.algs4.In;

import java.io.File;

public class LSD {

    /**this class is used to sort a sequence of string with same length
     * by assignment of index for each character. Sorting begins from the
     * ends to the very beginning*/

    public static void main(String [] args){

        In in = new In(args[0]);

        String[] strings = new String[Integer.parseInt( in.readLine())];

        for (int i = 0; i < strings.length; i ++)
            strings[i] = in.readLine();

        int R = 256;
        int l = strings[0].length();
        int n = strings.length;

        String[] aux = new String[n];


        System.out.println("before sorting :");
        for (int i = 0; i < n; i ++)
            System.out.print(strings[i] + "    ");

        for (int i = l - 1; i > -1; i --){

            int[]   count = new int[R + 1];     //we must reset count array every time


            /**firstly, get statistics of frequency at right side in order
             * to count the start position in aux array*/
            for (int j = 0; j < n; j ++)
                count[strings[j].charAt(i) + 1] ++;

            /**secondly, calculate the start position of each character*/
            for (int j = 1; j < R; j ++)
                count[j] = count[j] + count[j - 1];

            /**thirdly, put items to their correct positions, and increase every item's
             * next position*/
            for (int j = 0; j < n; j ++)
                aux[count[strings[j].charAt(i)]++] = strings[j];
            /**count[strings[j].charAt(i)] get the position of this item from count array*/

            /**finally, copy all partly sorted items to original array*/
            for (int j = 0; j < n; j ++)
                strings[j] = aux[j];

        }

        System.out.println("\nafter sorting :");
        for (int i = 0; i < n; i ++)
            System.out.print(strings[i]+ "  ");

    }

}
