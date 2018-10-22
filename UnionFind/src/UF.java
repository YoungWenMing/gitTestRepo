public class UF implements Union {

    int N;
    int[] id;

    /*
    * initialize the union-find structure for integer N
    * */
    public UF(int N){
        this.N = N;
        id = new int[N];
        for(int i = 0; i < N; i += 1)
            id[i] = i;
    }


    public void union(int p, int q){
        int mi = Math.min(id[p], id[q]), ma = Math.max(id[p], id[q]);
        for(int i = ma; i < N; i+= 1)
            if(id[i] == ma)     id[i] = mi;
        N -= 1;
    }

    public int find(int p){
        return id[p];
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public int count(){
        return N;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Out out = new Out(args[1]);

        UF uf = new UF(in.readInt());
        while(in.hasNextLine()){
            int p = in.readInt();
            int q = in.readInt();
            uf.union(p, q);
            out.println(p + "  " + q);
        }
        out.println(uf.count() + "  components");
        uf.union(1, 10);
        System.out.println( uf.find(2) + "  " + uf.find(9));
    }

}
