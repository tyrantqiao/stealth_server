package com.tyrantQiao.stealth.controller;

import com.tyrantQiao.stealth.POJO.User;
import com.tyrantQiao.stealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class LoginController {
	private UserService userService;

	@Autowired
	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ModelAndView processLoginPage(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult){
		return modelAndView;
	}
}
