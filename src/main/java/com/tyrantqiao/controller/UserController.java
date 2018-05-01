package com.tyrantqiao.controller;

import com.tyrantqiao.entity.User;
import com.tyrantqiao.mapper.UserMapper;
import com.tyrantqiao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private UserService userService;
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
		return userMapper.findById(id);
	}
}


