import java.util.HashMap;

public class Alphabet {

    private int R;
    private HashMap<Character, Integer> indexMap;
    private char[] chars;

    public Alphabet(String s){
        //int R = 0;
        //chars = new char[R];
        indexMap = new HashMap<>();
        for (int i = 0;i < s.length(); i ++){
            //chars[i] = s.charAt(i);
            char c = s.charAt(i);
            if (!indexMap.containsKey(c)) {
                indexMap.put(c, R++);
            }
        }

        chars = new char[R];
        for (char c : indexMap.keySet())
            chars[indexMap.get(c)] = c;

    }

    public boolean contains(char c){
        return indexMap.containsKey(c);
    }

    public int toIndex(char c){
        return indexMap.get(c);
    }

    public char toCharacter(int i){
        return chars[i];
    }

    public static void main(String[] args){
        String a = "abcdefghijklmnopqrstuvwxyz";
        Alphabet table = new Alphabet(a);

        System.out.println(table.toIndex('d'));
    }

    public int size(){  return R;}
}
