package Sort;

import edu.princeton.cs.algs4.StdRandom;

public class SelectionSort implements Sorter {

    public SelectionSort(){}

    public void sort(Comparable[] a){
        for (int i = 0, n = a.length; i < n; i ++){
            int min = findMin(a, i);
            Sorter.exch(a, min, i);
        }
    }


    public void visualizeSort(Double[] a){
        SortingVisualizer SV = new SortingVisualizer(a);
        SV.drawColumn(new Integer[]{});
        for (int i = 0, n = a.length; i < n; i ++){
            int min = findMin(a, i);
            SV.drawColumn(new Integer[]{i, min});
            Sorter.exch(a, min, i);
            //SV.drawColumn(new Integer[]{min, i});
        }
    }

    /*
    @Override
    public boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }*/




    /*
    * find the minimum item's position from pos i
    * */
    private int findMin(Comparable[] a, int i){
        Comparable candidate = a[i];
        int pos = i;
        for(int n = a.length ; i < n; i ++ ){
            if(Sorter.less(a[i], candidate)){
                candidate = a[i];
                pos = i;
            }
        }
        return pos;
    }





    /*
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
    */

    public static void main(String args[]){
        Double a[] = new Double[30];
        for(int i = 0; i < a.length; i ++){
            a[i] = StdRandom.uniform(0.0, 20.0);
            System.out.print("  " + a[i]);
        }
        System.out.println("after sorting :");
        SelectionSort sorter = new SelectionSort();
        sorter.visualizeSort(a);

    }

}
