import java.io.PrintStream;

public class BoyerMoore {

    private int right[];
    private String pat;

    public BoyerMoore(String pat){
        /**construct the right array to record the most right position in pattern
         * of identical characters*/
        int R = 256;
        this.pat = pat;
        right = new int[R];

        int M = pat.length();

        for (int i = 0; i < R; i ++)
            right[i] = -1;
        /*record the most right index of every valid character. */
        for (int i = 0; i < M; i ++)
            right[pat.charAt(i)] = i;
    }

    public int search(String txt){
        int M = pat.length(), N = txt.length(), i, j;
        for (i = 0; i <= N - M; ){
            int t = 0;
            for (j = M - 1; j >= 0; j --){
                /*compare every character from right to left*/
                char c = txt.charAt(i + j);
                /*if we meet a different character, some movements must be done
                * what we should ensure that every movement is rightwards
                * considering that left string episode can not match pattern*/
                if (c != pat.charAt(j)){
                    t = j - right[c];
                    t = t > 0? t: 1;        //to make sure we only move rightwards
                    break;                  //because of not matching characters, we move the pattern rightwards
                }
            }
            /*t equals to 0, means no movement is needed, we got matching episode*/
            if (t == 0)     return i;
            i += t;
        }
        return N;
    }


    public static void main(String[] args){
        PrintStream outAlias = new PrintStream(System.out);

        String x = "ABABAC";
        BoyerMoore k = new BoyerMoore(x);


        outAlias.println(k.search("ABABABAC"));


        String s = "ABCABBCBBABABAABABAC";
        outAlias.println(k.search(s));
    }

}
