package com.tyrantQiao.stealth.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping("/")
	public String home(){
		return "hello, this is a page to sign in or sign up account for unity";
	}
}
