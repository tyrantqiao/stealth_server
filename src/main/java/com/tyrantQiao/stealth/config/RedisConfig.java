package com.tyrantQiao.stealth.config;

import com.tyrantQiao.stealth.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * author: TyrantQiao
 * date: 2018/4/20
 * github: github.com/tyrantqiao
 * Description: aiming to create redisTemplate bean which needs lettuceConnectionFactory
 */
@Configuration
public class RedisConfig {

	/**
	 * create a lettuceConnectionFactory for redisTemplate {@link #redisUserTemplate()}
	 * @return LettuceConnectionFactory, the old version use Jedis
	 */
	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory() {
		return new LettuceConnectionFactory();
	}

	/**
	 * Description: aiming to create a template for Long and user(To be a user cache)
	 * @return redisTemplate<Long,User>
	 */
	@Bean
	@Qualifier("redisUserTemplate")
	public RedisTemplate<Long, User> redisUserTemplate() {
		RedisTemplate<Long, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(new LettuceConnectionFactory());
		return redisTemplate;
	}

	/**
	 * This method is used to create redisTemplate bean.
	 * @param redisConnectionFactory Thread-safe factory of Redis connections(It would use the {@link #lettuceConnectionFactory()} to create
	 * @return RedisTemplate<K,V> you can specify the types you want
	 */
	@Bean
	@Qualifier("lockRedisTemplate")
	public RedisTemplate<Long, byte[]> lockRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Long, byte[]> lockTemplate = new RedisTemplate<>();
		lockTemplate.setConnectionFactory(redisConnectionFactory);
		lockTemplate.afterPropertiesSet();
		return lockTemplate;
	}


}
