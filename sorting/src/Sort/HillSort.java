package Sort;



public class HillSort implements Sorter {

    public void sort(Comparable[] a){
        int N = a.length, h = 1;
        while(h < N / 3)    h = h * 3 + 1;
        while( h >= 1){
            for (int i = h; i < N ; i ++){
                for (int j = i; j >= h && Sorter.less(a[j], a[j - h]) ; j -= h)
                    Sorter.exch(a, j, j - h);
            }
            h /= 3;
        }
    }


    public void visualizeSort(Double[] a, int stayTime, int xScale, int yScale){
        SortingVisualizer SV = new SortingVisualizer(a, stayTime, xScale, yScale);
        int N = a.length, h = 1;
        while(h < N / 3)    h = h * 3 + 1;
        while( h >= 1){
            for (int i = h; i < N ; i ++){
                for (int j = i; j >= h && Sorter.less(a[j], a[j - h]) ; j -= h) {
                    Sorter.exch(a, j, j - h);
                    SV.drawColumn(new Integer[]{j - h, j});
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args){

        SortingTest ST = new SortingTest();
        Double[] array = ST.randomGenerator(Integer.parseInt(args[0]), Double.parseDouble(args[1]));
        HillSort hs = new HillSort();
        hs.visualizeSort(array, 100, 1200, 600);

        /*
        long time = ST.testTime(array, new HillSort());
        System.out.printf("for %d sized array, HillSort cost %d milliseconds \n", array.length, time);
        assert Sorter.isSorted(array);*/
    }

}
