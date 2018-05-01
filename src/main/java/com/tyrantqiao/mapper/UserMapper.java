package com.tyrantqiao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tyrantqiao.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 复杂的用mapper，简易的查询直接用repository实现。
 * mapper 使用也可以继承BaseMapper<Object> 跟repository继承CrudRepository是一样的
 * date: 2018/4/20
 * github: github.com/tyrantqiao
 *
 * @author tyrantqiao
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
	/**
	 * serach by id, return bool
	 *
	 * @param id userId
	 * @return boolean
	 */
	@Select("SELECT EXISTS (SELECT 1 FROM user WHERE id=#{id})")
	boolean isUserExist(@Param("id") Long id);

	/**
	 * 返回user
	 *
	 * @param id id
	 * @return user
	 */
	@Select("SELECT * FROM user where id=#{id}")
	User findById(@Param("id") Long id);

	/**
	 * all user
	 *
	 * @return all user
	 */
	@Select("SELECT * FROM user where enabled=true")
	List<User> getAll();

	/**
	 * findByEmail
	 *
	 * @param email
	 * @return
	 */
	@Select("SELECT * FROM user where email=#{email}")
	User findByEmail(@Param("email") String email);

	/**
	 * login use
	 *
	 * @param name
	 * @param password
	 * @return
	 */
	@Select("SELECT * FROM user where name=#{name} and password=#{password}")
	User findByNameAndPassword(@Param("name") String name, @Param("password") String password);

	/**
	 * register
	 *
	 * @param confirmationToken
	 * @return
	 */
	@Select("SELECT * FROM user where name=#{confirmationToken}")
	User findByConfirmationToken(@Param("confirmationToken") String confirmationToken);

	/**
	 * delete
	 *
	 * @param id
	 */
	@Delete("DELETE FROM user where id=#{id}")
	void deleteById(@Param("id") Long id);
}
