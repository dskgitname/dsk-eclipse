package cn.mldn.util.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionUtil {
	//redis主机的Ip地址
	private static final String REDIS_HOST="192.168.94.141";
	private static final int REDIS_PORT=6379;
	private static final String REDIS_AUTH="mldnjava";
	private static final int MAX_TOTAL=200;//最大存在有200个连接
	private static final int MAX_IDLE=30;//没人访问维持30个连接
	private static final int TIMEOUT=1000;//连接超时等待
	private static final boolean TEST_ON_BORROW =true;//是否测试
	private JedisPool jedisPool=null;//定义的是Jedis连接池对象
	public RedisConnectionUtil() {//在构造方法之中进行Redis连接
		JedisPoolConfig config= new JedisPoolConfig();
		config.setMaxTotal(MAX_TOTAL);//最大连接数
		config.setMaxIdle(MAX_IDLE);//最大维持连接数
		config.setMaxWaitMillis(TIMEOUT);//连接满之后等待时间
		config.setTestOnBorrow(TEST_ON_BORROW);//是否经过测试后返回可用连接
		this.jedisPool=new JedisPool(config,REDIS_HOST,REDIS_PORT,TIMEOUT,REDIS_AUTH);
	}
	public void close() {
		if(this.jedisPool!=null) {
			this.jedisPool.close();
		}
	}
	public Jedis getConnection() {
		return this.jedisPool.getResource();//从连接池中获取连接
	}
	}
