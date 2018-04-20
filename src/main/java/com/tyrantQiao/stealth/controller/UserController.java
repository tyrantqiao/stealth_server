package com.tyrantQiao.stealth.controller;

import com.tyrantQiao.stealth.POJO.User;
import com.tyrantQiao.stealth.service.OrderService;
import com.tyrantQiao.stealth.service.RedisTemplateService;
import com.tyrantQiao.stealth.service.ResultService;
import com.tyrantQiao.stealth.service.UserService;
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

	private UserService userService;
	private OrderService orderService;
	private ResultService resultService;
	private RedisTemplateService redisTemplateService;

	@Autowired
	public UserController(RedisTemplateService redisTemplateService, UserService userService, OrderService orderService, ResultService resultService) {
		this.redisTemplateService = redisTemplateService;
		this.userService = userService;
		this.orderService = orderService;
		this.resultService = resultService;
	}

	@GetMapping("/getAll")
	public List<User> getAllUser() {
		return userService.getAllUsers();
	}

	@GetMapping("/get")
	public User getUserById(@RequestParam(name = "id") Long id) {
		try {
			if (redisTemplateService.isValueExist(id)) {
				return (User) redisTemplateService.loadObject(id);
			} else {
				redisTemplateService.lock(id);
				if (redisTemplateService.isValueExist(id)) {
					return (User) redisTemplateService.loadObject(id);
				} else {
					if (userService.isUserExist(id)) {
						User user = userService.findById(id);
						redisTemplateService.saveObject(id, user);
						return user;
					} else {
						redisTemplateService.saveObject(id, null);
						return null;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {

		}
		return null;
	}
}


