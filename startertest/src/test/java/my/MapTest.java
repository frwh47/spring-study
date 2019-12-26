package my;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/25.
 */
public class MapTest {
    private StopWatch watch = new StopWatch();
    private final int LOOP_TIMES = 1000 * 1000 * 5;

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testHashtable() {
        Map<Integer, String> map = new Hashtable<>();
        testMap(map);
    }

    @Test
    public void testMyMap() {
        Map<Integer, String> map = new MyMap<>();
        testMap(map);
    }

    @Test
    public void testHashMap() {
        Map<Integer, String> map = new HashMap<>();
        testMap(map);
    }

    @Test
    public void performanceHashtable() {
        Map<Integer, String> map = new Hashtable<>();
        testMapPerformance(map);
    }

    @Test
    public void performanceMyMap() {
        Map<Integer, String> map = new MyMap<>();
        testMapPerformance(map);
    }

    @Test
    public void performanceHashMap() {
        Map<Integer, String> map = new HashMap<>();
        testMapPerformance(map);
    }

    private void testMap(Map<Integer, String> map) {
        String value = null;
        final int loopTimes = LOOP_TIMES / 100;
        for (int key = 1; key <= loopTimes; key++) {
            value = "v" + key;
            map.put(key, value);
            Assert.assertTrue(map.containsKey(key));
            Assert.assertEquals(value, map.get(key));
            Assert.assertEquals(key, map.size());
        }
        for (int key = loopTimes; key > 0; key--) {
            value = map.get(key);
            Assert.assertEquals("v" + key, value);

            Assert.assertEquals(key, map.size());
            value = map.remove(key);
            Assert.assertEquals("v" + key, value);
            Assert.assertFalse(map.containsKey(key));
        }
        Assert.assertNotNull(value);
    }

    private void testMapPerformance(Map<Integer, String> map) {
        String value = null;
        watch = new StopWatch();
        watch.start();
        for (int key = 1; key <= LOOP_TIMES; key++) {
            value = "v" + key;
            map.put(key, value);
        }
        watch.stop();
        System.out.println(map.getClass().getName() + ", put " + watch.getTotalTimeSeconds() + " seconds");

        watch = new StopWatch();
        watch.start();
        for (int key = LOOP_TIMES; key > 0; key--) {
            value = map.get(key);
        }
        watch.stop();
        System.out.println(map.getClass().getName() + ", get " + watch.getTotalTimeSeconds() + " seconds");

        watch = new StopWatch();
        watch.start();
        for (int key = LOOP_TIMES; key > 0; key--) {
            value = map.remove(key);
        }
        watch.stop();
        System.out.println(map.getClass().getName() + ", remove " + watch.getTotalTimeSeconds() + " seconds");

        Assert.assertNotNull(value);
    }

    @Test
    public void performanceMod() {
        int sum = 0;
        for (int i = 0; i < LOOP_TIMES; i++) {
            sum += i % 16;
        }
        System.out.println("sum = " + sum);
    }

    @Test
    public void performanceAnd() {
        int sum = 0;
        for (int i = 0; i < LOOP_TIMES; i++) {
            sum += i & 15;
        }
        System.out.println("sum = " + sum);
    }
}
