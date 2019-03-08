package cn.mldn.mldnredis.jeids.test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestConnection {
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	@Autowired
	private RedisTemplate<String, String> redisTemplate ;
	@Test
	public void testStringDataMore()throws Exception{
		for(int x=0;x<1000;x++) {
			this.redisTemplate.opsForValue().set("mldn-"+x, "java");
		}
	}
	@Test
	public void testKeys()throws Exception{
		Set<String> set=this.redisTemplate.keys("mldn-*");
		set.forEach(System.out::println);
	}
	@Test
	public void testFlush()throws Exception{
		this.jedisConnectionFactory.getConnection().flushDb();
		
	}
	@Test
	public void testStringData()throws Exception{
		this.redisTemplate.opsForValue().set("mldn", "java");
		this.redisTemplate.opsForValue().set("mldn-key", "mldnjava",2,TimeUnit.SECONDS);
		System.out.println(this.redisTemplate.opsForValue().get("mldn"));
		TimeUnit.SECONDS.sleep(3);
		System.out.println(this.redisTemplate.opsForValue().get("mldn-key"));
	}
	@Test
	public void testHashData()throws Exception{
		this.redisTemplate.opsForHash().put("mldn-famliy", "dady", "杜思康");
		this.redisTemplate.opsForHash().put("mldn-famliy", "mamu", "李贝贝");
		System.out.println(this.redisTemplate.opsForHash().get("mldn-famliy", "dady"));
	}
}
