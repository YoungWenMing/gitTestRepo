import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdRandom;

import java.util.StringTokenizer;

public class stringGenerator {

    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args){

        int N = Integer.parseInt(args[0]);
        int len = Integer.parseInt(args[1]);
        Out out = new Out(args[2]);

        out.println(args[0]);
        for (int i = 0; i < N; i++){
            String s = "";
            for (int j =0 ; j < len; j ++) {
                s += alphabet.charAt(StdRandom.uniform(26));
            }
            out.println(s );
        }

    }


}
