import edu.princeton.cs.algs4.In;

public class TST<Value> {

    /**triple direction search trie for strings
     * */
    private Node root;

    private static class Node{
        Node left, mid, right;
        char k;
        Object v;
    }

    public void put(String s, Value v){
        root = put(root, s, v, 0);
    }

    private Node put(Node n, String s, Value v, int d){
        /**if depth of searching exceeds s.length, do nothing but return the original node**/
        if (d == s.length())
            return n;
        /**when the path does not exist, build new path along string s*/
        if (n == null) {
            n = new Node();
            n.k = s.charAt(d);
        }
        /**if reaching the end of s, put value into it*/
        if (d == s.length() - 1){
            n.v = v;
        }

        int i = s.charAt(d) - n.k;      //when d equals to s.length(), searching has already exceeded extreme
        if (i == 0)
            n.mid = put(n.mid, s, v, d + 1);        // only the middle branch counts
        else if (i < 0)
            n.left = put(n.left, s, v, d);
        else
            n.right = put(n.right, s, v, d);
        return n;
    }

    public Value get(String s){
        Node x =get(s, root, 0);
        if (x == null)  return null;
        return (Value) x.v;
    }

    private Node get(String s, Node n, int d){
        /**if searching exceeds the range of s*/
        //if (d == s.length())    return null;
        if (n == null)          return n;
        int i  = s.charAt(d) - n.k;


        /**turn left */
        if (i < 0)
            return get(s, n.left, d);
        else if (i > 0)
            return get(s, n.right, d);
        else if (d < s.length() - 1)
            return get(s, n.mid, d + 1);
        else
            return n;

    }


    public static void  main(String[] args){
        TST<Integer> t = new TST<>();
        t.put("p", 1);

        System.out.println(t.get("p"));

        In  in = new In("testStrings.txt");
        int i = in.readInt();

        for (;i > 10; i --)
            t.put(in.readLine(), i);

    }
}
