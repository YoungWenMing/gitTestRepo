public class WeightedQuickUF implements Union {

    private int[] id;
    private int[] depth;
    int     count;

    public WeightedQuickUF(int N){
        count = N;
        id  = new int[N];
        depth = new int[N];
        for(int i = 0; i < N ; i += 1){
            id[i] = i;
            depth[i] = 1;
        }
    }

    public int count(){
        return count;
    }

    public int find(int p){
        int pos = id[p];
        while (pos != p){
            p = pos;
            pos = id[pos];
        }
        return pos;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public void union(int p, int q){
        p = find(p);
        q = find(q);
        if(depth[p] >= depth[q]){
            id[q] = p;
            depth[p] += depth[q];
        }else {
            id[p] = q;
            depth[q] += depth[p];
        }
        count --;
    }

}
