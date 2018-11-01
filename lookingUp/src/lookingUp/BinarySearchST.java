package lookingUp;

import javax.swing.*;
import java.util.Iterator;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    int size;
    Key[] keys;
    Value[] values;

    public BinarySearchST(int max){
        keys = (Key[]) new Comparable[max];
        values =(Value[]) new Object[max];
        size = 0;
    }

    /*
    * search a key by binary searching
    * */
    public Value get(Key key){
        if (isEmpty())  return null;
        int r = rank(key);
        if (r < size && key.equals(keys[r]))    return values[r];
        return null;
        /*
        int hi = size - 1, lo = 0;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int indi = keys[mid].compareTo(key);
            if (indi == 0)  return values[mid];
            else if (indi < 0)  lo = mid + 1;
            else                hi = mid - 1;
        }
        return null;
        */
    }

    /*
    * put key-value pair into the table
    * the loop is to make it clear that, the key exists or not by using
    * binary searching
    * if not exist, the loop give us this key's proper position in the table
    * move all key-value pairs backward for one step
    * */
    public void put(Key key, Value value){
        int r = rank(key);
        if (key.equals(keys[r]))    values[r] = value;
        else{
            moveOneStep(r);
            keys[r] = key; values[r] = value;
            size ++;
        }
        /*
        int hi = size - 1, lo = 0;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int indi = keys[mid].compareTo(key);
            if (indi == 0){
                values[mid] = value;
                size ++;
                return;
            }
            else if (indi < 0)  lo = mid + 1;
            else                hi = mid - 1;
        }
        moveOneStep(lo);
        keys[lo] = key;
        values[lo] = value;
        size ++;*/
    }

    /*
    * return the rank of this key in the key array
    * if it does not exist, return its bigger neighbor's position
    * note that this method may give back size itself
    * */
    public int rank(Key key){
        int hi = size - 1, lo = 0;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int indi = keys[mid].compareTo(key);
            if (indi == 0)  return mid;
            else if (indi < 0)  lo = mid + 1;
            else                hi = mid - 1;
        }
        return lo;
    }

    /*
    * move those pairs behind position i for one step
    * */
    private void moveOneStep(int i){
        if (size + 1 == keys.length)    throw new NullPointerException("no enough capacity!");
        for (int j = size; j >i;j --){
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Iterator<Key> iterator(){
        return new keysIter();
    }

    private class keysIter implements Iterator<Key>{
        int pos;

        @Override
        public Key next() {
            return keys[pos++];
        }

        @Override
        public boolean hasNext() {
            return pos<size;
        }
    }
}
