package Sort;

import edu.princeton.cs.algs4.StdRandom;

public class InsertionSort implements Sorter {

    @Override
    public boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    @Override
    public void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 1, n = a.length; i < n; i += 1)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    /*
    * go through each item, check whether its left item is samller
    * if so, switch them forward until the condition fails
    * stay otherwise
    * */
    public void sort(Comparable[] a){
        for (int i = 1, n = a.length; i < n; i ++){
            int left = i - 1, handler = i;
            while(left > -1 && less(a[handler], a[left])){
                exch(a, handler, left);
                handler --;
                left --;
            }
        }
    }

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
    }

}
