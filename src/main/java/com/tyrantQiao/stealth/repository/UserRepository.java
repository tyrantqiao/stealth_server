package com.tyrantQiao.stealth.repository;

import com.tyrantQiao.stealth.POJO.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
	User findByNameAndPassword(String name,String password);
}
