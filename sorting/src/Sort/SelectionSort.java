package Sort;

import edu.princeton.cs.algs4.StdRandom;

public class SelectionSort implements Sorter {

    public SelectionSort(){}

    public void sort(Comparable[] a){
        for (int i = 0, n = a.length; i < n; i ++){
            int min = findMin(a, i);
            exch(a, min, i);
        }
    }

    @Override
    public boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }


    /*
    * find the minimum item's position from pos i
    * */
    private int findMin(Comparable[] a, int i){
        Comparable candidate = a[i];
        int pos = i;
        for(int n = a.length ; i < n; i ++ ){
            if(less(a[i], candidate)){
                candidate = a[i];
                pos = i;
            }
        }
        return pos;
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


    public static void main(String args[]){
        Integer a[] = new Integer[10];
        for(int i = 0; i < 10; i ++){
            a[i] = StdRandom.uniform(-100, 100);
            System.out.print("  " + a[i]);
        }
        System.out.println("after sorting :");
        SelectionSort sorter = new SelectionSort();
        sorter.sort(a);
        for (int x : a)
            System.out.print("  " + x);
    }
}
