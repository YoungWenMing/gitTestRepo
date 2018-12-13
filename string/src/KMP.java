import java.awt.desktop.OpenURIEvent;
import java.io.PrintStream;
import java.util.Map;

public class KMP {

    private Alphabet alpha;
    private int[][] dfa;
    private int R;
    private String pat;

    public KMP(String pat){
        alpha = new Alphabet(pat);
        this.pat = pat;
        this.R = alpha.size();

        int M = pat.length();

        dfa = new int[R][M];
        int X = 0;

        dfa[alpha.toIndex(pat.charAt(0))][0] = 1;
        for (int i = 1; i < M; i ++){
            //handle next state
            for (int j = 0; j < R; j++)
                dfa[j][i] = dfa[j][X];
            dfa[alpha.toIndex(pat.charAt(i))][i] = i + 1;
            System.out.println("back state number is " + X);
            X = dfa[alpha.toIndex(pat.charAt(i))][X];
        }
    }

    public int search(String txt){
        int i = 0, M = pat.length(), N = txt.length(),  j = 0;
        for (; j < M && i < N; i ++)
            j = dfa[alpha.toIndex(txt.charAt(i))][j];
        if (j == M)     return i - M;
        return  N;

    }

    public String dfaToString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i ++) {
            for (int j = 0; j < pat.length(); j++)
                sb.append(dfa[i][j] + "  ");
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args){
        PrintStream outAlias = new PrintStream(System.out);

        String x = "ABABAC";
        KMP k = new KMP(x);
        outAlias.print(k.dfaToString());

        outAlias.println(k.search("ABABABAC"));


        String s = "ABCABBCBBABABAABABAC";
        outAlias.println(k.search(s));
    }
}
