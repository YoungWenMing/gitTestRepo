package Sort;

import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdRandom;

import javax.swing.*;

public class SortingTest {

    static final int BOUND = 1000000;


    public long testTime(Comparable[] a, Sorter sorter){
        long start = System.currentTimeMillis();
        sorter.sort(a);
        return System.currentTimeMillis() - start;
    }

    public Integer[] randomGenerator(int N){
        Integer[] a = new Integer[N];
        for (int i =0; i < N; i ++)
            a[i] = StdRandom.uniform(-BOUND, BOUND);
        return a;
    }

    public static void main(String[] args){
        //int initScale = Integer.parseInt(args[0]);
        //int times = Integer.parseInt(args[1]);

        SortingTest ST = new SortingTest();
        SelectionSort sorterSelect = new SelectionSort();
        InsertionSort sorterInsert = new InsertionSort();

        int N = 20000;

        Integer[] testArray = ST.randomGenerator(N);
        Integer[] testArray2 = ST.randomGenerator(N);

        long time1 = ST.testTime(testArray, sorterSelect);
        long time2 = ST.testTime(testArray2, sorterInsert);

        System.out.println("SelectionSort for " + N + " random items " + time1 );
        System.out.println("InsertionSort for " + N + " random items " + time2 );
        /*
        for (int i = 0; i < times; i ++){
            Integer[] X = ST.randomGenerator(initScale);
            long time = ST.testTime(X, sorter);
            System.out.println(initScale + " numbers, sorting costs " + time + "milliseconds with ratio is " + time / (ratioU * 1.0));
            ratioU =  time > 1? time:ratioU;
            initScale *= 2;
        }
        */
    }


}
