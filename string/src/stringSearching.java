import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;

public class stringSearching {

    public static int search(String txt, String pat, HashMap<Character, Integer> map){
        int M = pat.length(), N = txt.length();
        for (int i = 0; i <= N - M; i ++){
            int j = 0;
            for (;j < M; j ++) {
                map.put('a', map.get('a') + 1);
                if (txt.charAt(j + i) != pat.charAt(j))
                    break;
            }
            map.put('a', map.get('a') + 1);
            if (j == M)     return i;
        }
        return -1;
    }


    public static int search2(String txt, String pat, HashMap<Character, Integer> map){
        int M = pat.length(), N = txt.length();
        for (int i = 0; i <= N - M; i ++){
            int j = 0, k = i;
            for (;j < M; j ++) {
                map.put('a', map.get('a') + 2);
                if (pat.charAt(j) == txt.charAt(i))
                    k = j + i;
                if (txt.charAt(j + i) != pat.charAt(j)) {
                    i = k;
                    break;
                }
            }
            map.put('a', map.get('a') + 1);
            if (j == M)     return i;
        }
        return -1;
    }

    public static void main(String[] args){
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', 0);
        int N = 300;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < N; i ++)
            s.append((char) StdRandom.uniform(97, 123));
        s.append("kobebraent");
        for (int i = 0; i < N; i ++)
            s.append((char) StdRandom.uniform(97, 123));

        String pat = "kobebraent";
        int p = stringSearching.search(s.toString(), pat, map);
        int c = map.get('a');
        System.out.printf("\nfind %s at %d of txt, with %d times of comparisons", pat, p, c);

        map.put('a', 0);
        p = stringSearching.search2(s.toString(), pat, map);
        c = map.get('a');
        System.out.printf("\nfind %s at %d of txt, with %d times of comparisons", pat, p, c);
        /*
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < N; i ++)
            s1.append('a');
        s1.append('b');
        String txt = s1.toString();

        String pat = "aab";
        p = stringSearching.search(txt, pat, map);
        c = map.get('a');
        System.out.printf("\nfind %s at %d of txt, with %d times of comparisons", pat, p, c);

        map.put('a', 0);
        pat = "aaab";
        p = stringSearching.search(txt, pat, map);
        c = map.get('a');
        System.out.printf("\nfind %s at %d of txt, with %d times of comparisons", pat, p, c);

        map.put('a', 0);
        pat = "aaaab";
        p = stringSearching.search(txt, pat, map);
        c = map.get('a');
        System.out.printf("\nfind %s at %d of txt, with %d times of comparisons", pat, p, c);

        map.put('a', 0);
        pat = "aaaaab";
        p = stringSearching.search(txt, pat, map);
        c = map.get('a');
        System.out.printf("\nfind %s at %d of txt, with %d times of comparisons", pat, p, c);*/
    }
}
