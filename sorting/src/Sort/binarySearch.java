package Sort;

public class binarySearch <T extends Comparable> {

    public int find(T[] a, T goal ){
        int hi = a.length - 1;
        int lo = 0;
        while (hi >= lo){
            int mid = lo + (hi - lo) / 2;
            if(a[mid].compareTo(goal) > 0)    hi = mid - 1;
            else if(a[mid].compareTo(goal) < 0)   lo = mid  + 1;
            else return mid;
        }
        return -1;
    }




}
