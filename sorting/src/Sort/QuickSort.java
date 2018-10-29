package Sort;

/*
* implementation of quickSort
* the key step of quick sort is to do partition correctly
* partition operation pick a item and make it the standard
* upon which we split the array into two parts, one contains all bigger than
* or equal to that item, another contains the rest.
* after one partition, we settle one item at correct position.
* */

import com.sun.nio.sctp.AbstractNotificationHandler;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Quick;

public class QuickSort implements Sorter {

    static final int M = 10;    //the threshold for small array to apply insertion sort

    @Override
    public void sort(Comparable[] a) {
        QuickSort.quickSort(a, 0, a.length - 1);
    }

    /*
    * recursive function to deal with the left part and the right part
    * given that the middle position is already correctly settled
    * */
    public static void quickSort(Comparable[] a, int start, int end){

        if (end <= start + M){
            InsertionSort.sortPartly(a, start, end);        // when the part which is not sorted yet, apply insertionSort
            return;
            /*mistake corrected here, return missed in this block may cause -1 as array index
            * because of the "splitPos - 1"*/
        }
        int splitPos = partition(a, start, end);
        quickSort(a, start, splitPos - 1);
        quickSort(a, splitPos + 1, end);
    }

    /**
     * another quick sort method by using 3-way partition
     * */
    public static void quickSort3(Comparable[] a, int start, int end){
        if (end <= start + M){
            InsertionSort.sortPartly(a, start, end);
            return;
        }
        int[] X = partition3(a, start, end);
        quickSort3(a, start, X[0] - 1);
        quickSort3(a, X[1] + 1, end);
    }

    /*
    * move the two pointer until they cross or stay at same position,
    * swap items which x & y stay, settle the standard item and return its position.
    * //
    * in most cases, x & y do not stop at same position,
    * y will exceed x for one step, because it must stop when getting
    * a item which is smaller than the standard.
    * //
    * note that they may not cross on some special cases
    * for example 5, 1, 2, 3
    * x goes from 1 to 3, and stopped
    * y stay on 3 and never move forward
    * so x == y in such case, we also need to break the loop
    * the program may throw outOfRange exception
    * */
    private static int partition(Comparable[] a, int start, int end){
        Comparable item = a[start];
        int x = start , y = end + 1;     //the two pointers
        while (true){
            while (Sorter.less(a[++x], item))     if (x == end) break;
            while (Sorter.less(item, a[--y]))     if (y == start) break; //redundant statement
            if (x >= y)      break;
            /*mistake corrected here, when x equals to y, we also need to break
            * */
            Sorter.exch(a, x, y);
        }
        Sorter.exch(a, start, y);
        return y;
    }



    /*
    * different method to do partitions --- split the array into 3 part
    * the additional part is items equals to the goal
    * to overcome the slow convergence of binary split with many duplicate elements
    * */
    private static int[] partition3(Comparable[] a, int x, int y){
        Comparable v = a[x];
        int left = x, i = x, right = y;
        while (i<= right){
            int z = a[i].compareTo(v);
            if (z == 0)     i ++;
            else if (z <0){
                Sorter.exch(a, i, left);
                left ++;
                i ++;
            }else {
                Sorter.exch(a, i, right);
                right --;
            }
        }
        return new int[]{left, right};
    }


    public static void main(String[] args){

        Integer[] toSort = SortingTest.randomGenerator(1000000, 10000);
        Integer[] toSort2 = toSort.clone();

        long start = System.currentTimeMillis();
        QuickSort.quickSort(toSort, 0, toSort.length - 1);
        long time  = System.currentTimeMillis();
        System.out.printf("binary partition time consumption: %d\n", (time - start));

        start = System.currentTimeMillis();
        QuickSort.quickSort3(toSort2, 0, toSort.length - 1);
        time  = System.currentTimeMillis();
        System.out.printf("trible partition time consumption: %d\n", (time - start));

        /*
        long time = SortingTest.testTime(toSort, new QuickSort());
        System.out.println("after sorting. " + time);

        time = SortingTest.testTime(toSort, new MergeSort());
        System.out.println("after sorting. " + time);*/
        //for (double x : toSort)     System.out.print(x + " ");

        /*
        Integer toSort[] = new Integer[]{4, 3, 7, 4, 5, 6};
        int[] a = QuickSort.partition3(toSort, 0, 4);
        assert a[0] == 1 && a[1] == 2;
        for (int x : toSort)    System.out.println(x);
        */
    }
}
