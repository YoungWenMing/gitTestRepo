package Sort;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;

public class MergeSort implements Sorter {

    private static Comparable[] temp;



    @Override
    public void sort(Comparable[] a) {
        MergeSort.sortBU(a);
    }

    public static void sortBU(Comparable[] a){
        temp = new Comparable[a.length];
        sortBU(a, 0, a.length - 1);
    }

    public static void sortBUInsertion(Comparable[] a){
        temp = new Comparable[a.length];
        sortBUInsertion(a, 0, a.length - 1);
    }

    public static void sortBottomToUp(Comparable[] a){
        int len = a.length;
        temp = new Comparable[len];
        int step = 1;
        while (step < len){
            for (int i = 0;i < len;i += 2 * step){
                int mid = i + step - 1;
                if (mid >= len - 1)     continue;       //if the first part to merge is out of range, just continue
                int j = mid + step >= len? len - 1: mid + step;
                merge(a, i, mid, j);
            }
            step *= 2;
        }
    }

    /*
    * recursively merge the sorted subordinate arrays
    * if lo == hi, that means the subarray is of 1 capacity
    * else, sort the two subarrays and then merge them
    * */
    private static void sortBU(Comparable[] a, int lo, int hi){
        if (lo >= hi)   return;
        else{
            int mid = lo + (hi - lo) / 2;
            sortBU(a, mid+ 1, hi);
            sortBU(a, lo, mid);
            merge(a, lo, mid, hi);
        }
    }

    /*
    * to take advantage of insertion sorting
    * we apply insertion sort to small pieces of the total array
    * */

    private static void sortBUInsertion(Comparable[] a, int lo, int hi){
        if (hi - lo <= 15) InsertionSort.sortPartly(a, lo, hi + 1);
        else if(lo >= hi)   return;
        else {
            int mid = lo + (hi - lo) / 2;
            sortBUInsertion(a, mid+ 1, hi);
            sortBUInsertion(a, lo, mid);
            merge(a, lo, mid, hi);
        }
    }


    /*
    * make a duplicate subarray from lo to hi of a
    * and then merge
    * */
    public static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo, j = mid  + 1;

        for (int k = lo; k <= hi; k ++)
            temp[k] = a[k];

        for (int k = lo; k <= hi; k ++){
            if (i > mid)    a[k] = temp[j++];
            else if (j > hi)    a[k] = temp[i++];
            else if (Sorter.less(temp[i], temp[j]))     a[k] = temp[i++];
            else a[k] = temp[j++];
        }

    }

    public static void main(String[] args){
        /*
        Double[] toSort = new Double[]{1.0, 3.0, 5.0, 2.0, 4.0, 6.0};
        Double[] toSort2 = new Double[]{1.0};

        //System.out.println("before sorting.");
        //SortingVisualizer.printArray(toSort);
        //MergeSort.merge(toSort, 0, 2, 5);
        //MergeSort MS = new MergeSort();
        MergeSort.sortBottomToUp(toSort2);

        System.out.println("after sorting.");
        for (double x : toSort2)     System.out.print(x + " ");
        */

        Double[] A1 = SortingTest.randomGenerator(40000, 100.0);
        Double[] A2 = A1.clone();
        Double[] A3 = A1.clone();

        long start = System.currentTimeMillis();
        MergeSort.sortBUInsertion(A1);
        long time = System.currentTimeMillis();
        System.out.printf("sorting with insertion costs %d milliseconds", (time - start));

        start = System.currentTimeMillis();
        MergeSort.sortBU(A2);
        time  = System.currentTimeMillis();
        System.out.printf("sorting without insertion costs %d milliseconds", (time - start));

        start = System.currentTimeMillis();
        MergeSort.sortBottomToUp(A3);
        time  = System.currentTimeMillis();
        System.out.printf("sorting without insertion from bottom to up costs %d milliseconds", (time - start));
    }

}
