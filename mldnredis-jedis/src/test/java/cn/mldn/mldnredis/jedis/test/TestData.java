package cn.mldn.mldnredis.jedis.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.mldn.util.redis.RedisConnectionUtil;
import redis.clients.jedis.Jedis;

public class TestData {
	private static Jedis jedis;
	static { //优先于所有方法执行，可以做一切初始化操作
		RedisConnectionUtil rcu =new RedisConnectionUtil();
		jedis=rcu.getConnection();
	}
	@Test
	public void testStringData()throws Exception {
		jedis.set("mldn", "java");
		jedis.setex("mldn-key", 2, "www.mldn.cn");
		System.out.println(jedis.get("mldn"));
		TimeUnit.SECONDS.sleep(3);
		System.out.println(jedis.get("mldn-key"));
	}
	@Test
	public void testStringDataMore() {
		for(int x=0;x<1000;x++) {
			jedis.set("mldn-"+x, "java");
		}
	}
	@Test
	public void testKeys()throws Exception{
		Set<String> set=jedis.keys("mldn-*");
		set.forEach(System.out::println);
	}
	@Test
	public void testFlush() {
		jedis.flushDB();
	}
	@Test
	public void testHashData() {
		 jedis.hset("famliy", "爸爸", "杜思康");
		 jedis.hset("famliy", "妈妈", "李贝贝");
		 jedis.hset("famliy", "闺女", "杜诗文");
		 System.out.println(jedis.hget("famliy", "爸爸"));
	}
	@Test
	public void testListData() {
		jedis.lpush("mldn-queue", "dusikang","libeibei","dusiwen","dubowen");
		jedis.rpush("mldn-queue", "shixiuqiu","duruitian");
		List<String> list= jedis.lrange("mldn-queue", 0, -1);
		list.forEach(System.out::println);
		System.out.println("------------");
		System.out.println(jedis.lpop("mldn-queue"));
		System.out.println(jedis.rpop("mldn-queue"));
				
	}
	@Test
	public void testSetData() {
		jedis.sadd("user-mldn", "a","b","c");
		jedis.sadd("user-admin", "a","b","x");
		Set<String> set= jedis.sinter("user-mldn","user-admin");
		 set=jedis.spop("user-admin",1);
		set.forEach(System.out::println);
	}
	@Test
	public void testZsetData() {
		Map<String,Double> map= new HashMap<String,Double>();
		map.put("java",1.0);
		map.put("Redis",2.0);
		jedis.zadd("mldn-hostKey", map);
		Set<String> set= jedis.zrevrange("mldn-hostKey", 0, -1);
		set.forEach(System.out::println);
	}
	
}
