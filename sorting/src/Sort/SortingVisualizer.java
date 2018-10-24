package Sort;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.concurrent.Callable;

public class SortingVisualizer {

    static final Color normal = StdDraw.GRAY;
    static final Color special = StdDraw.BOOK_RED;
    static final double xExtreme = 100;
    private static final binarySearch searcher = new binarySearch();

    private Double[] originArray;
    private double width, unitWidth, start;
    private int StayTime = 500;
    private int canvasXScale = 800, canvasYScale = 600;

    /*
    * initialize the canvas, set parameters including canvas size, column width and relative position on the canvas
    * */
    public SortingVisualizer(Double[] a){
        originArray = a;
        double max = extremeOfArray(a)[1];
        width = xExtreme / a.length * 0.9;
        unitWidth = width * 0.8;
        start = xExtreme * 0.05;

        StdDraw.setCanvasSize(canvasXScale, canvasYScale);
        StdDraw.setXscale(0, xExtreme);
        StdDraw.setYscale(- max * 0.1, max * 1.1);

    }

    public SortingVisualizer(Double[] a, int stayTime, int xs, int ys){
        this(a);
        this.StayTime = stayTime;
        canvasXScale = xs;
        canvasYScale = ys;
    }

    /*
    * the specialPos array stores those items has been just checked in the latest exchange operation
    * */
    public void drawColumn(Integer[] specialPos){
        StdDraw.setPenColor(normal);
        for (int i = 0, n = originArray.length; i < n; i++){
            double posX = start + i * width + unitWidth / 2;
            double posY = originArray[i] / 2;
            if (searcher.find(specialPos, i) > -1)
                StdDraw.setPenColor(special);
            StdDraw.filledRectangle(posX, posY, unitWidth / 2, posY);
            StdDraw.setPenColor(normal);
        }
        StdDraw.show(StayTime);
        StdDraw.clear(Color.WHITE);
    }

    /*
    * find the maximum and minimum items in the array
    * */
    static private Double[] extremeOfArray(Double[] a){
        Double max = a[0], min = a[0];
        for (Double x : a) {
            if (max.compareTo(x) < 0) max = x;
            if (min.compareTo(x) > 0) min = x;
        }
        return new Double[]{min, max};
    }

    public static void main(String[] args){
        //Double[] arrayD = new Double[]{1.0, 3.0, 4.0, 2.0, 10.0, 11.0, 1.6, 2.7, 3.9, 9.4, 5.9, 6.8, 7.0};
        //SortingVisualizer V = new SortingVisualizer(arrayD);
        //V.drawColumn(new Integer[]{1, 3});
        int n = Integer.parseInt(args[0]);
        Double arrayD[] = new Double[n];
        for (int i = 0; i < n; i ++)
            arrayD[i] = StdRandom.uniform();

        //InsertionSort sorterInsertion = new InsertionSort();
        //sorterInsertion.visualizedSort(arrayD);
        SelectionSort ST = new SelectionSort();
        InsertionSort IST = new InsertionSort();
        //ST.visualizeSort(arrayD);
        IST.visualizedSort(arrayD);
    }
}
