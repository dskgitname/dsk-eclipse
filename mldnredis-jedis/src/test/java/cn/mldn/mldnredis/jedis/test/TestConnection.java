package cn.mldn.mldnredis.jedis.test;

import org.junit.Test;

import cn.mldn.util.redis.RedisConnectionUtil;
import redis.clients.jedis.Jedis;

public class TestConnection {
	@Test
	public void testConnection() {
		RedisConnectionUtil rcu =new RedisConnectionUtil();
		Jedis jedisPool=rcu.getConnection();
		System.out.println(jedisPool);
		jedisPool.close();
	}
}
