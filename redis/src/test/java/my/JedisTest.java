package my;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {
    private static final HostAndPort node = new HostAndPort("10.5.51.84", 6379);
    private static JedisCluster jedisCluster;
    private static JedisPool jedisPool;

    private static JedisCluster initJedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(node);
        //     nodes.add(node2);

        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();
        return new JedisCluster(nodes, jedisPoolConfig);
    }

    private static JedisPool initJedisPool() {
        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();

        return new JedisPool(jedisPoolConfig, node.getHost(), node.getPort());
    }

    private static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(100);
        config.setMaxTotal(500);
        config.setMinIdle(10);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        config.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        config.setTestOnBorrow(true);
        return config;
    }

    @BeforeAll
    public static void beforeAll() {
        jedisPool = initJedisPool();
    }

    @Test
    public void test() {
        String key = "k1";
        String value = "v1";
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            String ret = jedis.get(key);
            System.out.println(ret);
            Assertions.assertEquals(value, ret);
        }
    }
}
