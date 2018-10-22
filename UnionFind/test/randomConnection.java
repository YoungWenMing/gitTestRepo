public class randomConnection {
    Union uf;
    int count;
    int N;

    public randomConnection(Union newUnion){
        N = newUnion.count();
        uf = newUnion;
    }

    public int count(){
        return count;
    }

    public void connectAll(){
        while (uf.count() != 1){
            int a = StdRandom.uniform(N);
            int b = StdRandom.uniform(N);
            if(!uf.connected(a, b)){
                uf.union(a, b);
                count += 1;
            }

        }
    }

    public long runtimeDetect(){
        long start = System.currentTimeMillis();
        connectAll();
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args){
        randomConnection RC ; //= new randomConnection(Integer.parseInt(args[0]));
        int N = 1000;
        for(int i = 1; i < 11; i ++){
           RC = new randomConnection(new UF(N));
           System.out.println(N + " nodes: " +RC.runtimeDetect() + " milliseconds with " + RC.count() + " union operations ");
           N *= 2;
        }
        System.out.println("\n");
        N = 1000;
        for(int i = 1; i < 11; i ++){
            RC = new randomConnection(new WeightedQuickUF(N));
            System.out.println(N + " nodes: " +RC.runtimeDetect() + " milliseconds with"+ RC.count() + " union operations ");
            N *= 2;
        }
    }
}
