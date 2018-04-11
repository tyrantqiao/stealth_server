package com.tyrantQiao.stealth.controller;

import com.tyrantQiao.stealth.POJO.User;
import com.tyrantQiao.stealth.service.EmailService;
import com.tyrantQiao.stealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

/**
 * author: tyrantqiao
 */

@RestController
public class RegisterController {
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	private UserService userService;
	private EmailService emailService;

	@Autowired
	public RegisterController(UserService userService, EmailService emailService) {
		this.userService = userService;
		this.emailService = emailService;
	}

	@GetMapping(value = "/register")
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@PostMapping(value = "/register")
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {

		// Lookup user in database by e-mail
		User userExists = userService.findByEmail(user.getEmail());

		System.out.println(userExists);

		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}

		if (bindingResult.hasErrors()) {
			System.out.println("erros: " + bindingResult.getAllErrors());
			modelAndView.setViewName("register");
		} else { // new user so we create user and send confirmation e-mail
			user.setEnabled(false);
			user.setConfirmationToken(UUID.randomUUID().toString());
			System.out.println("confirmationToken is " + user.getConfirmationToken());
			userService.saveUser(user);

			String appUrl = request.getScheme() + "://" + request.getServerName();

			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("Do not reply, thanks. To confirm your e-mail address, please click the link below:\n"
					+ appUrl + ":8080/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("tyrantqiao@qq.com");

			emailService.sendEmail(registrationEmail);

			modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
			modelAndView.setViewName("register");
		}

		return modelAndView;
	}

	@GetMapping(value = "/confirm")
	public void showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {

		User user = userService.findByConfirmationToken(token);

		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
			System.out.println("invalidToken");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());

			user.setEnabled(true);
			userService.saveUser(user);
			System.out.println("successful create account:"+user.getName());
		}
	}

	/**
	 * make password encrypt
	 */
//	@PostMapping(value = "/confirm")
//	public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {
//
//		modelAndView.setViewName("confirm");
//
//		Zxcvbn passwordCheck = new Zxcvbn();
//
//		Strength strength = passwordCheck.measure((String) requestParams.get("password"));
//
//		if (strength.getScore() < 3) {
//			bindingResult.reject("password");
//
//			redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
//
//			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
//			System.out.println(requestParams.get("token"));
//			return modelAndView;
//		}
//
//		User user = userService.findByConfirmationToken((String) requestParams.get("token"));
//
//		user.setPassword(bCryptPasswordEncoder.encode((CharSequence) requestParams.get("password")));
//
//		user.setEnabled(true);
//
//		userService.saveUser(user);
//
//		modelAndView.addObject("successMessage", "Your password has been set!");
//		return modelAndView;
//	}
}
