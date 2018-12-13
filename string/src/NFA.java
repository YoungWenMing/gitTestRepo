import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.Stack;

public class NFA {
    /*characters of norm expression*/
    private char[] re;
    /*graph of epsilon transformation*/
    private Digraph G;
    /*quantity of status*/
    private int M;

    public NFA (String exp){
        re = exp.toCharArray();
        M = re.length;
        /*storage special items, including '(','|'*/
        Stack<Integer> ops = new Stack<>();
        G = new Digraph(M + 1);
        /*go through every item in the expression*/
        for (int i =0; i < M; i ++){
            /*let lp to record the left parentheses*/
            int lp = i;
            /*if we get '(' or '|', push it into stack
            * to build special links later*/
            if (re[i] == '(' || re[i] == '|')
                ops.push(i);
            else if (re[i] == ')'){
                int or = ops.pop();
                if (re[or] == '|'){
                    lp = ops.pop();
                    G.addEdge(or, i);
                    G.addEdge(lp, or + 1);
                }else
                    lp = or;
            }
            /*lp record either latest parentheses or current status
            * and its actual function is to cope with '*'*/
            if (i < M - 1 && re[i+ 1] == '*'){
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            /*only when meeting '(', '*' and ')' do we add links, because the graph only has epsilon transformations*/
            if (re[i] == '(' || re[i] == ')' || re[i] == '*')
                G.addEdge(i, i + 1);
        }
    }

    public boolean recognize(String txt){


        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        /*start from status 0, find all vertexes can be reached by epsilon transformations*/
        for (int i = 0; i < G.V(); i ++)
            if (dfs.marked(i))  pc.add(i);

        char[] txtChars = txt.toCharArray();
        int N = txt.length();
        for (int i = 0; i < N; i ++){
            Bag<Integer> match = new Bag<>();
            /*pc contains all status reached by epsilon or matching transformations
            * check these status can match or not, if so go to next status*/
            for (int j : pc)
                /*the txt may partly match exp, but that's not what we want*/
                if (j < M)
                    if (re[j] == txtChars[i] || re[j] == '.')
                        match.add(j + 1);
            pc = new Bag<>();
            /*from matched status, find all reachable status by epsilon links*/
            dfs = new DirectedDFS(G, match);
            for (int j = 0; j < G.V(); j ++)
                if (dfs.marked(j))  pc.add(j);

        }
        for (int i : pc)    if (i == M)     return true;
        return false;
    }

    public static void main(String[] args){
        String pat = "((A*B|AC)D)";
        NFA nfa = new NFA(pat);

        String txt = "AAABD";
        System.out.printf("\n%s matches %s: %b\n", txt, pat, nfa.recognize(pat));
    }
}

/*
*
*
*
* */