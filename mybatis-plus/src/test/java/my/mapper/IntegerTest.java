package my.mapper;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class IntegerTest {
    @Test
    public void t1() {
        Integer n1 = 10;
        Integer n2 = 10;
        Assert.assertEquals(n1, n2);
        Assert.assertSame(n1, n2);

        Integer n3 = new Integer(10);
        Assert.assertEquals(n1, n3);
        Assert.assertNotSame(n1, n3);

        Integer n4 = (n1 + n3) / 2;
        Assert.assertEquals(n1, n4);
        Assert.assertSame(n1, n4);
    }
}
