import org.junit.Test;

import java.util.Random;

public class testUF {

    @Test
    public void testUF(){
        UF uf = new UF(20);
        uf.union(1, 2);
        uf.union(2, 3);
        assert uf.connected(1, 3);
        assert uf.count() == 18;

        assert uf.find(3) == 1;
    }

    @Test
    public void testRunTime(){

        for(int i = 0; i < 1000; i ++){
            if(StdRandom.uniform(10) == 10)
                throw new ArrayIndexOutOfBoundsException("something wrong ");
        }
    }
}
