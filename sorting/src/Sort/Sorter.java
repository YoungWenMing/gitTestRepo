package Sort;

public interface Sorter {

    void sort(Comparable[] a);

    /*
    * return whether v is less than w
    * */
    //boolean less(Comparable v, Comparable w);

    /*
    * exchange two items at position a & b
    * */
    static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    /*
    * check whether the array is sorted
    * */
    static boolean isSorted(Comparable[] a) {
        for (int i = 1, n = a.length; i < n; i += 1)
            if (Sorter.less(a[i], a[i - 1])) return false;
        return true;
    }

    static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

}
