package my;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2019/12/21.
 */
public class StringTest {
    @Test
    public void t1() {
        String s = "a";
        final String s1 = "a";
        Assert.assertSame(s, s1);

        String s2 = new String(s);
        Assert.assertNotSame(s1, s2);
        String s3 = new String("a");
        Assert.assertNotSame(s1, s3);
    }

    @Test
    public void t2() {
        String a = "a";
        final String b = "b";
        final String c = a + b;
        String d = a + b;
        String e = a + "b";
        String f = "a" + b;
        String g = "a" + "b";
        String h = "ab";
        String i = new String(h);

        Assert.assertNotSame(c, h);
        Assert.assertNotSame(d, h);
        Assert.assertNotSame(e, h);
        Assert.assertSame(f, h);
        Assert.assertSame(g, h);
        Assert.assertNotSame(i, h);
    }

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
