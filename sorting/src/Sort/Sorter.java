package Sort;

public interface Sorter {

    void sort(Comparable[] a);

    /*
    * return whether v is less than w
    * */
    boolean less(Comparable v, Comparable w);

    /*
    * exchange two items at position a & b
    * */
    void exch(Comparable[] a, int i, int j);

    /*
    * check whether the array is sorted
    * */
    boolean isSorted(Comparable[] a);

}
