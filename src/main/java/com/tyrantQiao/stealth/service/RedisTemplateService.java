package com.tyrantQiao.stealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * author: TyrantQiao
 * date: 2018/4/20
 * github: github.com/tyrantqiao
 */
@Service("redisService")
public class RedisTemplateService<H, V> {
	private static final String[] LOCKS = new String[128];
	private String[] locks = RedisTemplateService.LOCKS;
	private RedisTemplate redisTemplate;
	private HashOperations<H, byte[], byte[]> hashOperations = redisTemplate.opsForHash();
	private HashMapper<Object, byte[], byte[]> hashMapper = new ObjectHashMapper();

	static {
		for (int i = 0; i < 128; i++) {
			LOCKS[i] = "lock_" + i;
		}
	}

	@Autowired
	public RedisTemplateService(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void saveObject(H key, V value) {
		Map<byte[], byte[]> mappedHash = hashMapper.toHash(value);
		hashOperations.putAll(key, mappedHash);
	}

	public Object loadObject(H key) {
		Map<byte[], byte[]> loadedHash = hashOperations.entries(key);
		return hashMapper.fromHash(loadedHash);
	}

	//TODO Not sure this gonna be worked.
	public boolean isValueExist(H key) {
		return hashOperations.entries(key) != null;
	}

	public void lock(Long id) {
		int index = id.hashCode() & (locks.length - 1);
		//TODO distributed redis lock.
	}

//	public User getListBy(Long id) {
//		return (User) hashOperations.get(id);
//	}
}
