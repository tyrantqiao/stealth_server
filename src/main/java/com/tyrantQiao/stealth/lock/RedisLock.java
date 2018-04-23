package com.tyrantQiao.stealth.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Create by TyrantQiao on 2018/4/21
 * Description: aiming to lock key to achieve the goal of distributed service.
 * github: github.com/tyrantqiao
 */
@Component
public class RedisLock implements Lock {
	private RedisTemplate<Long, byte[]> lockRedisTemplate = getLockRedisTemplate();
	private static final String[] LOCKS = new String[128];
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

	static {
		for (int i = 0; i < 128; i++) {
			LOCKS[i] = "lock_" + i;
		}
	}

	// 请求锁的超时时间(ms) 若请求时间超过，那么则放弃，开始重新申请，避免长时间堵塞在一个锁上面
	private static final long TIME_OUT = 30000;

	// 锁的有效时间(s)
	private static final int EXPIRE = 60;

	// 锁标志对应的key;  constructor 定义了
	private Long key;

	// 锁状态
	private volatile boolean isLocked = false;

	@Autowired
	@Lazy
	@Qualifier("lockRedisTemplate")
	public void setLockRedisTemplate(RedisTemplate<Long, byte[]> lockRedisTemplate) {
		this.lockRedisTemplate = lockRedisTemplate;
	}

	private RedisTemplate<Long, byte[]> getLockRedisTemplate() {
		return lockRedisTemplate;
	}

	/**
	 * this lock use red Lock algorithms
	 * 1. If request lock's time > timeout(block time), need to end requesting, try next lock.
	 * 2. when get the lock, try send a object's key be lock's key, pre-defind object be lock's value
	 * 3. set expire time prevent the case never complete
	 */
	@Override
	public void lock() {
		Long nowTime = System.nanoTime();
		Long timeout = TIME_OUT * 1000000;
		String[] locks = RedisLock.LOCKS;
		int index = key.hashCode() & (locks.length - 1);
		Random r = new Random();
		try {
			while ((System.nanoTime() - nowTime) < timeout) {
				//setNX will return boolean value, but also do the things.
				if (lockRedisTemplate.getConnectionFactory().getConnection().setNX(new byte[]{key.byteValue()}, locks[index].getBytes())) {
					//expire will use byte[] rawKey=rawKey(key).
					lockRedisTemplate.expire(key, EXPIRE, TimeUnit.SECONDS);
					isLocked = true;
					break;
				} else {
					//This nanoseconds is meant to prevent the case two thread request the lock Simultaneously.
					Thread.sleep(3, r.nextInt(500));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void unlock() {
		if (isLocked)
			lockRedisTemplate.delete(key);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}


	@Override
	public Condition newCondition() {
		return null;
	}

	public void setKey(Long id) {
		this.key = id;
	}
}
