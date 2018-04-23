package com.tyrantQiao.stealth.controller;

import com.tyrantQiao.stealth.entity.User;
import com.tyrantQiao.stealth.mapper.UserMapper;
import com.tyrantQiao.stealth.service.RedisUserService;
import com.tyrantQiao.stealth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: TyrantQiao
 * date: 2018/4/20
 * github: github.com/tyrantqiao
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("redisUserService")
	private RedisUserService redisUserService;
	private UserMapper userMapper;

	public UserController(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@GetMapping("/getAll")
	public List<User> getAll() {
		return userMapper.getAll();
	}

	@GetMapping("/get")
	public User getUserById(@RequestParam(name = "id") Long id) {
		try {
			if (redisUserService.isValueExist(id)) {
				return (User) redisUserService.loadObject(id);
			} else {
				redisUserService.lock(id);
				if (redisUserService.isValueExist(id)) {
					return (User) redisUserService.loadObject(id);
				} else {
					if (userService.isUserExist(id)) {
						User user = userService.findById(id);
						redisUserService.saveObject(id, user);
						return user;
					} else {
						redisUserService.saveObject(id, null);
						return null;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			redisUserService.unlock();
		}
		return null;
	}
}


