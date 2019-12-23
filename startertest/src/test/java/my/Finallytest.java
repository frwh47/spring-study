package my;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2019/12/22.
 */
public class Finallytest {
    private int add(int a) {
        try {
            return a + 1;
        } finally {
            return a + 2;
        }
    }

    @Test
    public void t1() {
        int a = 3;
        Assert.assertEquals(a + 2, add(a));
    }

}
