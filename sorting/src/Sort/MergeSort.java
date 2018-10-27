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
        sortWithInsertion(a, 0, a.length - 1);
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

    private static void sortWithInsertion(Comparable[] a, int lo, int hi){
        if (hi - lo <= 15) InsertionSort.sortPartly(a, lo, hi + 1);
        else if(lo >= hi)   return;
        else {
            int mid = lo + (hi - lo) / 2;
            sortWithInsertion(a, mid+ 1, hi);
            sortWithInsertion(a, lo, mid);
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
        System.out.println("before sorting.");
        SortingVisualizer.printArray(toSort);
        //MergeSort.merge(toSort, 0, 2, 5);
        //MergeSort MS = new MergeSort();
        MergeSort.sortBU(toSort);

        System.out.println("after sorting.");
        SortingVisualizer.printArray(toSort);
        */
        Double[] A1 = SortingTest.randomGenerator(80000, 100.0);
        Double[] A2 = A1.clone();

        long start = System.currentTimeMillis();
        MergeSort.sortBUInsertion(A1);
        long time = System.currentTimeMillis();
        System.out.printf("sorting with insertion costs %d milliseconds", (time - start));

        start = System.currentTimeMillis();
        MergeSort.sortBU(A2);
        time  = System.currentTimeMillis();
        System.out.printf("sorting without insertion costs %d milliseconds", (time - start));

    }

}
