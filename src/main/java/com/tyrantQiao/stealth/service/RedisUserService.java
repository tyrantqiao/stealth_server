package com.tyrantQiao.stealth.service;

import com.tyrantQiao.stealth.config.RedisConfig;
import com.tyrantQiao.stealth.entity.User;
import com.tyrantQiao.stealth.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * author: TyrantQiao
 * date: 2018/4/20
 * github: github.com/tyrantqiao
 */
@Service(value = "redisUserService")
public class RedisUserService extends RedisConfig {
	private RedisTemplate<Long, User> redisUserTemplate;
	private HashOperations<Long, byte[], byte[]> hashOperations;
	private HashMapper<Object, byte[], byte[]> hashMapper = new ObjectHashMapper();
	private RedisLock redisLock;


	@Autowired
	public void setRedisUserTemplate(RedisTemplate<Long, User> redisUserTemplate) {
		this.redisUserTemplate = redisUserTemplate;
		hashOperations = redisUserTemplate.opsForHash();
	}

	@Autowired
	public void setRedisLock(RedisLock redisLock) {
		this.redisLock = redisLock;
	}

	private RedisTemplate<Long, User> getRedisUserTemplate() {
		return redisUserTemplate;
	}

	private RedisLock getRedisLock() {
		return redisLock;
	}

	@Nullable
	public void saveObject(Long key, User user) {
		Map<byte[], byte[]> mappedHash = hashMapper.toHash(user);
		hashOperations.putAll(key, mappedHash);
	}

	public Object loadObject(Long key) {
		Map<byte[], byte[]> loadedHash = hashOperations.entries(key);
		return hashMapper.fromHash(loadedHash);
	}

	public boolean isValueExist(Long key) {
		return hashOperations.entries(key) != null;
	}

	public void lock(Long id) {
		redisLock.setKey(id);
		redisLock.lock();
	}

	public void unlock() {
		redisLock.unlock();
	}

//	public User getListBy(Long id) {
//		return (User) hashOperations.get(id);
//	}
}
