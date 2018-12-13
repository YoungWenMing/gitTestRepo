import java.util.ArrayList;
import java.util.Collection;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

public class TrieST<Value> {

    private Node root;
    private static int R = 256;

    private static class Node{
        private Object val;
        private Node[] next = new Node[R];
    }

    public void put( String a, Value v){
        root = put(root, a, v, 0);
    }

    private Node put(Node start, String a, Value v, int d){
        /*terminating condition for recursion*/
        if (start == null)      start = new Node();
        /*when reaching string a's end, directly set its val to d*/
        if (d == a.length()){
            start.val = v;
            return start;
        }
        char c = a.charAt(d);
        start.next[c] = put(start.next[c], a, v, d + 1);
        return start;

    }



    public Value get(String a) {
        /*
        Node start = root;
        for (int i = 0; i < a.length(); i ++) {
            start = start.next[a.charAt(i)];
            if (start == null)      return null;
        }
        return (Value) start.val;*/

        Node n = get(a, root ,0);
        if (n == null)  return null;
        return (Value) n.val;
    }

    private Node get(String a, Node n, int d){
        /*when reaching the end of a branch of this trie, return null*/
        if (n == null)      return null;
        /**when d is maximum index of a, return this node*/
        if (d == a.length() - 1)    return n;
        /*when
        if (d >= a.length())        return null;this statement can never be reached */
        return get(a, n.next[a.charAt(d)], d +1);


    }


    /*start at node n, collect all meaningful strings alongside the path to the end in to a queue*/
    private void collect(Node n, String a, Queue<String> q){
        if (n == null)      return ;
        if (n.val != null)      q.enqueue(a);       //a node is meaningful if it has a nonnull value
        for (char i = 0; i < R; i ++)
            collect(n.next[i], a + i , q);
    }

    private void collect(Node n, String pre, String pat, Queue<String> q){
        int d = pre.length();
        if (n == null)      return;
        /**when reach the level of pat's length*/
        if (n.val != null && d == pat.length())  q.enqueue(pre);
        if (d == pat.length())              return;

        char a  = pat.charAt(d);
        for (char c = 0; c < R; c ++)
            if (a == '.' || c == a)
                collect(n.next[c], pre + c, pat, q);
    }


    /**find all keys with prefix p
     * we first find the last node conform to p
     * then collect all legal strings with method string*/
    public Iterable<String> keysWithPrefix(String p){
        Queue<String> q = new Queue<>();
        Node n = get(p, root, 0);
        collect(n, p, q);
        return q;
    }

    /**find all keys, just collect from the start*/
    public Iterable<String> keys(){
        Queue<String> q = new Queue<>();
        collect(root, "", q);
        return q;
    }

    /**search keys conform to pre, where "." can match any characters*/
    public Iterable<String> keysThatMatch(String pat){
        Queue<String> q = new Queue<>();
        collect(root, "", pat, q);
        return q;
    }


    /**find the longest prefix in the trie*/
    public String longestPrefix(String s){
        int l = search(root, s, 0, 0);      //start from 0
        return s.substring(0, l);
    }

    /*search the longest episode of string a that matching strings
    * in this trie, start from d
    * noting that the bottom of the trie must has a val
    * if the depth exceed the length of a, a must match some strings' prefix
    * otherwise, a is longer than its route in the trie,
    * just return the bottom key
    * for example, "abcd" is in it
    * when searching "abc", outcome is 2 with "abc"
    * when searching "abcde", outcome is 3 with "abcd"
    * */
    private int search(Node n, String a, int d, int l){
        if (n == null)      return l;   //we exceed the bottom here, l record the maximum length of this route
        if (n.val != null)  l = d;      //when this condition is satisfied, we may reach the bottom of trie
        if (d == a.length())    return a.length();      //here, a's length absolutely exceed the length of current route
        char c = a.charAt(d);
        return search(n.next[c], a, d + 1, l);
    }

    /**delete a key if it is in this trie
     * A part of another route this key may be, we do nothing more than
     * setting its val to null
     * delete the whole node otherwise*/
    public void delete(String a){
        root = delete(a, root, 0);
    }

    private Node delete(String a, Node n, int d){
        /**when this key actually does not exist in this trie*/
        if (n == null)      return null;
        if (a.length() == d){
            //a matches strings in the trie
            n.val = null;
        }
        else{
            char c = a.charAt(d);
            n.next[c] = delete(a, n.next[c], d + 1);
        }

        if (n.val != null)      return n;       //when having val, deletion process ends here


        /**if val is null, we must check pointers*/
        for (char c = 0; c < R; c ++)
            if (n.next[c] != null)  return n;       //if there is any child node pointed by n, do nothing
        return null;                                //return null otherwise
    }




    private Queue<String> longestPrefix(Node n, String a, Queue<String> q, int l){
        if (n == null)      return q;
        int x = a.length();
        if (n.val != null){
            if (x > l){
                q = new Queue<>();
                q.enqueue(a);
                l = x;
            }else if (x == l){
                q.enqueue(a);
            }
        }
        for (char i = 0; i < R; i ++)
            q = longestPrefix(n.next[i], a + i, q, l);
        return q;
    }

    public static void main (String[] args) {
        String s1 = "abc", s2 = "abd";
        TrieST<Integer> t = new TrieST<>();
        t.put(s1, 1);
        t.put(s2, 2);

        In in = new In(args[0]);
        int n = Integer.parseInt(in.readLine());
        for (; n > 15; n --)
            t.put(in.readLine(), n);

        Iterable<String> iter = t.keys();
        System.out.println(iter);

        Iterable<String> iter2 = t.keysWithPrefix("iy");
        System.out.println(iter2);

        System.out.println("the longest prefix of abcoie is :");
        System.out.println(t.longestPrefix("abcoie"));
    }
}
