package lookingUp;

import edu.princeton.cs.algs4.Quick;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    public BST(){}

    public BST(Key[] keys, Value[] values){
        //this();
        if (keys.length!=values.length)     throw new IllegalArgumentException("incompatible arrays!");
        for (int i = 0; i < keys.length; i++)
            put(keys[i], values[i]);
    }

    /*
    * class to store informations
    * */
    private class Node{
        Key key;
        Value value;
        Node left, right;
        int N;

        public Node(Key key, Value value, int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    /*
    * return the corresponding value of the key
    * for that private method, do not indirectly return a Node
    * to avoid unnecessary nullPointer check
    * */
    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node X, Key key){
        if (X == null)  return null;
        int x = key.compareTo(X.key);
        if (x == 0)     return X.value;
        else if (x > 0) return get(X.right, key);
        else            return get(X.left, key);
    }

    /*
    * put the key-value pair into the tree
    * */
    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    private Node put(Node X, Key key, Value value){
        if (X == null)      return new Node(key, value, 1);     //get the empty branch, create a new node
        int x = key.compareTo(X.key);
        if (x == 0)     X.value = value;
        else if (x > 0) X.right = put(X.right, key, value);         //turn to right
        else            X.left = put(X.left, key, value);           //turn to left
        X.N = size(X.left) + size(X.right) + 1;                     //update the size
        return X;
    }

    public int size(){
        return size(root);
    }

    private int size(Node node){
        if (node == null)   return 0;
        else                return node.N;
    }

    /*
    * iteratively implement the max function
    * reach the most right position
    * */
    public Key max(){
        if (size() == 0)    return null;
        return max(root).key;
    }

    private Node max(Node node){
        if (node == null)   return null;
        while (node.right != null)
            node = node.right;
        return node;
    }


    public Key min(){
        if (root == null)   return null;
        return min(root).key;
    }

    private Node min(Node node){
        if (node == null)   return null;
        while (node.left != null)
            node = node.left;
        return node;
    }

    /*
    * return the biggest key in the list which equals or is less than this key
    * */
    public Key floor(Key key){
        return floor(key, root);
    }

    /*
    * if we get the perfectly same key, return it
    * if the key is less than current key, search the left side
    * if the key is bigger than current key, search the right side
    *       and return the outcome if it is not null or the current key
    *       otherwise
    * */
    private Key floor(Key key, Node node){
        if (node == null)   return null;
        //Key answer = null;
        int i = key.compareTo(node.key);
        if (i == 0)     return key;
        if (i < 0)      return floor(key, node.left);
        else {
            Key answer = floor(key, node.right);
            return answer == null? node.key:answer;
        }
    }

    /*
    * find the smallest key that is bigger than or equals to this key
    *
    **/
    public Key ceiling(Key key){
        Node probe = root;
        Key turn = null;                            //to record the latest key which is bigger than key.
        while (probe != null){
            int i = key.compareTo(probe.key);
            if (i == 0)     return key;
            else if (i > 0) probe = probe.right;    //if current is smaller than this key, the answer can only be in the right or even do not exist
            else {                                  //if current is bigger than key, the answer maybe current or another key in the left
                turn = probe.key;
                probe = probe.left;
            }
        }
        return turn;
    }


    /*
    * get the key which ranks num
    * that is to say, there are num keys in front
    * */
    public Key select(int num){
        if (num<0)  throw new IllegalArgumentException("num must be positive!");
        return select(num, root);
    }

    private Key select(int num, Node node){
        if (node == null)   return null;
        int i = size(node.left);
        if (i == num)   return node.key;        //when there perfectly num keys on the left side
        if (i > num)    return select(num, node.left);              //the goal must be on the left side
        else            return select(num - i -1, node.right);  //on the right side, remove the left and current keys
    }


    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node node){
        if (node == null)   return 0;
        int i = key.compareTo(node.key);
        if (i == 0)     return size(node.left);
        if (i < 0)      return rank(key, node.left);
        else            return size(node.left) + 1 + rank(key, node.right);
    }



    public void deleteMin(){
        if (root == null)   throw new NullPointerException("nothing in the tree");
        root = deleteMin(root);
    }

    private Node deleteMin(Node node){
        //if (tree == null)       return null;
        if (node.left == null)  node = node.right;
        else                    node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }


    public void deleteMax(){
        if (root == null)    throw new NullPointerException("nothing in the tree");
        root = deleteMax(root);
    }

    private Node deleteMax(Node node){
        if (node.right == null)     node = node.left;           //reach the maximum, return its left children to its parents
        else                        node.right = deleteMax(node.right);     //still have bigger ones
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }


    public void delete(Key key){
        root = delete(key, root);
    }

    private Node delete(Key key, Node node){
        if (node == null)   return null;
        int i = key.compareTo(node.key);

        if (i == 0){
            if (node.left == null)  return node.right;
            if (node.right == null) return node.left;
            Node X ;
            if (size(node.right) > size(node.left)){        //compare the sizes of two sides
                X = min(node.right);                        //pick one node from the bigger one
                X.right = deleteMin(node.right);
                X.left = node.left;
            }else {
                X = max(node.left);
                X.left = deleteMax(node.left);
                X.right = node.right;
            }
            node = X;
        }
        else if(i < 0) node.left =  delete(key, node.left);
        else           node.right = delete(key, node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }




    public Iterable<Key> keys(){
        List<Key> que = new ArrayList<>();
        keys(root, que);
        return que;
    }

    public Iterable<Key> keys(Key lo, Key hi){
        List<Key> que = new ArrayList<>();
        keys(root, que, lo, hi);
        return que;
    }

    private void keys(Node node, List<Key> Q){
        if (node == null)   return;
        keys(node.left, Q);
        Q.add(node.key);
        keys(node.right, Q);
    }

    private void keys(Node node, List<Key> L, Key lo, Key hi){
        if (node == null)       return ;
        int i = lo.compareTo(node.key), j = hi.compareTo(node.key);
        if (i > 0 || j < 0)     return ;                           //after this statement, node.key is not bigger than hi or smaller than lo
        if (i < 0)      keys(node.left, L, lo, hi);
        L.add(node.key);
        if (j > 0)      keys(node.right, L, lo, hi);
    }

}
