package com.tyrantqiao.service;

import com.tyrantqiao.entity.User;
import com.tyrantqiao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询可选，mapper和repository，为了锻炼写sql，就不用repository了
 *
 * @author tyrantqiao
 */
@Service("userService")
@CacheConfig(cacheNames = "users")
public class UserService {
	private UserMapper userMapper;

	@Autowired
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Cacheable(cacheNames = "user1", key = "#email")
	public User findByEmail(String email) {
		return userMapper.findByEmail(email);
	}

	public User findByNameAndPassword(String name, String password) {return userMapper.findByNameAndPassword(name, password);}

	@Cacheable(cacheNames = "user1", key = "#id")
	public User findById(Long id) {
		return userMapper.findById(id);
	}

	@Cacheable(cacheNames = "user1", key = "#confirmationToken")
	public User findByConfirmationToken(String confirmationToken) {
		return userMapper.findByConfirmationToken(confirmationToken);
	}

	public List<User> getAllUsers() {
		return userMapper.getAll();
	}

	@CacheEvict(cacheNames = "user1", key = "#id")
	public void deleteById(Long id) {userMapper.deleteById(id);}

	@CachePut(cacheNames = "user1", key = "#user.id")
	public void saveUser(User user) {
		userMapper.insert(user);
	}

	public boolean isUserExist(Long id) {
		return userMapper.isUserExist(id);
	}
}
