import lookingUp.BinarySearchST;
import lookingUp.STEasy;
import org.junit.Test;

import java.util.Iterator;

public class testSTEasy {

    @Test
    public void testPut(){
        STEasy<String, Integer> stSI = new STEasy<>();
        assert  stSI.contains("af");
        assert  stSI.size() == 0;
        stSI.put("af", 4);
        assert  stSI.get("af") == 4;
        stSI.put("af", 10);
        assert  stSI.get("af") == 10;
    }

    @Test
    public void testIterable(){
        STEasy<Integer, String> ST = new STEasy<>();
        ST.put(1, "i");
        ST.put(2, "am");
        ST.put(3, "best");
        Iterator<Integer> stIter = ST.keys();
        //for (int x : stIter)    System.out.print(x + " ");
    }


    @Test
    public void testBinaryST(){
        BinarySearchST<Integer, String> bst = new BinarySearchST<>(15);
        bst.put(1, "a");
        bst.put(2, "i");
        bst.put(9, "o");
        bst.put(4, "h");
        for (int i : bst)   System.out.print(i + " ");

        bst.put(3, "h");
        assert  bst.get(3).equals("h");
    }
}
