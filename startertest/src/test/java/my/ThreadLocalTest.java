package my;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2019/12/21.
 */
public class ThreadLocalTest {
    @Test
    public void t3() {
        final int value1 = 3;
        ThreadLocal<Integer> tl1 = new ThreadLocal<>();
        tl1.set(value1);
        Assert.assertEquals(value1, tl1.get().intValue());

        ThreadLocal<Integer> tl2 = new ThreadLocal<>();
        final int value2 = 5;
        tl2.set(value2);
        Assert.assertEquals(value2, tl2.get().intValue());

        Assert.assertEquals(value1, tl1.get().intValue());
    }
}
