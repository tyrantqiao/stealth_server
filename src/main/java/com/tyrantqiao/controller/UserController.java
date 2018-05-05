package com.tyrantqiao.controller;

import com.tyrantqiao.entity.User;
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
 * @author TyrantQiao [tyrantqiao@gmail.com]
 * date: 2018/4/20
 * github: github.com/tyrantqiao
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/getAll")
	public List<User> getAll() {
		return userService.getAllUsers();
	}

	@GetMapping("/get")
	public User getUserById(@RequestParam(name = "id") Long id) {
		return userService.getById(id);
	}

	/**
	 * 因为我们没有发出delete的请求，而是通过service调用mapper删除，这个本质上只是{@code @GetMapping()}
	 * 不用加事务也是可以的，但如果搭配{@code @DeleteMapping()}就需要了，因为现在的代码真正执行点不在这里。
	 * @param id
	 */
	@GetMapping("/delete")
	public void deleteUserById(@RequestParam(name = "id") Long id) {
		userService.deleteById(id);
	}
}


