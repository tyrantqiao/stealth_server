package com.tyrantqiao.repository;

import com.tyrantqiao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 简易的查询就用repository来实现
 *
 * @author tyrantqiao
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);

	User findByConfirmationToken(String confirmationToken);

	User findByNameAndPassword(String name, String password);

	@Override
	void deleteById(Long id);

	List<User> getAllByEnabled(boolean enabled);
}
