package com.tyrantQiao.stealth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * author: TyrantQiao
 * date: 2018/4/20
 * github: github.com/tyrantqiao
 */
@Configuration
public class RedisConfig<K, V> {
	@Bean
	@Autowired
	public RedisTemplate<K, V> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
		return redisTemplate;
	}
}
