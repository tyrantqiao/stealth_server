package com.tyrantqiao.controller;

import com.tyrantqiao.entity.User;
import com.tyrantqiao.service.EmailService;
import com.tyrantqiao.service.ResultService;
import com.tyrantqiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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
	private ResultService resultService;

	@Autowired
	public RegisterController(UserService userService, EmailService emailService, ResultService resultService) {
		this.userService = userService;
		this.emailService = emailService;
		this.resultService = resultService;
	}

	@GetMapping(value = "/register")
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user, HttpServletRequest request) {
		try {
			modelAndView.addObject("user", user);
			modelAndView.setViewName("register");
			return modelAndView;
		} catch (Exception e) {
			User userExists = userService.getByEmail(user.getEmail());
			modelAndView.setView(new MappingJackson2JsonView());
			if (userExists != null) {
				modelAndView.addObject(
						"result", resultService.error(
								500,
								"already had account by email:" + user.getEmail(), user));
				return modelAndView;
			} else {
				user.setEnabled(false);
				user.setConfirmationToken(UUID.randomUUID().toString());
				System.out.println("confirmationToken is " + user.getConfirmationToken());
				userService.saveUser(user);

				String appUrl = request.getScheme() + "://" + request.getServerName();
				var registrationEmail = emailService.createMailMessage(
						user.getEmail(),
						"tyrantqiao@qq.com",
						"Registration Confirmation",
						"Do not reply, thanks. To confirm your e-mail address, please click the link below:\n"
								+ appUrl + ":8080/confirm?token=" + user.getConfirmationToken());
				emailService.sendEmail(registrationEmail);
			}
			modelAndView.addObject("result", resultService.success(user));
			return modelAndView;
		}
	}

	// make postMapping void

//	@PostMapping(value = "/register")
//	public Result<User> processRegistrationForm(@Valid User user, HttpServletRequest request) {
//		User userExists = userService.getByEmail(user.getEmail());
//		if (userExists != null) {
//			return resultService.error(500, "already had account by email:" + user.getEmail(), user);
//		} else {
//			user.setEnabled(false);
//			user.setConfirmationToken(UUID.randomUUID().toString());
//			System.out.println("confirmationToken is " + user.getConfirmationToken());
//			userService.saveUser(user);
//
//			String appUrl = request.getScheme() + "://" + request.getServerName();
//			var registrationEmail = emailService.createMailMessage(
//					user.getEmail(),
//					"tyrantqiao@qq.com",
//					"Registration Confirmation",
//					"Do not reply, thanks. To confirm your e-mail address, please click the link below:\n"
//							+ appUrl + ":8080/confirm?token=" + user.getConfirmationToken());
//			emailService.sendEmail(registrationEmail);
//		}
//		return resultService.success(user);
//	}

	@PostMapping(value = "/register")
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {

		User userExists = userService.getByEmail(user.getEmail());

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

		User user = userService.getByConfirmationToken(token);

		if (user == null) {
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
			System.out.println("invalidToken");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());

			user.setEnabled(true);
			userService.saveUser(user);
			System.out.println("successful create account:" + user.getName());
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
//		User user = userService.getByConfirmationToken((String) requestParams.get("token"));
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
