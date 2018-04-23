package com.tyrantQiao.stealth.service;

import com.tyrantQiao.stealth.entity.User;
import com.tyrantQiao.stealth.mapper.UserMapper;
import com.tyrantQiao.stealth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
@Qualifier("userService")
public class UserService {
	private UserRepository userRepository;
	private UserMapper userMapper;

	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByNameAndPassword(String name, String password) {return userRepository.findByNameAndPassword(name, password);}

	public User findById(Long id) {
		return userMapper.findById(id);
	}

	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}

	public List<User> getAllUsers() {
		return userMapper.getAll();
	}

	public void deleteById(Long id) {userRepository.deleteById(id);}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public boolean isUserExist(Long id) {
		return userMapper.isUserExist(id);
	}
}
