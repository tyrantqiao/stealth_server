package com.tyrantqiao.controller;

import com.tyrantqiao.pojo.Result;
import com.tyrantqiao.entity.User;
import com.tyrantqiao.enums.ResultEnum;
import com.tyrantqiao.service.ResultService;
import com.tyrantqiao.service.UserService;
import com.unboundid.ldap.sdk.BindResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class LoginController {
	private UserService userService;
	private ResultService resultService;

	@Autowired
	public LoginController(UserService userService, ResultService resultService) {
		this.userService = userService;
		this.resultService = resultService;
	}

	@PostMapping(value = "/login")
	public ModelAndView processLoginPage(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult) {
		User userExists = userService.findByNameAndPassword(user.getName(), user.getPassword());

		if (userExists == null) {
			modelAndView.addObject("errorMessage", "Name or password is wrong, or you might not register the account.");
			modelAndView.setViewName("login");
			bindingResult.reject("name or password");
		}

		if (bindingResult.hasErrors()) {
			System.out.println("erros: " + bindingResult.getAllErrors());
			modelAndView.setViewName("login");
		} else {
			modelAndView.addObject("successMessage", "Welcome my friend player " + user.getId());
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@GetMapping(value = "/login")
	@ResponseBody
	public Result processLoginPage(@RequestParam("name") String name, @RequestParam("password") String password, BindResult bindResult) {
		User userExists = userService.findByNameAndPassword(name, password);
		if (userExists != null) {
			return resultService.success(userExists);
		}
		return resultService.error(ResultEnum.NOTFOUND);
	}
}
