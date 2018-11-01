package lookingUp;

import java.util.Iterator;

public class STEasy<Key extends Comparable<Key>, Value> implements Iterable<Key>{
    /*
    * this is a easy implementation of searching table
    * we apply linked list here, which means that the get
    * & put operations all need to go through the whole
    * array, most methods are of N time complexity
    * */

    int size;
    Node root;

    public STEasy(){
        size = 0;
        root = new Node(null, null, null);      //Node root is a sentinel
    }

    public class Node{
        Key k;
        Value v;
        Node next;

        public Node(Key k, Value v, Node next){
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

    /*
    * put key-value pair into this table
    * go through the key list to find this key's position
    * if it already exists, update its value
    * create new Node and insert into the table other wise
    * */
    /*
    public void put(Key key, Value value){
        if (key == null)    throw new IllegalArgumentException("key can not be null!");
        Node prev = root;
        Node probe = root.next;
        while (probe != null && probe.k.compareTo(key) < 0) {
            prev = probe;
            probe = probe.next;
        }
        if (probe != null && probe.k.compareTo(key) == 0)
            probe.v = value;
        else prev.next = new Node(key, value, probe);
    }*/

    /*
    * the list has no order, go through and put the pair in to it
    * */
    public void put(Key key, Value value){
        for (Node probe = root.next; probe != null; probe = probe.next)
            if (probe.k.compareTo(key) == 0){
                probe.v = value;
                return;
            }
        Node X = new Node(key, value, root.next);
        root.next = X;
        size ++;
    }

    /*
    * go through the list to find the value according to key
    * if this key does not exist, return null
    * */
    public Value get(Key key){
        if (key == null)    throw new IllegalArgumentException("key can not be null!");
        Node probe = root.next;
        while (probe != null){
            if (probe.k.compareTo(key) == 0)    return probe.v;
            probe = probe.next;
        }
        return null;
    }

    /*
    * go through the list to find the node with this key
    * leave it alone, no pointer points to it anymore
    * */
    public void delete(Key key){
        if (key == null)    throw new IllegalArgumentException("key can not be null!");
        for (Node probe = root.next, prev = root;probe!=null;prev = probe, probe = probe.next){
            if (probe.k.compareTo(key) == 0){
                prev.next = probe.next;
                size ++;
                return;
            }
        }
    }

    /*
    * apply get method
    * if this key exist, the get method return a Value
    * */
    public boolean contains(Key key){
        return ! (get(key) == null);
    }

    /*
     * return the size of this table
     * */
    public int size(){
        return size;
    }

    public Key min(){
        if (size == 0)      return null;
        Node probe = root.next;
        Key k = probe.k;
        for (;probe != null; probe = probe.next)
            if (k.compareTo(probe.k)>0)     k = probe.k;
        return k;
    }

    public Key max(){
        if (size == 0)      return null;
        Node probe = root.next;
        Key k = probe.k;
        for (;probe != null; probe = probe.next)
            if (k.compareTo(probe.k)<0)     k = probe.k;
        return k;
    }


    public Iterator<Key> keys(){
        return new keysIterator();
    }


    public Iterator<Key> iterator(){
        return new keysIterator();
    }

    public static void main(String[] args){

    }



    private class keysIterator implements Iterator<Key>{

        Node pointer = root.next;

        @Override
        public boolean hasNext() {
            return !(pointer == null);
        }

        @Override
        public Key next() {
            Key X = pointer.k;
            pointer = pointer.next;
            return X;
        }
    }

}