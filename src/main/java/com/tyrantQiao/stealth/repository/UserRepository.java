package com.tyrantQiao.stealth.repository;

import com.tyrantQiao.stealth.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Not recommend to make @cache* annotation put on the interface
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
	User findByNameAndPassword(String name,String password);
	void deleteById(Long id);
	List<User> getAllByEnabled(boolean enabled);
}
