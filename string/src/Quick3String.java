import edu.princeton.cs.algs4.In;

public class Quick3String {

    private static int charAt (String s,int d){
        return s.length() <= d ? -1 : s.charAt(d);
    }

    public static void sort(String[] a){
        sort(a, 0, a.length - 1, 0);
    }


    /**both lo and hi are inclusive*/
    private static void sort(String[] a, int lo, int hi, int d){
        if (lo >= hi)       return;
        int v = charAt(a[lo], d);
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        while (i <= gt){
            int x = charAt(a[i], d);
            /*put the bigger one right side*/
            if (x > v)  exchange(a, i, gt--);
            /*put the smaller one left side, keep lt point to the very left v*/
            else if (x < v)  exchange(a, lt++, i++);
            /*just move i if x equals to v*/
            else            i++;
        }

        /**note that for those smaller than v, they are still unsorted at digit d, so do not increase d by 1*/
        sort(a, lo, lt -1, d);
        /**if v == -1, a[lo] has digits less than or equal to d, they have been actually sorted
         * so just neglect them*/
        if (v > -1)     sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    private static void exchange(String[] a, int x, int y){
        String z = a[x];
        a[x] = a[y];
        a[y] = z;
    }


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
}
