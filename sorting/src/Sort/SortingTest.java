package Sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
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


    public Double[] randomGenerator(int N, double bound){
        Double[] a = new Double[N];
        for(int i = 0; i < N; i ++)
            a[i] = StdRandom.uniform(0, bound);
        return a;
    }


    public static void randomGenerator(int N, double bound, String out){
        Out outTo = new Out(out);
        for (int i = 0; i < N; i ++)
            outTo.println(StdRandom.uniform(0.0, bound));
    }

    public void randomShuffler(Comparable[] a, double rate){
        int len = a.length;
        int number =(int)  rate * len;
        for (int i =0; i < number; i ++){
            int x = StdRandom.uniform(len), y = StdRandom.uniform(len);
            Comparable z = a[x];
            a[x] = a[y];
            a[y] = z;
        }
    }

    public static void main(String[] args){
        //int initScale = Integer.parseInt(args[0]);
        //int times = Integer.parseInt(args[1]);

        SortingTest ST = new SortingTest();
        SelectionSort sorterSelect = new SelectionSort();
        InsertionSort sorterInsert = new InsertionSort();
        HillSort      sorterHill      = new HillSort();

        int N = 100000;

        Integer[] testArray = ST.randomGenerator(N);
        Integer[] testArray2 = testArray.clone(); // ST.randomGenerator(N);
        Integer[] testArray3 = testArray.clone();


        long time1 = ST.testTime(testArray, sorterSelect);
        long time2 = ST.testTime(testArray2, sorterInsert);
        long time3 = ST.testTime(testArray3, sorterHill);

        System.out.println("for totally random array: ");
        System.out.println("SelectionSort for " + N + " random items " + time1 + "  milliseconds");
        System.out.println("InsertionSort for " + N + " random items " + time2 + "  milliseconds");
        System.out.printf("SelectionSort for %d random items %d  milliseconds", N, time3);

        /*
        ST.randomShuffler(testArray, 0.1);
        ST.randomShuffler(testArray2, 0.1);
        time1 = ST.testTime(testArray, sorterSelect);
        time2 = ST.testTime(testArray2, sorterInsert);

        System.out.println("for almost sorted array:");
        System.out.println("SelectionSort for " + N + " random items " + time1 + "  milliseconds");
        System.out.println("InsertionSort for " + N + " random items " + time2 + "  milliseconds");
        */
        /*
        for (int i = 0; i < times; i ++){
            Integer[] X = ST.randomGenerator(initScale);
            long time = ST.testTime(X, sorter);
            System.out.println(initScale + " numbers, sorting costs " + time + "milliseconds with ratio is " + time / (ratioU * 1.0));
            ratioU =  time > 1? time:ratioU;
            initScale *= 2;
        }

        /*
        int N = Integer.parseInt(args[0]);
        double bound = Double.parseDouble(args[1]);
        SortingTest.randomGenerator(N, bound, args[2]);*/
    }


}
