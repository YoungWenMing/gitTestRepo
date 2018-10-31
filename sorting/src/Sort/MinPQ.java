package Sort;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinPQ<Key extends Comparable<Key>> {

    int size = 0;
    int maxCapacity;
    private Key[] pq;

    /*
    * by choosing appropriate comparator, we can make
    * minPQ or MaxPQ
    * */
    public MinPQ(int max){
        maxCapacity = max;
        pq = (Key[]) new Comparable[max + 1];
    }

    public MinPQ(Key[] a){
        this(a.length);
        for (Key x : a)     insert(x);
    }

    /*
    * insert a item into the queue, make it swim
    * until a proper and correct position
    * */
    public void insert(Key a){
        pq[++size] = a;         //note size points to the last item
        swim(size);
    }

    /*
    * get the first item of the priority queue
    * */
    public Key first(){
        if (isEmpty())  throw new NoSuchElementException("the queue is empty!");
        return pq[1];
    }

    /**
     * delete the first item and return it
     *
     *
     */
    public Key delFirst(){
        if (isEmpty())  throw new NoSuchElementException("the queue is empty!");
        Key a = pq[1];
        exch(1, size);
        pq[size--] = null;
        sink(1);
        return a;
    }


    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    /*
    * compare item to its parent and exchange them if needed
    * do this until, its parent is bigger than itself
    * */
    private void swim(int i){
        while (i > 1 && less(i, i / 2)){
            exch(i, i/2);
            i /= 2;
        }
    }

    /*
    * put the item down if its children are smaller than it
    * */
    private void sink(int i){

        //MinPQ.sink(pq, i, size);

        while (2 * i <= size){
            int j = 2 * i;
            if (j < size && less(j + 1, j))    j ++;   //if i have 2 children and pick the smaller one of them
            if (less(i, j))     break;                  //if the smaller one is bigger than i, then break, i gets it proper position
            exch(i, j);                                 //exchange i and its smaller child
            i = j;                                      //update i and keep the loop going
        }
    }

    /*
    * static version of sink
    * */
    public static void sink(Comparable[] a, int i, int end){    //end is the last item we concern, inclusive!
        while (2 * i + 1 <= end){
            int j = 2 * i + 1;          //different from sink, because common array start from position 0, we must use 2*i + 1 to get first child
            if (j < end && less(a,j + 1, j))    j ++;   //if i have 2 children and pick the smaller one of them
            if (less(a, i, j))     break;                  //if the smaller one is bigger than i, then break, i gets it proper position
            exch(a, i, j);                                 //exchange i and its smaller child
            i = j;                                      //update i and keep the loop going
        }
    }


    private void exch(int i, int j){
        Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;
    }

    /*
    * static version of exchange*/
    private static void exch(Comparable[] pq, int i, int j){
        Comparable t = pq[i]; pq[i] = pq[j]; pq[j] = t;
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    /*
    * static version of less*/
    private static boolean less(Comparable[] a, int i, int j){
        return a[i].compareTo(a[j]) < 0;
    }

    /*
    * implementation of heapSort method with sink and exch function here
    * note that this is a minPQ, just in order to implement the actual
    * method, array of descending order will be returned
    * */
    public static void heapSort(Comparable[] a){
        int size = a.length - 1;
        for (int k = size / 2; k >= 0; k --)
            sink(a, k, size);
        while (size >= 1){
            exch(a, 0, size--);         //note that if size = 1 and continue the loop, sink will be trapped int infinite loop
            sink(a, 0, size);
        }
    }
    //we got problems with heapSort
    //because we left an empty unit in heap's array
    //how to calculate the relationship between parent and children
    //solved by consider the new relationship


    public void printState(){
        for (Comparable x : pq)
            System.out.print(x + " ");
    }

    public static void main(String[] args){
        /*
        MinPQ<Integer> pq = new MinPQ<>(10);
        assert pq.isEmpty();

        pq.insert(10);
        pq.insert(1);
        assert pq.first() == 1;

        pq.insert(17);
        pq.insert(12);
        pq.insert(4);

        while (!pq.isEmpty())
            System.out.print(pq.delFirst() + " ");
        */
        //pq.delFirst();

        Integer[] X = SortingTest.randomGenerator(15, 100);
        MinPQ.heapSort(X);
        for (int x : X)     System.out.print(x + " ");


    }


}
