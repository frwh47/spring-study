package my;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class SimpleServiceTest {
    @Resource
    private SimpleService simpleService;

    @Before
    public void before() {
        Assert.assertNotNull(simpleService);
    }

    @Test
    public void test1() {
        simpleService.test(1);
    }

    @Test
    public void core() {
        simpleService.core(2);
    }

    @Test
    public void work() {
        simpleService.work(3);
    }
}