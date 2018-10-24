package Sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class InsertionSort implements Sorter {

    /*
    * go through each item, check whether its left item is samller
    * if so, switch them forward until the condition fails
    * stay otherwise
    * */
    public void sort(Comparable[] a){
        for (int i = 1, n = a.length; i < n; i ++){
            int left = i - 1, handler = i;
            while(left > -1 && Sorter.less(a[handler], a[left])){
                Sorter.exch(a, handler, left);
                handler --;
                left --;
            }
        }
    }


    public void visualizedSort(Double[] a){
        SortingVisualizer SV = new SortingVisualizer(a);
        SV.drawColumn(new Integer[]{});
        for (int i = 1, n = a.length; i < n; i ++){
            int left = i - 1, handler = i;

            while(left > -1 && Sorter.less(a[handler], a[left])){
                Sorter.exch(a, handler, left);
                SV.drawColumn(new Integer[]{left});
                handler --;
                left --;
            }
        }
    }

    /*
    public static void main(String args[]){
        Integer a[] = new Integer[20];
        for(int i = 0; i < 20; i ++){
            a[i] = StdRandom.uniform(-100, 100);
            System.out.print("  " + a[i]);
        }
        System.out.println("after sorting :");
        //SelectionSort sorter = new SelectionSort();
        InsertionSort sorter = new InsertionSort();
        sorter.sort(a);
        for (int x : a)
            System.out.print("  " + x);
    }*/

    public static void main(String args[]) {
        Double a[] = new Double[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(0, 50.0);
            System.out.print("  " + a[i]);
        }
        System.out.println("after sorting :");
        //SelectionSort sorter = new SelectionSort();
        InsertionSort sorter = new InsertionSort();
        sorter.visualizedSort(a);
    }

}
